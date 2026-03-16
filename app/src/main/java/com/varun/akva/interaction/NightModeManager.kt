package com.varun.akva.interaction

import android.content.Context
import com.varun.akva.intelligence.ContextDetector

class NightModeManager(private val context: Context) {
    private val contextDetector = ContextDetector(context)

    fun isNightMode(): Boolean = contextDetector.isNightTime()
    fun getVolume(): Float = if (isNightMode()) 0.35f else 1.0f
    fun shouldWhisper(): Boolean { val h = contextDetector.getHourOfDay(); return h >= 23 || h < 6 }
    fun getScreenMood(): ScreenMoodEngine.Mood = ScreenMoodEngine.Mood.entries.first().let {
        val h = contextDetector.getHourOfDay()
        when (h) {
            in 5..11 -> ScreenMoodEngine.Mood.MORNING_GOLD
            in 12..17 -> ScreenMoodEngine.Mood.AFTERNOON_CLEAR
            in 18..21 -> ScreenMoodEngine.Mood.EVENING_COOL
            else -> ScreenMoodEngine.Mood.NIGHT_AMBER
        }
    }
}
