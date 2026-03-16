package com.varun.akva.interaction;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0006\u0010\u0007\u001a\u00020\bJ\u0006\u0010\t\u001a\u00020\nJ\u0006\u0010\u000b\u001a\u00020\fJ\u0006\u0010\r\u001a\u00020\fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000e"}, d2 = {"Lcom/varun/akva/interaction/NightModeManager;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "contextDetector", "Lcom/varun/akva/intelligence/ContextDetector;", "getScreenMood", "Lcom/varun/akva/interaction/ScreenMoodEngine$Mood;", "getVolume", "", "isNightMode", "", "shouldWhisper", "app_debug"})
public final class NightModeManager {
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull()
    private final com.varun.akva.intelligence.ContextDetector contextDetector = null;
    
    public NightModeManager(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    public final boolean isNightMode() {
        return false;
    }
    
    public final float getVolume() {
        return 0.0F;
    }
    
    public final boolean shouldWhisper() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.varun.akva.interaction.ScreenMoodEngine.Mood getScreenMood() {
        return null;
    }
}