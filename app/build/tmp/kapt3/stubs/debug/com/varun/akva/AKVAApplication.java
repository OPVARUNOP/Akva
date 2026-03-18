package com.varun.akva;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u00109\u001a\u00020:H\u0016R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\u00020\u0010X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001a\u0010\u0015\u001a\u00020\u0016X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u001a\u0010\u001b\u001a\u00020\u001cX\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R\u001a\u0010!\u001a\u00020\"X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&R\u001a\u0010\'\u001a\u00020(X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b)\u0010*\"\u0004\b+\u0010,R\u001a\u0010-\u001a\u00020.X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b/\u00100\"\u0004\b1\u00102R\u001a\u00103\u001a\u000204X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b5\u00106\"\u0004\b7\u00108\u00a8\u0006;"}, d2 = {"Lcom/varun/akva/AKVAApplication;", "Landroid/app/Application;", "()V", "database", "Lcom/varun/akva/data/AkvaDatabase;", "getDatabase", "()Lcom/varun/akva/data/AkvaDatabase;", "setDatabase", "(Lcom/varun/akva/data/AkvaDatabase;)V", "geminiEngine", "Lcom/varun/akva/intelligence/GeminiEngine;", "getGeminiEngine", "()Lcom/varun/akva/intelligence/GeminiEngine;", "setGeminiEngine", "(Lcom/varun/akva/intelligence/GeminiEngine;)V", "hapticEngine", "Lcom/varun/akva/interaction/HapticEngine;", "getHapticEngine", "()Lcom/varun/akva/interaction/HapticEngine;", "setHapticEngine", "(Lcom/varun/akva/interaction/HapticEngine;)V", "listenerEngine", "Lcom/varun/akva/interaction/AKVAListenerEngine;", "getListenerEngine", "()Lcom/varun/akva/interaction/AKVAListenerEngine;", "setListenerEngine", "(Lcom/varun/akva/interaction/AKVAListenerEngine;)V", "liveEngine", "Lcom/varun/akva/intelligence/AKVALiveEngine;", "getLiveEngine", "()Lcom/varun/akva/intelligence/AKVALiveEngine;", "setLiveEngine", "(Lcom/varun/akva/intelligence/AKVALiveEngine;)V", "memoryEngine", "Lcom/varun/akva/intelligence/AKVAMemoryEngine;", "getMemoryEngine", "()Lcom/varun/akva/intelligence/AKVAMemoryEngine;", "setMemoryEngine", "(Lcom/varun/akva/intelligence/AKVAMemoryEngine;)V", "repository", "Lcom/varun/akva/data/AkvaRepository;", "getRepository", "()Lcom/varun/akva/data/AkvaRepository;", "setRepository", "(Lcom/varun/akva/data/AkvaRepository;)V", "settings", "Lcom/varun/akva/data/SettingsManager;", "getSettings", "()Lcom/varun/akva/data/SettingsManager;", "setSettings", "(Lcom/varun/akva/data/SettingsManager;)V", "voiceEngine", "Lcom/varun/akva/interaction/VoiceEngine;", "getVoiceEngine", "()Lcom/varun/akva/interaction/VoiceEngine;", "setVoiceEngine", "(Lcom/varun/akva/interaction/VoiceEngine;)V", "onCreate", "", "app_debug"})
public final class AKVAApplication extends android.app.Application {
    public com.varun.akva.data.AkvaDatabase database;
    public com.varun.akva.data.AkvaRepository repository;
    public com.varun.akva.data.SettingsManager settings;
    public com.varun.akva.interaction.VoiceEngine voiceEngine;
    public com.varun.akva.interaction.HapticEngine hapticEngine;
    public com.varun.akva.intelligence.AKVAMemoryEngine memoryEngine;
    public com.varun.akva.intelligence.GeminiEngine geminiEngine;
    public com.varun.akva.intelligence.AKVALiveEngine liveEngine;
    public com.varun.akva.interaction.AKVAListenerEngine listenerEngine;
    
    public AKVAApplication() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.varun.akva.data.AkvaDatabase getDatabase() {
        return null;
    }
    
    public final void setDatabase(@org.jetbrains.annotations.NotNull()
    com.varun.akva.data.AkvaDatabase p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.varun.akva.data.AkvaRepository getRepository() {
        return null;
    }
    
    public final void setRepository(@org.jetbrains.annotations.NotNull()
    com.varun.akva.data.AkvaRepository p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.varun.akva.data.SettingsManager getSettings() {
        return null;
    }
    
    public final void setSettings(@org.jetbrains.annotations.NotNull()
    com.varun.akva.data.SettingsManager p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.varun.akva.interaction.VoiceEngine getVoiceEngine() {
        return null;
    }
    
    public final void setVoiceEngine(@org.jetbrains.annotations.NotNull()
    com.varun.akva.interaction.VoiceEngine p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.varun.akva.interaction.HapticEngine getHapticEngine() {
        return null;
    }
    
    public final void setHapticEngine(@org.jetbrains.annotations.NotNull()
    com.varun.akva.interaction.HapticEngine p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.varun.akva.intelligence.AKVAMemoryEngine getMemoryEngine() {
        return null;
    }
    
    public final void setMemoryEngine(@org.jetbrains.annotations.NotNull()
    com.varun.akva.intelligence.AKVAMemoryEngine p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.varun.akva.intelligence.GeminiEngine getGeminiEngine() {
        return null;
    }
    
    public final void setGeminiEngine(@org.jetbrains.annotations.NotNull()
    com.varun.akva.intelligence.GeminiEngine p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.varun.akva.intelligence.AKVALiveEngine getLiveEngine() {
        return null;
    }
    
    public final void setLiveEngine(@org.jetbrains.annotations.NotNull()
    com.varun.akva.intelligence.AKVALiveEngine p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.varun.akva.interaction.AKVAListenerEngine getListenerEngine() {
        return null;
    }
    
    public final void setListenerEngine(@org.jetbrains.annotations.NotNull()
    com.varun.akva.interaction.AKVAListenerEngine p0) {
    }
    
    @java.lang.Override()
    public void onCreate() {
    }
}