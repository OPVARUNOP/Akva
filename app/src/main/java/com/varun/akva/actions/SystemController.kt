package com.varun.akva.actions

import android.content.Context
import android.hardware.camera2.CameraManager
import android.net.wifi.WifiManager
import android.media.AudioManager

class SystemController(private val ctx: Context) {
    fun toggleFlashlight(s: Boolean) { val cm = ctx.getSystemService(Context.CAMERA_SERVICE) as CameraManager; try { val id = cm.cameraIdList[0]; cm.setTorchMode(id, s) } catch(_: Exception) {} }
    fun setWifi(s: Boolean) { val wm = ctx.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager; try { wm.isWifiEnabled = s } catch(_: Exception) {} }
    fun setVolume(l: Int) { val am = ctx.getSystemService(Context.AUDIO_SERVICE) as AudioManager; val max = am.getStreamMaxVolume(AudioManager.STREAM_MUSIC); am.setStreamVolume(AudioManager.STREAM_MUSIC, (max * l / 100), 0) }
}
