package com.varun.akva.intelligence;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J*\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0006\u0010\r\u001a\u00020\f2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0010\u001a\u00020\fH\u0082@\u00a2\u0006\u0002\u0010\u0011J4\u0010\u0012\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\f2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\f2\b\b\u0002\u0010\u0013\u001a\u00020\u0014H\u0086@\u00a2\u0006\u0002\u0010\u0015R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0016"}, d2 = {"Lcom/varun/akva/intelligence/GeminiEngine;", "", "ctx", "Landroid/content/Context;", "s", "Lcom/varun/akva/data/SettingsManager;", "(Landroid/content/Context;Lcom/varun/akva/data/SettingsManager;)V", "client", "Lokhttp3/OkHttpClient;", "model", "Lcom/google/ai/client/generativeai/GenerativeModel;", "callBackend", "", "p", "pr", "Lcom/varun/akva/data/UserProfile;", "app", "(Ljava/lang/String;Lcom/varun/akva/data/UserProfile;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getResponse", "tier", "", "(Ljava/lang/String;Lcom/varun/akva/data/UserProfile;Ljava/lang/String;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public final class GeminiEngine {
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context ctx = null;
    @org.jetbrains.annotations.NotNull()
    private final com.varun.akva.data.SettingsManager s = null;
    @org.jetbrains.annotations.NotNull()
    private final okhttp3.OkHttpClient client = null;
    @org.jetbrains.annotations.Nullable()
    private com.google.ai.client.generativeai.GenerativeModel model;
    
    public GeminiEngine(@org.jetbrains.annotations.NotNull()
    android.content.Context ctx, @org.jetbrains.annotations.NotNull()
    com.varun.akva.data.SettingsManager s) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getResponse(@org.jetbrains.annotations.NotNull()
    java.lang.String p, @org.jetbrains.annotations.Nullable()
    com.varun.akva.data.UserProfile pr, @org.jetbrains.annotations.NotNull()
    java.lang.String app, int tier, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.String> $completion) {
        return null;
    }
    
    private final java.lang.Object callBackend(java.lang.String p, com.varun.akva.data.UserProfile pr, java.lang.String app, kotlin.coroutines.Continuation<? super java.lang.String> $completion) {
        return null;
    }
}