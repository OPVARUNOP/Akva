package com.varun.akva

import android.app.Application
import com.varun.akva.data.AkvaDatabase
import com.varun.akva.data.AkvaRepository
import com.varun.akva.data.SettingsManager

class AKVAApplication : Application() {

    lateinit var database: AkvaDatabase
    lateinit var repository: AkvaRepository
    lateinit var settingsManager: SettingsManager

    override fun onCreate() {
        super.onCreate()
        database = AkvaDatabase.getInstance(this)
        repository = AkvaRepository(this)
        settingsManager = SettingsManager(this)
    }
}
