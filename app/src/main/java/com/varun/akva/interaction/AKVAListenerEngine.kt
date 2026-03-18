package com.varun.akva.interaction

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer

class AKVAListenerEngine(private val ctx: Context, private val onRes: (String) -> Unit) : RecognitionListener {
    private var sr = SpeechRecognizer.createSpeechRecognizer(ctx).apply { setRecognitionListener(this@AKVAListenerEngine) }
    private val i = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply { putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM) }

    fun startListening() { try { sr.startListening(i) } catch(_: Exception) { startListening() } }
    fun stopListening() { sr.stopListening() }

    override fun onResults(r: Bundle?) { val m = r?.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION); if(!m.isNullOrEmpty()) onRes(m[0]); startListening() }
    override fun onError(e: Int) { startListening() }
    override fun onReadyForSpeech(p: Bundle?) {}
    override fun onBeginningOfSpeech() {}
    override fun onRmsChanged(r: Float) {}
    override fun onBufferReceived(b: ByteArray?) {}
    override fun onEndOfSpeech() {}
    override fun onPartialResults(p: Bundle?) {}
    override fun onEvent(e: Int, p: Bundle?) {}
}
