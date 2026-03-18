package com.varun.akva.services

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import androidx.core.app.NotificationCompat

class AKVABackgroundService : Service() {
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val channelId = "akva_core"
        val channel = NotificationChannel(channelId, "AKVA Core", NotificationManager.IMPORTANCE_LOW)
        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.createNotificationChannel(channel)
        
        val notification = NotificationCompat.Builder(this, channelId)
            .setContentTitle("AKVA")
            .setContentText("Living OS Active")
            .setSmallIcon(android.R.drawable.ic_lock_power_off)
            .build()
            
        startForeground(1, notification)
        return START_STICKY
    }
    override fun onBind(intent: Intent?): IBinder? = null
}
