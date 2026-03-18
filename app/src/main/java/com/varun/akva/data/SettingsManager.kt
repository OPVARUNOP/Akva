package com.varun.akva.data
import android.content.Context
class SettingsManager(context: Context) {
    private val p = context.getSharedPreferences("akva_settings", Context.MODE_PRIVATE)
    var isVoiceEnabled: Boolean get() = p.getBoolean("v", true); set(v) = p.edit().putBoolean("v", v).apply()
    var isWakeWordEnabled: Boolean get() = p.getBoolean("w", true); set(v) = p.edit().putBoolean("w", v).apply()
    var isProactiveEnabled: Boolean get() = p.getBoolean("p", true); set(v) = p.edit().putBoolean("p", v).apply()
    var backendUrl: String get() = p.getString("b", "https://web-production-d4aa5.up.railway.app") ?: ""; set(v) = p.edit().putString("b", v).apply()
    var geminiApiKey: String get() = p.getString("g", "") ?: ""; set(v) = p.edit().putString("g", v).apply()
    var searchApiKey: String get() = p.getString("sa", "") ?: ""; set(v) = p.edit().putString("sa", v).apply()
    var searchCx: String get() = p.getString("scx", "") ?: ""; set(v) = p.edit().putString("scx", v).apply()
}
