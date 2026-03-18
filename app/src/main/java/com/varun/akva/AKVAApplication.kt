package com.varun.akva

import android.app.Application
import android.util.Log
import com.varun.akva.actions.*
import com.varun.akva.data.*
import com.varun.akva.intelligence.*
import com.varun.akva.interaction.*

class AKVAApplication : Application() {
    lateinit var database: AkvaDatabase
    lateinit var repository: AkvaRepository
    lateinit var settings: SettingsManager
    lateinit var voiceEngine: VoiceEngine
    lateinit var hapticEngine: HapticEngine
    lateinit var memoryEngine: AKVAMemoryEngine
    lateinit var geminiEngine: GeminiEngine
    lateinit var liveEngine: AKVALiveEngine
    lateinit var listenerEngine: AKVAListenerEngine

    override fun onCreate() {
        super.onCreate()
        database = AkvaDatabase.getDatabase(this)
        repository = AkvaRepository(
            database.userProfileDao(),
            database.conversationDao(),
            database.actionDao()
        )
        settings = SettingsManager(this)
        voiceEngine = VoiceEngine(this)
        hapticEngine = HapticEngine(this)
        geminiEngine = GeminiEngine(this, settings)
        memoryEngine = AKVAMemoryEngine(repository, geminiEngine)
        
        val appLauncher = AppLauncher(this)
        val messageSender = MessageSender(this)
        val reminderManager = ReminderManager(this)
        val systemController = SystemController(this)
        val webSearch = AKVAWebSearch(settings)

        liveEngine = AKVALiveEngine(
            this, repository, settings, voiceEngine, hapticEngine,
            memoryEngine, geminiEngine, CommandParser(geminiEngine),
            appLauncher, messageSender, reminderManager, systemController, webSearch
        )

        listenerEngine = AKVAListenerEngine(this) { transcript ->
            liveEngine.onUserSpeech(transcript)
        }
        
        if (settings.isWakeWordEnabled) {
            listenerEngine.startListening()
        }
    }
}
