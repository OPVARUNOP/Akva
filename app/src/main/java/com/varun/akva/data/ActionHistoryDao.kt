package com.varun.akva.data

import androidx.room.*

@Dao
interface ActionHistoryDao {
    @Query("SELECT * FROM action_history ORDER BY timestamp DESC LIMIT :limit")
    suspend fun getRecentActions(limit: Int): List<ActionHistory>

    @Insert
    suspend fun insert(action: ActionHistory)

    @Query("DELETE FROM action_history")
    suspend fun clearHistory()

    @Query("DELETE FROM action_history WHERE timestamp < :threshold")
    suspend fun deleteOldActions(threshold: Long)
}
