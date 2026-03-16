package com.varun.akva.intelligence;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0006\u0010\u000b\u001a\u00020\u0006J\u0006\u0010\f\u001a\u00020\u0006J\u0006\u0010\r\u001a\u00020\u0006J\u0006\u0010\u000e\u001a\u00020\u0006J\u001e\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u0010\u001a\u00020\u00052\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0012J\u000e\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0011\u001a\u00020\u0012R\u001a\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0016"}, d2 = {"Lcom/varun/akva/intelligence/PersonalityEngine;", "", "()V", "appVoices", "", "", "Lcom/varun/akva/intelligence/VoiceConfig;", "defaultVoice", "morningVoice", "nightVoice", "stressVoice", "getDefaultVoice", "getMorningVoice", "getNightVoice", "getStressVoice", "getVoiceConfig", "packageName", "isNight", "", "isStressed", "getVolume", "", "app_debug"})
public final class PersonalityEngine {
    @org.jetbrains.annotations.NotNull()
    private final java.util.Map<java.lang.String, com.varun.akva.intelligence.VoiceConfig> appVoices = null;
    @org.jetbrains.annotations.NotNull()
    private final com.varun.akva.intelligence.VoiceConfig defaultVoice = null;
    @org.jetbrains.annotations.NotNull()
    private final com.varun.akva.intelligence.VoiceConfig nightVoice = null;
    @org.jetbrains.annotations.NotNull()
    private final com.varun.akva.intelligence.VoiceConfig stressVoice = null;
    @org.jetbrains.annotations.NotNull()
    private final com.varun.akva.intelligence.VoiceConfig morningVoice = null;
    
    public PersonalityEngine() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.varun.akva.intelligence.VoiceConfig getVoiceConfig(@org.jetbrains.annotations.NotNull()
    java.lang.String packageName, boolean isNight, boolean isStressed) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.varun.akva.intelligence.VoiceConfig getDefaultVoice() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.varun.akva.intelligence.VoiceConfig getNightVoice() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.varun.akva.intelligence.VoiceConfig getStressVoice() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.varun.akva.intelligence.VoiceConfig getMorningVoice() {
        return null;
    }
    
    public final float getVolume(boolean isNight) {
        return 0.0F;
    }
}