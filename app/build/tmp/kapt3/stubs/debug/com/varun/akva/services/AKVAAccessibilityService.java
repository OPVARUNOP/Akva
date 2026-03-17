package com.varun.akva.services;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000x\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010%\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 ,2\u00020\u0001:\u0001,B\u0005\u00a2\u0006\u0002\u0010\u0002J\u001e\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\n2\u0006\u0010 \u001a\u00020\nH\u0082@\u00a2\u0006\u0002\u0010!J\u0012\u0010\"\u001a\u00020\u001e2\b\u0010#\u001a\u0004\u0018\u00010$H\u0016J\b\u0010%\u001a\u00020\u001eH\u0016J\b\u0010&\u001a\u00020\u001eH\u0016J\b\u0010\'\u001a\u00020\u001eH\u0014J\u0012\u0010(\u001a\u00020)2\b\u0010*\u001a\u0004\u0018\u00010+H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\r0\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\rX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u001cX\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006-"}, d2 = {"Lcom/varun/akva/services/AKVAAccessibilityService;", "Landroid/accessibilityservice/AccessibilityService;", "()V", "contextDetector", "Lcom/varun/akva/intelligence/ContextDetector;", "geminiEngine", "Lcom/varun/akva/intelligence/GeminiEngine;", "hapticEngine", "Lcom/varun/akva/interaction/HapticEngine;", "lastPackage", "", "lastSpeechPerApp", "", "", "lastSpeechTime", "patternLearner", "Lcom/varun/akva/intelligence/PatternLearner;", "personalityEngine", "Lcom/varun/akva/intelligence/PersonalityEngine;", "phoneSystemMonitor", "Lcom/varun/akva/interaction/PhoneSystemMonitor;", "repository", "Lcom/varun/akva/data/AkvaRepository;", "scope", "Lkotlinx/coroutines/CoroutineScope;", "settingsManager", "Lcom/varun/akva/data/SettingsManager;", "voiceEngine", "Lcom/varun/akva/interaction/VoiceEngine;", "handleAppOpen", "", "packageName", "previousApp", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onAccessibilityEvent", "event", "Landroid/view/accessibility/AccessibilityEvent;", "onDestroy", "onInterrupt", "onServiceConnected", "onUnbind", "", "intent", "Landroid/content/Intent;", "Companion", "app_debug"})
public final class AKVAAccessibilityService extends android.accessibilityservice.AccessibilityService {
    private com.varun.akva.interaction.VoiceEngine voiceEngine;
    private com.varun.akva.interaction.HapticEngine hapticEngine;
    private com.varun.akva.interaction.PhoneSystemMonitor phoneSystemMonitor;
    private com.varun.akva.intelligence.ContextDetector contextDetector;
    private com.varun.akva.intelligence.PatternLearner patternLearner;
    private com.varun.akva.intelligence.PersonalityEngine personalityEngine;
    private com.varun.akva.intelligence.GeminiEngine geminiEngine;
    private com.varun.akva.data.AkvaRepository repository;
    private com.varun.akva.data.SettingsManager settingsManager;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.CoroutineScope scope = null;
    private long lastSpeechTime = 0L;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String lastPackage = "";
    @org.jetbrains.annotations.NotNull()
    private final java.util.Map<java.lang.String, java.lang.Long> lastSpeechPerApp = null;
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String TAG = "AKVAAccessibility";
    private static final long MIN_SPEECH_GAP_MS = 3000L;
    private static final long REPEAT_APP_COOLDOWN_MS = 600000L;
    private static boolean isRunning = false;
    @org.jetbrains.annotations.Nullable()
    private static com.varun.akva.services.AKVAAccessibilityService instance;
    @org.jetbrains.annotations.NotNull()
    private static final java.util.Set<java.lang.String> SILENT_PACKAGES = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.varun.akva.services.AKVAAccessibilityService.Companion Companion = null;
    
    public AKVAAccessibilityService() {
        super();
    }
    
    @java.lang.Override()
    protected void onServiceConnected() {
    }
    
    @java.lang.Override()
    public void onAccessibilityEvent(@org.jetbrains.annotations.Nullable()
    android.view.accessibility.AccessibilityEvent event) {
    }
    
    private final java.lang.Object handleAppOpen(java.lang.String packageName, java.lang.String previousApp, kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override()
    public void onInterrupt() {
    }
    
    @java.lang.Override()
    public boolean onUnbind(@org.jetbrains.annotations.Nullable()
    android.content.Intent intent) {
        return false;
    }
    
    @java.lang.Override()
    public void onDestroy() {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0082T\u00a2\u0006\u0002\n\u0000R\"\u0010\f\u001a\u0004\u0018\u00010\u000b2\b\u0010\n\u001a\u0004\u0018\u00010\u000b@BX\u0086\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u001e\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\n\u001a\u00020\u000f@BX\u0086\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011\u00a8\u0006\u0012"}, d2 = {"Lcom/varun/akva/services/AKVAAccessibilityService$Companion;", "", "()V", "MIN_SPEECH_GAP_MS", "", "REPEAT_APP_COOLDOWN_MS", "SILENT_PACKAGES", "", "", "TAG", "<set-?>", "Lcom/varun/akva/services/AKVAAccessibilityService;", "instance", "getInstance", "()Lcom/varun/akva/services/AKVAAccessibilityService;", "", "isRunning", "()Z", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        public final boolean isRunning() {
            return false;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final com.varun.akva.services.AKVAAccessibilityService getInstance() {
            return null;
        }
    }
}