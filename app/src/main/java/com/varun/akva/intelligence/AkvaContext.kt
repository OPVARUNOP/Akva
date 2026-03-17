package com.varun.akva.intelligence

data class AkvaContext(
    val appName: String,
    val packageName: String,
    val previousApp: String,
    val timeOfDay: String,
    val hourOfDay: Int,
    val dayOfWeek: String,
    val unreadCount: Int,
    val totalUnread: Int = 0,
    val senderNames: List<String>,
    val timesOpenedToday: Int,
    val batteryPercent: Int,
    val isCharging: Boolean,
    val networkType: String,
    val stressScore: Int,
    val userPattern: String,
    val deviceId: String
)
