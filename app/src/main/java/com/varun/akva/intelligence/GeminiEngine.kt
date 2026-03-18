package com.varun.akva.intelligence

import android.content.Context
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.content
import com.varun.akva.data.SettingsManager
import com.varun.akva.data.UserProfile
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import java.util.concurrent.TimeUnit

class GeminiEngine(private val ctx: Context, private val s: SettingsManager) {
    private val client = OkHttpClient.Builder().connectTimeout(15, TimeUnit.SECONDS).build()
    private var model: GenerativeModel? = if(s.geminiApiKey.isNotBlank()) GenerativeModel("gemini-1.5-flash", s.geminiApiKey) else null

    suspend fun getResponse(p: String, pr: UserProfile?, app: String = "", tier: Int = 1): String {
        if (tier == 1) {
            val res = callBackend(p, pr, app)
            if (res != null) return res
        }
        if (tier <= 2 && model != null) {
            val res = model?.generateContent(content { text("User: ${pr?.userName}. Memory: ${pr?.facts}. Context: $app. System: Be JARVIS-like."); text(p) })
            if (res?.text != null) return res.text!!
        }
        return "Acknowledged. Operating on local awareness."
    }

    private suspend fun callBackend(p: String, pr: UserProfile?, app: String): String? {
        val body = JSONObject().apply { put("query", p); put("user", pr?.userName); put("ctx", app) }.toString().toRequestBody("application/json".toMediaType())
        val req = Request.Builder().url("${s.backendUrl}/chat").post(body).build()
        return try { val r = client.newCall(req).execute(); if(r.isSuccessful) JSONObject(r.body?.string() ?: "{}").optString("response", null) else null } catch(_: Exception) { null }
    }
}
