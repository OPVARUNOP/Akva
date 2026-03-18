package com.varun.akva.interaction;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\u0003\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005\u00a2\u0006\u0002\u0010\bJ\b\u0010\u000e\u001a\u00020\u0007H\u0016J\u0012\u0010\u000f\u001a\u00020\u00072\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0016J\b\u0010\u0012\u001a\u00020\u0007H\u0016J\u0010\u0010\u0013\u001a\u00020\u00072\u0006\u0010\u0014\u001a\u00020\u0015H\u0016J\u001a\u0010\u0016\u001a\u00020\u00072\u0006\u0010\u0014\u001a\u00020\u00152\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0016J\u0012\u0010\u0019\u001a\u00020\u00072\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0016J\u0012\u0010\u001a\u001a\u00020\u00072\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0016J\u0012\u0010\u001b\u001a\u00020\u00072\b\u0010\u001c\u001a\u0004\u0018\u00010\u0018H\u0016J\u0010\u0010\u001d\u001a\u00020\u00072\u0006\u0010\u001c\u001a\u00020\u001eH\u0016J\u0006\u0010\u001f\u001a\u00020\u0007J\u0006\u0010 \u001a\u00020\u0007R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u000b\u001a\n \r*\u0004\u0018\u00010\f0\fX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006!"}, d2 = {"Lcom/varun/akva/interaction/AKVAListenerEngine;", "Landroid/speech/RecognitionListener;", "ctx", "Landroid/content/Context;", "onRes", "Lkotlin/Function1;", "", "", "(Landroid/content/Context;Lkotlin/jvm/functions/Function1;)V", "i", "Landroid/content/Intent;", "sr", "Landroid/speech/SpeechRecognizer;", "kotlin.jvm.PlatformType", "onBeginningOfSpeech", "onBufferReceived", "b", "", "onEndOfSpeech", "onError", "e", "", "onEvent", "p", "Landroid/os/Bundle;", "onPartialResults", "onReadyForSpeech", "onResults", "r", "onRmsChanged", "", "startListening", "stopListening", "app_debug"})
public final class AKVAListenerEngine implements android.speech.RecognitionListener {
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context ctx = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.jvm.functions.Function1<java.lang.String, kotlin.Unit> onRes = null;
    private android.speech.SpeechRecognizer sr;
    @org.jetbrains.annotations.NotNull()
    private final android.content.Intent i = null;
    
    public AKVAListenerEngine(@org.jetbrains.annotations.NotNull()
    android.content.Context ctx, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> onRes) {
        super();
    }
    
    public final void startListening() {
    }
    
    public final void stopListening() {
    }
    
    @java.lang.Override()
    public void onResults(@org.jetbrains.annotations.Nullable()
    android.os.Bundle r) {
    }
    
    @java.lang.Override()
    public void onError(int e) {
    }
    
    @java.lang.Override()
    public void onReadyForSpeech(@org.jetbrains.annotations.Nullable()
    android.os.Bundle p) {
    }
    
    @java.lang.Override()
    public void onBeginningOfSpeech() {
    }
    
    @java.lang.Override()
    public void onRmsChanged(float r) {
    }
    
    @java.lang.Override()
    public void onBufferReceived(@org.jetbrains.annotations.Nullable()
    byte[] b) {
    }
    
    @java.lang.Override()
    public void onEndOfSpeech() {
    }
    
    @java.lang.Override()
    public void onPartialResults(@org.jetbrains.annotations.Nullable()
    android.os.Bundle p) {
    }
    
    @java.lang.Override()
    public void onEvent(int e, @org.jetbrains.annotations.Nullable()
    android.os.Bundle p) {
    }
}