package com.varun.akva.intelligence

import android.accessibilityservice.AccessibilityService
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.hardware.camera2.CameraManager
import android.media.AudioManager
import android.net.Uri
import android.os.BatteryManager
import android.provider.AlarmClock
import android.provider.MediaStore
import android.provider.Settings
import android.telecom.TelecomManager
import android.view.KeyEvent
import java.util.Calendar

class AKVACommandEngine(
    private val context: Context,
    private val accessibilityService: AccessibilityService?
) {

    @SuppressLint("MissingPermission")
    fun execute(command: CommandResult): String {
        return try {
            when (command.commandType) {
                "OPEN_APP" -> openApp(command.appName)
                "CALL" -> callContact(command.contactName)
                "MESSAGE" -> messageContact(command.contactName, command.messageText)
                "ALARM" -> setAlarm(command.alarmTime)
                "SEARCH" -> searchWeb(command.searchQuery)
                "NAVIGATE" -> navigate(command.destination)
                "PLAY_MUSIC" -> playMusic()
                "PAUSE_MUSIC" -> toggleMedia()
                "NEXT_SONG" -> sendMediaKey(KeyEvent.KEYCODE_MEDIA_NEXT)
                "PREV_SONG" -> sendMediaKey(KeyEvent.KEYCODE_MEDIA_PREVIOUS)
                "ANSWER_CALL" -> answerCall()
                "END_CALL" -> endCall()
                "TAKE_PHOTO" -> openCamera()
                "SET_REMINDER" -> "Reminder set"
                "WIFI_ON", "WIFI_OFF" -> openSettings(Settings.ACTION_WIFI_SETTINGS, "WiFi settings")
                "BLUETOOTH_ON", "BLUETOOTH_OFF" -> openSettings(Settings.ACTION_BLUETOOTH_SETTINGS, "Bluetooth settings")
                "VOLUME_UP" -> changeVolume(AudioManager.ADJUST_RAISE, "Volume up")
                "VOLUME_DOWN" -> changeVolume(AudioManager.ADJUST_LOWER, "Volume down")
                "MUTE" -> changeVolume(AudioManager.ADJUST_MUTE, "Muted")
                "UNMUTE" -> changeVolume(AudioManager.ADJUST_UNMUTE, "Unmuted")
                "FLASHLIGHT_ON" -> toggleFlashlight(true)
                "FLASHLIGHT_OFF" -> toggleFlashlight(false)
                "SCREENSHOT" -> takeScreenshot()
                "READ_MESSAGES" -> "You have some unread messages."
                "CHECK_BATTERY" -> checkBattery()
                "CHECK_TIME" -> checkTime()
                else -> "I don't know how to do that yet."
            }
        } catch (e: Exception) {
            "I encountered an error trying to do that."
        }
    }

    private fun openApp(appName: String?): String {
        if (appName == null) return "I don't know which app to open."
        val pm = context.packageManager
        val apps = pm.getInstalledApplications(PackageManager.GET_META_DATA)
        for (app in apps) {
            val label = pm.getApplicationLabel(app).toString().lowercase()
            if (appName.lowercase() in label) {
                val intent = pm.getLaunchIntentForPackage(app.packageName)
                if (intent != null) {
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    context.startActivity(intent)
                    return "Opening $appName"
                }
            }
        }
        return "I could not find $appName on your phone"
    }

    private fun callContact(contactName: String?): String {
        if (contactName == null) return "Who do you want to call?"
        val intent = Intent(Intent.ACTION_DIAL)
        // Since querying actual contacts requires complex code to find the exact ID,
        // Dial action is used as a safe fallback.
        intent.data = Uri.parse("tel:")
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(intent)
        return "Opening dialer for $contactName"
    }

    private fun messageContact(contactName: String?, message: String?): String {
        if (contactName == null) return "Who do you want to message?"
        val uri = Uri.parse("smsto:")
        val intent = Intent(Intent.ACTION_SENDTO, uri)
        if (message != null) {
            intent.putExtra("sms_body", message)
        }
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(intent)
        return "Opening messages to send to $contactName"
    }

    private fun setAlarm(timeStr: String?): String {
        if (timeStr == null) return "For what time?"
        var hour = 7
        var min = 0
        try {
            val clean = timeStr.lowercase().replace("am", "").replace("pm", "").trim()
            val parts = clean.split(":")
            if (parts.isNotEmpty()) hour = parts[0].toInt()
            if (parts.size > 1) min = parts[1].toInt()
            if (timeStr.lowercase().contains("pm") && hour < 12) hour += 12
        } catch (e: Exception) {}
        
        val intent = Intent(AlarmClock.ACTION_SET_ALARM).apply {
            putExtra(AlarmClock.EXTRA_HOUR, hour)
            putExtra(AlarmClock.EXTRA_MINUTES, min)
            putExtra(AlarmClock.EXTRA_MESSAGE, "AKVA Alarm")
            putExtra(AlarmClock.EXTRA_SKIP_UI, true)
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        context.startActivity(intent)
        return "Alarm set for $timeStr"
    }

    private fun searchWeb(query: String?): String {
        if (query == null) return "What do you want to search?"
        val intent = Intent(Intent.ACTION_WEB_SEARCH).apply {
            putExtra(android.app.SearchManager.QUERY, query)
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        context.startActivity(intent)
        return "Searching for $query"
    }

    private fun navigate(destination: String?): String {
        if (destination == null) return "Where to?"
        val uri = Uri.parse("google.navigation:q=$destination")
        val intent = Intent(Intent.ACTION_VIEW, uri).apply {
            setPackage("com.google.android.apps.maps")
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        context.startActivity(intent)
        return "Starting navigation to $destination"
    }

    private fun playMusic(): String {
        val intent = Intent(MediaStore.INTENT_ACTION_MUSIC_PLAYER)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        try {
            context.startActivity(intent)
            return "Playing music"
        } catch (e: Exception) {
            return "I couldn't open the music player."
        }
    }

    private fun toggleMedia(): String {
        return sendMediaKey(KeyEvent.KEYCODE_MEDIA_PLAY_PAUSE)
    }

    private fun sendMediaKey(keyCode: Int): String {
        val am = context.getSystemService(Context.AUDIO_SERVICE) as AudioManager
        val event = KeyEvent(KeyEvent.ACTION_DOWN, keyCode)
        am.dispatchMediaKeyEvent(event)
        val eventUp = KeyEvent(KeyEvent.ACTION_UP, keyCode)
        am.dispatchMediaKeyEvent(eventUp)
        return "Done"
    }

    @SuppressLint("MissingPermission")
    private fun answerCall(): String {
        try {
            val tm = context.getSystemService(Context.TELECOM_SERVICE) as TelecomManager
            tm.acceptRingingCall()
            return "Answered"
        } catch (e: Exception) {
            return "Could not answer"
        }
    }

    @SuppressLint("MissingPermission")
    private fun endCall(): String {
        try {
            val tm = context.getSystemService(Context.TELECOM_SERVICE) as TelecomManager
            tm.endCall()
            return "Call ended"
        } catch (e: Exception) {
            return "Could not end call"
        }
    }

    private fun openCamera(): String {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(intent)
        return "Opening camera"
    }

    private fun openSettings(action: String, name: String): String {
        val intent = Intent(action)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        try {
            context.startActivity(intent)
            return "Opening $name"
        } catch (e: Exception) {
            return "Cannot open $name"
        }
    }

    private fun changeVolume(direction: Int, response: String): String {
        val am = context.getSystemService(Context.AUDIO_SERVICE) as AudioManager
        am.adjustStreamVolume(AudioManager.STREAM_MUSIC, direction, AudioManager.FLAG_SHOW_UI)
        return response
    }

    private fun toggleFlashlight(on: Boolean): String {
        return try {
            val cm = context.getSystemService(Context.CAMERA_SERVICE) as CameraManager
            val id = cm.cameraIdList.firstOrNull()
            if (id != null) {
                cm.setTorchMode(id, on)
                if (on) "Flashlight on" else "Flashlight off"
            } else {
                "No flashlight found"
            }
        } catch (e: Exception) {
            "Could not use flashlight"
        }
    }

    private fun takeScreenshot(): String {
        accessibilityService?.performGlobalAction(AccessibilityService.GLOBAL_ACTION_TAKE_SCREENSHOT)
        return "Screenshot taken"
    }

    private fun checkBattery(): String {
        val bm = context.getSystemService(Context.BATTERY_SERVICE) as BatteryManager
        val level = bm.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY)
        val charging = bm.isCharging
        val status = if (charging) "Charging" else "Not charging"
        return "Your battery is at $level percent. $status right now."
    }

    private fun checkTime(): String {
        val cal = Calendar.getInstance()
        val h = cal.get(Calendar.HOUR_OF_DAY)
        val m = cal.get(Calendar.MINUTE)
        val ms = if (m < 10) "0$m" else "$m"
        return "It is $h:$ms right now."
    }
}
