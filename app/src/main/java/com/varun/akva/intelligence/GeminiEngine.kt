package com.varun.akva.intelligence

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import com.varun.akva.data.SettingsManager
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.URL
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GeminiEngine(private val context: Context) {
    private val settingsManager = SettingsManager(context)

    companion object {
        private const val TAG = "GeminiEngine"
        private const val SERVER_TIMEOUT = 8000
    }

    private val observerPrompt = """You are AKVA, a warm AI companion on this Android phone.
An app just opened. You may choose to say something or stay silent.

Rules:
- If you have something genuinely useful to say: say it in 1-2 sentences
- If you have nothing useful to say: return exactly the word SILENT
- Never generic filler. Only speak if it adds real value.
- Sound human. Warm. Like a close friend who notices things.
- Never say you are AI. Never mention Gemini or AKVA.
- Mention unread messages only if count is above 0.
- If opened 4 plus times today: gently notice the habit.
- If after 10PM: acknowledge it is late only if relevant."""

    private val conversationPrompt = """You are AKVA, a warm AI companion on this Android phone.
The user just spoke to you directly. Have a real conversation.

Rules:
- Answer directly and helpfully. Max 3 sentences.
- Sound human. Warm. Like a close friend.
- Never say you are AI. Never mention Gemini or AKVA.
- Use the phone context data to give accurate answers.
- If asked about messages: use the unreadCount data.
- If asked about battery: use the batteryPercent data.
- If asked about time: use the hourOfDay data.
- If asked to do something: say you will do it right now.
- Be natural. Be brief. Be real."""

    private val commandPrompt = """Classify this as a phone command. Return JSON only. No other text.
User said: "{userSpeech}"

JSON format:
{
  "isCommand": true or false,
  "commandType": "OPEN_APP|CALL|MESSAGE|ALARM|SEARCH|NAVIGATE|PLAY_MUSIC|PAUSE_MUSIC|NEXT_SONG|PREV_SONG|ANSWER_CALL|END_CALL|TAKE_PHOTO|SET_REMINDER|WIFI_ON|WIFI_OFF|BLUETOOTH_ON|BLUETOOTH_OFF|VOLUME_UP|VOLUME_DOWN|MUTE|UNMUTE|FLASHLIGHT_ON|FLASHLIGHT_OFF|SCREENSHOT|READ_MESSAGES|CHECK_BATTERY|CHECK_TIME|CONVERSATION",
  "appName": "app name or null",
  "contactName": "contact name or null",
  "messageText": "message content or null",
  "searchQuery": "search query or null",
  "alarmTime": "time string or null",
  "destination": "place name or null"
}

Examples:
"open instagram" = OPEN_APP instagram
"call mom" = CALL mom
"message rahul say hi" = MESSAGE rahul hi
"alarm 7am" = ALARM 7:00 AM
"search restaurants near me" = SEARCH restaurants near me
"take me to airport" = NAVIGATE airport
"answer" or "pick up" = ANSWER_CALL
"hang up" or "end call" = END_CALL
"turn on wifi" = WIFI_ON
"flashlight on" = FLASHLIGHT_ON
"volume up" = VOLUME_UP
"screenshot" = SCREENSHOT
"read my messages" = READ_MESSAGES
"what time is it" = CHECK_TIME
"how is my battery" = CHECK_BATTERY
"I am feeling stressed" = CONVERSATION (not a command)"""

    suspend fun getObserverResponse(ctx: AkvaContext): String = withContext(Dispatchers.IO) {
        if (!isNetworkAvailable()) return@withContext getLocalFallback(ctx)

        val senders = if (ctx.senderNames.isNotEmpty()) ctx.senderNames.joinToString(", ") else "none"
        val userMessage = """App just opened: ${ctx.appName}
Previous app: ${ctx.previousApp}
Time: ${ctx.hourOfDay}:00 on ${ctx.dayOfWeek} (${ctx.timeOfDay})
Unread notifications in ${ctx.appName}: ${ctx.unreadCount}
People waiting: $senders
Times opened today: ${ctx.timesOpenedToday}
Battery: ${ctx.batteryPercent}% — charging: ${ctx.isCharging}
Network: ${ctx.networkType}
Stress level: ${ctx.stressScore} out of 10
User pattern: ${ctx.userPattern}

Speak to the user right now about this moment."""

        val json = JSONObject()
        json.put("requestMode", "observer")
        json.put("userMessage", userMessage)
        json.put("systemPrompt", observerPrompt)
        
        // Add raw context for fallback logic on backend
        json.put("appName", ctx.appName)
        json.put("hourOfDay", ctx.hourOfDay)
        json.put("stressScore", ctx.stressScore)

        val response = callBackend(json)
        
        if (response.isNullOrBlank() || response.trim().uppercase() == "SILENT") {
            return@withContext ""
        }
        return@withContext response
    }

    suspend fun getConversationResponse(userSpeech: String, ctx: AkvaContext): String = withContext(Dispatchers.IO) {
        if (!isNetworkAvailable()) return@withContext getConversationFallback(userSpeech, ctx)

        val userMessage = """Time: ${ctx.hourOfDay}:00 ${ctx.dayOfWeek}
Current app: ${ctx.appName}
Battery: ${ctx.batteryPercent}% charging:${ctx.isCharging}
Unread messages: ${ctx.totalUnread}
Stress: ${ctx.stressScore}/10
User said: $userSpeech
Respond naturally."""

        val json = JSONObject()
        json.put("requestMode", "conversation")
        json.put("userMessage", userMessage)
        json.put("systemPrompt", conversationPrompt)
        
        // Context for fallback
        json.put("userSpeech", userSpeech)
        json.put("batteryPercent", ctx.batteryPercent)
        json.put("hourOfDay", ctx.hourOfDay)
        json.put("unreadCount", ctx.totalUnread)

        val response = callBackend(json)
        
        if (response.isNullOrBlank()) {
            return@withContext getConversationFallback(userSpeech, ctx)
        }
        return@withContext response
    }

    suspend fun classifyCommand(userSpeech: String): CommandResult = withContext(Dispatchers.IO) {
        if (!isNetworkAvailable()) return@withContext CommandResult(false, "CONVERSATION", null, null, null, null, null, null)

        val json = JSONObject()
        json.put("requestMode", "command")
        json.put("userSpeech", userSpeech)
        json.put("systemPrompt", commandPrompt.replace("{userSpeech}", userSpeech))
        json.put("userMessage", "Classify: $userSpeech")

        val response = callBackend(json)
        
        if (response.isNullOrBlank()) {
            return@withContext CommandResult(false, "CONVERSATION", null, null, null, null, null, null)
        }
        
        return@withContext CommandResult.fromJson(response)
    }

    private fun callBackend(payload: JSONObject): String? {
        return try {
            val baseUrl = settingsManager.backendUrl.trimEnd('/')
            val url = URL("$baseUrl/akva/speak")
            val conn = url.openConnection() as HttpURLConnection
            conn.requestMethod = "POST"
            conn.setRequestProperty("Content-Type", "application/json")
            conn.setRequestProperty("x-akva-app-id", "com.varun.akva")
            conn.connectTimeout = SERVER_TIMEOUT
            conn.readTimeout = SERVER_TIMEOUT
            conn.doOutput = true

            val writer = OutputStreamWriter(conn.outputStream)
            writer.write(payload.toString())
            writer.flush()
            writer.close()

            if (conn.responseCode == 200) {
                val reader = BufferedReader(InputStreamReader(conn.inputStream))
                val responseBody = reader.readText()
                reader.close()
                conn.disconnect()

                JSONObject(responseBody).optString("response", "")
            } else {
                conn.disconnect()
                null
            }
        } catch (e: Exception) {
            Log.e(TAG, "Backend error: ${e.message}")
            null
        }
    }

    private fun isNetworkAvailable(): Boolean {
        return try {
            val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val network = cm.activeNetwork ?: return false
            val caps = cm.getNetworkCapabilities(network) ?: return false
            caps.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                    caps.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
        } catch (e: Exception) {
            false
        }
    }

    private fun getLocalFallback(ctx: AkvaContext): String {
        if (ctx.hourOfDay >= 22 || ctx.hourOfDay <= 5) return "It is late. Maybe wind down soon."
        if (ctx.stressScore >= 7) return "Take a breath. Everything can wait a moment."
        val options = listOf("I am here with you.", "Still here, watching out for you.", "Right here if you need me.")
        return options[(Math.random() * options.size).toInt()]
    }

    private fun getConversationFallback(userSpeech: String, ctx: AkvaContext): String {
        val speech = userSpeech.lowercase()
        return when {
            speech.contains("battery") -> "Your battery is at ${ctx.batteryPercent} percent."
            speech.contains("time") -> "It is ${ctx.hourOfDay} o'clock right now."
            speech.contains("message") || speech.contains("unread") -> "You have ${ctx.unreadCount} unread messages."
            else -> "I am here. What do you need?"
        }
    }
}
