package com.varun.akva.services

import android.app.*
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.os.PowerManager
import android.util.Log
import androidx.core.app.NotificationCompat
import com.varun.akva.MainActivity
import com.varun.akva.R
import com.varun.akva.data.AkvaDatabase
import com.varun.akva.data.AkvaRepository
import com.varun.akva.data.SettingsManager
import com.varun.akva.intelligence.*
import com.varun.akva.interaction.HapticEngine
import com.varun.akva.interaction.VoiceEngine

class AKVABackgroundService : Service() {

    private var voiceInput: AKVAVoiceInput? = null
    private var wakeLock: PowerManager.WakeLock? = null
    
    // Core Engines
    private lateinit var geminiEngine: GeminiEngine
    private lateinit var memoryEngine: AKVAMemoryEngine
    private lateinit var autonomousAgent: AKVAAutonomousAgent
    private lateinit var executor: AKVAExecutor
    private lateinit var webSearch: AKVAWebSearch
    private lateinit var conversationService: AKVAConversationService
    private lateinit var responseEngine: AKVAResponseEngine
    private lateinit var actionFeedback: AKVAActionFeedback
    private lateinit var automationService: AKVAAutomationService
    private lateinit var commandExecutor: AKVACommandExecutor
    private lateinit var settingsManager: SettingsManager

    override fun onCreate() {
        super.onCreate()
        try {
            createNotificationChannel()
            startForeground(1001, buildNotification())
            acquireWakeLock()

            // 1. Base Components
            val voiceEngine = VoiceEngine(this)
            val hapticEngine = HapticEngine(this)
            settingsManager = SettingsManager(this)
            geminiEngine = GeminiEngine(this)
            
            // 2. Intelligence Layer
            memoryEngine = AKVAMemoryEngine(this)
            autonomousAgent = AKVAAutonomousAgent(geminiEngine)
            webSearch = AKVAWebSearch(geminiEngine, settingsManager)
            
            // 3. Execution Layer
            automationService = AKVAAutomationService()
            commandExecutor = AKVACommandExecutor(this)
            executor = AKVAExecutor(this, commandExecutor, automationService, webSearch)
            
            // 4. Feedback & Response Layer
            responseEngine = AKVAResponseEngine(this, voiceEngine)
            actionFeedback = AKVAActionFeedback(this, hapticEngine)
            
            // 5. Unified Service
            conversationService = AKVAConversationService(
                geminiEngine, memoryEngine, autonomousAgent, 
                executor, responseEngine, actionFeedback
            )

            // 6. Voice Awareness
            voiceInput = AKVAVoiceInput(this)
            voiceInput?.init(conversationService)
            voiceInput?.startContinuousListening()

            Log.d(TAG, "AKVA Ultimate Background Service fully wired and active")
        } catch (e: Exception) {
            Log.e(TAG, "Wire error: ${e.message}", e)
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int = START_STICKY
    override fun onBind(intent: Intent?): IBinder? = null

    private fun acquireWakeLock() {
        val powerManager = getSystemService(Context.POWER_SERVICE) as PowerManager
        wakeLock = powerManager.newWakeLock(
            PowerManager.PARTIAL_WAKE_LOCK,
            "AKVA::BackgroundWakeLock"
        )
        wakeLock?.acquire(120 * 60 * 1000L) // 2 hours
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel("akva_bg", "AKVA Background Service", NotificationManager.IMPORTANCE_LOW).apply {
                description = "Keeps AKVA alive"
                setShowBadge(false)
            }
            getSystemService(NotificationManager::class.java).createNotificationChannel(channel)
        }
    }

    private fun buildNotification(): Notification {
        val piFlags = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) 
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT 
        else 
            PendingIntent.FLAG_UPDATE_CURRENT

        val pi = PendingIntent.getActivity(this, 0, Intent(this, MainActivity::class.java), piFlags)
        
        return NotificationCompat.Builder(this, "akva_bg")
            .setContentTitle("AKVA ULTIMATE")
            .setContentText("Systems online. JARVIS protocol active.")
            .setSmallIcon(R.drawable.ic_notification)
            .setColor(android.graphics.Color.parseColor("#00D4FF"))
            .setContentIntent(pi)
            .setOngoing(true)
            .setPriority(NotificationCompat.PRIORITY_LOW)
            .build()
    }

    override fun onDestroy() {
        voiceInput?.stopListening()
        wakeLock?.let { if (it.isHeld) it.release() }
        super.onDestroy()
    }

    companion object { private const val TAG = "AKVABgService" }
}
