package com.varun.akva.intelligence

import com.varun.akva.data.SettingsManager
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject

class AKVAWebSearch(private val s: SettingsManager) {
    private val client = OkHttpClient()
    suspend fun search(q: String): String {
        val url = "https://www.googleapis.com/customsearch/v1?key=${s.searchApiKey}&cx=${s.searchCx}&q=$q"
        return try {
            val r = client.newCall(Request.Builder().url(url).build()).execute()
            if (r.isSuccessful) JSONObject(r.body?.string() ?: "{}").optJSONArray("items")?.getJSONObject(0)?.optString("snippet") ?: "No results." else "Failed."
        } catch(_: Exception) { "Error." }
    }
}
