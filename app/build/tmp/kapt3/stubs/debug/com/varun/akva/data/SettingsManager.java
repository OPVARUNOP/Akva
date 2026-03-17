package com.varun.akva.data;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0017\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0012\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0006\u0010=\u001a\u00020>J\u000e\u0010?\u001a\u00020\f2\u0006\u0010@\u001a\u00020+R$\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00068F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR$\u0010\r\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\f8F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R$\u0010\u0012\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\f8F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b\u0012\u0010\u000f\"\u0004\b\u0013\u0010\u0011R$\u0010\u0014\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00068F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b\u0015\u0010\t\"\u0004\b\u0016\u0010\u000bR$\u0010\u0017\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00068F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b\u0018\u0010\t\"\u0004\b\u0019\u0010\u000bR$\u0010\u001a\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\f8F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b\u001b\u0010\u000f\"\u0004\b\u001c\u0010\u0011R$\u0010\u001d\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\f8F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b\u001e\u0010\u000f\"\u0004\b\u001f\u0010\u0011R$\u0010 \u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\f8F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b!\u0010\u000f\"\u0004\b\"\u0010\u0011R\u000e\u0010#\u001a\u00020$X\u0082\u0004\u00a2\u0006\u0002\n\u0000R$\u0010%\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\f8F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b&\u0010\u000f\"\u0004\b\'\u0010\u0011R$\u0010(\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\f8F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b)\u0010\u000f\"\u0004\b*\u0010\u0011R$\u0010,\u001a\u00020+2\u0006\u0010\u0005\u001a\u00020+8F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b-\u0010.\"\u0004\b/\u00100R$\u00101\u001a\u00020+2\u0006\u0010\u0005\u001a\u00020+8F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b2\u0010.\"\u0004\b3\u00100R$\u00104\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00068F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b5\u0010\t\"\u0004\b6\u0010\u000bR$\u00107\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\f8F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b8\u0010\u000f\"\u0004\b9\u0010\u0011R$\u0010:\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\f8F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b;\u0010\u000f\"\u0004\b<\u0010\u0011\u00a8\u0006A"}, d2 = {"Lcom/varun/akva/data/SettingsManager;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "value", "", "backendUrl", "getBackendUrl", "()Ljava/lang/String;", "setBackendUrl", "(Ljava/lang/String;)V", "", "hapticEnabled", "getHapticEnabled", "()Z", "setHapticEnabled", "(Z)V", "isFirstLaunch", "setFirstLaunch", "lastMorningBriefingDate", "getLastMorningBriefingDate", "setLastMorningBriefingDate", "lastWeeklyStoryDate", "getLastWeeklyStoryDate", "setLastWeeklyStoryDate", "masterVoiceEnabled", "getMasterVoiceEnabled", "setMasterVoiceEnabled", "morningBriefingEnabled", "getMorningBriefingEnabled", "setMorningBriefingEnabled", "nightModeEnabled", "getNightModeEnabled", "setNightModeEnabled", "prefs", "Landroid/content/SharedPreferences;", "screenMoodEnabled", "getScreenMoodEnabled", "setScreenMoodEnabled", "settingsGuideEnabled", "getSettingsGuideEnabled", "setSettingsGuideEnabled", "", "silentHourEnd", "getSilentHourEnd", "()I", "setSilentHourEnd", "(I)V", "silentHourStart", "getSilentHourStart", "setSilentHourStart", "voicePersonality", "getVoicePersonality", "setVoicePersonality", "wakeWordEnabled", "getWakeWordEnabled", "setWakeWordEnabled", "weeklyStoryEnabled", "getWeeklyStoryEnabled", "setWeeklyStoryEnabled", "clearLearnedData", "", "isSilentHour", "hour", "app_debug"})
public final class SettingsManager {
    @org.jetbrains.annotations.NotNull()
    private final android.content.SharedPreferences prefs = null;
    
    public SettingsManager(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    public final boolean getMasterVoiceEnabled() {
        return false;
    }
    
    public final void setMasterVoiceEnabled(boolean value) {
    }
    
    public final boolean getNightModeEnabled() {
        return false;
    }
    
    public final void setNightModeEnabled(boolean value) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getVoicePersonality() {
        return null;
    }
    
    public final void setVoicePersonality(@org.jetbrains.annotations.NotNull()
    java.lang.String value) {
    }
    
    public final boolean getHapticEnabled() {
        return false;
    }
    
    public final void setHapticEnabled(boolean value) {
    }
    
    public final boolean getScreenMoodEnabled() {
        return false;
    }
    
    public final void setScreenMoodEnabled(boolean value) {
    }
    
    public final boolean getWakeWordEnabled() {
        return false;
    }
    
    public final void setWakeWordEnabled(boolean value) {
    }
    
    public final int getSilentHourStart() {
        return 0;
    }
    
    public final void setSilentHourStart(int value) {
    }
    
    public final int getSilentHourEnd() {
        return 0;
    }
    
    public final void setSilentHourEnd(int value) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getBackendUrl() {
        return null;
    }
    
    public final void setBackendUrl(@org.jetbrains.annotations.NotNull()
    java.lang.String value) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getLastMorningBriefingDate() {
        return null;
    }
    
    public final void setLastMorningBriefingDate(@org.jetbrains.annotations.NotNull()
    java.lang.String value) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getLastWeeklyStoryDate() {
        return null;
    }
    
    public final void setLastWeeklyStoryDate(@org.jetbrains.annotations.NotNull()
    java.lang.String value) {
    }
    
    public final boolean isFirstLaunch() {
        return false;
    }
    
    public final void setFirstLaunch(boolean value) {
    }
    
    public final boolean getMorningBriefingEnabled() {
        return false;
    }
    
    public final void setMorningBriefingEnabled(boolean value) {
    }
    
    public final boolean getWeeklyStoryEnabled() {
        return false;
    }
    
    public final void setWeeklyStoryEnabled(boolean value) {
    }
    
    public final boolean getSettingsGuideEnabled() {
        return false;
    }
    
    public final void setSettingsGuideEnabled(boolean value) {
    }
    
    public final boolean isSilentHour(int hour) {
        return false;
    }
    
    public final void clearLearnedData() {
    }
}