package com.varun.akva.services;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0010\t\n\u0002\b\u0005\u0018\u0000 -2\u00020\u0001:\u0001-B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020\u001cJ\u0010\u0010#\u001a\u00020!2\u0006\u0010$\u001a\u00020\u001fH\u0002J\u0010\u0010%\u001a\u00020!2\u0006\u0010&\u001a\u00020\u001fH\u0002J\u0010\u0010\'\u001a\u00020!2\u0006\u0010(\u001a\u00020)H\u0002J\u0006\u0010*\u001a\u00020!J\b\u0010+\u001a\u00020!H\u0002J\u0006\u0010,\u001a\u00020!R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0010X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0010X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u001aX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u001cX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001f0\u001eX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006."}, d2 = {"Lcom/varun/akva/services/AKVAVoiceInput;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "commandEngine", "Lcom/varun/akva/intelligence/AKVACommandEngine;", "contextDetector", "Lcom/varun/akva/intelligence/ContextDetector;", "geminiEngine", "Lcom/varun/akva/intelligence/GeminiEngine;", "handler", "Landroid/os/Handler;", "hapticEngine", "Lcom/varun/akva/interaction/HapticEngine;", "isDestroyed", "", "isListening", "isWaitingForCommand", "phoneSystemMonitor", "Lcom/varun/akva/interaction/PhoneSystemMonitor;", "scope", "Lkotlinx/coroutines/CoroutineScope;", "settingsManager", "Lcom/varun/akva/data/SettingsManager;", "speechRecognizer", "Landroid/speech/SpeechRecognizer;", "voiceEngine", "Lcom/varun/akva/interaction/VoiceEngine;", "wakeWords", "", "", "init", "", "voice", "onWakeWordDetected", "fullText", "processCommand", "userSpeech", "restartListening", "delayMs", "", "startContinuousListening", "startListening", "stopListening", "Companion", "app_debug"})
public final class AKVAVoiceInput {
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull()
    private final com.varun.akva.data.SettingsManager settingsManager = null;
    @org.jetbrains.annotations.NotNull()
    private final com.varun.akva.intelligence.ContextDetector contextDetector = null;
    @org.jetbrains.annotations.NotNull()
    private final com.varun.akva.intelligence.GeminiEngine geminiEngine = null;
    @org.jetbrains.annotations.NotNull()
    private final com.varun.akva.interaction.PhoneSystemMonitor phoneSystemMonitor = null;
    @org.jetbrains.annotations.NotNull()
    private final com.varun.akva.interaction.HapticEngine hapticEngine = null;
    @org.jetbrains.annotations.Nullable()
    private com.varun.akva.interaction.VoiceEngine voiceEngine;
    @org.jetbrains.annotations.NotNull()
    private final com.varun.akva.intelligence.AKVACommandEngine commandEngine = null;
    @org.jetbrains.annotations.Nullable()
    private android.speech.SpeechRecognizer speechRecognizer;
    @org.jetbrains.annotations.NotNull()
    private final android.os.Handler handler = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.CoroutineScope scope = null;
    private boolean isListening = false;
    private boolean isWaitingForCommand = false;
    private boolean isDestroyed = false;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<java.lang.String> wakeWords = null;
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String TAG = "AKVAVoiceInput";
    @org.jetbrains.annotations.NotNull()
    public static final com.varun.akva.services.AKVAVoiceInput.Companion Companion = null;
    
    public AKVAVoiceInput(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    public final void init(@org.jetbrains.annotations.NotNull()
    com.varun.akva.interaction.VoiceEngine voice) {
    }
    
    public final void startContinuousListening() {
    }
    
    private final void startListening() {
    }
    
    private final void onWakeWordDetected(java.lang.String fullText) {
    }
    
    private final void processCommand(java.lang.String userSpeech) {
    }
    
    private final void restartListening(long delayMs) {
    }
    
    public final void stopListening() {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lcom/varun/akva/services/AKVAVoiceInput$Companion;", "", "()V", "TAG", "", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}