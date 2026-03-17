package com.varun.akva.intelligence

import android.content.Context
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class ContextDetector(private val context: Context) {
    fun getTimeOfDay(): String {
        val hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
        return when (hour) {
            in 5..11 -> "morning"
            in 12..16 -> "afternoon"
            in 17..21 -> "evening"
            else -> "night"
        }
    }

    fun getHourOfDay(): Int {
        return Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
    }

    fun getDayOfWeek(): String {
        return SimpleDateFormat("EEEE", Locale.getDefault()).format(Date())
    }

    fun isRestrictedApp(packageName: String): Boolean {
        val restricted = setOf(
            "com.android.settings", "com.google.android.settings",
            "com.android.bank", "com.google.android.apps.walletnfcrel",
            "com.paypal.android.p2pmobile", "com.venmo", "com.binance.dev",
            "com.coinbase.android", "in.org.npci.upiapp"
        )
        return restricted.any { packageName.contains(it) }
    }

    fun isSettingsApp(packageName: String): Boolean {
        return packageName.contains("settings") || packageName.contains("systemui")
    }

    fun getAppName(packageName: String): String {
        return try {
            val pm = context.packageManager
            val info = pm.getApplicationInfo(packageName, 0)
            pm.getApplicationLabel(info).toString()
        } catch (_: Exception) {
            packageName.substringAfterLast('.')
        }
    }
}
