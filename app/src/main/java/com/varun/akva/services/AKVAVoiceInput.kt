package com.varun.akva.services

import android.content.Context
import android.content.Intent
import android.media.AudioManager
import android.media.ToneGenerator
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.util.Log
import com.varun.akva.data.SettingsManager
import com.varun.akva.intelligence.*
import com.varun.akva.interaction.*
import kotlinx.coroutines.*
import java.util.Locale

class AKVAVoiceInput(private val context: Context) {

    private val settingsManager = SettingsManager(context)
    private var conversationService: AKVAConversationService? = null
    private var hapticEngine = HapticEngine(context)
    
    private var speechRecognizer: SpeechRecognizer? = null
    private val handler = Handler(Looper.getMainLooper())
    private val scope = CoroutineScope(Dispatchers.Main + SupervisorJob())
    private var isListening = false
    private var isWaitingForCommand = false
    private var isDestroyed = false
    private val wakeWords = listOf("hey akva", "akva", "hey aqua", "aqua")

    fun init(service: AKVAConversationService) { 
        conversationService = service 
    }

    fun startContinuousListening() {
        if (!settingsManager.wakeWordEnabled) return
        if (!SpeechRecognizer.isRecognitionAvailable(context)) return
        isDestroyed = false
        handler.post { startListening() }
    }

    private fun startListening() {
        if (isListening || isDestroyed) return
        
        try {
            speechRecognizer?.destroy()
            speechRecognizer = SpeechRecognizer.createSpeechRecognizer(context)

            val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
                putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
                putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault().toLanguageTag())
                putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 1)
                putExtra(RecognizerIntent.EXTRA_PARTIAL_RESULTS, true)
            }

            speechRecognizer?.setRecognitionListener(object : RecognitionListener {
                override fun onReadyForSpeech(p: Bundle?) { isListening = true }
                override fun onBeginningOfSpeech() {}
                override fun onRmsChanged(rms: Float) {}
                override fun onBufferReceived(buf: ByteArray?) {}
                override fun onEndOfSpeech() { isListening = false }

                override fun onError(error: Int) {
                    isListening = false
                    if (isDestroyed) return
                    handler.postDelayed({ if (!isDestroyed && settingsManager.wakeWordEnabled) startListening() }, 1000)
                }

                override fun onResults(results: Bundle?) {
                    isListening = false
                    val text = results?.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)?.firstOrNull()?.lowercase() ?: ""

                    if (isWaitingForCommand) {
                        isWaitingForCommand = false
                        if (text.isNotBlank()) {
                            processCommand(text)
                        } else {
                            restartListening(500)
                        }
                    } else if (wakeWords.any { text.contains(it) }) {
                        onWakeWordDetected(text)
                    } else {
                        restartListening(500)
                    }
                }

                override fun onPartialResults(partial: Bundle?) {
                    val text = partial?.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)?.firstOrNull()?.lowercase() ?: ""
                    if (!isWaitingForCommand && wakeWords.any { text.contains(it) }) {
                        onWakeWordDetected(text)
                    }
                }

                override fun onEvent(type: Int, params: Bundle?) {}
            })

            speechRecognizer?.startListening(intent)
        } catch (e: Exception) {
            Log.e(TAG, "Listen error: ${e.message}")
            isListening = false
            restartListening(2000)
        }
    }

    private fun onWakeWordDetected(fullText: String) {
        try {
            val tg = ToneGenerator(AudioManager.STREAM_NOTIFICATION, 50)
            tg.startTone(ToneGenerator.TONE_PROP_BEEP, 150)
            handler.postDelayed({ tg.release() }, 200)
            hapticEngine.playTick()

            var commandText = fullText
            wakeWords.forEach { commandText = commandText.replace(it, "") }
            commandText = commandText.trim()

            if (commandText.length > 3) {
                processCommand(commandText)
            } else {
                isWaitingForCommand = true
                speechRecognizer?.destroy()
                handler.postDelayed({ startListening() }, 300)
            }
        } catch (e: Exception) {
            restartListening(1000)
        }
    }

    private fun processCommand(userSpeech: String) {
        scope.launch {
            try {
                conversationService?.onUserSpeaks(userSpeech)
                handler.postDelayed({ restartListening(500) }, 5000)
            } catch (e: Exception) {
                restartListening(1000)
            }
        }
    }

    private fun restartListening(delayMs: Long) {
        if (isDestroyed) return
        handler.postDelayed({ if (!isDestroyed) startListening() }, delayMs)
    }

    fun stopListening() {
        isDestroyed = true
        handler.removeCallbacksAndMessages(null)
        try {
            speechRecognizer?.destroy()
        } catch (_: Exception) {}
        scope.cancel()
    }

    companion object { private const val TAG = "AKVAVoiceInput" }
}
