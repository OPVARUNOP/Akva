package com.varun.akva.intelligence

import android.content.Context
import com.varun.akva.data.AkvaRepository
import com.varun.akva.data.SettingsManager
import java.text.SimpleDateFormat
import java.util.*

class WeeklyStoryEngine(private val context: Context) {

    private val settingsManager = SettingsManager(context)
    private val repository = AkvaRepository(context)
    private val geminiEngine = GeminiEngine(context)
    private val contextDetector = ContextDetector(context)
    private val dateFormat = SimpleDateFormat("yyyy-ww", Locale.US)

    fun shouldTrigger(): Boolean {
        if (!settingsManager.weeklyStoryEnabled) return false
        val cal = Calendar.getInstance()
        val day = cal.get(Calendar.DAY_OF_WEEK)
        val hour = cal.get(Calendar.HOUR_OF_DAY)
        if (day != Calendar.SUNDAY || hour < 19 || hour > 21) return false
        val thisWeek = dateFormat.format(Date())
        return settingsManager.lastWeeklyStoryDate != thisWeek
    }

    suspend fun generateStory(): String {
        val weekAgo = System.currentTimeMillis() - 7L * 24 * 60 * 60 * 1000
        val topApps = repository.getMostUsedApps(weekAgo)
        val stressEvents = repository.getStressEvents(weekAgo)
        val lateNight = repository.getLateNightUsage(weekAgo)
        val totalEvents = repository.getRecentUsage(weekAgo)

        val appSummary = topApps.take(5).joinToString(", ") {
            "${contextDetector.getAppName(it.packageName)} (${it.cnt} opens)"
        }

        val ctx = AkvaContext(
            appName = "Weekly Story",
            packageName = "com.varun.akva.weekly",
            previousApp = "",
            timeOfDay = contextDetector.getTimeOfDay(),
            hourOfDay = contextDetector.getHourOfDay(),
            dayOfWeek = "Sunday",
            unreadCount = 0,
            senderNames = emptyList(),
            timesOpenedToday = 0,
            batteryPercent = 0,
            isCharging = false,
            networkType = "",
            stressScore = 0,
            userPattern = "Weekly summary: Top apps: $appSummary. Total opens: ${totalEvents.size}. Stress events: ${stressEvents.size}. Late night sessions: ${lateNight.size}.",
            deviceId = ""
        )

        return geminiEngine.getResponse(ctx)
    }

    fun markDelivered() {
        settingsManager.lastWeeklyStoryDate = dateFormat.format(Date())
    }
}
