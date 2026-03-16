package com.varun.akva;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0015\u001a\u00020\u0016H\u0016R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\u00020\u0010X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014\u00a8\u0006\u0017"}, d2 = {"Lcom/varun/akva/AKVAApplication;", "Landroid/app/Application;", "()V", "database", "Lcom/varun/akva/data/AkvaDatabase;", "getDatabase", "()Lcom/varun/akva/data/AkvaDatabase;", "setDatabase", "(Lcom/varun/akva/data/AkvaDatabase;)V", "repository", "Lcom/varun/akva/data/AkvaRepository;", "getRepository", "()Lcom/varun/akva/data/AkvaRepository;", "setRepository", "(Lcom/varun/akva/data/AkvaRepository;)V", "settingsManager", "Lcom/varun/akva/data/SettingsManager;", "getSettingsManager", "()Lcom/varun/akva/data/SettingsManager;", "setSettingsManager", "(Lcom/varun/akva/data/SettingsManager;)V", "onCreate", "", "app_debug"})
public final class AKVAApplication extends android.app.Application {
    public com.varun.akva.data.AkvaDatabase database;
    public com.varun.akva.data.AkvaRepository repository;
    public com.varun.akva.data.SettingsManager settingsManager;
    
    public AKVAApplication() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.varun.akva.data.AkvaDatabase getDatabase() {
        return null;
    }
    
    public final void setDatabase(@org.jetbrains.annotations.NotNull()
    com.varun.akva.data.AkvaDatabase p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.varun.akva.data.AkvaRepository getRepository() {
        return null;
    }
    
    public final void setRepository(@org.jetbrains.annotations.NotNull()
    com.varun.akva.data.AkvaRepository p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.varun.akva.data.SettingsManager getSettingsManager() {
        return null;
    }
    
    public final void setSettingsManager(@org.jetbrains.annotations.NotNull()
    com.varun.akva.data.SettingsManager p0) {
    }
    
    @java.lang.Override()
    public void onCreate() {
    }
}