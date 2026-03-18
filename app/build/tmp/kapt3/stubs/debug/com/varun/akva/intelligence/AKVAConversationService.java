package com.varun.akva.intelligence;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\u0018\u00002\u00020\u0001B5\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u00a2\u0006\u0002\u0010\u000eJ\u0010\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0002J\u0016\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u0014H\u0086@\u00a2\u0006\u0002\u0010\u0017R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0018"}, d2 = {"Lcom/varun/akva/intelligence/AKVAConversationService;", "", "geminiEngine", "Lcom/varun/akva/intelligence/GeminiEngine;", "memoryEngine", "Lcom/varun/akva/intelligence/AKVAMemoryEngine;", "autonomousAgent", "Lcom/varun/akva/intelligence/AKVAAutonomousAgent;", "executor", "Lcom/varun/akva/intelligence/AKVAExecutor;", "responseEngine", "Lcom/varun/akva/intelligence/AKVAResponseEngine;", "feedback", "Lcom/varun/akva/intelligence/AKVAActionFeedback;", "(Lcom/varun/akva/intelligence/GeminiEngine;Lcom/varun/akva/intelligence/AKVAMemoryEngine;Lcom/varun/akva/intelligence/AKVAAutonomousAgent;Lcom/varun/akva/intelligence/AKVAExecutor;Lcom/varun/akva/intelligence/AKVAResponseEngine;Lcom/varun/akva/intelligence/AKVAActionFeedback;)V", "scope", "Lkotlinx/coroutines/CoroutineScope;", "isActionRequest", "", "text", "", "onUserSpeaks", "userText", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public final class AKVAConversationService {
    @org.jetbrains.annotations.NotNull()
    private final com.varun.akva.intelligence.GeminiEngine geminiEngine = null;
    @org.jetbrains.annotations.NotNull()
    private final com.varun.akva.intelligence.AKVAMemoryEngine memoryEngine = null;
    @org.jetbrains.annotations.NotNull()
    private final com.varun.akva.intelligence.AKVAAutonomousAgent autonomousAgent = null;
    @org.jetbrains.annotations.NotNull()
    private final com.varun.akva.intelligence.AKVAExecutor executor = null;
    @org.jetbrains.annotations.NotNull()
    private final com.varun.akva.intelligence.AKVAResponseEngine responseEngine = null;
    @org.jetbrains.annotations.NotNull()
    private final com.varun.akva.intelligence.AKVAActionFeedback feedback = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.CoroutineScope scope = null;
    
    public AKVAConversationService(@org.jetbrains.annotations.NotNull()
    com.varun.akva.intelligence.GeminiEngine geminiEngine, @org.jetbrains.annotations.NotNull()
    com.varun.akva.intelligence.AKVAMemoryEngine memoryEngine, @org.jetbrains.annotations.NotNull()
    com.varun.akva.intelligence.AKVAAutonomousAgent autonomousAgent, @org.jetbrains.annotations.NotNull()
    com.varun.akva.intelligence.AKVAExecutor executor, @org.jetbrains.annotations.NotNull()
    com.varun.akva.intelligence.AKVAResponseEngine responseEngine, @org.jetbrains.annotations.NotNull()
    com.varun.akva.intelligence.AKVAActionFeedback feedback) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object onUserSpeaks(@org.jetbrains.annotations.NotNull()
    java.lang.String userText, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.String> $completion) {
        return null;
    }
    
    private final boolean isActionRequest(java.lang.String text) {
        return false;
    }
}