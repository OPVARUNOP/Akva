package com.varun.akva.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "app_personalities")
data class AppPersonality(
    @PrimaryKey val packageName: String,
    val voiceStyle: String = "default",
    val isEnabled: Boolean = true,
    val pitch: Float = 1.0f,
    val speechRate: Float = 0.95f
)
