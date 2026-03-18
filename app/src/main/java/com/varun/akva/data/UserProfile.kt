package com.varun.akva.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_profile")
data class UserProfile(
    @PrimaryKey val id: Int = 1, // Only one profile
    val userName: String = "",
    val preferredName: String = "",
    val dailyRoutine: String = "",
    val lastUpdated: Long = System.currentTimeMillis()
)
