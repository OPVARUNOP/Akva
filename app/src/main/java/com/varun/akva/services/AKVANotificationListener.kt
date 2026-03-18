package com.varun.akva.services

import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import com.varun.akva.AKVAApplication

class AKVANotificationListener : NotificationListenerService() {
    override fun onNotificationPosted(sbn: StatusBarNotification) {
        val app = application as AKVAApplication
        val text = sbn.notification.extras.getCharSequence("android.text")?.toString() ?: ""
        if (text.isNotBlank()) {
            app.voiceEngine.speak("New message: $text")
        }
    }
}
