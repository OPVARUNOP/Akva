package com.varun.akva.intelligence

import android.content.Context
import com.varun.akva.data.AkvaRepository
import com.varun.akva.data.SettingsManager
import java.text.SimpleDateFormat
import java.util.*

class MorningBriefing(private val context: Context) {

    private val settingsManager = SettingsManager(context)
    private val repository = AkvaRepository(context)
    private val geminiEngine = GeminiEngine(context)
    private val contextDetector = ContextDetector(context)
    private val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.US)

    fun shouldTrigger(): Boolean {
        if (!settingsManager.morningBriefingEnabled) return false
        val hour = contextDetector.getHourOfDay()
        if (hour < 5 || hour > 11) return false
        val today = dateFormat.format(Date())
        return settingsManager.lastMorningBriefingDate != today
    }

    suspend fun generateBriefing(
        totalUnread: Int,
        perAppUnread: Map<String, Int>,
        batteryPercent: Int,
        networkType: String
    ): String {
        val day = contextDetector.getDayOfWeek()
        val time = contextDetector.getTimeOfDay()

        val appBreakdown = perAppUnread.entries
            .filter { it.value > 0 }
            .sortedByDescending { it.value }
            .take(5)
            .joinToString(", ") { "${contextDetector.getAppName(it.key)}: ${it.value}" }

        val ctx = AkvaContext(
            appName = "Morning Briefing",
            packageName = "com.varun.akva.morning",
            previousApp = "sleep",
            timeOfDay = time,
            hourOfDay = contextDetector.getHourOfDay(),
            dayOfWeek = day,
            unreadCount = totalUnread,
            senderNames = emptyList(),
            timesOpenedToday = 0,
            batteryPercent = batteryPercent,
            isCharging = false,
            networkType = networkType,
            stressScore = 0,
            userPattern = "First unlock of the day. Unread breakdown: $appBreakdown",
            deviceId = ""
        )

        return geminiEngine.getResponse(ctx)
    }

    fun markDelivered() {
        settingsManager.lastMorningBriefingDate = dateFormat.format(Date())
    }
}
