package com.varun.akva.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.util.Calendar

class AkvaRepository(
    private val usageDao: AppUsageDao,
    private val personalityDao: AppPersonalityDao,
    private val userProfileDao: UserProfileDao,
    private val conversationDao: ConversationDao,
    private val actionHistoryDao: ActionHistoryDao
) {
    suspend fun getUserProfile() = userProfileDao.getUserProfile()
    suspend fun saveUserProfile(profile: UserProfile) = userProfileDao.insertOrUpdate(profile)
    
    suspend fun getRecentConversations(limit: Int) = conversationDao.getRecentConversations(limit)
    suspend fun saveConversation(memory: ConversationMemory) = conversationDao.insert(memory)
    
    suspend fun getRecentActions(limit: Int) = actionHistoryDao.getRecentActions(limit)


    suspend fun logEvent(packageName: String, stressScore: Int, isNight: Boolean, aiResponse: String) {
        usageDao.insertUsageEvent(
            AppUsageEvent(
                packageName = packageName,
                timestamp = System.currentTimeMillis(),
                isNightUsage = isNight,
                stressScore = stressScore,
                aiResponse = aiResponse
            )
        )
    }

    fun getRecentEvents(limit: Int): Flow<List<AppUsageEvent>> = flow {
        emit(usageDao.getLatestEvents(limit))
    }

    suspend fun getRecentEventsList(limit: Int): List<AppUsageEvent> {
        return usageDao.getLatestEvents(limit)
    }

    suspend fun getTimesOpenedToday(packageName: String): Int {
        val todayStart = getTodayStart()
        return usageDao.getOpenCount(packageName, todayStart)
    }

    private fun getTodayStart(): Long {
        return Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }.timeInMillis
    }
}
