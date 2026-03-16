package com.varun.akva.data

import androidx.room.*

@Dao
interface AppUsageDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUsageEvent(event: AppUsageEvent)

    @Query("SELECT * FROM app_usage_events WHERE timestamp >= :since ORDER BY timestamp DESC")
    suspend fun getRecentUsage(since: Long): List<AppUsageEvent>

    @Query("SELECT * FROM app_usage_events WHERE isNightUsage = 1 AND timestamp >= :since ORDER BY timestamp DESC")
    suspend fun getLateNightUsage(since: Long): List<AppUsageEvent>

    @Query("SELECT * FROM app_usage_events WHERE packageName = :pkg AND timestamp >= :since ORDER BY timestamp DESC")
    suspend fun getUsageByPackage(pkg: String, since: Long): List<AppUsageEvent>

    @Query("SELECT COUNT(*) FROM app_usage_events WHERE packageName = :pkg AND timestamp >= :since")
    suspend fun getOpenCount(pkg: String, since: Long): Int

    @Query("SELECT packageName, COUNT(*) as cnt FROM app_usage_events WHERE timestamp >= :since GROUP BY packageName ORDER BY cnt DESC LIMIT :limit")
    suspend fun getMostUsedApps(since: Long, limit: Int = 5): List<AppCount>

    @Query("SELECT * FROM app_usage_events WHERE stressScore >= :minStress AND timestamp >= :since")
    suspend fun getStressEvents(minStress: Int, since: Long): List<AppUsageEvent>

    @Query("SELECT * FROM app_usage_events ORDER BY timestamp DESC LIMIT :limit")
    suspend fun getLatestEvents(limit: Int): List<AppUsageEvent>

    @Query("DELETE FROM app_usage_events WHERE timestamp < :timestamp")
    suspend fun deleteOlderThan(timestamp: Long)
}

data class AppCount(val packageName: String, val cnt: Int)
