package com.varun.akva.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "conversation_memory")
data class ConversationMemory(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val timestamp: Long = System.currentTimeMillis(),
    val userSaid: String,
    val akvaReplied: String,
    val context: String = "",
    val actionTaken: String? = null
)
