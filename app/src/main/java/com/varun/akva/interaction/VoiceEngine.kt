package com.varun.akva.interaction

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.speech.tts.TextToSpeech
import android.speech.tts.UtteranceProgressListener
import android.util.Log
import java.util.Calendar
import java.util.Locale
import java.util.UUID

data class VoiceConfig(val pitch: Float, val speechRate: Float)

class VoiceEngine(private val context: Context) {

    private var tts: TextToSpeech? = null
    private var isInitialized = false
    private val handler = Handler(Looper.getMainLooper())
    
    @Volatile
    var isSpeakingConversation = false
        private set

    init {
        tts = TextToSpeech(context) { status ->
            if (status == TextToSpeech.SUCCESS) {
                val localeResult = tts?.setLanguage(Locale.getDefault())
                if (localeResult == TextToSpeech.LANG_MISSING_DATA || localeResult == TextToSpeech.LANG_NOT_SUPPORTED) {
                    tts?.setLanguage(Locale.US)
                }
                isInitialized = true

                tts?.setOnUtteranceProgressListener(object : UtteranceProgressListener() {
                    override fun onStart(utteranceId: String?) {}
                    override fun onDone(utteranceId: String?) {
                        if (utteranceId?.startsWith("conv_") == true) {
                            isSpeakingConversation = false
                        }
                    }
                    @Deprecated("Deprecated in Java")
                    override fun onError(utteranceId: String?) {
                        if (utteranceId?.startsWith("conv_") == true) {
                            isSpeakingConversation = false
                        }
                    }
                })
                Log.d(TAG, "TTS initialized")
            } else {
                Log.e(TAG, "TTS init failed: $status")
            }
        }
    }

    private fun getVolume(): Float {
        val hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
        return if (hour >= 22 || hour <= 7) 0.4f else 1.0f
    }

    private fun getConfigForApp(packageName: String): VoiceConfig {
        return when {
            packageName.contains("gmail") -> VoiceConfig(0.96f, 0.90f)
            packageName.contains("whatsapp") -> VoiceConfig(1.02f, 0.92f)
            packageName.contains("instagram") -> VoiceConfig(0.98f, 0.88f)
            packageName.contains("youtube") -> VoiceConfig(1.05f, 0.95f)
            else -> VoiceConfig(1.0f, 0.88f)
        }
    }

    // Observer Mode uses QUEUE_ADD, won't interrupt Conversation
    fun speakProactive(text: String, packageName: String) {
        if (text.isBlank() || !isInitialized) return
        if (isSpeakingConversation) return

        try {
            val config = getConfigForApp(packageName.lowercase())
            tts?.setPitch(config.pitch)
            tts?.setSpeechRate(config.speechRate)

            val params = Bundle()
            params.putFloat(TextToSpeech.Engine.KEY_PARAM_VOLUME, getVolume())

            val utteranceId = "obs_${UUID.randomUUID()}"
            tts?.speak(text, TextToSpeech.QUEUE_ADD, params, utteranceId)
        } catch (e: Exception) {
            Log.e(TAG, "Speak error: ${e.message}")
        }
    }

    // Conversation Mode uses QUEUE_FLUSH to interrupt everything else
    fun speakConversation(text: String) {
        if (text.isBlank() || !isInitialized) return

        try {
            stop()
            isSpeakingConversation = true
            
            // Conversation uses default grounding voice config
            tts?.setPitch(1.0f)
            tts?.setSpeechRate(0.88f)

            val params = Bundle()
            params.putFloat(TextToSpeech.Engine.KEY_PARAM_VOLUME, getVolume())

            val utteranceId = "conv_${UUID.randomUUID()}"
            tts?.speak(text, TextToSpeech.QUEUE_FLUSH, params, utteranceId)
        } catch (e: Exception) {
            isSpeakingConversation = false
            Log.e(TAG, "SpeakConversation error: ${e.message}")
        }
    }

    fun stop() {
        try {
            isSpeakingConversation = false
            tts?.stop()
        } catch (_: Exception) {}
    }

    fun shutdown() {
        try {
            tts?.stop()
            tts?.shutdown()
            tts = null
            isInitialized = false
            isSpeakingConversation = false
        } catch (_: Exception) {}
    }

    companion object { private const val TAG = "VoiceEngine" }
}
