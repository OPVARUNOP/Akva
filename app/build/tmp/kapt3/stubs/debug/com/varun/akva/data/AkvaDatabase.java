package com.varun.akva.data;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\'\u0018\u0000 \t2\u00020\u0001:\u0001\tB\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H&J\b\u0010\u0005\u001a\u00020\u0006H&J\b\u0010\u0007\u001a\u00020\bH&\u00a8\u0006\n"}, d2 = {"Lcom/varun/akva/data/AkvaDatabase;", "Landroidx/room/RoomDatabase;", "()V", "actionDao", "Lcom/varun/akva/data/ActionDao;", "conversationDao", "Lcom/varun/akva/data/ConversationDao;", "userProfileDao", "Lcom/varun/akva/data/UserProfileDao;", "Companion", "app_debug"})
@androidx.room.Database(entities = {com.varun.akva.data.UserProfile.class, com.varun.akva.data.ConversationMemory.class, com.varun.akva.data.ActionHistory.class}, version = 4, exportSchema = false)
public abstract class AkvaDatabase extends androidx.room.RoomDatabase {
    @org.jetbrains.annotations.NotNull()
    public static final com.varun.akva.data.AkvaDatabase.Companion Companion = null;
    
    public AkvaDatabase() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.varun.akva.data.UserProfileDao userProfileDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.varun.akva.data.ConversationDao conversationDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.varun.akva.data.ActionDao actionDao();
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/varun/akva/data/AkvaDatabase$Companion;", "", "()V", "getDatabase", "Lcom/varun/akva/data/AkvaDatabase;", "ctx", "Landroid/content/Context;", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.varun.akva.data.AkvaDatabase getDatabase(@org.jetbrains.annotations.NotNull()
        android.content.Context ctx) {
            return null;
        }
    }
}