package com.varun.akva.services

import android.accessibilityservice.AccessibilityService
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
        private const val MIN_SPEECH_GAP_MS = 3000L
        private const val REPEAT_APP_COOLDOWN_MS = 10 * 60 * 1000L // 10 minutes
        
        var isRunning = false
            private set
        
        var instance: AKVAAccessibilityService? = null
            private set

        private val SILENT_PACKAGES = setOf(
            "com.android.systemui", "com.android.launcher", "com.android.launcher3",
            "android", "com.varun.akva"
        )
    }

    override fun onServiceConnected() {
        super.onServiceConnected()
        try {
            instance = this
            voiceEngine = VoiceEngine(this)
            hapticEngine = HapticEngine(this)
            phoneSystemMonitor = PhoneSystemMonitor(this)
            contextDetector = ContextDetector(this)
            patternLearner = PatternLearner(this)
            personalityEngine = PersonalityEngine()
            geminiEngine = GeminiEngine(this)
            
            val database = AkvaDatabase.getDatabase(this)
            repository = AkvaRepository(database.usageDao(), database.personalityDao())
            settingsManager = SettingsManager(this)

            isRunning = true
            Log.d(TAG, "Service connected")
        } catch (e: Exception) {
            Log.e(TAG, "Init error: ${e.message}")
        }
    }

    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        if (event == null || !isRunning) return
        if (event.eventType != AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED) return

        val packageName = event.packageName?.toString() ?: return
        
        // Silence rules
        if (SILENT_PACKAGES.contains(packageName)) return
        if (packageName.contains("launcher", ignoreCase = true)) return
        if (contextDetector.isRestrictedApp(packageName)) return
        
        // Time rules
        val now = SystemClock.elapsedRealtime()
        if (now - lastSpeechTime < MIN_SPEECH_GAP_MS) return
        
        val lastTimeForApp = lastSpeechPerApp[packageName] ?: 0L
        if ((now - lastTimeForApp) < REPEAT_APP_COOLDOWN_MS) return

        val previousApp = if (lastPackage.isNotEmpty()) contextDetector.getAppName(lastPackage) else ""
        lastPackage = packageName

        scope.launch {
            handleAppOpen(packageName, previousApp)
        }
    }

    private suspend fun handleAppOpen(packageName: String, previousApp: String) {
        val appName = contextDetector.getAppName(packageName)
        val usageEvents = withContext(Dispatchers.IO) { repository.getRecentEventsList(50) }
        
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
            stressScore = 0, // Simplified for now
            userPattern = patternLearner.deducePattern(usageEvents, packageName),
            deviceId = "akva_user_1"
        )

        val response = geminiEngine.getObserverResponse(ctx)
        
        if (response.isNotEmpty()) {
            voiceEngine.speakProactive(response, packageName)
            lastSpeechTime = SystemClock.elapsedRealtime()
            lastSpeechPerApp[packageName] = lastSpeechTime
            hapticEngine.playTick()
            
            // Log interaction
            withContext(Dispatchers.IO) {
                repository.logEvent(packageName, 0, false, response)
            }
        }
    }

    override fun onInterrupt() {
        voiceEngine.stop()
    }

    override fun onUnbind(intent: Intent?): Boolean {
        isRunning = false
        instance = null
        return super.onUnbind(intent)
    }

    override fun onDestroy() {
        isRunning = false
        instance = null
        scope.cancel()
        voiceEngine.shutdown()
        super.onDestroy()
    }
}
