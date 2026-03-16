package com.varun.akva.intelligence

data class VoiceConfig(val pitch: Float, val speechRate: Float, val style: String = "default")

class PersonalityEngine {

    private val appVoices = mapOf(
        "com.google.android.gm" to VoiceConfig(0.90f, 1.10f, "professional"),
        "com.whatsapp" to VoiceConfig(1.05f, 0.95f, "friendly"),
        "com.instagram.android" to VoiceConfig(1.10f, 1.00f, "casual"),
        "com.google.android.youtube" to VoiceConfig(1.20f, 1.10f, "energetic"),
        "com.google.android.apps.maps" to VoiceConfig(0.85f, 0.90f, "calm"),
        "com.spotify.music" to VoiceConfig(0.95f, 0.85f, "smooth"),
        "com.google.android.calendar" to VoiceConfig(1.05f, 0.90f, "focused"),
        "com.android.camera" to VoiceConfig(1.30f, 1.10f, "excited"),
        "com.google.android.GoogleCamera" to VoiceConfig(1.30f, 1.10f, "excited"),
        "com.sec.android.app.camera" to VoiceConfig(1.30f, 1.10f, "excited"),
        "com.netflix.mediaclient" to VoiceConfig(0.95f, 0.90f, "relaxed"),
        "com.twitter.android" to VoiceConfig(1.05f, 1.05f, "quick"),
        "com.linkedin.android" to VoiceConfig(0.90f, 0.95f, "professional"),
        "com.android.chrome" to VoiceConfig(1.00f, 1.00f, "neutral"),
        "com.zomato.ordering" to VoiceConfig(1.10f, 1.00f, "casual"),
        "in.swiggy.android" to VoiceConfig(1.10f, 1.00f, "casual"),
        "com.reddit.frontpage" to VoiceConfig(1.05f, 0.95f, "curious"),
        "com.discord" to VoiceConfig(1.10f, 1.00f, "playful"),
        "com.snapchat.android" to VoiceConfig(1.15f, 1.05f, "fun")
    )

    private val defaultVoice = VoiceConfig(1.00f, 0.95f, "default")
    private val nightVoice = VoiceConfig(0.85f, 0.80f, "whisper")
    private val stressVoice = VoiceConfig(0.90f, 0.75f, "calm")
    private val morningVoice = VoiceConfig(1.05f, 0.90f, "warm")

    fun getVoiceConfig(packageName: String, isNight: Boolean, isStressed: Boolean): VoiceConfig {
        if (isNight) return nightVoice
        if (isStressed) return stressVoice
        return appVoices[packageName] ?: defaultVoice
    }

    fun getDefaultVoice() = defaultVoice
    fun getNightVoice() = nightVoice
    fun getStressVoice() = stressVoice
    fun getMorningVoice() = morningVoice

    fun getVolume(isNight: Boolean): Float = if (isNight) 0.35f else 1.0f
}
