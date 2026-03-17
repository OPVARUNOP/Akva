package com.varun.akva.services

import android.accessibilityservice.AccessibilityService
import android.content.Intent
import android.os.BatteryManager
import android.os.SystemClock
import android.provider.Settings
import android.util.Log
import android.view.accessibility.AccessibilityEvent
import com.varun.akva.data.AkvaRepository
import com.varun.akva.data.SettingsManager
import com.varun.akva.intelligence.*
import com.varun.akva.interaction.*
import kotlinx.coroutines.*
import java.security.MessageDigest
import java.util.Calendar

class AKVAAccessibilityService : AccessibilityService() {

    private lateinit var voiceEngine: VoiceEngine
    private lateinit var hapticEngine: HapticEngine
    private lateinit var screenMoodEngine: ScreenMoodEngine
    private lateinit var nightModeManager: NightModeManager
    private lateinit var phoneSystemMonitor: PhoneSystemMonitor
    private lateinit var contextDetector: ContextDetector
    private lateinit var stressDetector: StressDetector
    private lateinit var patternLearner: PatternLearner
    private lateinit var personalityEngine: PersonalityEngine
    private lateinit var geminiEngine: GeminiEngine
    private lateinit var morningBriefing: MorningBriefing
    private lateinit var weeklyStoryEngine: WeeklyStoryEngine
    private lateinit var settingsGuide: AKVASettingsGuide
    private lateinit var repository: AkvaRepository
    private lateinit var settingsManager: SettingsManager

    private val scope = CoroutineScope(Dispatchers.Main + SupervisorJob())
    private var lastSpeechTime = 0L // elapsedRealtime
    private var lastPackage = ""
    private val usageCounts = mutableMapOf<String, Int>()
    private val lastSpeechPerApp = mutableMapOf<String, Long>() // per-app last speech elapsedRealtime
    private var deviceId = ""

    companion object {
        private const val TAG = "AKVAAccessibility"
        private const val MIN_SPEECH_GAP_MS = 3000L
        private const val REPEAT_APP_COOLDOWN_MS = 10 * 60 * 1000L // 10 minutes
        var isRunning = false
            private set

        // Packages that should always be silent
        private val SILENT_PACKAGES = setOf(
            "com.android.systemui",
            "com.android.launcher",
            "com.android.launcher3",
            "com.oppo.launcher",
            "com.oneplus.launcher",
            "com.sec.android.app.launcher",
            "com.miui.home",
            "com.huawei.android.launcher",
            "android"
        )

        // Video call apps — total silence during calls
        private val VIDEO_CALL_PACKAGES = setOf(
            "us.zoom.videomeetings",
            "com.google.android.apps.meetings",
            "com.microsoft.teams",
            "com.skype.raider",
            "com.discord"
        )
    }

    override fun onServiceConnected() {
        super.onServiceConnected()
        try {
            voiceEngine = VoiceEngine(this)
            hapticEngine = HapticEngine(this)
            screenMoodEngine = ScreenMoodEngine(this)
            nightModeManager = NightModeManager(this)
            phoneSystemMonitor = PhoneSystemMonitor(this)
            contextDetector = ContextDetector(this)
            stressDetector = StressDetector(this)
            patternLearner = PatternLearner(this)
            personalityEngine = PersonalityEngine()
            geminiEngine = GeminiEngine(this)
            morningBriefing = MorningBriefing(this)
            weeklyStoryEngine = WeeklyStoryEngine(this)
            settingsGuide = AKVASettingsGuide(this)
            repository = AkvaRepository(this)
            settingsManager = SettingsManager(this)
            deviceId = getHashedDeviceId()

            phoneSystemMonitor.init(voiceEngine, hapticEngine)
            phoneSystemMonitor.registerBatteryMonitor()
            phoneSystemMonitor.registerScreenMonitor()

            phoneSystemMonitor.setOnFirstUnlockOfDay {
                scope.launch { tryMorningBriefing() }
            }

            phoneSystemMonitor.setOnLateNightScreenOn {
                val vc = personalityEngine.getNightVoice()
                voiceEngine.speak("You are up late. Maybe wind down soon.", vc, true)
                hapticEngine.playGoodnight()
            }

            voiceEngine.setOnSpeechStart { hapticEngine.playHeartbeatPulse() }

            isRunning = true

            // Welcome speech
            val vc = personalityEngine.getDefaultVoice()
            voiceEngine.speak("AKVA is now alive.", vc, false)

            // Check morning briefing on connect
            scope.launch { tryMorningBriefing() }

            Log.d(TAG, "Service connected — all engines initialized")
        } catch (e: Exception) {
            Log.e(TAG, "Init error: ${e.message}")
        }
    }

    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        if (event == null) return
        if (!settingsManager.masterVoiceEnabled) return
        if (event.eventType != AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED) return

        val packageName = event.packageName?.toString() ?: return
        val className = event.className?.toString() ?: ""

        // === SILENCE CHECK ===
        // Skip our own app
        if (packageName == "com.varun.akva") return

        // Skip known silent packages
        if (SILENT_PACKAGES.contains(packageName)) return

        // Skip any launcher or systemui package
        if (packageName.contains("launcher", ignoreCase = true)) return
        if (packageName.contains("systemui", ignoreCase = true)) return

        // Skip settings unless settings guide is active
        if (contextDetector.isSettingsApp(packageName) && !settingsManager.settingsGuideEnabled) return

        // === RESTRICTED APPS — total silence ===
        if (contextDetector.isRestrictedApp(packageName)) return

        // Video call apps — total silence
        if (VIDEO_CALL_PACKAGES.contains(packageName)) return

        // Call gate
        if (phoneSystemMonitor.isInActiveCall()) return

        // Silent hours gate
        val hour = contextDetector.getHourOfDay()
        if (settingsManager.isSilentHour(hour)) return

        // === MINIMUM GAP — 3000ms between any two speech events ===
        val now = SystemClock.elapsedRealtime()
        if (now - lastSpeechTime < MIN_SPEECH_GAP_MS) return

        // === REPEAT CHECK — same app within 10 minutes ===
        val lastTimeForApp = lastSpeechPerApp[packageName] ?: 0L
        if (packageName == lastPackage && (now - lastTimeForApp) < REPEAT_APP_COOLDOWN_MS) {
            // Same app and within 10 minutes — skip unless it's settings with guide
            if (!contextDetector.isSettingsApp(packageName)) return
        }

        val previousApp = if (lastPackage.isNotEmpty()) contextDetector.getAppName(lastPackage) else ""
        lastPackage = packageName
        stressDetector.recordSwitch()

        // Track usage
        usageCounts[packageName] = (usageCounts[packageName] ?: 0) + 1

        scope.launch {
            try {
                handleAppOpen(packageName, className, previousApp)
            } catch (e: Exception) {
                Log.e(TAG, "Handle error: ${e.message}")
            }
        }
    }

    private suspend fun handleAppOpen(packageName: String, className: String, previousApp: String) {
        // Weekly story check
        if (weeklyStoryEngine.shouldTrigger()) {
            deliverWeeklyStory()
            return
        }

        // Settings guide
        if (contextDetector.isSettingsApp(packageName) && settingsManager.settingsGuideEnabled) {
            val guide = settingsGuide.getGuide(className)
            if (guide.isNotBlank()) {
                val vc = personalityEngine.getVoiceConfig(packageName, nightModeManager.isNightMode(), false)
                voiceEngine.speak(guide, vc, nightModeManager.isNightMode())
                lastSpeechTime = SystemClock.elapsedRealtime()
                lastSpeechPerApp[packageName] = lastSpeechTime
                return
            }
        }

        // Build full context
        val appName = contextDetector.getAppName(packageName)
        val isNight = nightModeManager.isNightMode()
        val stressScore = stressDetector.getStressScore()
        val patternSummary = withContext(Dispatchers.IO) { patternLearner.getUserPatternSummary() }

        val ctx = AkvaContext(
            appName = appName,
            packageName = packageName,
            previousApp = previousApp,
            timeOfDay = contextDetector.getTimeOfDay(),
            hourOfDay = contextDetector.getHourOfDay(),
            dayOfWeek = contextDetector.getDayOfWeek(),
            unreadCount = AKVANotificationListener.getUnreadCount(packageName),
            senderNames = AKVANotificationListener.getSenderNames(packageName),
            timesOpenedToday = usageCounts[packageName] ?: 1,
            batteryPercent = phoneSystemMonitor.getBatteryPercent(),
            isCharging = phoneSystemMonitor.isCharging(),
            networkType = phoneSystemMonitor.getNetworkType(),
            stressScore = stressScore,
            userPattern = patternSummary,
            deviceId = deviceId
        )

        var response = withContext(Dispatchers.IO) { geminiEngine.getResponse(ctx) }

        // === QUALITY CHECK on AI response ===
        response = qualityCheck(response, ctx)

        val vc = personalityEngine.getVoiceConfig(packageName, isNight, stressDetector.isStressed())
        voiceEngine.speak(response, vc, isNight)
        val now = SystemClock.elapsedRealtime()
        lastSpeechTime = now
        lastSpeechPerApp[packageName] = now

        // Haptic
        if (stressDetector.isStressed()) hapticEngine.playStressPulse()
        else hapticEngine.playHeartbeatPulse()

        // Screen mood
        val mood = screenMoodEngine.getMoodForHour(contextDetector.getHourOfDay(), stressDetector.isStressed())
        screenMoodEngine.applyMood(mood)

        // Log
        withContext(Dispatchers.IO) {
            repository.logEvent(packageName, stressScore, isNight, response)
        }

        AKVANotificationListener.clearApp(packageName)
    }

    /**
     * Quality check on Gemini response:
     * - If empty or <5 chars — use fallback
     * - If contains "I am an AI" or "Gemini" — regenerate once, then fallback
     */
    private suspend fun qualityCheck(response: String, ctx: AkvaContext): String {
        // Empty or too short
        if (response.isBlank() || response.length < 5) {
            return geminiEngine.getLocalFallback(ctx)
        }

        // Contains forbidden phrases — try regenerating once
        val lower = response.lowercase()
        if (lower.contains("i am an ai") || lower.contains("gemini") || lower.contains("i'm an ai")) {
            Log.d(TAG, "Quality check failed — regenerating")
            val retry = withContext(Dispatchers.IO) { geminiEngine.getResponse(ctx) }
            val retryLower = retry.lowercase()
            return if (retry.isBlank() || retry.length < 5 ||
                retryLower.contains("i am an ai") || retryLower.contains("gemini") || retryLower.contains("i'm an ai")) {
                geminiEngine.getLocalFallback(ctx)
            } else {
                retry
            }
        }

        return response
    }

    private suspend fun tryMorningBriefing() {
        if (!morningBriefing.shouldTrigger()) return
        val totalUnread = AKVANotificationListener.getTotalUnread()
        val perApp = AKVANotificationListener.getAllUnreadCounts()

        val briefing = morningBriefing.generateBriefing(
            totalUnread, perApp,
            phoneSystemMonitor.getBatteryPercent(),
            phoneSystemMonitor.getNetworkType()
        )

        val sentences = briefing.split(". ").filter { it.isNotBlank() }.map { it.trim().trimEnd('.') + "." }
        voiceEngine.speakConversation(sentences, personalityEngine.getMorningVoice(), 1500L, nightModeManager.isNightMode())
        hapticEngine.playMorningPulse()
        morningBriefing.markDelivered()
        lastSpeechTime = SystemClock.elapsedRealtime()
        screenMoodEngine.applyMood(ScreenMoodEngine.Mood.MORNING_GOLD)
    }

    private suspend fun deliverWeeklyStory() {
        val story = weeklyStoryEngine.generateStory()
        val sentences = story.split(". ").filter { it.isNotBlank() }.map { it.trim().trimEnd('.') + "." }
        voiceEngine.speakConversation(sentences, personalityEngine.getDefaultVoice(), 2000L, nightModeManager.isNightMode())
        hapticEngine.playAchievement()
        weeklyStoryEngine.markDelivered()
        lastSpeechTime = SystemClock.elapsedRealtime()
    }

    private fun getHashedDeviceId(): String {
        return try {
            @Suppress("HardwareIds")
            val androidId = Settings.Secure.getString(contentResolver, Settings.Secure.ANDROID_ID)
            val md = MessageDigest.getInstance("MD5")
            val digest = md.digest(androidId.toByteArray())
            digest.joinToString("") { "%02x".format(it) }
        } catch (_: Exception) { "unknown" }
    }

    override fun onInterrupt() { voiceEngine.stop() }

    override fun onUnbind(intent: Intent?): Boolean {
        isRunning = false
        // Request restart
        try {
            val restartIntent = Intent(this, AKVAAccessibilityService::class.java)
            startService(restartIntent)
        } catch (_: Exception) {}
        return super.onUnbind(intent)
    }

    override fun onDestroy() {
        isRunning = false
        scope.cancel()
        voiceEngine.shutdown()
        phoneSystemMonitor.unregister()
        screenMoodEngine.removeOverlay()
        super.onDestroy()
    }
}
