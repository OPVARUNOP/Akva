package com.varun.akva.intelligence;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010$\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J:\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0012\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00120\u00142\u0006\u0010\u0015\u001a\u00020\u00122\u0006\u0010\u0016\u001a\u00020\u0010H\u0086@\u00a2\u0006\u0002\u0010\u0017J\u0006\u0010\u0018\u001a\u00020\u0019J\u0006\u0010\u001a\u001a\u00020\u001bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001c"}, d2 = {"Lcom/varun/akva/intelligence/MorningBriefing;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "contextDetector", "Lcom/varun/akva/intelligence/ContextDetector;", "dateFormat", "Ljava/text/SimpleDateFormat;", "geminiEngine", "Lcom/varun/akva/intelligence/GeminiEngine;", "repository", "Lcom/varun/akva/data/AkvaRepository;", "settingsManager", "Lcom/varun/akva/data/SettingsManager;", "generateBriefing", "", "totalUnread", "", "perAppUnread", "", "batteryPercent", "networkType", "(ILjava/util/Map;ILjava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "markDelivered", "", "shouldTrigger", "", "app_debug"})
public final class MorningBriefing {
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull()
    private final com.varun.akva.data.SettingsManager settingsManager = null;
    @org.jetbrains.annotations.NotNull()
    private final com.varun.akva.data.AkvaRepository repository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.varun.akva.intelligence.GeminiEngine geminiEngine = null;
    @org.jetbrains.annotations.NotNull()
    private final com.varun.akva.intelligence.ContextDetector contextDetector = null;
    @org.jetbrains.annotations.NotNull()
    private final java.text.SimpleDateFormat dateFormat = null;
    
    public MorningBriefing(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    public final boolean shouldTrigger() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object generateBriefing(int totalUnread, @org.jetbrains.annotations.NotNull()
    java.util.Map<java.lang.String, java.lang.Integer> perAppUnread, int batteryPercent, @org.jetbrains.annotations.NotNull()
    java.lang.String networkType, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.String> $completion) {
        return null;
    }
    
    public final void markDelivered() {
    }
}