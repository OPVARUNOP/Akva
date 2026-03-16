package com.varun.akva.intelligence

import android.content.Context
import android.content.pm.PackageManager
import java.util.Calendar

class ContextDetector(private val context: Context) {

    fun getTimeOfDay(): String {
        return when (getHourOfDay()) {
            in 5..11 -> "morning"
            in 12..16 -> "afternoon"
            in 17..21 -> "evening"
            else -> "night"
        }
    }

    fun getHourOfDay(): Int = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)

    fun getDayOfWeek(): String {
        return when (Calendar.getInstance().get(Calendar.DAY_OF_WEEK)) {
            Calendar.MONDAY -> "Monday"
            Calendar.TUESDAY -> "Tuesday"
            Calendar.WEDNESDAY -> "Wednesday"
            Calendar.THURSDAY -> "Thursday"
            Calendar.FRIDAY -> "Friday"
            Calendar.SATURDAY -> "Saturday"
            Calendar.SUNDAY -> "Sunday"
            else -> "Today"
        }
    }

    fun isNightTime(): Boolean {
        val hour = getHourOfDay()
        return hour >= 22 || hour < 6
    }

    fun getAppName(packageName: String): String {
        val knownApps = mapOf(
            "com.whatsapp" to "WhatsApp",
            "com.instagram.android" to "Instagram",
            "com.google.android.youtube" to "YouTube",
            "com.google.android.gm" to "Gmail",
            "com.google.android.apps.maps" to "Google Maps",
            "com.spotify.music" to "Spotify",
            "com.android.camera" to "Camera",
            "com.google.android.GoogleCamera" to "Camera",
            "com.sec.android.app.camera" to "Camera",
            "com.google.android.calendar" to "Calendar",
            "com.android.chrome" to "Chrome",
            "com.netflix.mediaclient" to "Netflix",
            "com.twitter.android" to "Twitter",
            "com.linkedin.android" to "LinkedIn",
            "com.zomato.ordering" to "Zomato",
            "in.swiggy.android" to "Swiggy",
            "com.google.android.apps.photos" to "Photos",
            "com.google.android.dialer" to "Phone",
            "com.google.android.apps.messaging" to "Messages",
            "com.facebook.katana" to "Facebook",
            "com.snapchat.android" to "Snapchat",
            "com.pinterest" to "Pinterest",
            "com.amazon.mShop.android.shopping" to "Amazon",
            "com.flipkart.android" to "Flipkart",
            "com.reddit.frontpage" to "Reddit",
            "com.discord" to "Discord",
            "com.zhiliaoapp.musically" to "TikTok",
            "com.google.android.apps.docs" to "Google Docs",
            "com.google.android.keep" to "Google Keep",
            "com.android.settings" to "Settings"
        )

        knownApps[packageName]?.let { return it }

        // Try to get app label from package manager
        return try {
            val pm = context.packageManager
            val appInfo = pm.getApplicationInfo(packageName, 0)
            pm.getApplicationLabel(appInfo).toString()
        } catch (e: Exception) {
            packageName.split(".").lastOrNull()
                ?.replaceFirstChar { it.uppercase() } ?: "App"
        }
    }

    fun isRestrictedApp(packageName: String): Boolean {
        val restricted = listOf(
            "com.phonepe.app",
            "com.google.android.apps.nbu.paisa.user",
            "net.one97.paytm",
            "com.amazon.mShop.android.shopping",
            "in.amazon.mShop.android.shopping",
            "com.snapwork.hdfc",
            "com.csam.icici.bank",
            "com.sbi.lotusintouch",
            "com.axis.mobile",
            "com.kotak.mahindra.kotak",
            "com.bank.bakoum",
            "com.whatsapp.w4b",
            "org.thoughtcrime.securesms",
            "org.telegram.messenger",
            "com.android.chrome.incognito",
            "com.lastpass.lpandroid",
            "com.onepassword.android",
            "com.dashlane",
            "com.android.server.telecom",
            "com.google.android.dialer"
        )
        return restricted.any { packageName.contains(it) } ||
                packageName.contains("bank") ||
                packageName.contains("pay") ||
                packageName.contains("wallet") ||
                packageName.contains("password") ||
                packageName.contains("vault")
    }

    fun isSettingsApp(packageName: String): Boolean {
        return packageName == "com.android.settings" || packageName.contains("settings")
    }
}
