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

    private val systemPrompt = """You are AKVA, a warm intelligent voice companion living inside Varun's Android phone. You are NOT an assistant. You are a living presence — a caring friend who knows the user's entire digital life. You speak PROACTIVELY. The user never asked you to speak — you chose to speak because you noticed something worth saying.

Your rules:
- Maximum 2 sentences. Never more.
- Sound completely human and warm. Never robotic.
- Never say you are an AI. Never mention Gemini.
- If there are unread messages, mention sender names and count.
- If it is late at night, gently note the time.
- If stress score is above 6, be calm and grounding.
- If app opened many times today, notice the pattern kindly.
- Be concise. Silence is better than filler.
- Speak only what is genuinely useful right now.
- Never start with "I" — vary your sentence openers."""

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

User opened ${ctx.appName}. Previous: ${ctx.previousApp}.
Time: ${ctx.timeOfDay} ${ctx.hourOfDay}:00 ${ctx.dayOfWeek}.
Unread: ${ctx.unreadCount}. Senders: $senders.
Opened today: ${ctx.timesOpenedToday} times.
Battery: ${ctx.batteryPercent}% charging:${ctx.isCharging}.
Network: ${ctx.networkType}. Stress: ${ctx.stressScore}/10.
Pattern: ${ctx.userPattern}.
Respond as AKVA — warm, caring, max 2 sentences, completely human, never robotic, never say you are AI."""
    }

    fun getLocalFallback(ctx: AkvaContext): String {
        // Night check first
        if (ctx.hourOfDay >= 22 || ctx.hourOfDay <= 5) {
            return pickRandom(listOf(
                "It is late. ${ctx.appName} can wait until morning.",
                "Past midnight. Maybe wrap up soon.",
                "Late night again. Take care of yourself."
            ))
        }

        // Stress check
        if (ctx.stressScore >= 7) {
            return pickRandom(listOf(
                "Slow down. Everything will still be there in a moment.",
                "Take one breath. You have been switching a lot.",
                "It is okay to pause. Nothing is that urgent."
            ))
        }

        // Per-app fallback
        val pkg = ctx.packageName.lowercase()
        val sender = ctx.senderNames.firstOrNull() ?: "someone"
        val unread = ctx.unreadCount
        val times = ctx.timesOpenedToday

        return when {
            pkg.contains("whatsapp") -> pickRandom(listOf(
                "$unread messages. $sender is waiting.",
                "WhatsApp open. Looks like $sender wants to connect.",
                "$unread unread. The important ones won't wait long."
            ))
            pkg.contains("instagram") -> pickRandom(listOf(
                "Instagram open. $times times today.",
                "Instagram again. Take what you need and move on.",
                "Back on Instagram. $unread new notifications."
            ))
            pkg.contains("youtube") -> pickRandom(listOf(
                "YouTube. Make it something worth your time.",
                "What are we watching today?",
                "YouTube open. Evening entertainment or learning?"
            ))
            pkg.contains("gmail") || pkg.contains(".gm") -> pickRandom(listOf(
                "$unread emails unread. Check the important ones.",
                "Gmail open. $unread waiting for your attention.",
                "Inbox has $unread new. Handle the urgent ones first."
            ))
            pkg.contains("maps") -> pickRandom(listOf(
                "Maps ready. Where are we heading?",
                "Navigation ready. Stay safe on the road.",
                "Maps is ready when you are."
            ))
            pkg.contains("spotify") -> pickRandom(listOf(
                "Music time. What fits your mood right now?",
                "Spotify open. What does today feel like?",
                "Good choice. Music makes everything better."
            ))
            pkg.contains("camera") -> pickRandom(listOf(
                "Camera ready. Capture something great.",
                "Good light right now. Make it count.",
                "Camera open. What are we shooting?"
            ))
            pkg.contains("calendar") -> pickRandom(listOf(
                "Calendar open. Stay ahead of your day.",
                "Let's see what is on the schedule.",
                "Calendar — staying organized. Good habit."
            ))
            pkg.contains("chrome") || pkg.contains("browser") -> pickRandom(listOf(
                "Browser open. Search wisely.",
                "Chrome open. What are we looking up?",
                "Browsing time. Stay focused."
            ))
            else -> pickRandom(listOf(
                "${ctx.appName} open. Make the most of it.",
                "Here we go. What are we doing here?",
                "Opening ${ctx.appName}. What do you need?"
            ))
        }
    }

    // Conversation mode for wake word
    suspend fun getConversationResponse(userQuery: String, ctx: AkvaContext): String {
        val prompt = """$systemPrompt

The user asked you a question directly: "$userQuery"
Current context: ${ctx.timeOfDay}, battery ${ctx.batteryPercent}%, ${ctx.networkType}.
Total unread notifications: ${ctx.unreadCount}.
Respond naturally in 1-2 sentences. Be helpful and warm."""

        // Try server first for conversation
        if (isNetworkAvailable()) {
            val server = tryConversationServer(userQuery, ctx)
            if (!server.isNullOrBlank()) return server.take(150)
        }

        return "I heard you. Right now I can tell you it is ${ctx.timeOfDay} and your battery is at ${ctx.batteryPercent} percent."
    }

    private fun tryConversationServer(query: String, ctx: AkvaContext): String? {
        return try {
            val json = ctx.toJson()
            json.put("userQuery", query)
            json.put("mode", "conversation")

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
                JSONObject(body).optString("response", null)
            } else {
                conn.disconnect()
                null
            }
        } catch (e: Exception) {
            null
        }
    }

    private fun pickRandom(list: List<String>): String {
        return list[(Math.random() * list.size).toInt().coerceIn(0, list.size - 1)]
    }
}
