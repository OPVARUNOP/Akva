package com.varun.akva.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Entity(tableName = "user_profile")
data class UserProfile(@PrimaryKey val id: Int = 1, val userName: String = "", val facts: String = "[]", val lastUpdated: Long = System.currentTimeMillis())

@Entity(tableName = "conversation")
data class ConversationMemory(@PrimaryKey(autoGenerate = true) val id: Long = 0, val timestamp: Long = System.currentTimeMillis(), val userSaid: String, val akvaReplied: String)

@Entity(tableName = "actions")
data class ActionHistory(@PrimaryKey(autoGenerate = true) val id: Long = 0, val timestamp: Long = System.currentTimeMillis(), val action: String, val target: String, val result: String, val success: Boolean)

@Dao
interface UserProfileDao { @Query("SELECT * FROM user_profile WHERE id = 1") suspend fun getProfile(): UserProfile?; @Insert(onConflict = OnConflictStrategy.REPLACE) suspend fun insert(p: UserProfile) }

@Dao
interface ConversationDao { @Query("SELECT * FROM conversation ORDER BY timestamp DESC LIMIT :limit") fun getHistory(limit: Int): Flow<List<ConversationMemory>>; @Insert suspend fun insert(m: ConversationMemory) }

@Dao
interface ActionDao { @Query("SELECT * FROM actions ORDER BY timestamp DESC LIMIT :limit") fun getActions(limit: Int): Flow<List<ActionHistory>>; @Insert suspend fun insert(a: ActionHistory) }

@Database(entities = [UserProfile::class, ConversationMemory::class, ActionHistory::class], version = 4, exportSchema = false)
abstract class AkvaDatabase : RoomDatabase() {
    abstract fun userProfileDao(): UserProfileDao
    abstract fun conversationDao(): ConversationDao
    abstract fun actionDao(): ActionDao
    companion object {
        fun getDatabase(ctx: android.content.Context) = Room.databaseBuilder(ctx, AkvaDatabase::class.java, "akva_v4").fallbackToDestructiveMigration().build()
    }
}
