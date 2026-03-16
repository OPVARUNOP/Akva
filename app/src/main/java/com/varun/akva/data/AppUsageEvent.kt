package com.varun.akva.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "app_usage_events")
data class AppUsageEvent(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val packageName: String,
    val timestamp: Long,
    val durationMillis: Long? = null,
    val isNightUsage: Boolean = false,
    val stressScore: Int = 0,
    val spokenText: String = ""
)
