package com.varun.akva.data;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u001a\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\b2\u0006\u0010\u000b\u001a\u00020\fJ\u001c\u0010\r\u001a\b\u0012\u0004\u0012\u00020\n0\t2\u0006\u0010\u000b\u001a\u00020\fH\u0086@\u00a2\u0006\u0002\u0010\u000eJ\u0016\u0010\u000f\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0086@\u00a2\u0006\u0002\u0010\u0012J\b\u0010\u0013\u001a\u00020\u0014H\u0002J.\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0017\u001a\u00020\f2\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u0011H\u0086@\u00a2\u0006\u0002\u0010\u001bR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001c"}, d2 = {"Lcom/varun/akva/data/AkvaRepository;", "", "usageDao", "Lcom/varun/akva/data/AppUsageDao;", "personalityDao", "Lcom/varun/akva/data/AppPersonalityDao;", "(Lcom/varun/akva/data/AppUsageDao;Lcom/varun/akva/data/AppPersonalityDao;)V", "getRecentEvents", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/varun/akva/data/AppUsageEvent;", "limit", "", "getRecentEventsList", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getTimesOpenedToday", "packageName", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getTodayStart", "", "logEvent", "", "stressScore", "isNight", "", "aiResponse", "(Ljava/lang/String;IZLjava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public final class AkvaRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.varun.akva.data.AppUsageDao usageDao = null;
    @org.jetbrains.annotations.NotNull()
    private final com.varun.akva.data.AppPersonalityDao personalityDao = null;
    
    public AkvaRepository(@org.jetbrains.annotations.NotNull()
    com.varun.akva.data.AppUsageDao usageDao, @org.jetbrains.annotations.NotNull()
    com.varun.akva.data.AppPersonalityDao personalityDao) {
        super();
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