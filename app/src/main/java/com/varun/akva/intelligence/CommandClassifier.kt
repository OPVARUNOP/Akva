package com.varun.akva.intelligence

import org.json.JSONObject

data class CommandResult(
    val isCommand: Boolean,
    val commandType: String,
    val appName: String?,
    val contactName: String?,
    val messageText: String?,
    val searchQuery: String?,
    val alarmTime: String?,
    val destination: String?
) {
    companion object {
        fun fromJson(jsonStr: String): CommandResult {
            return try {
                // Remove markdown formatting if Gemini included it
                val cleanJson = jsonStr.replace("```json", "").replace("```", "").trim()
                val obj = JSONObject(cleanJson)
                CommandResult(
                    isCommand = obj.optBoolean("isCommand", false),
                    commandType = obj.optString("commandType", ""),
                    appName = obj.optString("appName", null).takeIf { it != "null" && !it.isNullOrBlank() },
                    contactName = obj.optString("contactName", null).takeIf { it != "null" && !it.isNullOrBlank() },
                    messageText = obj.optString("messageText", null).takeIf { it != "null" && !it.isNullOrBlank() },
                    searchQuery = obj.optString("searchQuery", null).takeIf { it != "null" && !it.isNullOrBlank() },
                    alarmTime = obj.optString("alarmTime", null).takeIf { it != "null" && !it.isNullOrBlank() },
                    destination = obj.optString("destination", null).takeIf { it != "null" && !it.isNullOrBlank() }
                )
            } catch (e: Exception) {
                CommandResult(false, "", null, null, null, null, null, null)
            }
        }
    }
}
