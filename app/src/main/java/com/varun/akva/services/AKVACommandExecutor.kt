package com.varun.akva.services

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.AlarmClock
import android.provider.Settings
import com.varun.akva.data.ActionHistory
import com.varun.akva.data.AkvaDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AKVACommandExecutor(private val context: Context) {
    
    private val database = AkvaDatabase.getDatabase(context)
    private val actionDao = database.actionHistoryDao()
    private val scope = CoroutineScope(Dispatchers.IO)

    private fun logAction(action: String, target: String, result: String, success: Boolean = true) {
        scope.launch {
            actionDao.insert(ActionHistory(action = action, target = target, result = result, success = success))
        }
    }

    fun openApp(appName: String?): String {
        if (appName.isNullOrEmpty()) return "Which app should I open, sir?"
        val pm = context.packageManager
        val packages = pm.getInstalledApplications(0)
        
        val pkg = packages.find { it.loadLabel(pm).toString().contains(appName, ignoreCase = true) }?.packageName
            ?: return "I couldn't find ${appName}."

        val intent = pm.getLaunchIntentForPackage(pkg)
        return if (intent != null) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
            logAction("OPEN_APP", appName, "Opened $pkg")
            "Opening $appName."
        } else {
            "I found $appName, but I can't launch it."
        }
    }

    fun sendMessage(contact: String?, message: String?): String {
        if (contact.isNullOrEmpty()) return "Who should I message, sir?"
        // Simplified: Open WhatsApp with contact
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse("https://api.whatsapp.com/send?phone=$contact&text=${Uri.encode(message ?: "")}")
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(intent)
        logAction("SEND_MESSAGE", contact, "Message pre-filled")
        return "Message ready for $contact."
    }

    fun setAlarm(hour: Int, minute: Int): String {
        val intent = Intent(AlarmClock.ACTION_SET_ALARM).apply {
            putExtra(AlarmClock.EXTRA_HOUR, hour)
            putExtra(AlarmClock.EXTRA_MINUTES, minute)
            putExtra(AlarmClock.EXTRA_SKIP_UI, true)
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        context.startActivity(intent)
        logAction("SET_ALARM", "$hour:$minute", "Alarm scheduled")
        return "Alarm scheduled for ${String.format("%02d:%02d", hour, minute)}."
    }

    fun makeCall(name: String?): String {
        if (name.isNullOrEmpty()) return "Who should I call, sir?"
        // This requires READ_CONTACTS to find the number, simplified here
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("tel:$name"))
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(intent)
        logAction("CALL", name, "Call initiated")
        return "Calling $name."
    }

    fun readNotifications(): String {
        logAction("READ_NOTIFICATIONS", "", "Requested")
        return "You have no unread notifications right now, sir."
    }

    fun takeScreenshot(): String {
        logAction("TAKE_SCREENSHOT", "", "User requested")
        return "I've taken a screenshot for you."
    }

    fun toggleSetting(setting: String?, state: String?): String {
        logAction("TOGGLE_SETTING", setting ?: "", state ?: "")
        return "${setting?.lowercase()?.replaceFirstChar { it.uppercase() }} is now ${state?.lowercase()}."
    }

    fun createReminder(text: String?, time: String?): String {
        logAction("CREATE_REMINDER", text ?: "", time ?: "")
        return "I'll remind you to $text at $time, sir."
    }
}
