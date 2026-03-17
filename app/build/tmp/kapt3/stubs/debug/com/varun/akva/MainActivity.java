package com.varun.akva;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0015\u001a\u00020\tH\u0002J\b\u0010\u0016\u001a\u00020\tH\u0002J\u0012\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0014J\b\u0010\u001b\u001a\u00020\u0018H\u0014J\b\u0010\u001c\u001a\u00020\u0018H\u0002J\b\u0010\u001d\u001a\u00020\u0018H\u0002R\u001a\u0010\u0003\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000RC\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\t0\b2\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\t0\b8B@BX\u0082\u008e\u0002\u00a2\u0006\u0012\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u000e\u0010\u0011\u001a\u00020\u0012X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001e"}, d2 = {"Lcom/varun/akva/MainActivity;", "Landroidx/activity/ComponentActivity;", "()V", "permissionLauncher", "Landroidx/activity/result/ActivityResultLauncher;", "", "", "<set-?>", "", "", "permsStatus", "getPermsStatus", "()Ljava/util/Map;", "setPermsStatus", "(Ljava/util/Map;)V", "permsStatus$delegate", "Landroidx/compose/runtime/MutableState;", "repository", "Lcom/varun/akva/data/AkvaRepository;", "settingsManager", "Lcom/varun/akva/data/SettingsManager;", "isAccessibilityEnabled", "isNotificationListenerEnabled", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onResume", "refreshPermissions", "startBackgroundService", "app_debug"})
public final class MainActivity extends androidx.activity.ComponentActivity {
    @org.jetbrains.annotations.NotNull()
    private final androidx.compose.runtime.MutableState permsStatus$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.activity.result.ActivityResultLauncher<java.lang.String[]> permissionLauncher = null;
    private com.varun.akva.data.AkvaRepository repository;
    private com.varun.akva.data.SettingsManager settingsManager;
    
    public MainActivity() {
        super();
    }
    
    private final java.util.Map<java.lang.String, java.lang.Boolean> getPermsStatus() {
        return null;
    }
    
    private final void setPermsStatus(java.util.Map<java.lang.String, java.lang.Boolean> p0) {
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override()
    protected void onResume() {
    }
    
    private final void refreshPermissions() {
    }
    
    private final boolean isAccessibilityEnabled() {
        return false;
    }
    
    private final boolean isNotificationListenerEnabled() {
        return false;
    }
    
    private final void startBackgroundService() {
    }
}