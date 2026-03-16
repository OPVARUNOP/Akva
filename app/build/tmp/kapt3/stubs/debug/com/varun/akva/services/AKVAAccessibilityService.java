package com.varun.akva.services;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u00a2\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 ?2\u00020\u0001:\u0001?B\u0005\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010+\u001a\u00020,H\u0082@\u00a2\u0006\u0002\u0010-J\b\u0010.\u001a\u00020\u0006H\u0002J&\u0010/\u001a\u00020,2\u0006\u00100\u001a\u00020\u00062\u0006\u00101\u001a\u00020\u00062\u0006\u00102\u001a\u00020\u0006H\u0082@\u00a2\u0006\u0002\u00103J\u0012\u00104\u001a\u00020,2\b\u00105\u001a\u0004\u0018\u000106H\u0016J\b\u00107\u001a\u00020,H\u0016J\b\u00108\u001a\u00020,H\u0016J\b\u00109\u001a\u00020,H\u0014J\u0012\u0010:\u001a\u00020;2\b\u0010<\u001a\u0004\u0018\u00010=H\u0016J\u000e\u0010>\u001a\u00020,H\u0082@\u00a2\u0006\u0002\u0010-R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u001dX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u001fX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020!X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020#X\u0082.\u00a2\u0006\u0002\n\u0000R\u001a\u0010$\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020&0%X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\'\u001a\u00020(X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010)\u001a\u00020*X\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006@"}, d2 = {"Lcom/varun/akva/services/AKVAAccessibilityService;", "Landroid/accessibilityservice/AccessibilityService;", "()V", "contextDetector", "Lcom/varun/akva/intelligence/ContextDetector;", "deviceId", "", "geminiEngine", "Lcom/varun/akva/intelligence/GeminiEngine;", "hapticEngine", "Lcom/varun/akva/interaction/HapticEngine;", "lastPackage", "lastSpeechTime", "", "morningBriefing", "Lcom/varun/akva/intelligence/MorningBriefing;", "nightModeManager", "Lcom/varun/akva/interaction/NightModeManager;", "patternLearner", "Lcom/varun/akva/intelligence/PatternLearner;", "personalityEngine", "Lcom/varun/akva/intelligence/PersonalityEngine;", "phoneSystemMonitor", "Lcom/varun/akva/interaction/PhoneSystemMonitor;", "repository", "Lcom/varun/akva/data/AkvaRepository;", "scope", "Lkotlinx/coroutines/CoroutineScope;", "screenMoodEngine", "Lcom/varun/akva/interaction/ScreenMoodEngine;", "settingsGuide", "Lcom/varun/akva/intelligence/AKVASettingsGuide;", "settingsManager", "Lcom/varun/akva/data/SettingsManager;", "stressDetector", "Lcom/varun/akva/intelligence/StressDetector;", "usageCounts", "", "", "voiceEngine", "Lcom/varun/akva/interaction/VoiceEngine;", "weeklyStoryEngine", "Lcom/varun/akva/intelligence/WeeklyStoryEngine;", "deliverWeeklyStory", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getHashedDeviceId", "handleAppOpen", "packageName", "className", "previousApp", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onAccessibilityEvent", "event", "Landroid/view/accessibility/AccessibilityEvent;", "onDestroy", "onInterrupt", "onServiceConnected", "onUnbind", "", "intent", "Landroid/content/Intent;", "tryMorningBriefing", "Companion", "app_debug"})
public final class AKVAAccessibilityService extends android.accessibilityservice.AccessibilityService {
    private com.varun.akva.interaction.VoiceEngine voiceEngine;
    private com.varun.akva.interaction.HapticEngine hapticEngine;
    private com.varun.akva.interaction.ScreenMoodEngine screenMoodEngine;
    private com.varun.akva.interaction.NightModeManager nightModeManager;
    private com.varun.akva.interaction.PhoneSystemMonitor phoneSystemMonitor;
    private com.varun.akva.intelligence.ContextDetector contextDetector;
    private com.varun.akva.intelligence.StressDetector stressDetector;
    private com.varun.akva.intelligence.PatternLearner patternLearner;
    private com.varun.akva.intelligence.PersonalityEngine personalityEngine;
    private com.varun.akva.intelligence.GeminiEngine geminiEngine;
    private com.varun.akva.intelligence.MorningBriefing morningBriefing;
    private com.varun.akva.intelligence.WeeklyStoryEngine weeklyStoryEngine;
    private com.varun.akva.intelligence.AKVASettingsGuide settingsGuide;
    private com.varun.akva.data.AkvaRepository repository;
    private com.varun.akva.data.SettingsManager settingsManager;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.CoroutineScope scope = null;
    private long lastSpeechTime = 0L;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String lastPackage = "";
    @org.jetbrains.annotations.NotNull()
    private final java.util.Map<java.lang.String, java.lang.Integer> usageCounts = null;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String deviceId = "";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String TAG = "AKVAAccessibility";
    private static final long MIN_SPEECH_GAP_MS = 2500L;
    private static boolean isRunning = false;
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
    
    private final java.lang.Object handleAppOpen(java.lang.String packageName, java.lang.String className, java.lang.String previousApp, kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    private final java.lang.Object tryMorningBriefing(kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    private final java.lang.Object deliverWeeklyStory(kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    private final java.lang.String getHashedDeviceId() {
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
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T\u00a2\u0006\u0002\n\u0000R\u001e\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b@BX\u0086\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u00a8\u0006\u000b"}, d2 = {"Lcom/varun/akva/services/AKVAAccessibilityService$Companion;", "", "()V", "MIN_SPEECH_GAP_MS", "", "TAG", "", "<set-?>", "", "isRunning", "()Z", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        public final boolean isRunning() {
            return false;
        }
    }
}