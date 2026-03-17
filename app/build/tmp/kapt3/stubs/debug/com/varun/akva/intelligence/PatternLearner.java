package com.varun.akva.intelligence;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u001c\u0010\u0005\u001a\u00020\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\n\u001a\u00020\u0006J\u001c\u0010\u000b\u001a\u00020\f2\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\r\u001a\u00020\u0006R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000e"}, d2 = {"Lcom/varun/akva/intelligence/PatternLearner;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "deducePattern", "", "events", "", "Lcom/varun/akva/data/AppUsageEvent;", "currentApp", "getTimesOpenedToday", "", "packageName", "app_debug"})
public final class PatternLearner {
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    
    public PatternLearner(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String deducePattern(@org.jetbrains.annotations.NotNull()
    java.util.List<com.varun.akva.data.AppUsageEvent> events, @org.jetbrains.annotations.NotNull()
    java.lang.String currentApp) {
        return null;
    }
    
    public final int getTimesOpenedToday(@org.jetbrains.annotations.NotNull()
    java.util.List<com.varun.akva.data.AppUsageEvent> events, @org.jetbrains.annotations.NotNull()
    java.lang.String packageName) {
        return 0;
    }
}