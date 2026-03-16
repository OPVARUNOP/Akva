package com.varun.akva.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface AppPersonalityDao {
    @Query("SELECT * FROM app_personalities WHERE packageName = :packageName")
    suspend fun getPersonality(packageName: String): AppPersonality?

    @Query("SELECT * FROM app_personalities")
    fun getAllPersonalities(): Flow<List<AppPersonality>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdate(personality: AppPersonality)
}
