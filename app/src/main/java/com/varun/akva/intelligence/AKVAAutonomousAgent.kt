package com.varun.akva.intelligence

import android.content.Context
import org.json.JSONObject
import java.util.regex.Pattern

data class AkvaPlan(
    val goal: String,
    val steps: List<AkvaStep>
)

data class AkvaStep(
    val step: Int,
    val tool: String,
    val description: String,
    val parameters: JSONObject,
    val critical: Boolean = true
)

class AKVAAutonomousAgent(
    private val geminiEngine: GeminiEngine
) {
    private val PLANNER_PROMPT = """
        You are the planning module of AKVA Ultimate, a personal AI assistant for Android.
        Your job: break any user goal into a sequence of steps using ONLY the tools listed below.

        ABSOLUTE RULES:
        - NEVER use generated_code or write Python scripts.
        - EVERY step is independent in its initial definition.
        - Use web_search for information retrieval or current data.
        - Use open_app to launch applications.
        - Use send_message for messaging platforms.
        - Max 5 steps. Use the minimum steps needed.

        AVAILABLE TOOLS:
        - open_app (app_name: string)
        - send_message (platform: "WhatsApp"|"SMS", contact: string, message: string)
        - set_alarm (hour: int, minute: int)
        - call_contact (name: string)
        - web_search (query: string)
        - read_notifications ()
        - take_screenshot ()
        - toggle_setting (setting: "WIFI"|"BLUETOOTH"|"FLASHLIGHT", state: "ON"|"OFF")
        - create_reminder (text: string, time: string)
        - general_answer (text: string)

        OUTPUT — return ONLY valid JSON:
        {
          "goal": "...",
          "steps": [
            {
              "step": 1,
              "tool": "tool_name",
              "description": "what this step does",
              "parameters": {},
              "critical": true
            }
          ]
        }
    """.trimIndent()

    suspend fun createPlan(goal: String, context: String = ""): AkvaPlan {
        val userPrompt = if (context.isNotEmpty()) "Goal: $goal\nContext: $context" else "Goal: $goal"
        
        return try {
            val response = geminiEngine.getResponse("$PLANNER_PROMPT\n\n$userPrompt")
            parsePlanJson(response, goal)
        } catch (e: Exception) {
            fallbackPlan(goal)
        }
    }

    private fun parsePlanJson(jsonStr: String, goal: String): AkvaPlan {
        val cleanJson = jsonStr.substringAfter("{").substringBeforeLast("}") .let { "{$it}" }
        val root = JSONObject(cleanJson)
        val stepsArray = root.getJSONArray("steps")
        val steps = mutableListOf<AkvaStep>()
        
        for (i in 0 until stepsArray.length()) {
            val s = stepsArray.getJSONObject(i)
            steps.add(AkvaStep(
                step = s.getInt("step"),
                tool = s.getString("tool"),
                description = s.getString("description"),
                parameters = s.getJSONObject("parameters"),
                critical = s.optBoolean("critical", true)
            ))
        }
        return AkvaPlan(goal, steps)
    }

    private fun fallbackPlan(goal: String): AkvaPlan {
        return AkvaPlan(goal, listOf(
            AkvaStep(1, "general_answer", "Search for: $goal", JSONObject().apply { put("text", goal) })
        ))
    }
}
