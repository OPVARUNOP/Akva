package com.varun.akva.data;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000t\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\t\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u00a2\u0006\u0002\u0010\fJ\u001c\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\u0006\u0010\u0010\u001a\u00020\u0011H\u0086@\u00a2\u0006\u0002\u0010\u0012J\u001c\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00140\u000e2\u0006\u0010\u0010\u001a\u00020\u0011H\u0086@\u00a2\u0006\u0002\u0010\u0012J\u001a\u0010\u0015\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00170\u000e0\u00162\u0006\u0010\u0010\u001a\u00020\u0011J\u001c\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00170\u000e2\u0006\u0010\u0010\u001a\u00020\u0011H\u0086@\u00a2\u0006\u0002\u0010\u0012J\u0016\u0010\u0019\u001a\u00020\u00112\u0006\u0010\u001a\u001a\u00020\u001bH\u0086@\u00a2\u0006\u0002\u0010\u001cJ\b\u0010\u001d\u001a\u00020\u001eH\u0002J\u0010\u0010\u001f\u001a\u0004\u0018\u00010 H\u0086@\u00a2\u0006\u0002\u0010!J.\u0010\"\u001a\u00020#2\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010$\u001a\u00020\u00112\u0006\u0010%\u001a\u00020&2\u0006\u0010\'\u001a\u00020\u001bH\u0086@\u00a2\u0006\u0002\u0010(J\u0016\u0010)\u001a\u00020#2\u0006\u0010*\u001a\u00020\u0014H\u0086@\u00a2\u0006\u0002\u0010+J\u0016\u0010,\u001a\u00020#2\u0006\u0010-\u001a\u00020 H\u0086@\u00a2\u0006\u0002\u0010.R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006/"}, d2 = {"Lcom/varun/akva/data/AkvaRepository;", "", "usageDao", "Lcom/varun/akva/data/AppUsageDao;", "personalityDao", "Lcom/varun/akva/data/AppPersonalityDao;", "userProfileDao", "Lcom/varun/akva/data/UserProfileDao;", "conversationDao", "Lcom/varun/akva/data/ConversationDao;", "actionHistoryDao", "Lcom/varun/akva/data/ActionHistoryDao;", "(Lcom/varun/akva/data/AppUsageDao;Lcom/varun/akva/data/AppPersonalityDao;Lcom/varun/akva/data/UserProfileDao;Lcom/varun/akva/data/ConversationDao;Lcom/varun/akva/data/ActionHistoryDao;)V", "getRecentActions", "", "Lcom/varun/akva/data/ActionHistory;", "limit", "", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getRecentConversations", "Lcom/varun/akva/data/ConversationMemory;", "getRecentEvents", "Lkotlinx/coroutines/flow/Flow;", "Lcom/varun/akva/data/AppUsageEvent;", "getRecentEventsList", "getTimesOpenedToday", "packageName", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getTodayStart", "", "getUserProfile", "Lcom/varun/akva/data/UserProfile;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "logEvent", "", "stressScore", "isNight", "", "aiResponse", "(Ljava/lang/String;IZLjava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "saveConversation", "memory", "(Lcom/varun/akva/data/ConversationMemory;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "saveUserProfile", "profile", "(Lcom/varun/akva/data/UserProfile;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public final class AkvaRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.varun.akva.data.AppUsageDao usageDao = null;
    @org.jetbrains.annotations.NotNull()
    private final com.varun.akva.data.AppPersonalityDao personalityDao = null;
    @org.jetbrains.annotations.NotNull()
    private final com.varun.akva.data.UserProfileDao userProfileDao = null;
    @org.jetbrains.annotations.NotNull()
    private final com.varun.akva.data.ConversationDao conversationDao = null;
    @org.jetbrains.annotations.NotNull()
    private final com.varun.akva.data.ActionHistoryDao actionHistoryDao = null;
    
    public AkvaRepository(@org.jetbrains.annotations.NotNull()
    com.varun.akva.data.AppUsageDao usageDao, @org.jetbrains.annotations.NotNull()
    com.varun.akva.data.AppPersonalityDao personalityDao, @org.jetbrains.annotations.NotNull()
    com.varun.akva.data.UserProfileDao userProfileDao, @org.jetbrains.annotations.NotNull()
    com.varun.akva.data.ConversationDao conversationDao, @org.jetbrains.annotations.NotNull()
    com.varun.akva.data.ActionHistoryDao actionHistoryDao) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getUserProfile(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.varun.akva.data.UserProfile> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object saveUserProfile(@org.jetbrains.annotations.NotNull()
    com.varun.akva.data.UserProfile profile, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getRecentConversations(int limit, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.varun.akva.data.ConversationMemory>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object saveConversation(@org.jetbrains.annotations.NotNull()
    com.varun.akva.data.ConversationMemory memory, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getRecentActions(int limit, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.varun.akva.data.ActionHistory>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object logEvent(@org.jetbrains.annotations.NotNull()
    java.lang.String packageName, int stressScore, boolean isNight, @org.jetbrains.annotations.NotNull()
    java.lang.String aiResponse, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.varun.akva.data.AppUsageEvent>> getRecentEvents(int limit) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getRecentEventsList(int limit, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.varun.akva.data.AppUsageEvent>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getTimesOpenedToday(@org.jetbrains.annotations.NotNull()
    java.lang.String packageName, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion) {
        return null;
    }
    
    private final long getTodayStart() {
        return 0L;
    }
}