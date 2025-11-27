package com.sopt.dive.data.local.datasourceimpl

import com.sopt.dive.data.local.dao.FriendDao
import com.sopt.dive.data.local.datasource.ReqresLocalDataSource
import com.sopt.dive.data.local.entity.FriendEntity
import kotlinx.coroutines.flow.Flow

class ReqresLocalDataSourceImpl(
    private val friendDao: FriendDao
) : ReqresLocalDataSource {
    override fun getFriendsStream(): Flow<List<FriendEntity>> =
        friendDao.getAllFriends()

    override suspend fun saveFriends(friends: List<FriendEntity>) =
        friendDao.insertAll(friends)
}