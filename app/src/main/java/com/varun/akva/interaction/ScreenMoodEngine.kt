package com.varun.akva.interaction

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Color
import android.graphics.PixelFormat
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.provider.Settings
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.view.animation.AccelerateDecelerateInterpolator
import com.varun.akva.data.SettingsManager

class ScreenMoodEngine(private val context: Context) {

    private val settingsManager = SettingsManager(context)
    private var overlayView: View? = null
    private var windowManager: WindowManager? = null
    private var currentAlpha = 0f
    private val handler = Handler(Looper.getMainLooper())

    enum class Mood(val color: Int, val alpha: Float) {
        MORNING_GOLD(Color.parseColor("#FFF3E0"), 0.07f),
        AFTERNOON_CLEAR(Color.TRANSPARENT, 0.0f),
        EVENING_COOL(Color.parseColor("#E3F2FD"), 0.07f),
        NIGHT_AMBER(Color.parseColor("#FF6F00"), 0.10f),
        STRESS_CALM(Color.parseColor("#1565C0"), 0.12f)
    }

    fun getMoodForHour(hour: Int, isStressed: Boolean = false): Mood {
        if (isStressed) return Mood.STRESS_CALM
        return when (hour) {
            in 5..11 -> Mood.MORNING_GOLD
            in 12..17 -> Mood.AFTERNOON_CLEAR
            in 18..21 -> Mood.EVENING_COOL
            else -> Mood.NIGHT_AMBER
        }
    }

    fun applyMood(mood: Mood) {
        if (!settingsManager.screenMoodEnabled) { removeOverlay(); return }
        if (!Settings.canDrawOverlays(context)) return

        handler.post {
            try {
                if (mood == Mood.AFTERNOON_CLEAR) {
                    animateTo(0f) { removeOverlay() }
                    return@post
                }
                if (overlayView == null) createOverlay()
                animateTo(mood.alpha, mood.color)
            } catch (e: Exception) {
                Log.e(TAG, "Apply mood error: ${e.message}")
            }
        }
    }

    private fun createOverlay() {
        try {
            windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
            overlayView = View(context).apply { setBackgroundColor(Color.TRANSPARENT) }
            val type = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
                WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY
            else @Suppress("DEPRECATION") WindowManager.LayoutParams.TYPE_SYSTEM_OVERLAY

            val params = WindowManager.LayoutParams(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT,
                type,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE or
                        WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE or
                        WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL or
                        WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN,
                PixelFormat.TRANSLUCENT
            )
            windowManager?.addView(overlayView, params)
        } catch (e: Exception) {
            Log.e(TAG, "Create overlay error: ${e.message}")
        }
    }

    private fun animateTo(targetAlpha: Float, targetColor: Int = Color.TRANSPARENT, onComplete: (() -> Unit)? = null) {
        val view = overlayView ?: run { onComplete?.invoke(); return }
        val startAlpha = currentAlpha
        ValueAnimator.ofFloat(0f, 1f).apply {
            duration = 1800L
            interpolator = AccelerateDecelerateInterpolator()
            addUpdateListener { anim ->
                val f = anim.animatedFraction
                currentAlpha = startAlpha + (targetAlpha - startAlpha) * f
                val a = (currentAlpha * 255).toInt().coerceIn(0, 255)
                view.setBackgroundColor(Color.argb(a, Color.red(targetColor), Color.green(targetColor), Color.blue(targetColor)))
                if (f >= 1f) onComplete?.invoke()
            }
            start()
        }
    }

    fun removeOverlay() {
        handler.post {
            try {
                overlayView?.let { windowManager?.removeView(it) }
                overlayView = null
                currentAlpha = 0f
            } catch (_: Exception) {}
        }
    }

    companion object { private const val TAG = "ScreenMoodEngine" }
}
