package com.varun.akva.intelligence;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0006\u0010\u0007\u001a\u00020\bJ\u000e\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000bJ\u0006\u0010\f\u001a\u00020\bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\r"}, d2 = {"Lcom/varun/akva/intelligence/AKVAActionFeedback;", "", "context", "Landroid/content/Context;", "hapticEngine", "Lcom/varun/akva/interaction/HapticEngine;", "(Landroid/content/Context;Lcom/varun/akva/interaction/HapticEngine;)V", "onActionFailure", "", "onActionStart", "actionName", "", "onActionSuccess", "app_debug"})
public final class AKVAActionFeedback {
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull()
    private final com.varun.akva.interaction.HapticEngine hapticEngine = null;
    
    public AKVAActionFeedback(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    com.varun.akva.interaction.HapticEngine hapticEngine) {
        super();
    }
    
    public final void onActionStart(@org.jetbrains.annotations.NotNull()
    java.lang.String actionName) {
    }
    
    public final void onActionSuccess() {
    }
    
    public final void onActionFailure() {
    }
}