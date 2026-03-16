package com.varun.akva.intelligence

import org.json.JSONArray
import org.json.JSONObject

data class AkvaContext(
    val appName: String,
    val packageName: String,
    val previousApp: String,
    val timeOfDay: String,
    val hourOfDay: Int,
    val dayOfWeek: String,
    val unreadCount: Int,
    val senderNames: List<String>,
    val timesOpenedToday: Int,
    val batteryPercent: Int,
    val isCharging: Boolean,
    val networkType: String,
    val stressScore: Int,
    val userPattern: String,
    val deviceId: String
) {
    fun toJson(): JSONObject = JSONObject().apply {
        put("appName", appName)
        put("packageName", packageName)
        put("previousApp", previousApp)
        put("timeOfDay", timeOfDay)
        put("hourOfDay", hourOfDay)
        put("dayOfWeek", dayOfWeek)
        put("unreadCount", unreadCount)
        put("senderNames", JSONArray(senderNames))
        put("timesOpenedToday", timesOpenedToday)
        put("batteryPercent", batteryPercent)
        put("isCharging", isCharging)
        put("networkType", networkType)
        put("stressScore", stressScore)
        put("userPattern", userPattern)
        put("deviceId", deviceId)
    }
}
