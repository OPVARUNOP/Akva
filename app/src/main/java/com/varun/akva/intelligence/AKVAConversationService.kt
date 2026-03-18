package com.varun.akva.intelligence

import com.varun.akva.interaction.VoiceEngine
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AKVAConversationService(
    private val geminiEngine: GeminiEngine,
    private val memoryEngine: AKVAMemoryEngine,
    private val autonomousAgent: AKVAAutonomousAgent,
    private val executor: AKVAExecutor,
    private val responseEngine: AKVAResponseEngine,
    private val feedback: AKVAActionFeedback
) {
    private val scope = CoroutineScope(Dispatchers.Main)

    suspend fun onUserSpeaks(userText: String): String {
        // 1. Get Memory and Identity Context
        val memoryContext = memoryEngine.getUserContext()
        val history = memoryEngine.getConversationHistory(5)
        
        // 2. Build Multi-Turn JARVIS Prompt
        val systemPrompt = """
            You are AKVA — The Living OS. Sharp, efficient, and proactive.
            Memory: $memoryContext
            History: $history
            
            Rules:
            - Max 2 sentences.
            - Calm, professional, but warm.
            - If an action is requested, just say "On it, sir" or "Right away" and let the agent work.
            - Never mentions you are an AI.
        """.trimIndent()

        // 3. Generate initial response
        val response = geminiEngine.getResponse("$systemPrompt\nUser: $userText")
        
        // 4. Decision: Is this a complex task needing planning?
        if (isActionRequest(userText)) {
            feedback.onActionStart(userText)
            scope.launch(Dispatchers.IO) {
                try {
                    val plan = autonomousAgent.createPlan(userText, memoryContext)
                    val result = executor.execute(plan) { status ->
                        // Optional: trigger feedback on status change
                    }
                    feedback.onActionSuccess()
                    // Optionally speak the result
                } catch (e: Exception) {
                    feedback.onActionFailure()
                }
            }
            val quickAck = if (response.length < 50) response else "On it, sir."
            responseEngine.speak(quickAck)
            return quickAck
        } else {
            responseEngine.speak(response)
            memoryEngine.saveConversation(userText, response)
            return response
        }
    }

    private fun isActionRequest(text: String): Boolean {
        val keywords = listOf("open", "send", "call", "set", "alarm", "remind", "search", "turn", "take", "read", "click", "scroll", "type")
        return keywords.any { text.contains(it, ignoreCase = true) }
    }
}
