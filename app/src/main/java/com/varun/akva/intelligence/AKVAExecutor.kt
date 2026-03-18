package com.varun.akva.intelligence

import android.content.Context
import com.varun.akva.services.AKVACommandExecutor
import com.varun.akva.services.AKVAAutomationService
import kotlinx.coroutines.delay
import org.json.JSONObject

class AKVAExecutor(
    private val context: Context,
    private val commandExecutor: AKVACommandExecutor,
    private val automationService: AKVAAutomationService,
    private val webSearch: AKVAWebSearch
) {
    suspend fun execute(plan: AkvaPlan, onStatus: (String) -> Unit): String {
        val stepResults = mutableMapOf<Int, String>()
        var finalSummary = ""

        for (step in plan.steps) {
            onStatus("Executing: ${step.description}")
            
            val updatedParams = injectContext(step.parameters, stepResults)
            
            try {
                val result = when (step.tool) {
                    "open_app" -> commandExecutor.openApp(updatedParams.optString("app_name"))
                    "send_message" -> {
                        val msg = updatedParams.optString("message").ifEmpty { stepResults.values.lastOrNull() ?: "" }
                        commandExecutor.sendMessage(updatedParams.optString("contact"), msg)
                    }
                    "set_alarm" -> commandExecutor.setAlarm(updatedParams.optInt("hour"), updatedParams.optInt("minute"))
                    "call_contact" -> commandExecutor.makeCall(updatedParams.optString("name"))
                    "web_search" -> webSearch.search(updatedParams.optString("query"))
                    "read_notifications" -> commandExecutor.readNotifications()
                    "take_screenshot" -> commandExecutor.takeScreenshot()
                    "toggle_setting" -> commandExecutor.toggleSetting(updatedParams.optString("setting"), updatedParams.optString("state"))
                    "create_reminder" -> commandExecutor.createReminder(updatedParams.optString("text"), updatedParams.optString("time"))
                    "click" -> if(automationService.performClick(updatedParams.optString("text"))) "Clicked ${updatedParams.optString("text")}" else "Could not find ${updatedParams.optString("text")}"
                    "type" -> if(automationService.inputText(updatedParams.optString("text"))) "Typed ${updatedParams.optString("text")}" else "Could not type"
                    "scroll" -> if(automationService.scroll(updatedParams.optString("direction"))) "Scrolled ${updatedParams.optString("direction")}" else "Could not scroll"
                    "general_answer" -> updatedParams.optString("text")
                    else -> "Unknown tool: ${step.tool}"
                }
                
                stepResults[step.step] = result
                finalSummary = result
                delay(800) 
            } catch (e: Exception) {
                if (step.critical) return "Stopped at step ${step.step}: ${e.message}"
            }
        }
        
        return finalSummary.ifEmpty { "Task completed." }
    }

    private fun injectContext(params: JSONObject, results: Map<Int, String>): JSONObject {
        val keys = params.keys()
        while(keys.hasNext()) {
            val key = keys.next()
            if (params.get(key) == "" && results.isNotEmpty()) {
                params.put(key, results.values.last())
            }
        }
        return params
    }
}
