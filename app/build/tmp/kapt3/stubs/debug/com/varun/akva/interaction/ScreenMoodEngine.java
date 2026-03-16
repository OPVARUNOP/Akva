package com.varun.akva.interaction;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0004\u0018\u0000 \u001f2\u00020\u0001:\u0002\u001f B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J,\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00062\b\b\u0002\u0010\u0012\u001a\u00020\u00132\u0010\b\u0002\u0010\u0014\u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u0015H\u0002J\u000e\u0010\u0016\u001a\u00020\u00102\u0006\u0010\u0017\u001a\u00020\u0018J\b\u0010\u0019\u001a\u00020\u0010H\u0002J\u0018\u0010\u001a\u001a\u00020\u00182\u0006\u0010\u001b\u001a\u00020\u00132\b\b\u0002\u0010\u001c\u001a\u00020\u001dJ\u0006\u0010\u001e\u001a\u00020\u0010R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006!"}, d2 = {"Lcom/varun/akva/interaction/ScreenMoodEngine;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "currentAlpha", "", "handler", "Landroid/os/Handler;", "overlayView", "Landroid/view/View;", "settingsManager", "Lcom/varun/akva/data/SettingsManager;", "windowManager", "Landroid/view/WindowManager;", "animateTo", "", "targetAlpha", "targetColor", "", "onComplete", "Lkotlin/Function0;", "applyMood", "mood", "Lcom/varun/akva/interaction/ScreenMoodEngine$Mood;", "createOverlay", "getMoodForHour", "hour", "isStressed", "", "removeOverlay", "Companion", "Mood", "app_debug"})
public final class ScreenMoodEngine {
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull()
    private final com.varun.akva.data.SettingsManager settingsManager = null;
    @org.jetbrains.annotations.Nullable()
    private android.view.View overlayView;
    @org.jetbrains.annotations.Nullable()
    private android.view.WindowManager windowManager;
    private float currentAlpha = 0.0F;
    @org.jetbrains.annotations.NotNull()
    private final android.os.Handler handler = null;
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String TAG = "ScreenMoodEngine";
    @org.jetbrains.annotations.NotNull()
    public static final com.varun.akva.interaction.ScreenMoodEngine.Companion Companion = null;
    
    public ScreenMoodEngine(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.varun.akva.interaction.ScreenMoodEngine.Mood getMoodForHour(int hour, boolean isStressed) {
        return null;
    }
    
    public final void applyMood(@org.jetbrains.annotations.NotNull()
    com.varun.akva.interaction.ScreenMoodEngine.Mood mood) {
    }
    
    private final void createOverlay() {
    }
    
    private final void animateTo(float targetAlpha, int targetColor, kotlin.jvm.functions.Function0<kotlin.Unit> onComplete) {
    }
    
    public final void removeOverlay() {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lcom/varun/akva/interaction/ScreenMoodEngine$Companion;", "", "()V", "TAG", "", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u000b\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0017\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000f\u00a8\u0006\u0010"}, d2 = {"Lcom/varun/akva/interaction/ScreenMoodEngine$Mood;", "", "color", "", "alpha", "", "(Ljava/lang/String;IIF)V", "getAlpha", "()F", "getColor", "()I", "MORNING_GOLD", "AFTERNOON_CLEAR", "EVENING_COOL", "NIGHT_AMBER", "STRESS_CALM", "app_debug"})
    public static enum Mood {
        /*public static final*/ MORNING_GOLD /* = new MORNING_GOLD(0, 0.0F) */,
        /*public static final*/ AFTERNOON_CLEAR /* = new AFTERNOON_CLEAR(0, 0.0F) */,
        /*public static final*/ EVENING_COOL /* = new EVENING_COOL(0, 0.0F) */,
        /*public static final*/ NIGHT_AMBER /* = new NIGHT_AMBER(0, 0.0F) */,
        /*public static final*/ STRESS_CALM /* = new STRESS_CALM(0, 0.0F) */;
        private final int color = 0;
        private final float alpha = 0.0F;
        
        Mood(int color, float alpha) {
        }
        
        public final int getColor() {
            return 0;
        }
        
        public final float getAlpha() {
            return 0.0F;
        }
        
        @org.jetbrains.annotations.NotNull()
        public static kotlin.enums.EnumEntries<com.varun.akva.interaction.ScreenMoodEngine.Mood> getEntries() {
            return null;
        }
    }
}