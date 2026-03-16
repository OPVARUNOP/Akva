package com.varun.akva.interaction

import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager
import android.util.Log
import com.varun.akva.data.SettingsManager

class HapticEngine(private val context: Context) {

    private val settingsManager = SettingsManager(context)

    private val vibrator: Vibrator by lazy {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            (context.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager).defaultVibrator
        } else {
            @Suppress("DEPRECATION")
            context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        }
    }

    fun playHeartbeatPulse()  = vibratePattern(longArrayOf(0, 60, 80, 60))
    fun playStressPulse()     = vibratePattern(longArrayOf(0, 400))
    fun playMorningPulse()    = vibratePattern(longArrayOf(0, 100, 200, 100))
    fun playAchievement()     = vibratePattern(longArrayOf(0, 50, 50, 50))
    fun playGoodnight()       = vibratePattern(longArrayOf(0, 600))
    fun playAlert()           = vibratePattern(longArrayOf(0, 100, 50, 100))

    private fun vibratePattern(pattern: LongArray) {
        if (!settingsManager.hapticEnabled) return
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val amplitudes = IntArray(pattern.size) { if (it % 2 == 0) 0 else 120 }
                vibrator.vibrate(VibrationEffect.createWaveform(pattern, amplitudes, -1))
            } else {
                @Suppress("DEPRECATION")
                vibrator.vibrate(pattern, -1)
            }
        } catch (e: Exception) {
            Log.e("HapticEngine", "Vibrate error: ${e.message}")
        }
    }
}
