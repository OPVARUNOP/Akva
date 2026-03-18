package com.varun.akva.actions

import android.content.Context
import android.content.Intent

class AppLauncher(private val ctx: Context) {
    fun launchApp(name: String): Boolean {
        val pm = ctx.packageManager
        val pkg = pm.getInstalledApplications(0).firstOrNull { pm.getApplicationLabel(it).toString().lowercase().contains(name.lowercase()) }?.packageName ?: return false
        val intent = pm.getLaunchIntentForPackage(pkg)?.apply { addFlags(Intent.FLAG_ACTIVITY_NEW_TASK) }
        return if (intent != null) { ctx.startActivity(intent); true } else false
    }
}
