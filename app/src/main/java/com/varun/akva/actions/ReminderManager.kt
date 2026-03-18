package com.varun.akva.actions

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.*
import android.provider.AlarmClock
import com.varun.akva.AKVAApplication

class ReminderManager(private val ctx: Context) {
    fun setAlarm(h: Int, m: Int) { 
        val i = Intent(AlarmClock.ACTION_SET_ALARM).apply { putExtra(AlarmClock.EXTRA_HOUR, h); putExtra(AlarmClock.EXTRA_MINUTES, m); putExtra(AlarmClock.EXTRA_SKIP_UI, true); addFlags(Intent.FLAG_ACTIVITY_NEW_TASK) }
        ctx.startActivity(i)
    }
}

class ReminderReceiver : BroadcastReceiver() {
    override fun onReceive(ctx: Context, i: Intent) {
        val app = ctx.applicationContext as AKVAApplication
        app.voiceEngine.speak("Sir, I have a reminder for you.")
    }
}
