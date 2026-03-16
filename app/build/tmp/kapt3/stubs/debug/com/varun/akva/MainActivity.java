package com.varun.akva;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0015\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010!\u001a\u00020\u0004H\u0002J\b\u0010\"\u001a\u00020\u0004H\u0002J\u0012\u0010#\u001a\u00020$2\b\u0010%\u001a\u0004\u0018\u00010&H\u0014J\b\u0010\'\u001a\u00020$H\u0014J\b\u0010(\u001a\u00020$H\u0002J\b\u0010)\u001a\u00020$H\u0002J\b\u0010*\u001a\u00020$H\u0002R+\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u00048B@BX\u0082\u008e\u0002\u00a2\u0006\u0012\n\u0004\b\n\u0010\u000b\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001c\u0010\f\u001a\u0010\u0012\f\u0012\n \u000f*\u0004\u0018\u00010\u000e0\u000e0\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000R+\u0010\u0010\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u00048B@BX\u0082\u008e\u0002\u00a2\u0006\u0012\n\u0004\b\u0013\u0010\u000b\u001a\u0004\b\u0011\u0010\u0007\"\u0004\b\u0012\u0010\tR+\u0010\u0014\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u00048B@BX\u0082\u008e\u0002\u00a2\u0006\u0012\n\u0004\b\u0017\u0010\u000b\u001a\u0004\b\u0015\u0010\u0007\"\u0004\b\u0016\u0010\tR+\u0010\u0018\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u00048B@BX\u0082\u008e\u0002\u00a2\u0006\u0012\n\u0004\b\u001b\u0010\u000b\u001a\u0004\b\u0019\u0010\u0007\"\u0004\b\u001a\u0010\tR\u001c\u0010\u001c\u001a\u0010\u0012\f\u0012\n \u000f*\u0004\u0018\u00010\u000e0\u000e0\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000R+\u0010\u001d\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u00048B@BX\u0082\u008e\u0002\u00a2\u0006\u0012\n\u0004\b \u0010\u000b\u001a\u0004\b\u001e\u0010\u0007\"\u0004\b\u001f\u0010\t\u00a8\u0006+"}, d2 = {"Lcom/varun/akva/MainActivity;", "Landroidx/activity/ComponentActivity;", "()V", "<set-?>", "", "accessibilityGranted", "getAccessibilityGranted", "()Z", "setAccessibilityGranted", "(Z)V", "accessibilityGranted$delegate", "Landroidx/compose/runtime/MutableState;", "micPermLauncher", "Landroidx/activity/result/ActivityResultLauncher;", "", "kotlin.jvm.PlatformType", "microphoneGranted", "getMicrophoneGranted", "setMicrophoneGranted", "microphoneGranted$delegate", "notificationGranted", "getNotificationGranted", "setNotificationGranted", "notificationGranted$delegate", "overlayGranted", "getOverlayGranted", "setOverlayGranted", "overlayGranted$delegate", "phonePermLauncher", "phoneStateGranted", "getPhoneStateGranted", "setPhoneStateGranted", "phoneStateGranted$delegate", "isAccessibilityEnabled", "isNotificationListenerEnabled", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onResume", "refreshPermissions", "requestMissingPermissions", "startBackgroundService", "app_debug"})
public final class MainActivity extends androidx.activity.ComponentActivity {
    @org.jetbrains.annotations.NotNull()
    private final androidx.compose.runtime.MutableState accessibilityGranted$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.compose.runtime.MutableState notificationGranted$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.compose.runtime.MutableState overlayGranted$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.compose.runtime.MutableState microphoneGranted$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.compose.runtime.MutableState phoneStateGranted$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.activity.result.ActivityResultLauncher<java.lang.String> micPermLauncher = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.activity.result.ActivityResultLauncher<java.lang.String> phonePermLauncher = null;
    
    public MainActivity() {
        super();
    }
    
    private final boolean getAccessibilityGranted() {
        return false;
    }
    
    private final void setAccessibilityGranted(boolean p0) {
    }
    
    private final boolean getNotificationGranted() {
        return false;
    }
    
    private final void setNotificationGranted(boolean p0) {
    }
    
    private final boolean getOverlayGranted() {
        return false;
    }
    
    private final void setOverlayGranted(boolean p0) {
    }
    
    private final boolean getMicrophoneGranted() {
        return false;
    }
    
    private final void setMicrophoneGranted(boolean p0) {
    }
    
    private final boolean getPhoneStateGranted() {
        return false;
    }
    
    private final void setPhoneStateGranted(boolean p0) {
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
    
    private final void requestMissingPermissions() {
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