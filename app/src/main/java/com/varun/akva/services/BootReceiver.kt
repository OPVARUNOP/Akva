package com.varun.akva.services

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log

class BootReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (context == null) return
        if (intent?.action == Intent.ACTION_BOOT_COMPLETED || intent?.action == "android.intent.action.QUICKBOOT_POWERON") {
            Log.d("BootReceiver", "Boot completed — starting AKVA")
            try {
                val si = Intent(context, AKVABackgroundService::class.java)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) context.startForegroundService(si)
                else context.startService(si)
            } catch (e: Exception) {
                Log.e("BootReceiver", "Boot start error: ${e.message}")
            }
        }
    }
}
