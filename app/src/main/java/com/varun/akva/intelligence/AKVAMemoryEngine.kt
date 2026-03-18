package com.varun.akva.intelligence

import com.varun.akva.data.*
import org.json.JSONArray
import org.json.JSONObject

class AKVAMemoryEngine(private val r: AkvaRepository, private val g: GeminiEngine) {
    suspend fun processConversation(u: String, a: String) {
        r.addMemory(ConversationMemory(userSaid = u, akvaReplied = a))
        // Fact extraction logic can be triggered here every N turns
    }
}
