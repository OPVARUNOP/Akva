package com.varun.akva.intelligence;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006J\u0006\u0010\b\u001a\u00020\u0006J\u0006\u0010\t\u001a\u00020\nJ\u0006\u0010\u000b\u001a\u00020\u0006J\u0006\u0010\f\u001a\u00020\rJ\u000e\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0007\u001a\u00020\u0006J\u000e\u0010\u000f\u001a\u00020\r2\u0006\u0010\u0007\u001a\u00020\u0006R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0010"}, d2 = {"Lcom/varun/akva/intelligence/ContextDetector;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "getAppName", "", "packageName", "getDayOfWeek", "getHourOfDay", "", "getTimeOfDay", "isNightTime", "", "isRestrictedApp", "isSettingsApp", "app_debug"})
public final class ContextDetector {
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    
    public ContextDetector(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getTimeOfDay() {
        return null;
    }
    
    public final int getHourOfDay() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getDayOfWeek() {
        return null;
    }
    
    public final boolean isNightTime() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getAppName(@org.jetbrains.annotations.NotNull()
    java.lang.String packageName) {
        return null;
    }
    
    public final boolean isRestrictedApp(@org.jetbrains.annotations.NotNull()
    java.lang.String packageName) {
        return false;
    }
    
    public final boolean isSettingsApp(@org.jetbrains.annotations.NotNull()
    java.lang.String packageName) {
        return false;
    }
}