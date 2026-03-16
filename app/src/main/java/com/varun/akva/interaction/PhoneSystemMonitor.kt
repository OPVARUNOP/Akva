package com.varun.akva.interaction

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.BatteryManager
import android.telephony.TelephonyManager
import android.util.Log
import com.varun.akva.data.SettingsManager
import com.varun.akva.intelligence.ContextDetector
import com.varun.akva.intelligence.PersonalityEngine

class PhoneSystemMonitor(private val context: Context) {

    private val contextDetector = ContextDetector(context)
    private val personalityEngine = PersonalityEngine()
    private val settingsManager = SettingsManager(context)
    private var voiceEngine: VoiceEngine? = null
    private var hapticEngine: HapticEngine? = null
    private var batteryReceiver: BroadcastReceiver? = null
    private var screenReceiver: BroadcastReceiver? = null
    var isInCall: Boolean = false
        private set
    private var lastBatteryWarning = -1
    private var lastScreenOffTime = 0L
    private var onFirstUnlockOfDay: (() -> Unit)? = null
    private var onLateNightScreenOn: (() -> Unit)? = null

    fun init(voice: VoiceEngine, haptic: HapticEngine) {
        voiceEngine = voice
        hapticEngine = haptic
    }

    fun getBatteryPercent(): Int {
        return try {
            val bm = context.getSystemService(Context.BATTERY_SERVICE) as BatteryManager
            bm.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY)
        } catch (_: Exception) { -1 }
    }

    fun isCharging(): Boolean {
        return try {
            val bm = context.getSystemService(Context.BATTERY_SERVICE) as BatteryManager
            bm.isCharging
        } catch (_: Exception) { false }
    }

    fun getNetworkType(): String {
        return try {
            val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val caps = cm.getNetworkCapabilities(cm.activeNetwork ?: return "None") ?: return "None"
            when {
                caps.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> "WiFi"
                caps.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> "Mobile"
                else -> "Other"
            }
        } catch (_: Exception) { "Unknown" }
    }

    fun isInActiveCall(): Boolean {
        return try {
            val tm = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
            @Suppress("DEPRECATION")
            tm.callState != TelephonyManager.CALL_STATE_IDLE
        } catch (_: Exception) { false }
    }

    fun setOnFirstUnlockOfDay(callback: () -> Unit) { onFirstUnlockOfDay = callback }
    fun setOnLateNightScreenOn(callback: () -> Unit) { onLateNightScreenOn = callback }

    fun registerBatteryMonitor() {
        try {
            batteryReceiver = object : BroadcastReceiver() {
                override fun onReceive(ctx: Context?, intent: Intent?) {
                    val level = intent?.getIntExtra(BatteryManager.EXTRA_LEVEL, -1) ?: return
                    val scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, 100)
                    val percent = (level * 100) / scale
                    val status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1)
                    val charging = status == BatteryManager.BATTERY_STATUS_CHARGING || status == BatteryManager.BATTERY_STATUS_FULL
                    handleBattery(percent, charging)
                }
            }
            context.registerReceiver(batteryReceiver, IntentFilter(Intent.ACTION_BATTERY_CHANGED))
        } catch (e: Exception) { Log.e(TAG, "Battery monitor error: ${e.message}") }
    }

    fun registerScreenMonitor() {
        try {
            screenReceiver = object : BroadcastReceiver() {
                override fun onReceive(ctx: Context?, intent: Intent?) {
                    when (intent?.action) {
                        Intent.ACTION_SCREEN_OFF -> {
                            lastScreenOffTime = System.currentTimeMillis()
                        }
                        Intent.ACTION_SCREEN_ON -> {
                            val gap = System.currentTimeMillis() - lastScreenOffTime
                            val hour = contextDetector.getHourOfDay()

                            // First unlock of day: gap > 4 hours
                            if (gap > 4 * 60 * 60 * 1000 && hour in 5..11) {
                                onFirstUnlockOfDay?.invoke()
                            }

                            // Late night screen on after 2+ hour gap
                            if (gap > 2 * 60 * 60 * 1000 && (hour >= 23 || hour <= 1)) {
                                onLateNightScreenOn?.invoke()
                            }
                        }
                    }
                }
            }
            val filter = IntentFilter().apply {
                addAction(Intent.ACTION_SCREEN_ON)
                addAction(Intent.ACTION_SCREEN_OFF)
            }
            context.registerReceiver(screenReceiver, filter)
        } catch (e: Exception) { Log.e(TAG, "Screen monitor error: ${e.message}") }
    }

    private fun handleBattery(percent: Int, charging: Boolean) {
        if (isInActiveCall()) return
        val isNight = contextDetector.isNightTime()
        val vc = if (isNight) personalityEngine.getNightVoice() else personalityEngine.getDefaultVoice()

        when {
            percent <= 10 && lastBatteryWarning != 10 -> {
                lastBatteryWarning = 10
                voiceEngine?.speakImmediate("Battery critically low at $percent percent. Charge now.", vc, isNight)
                hapticEngine?.playAlert()
            }
            percent <= 20 && lastBatteryWarning != 20 && lastBatteryWarning != 10 -> {
                lastBatteryWarning = 20
                voiceEngine?.speak("Battery is at $percent percent. Consider charging soon.", vc, isNight)
                hapticEngine?.playAlert()
            }
            percent == 100 && charging && lastBatteryWarning != 100 -> {
                lastBatteryWarning = 100
                voiceEngine?.speak("Fully charged. You can unplug now.", vc, isNight)
                hapticEngine?.playAchievement()
            }
            percent > 20 && lastBatteryWarning <= 20 -> { lastBatteryWarning = -1 }
        }
    }

    fun unregister() {
        try { batteryReceiver?.let { context.unregisterReceiver(it) }; batteryReceiver = null } catch (_: Exception) {}
        try { screenReceiver?.let { context.unregisterReceiver(it) }; screenReceiver = null } catch (_: Exception) {}
    }

    companion object { private const val TAG = "PhoneSystemMonitor" }
}
