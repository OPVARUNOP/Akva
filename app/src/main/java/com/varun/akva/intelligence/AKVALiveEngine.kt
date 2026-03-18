package com.varun.akva.intelligence

import android.content.Context
import com.varun.akva.actions.*
import com.varun.akva.data.*
import com.varun.akva.interaction.*
import kotlinx.coroutines.*

class AKVALiveEngine(
    private val ctx: Context, private val r: AkvaRepository, private val s: SettingsManager,
    private val v: VoiceEngine, private val h: HapticEngine, private val m: AKVAMemoryEngine,
    private val g: GeminiEngine, private val p: CommandParser,
    private val al: AppLauncher, private val ms: MessageSender, private val rm: ReminderManager, 
    private val sc: SystemController, private val ws: AKVAWebSearch
) {
    private val scope = CoroutineScope(Dispatchers.Main)

    fun onUserSpeech(u: String) {
        h.playTick()
        scope.launch {
            val pr = r.getUserProfile()
            val intent = p.parse(u, pr)
            val resAction = handle(intent)
            val brain = g.getResponse(u, pr, app = "Action: ${intent.action}. Result: $resAction")
            v.speak(brain)
            m.processConversation(u, brain)
        }
    }

    private fun handle(i: ActionIntent) = when(i.action) {
        "OPEN_APP" -> if(al.launchApp(i.target)) "Success" else "Failed"
        else -> "None"
    }
    
    fun proactiveSpeak(t: String) { if(s.isProactiveEnabled) v.speak(t) }
}
