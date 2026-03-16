package com.varun.akva.intelligence

import android.content.Context

class AKVASettingsGuide(private val context: Context) {

    private val geminiEngine = GeminiEngine(context)

    private val settingsNames = mapOf(
        "com.android.settings.Settings\$WifiSettingsActivity" to "Wi-Fi Settings",
        "com.android.settings.Settings\$BluetoothSettingsActivity" to "Bluetooth Settings",
        "com.android.settings.Settings\$DataUsageSummaryActivity" to "Data Usage",
        "com.android.settings.Settings\$DisplaySettingsActivity" to "Display Settings",
        "com.android.settings.Settings\$SoundSettingsActivity" to "Sound Settings",
        "com.android.settings.Settings\$BatterySettingsActivity" to "Battery Settings",
        "com.android.settings.Settings\$StorageDashboardActivity" to "Storage Settings",
        "com.android.settings.Settings\$SecurityDashboardActivity" to "Security Settings",
        "com.android.settings.Settings\$LocationSettingsActivity" to "Location Settings",
        "com.android.settings.Settings\$DevelopmentSettingsActivity" to "Developer Options",
        "com.android.settings.Settings\$AccessibilitySettingsActivity" to "Accessibility Settings",
        "com.android.settings.Settings\$ManageApplications" to "App Manager",
        "com.android.settings.Settings\$NotificationSettingsActivity" to "Notification Settings"
    )

    fun isSettingsApp(packageName: String): Boolean {
        return packageName == "com.android.settings" || packageName.contains("settings")
    }

    suspend fun getGuide(className: String): String {
        val screenName = settingsNames[className] ?: return ""

        val ctx = AkvaContext(
            appName = screenName,
            packageName = "com.android.settings",
            previousApp = "",
            timeOfDay = "",
            hourOfDay = 0,
            dayOfWeek = "",
            unreadCount = 0,
            senderNames = emptyList(),
            timesOpenedToday = 0,
            batteryPercent = 0,
            isCharging = false,
            networkType = "",
            stressScore = 0,
            userPattern = "User navigated to $screenName. Explain what this settings screen does in one helpful sentence.",
            deviceId = ""
        )

        return geminiEngine.getResponse(ctx)
    }
}
