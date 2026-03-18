package com.varun.akva.services

import android.accessibilityservice.AccessibilityService
import android.util.Log
import android.view.accessibility.AccessibilityEvent
import com.varun.akva.AKVAApplication

class AKVAAccessibilityService : AccessibilityService() {
    override fun onAccessibilityEvent(event: AccessibilityEvent) {
        if (event.eventType == AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED) {
            val app = application as AKVAApplication
            app.liveEngine.proactiveSpeak("Sir, switching to ${event.packageName}")
        }
    }
    override fun onInterrupt() {}
    override fun onServiceConnected() {
        super.onServiceConnected()
        Log.d("AKVA_ACC", "Connected")
    }
}
