package com.varun.akva.intelligence

data class VoiceConfig(val pitch: Float, val speechRate: Float, val style: String = "default")

class PersonalityEngine {

    // Subtle per-app voice differences — NOT aggressive pitch changes
    private val appVoices = mapOf(
        "com.google.android.gm" to VoiceConfig(0.95f, 0.92f, "professional"),
        "com.whatsapp" to VoiceConfig(1.02f, 0.90f, "friendly"),
        "com.instagram.android" to VoiceConfig(1.05f, 0.92f, "casual"),
        "com.google.android.youtube" to VoiceConfig(1.05f, 0.95f, "energetic")
    )

    // Natural default voice
    private val defaultVoice = VoiceConfig(1.00f, 0.90f, "default")
    private val nightVoice = VoiceConfig(0.90f, 0.85f, "whisper")
    private val stressVoice = VoiceConfig(0.95f, 0.80f, "calm")
    private val morningVoice = VoiceConfig(1.02f, 0.90f, "warm")

    fun getVoiceConfig(packageName: String, isNight: Boolean, isStressed: Boolean): VoiceConfig {
        if (isNight) return nightVoice
        if (isStressed) return stressVoice
        return appVoices[packageName] ?: defaultVoice
    }

    fun getDefaultVoice() = defaultVoice
    fun getNightVoice() = nightVoice
    fun getStressVoice() = stressVoice
    fun getMorningVoice() = morningVoice

    fun getVolume(isNight: Boolean): Float = if (isNight) 0.4f else 1.0f
}
