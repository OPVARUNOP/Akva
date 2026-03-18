package com.varun.akva.intelligence;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\b\n\u0000\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u00a2\u0006\u0002\u0010\nJ*\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u00110\u0010H\u0086@\u00a2\u0006\u0002\u0010\u0012J$\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00142\u0012\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\f0\u0017H\u0002R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0019"}, d2 = {"Lcom/varun/akva/intelligence/AKVAExecutor;", "", "context", "Landroid/content/Context;", "commandExecutor", "Lcom/varun/akva/services/AKVACommandExecutor;", "automationService", "Lcom/varun/akva/services/AKVAAutomationService;", "webSearch", "Lcom/varun/akva/intelligence/AKVAWebSearch;", "(Landroid/content/Context;Lcom/varun/akva/services/AKVACommandExecutor;Lcom/varun/akva/services/AKVAAutomationService;Lcom/varun/akva/intelligence/AKVAWebSearch;)V", "execute", "", "plan", "Lcom/varun/akva/intelligence/AkvaPlan;", "onStatus", "Lkotlin/Function1;", "", "(Lcom/varun/akva/intelligence/AkvaPlan;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "injectContext", "Lorg/json/JSONObject;", "params", "results", "", "", "app_debug"})
public final class AKVAExecutor {
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull()
    private final com.varun.akva.services.AKVACommandExecutor commandExecutor = null;
    @org.jetbrains.annotations.NotNull()
    private final com.varun.akva.services.AKVAAutomationService automationService = null;
    @org.jetbrains.annotations.NotNull()
    private final com.varun.akva.intelligence.AKVAWebSearch webSearch = null;
    
    public AKVAExecutor(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    com.varun.akva.services.AKVACommandExecutor commandExecutor, @org.jetbrains.annotations.NotNull()
    com.varun.akva.services.AKVAAutomationService automationService, @org.jetbrains.annotations.NotNull()
    com.varun.akva.intelligence.AKVAWebSearch webSearch) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object execute(@org.jetbrains.annotations.NotNull()
    com.varun.akva.intelligence.AkvaPlan plan, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> onStatus, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.String> $completion) {
        return null;
    }
    
    private final org.json.JSONObject injectContext(org.json.JSONObject params, java.util.Map<java.lang.Integer, java.lang.String> results) {
        return null;
    }
}