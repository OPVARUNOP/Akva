package com.varun.akva.data;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\u0016\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0086@\u00a2\u0006\u0002\u0010\rJ\u0016\u0010\u000e\u001a\u00020\n2\u0006\u0010\u000f\u001a\u00020\u0010H\u0086@\u00a2\u0006\u0002\u0010\u0011J\u001a\u0010\u0012\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u00140\u00132\u0006\u0010\u0015\u001a\u00020\u0016J\u001a\u0010\u0017\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u00140\u00132\u0006\u0010\u0015\u001a\u00020\u0016J\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0086@\u00a2\u0006\u0002\u0010\u001aJ\u0016\u0010\u001b\u001a\u00020\n2\u0006\u0010\u001c\u001a\u00020\u0019H\u0086@\u00a2\u0006\u0002\u0010\u001dR\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001e"}, d2 = {"Lcom/varun/akva/data/AkvaRepository;", "", "profileDao", "Lcom/varun/akva/data/UserProfileDao;", "convDao", "Lcom/varun/akva/data/ConversationDao;", "actionDao", "Lcom/varun/akva/data/ActionDao;", "(Lcom/varun/akva/data/UserProfileDao;Lcom/varun/akva/data/ConversationDao;Lcom/varun/akva/data/ActionDao;)V", "addAction", "", "a", "Lcom/varun/akva/data/ActionHistory;", "(Lcom/varun/akva/data/ActionHistory;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "addMemory", "m", "Lcom/varun/akva/data/ConversationMemory;", "(Lcom/varun/akva/data/ConversationMemory;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getRecentActions", "Lkotlinx/coroutines/flow/Flow;", "", "l", "", "getRecentHistory", "getUserProfile", "Lcom/varun/akva/data/UserProfile;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "saveUserProfile", "p", "(Lcom/varun/akva/data/UserProfile;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public final class AkvaRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.varun.akva.data.UserProfileDao profileDao = null;
    @org.jetbrains.annotations.NotNull()
    private final com.varun.akva.data.ConversationDao convDao = null;
    @org.jetbrains.annotations.NotNull()
    private final com.varun.akva.data.ActionDao actionDao = null;
    
    public AkvaRepository(@org.jetbrains.annotations.NotNull()
    com.varun.akva.data.UserProfileDao profileDao, @org.jetbrains.annotations.NotNull()
    com.varun.akva.data.ConversationDao convDao, @org.jetbrains.annotations.NotNull()
    com.varun.akva.data.ActionDao actionDao) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getUserProfile(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.varun.akva.data.UserProfile> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object saveUserProfile(@org.jetbrains.annotations.NotNull()
    com.varun.akva.data.UserProfile p, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.varun.akva.data.ConversationMemory>> getRecentHistory(int l) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object addMemory(@org.jetbrains.annotations.NotNull()
    com.varun.akva.data.ConversationMemory m, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.varun.akva.data.ActionHistory>> getRecentActions(int l) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object addAction(@org.jetbrains.annotations.NotNull()
    com.varun.akva.data.ActionHistory a, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
}