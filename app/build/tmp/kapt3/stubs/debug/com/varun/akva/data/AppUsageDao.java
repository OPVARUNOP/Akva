package com.varun.akva.data;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\n\bg\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u001c\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\n\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u001c\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\f\u001a\u00020\rH\u00a7@\u00a2\u0006\u0002\u0010\u000eJ&\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\b2\u0006\u0010\n\u001a\u00020\u00052\b\b\u0002\u0010\f\u001a\u00020\rH\u00a7@\u00a2\u0006\u0002\u0010\u0011J\u001e\u0010\u0012\u001a\u00020\r2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\n\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0015J\u001c\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\n\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J$\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\u0018\u001a\u00020\r2\u0006\u0010\n\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0019J$\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\n\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0015J\u0016\u0010\u001b\u001a\u00020\u00032\u0006\u0010\u001c\u001a\u00020\tH\u00a7@\u00a2\u0006\u0002\u0010\u001d\u00a8\u0006\u001e"}, d2 = {"Lcom/varun/akva/data/AppUsageDao;", "", "deleteOlderThan", "", "timestamp", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getLateNightUsage", "", "Lcom/varun/akva/data/AppUsageEvent;", "since", "getLatestEvents", "limit", "", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getMostUsedApps", "Lcom/varun/akva/data/AppCount;", "(JILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getOpenCount", "pkg", "", "(Ljava/lang/String;JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getRecentUsage", "getStressEvents", "minStress", "(IJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getUsageByPackage", "insertUsageEvent", "event", "(Lcom/varun/akva/data/AppUsageEvent;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
@androidx.room.Dao()
public abstract interface AppUsageDao {
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertUsageEvent(@org.jetbrains.annotations.NotNull()
    com.varun.akva.data.AppUsageEvent event, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM app_usage_events WHERE timestamp >= :since ORDER BY timestamp DESC")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getRecentUsage(long since, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.varun.akva.data.AppUsageEvent>> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM app_usage_events WHERE isNightUsage = 1 AND timestamp >= :since ORDER BY timestamp DESC")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getLateNightUsage(long since, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.varun.akva.data.AppUsageEvent>> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM app_usage_events WHERE packageName = :pkg AND timestamp >= :since ORDER BY timestamp DESC")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getUsageByPackage(@org.jetbrains.annotations.NotNull()
    java.lang.String pkg, long since, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.varun.akva.data.AppUsageEvent>> $completion);
    
    @androidx.room.Query(value = "SELECT COUNT(*) FROM app_usage_events WHERE packageName = :pkg AND timestamp >= :since")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getOpenCount(@org.jetbrains.annotations.NotNull()
    java.lang.String pkg, long since, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion);
    
    @androidx.room.Query(value = "SELECT packageName, COUNT(*) as cnt FROM app_usage_events WHERE timestamp >= :since GROUP BY packageName ORDER BY cnt DESC LIMIT :limit")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getMostUsedApps(long since, int limit, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.varun.akva.data.AppCount>> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM app_usage_events WHERE stressScore >= :minStress AND timestamp >= :since")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getStressEvents(int minStress, long since, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.varun.akva.data.AppUsageEvent>> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM app_usage_events ORDER BY timestamp DESC LIMIT :limit")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getLatestEvents(int limit, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.varun.akva.data.AppUsageEvent>> $completion);
    
    @androidx.room.Query(value = "DELETE FROM app_usage_events WHERE timestamp < :timestamp")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteOlderThan(long timestamp, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 3, xi = 48)
    public static final class DefaultImpls {
    }
}