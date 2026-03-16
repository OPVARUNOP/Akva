package com.varun.akva.data

import android.content.Context

class AkvaRepository(context: Context) {

    private val db = AkvaDatabase.getInstance(context)
    private val usageDao = db.appUsageDao()
    private val personalityDao = db.appPersonalityDao()

    suspend fun logEvent(packageName: String, stressScore: Int, isNight: Boolean, spokenText: String) {
        usageDao.insertUsageEvent(
            AppUsageEvent(
                packageName = packageName,
                timestamp = System.currentTimeMillis(),
                isNightUsage = isNight,
                stressScore = stressScore,
                spokenText = spokenText
            )
        )
    }

    suspend fun getTimesOpenedToday(packageName: String): Int {
        val todayStart = getTodayStart()
        return usageDao.getOpenCount(packageName, todayStart)
    }

    suspend fun getRecentEvents(limit: Int): List<AppUsageEvent> {
        return usageDao.getLatestEvents(limit)
    }

    suspend fun getRecentUsage(since: Long): List<AppUsageEvent> {
        return usageDao.getRecentUsage(since)
    }

    suspend fun getMostUsedApps(since: Long, limit: Int = 5): List<AppCount> {
        return usageDao.getMostUsedApps(since, limit)
    }

    suspend fun getStressEvents(since: Long): List<AppUsageEvent> {
        return usageDao.getStressEvents(7, since)
    }

    suspend fun getLateNightUsage(since: Long): List<AppUsageEvent> {
        return usageDao.getLateNightUsage(since)
    }

    suspend fun cleanup() {
        val thirtyDaysAgo = System.currentTimeMillis() - 30L * 24 * 60 * 60 * 1000
        usageDao.deleteOlderThan(thirtyDaysAgo)
    }

    private fun getTodayStart(): Long {
        val cal = java.util.Calendar.getInstance()
        cal.set(java.util.Calendar.HOUR_OF_DAY, 0)
        cal.set(java.util.Calendar.MINUTE, 0)
        cal.set(java.util.Calendar.SECOND, 0)
        cal.set(java.util.Calendar.MILLISECOND, 0)
        return cal.timeInMillis
    }
}
