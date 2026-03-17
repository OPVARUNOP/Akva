package com.varun.akva.interaction

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.speech.tts.TextToSpeech
import android.speech.tts.UtteranceProgressListener
import android.util.Log
import com.varun.akva.intelligence.VoiceConfig
import java.util.Locale
import java.util.UUID

class VoiceEngine(private val context: Context) {

    private var tts: TextToSpeech? = null
    private var isInitialized = false
    private val handler = Handler(Looper.getMainLooper())
    private var onSpeechStartCallback: (() -> Unit)? = null
    private var onSpeechDoneCallback: (() -> Unit)? = null
    private val pendingQueue = mutableListOf<PendingSpeak>()

    // Track whether we're speaking a conversation response (don't interrupt these)
    @Volatile
    var isSpeakingConversation = false
        private set

    private data class PendingSpeak(val text: String, val config: VoiceConfig, val isWhisper: Boolean)

    init {
        tts = TextToSpeech(context) { status ->
            if (status == TextToSpeech.SUCCESS) {
                // Try device locale first, fall back to US English
                val localeResult = tts?.setLanguage(Locale.getDefault())
                if (localeResult == TextToSpeech.LANG_MISSING_DATA || localeResult == TextToSpeech.LANG_NOT_SUPPORTED) {
                    tts?.setLanguage(Locale.US)
                }
                isInitialized = true

                tts?.setOnUtteranceProgressListener(object : UtteranceProgressListener() {
                    override fun onStart(utteranceId: String?) {
                        onSpeechStartCallback?.invoke()
                    }
                    override fun onDone(utteranceId: String?) {
                        if (utteranceId?.startsWith("conv_") == true) {
                            isSpeakingConversation = false
                        }
                        onSpeechDoneCallback?.invoke()
                    }
                    @Deprecated("Deprecated in Java")
                    override fun onError(utteranceId: String?) {
                        if (utteranceId?.startsWith("conv_") == true) {
                            isSpeakingConversation = false
                        }
                        onSpeechDoneCallback?.invoke()
                    }
                })

                // Process any queued speech
                processPendingQueue()
                Log.d(TAG, "TTS initialized")
            } else {
                Log.e(TAG, "TTS init failed: $status")
            }
        }
    }

    fun speak(text: String, config: VoiceConfig, isWhisper: Boolean = false) {
        if (text.isBlank()) return

        // Don't interrupt conversation responses with proactive speech
        if (isSpeakingConversation) return

        if (!isInitialized) {
            pendingQueue.add(PendingSpeak(text, config, isWhisper))
            handler.postDelayed({ processPendingQueue() }, 1000)
            return
        }

        try {
            tts?.setPitch(config.pitch)
            tts?.setSpeechRate(config.speechRate)

            val params = Bundle()
            val volume = if (isWhisper) 0.4f else 1.0f
            params.putFloat(TextToSpeech.Engine.KEY_PARAM_VOLUME, volume)

            val utteranceId = UUID.randomUUID().toString()
            // QUEUE_FLUSH — new speech always interrupts old proactive speech
            tts?.speak(text, TextToSpeech.QUEUE_FLUSH, params, utteranceId)
        } catch (e: Exception) {
            Log.e(TAG, "Speak error: ${e.message}")
        }
    }

    fun speakImmediate(text: String, config: VoiceConfig, isWhisper: Boolean = false) {
        if (text.isBlank()) return
        if (!isInitialized) {
            pendingQueue.clear()
            pendingQueue.add(PendingSpeak(text, config, isWhisper))
            return
        }

        try {
            stop()
            isSpeakingConversation = true
            tts?.setPitch(config.pitch)
            tts?.setSpeechRate(config.speechRate)

            val params = Bundle()
            params.putFloat(TextToSpeech.Engine.KEY_PARAM_VOLUME, if (isWhisper) 0.4f else 1.0f)

            val utteranceId = "conv_${UUID.randomUUID()}"
            tts?.speak(text, TextToSpeech.QUEUE_FLUSH, params, utteranceId)
        } catch (e: Exception) {
            isSpeakingConversation = false
            Log.e(TAG, "SpeakImmediate error: ${e.message}")
        }
    }

    fun speakConversation(lines: List<String>, config: VoiceConfig, delayMs: Long = 1500L, isWhisper: Boolean = false) {
        if (!isInitialized || lines.isEmpty()) return

        try {
            tts?.setPitch(config.pitch)
            tts?.setSpeechRate(config.speechRate)

            val params = Bundle()
            params.putFloat(TextToSpeech.Engine.KEY_PARAM_VOLUME, if (isWhisper) 0.4f else 1.0f)

            lines.forEachIndexed { index, line ->
                if (index > 0) {
                    tts?.playSilentUtterance(delayMs, TextToSpeech.QUEUE_ADD, "silence_$index")
                }
                tts?.speak(line.trim(), TextToSpeech.QUEUE_ADD, params, "line_$index")
            }
        } catch (e: Exception) {
            Log.e(TAG, "Conversation error: ${e.message}")
        }
    }

    private fun processPendingQueue() {
        if (!isInitialized || pendingQueue.isEmpty()) return
        val items = pendingQueue.toList()
        pendingQueue.clear()
        items.forEach { speak(it.text, it.config, it.isWhisper) }
    }

    fun stop() {
        try {
            isSpeakingConversation = false
            tts?.stop()
        } catch (_: Exception) {}
    }

    fun isSpeaking(): Boolean = try { tts?.isSpeaking == true } catch (_: Exception) { false }

    fun setOnSpeechStart(callback: () -> Unit) { onSpeechStartCallback = callback }
    fun setOnSpeechDone(callback: () -> Unit) { onSpeechDoneCallback = callback }

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
