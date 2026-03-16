package com.varun.akva.services

import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import android.util.Log
import com.varun.akva.intelligence.ContextDetector
import java.util.concurrent.ConcurrentHashMap

class AKVANotificationListener : NotificationListenerService() {

    private lateinit var contextDetector: ContextDetector

    override fun onCreate() {
        super.onCreate()
        contextDetector = ContextDetector(this)
        instance = this
    }

    override fun onNotificationPosted(sbn: StatusBarNotification?) {
        sbn ?: return
        val pkg = sbn.packageName ?: return
        if (contextDetector.isRestrictedApp(pkg)) return
        if (pkg == "com.varun.akva") return

        try {
            val title = sbn.notification?.extras?.getCharSequence("android.title")?.toString() ?: return

            synchronized(lock) {
                unreadCounts[pkg] = (unreadCounts[pkg] ?: 0) + 1

                val senders = senderNames.getOrPut(pkg) { ArrayDeque() }
                if (!senders.contains(title)) {
                    if (senders.size >= 3) senders.removeFirst()
                    senders.addLast(title)
                }
            }
        } catch (e: Exception) {
            Log.e(TAG, "Notification error: ${e.message}")
        }
    }

    override fun onNotificationRemoved(sbn: StatusBarNotification?) {
        sbn ?: return
        val pkg = sbn.packageName ?: return
        synchronized(lock) {
            val c = unreadCounts[pkg] ?: return
            if (c > 0) unreadCounts[pkg] = c - 1
        }
    }

    override fun onDestroy() { instance = null; super.onDestroy() }

    companion object {
        private const val TAG = "AKVANotifListener"
        private val lock = Any()
        private val unreadCounts = ConcurrentHashMap<String, Int>()
        private val senderNames = ConcurrentHashMap<String, ArrayDeque<String>>()
        private var instance: AKVANotificationListener? = null

        fun getUnreadCount(pkg: String): Int = synchronized(lock) { unreadCounts[pkg] ?: 0 }
        fun getSenderNames(pkg: String): List<String> = synchronized(lock) { senderNames[pkg]?.toList() ?: emptyList() }
        fun getTotalUnread(): Int = synchronized(lock) { unreadCounts.values.sum() }
        fun getAllUnreadCounts(): Map<String, Int> = synchronized(lock) { unreadCounts.toMap() }
        fun clearApp(pkg: String) = synchronized(lock) { unreadCounts.remove(pkg); senderNames.remove(pkg) }
    }
}
