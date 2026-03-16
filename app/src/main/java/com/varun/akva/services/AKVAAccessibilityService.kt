package com.varun.akva.services

import android.accessibilityservice.AccessibilityService
import android.content.Intent
import android.os.BatteryManager
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
    private var lastSpeechTime = 0L
    private var lastPackage = ""
    private val usageCounts = mutableMapOf<String, Int>()
    private var deviceId = ""

    companion object {
        private const val TAG = "AKVAAccessibility"
        private const val MIN_SPEECH_GAP_MS = 2500L
        var isRunning = false
            private set
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

        // Skip noise
        if (packageName == "com.varun.akva") return
        if (packageName == "com.android.systemui") return
        if (packageName == "android") return
        if (packageName.contains("launcher")) return

        // Privacy gate
        if (contextDetector.isRestrictedApp(packageName)) return

        // Call gate
        if (phoneSystemMonitor.isInActiveCall()) return

        // Silent hours gate
        val hour = contextDetector.getHourOfDay()
        if (settingsManager.isSilentHour(hour)) return

        // Timing gate
        val now = System.currentTimeMillis()
        if (now - lastSpeechTime < MIN_SPEECH_GAP_MS) return

        // Skip same app (unless settings)
        if (packageName == lastPackage && !contextDetector.isSettingsApp(packageName)) return

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
                lastSpeechTime = System.currentTimeMillis()
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

        val response = withContext(Dispatchers.IO) { geminiEngine.getResponse(ctx) }

        val vc = personalityEngine.getVoiceConfig(packageName, isNight, stressDetector.isStressed())
        voiceEngine.speak(response, vc, isNight)
        lastSpeechTime = System.currentTimeMillis()

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
        lastSpeechTime = System.currentTimeMillis()
        screenMoodEngine.applyMood(ScreenMoodEngine.Mood.MORNING_GOLD)
    }

    private suspend fun deliverWeeklyStory() {
        val story = weeklyStoryEngine.generateStory()
        val sentences = story.split(". ").filter { it.isNotBlank() }.map { it.trim().trimEnd('.') + "." }
        voiceEngine.speakConversation(sentences, personalityEngine.getDefaultVoice(), 2000L, nightModeManager.isNightMode())
        hapticEngine.playAchievement()
        weeklyStoryEngine.markDelivered()
        lastSpeechTime = System.currentTimeMillis()
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
