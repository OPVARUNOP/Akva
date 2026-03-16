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

class AKVAVoiceInput(private val context: Context) {

    private val settingsManager = SettingsManager(context)
    private val contextDetector = ContextDetector(context)
    private val geminiEngine = GeminiEngine(context)
    private val personalityEngine = PersonalityEngine()
    private val nightModeManager = NightModeManager(context)
    private val phoneSystemMonitor = PhoneSystemMonitor(context)
    private val hapticEngine = HapticEngine(context)
    private var voiceEngine: VoiceEngine? = null
    private var speechRecognizer: SpeechRecognizer? = null
    private val handler = Handler(Looper.getMainLooper())
    private val scope = CoroutineScope(Dispatchers.Main + SupervisorJob())
    private var isListening = false
    private var isWaitingForCommand = false
    private val wakeWords = listOf("hey akva", "akva", "hey aqua", "aqua")

    fun init(voice: VoiceEngine) { voiceEngine = voice }

    fun startContinuousListening() {
        if (!settingsManager.wakeWordEnabled) return
        if (!SpeechRecognizer.isRecognitionAvailable(context)) return
        handler.post { startListening() }
    }

    private fun startListening() {
        if (isListening) return
        if (phoneSystemMonitor.isInActiveCall()) return

        try {
            speechRecognizer?.destroy()
            speechRecognizer = SpeechRecognizer.createSpeechRecognizer(context)

            val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
                putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
                putExtra(RecognizerIntent.EXTRA_LANGUAGE, "en-US")
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
                    if (error != SpeechRecognizer.ERROR_INSUFFICIENT_PERMISSIONS) {
                        handler.postDelayed({ if (settingsManager.wakeWordEnabled) startListening() }, 1000)
                    }
                }

                override fun onResults(results: Bundle?) {
                    isListening = false
                    val text = results?.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)?.firstOrNull()?.lowercase() ?: ""

                    if (isWaitingForCommand) {
                        isWaitingForCommand = false
                        processCommand(text)
                    } else if (wakeWords.any { text.contains(it) }) {
                        onWakeWordDetected(text)
                    } else {
                        handler.postDelayed({ startListening() }, 500)
                    }
                }

                override fun onPartialResults(partial: Bundle?) {
                    val text = partial?.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)?.firstOrNull()?.lowercase() ?: ""
                    if (!isWaitingForCommand && wakeWords.any { text.contains(it) }) onWakeWordDetected(text)
                }

                override fun onEvent(type: Int, params: Bundle?) {}
            })

            speechRecognizer?.startListening(intent)
        } catch (e: Exception) {
            Log.e(TAG, "Listen error: ${e.message}")
            isListening = false
        }
    }

    private fun onWakeWordDetected(fullText: String) {
        try {
            val tg = ToneGenerator(AudioManager.STREAM_NOTIFICATION, 50)
            tg.startTone(ToneGenerator.TONE_PROP_ACK, 150)
            handler.postDelayed({ tg.release() }, 200)
            hapticEngine.playAchievement()

            val command = wakeWords.fold(fullText) { acc, w -> acc.replace(w, "").trim() }
            if (command.length > 3) {
                processCommand(command)
            } else {
                isWaitingForCommand = true
                speechRecognizer?.destroy()
                handler.postDelayed({ startListening() }, 300)
                handler.postDelayed({ if (isWaitingForCommand) { isWaitingForCommand = false; startListening() } }, 8000)
            }
        } catch (e: Exception) {
            isWaitingForCommand = false
            handler.postDelayed({ startListening() }, 1000)
        }
    }

    private fun processCommand(command: String) {
        scope.launch {
            try {
                val ctx = AkvaContext(
                    appName = "Conversation",
                    packageName = "com.varun.akva",
                    previousApp = "",
                    timeOfDay = contextDetector.getTimeOfDay(),
                    hourOfDay = contextDetector.getHourOfDay(),
                    dayOfWeek = contextDetector.getDayOfWeek(),
                    unreadCount = AKVANotificationListener.getTotalUnread(),
                    senderNames = emptyList(),
                    timesOpenedToday = 0,
                    batteryPercent = phoneSystemMonitor.getBatteryPercent(),
                    isCharging = phoneSystemMonitor.isCharging(),
                    networkType = phoneSystemMonitor.getNetworkType(),
                    stressScore = 0,
                    userPattern = "",
                    deviceId = ""
                )
                val response = withContext(Dispatchers.IO) { geminiEngine.getConversationResponse(command, ctx) }
                voiceEngine?.speakImmediate(response, personalityEngine.getDefaultVoice(), nightModeManager.isNightMode())
                handler.postDelayed({ startListening() }, 3000)
            } catch (e: Exception) {
                handler.postDelayed({ startListening() }, 1000)
            }
        }
    }

    fun stopListening() {
        isListening = false; isWaitingForCommand = false
        try { speechRecognizer?.cancel(); speechRecognizer?.destroy(); speechRecognizer = null } catch (_: Exception) {}
    }

    companion object { private const val TAG = "AKVAVoiceInput" }
}
