package com.varun.akva.data

import androidx.room.*

@Dao
interface ConversationDao {
    @Query("SELECT * FROM conversation_memory ORDER BY timestamp DESC LIMIT :limit")
    suspend fun getRecentConversations(limit: Int): List<ConversationMemory>

    @Insert
    suspend fun insert(conversation: ConversationMemory)

    @Query("DELETE FROM conversation_memory")
    suspend fun clearHistory()

    @Query("DELETE FROM conversation_memory WHERE timestamp < :threshold")
    suspend fun deleteOldConversations(threshold: Long)
}
