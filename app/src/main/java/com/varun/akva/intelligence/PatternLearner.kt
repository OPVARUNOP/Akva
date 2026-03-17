package com.varun.akva.intelligence

import android.content.Context
import com.varun.akva.data.AppUsageEvent
import java.util.Calendar

class PatternLearner(private val context: Context) {
    fun deducePattern(events: List<AppUsageEvent>, currentApp: String): String {
        if (events.isEmpty()) return "New pattern"
        val sameAppEvents = events.filter { it.packageName == currentApp }
        if (sameAppEvents.size > 5) {
            return "Frequent usage"
        }
        return "Occasional usage"
    }

    fun getTimesOpenedToday(events: List<AppUsageEvent>, packageName: String): Int {
        val today = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
        }.timeInMillis
        return events.count { it.packageName == packageName && it.timestamp > today }
    }
}
