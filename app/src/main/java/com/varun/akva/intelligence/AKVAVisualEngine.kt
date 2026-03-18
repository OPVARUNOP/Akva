package com.varun.akva.intelligence

import android.view.accessibility.AccessibilityNodeInfo

class AKVAVisualEngine(
    private val geminiEngine: GeminiEngine
) {
    fun extractScreenText(rootNode: AccessibilityNodeInfo?): String {
        if (rootNode == null) return ""
        val sb = StringBuilder()
        traverse(rootNode, sb)
        return sb.toString()
    }

    private fun traverse(node: AccessibilityNodeInfo, sb: StringBuilder) {
        node.text?.let { 
            if (it.isNotBlank()) sb.append(it).append(" | ")
        }
        node.contentDescription?.let {
            if (it.isNotBlank()) sb.append(it).append(" | ")
        }
        for (i in 0 until node.childCount) {
            node.getChild(i)?.let { traverse(it, sb) }
        }
    }

    suspend fun analyzeScreenContent(text: String): String {
        if (text.isBlank()) return "The screen appears to be empty or restricted, sir."
        
        val prompt = """
            The user's screen contains the following text:
            "$text"
            
            Summarize what the user is looking at in one short, professional sentence.
            If they are in a chat, mention who with. If in an app, mention the current activity.
            Address them as 'sir'.
        """.trimIndent()
        
        return geminiEngine.getResponse(prompt)
    }
}
