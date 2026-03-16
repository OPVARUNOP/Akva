package com.varun.akva.services

import android.app.*
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import com.varun.akva.MainActivity
import com.varun.akva.R
import com.varun.akva.interaction.VoiceEngine

class AKVABackgroundService : Service() {

    private var voiceInput: AKVAVoiceInput? = null
    private var voiceEngine: VoiceEngine? = null

    override fun onCreate() {
        super.onCreate()
        try {
            createNotificationChannel()
            startForeground(1001, buildNotification())

            voiceEngine = VoiceEngine(this)
            voiceInput = AKVAVoiceInput(this)
            voiceInput?.init(voiceEngine!!)
            voiceInput?.startContinuousListening()

            Log.d(TAG, "Background service started")
        } catch (e: Exception) {
            Log.e(TAG, "Start error: ${e.message}")
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int = START_STICKY
    override fun onBind(intent: Intent?): IBinder? = null

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
        val pi = PendingIntent.getActivity(this, 0, Intent(this, MainActivity::class.java), PendingIntent.FLAG_IMMUTABLE)
        return NotificationCompat.Builder(this, "akva_bg")
            .setContentTitle("AKVA")
            .setContentText("The Living OS is active")
            .setSmallIcon(R.drawable.ic_notification)
            .setColor(android.graphics.Color.parseColor("#1A1A2E"))
            .setContentIntent(pi)
            .setOngoing(true)
            .setPriority(NotificationCompat.PRIORITY_LOW)
            .build()
    }

    override fun onDestroy() {
        voiceInput?.stopListening()
        voiceEngine?.shutdown()
        super.onDestroy()
    }

    companion object { private const val TAG = "AKVABgService" }
}
