package com.sopt.dive.data.local.datasource

import com.sopt.dive.data.local.entity.FriendEntity
import kotlinx.coroutines.flow.Flow

interface ReqresLocalDataSource {
    fun getFriendsStream(): Flow<List<FriendEntity>>
    suspend fun saveFriends(friends: List<FriendEntity>)
    suspend fun deleteAllFriends()
}