package com.varun.akva.data;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\'\u0018\u0000 \r2\u00020\u0001:\u0001\rB\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H&J\b\u0010\u0005\u001a\u00020\u0006H&J\b\u0010\u0007\u001a\u00020\bH&J\b\u0010\t\u001a\u00020\nH&J\b\u0010\u000b\u001a\u00020\fH&\u00a8\u0006\u000e"}, d2 = {"Lcom/varun/akva/data/AkvaDatabase;", "Landroidx/room/RoomDatabase;", "()V", "actionHistoryDao", "Lcom/varun/akva/data/ActionHistoryDao;", "conversationDao", "Lcom/varun/akva/data/ConversationDao;", "personalityDao", "Lcom/varun/akva/data/AppPersonalityDao;", "usageDao", "Lcom/varun/akva/data/AppUsageDao;", "userProfileDao", "Lcom/varun/akva/data/UserProfileDao;", "Companion", "app_debug"})
@androidx.room.Database(entities = {com.varun.akva.data.AppUsageEvent.class, com.varun.akva.data.AppPersonality.class, com.varun.akva.data.UserProfile.class, com.varun.akva.data.ConversationMemory.class, com.varun.akva.data.ActionHistory.class}, version = 2, exportSchema = false)
public abstract class AkvaDatabase extends androidx.room.RoomDatabase {
    @kotlin.jvm.Volatile()
    @org.jetbrains.annotations.Nullable()
    private static volatile com.varun.akva.data.AkvaDatabase INSTANCE;
    @org.jetbrains.annotations.NotNull()
    public static final com.varun.akva.data.AkvaDatabase.Companion Companion = null;
    
    public AkvaDatabase() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.varun.akva.data.AppUsageDao usageDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.varun.akva.data.AppPersonalityDao personalityDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.varun.akva.data.UserProfileDao userProfileDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.varun.akva.data.ConversationDao conversationDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.varun.akva.data.ActionHistoryDao actionHistoryDao();
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0007R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\b"}, d2 = {"Lcom/varun/akva/data/AkvaDatabase$Companion;", "", "()V", "INSTANCE", "Lcom/varun/akva/data/AkvaDatabase;", "getDatabase", "context", "Landroid/content/Context;", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.varun.akva.data.AkvaDatabase getDatabase(@org.jetbrains.annotations.NotNull()
        android.content.Context context) {
            return null;
        }
    }
}