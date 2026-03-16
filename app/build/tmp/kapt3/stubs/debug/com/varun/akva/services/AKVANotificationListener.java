package com.varun.akva.services;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \f2\u00020\u0001:\u0001\fB\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0005\u001a\u00020\u0006H\u0016J\b\u0010\u0007\u001a\u00020\u0006H\u0016J\u0012\u0010\b\u001a\u00020\u00062\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0016J\u0012\u0010\u000b\u001a\u00020\u00062\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006\r"}, d2 = {"Lcom/varun/akva/services/AKVANotificationListener;", "Landroid/service/notification/NotificationListenerService;", "()V", "contextDetector", "Lcom/varun/akva/intelligence/ContextDetector;", "onCreate", "", "onDestroy", "onNotificationPosted", "sbn", "Landroid/service/notification/StatusBarNotification;", "onNotificationRemoved", "Companion", "app_debug"})
public final class AKVANotificationListener extends android.service.notification.NotificationListenerService {
    private com.varun.akva.intelligence.ContextDetector contextDetector;
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String TAG = "AKVANotifListener";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.Object lock = null;
    @org.jetbrains.annotations.NotNull()
    private static final java.util.concurrent.ConcurrentHashMap<java.lang.String, java.lang.Integer> unreadCounts = null;
    @org.jetbrains.annotations.NotNull()
    private static final java.util.concurrent.ConcurrentHashMap<java.lang.String, kotlin.collections.ArrayDeque<java.lang.String>> senderNames = null;
    @org.jetbrains.annotations.Nullable()
    private static com.varun.akva.services.AKVANotificationListener instance;
    @org.jetbrains.annotations.NotNull()
    public static final com.varun.akva.services.AKVANotificationListener.Companion Companion = null;
    
    public AKVANotificationListener() {
        super();
    }
    
    @java.lang.Override()
    public void onCreate() {
    }
    
    @java.lang.Override()
    public void onNotificationPosted(@org.jetbrains.annotations.Nullable()
    android.service.notification.StatusBarNotification sbn) {
    }
    
    @java.lang.Override()
    public void onNotificationRemoved(@org.jetbrains.annotations.Nullable()
    android.service.notification.StatusBarNotification sbn) {
    }
    
    @java.lang.Override()
    public void onDestroy() {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010$\n\u0000\n\u0002\u0010 \n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0016\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\n2\u0006\u0010\u000e\u001a\u00020\u0004J\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\f0\u0010J\u0014\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00040\u00122\u0006\u0010\u000e\u001a\u00020\u0004J\u0006\u0010\u0013\u001a\u00020\fJ\u000e\u0010\u0014\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0001X\u0082\u0004\u00a2\u0006\u0002\n\u0000R \u0010\b\u001a\u0014\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\n0\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\f0\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0015"}, d2 = {"Lcom/varun/akva/services/AKVANotificationListener$Companion;", "", "()V", "TAG", "", "instance", "Lcom/varun/akva/services/AKVANotificationListener;", "lock", "senderNames", "Ljava/util/concurrent/ConcurrentHashMap;", "Lkotlin/collections/ArrayDeque;", "unreadCounts", "", "clearApp", "pkg", "getAllUnreadCounts", "", "getSenderNames", "", "getTotalUnread", "getUnreadCount", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        public final int getUnreadCount(@org.jetbrains.annotations.NotNull()
        java.lang.String pkg) {
            return 0;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<java.lang.String> getSenderNames(@org.jetbrains.annotations.NotNull()
        java.lang.String pkg) {
            return null;
        }
        
        public final int getTotalUnread() {
            return 0;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.Map<java.lang.String, java.lang.Integer> getAllUnreadCounts() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final kotlin.collections.ArrayDeque<java.lang.String> clearApp(@org.jetbrains.annotations.NotNull()
        java.lang.String pkg) {
            return null;
        }
    }
}