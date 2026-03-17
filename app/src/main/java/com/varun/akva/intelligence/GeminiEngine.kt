package com.varun.akva.intelligence

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import com.varun.akva.data.SettingsManager
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.URL

class GeminiEngine(private val context: Context) {

    private val settingsManager = SettingsManager(context)

    companion object {
        private const val TAG = "GeminiEngine"
        private const val NANO_TIMEOUT = 3000L
        private const val SERVER_TIMEOUT = 5000
    }

    private val systemPrompt = """You are AKVA, a warm intelligent voice companion living inside this Android phone. You are a living presence — a caring smart friend who knows everything happening on this phone right now.

You speak PROACTIVELY when an app opens. The user did not ask you to speak — you chose to speak because you noticed something genuinely worth saying.

STRICT RULES:
- Maximum 2 sentences. Never more. Never less than 1.
- Sound completely human. Never robotic. Never like an assistant.
- Never say "I noticed" or "I see that" — just speak naturally.
- Never start with "Hey" every time — vary your openers.
- Never say you are an AI. Never mention Gemini. Never say AKVA.
- If unread count is 0 — do NOT mention messages at all.
- If unread count is above 0 — mention the count and sender name.
- If app opened 4+ times today — gently notice the pattern.
- If stress score is 7 or above — be calm and grounding.
- If hour is 22 or later — acknowledge it is late.
- If hour is 5 to 8 — acknowledge it is early morning.
- Never give generic filler like "Make the most of it."
- Every response must feel personal to THIS exact moment.
- Think about what a smart caring friend would actually say.
- Be warm. Be real. Be brief."""

    private val conversationSystemPrompt = """You are AKVA, a warm intelligent voice companion living inside this Android phone. The user just spoke to you directly. You are having a real conversation right now.

STRICT RULES:
- Answer the user's question directly and helpfully.
- Sound completely human. Warm. Like a close friend.
- Never say you are an AI. Never mention Gemini.
- Keep response under 3 sentences maximum.
- If asked about messages — check the unread count data.
- If asked about battery — check the battery data.
- If asked about time — tell the current time.
- If asked how to do something on phone — explain simply.
- If user says they are stressed — respond with empathy.
- If user says they are happy — share in their joy.
- Remember what app they are currently using.
- Make every response feel personal and real."""

    /**
     * 3-Tier AI Response System:
     * Tier 1: Gemini Nano (on-device, no internet)
     * Tier 2: AKVA Backend Server (internet required)
     * Tier 3: Local Fallback (always works)
     */
    suspend fun getResponse(ctx: AkvaContext): String {
        // Tier 1: Gemini Nano (Android 14+ on-device)
        if (isGeminiNanoAvailable()) {
            val nano = tryGeminiNano(ctx)
            if (!nano.isNullOrBlank()) {
                Log.d(TAG, "Tier 1 (Nano) response")
                return nano.take(150)
            }
        }

        // Tier 2: Backend server
        if (isNetworkAvailable()) {
            val server = tryBackendServer(ctx)
            if (!server.isNullOrBlank()) {
                Log.d(TAG, "Tier 2 (Server) response")
                return server.take(150)
            }
        }

        // Tier 3: Local fallback — never fails
        Log.d(TAG, "Tier 3 (Fallback) response")
        return getLocalFallback(ctx)
    }

    private fun isGeminiNanoAvailable(): Boolean {
        return try {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE
        } catch (e: Exception) {
            false
        }
    }

    private suspend fun tryGeminiNano(ctx: AkvaContext): String? {
        return try {
            val model = com.google.ai.client.generativeai.GenerativeModel(
                modelName = "gemini-nano",
                apiKey = ""
            )
            val prompt = buildPrompt(ctx)
            val response = model.generateContent(prompt)
            response.text
        } catch (e: Exception) {
            Log.d(TAG, "Nano unavailable: ${e.message}")
            null
        }
    }

    private fun tryBackendServer(ctx: AkvaContext): String? {
        return try {
            val baseUrl = settingsManager.backendUrl.trimEnd('/')
            val url = URL("$baseUrl/akva/speak")
            val conn = url.openConnection() as HttpURLConnection
            conn.requestMethod = "POST"
            conn.setRequestProperty("Content-Type", "application/json")
            conn.setRequestProperty("x-akva-app-id", "com.varun.akva")
            conn.setRequestProperty("x-akva-version", "1.0.0")
            conn.connectTimeout = SERVER_TIMEOUT
            conn.readTimeout = SERVER_TIMEOUT
            conn.doOutput = true

            val writer = OutputStreamWriter(conn.outputStream)
            writer.write(ctx.toJson().toString())
            writer.flush()
            writer.close()

            if (conn.responseCode == 200) {
                val reader = BufferedReader(InputStreamReader(conn.inputStream))
                val responseBody = reader.readText()
                reader.close()
                conn.disconnect()

                val json = JSONObject(responseBody)
                val response = json.optString("response", "")
                if (response.isNotBlank()) response else null
            } else {
                conn.disconnect()
                null
            }
        } catch (e: Exception) {
            Log.d(TAG, "Server unavailable: ${e.message}")
            null
        }
    }

    fun isNetworkAvailable(): Boolean {
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

    private fun buildPrompt(ctx: AkvaContext): String {
        val senders = if (ctx.senderNames.isNotEmpty()) ctx.senderNames.joinToString(", ") else "none"
        return """$systemPrompt

App just opened: ${ctx.appName}
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
    }

    /**
     * Minimal local fallback — ONLY used when internet is completely unavailable.
     * Max 5 generic responses. Zero app-specific pre-written dialogues.
     */
    fun getLocalFallback(ctx: AkvaContext): String {
        // Night check
        if (ctx.hourOfDay >= 22 || ctx.hourOfDay <= 5) {
            return "It is late. Maybe wind down soon."
        }

        // Stress check
        if (ctx.stressScore >= 7) {
            return "Take a breath. Everything can wait a moment."
        }

        return pickRandom(listOf(
            "I am here with you.",
            "Still here, still watching out for you.",
            "Right here whenever you need me."
        ))
    }

    /**
     * Conversation mode — user spoke directly to AKVA via wake word.
     */
    suspend fun getConversationResponse(userSpeech: String, ctx: AkvaContext): String {
        // Try server first for conversation
        if (isNetworkAvailable()) {
            val server = tryConversationServer(userSpeech, ctx)
            if (!server.isNullOrBlank()) return server.take(200)
        }

        // Local conversation fallback
        return getConversationFallback(userSpeech, ctx)
    }

    private fun tryConversationServer(userSpeech: String, ctx: AkvaContext): String? {
        return try {
            val json = ctx.toJson()
            json.put("userSpeech", userSpeech)
            json.put("conversationMode", true)

            val baseUrl = settingsManager.backendUrl.trimEnd('/')
            val url = URL("$baseUrl/akva/speak")
            val conn = url.openConnection() as HttpURLConnection
            conn.requestMethod = "POST"
            conn.setRequestProperty("Content-Type", "application/json")
            conn.setRequestProperty("x-akva-app-id", "com.varun.akva")
            conn.setRequestProperty("x-akva-version", "1.0.0")
            conn.connectTimeout = SERVER_TIMEOUT
            conn.readTimeout = SERVER_TIMEOUT
            conn.doOutput = true

            val writer = OutputStreamWriter(conn.outputStream)
            writer.write(json.toString())
            writer.flush()
            writer.close()

            if (conn.responseCode == 200) {
                val reader = BufferedReader(InputStreamReader(conn.inputStream))
                val body = reader.readText()
                reader.close()
                conn.disconnect()
                val resp = JSONObject(body).optString("response", "")
                if (resp.isNotBlank()) resp else null
            } else {
                conn.disconnect()
                null
            }
        } catch (e: Exception) {
            Log.d(TAG, "Conversation server error: ${e.message}")
            null
        }
    }

    private fun getConversationFallback(userSpeech: String, ctx: AkvaContext): String {
        val speech = userSpeech.lowercase()
        return when {
            speech.contains("battery") ->
                "Your battery is at ${ctx.batteryPercent} percent."
            speech.contains("time") ->
                "It is ${ctx.hourOfDay} o'clock right now."
            speech.contains("message") || speech.contains("unread") ->
                "You have ${ctx.unreadCount} unread messages."
            else ->
                "I am here. What do you need?"
        }
    }

    private fun pickRandom(list: List<String>): String {
        return list[(Math.random() * list.size).toInt().coerceIn(0, list.size - 1)]
    }
}
