package com.varun.akva.data;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R$\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00068F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR$\u0010\f\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00068F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b\r\u0010\t\"\u0004\b\u000e\u0010\u000bR$\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0005\u001a\u00020\u000f8F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R$\u0010\u0014\u001a\u00020\u000f2\u0006\u0010\u0005\u001a\u00020\u000f8F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b\u0014\u0010\u0011\"\u0004\b\u0015\u0010\u0013R$\u0010\u0016\u001a\u00020\u000f2\u0006\u0010\u0005\u001a\u00020\u000f8F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b\u0016\u0010\u0011\"\u0004\b\u0017\u0010\u0013R\u0016\u0010\u0018\u001a\n \u001a*\u0004\u0018\u00010\u00190\u0019X\u0082\u0004\u00a2\u0006\u0002\n\u0000R$\u0010\u001b\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00068F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b\u001c\u0010\t\"\u0004\b\u001d\u0010\u000bR$\u0010\u001e\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00068F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b\u001f\u0010\t\"\u0004\b \u0010\u000b\u00a8\u0006!"}, d2 = {"Lcom/varun/akva/data/SettingsManager;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "v", "", "backendUrl", "getBackendUrl", "()Ljava/lang/String;", "setBackendUrl", "(Ljava/lang/String;)V", "geminiApiKey", "getGeminiApiKey", "setGeminiApiKey", "", "isProactiveEnabled", "()Z", "setProactiveEnabled", "(Z)V", "isVoiceEnabled", "setVoiceEnabled", "isWakeWordEnabled", "setWakeWordEnabled", "p", "Landroid/content/SharedPreferences;", "kotlin.jvm.PlatformType", "searchApiKey", "getSearchApiKey", "setSearchApiKey", "searchCx", "getSearchCx", "setSearchCx", "app_debug"})
public final class SettingsManager {
    private final android.content.SharedPreferences p = null;
    
    public SettingsManager(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    public final boolean isVoiceEnabled() {
        return false;
    }
    
    public final void setVoiceEnabled(boolean v) {
    }
    
    public final boolean isWakeWordEnabled() {
        return false;
    }
    
    public final void setWakeWordEnabled(boolean v) {
    }
    
    public final boolean isProactiveEnabled() {
        return false;
    }
    
    public final void setProactiveEnabled(boolean v) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getBackendUrl() {
        return null;
    }
    
    public final void setBackendUrl(@org.jetbrains.annotations.NotNull()
    java.lang.String v) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getGeminiApiKey() {
        return null;
    }
    
    public final void setGeminiApiKey(@org.jetbrains.annotations.NotNull()
    java.lang.String v) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getSearchApiKey() {
        return null;
    }
    
    public final void setSearchApiKey(@org.jetbrains.annotations.NotNull()
    java.lang.String v) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getSearchCx() {
        return null;
    }
    
    public final void setSearchCx(@org.jetbrains.annotations.NotNull()
    java.lang.String v) {
    }
}