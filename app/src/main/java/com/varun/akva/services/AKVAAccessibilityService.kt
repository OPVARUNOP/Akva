package com.varun.akva.services

import android.accessibilityservice.AccessibilityService
import android.accessibilityservice.AccessibilityServiceInfo
import android.content.Intent
import android.os.SystemClock
import android.util.Log
import android.view.accessibility.AccessibilityEvent
import com.varun.akva.data.AkvaDatabase
import com.varun.akva.data.AkvaRepository
import com.varun.akva.data.SettingsManager
import com.varun.akva.intelligence.*
import com.varun.akva.interaction.*
import kotlinx.coroutines.*
import java.util.Calendar

class AKVAAccessibilityService : AccessibilityService() {

    private lateinit var voiceEngine: VoiceEngine
    private lateinit var hapticEngine: HapticEngine
    private lateinit var phoneSystemMonitor: PhoneSystemMonitor
    private lateinit var contextDetector: ContextDetector
    private lateinit var patternLearner: PatternLearner
    private lateinit var personalityEngine: PersonalityEngine
    private lateinit var geminiEngine: GeminiEngine
    private lateinit var repository: AkvaRepository
    private lateinit var settingsManager: SettingsManager
    private val scope = CoroutineScope(Dispatchers.Main + SupervisorJob())
    private var lastSpeechTime = 0L
    private var lastPackage = ""
    private val lastSpeechPerApp = mutableMapOf<String, Long>()

    companion object {
        private const val TAG = "AKVAAccessibility"
        private const val MIN_SPEECH_GAP_MS = 2500L
        private const val REPEAT_APP_COOLDOWN_MS = 60 * 1000L // 1 minute only

        var isRunning = false
            private set

        var instance: AKVAAccessibilityService? = null
            private set

        private val SILENT_PACKAGES = setOf(
            "com.android.systemui",
            "com.android.launcher",
            "com.android.launcher3",
            "com.oppo.launcher",
            "com.oneplus.launcher",
            "android",
            "com.varun.akva",
            "com.oplus.safecenter",
            "com.coloros.assistantscreen",
            "com.oppo.quicksearchbox"
        )
    }

    override fun onServiceConnected() {
        super.onServiceConnected()
        try {
            instance = this
            isRunning = true

            // Configure service info programmatically
            val info = AccessibilityServiceInfo()
            info.eventTypes = AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED or
                    AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED
            info.feedbackType = AccessibilityServiceInfo.FEEDBACK_GENERIC
            info.flags = AccessibilityServiceInfo.FLAG_INCLUDE_NOT_IMPORTANT_VIEWS or
                    AccessibilityServiceInfo.FLAG_REPORT_VIEW_IDS or
                    AccessibilityServiceInfo.FLAG_RETRIEVE_INTERACTIVE_WINDOWS
            info.notificationTimeout = 50
            serviceInfo = info

            voiceEngine = VoiceEngine(this)
            hapticEngine = HapticEngine(this)
            phoneSystemMonitor = PhoneSystemMonitor(this)
            contextDetector = ContextDetector(this)
            patternLearner = PatternLearner(this)
            personalityEngine = PersonalityEngine()
            geminiEngine = GeminiEngine(this)

            val database = AkvaDatabase.getDatabase(this)
            repository = AkvaRepository(
                database.usageDao(),
                database.personalityDao(),
                database.userProfileDao(),
                database.conversationDao(),
                database.actionHistoryDao()
            )
            settingsManager = SettingsManager(this)

            Log.d(TAG, "AKVA Service connected and alive")

            // Speak welcome after 2 seconds
            scope.launch {
                delay(2000)
                try {
                    voiceEngine.speakDirect("AKVA is now alive.")
                    Log.d(TAG, "Welcome message spoken")
                } catch (e: Exception) {
                    Log.e(TAG, "Welcome speak error: ${e.message}")
                }
            }

        } catch (e: Exception) {
            Log.e(TAG, "Init error: ${e.message}", e)
        }
    }

    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        if (event == null || !isRunning) return
        if (event.eventType != AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED) return

        val packageName = event.packageName?.toString() ?: return

        Log.d(TAG, "App opened: $packageName")

        // Skip silent packages
        if (SILENT_PACKAGES.contains(packageName)) return
        if (packageName.contains("launcher", ignoreCase = true)) return
        if (packageName.contains("systemui", ignoreCase = true)) return

        // Skip restricted apps
        if (isRestrictedApp(packageName)) {
            Log.d(TAG, "Restricted app — silent: $packageName")
            return
        }

        // Skip same package
        if (packageName == lastPackage) return

        // Time gap check
        val now = SystemClock.elapsedRealtime()
        if (now - lastSpeechTime < MIN_SPEECH_GAP_MS) {
            Log.d(TAG, "Too soon to speak again")
            lastPackage = packageName
            return
        }

        // Repeat cooldown — reduced to 1 minute
        val lastTimeForApp = lastSpeechPerApp[packageName] ?: 0L
        if ((now - lastTimeForApp) < REPEAT_APP_COOLDOWN_MS) {
            Log.d(TAG, "App cooldown active for: $packageName")
            lastPackage = packageName
            return
        }

        val previousApp = if (lastPackage.isNotEmpty())
            contextDetector.getAppName(lastPackage) else "home screen"
        lastPackage = packageName

        Log.d(TAG, "Triggering speech for: $packageName")

        scope.launch {
            try {
                handleAppOpen(packageName, previousApp)
            } catch (e: Exception) {
                Log.e(TAG, "handleAppOpen error: ${e.message}", e)
            }
        }
    }

    private suspend fun handleAppOpen(packageName: String, previousApp: String) {
        try {
            val appName = contextDetector.getAppName(packageName)
            Log.d(TAG, "Building context for: $appName")

            val usageEvents = withContext(Dispatchers.IO) {
                try { repository.getRecentEventsList(50) } catch (e: Exception) { emptyList() }
            }

            val ctx = AkvaContext(
                appName = appName,
                packageName = packageName,
                previousApp = previousApp,
                timeOfDay = contextDetector.getTimeOfDay(),
                hourOfDay = contextDetector.getHourOfDay(),
                dayOfWeek = contextDetector.getDayOfWeek(),
                unreadCount = AKVANotificationListener.getUnreadCount(packageName),
                totalUnread = AKVANotificationListener.getTotalUnread(),
                senderNames = AKVANotificationListener.getSenderNames(packageName),
                timesOpenedToday = patternLearner.getTimesOpenedToday(usageEvents, packageName),
                batteryPercent = phoneSystemMonitor.getBatteryPercent(),
                isCharging = phoneSystemMonitor.isCharging(),
                networkType = phoneSystemMonitor.getNetworkType(),
                stressScore = 0,
                userPattern = patternLearner.deducePattern(usageEvents, packageName),
                deviceId = "akva_user_1"
            )

            Log.d(TAG, "Calling Gemini for: $appName")
            val response = withContext(Dispatchers.IO) {
                geminiEngine.getObserverResponse(ctx)
            }

            Log.d(TAG, "Gemini response: $response")

            if (response.isNotEmpty() && response != "SILENT") {
                withContext(Dispatchers.Main) {
                    voiceEngine.speakProactive(response, packageName)
                    hapticEngine.playTick()
                    Log.d(TAG, "Speaking: $response")
                }
                lastSpeechTime = SystemClock.elapsedRealtime()
                lastSpeechPerApp[packageName] = lastSpeechTime

                withContext(Dispatchers.IO) {
                    try {
                        repository.logEvent(packageName, 0, false, response)
                    } catch (e: Exception) {
                        Log.e(TAG, "Log error: ${e.message}")
                    }
                }
            } else {
                Log.d(TAG, "No speech — response was empty or SILENT")
            }

        } catch (e: Exception) {
            Log.e(TAG, "handleAppOpen exception: ${e.message}", e)
        }
    }

    private fun isRestrictedApp(packageName: String): Boolean {
        val restricted = listOf(
            "phonepe", "paytm", "gpay", "bhim", "bank",
            "wallet", "password", "vault", "signal",
            "zoom", "meet", "teams", "dialer", "incognito"
        )
        return restricted.any { packageName.contains(it, ignoreCase = true) }
    }

    override fun onInterrupt() {
        try { voiceEngine.stop() } catch (e: Exception) { }
    }

    override fun onUnbind(intent: Intent?): Boolean {
        isRunning = false
        instance = null
        return super.onUnbind(intent)
    }

    override fun onDestroy() {
        isRunning = false
        instance = null
        try { scope.cancel() } catch (e: Exception) { }
        super.onDestroy()
    }
}
