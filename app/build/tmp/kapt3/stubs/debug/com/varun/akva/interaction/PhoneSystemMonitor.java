package com.varun.akva.interaction;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0010\u0018\u0000 /2\u00020\u0001:\u0001/B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0006\u0010\u001e\u001a\u00020\u0010J\u0006\u0010\u001f\u001a\u00020 J\u0018\u0010!\u001a\u00020\u00152\u0006\u0010\"\u001a\u00020\u00102\u0006\u0010#\u001a\u00020\fH\u0002J\u0016\u0010$\u001a\u00020\u00152\u0006\u0010%\u001a\u00020\u001d2\u0006\u0010&\u001a\u00020\nJ\u0006\u0010\'\u001a\u00020\fJ\u0006\u0010(\u001a\u00020\fJ\u0006\u0010)\u001a\u00020\u0015J\u0006\u0010*\u001a\u00020\u0015J\u0014\u0010+\u001a\u00020\u00152\f\u0010,\u001a\b\u0012\u0004\u0012\u00020\u00150\u0014J\u0014\u0010-\u001a\u00020\u00152\f\u0010,\u001a\b\u0012\u0004\u0012\u00020\u00150\u0014J\u0006\u0010.\u001a\u00020\u0015R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001e\u0010\r\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\f@BX\u0086\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u0013\u001a\n\u0012\u0004\u0012\u00020\u0015\u0018\u00010\u0014X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u0016\u001a\n\u0012\u0004\u0012\u00020\u0015\u0018\u00010\u0014X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u001c\u001a\u0004\u0018\u00010\u001dX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u00060"}, d2 = {"Lcom/varun/akva/interaction/PhoneSystemMonitor;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "batteryReceiver", "Landroid/content/BroadcastReceiver;", "contextDetector", "Lcom/varun/akva/intelligence/ContextDetector;", "hapticEngine", "Lcom/varun/akva/interaction/HapticEngine;", "<set-?>", "", "isInCall", "()Z", "lastBatteryWarning", "", "lastScreenOffTime", "", "onFirstUnlockOfDay", "Lkotlin/Function0;", "", "onLateNightScreenOn", "personalityEngine", "Lcom/varun/akva/intelligence/PersonalityEngine;", "screenReceiver", "settingsManager", "Lcom/varun/akva/data/SettingsManager;", "voiceEngine", "Lcom/varun/akva/interaction/VoiceEngine;", "getBatteryPercent", "getNetworkType", "", "handleBattery", "percent", "charging", "init", "voice", "haptic", "isCharging", "isInActiveCall", "registerBatteryMonitor", "registerScreenMonitor", "setOnFirstUnlockOfDay", "callback", "setOnLateNightScreenOn", "unregister", "Companion", "app_debug"})
public final class PhoneSystemMonitor {
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull()
    private final com.varun.akva.intelligence.ContextDetector contextDetector = null;
    @org.jetbrains.annotations.NotNull()
    private final com.varun.akva.intelligence.PersonalityEngine personalityEngine = null;
    @org.jetbrains.annotations.NotNull()
    private final com.varun.akva.data.SettingsManager settingsManager = null;
    @org.jetbrains.annotations.Nullable()
    private com.varun.akva.interaction.VoiceEngine voiceEngine;
    @org.jetbrains.annotations.Nullable()
    private com.varun.akva.interaction.HapticEngine hapticEngine;
    @org.jetbrains.annotations.Nullable()
    private android.content.BroadcastReceiver batteryReceiver;
    @org.jetbrains.annotations.Nullable()
    private android.content.BroadcastReceiver screenReceiver;
    private boolean isInCall = false;
    private int lastBatteryWarning = -1;
    private long lastScreenOffTime = 0L;
    @org.jetbrains.annotations.Nullable()
    private kotlin.jvm.functions.Function0<kotlin.Unit> onFirstUnlockOfDay;
    @org.jetbrains.annotations.Nullable()
    private kotlin.jvm.functions.Function0<kotlin.Unit> onLateNightScreenOn;
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String TAG = "PhoneSystemMonitor";
    @org.jetbrains.annotations.NotNull()
    public static final com.varun.akva.interaction.PhoneSystemMonitor.Companion Companion = null;
    
    public PhoneSystemMonitor(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    public final boolean isInCall() {
        return false;
    }
    
    public final void init(@org.jetbrains.annotations.NotNull()
    com.varun.akva.interaction.VoiceEngine voice, @org.jetbrains.annotations.NotNull()
    com.varun.akva.interaction.HapticEngine haptic) {
    }
    
    public final int getBatteryPercent() {
        return 0;
    }
    
    public final boolean isCharging() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getNetworkType() {
        return null;
    }
    
    public final boolean isInActiveCall() {
        return false;
    }
    
    public final void setOnFirstUnlockOfDay(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> callback) {
    }
    
    public final void setOnLateNightScreenOn(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> callback) {
    }
    
    public final void registerBatteryMonitor() {
    }
    
    public final void registerScreenMonitor() {
    }
    
    private final void handleBattery(int percent, boolean charging) {
    }
    
    public final void unregister() {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lcom/varun/akva/interaction/PhoneSystemMonitor$Companion;", "", "()V", "TAG", "", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}