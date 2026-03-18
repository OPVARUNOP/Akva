package com.varun.akva.intelligence

import com.varun.akva.data.SettingsManager
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject
import java.util.concurrent.TimeUnit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AKVAWebSearch(
    private val geminiEngine: GeminiEngine,
    private val settingsManager: SettingsManager
) {
    private val client = OkHttpClient.Builder()
        .connectTimeout(5, TimeUnit.SECONDS)
        .readTimeout(5, TimeUnit.SECONDS)
        .build()

    suspend fun search(query: String): String = withContext(Dispatchers.IO) {
        val apiKey = settingsManager.getSearchApiKey()
        val cx = settingsManager.getSearchCx()
        
        if (apiKey.isEmpty() || cx.isEmpty()) {
            return@withContext "I need a Search API key to look that up for you, sir. You can add it in the config panel."
        }

        val url = "https://www.googleapis.com/customsearch/v1?key=$apiKey&cx=$cx&q=$query&num=3"
        
        try {
            val request = Request.Builder().url(url).build()
            val response = client.newCall(request).execute()
            if (!response.isSuccessful) {
                response.close()
                return@withContext "I'm having trouble searching the web right now, sir."
            }
            
            val body = response.body?.string() ?: ""
            response.close()
            
            val json = JSONObject(body)
            val items = json.optJSONArray("items") ?: return@withContext "I couldn't find any results for '$query', sir."
            
            val results = StringBuilder()
            for (i in 0 until minOf(3, items.length())) {
                val item = items.getJSONObject(i)
                results.append("Title: ${item.getString("title")}\n")
                results.append("Snippet: ${item.getString("snippet")}\n\n")
            }
            
            val prompt = "Summarize these search results in 2-3 natural sentences for spoken delivery:\n$results"
            return@withContext geminiEngine.getResponse(prompt)
            
        } catch (e: Exception) {
            return@withContext "I couldn't connect to the internet to search for that, sir."
        }
    }

    suspend fun searchWeather(location: String): String = withContext(Dispatchers.IO) {
        val url = "https://api.open-meteo.com/v1/forecast?latitude=16.5062&longitude=80.6480&current_weather=true"
        
        try {
            val request = Request.Builder().url(url).build()
            val response = client.newCall(request).execute()
            if (!response.isSuccessful) {
                response.close()
                return@withContext "I can't reach the weather service right now."
            }
            
            val body = response.body?.string() ?: ""
            response.close()
            
            val json = JSONObject(body)
            val current = json.getJSONObject("current_weather")
            val temp = current.getDouble("temperature")
            
            return@withContext "It's about $temp degrees in $location right now. The conditions seem fair."
        } catch (e: Exception) {
            return@withContext "I'm not sure what the weather is like in $location right now, sir."
        }
    }
}
