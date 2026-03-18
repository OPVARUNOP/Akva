package com.varun.akva.interaction

import android.content.Context
import android.os.Bundle
import android.speech.tts.TextToSpeech
import java.util.Locale

data class SpeakRequest(val text: String, val pitch: Float, val rate: Float)

class VoiceEngine(private val context: Context) : TextToSpeech.OnInitListener {
    private var tts: TextToSpeech? = null
    private var isReady = false
    private val queue = mutableListOf<SpeakRequest>()

    init { tts = TextToSpeech(context, this) }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            tts?.language = Locale.US
            isReady = true
            synchronized(queue) {
                queue.forEach { speakNow(it.text, it.pitch, it.rate) }
                queue.clear()
            }
        }
    }

    fun speak(text: String, pitch: Float = 1.0f, rate: Float = 0.92f) {
        if (!isReady) {
            synchronized(queue) { queue.add(SpeakRequest(text, pitch, rate)) }
            return
        }
        speakNow(text, pitch, rate)
    }

    private fun speakNow(text: String, pitch: Float, rate: Float) {
        tts?.setPitch(pitch)
        tts?.setSpeechRate(rate)
        tts?.speak(text, TextToSpeech.QUEUE_ADD, null, "akva_${System.currentTimeMillis()}")
    }

    fun shutdown() { tts?.stop(); tts?.shutdown() }
}
