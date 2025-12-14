package com.sopt.dive.domain.repository

import com.sopt.dive.domain.model.ReqresUserModel
import kotlinx.coroutines.flow.Flow

interface ReqresRepository {
    fun getFriendsStream(): Flow<List<ReqresUserModel>>
    suspend fun updateFriends(page: Int, perPage: Int): Result<Unit>
    suspend fun deleteAllFriends()
}