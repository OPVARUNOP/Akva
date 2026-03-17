package com.varun.akva.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [AppUsageEvent::class, AppPersonality::class], version = 1, exportSchema = false)
abstract class AkvaDatabase : RoomDatabase() {

    abstract fun usageDao(): AppUsageDao
    abstract fun personalityDao(): AppPersonalityDao

    companion object {
        @Volatile
        private var INSTANCE: AkvaDatabase? = null

        fun getDatabase(context: Context): AkvaDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AkvaDatabase::class.java,
                    "akva_database"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                instance
            }
        }
    }
}
