package com.varun.akva.intelligence

import android.content.Context
import com.varun.akva.interaction.HapticEngine

class AKVAActionFeedback(private val context: Context, private val hapticEngine: HapticEngine) {

    fun onActionStart(actionName: String) {
        hapticEngine.playTick()
        // Could also trigger a bubble animation here
    }

    fun onActionSuccess() {
        hapticEngine.playSuccess()
    }

    fun onActionFailure() {
        hapticEngine.playError()
    }
}
