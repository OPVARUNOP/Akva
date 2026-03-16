package com.varun.akva.intelligence

import android.content.Context
import com.varun.akva.data.AkvaRepository
import java.util.Calendar

class PatternLearner(private val context: Context) {

    private val repository = AkvaRepository(context)

    suspend fun getMostUsedApps(): List<String> {
        val since = System.currentTimeMillis() - 24 * 60 * 60 * 1000
        return try {
            repository.getMostUsedApps(since).map { it.packageName }
        } catch (e: Exception) {
            emptyList()
        }
    }

    suspend fun getUserPatternSummary(): String {
        return try {
            val since = System.currentTimeMillis() - 7L * 24 * 60 * 60 * 1000
            val events = repository.getRecentUsage(since)
            if (events.isEmpty()) return "Regular usage"

            val hourCounts = IntArray(24)
            events.forEach { hourCounts[getHourFromTimestamp(it.timestamp)]++ }
            val peakHour = hourCounts.indices.maxByOrNull { hourCounts[it] } ?: 12

            val peakPeriod = when (peakHour) {
                in 5..11 -> "mornings"
                in 12..16 -> "afternoons"
                in 17..21 -> "evenings"
                else -> "nights"
            }

            "Usually active $peakPeriod"
        } catch (e: Exception) {
            "Regular usage"
        }
    }

    suspend fun getSleepEstimate(): String {
        return try {
            val since = System.currentTimeMillis() - 48L * 60 * 60 * 1000
            val events = repository.getRecentUsage(since).sortedBy { it.timestamp }
            if (events.size < 2) return "Unknown"

            var longestGap = 0L
            var sleepStart = 0L
            for (i in 1 until events.size) {
                val gap = events[i].timestamp - events[i - 1].timestamp
                if (gap > longestGap) {
                    longestGap = gap
                    sleepStart = events[i - 1].timestamp
                }
            }

            if (longestGap > 3 * 60 * 60 * 1000) {
                val cal = Calendar.getInstance().apply { timeInMillis = sleepStart }
                val hour = cal.get(Calendar.HOUR_OF_DAY)
                val min = cal.get(Calendar.MINUTE)
                "${hour}:${String.format("%02d", min)}"
            } else "Unknown"
        } catch (e: Exception) {
            "Unknown"
        }
    }

    suspend fun getWakeEstimate(): String {
        return try {
            val since = System.currentTimeMillis() - 48L * 60 * 60 * 1000
            val events = repository.getRecentUsage(since).sortedBy { it.timestamp }
            if (events.size < 2) return "Unknown"

            var longestGap = 0L
            var wakeIdx = 0
            for (i in 1 until events.size) {
                val gap = events[i].timestamp - events[i - 1].timestamp
                if (gap > longestGap) {
                    longestGap = gap
                    wakeIdx = i
                }
            }

            if (longestGap > 3 * 60 * 60 * 1000) {
                val cal = Calendar.getInstance().apply { timeInMillis = events[wakeIdx].timestamp }
                val hour = cal.get(Calendar.HOUR_OF_DAY)
                val min = cal.get(Calendar.MINUTE)
                "${hour}:${String.format("%02d", min)}"
            } else "Unknown"
        } catch (e: Exception) {
            "Unknown"
        }
    }

    private fun getHourFromTimestamp(timestamp: Long): Int {
        val cal = Calendar.getInstance()
        cal.timeInMillis = timestamp
        return cal.get(Calendar.HOUR_OF_DAY)
    }
}
