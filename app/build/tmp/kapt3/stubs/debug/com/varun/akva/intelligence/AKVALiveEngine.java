package com.varun.akva.intelligence;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000t\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001Bm\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u0011\u0012\u0006\u0010\u0012\u001a\u00020\u0013\u0012\u0006\u0010\u0014\u001a\u00020\u0015\u0012\u0006\u0010\u0016\u001a\u00020\u0017\u0012\u0006\u0010\u0018\u001a\u00020\u0019\u0012\u0006\u0010\u001a\u001a\u00020\u001b\u00a2\u0006\u0002\u0010\u001cJ\u0010\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\"H\u0002J\u000e\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020 J\u000e\u0010&\u001a\u00020$2\u0006\u0010\'\u001a\u00020 R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u001eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006("}, d2 = {"Lcom/varun/akva/intelligence/AKVALiveEngine;", "", "ctx", "Landroid/content/Context;", "r", "Lcom/varun/akva/data/AkvaRepository;", "s", "Lcom/varun/akva/data/SettingsManager;", "v", "Lcom/varun/akva/interaction/VoiceEngine;", "h", "Lcom/varun/akva/interaction/HapticEngine;", "m", "Lcom/varun/akva/intelligence/AKVAMemoryEngine;", "g", "Lcom/varun/akva/intelligence/GeminiEngine;", "p", "Lcom/varun/akva/intelligence/CommandParser;", "al", "Lcom/varun/akva/actions/AppLauncher;", "ms", "Lcom/varun/akva/actions/MessageSender;", "rm", "Lcom/varun/akva/actions/ReminderManager;", "sc", "Lcom/varun/akva/actions/SystemController;", "ws", "Lcom/varun/akva/intelligence/AKVAWebSearch;", "(Landroid/content/Context;Lcom/varun/akva/data/AkvaRepository;Lcom/varun/akva/data/SettingsManager;Lcom/varun/akva/interaction/VoiceEngine;Lcom/varun/akva/interaction/HapticEngine;Lcom/varun/akva/intelligence/AKVAMemoryEngine;Lcom/varun/akva/intelligence/GeminiEngine;Lcom/varun/akva/intelligence/CommandParser;Lcom/varun/akva/actions/AppLauncher;Lcom/varun/akva/actions/MessageSender;Lcom/varun/akva/actions/ReminderManager;Lcom/varun/akva/actions/SystemController;Lcom/varun/akva/intelligence/AKVAWebSearch;)V", "scope", "Lkotlinx/coroutines/CoroutineScope;", "handle", "", "i", "Lcom/varun/akva/intelligence/ActionIntent;", "onUserSpeech", "", "u", "proactiveSpeak", "t", "app_debug"})
public final class AKVALiveEngine {
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context ctx = null;
    @org.jetbrains.annotations.NotNull()
    private final com.varun.akva.data.AkvaRepository r = null;
    @org.jetbrains.annotations.NotNull()
    private final com.varun.akva.data.SettingsManager s = null;
    @org.jetbrains.annotations.NotNull()
    private final com.varun.akva.interaction.VoiceEngine v = null;
    @org.jetbrains.annotations.NotNull()
    private final com.varun.akva.interaction.HapticEngine h = null;
    @org.jetbrains.annotations.NotNull()
    private final com.varun.akva.intelligence.AKVAMemoryEngine m = null;
    @org.jetbrains.annotations.NotNull()
    private final com.varun.akva.intelligence.GeminiEngine g = null;
    @org.jetbrains.annotations.NotNull()
    private final com.varun.akva.intelligence.CommandParser p = null;
    @org.jetbrains.annotations.NotNull()
    private final com.varun.akva.actions.AppLauncher al = null;
    @org.jetbrains.annotations.NotNull()
    private final com.varun.akva.actions.MessageSender ms = null;
    @org.jetbrains.annotations.NotNull()
    private final com.varun.akva.actions.ReminderManager rm = null;
    @org.jetbrains.annotations.NotNull()
    private final com.varun.akva.actions.SystemController sc = null;
    @org.jetbrains.annotations.NotNull()
    private final com.varun.akva.intelligence.AKVAWebSearch ws = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.CoroutineScope scope = null;
    
    public AKVALiveEngine(@org.jetbrains.annotations.NotNull()
    android.content.Context ctx, @org.jetbrains.annotations.NotNull()
    com.varun.akva.data.AkvaRepository r, @org.jetbrains.annotations.NotNull()
    com.varun.akva.data.SettingsManager s, @org.jetbrains.annotations.NotNull()
    com.varun.akva.interaction.VoiceEngine v, @org.jetbrains.annotations.NotNull()
    com.varun.akva.interaction.HapticEngine h, @org.jetbrains.annotations.NotNull()
    com.varun.akva.intelligence.AKVAMemoryEngine m, @org.jetbrains.annotations.NotNull()
    com.varun.akva.intelligence.GeminiEngine g, @org.jetbrains.annotations.NotNull()
    com.varun.akva.intelligence.CommandParser p, @org.jetbrains.annotations.NotNull()
    com.varun.akva.actions.AppLauncher al, @org.jetbrains.annotations.NotNull()
    com.varun.akva.actions.MessageSender ms, @org.jetbrains.annotations.NotNull()
    com.varun.akva.actions.ReminderManager rm, @org.jetbrains.annotations.NotNull()
    com.varun.akva.actions.SystemController sc, @org.jetbrains.annotations.NotNull()
    com.varun.akva.intelligence.AKVAWebSearch ws) {
        super();
    }
    
    public final void onUserSpeech(@org.jetbrains.annotations.NotNull()
    java.lang.String u) {
    }
    
    private final java.lang.String handle(com.varun.akva.intelligence.ActionIntent i) {
        return null;
    }
    
    public final void proactiveSpeak(@org.jetbrains.annotations.NotNull()
    java.lang.String t) {
    }
}