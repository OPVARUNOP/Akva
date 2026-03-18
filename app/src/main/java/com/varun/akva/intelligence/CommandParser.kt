package com.varun.akva.intelligence

import org.json.JSONObject

data class ActionIntent(val action: String, val target: String = "", val params: Map<String, String> = emptyMap())

class CommandParser(private val g: GeminiEngine) {
    suspend fun parse(u: String, p: com.varun.akva.data.UserProfile?): ActionIntent {
        val prompt = "Return JSON only: {\"action\": \"...\", \"target\": \"...\", \"params\": {}}. " +
                "Actions: OPEN_APP, CALL, SEND_MESSAGE, SET_ALARM, WEB_SEARCH, CONVERSATION. Input: $u"
        val res = g.getResponse(prompt, p, tier = 2)
        return try {
            val j = JSONObject(res)
            val params = mutableMapOf<String, String>()
            j.optJSONObject("params")?.let { pjm -> pjm.keys().forEach { k -> params[k] = pjm.getString(k) } }
            ActionIntent(j.getString("action"), j.optString("target"), params)
        } catch(_: Exception) { ActionIntent("CONVERSATION") }
    }
}
