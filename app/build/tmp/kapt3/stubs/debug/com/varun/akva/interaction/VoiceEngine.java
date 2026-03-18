package com.varun.akva.interaction;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u000b\u0018\u0000 \u001f2\u00020\u0001:\u0001\u001fB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0002J\b\u0010\u0012\u001a\u00020\u0013H\u0002J\u0016\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00132\u0006\u0010\u0017\u001a\u00020\u0013J\u0006\u0010\u0018\u001a\u00020\u0015J\u000e\u0010\u0019\u001a\u00020\u00152\u0006\u0010\u001a\u001a\u00020\u0011J\u000e\u0010\u001b\u001a\u00020\u00152\u0006\u0010\u001a\u001a\u00020\u0011J\u000e\u0010\u001c\u001a\u00020\u00152\u0006\u0010\u001a\u001a\u00020\u0011J\u0016\u0010\u001d\u001a\u00020\u00152\u0006\u0010\u001a\u001a\u00020\u00112\u0006\u0010\u0010\u001a\u00020\u0011J\u0006\u0010\u001e\u001a\u00020\u0015R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001e\u0010\n\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b@BX\u0086\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006 "}, d2 = {"Lcom/varun/akva/interaction/VoiceEngine;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "handler", "Landroid/os/Handler;", "isInitialized", "", "<set-?>", "isSpeakingConversation", "()Z", "tts", "Landroid/speech/tts/TextToSpeech;", "getConfigForApp", "Lcom/varun/akva/interaction/VoiceConfig;", "packageName", "", "getVolume", "", "setParams", "", "pitch", "rate", "shutdown", "speak", "text", "speakConversation", "speakDirect", "speakProactive", "stop", "Companion", "app_debug"})
public final class VoiceEngine {
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    @org.jetbrains.annotations.Nullable()
    private android.speech.tts.TextToSpeech tts;
    private boolean isInitialized = false;
    @org.jetbrains.annotations.NotNull()
    private final android.os.Handler handler = null;
    @kotlin.jvm.Volatile()
    private volatile boolean isSpeakingConversation = false;
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String TAG = "VoiceEngine";
    @org.jetbrains.annotations.NotNull()
    public static final com.varun.akva.interaction.VoiceEngine.Companion Companion = null;
    
    public VoiceEngine(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    public final boolean isSpeakingConversation() {
        return false;
    }
    
    private final float getVolume() {
        return 0.0F;
    }
    
    private final com.varun.akva.interaction.VoiceConfig getConfigForApp(java.lang.String packageName) {
        return null;
    }
    
    public final void speakDirect(@org.jetbrains.annotations.NotNull()
    java.lang.String text) {
    }
    
    public final void speakProactive(@org.jetbrains.annotations.NotNull()
    java.lang.String text, @org.jetbrains.annotations.NotNull()
    java.lang.String packageName) {
    }
    
    public final void speakConversation(@org.jetbrains.annotations.NotNull()
    java.lang.String text) {
    }
    
    public final void stop() {
    }
    
    public final void shutdown() {
    }
    
    public final void setParams(float pitch, float rate) {
    }
    
    public final void speak(@org.jetbrains.annotations.NotNull()
    java.lang.String text) {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lcom/varun/akva/interaction/VoiceEngine$Companion;", "", "()V", "TAG", "", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}