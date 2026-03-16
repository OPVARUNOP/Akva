package com.varun.akva.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [AppUsageEvent::class, AppPersonality::class], version = 1, exportSchema = false)
abstract class AkvaDatabase : RoomDatabase() {
    abstract fun appUsageDao(): AppUsageDao
    abstract fun appPersonalityDao(): AppPersonalityDao

    companion object {
        @Volatile private var instance: AkvaDatabase? = null

        fun getInstance(context: Context): AkvaDatabase {
            return instance ?: synchronized(this) {
                instance ?: Room.databaseBuilder(
                    context.applicationContext,
                    AkvaDatabase::class.java,
                    "akva_database"
                ).fallbackToDestructiveMigration().build().also { instance = it }
            }
        }
    }
}
