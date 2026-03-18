package com.varun.akva.data

import kotlinx.coroutines.flow.Flow

class AkvaRepository(private val profileDao: UserProfileDao, private val convDao: ConversationDao, private val actionDao: ActionDao) {
    suspend fun getUserProfile() = profileDao.getProfile()
    suspend fun saveUserProfile(p: UserProfile) = profileDao.insert(p)
    fun getRecentHistory(l: Int) = convDao.getHistory(l)
    suspend fun addMemory(m: ConversationMemory) = convDao.insert(m)
    fun getRecentActions(l: Int) = actionDao.getActions(l)
    suspend fun addAction(a: ActionHistory) = actionDao.insert(a)
}
