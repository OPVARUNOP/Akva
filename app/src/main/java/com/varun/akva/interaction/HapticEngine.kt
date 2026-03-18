package com.varun.akva.interaction

import android.content.Context
import android.os.VibrationEffect
import android.os.Vibrator

class HapticEngine(private val ctx: Context) {
    private val v = ctx.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
    fun playTick() { v.vibrate(VibrationEffect.createOneShot(50, VibrationEffect.DEFAULT_AMPLITUDE)) }
}
