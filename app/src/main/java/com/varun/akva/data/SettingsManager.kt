package com.varun.akva.data

import android.content.Context
import android.content.SharedPreferences

class SettingsManager(context: Context) {

    private val prefs: SharedPreferences = context.getSharedPreferences("akva_settings", Context.MODE_PRIVATE)

    var masterVoiceEnabled: Boolean
        get() = prefs.getBoolean("master_voice_enabled", true)
        set(value) = prefs.edit().putBoolean("master_voice_enabled", value).apply()

    var voicePersonality: String
        get() = prefs.getString("voice_personality", "Warm") ?: "Warm"
        set(value) = prefs.edit().putString("voice_personality", value).apply()

    var hapticEnabled: Boolean
        get() = prefs.getBoolean("haptic_enabled", true)
        set(value) = prefs.edit().putBoolean("haptic_enabled", value).apply()

    var screenMoodEnabled: Boolean
        get() = prefs.getBoolean("screen_mood_enabled", true)
        set(value) = prefs.edit().putBoolean("screen_mood_enabled", value).apply()

    var wakeWordEnabled: Boolean
        get() = prefs.getBoolean("wake_word_enabled", true)
        set(value) = prefs.edit().putBoolean("wake_word_enabled", value).apply()

    var silentHourStart: Int
        get() = prefs.getInt("silent_hour_start", 23)
        set(value) = prefs.edit().putInt("silent_hour_start", value).apply()

    var silentHourEnd: Int
        get() = prefs.getInt("silent_hour_end", 7)
        set(value) = prefs.edit().putInt("silent_hour_end", value).apply()

    var backendUrl: String
        get() = prefs.getString("backend_url", "https://web-production-d4aa5.up.railway.app") ?: "https://web-production-d4aa5.up.railway.app"
        set(value) = prefs.edit().putString("backend_url", value).apply()

    var lastMorningBriefingDate: String
        get() = prefs.getString("last_morning_briefing_date", "") ?: ""
        set(value) = prefs.edit().putString("last_morning_briefing_date", value).apply()

    var lastWeeklyStoryDate: String
        get() = prefs.getString("last_weekly_story_date", "") ?: ""
        set(value) = prefs.edit().putString("last_weekly_story_date", value).apply()

    var isFirstLaunch: Boolean
        get() = prefs.getBoolean("first_launch", true)
        set(value) = prefs.edit().putBoolean("first_launch", value).apply()

    var morningBriefingEnabled: Boolean
        get() = prefs.getBoolean("morning_briefing_enabled", true)
        set(value) = prefs.edit().putBoolean("morning_briefing_enabled", value).apply()

    var weeklyStoryEnabled: Boolean
        get() = prefs.getBoolean("weekly_story_enabled", true)
        set(value) = prefs.edit().putBoolean("weekly_story_enabled", value).apply()

    var settingsGuideEnabled: Boolean
        get() = prefs.getBoolean("settings_guide_enabled", true)
        set(value) = prefs.edit().putBoolean("settings_guide_enabled", value).apply()

    fun isSilentHour(hour: Int): Boolean {
        return if (silentHourStart > silentHourEnd) {
            hour >= silentHourStart || hour < silentHourEnd
        } else {
            hour in silentHourStart until silentHourEnd
        }
    }

    fun clearLearnedData() {
        prefs.edit()
            .remove("last_morning_briefing_date")
            .remove("last_weekly_story_date")
            .apply()
    }
}
