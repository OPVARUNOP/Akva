package com.varun.akva.intelligence

import android.content.Context
import com.varun.akva.interaction.VoiceEngine
import java.util.Calendar

class AKVAResponseEngine(private val context: Context, private val voiceEngine: VoiceEngine) {

    fun speak(text: String, stressScore: Int = 0) {
        val hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
        val isNight = hour >= 22 || hour <= 6
        
        var pitch = 1.0f
        var rate = 1.0f
        
        if (isNight) {
            pitch = 0.8f
            rate = 0.85f // Calmer, slower at night
        }
        
        if (stressScore >= 7) {
            pitch = 0.9f
            rate = 0.8f // Reassuringly slow when user is stressed
        }
        
        voiceEngine.setParams(pitch, rate)
        voiceEngine.speak(text)
    }
}
