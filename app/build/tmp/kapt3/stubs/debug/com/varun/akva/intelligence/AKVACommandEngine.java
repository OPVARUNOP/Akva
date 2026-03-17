package com.varun.akva.intelligence;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\u0002\u0010\u0006J\b\u0010\u0007\u001a\u00020\bH\u0003J\u0012\u0010\t\u001a\u00020\b2\b\u0010\n\u001a\u0004\u0018\u00010\bH\u0002J\u0018\u0010\u000b\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\bH\u0002J\b\u0010\u000f\u001a\u00020\bH\u0002J\b\u0010\u0010\u001a\u00020\bH\u0002J\b\u0010\u0011\u001a\u00020\bH\u0003J\u0010\u0010\u0012\u001a\u00020\b2\u0006\u0010\u0013\u001a\u00020\u0014H\u0007J\u001c\u0010\u0015\u001a\u00020\b2\b\u0010\n\u001a\u0004\u0018\u00010\b2\b\u0010\u0016\u001a\u0004\u0018\u00010\bH\u0002J\u0012\u0010\u0017\u001a\u00020\b2\b\u0010\u0018\u001a\u0004\u0018\u00010\bH\u0002J\u0012\u0010\u0019\u001a\u00020\b2\b\u0010\u001a\u001a\u0004\u0018\u00010\bH\u0002J\b\u0010\u001b\u001a\u00020\bH\u0002J\u0018\u0010\u001c\u001a\u00020\b2\u0006\u0010\u001d\u001a\u00020\b2\u0006\u0010\u001e\u001a\u00020\bH\u0002J\b\u0010\u001f\u001a\u00020\bH\u0002J\u0012\u0010 \u001a\u00020\b2\b\u0010!\u001a\u0004\u0018\u00010\bH\u0002J\u0010\u0010\"\u001a\u00020\b2\u0006\u0010#\u001a\u00020\rH\u0002J\u0012\u0010$\u001a\u00020\b2\b\u0010%\u001a\u0004\u0018\u00010\bH\u0002J\b\u0010&\u001a\u00020\bH\u0002J\u0010\u0010\'\u001a\u00020\b2\u0006\u0010(\u001a\u00020)H\u0002J\b\u0010*\u001a\u00020\bH\u0002R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006+"}, d2 = {"Lcom/varun/akva/intelligence/AKVACommandEngine;", "", "context", "Landroid/content/Context;", "accessibilityService", "Landroid/accessibilityservice/AccessibilityService;", "(Landroid/content/Context;Landroid/accessibilityservice/AccessibilityService;)V", "answerCall", "", "callContact", "contactName", "changeVolume", "direction", "", "response", "checkBattery", "checkTime", "endCall", "execute", "command", "Lcom/varun/akva/intelligence/CommandResult;", "messageContact", "message", "navigate", "destination", "openApp", "appName", "openCamera", "openSettings", "action", "name", "playMusic", "searchWeb", "query", "sendMediaKey", "keyCode", "setAlarm", "timeStr", "takeScreenshot", "toggleFlashlight", "on", "", "toggleMedia", "app_debug"})
public final class AKVACommandEngine {
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    @org.jetbrains.annotations.Nullable()
    private final android.accessibilityservice.AccessibilityService accessibilityService = null;
    
    public AKVACommandEngine(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.Nullable()
    android.accessibilityservice.AccessibilityService accessibilityService) {
        super();
    }
    
    @android.annotation.SuppressLint(value = {"MissingPermission"})
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String execute(@org.jetbrains.annotations.NotNull()
    com.varun.akva.intelligence.CommandResult command) {
        return null;
    }
    
    private final java.lang.String openApp(java.lang.String appName) {
        return null;
    }
    
    private final java.lang.String callContact(java.lang.String contactName) {
        return null;
    }
    
    private final java.lang.String messageContact(java.lang.String contactName, java.lang.String message) {
        return null;
    }
    
    private final java.lang.String setAlarm(java.lang.String timeStr) {
        return null;
    }
    
    private final java.lang.String searchWeb(java.lang.String query) {
        return null;
    }
    
    private final java.lang.String navigate(java.lang.String destination) {
        return null;
    }
    
    private final java.lang.String playMusic() {
        return null;
    }
    
    private final java.lang.String toggleMedia() {
        return null;
    }
    
    private final java.lang.String sendMediaKey(int keyCode) {
        return null;
    }
    
    @android.annotation.SuppressLint(value = {"MissingPermission"})
    private final java.lang.String answerCall() {
        return null;
    }
    
    @android.annotation.SuppressLint(value = {"MissingPermission"})
    private final java.lang.String endCall() {
        return null;
    }
    
    private final java.lang.String openCamera() {
        return null;
    }
    
    private final java.lang.String openSettings(java.lang.String action, java.lang.String name) {
        return null;
    }
    
    private final java.lang.String changeVolume(int direction, java.lang.String response) {
        return null;
    }
    
    private final java.lang.String toggleFlashlight(boolean on) {
        return null;
    }
    
    private final java.lang.String takeScreenshot() {
        return null;
    }
    
    private final java.lang.String checkBattery() {
        return null;
    }
    
    private final java.lang.String checkTime() {
        return null;
    }
}