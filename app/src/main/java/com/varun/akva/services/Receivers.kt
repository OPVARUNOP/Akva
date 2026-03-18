package com.varun.akva.services

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.varun.akva.AKVAApplication

class BootReceiver : BroadcastReceiver() {
    override fun onReceive(ctx: Context, i: Intent) {
        if (i.action == Intent.ACTION_BOOT_COMPLETED) {
            ctx.startForegroundService(Intent(ctx, AKVABackgroundService::class.java))
        }
    }
}

class AKVABroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(ctx: Context, i: Intent) {
        val app = ctx.applicationContext as AKVAApplication
        when (i.action) {
            Intent.ACTION_POWER_CONNECTED -> app.liveEngine.proactiveSpeak("Power connected.")
            Intent.ACTION_POWER_DISCONNECTED -> app.liveEngine.proactiveSpeak("Power disconnected.")
        }
    }
}
