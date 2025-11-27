package com.sopt.dive.data.repositoryimpl

import com.sopt.dive.data.local.datasource.ReqresLocalDataSource
import com.sopt.dive.data.mapper.toDomain
import com.sopt.dive.data.mapper.toEntity
import com.sopt.dive.data.remote.datasource.ReqresRemoteDataSource
import com.sopt.dive.data.util.handleReqresApiResponse
import com.sopt.dive.data.util.safeApiCall
import com.sopt.dive.domain.model.ReqresUserModel
import com.sopt.dive.domain.repository.ReqresRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ReqresRepositoryImpl(
    private val reqresRemoteDataSource: ReqresRemoteDataSource,
    private val reqresLocalDataSource: ReqresLocalDataSource
): ReqresRepository {

    override fun getFriendsStream(): Flow<List<ReqresUserModel>> =
        reqresLocalDataSource.getFriendsStream().map { entities ->
            entities.toDomain()
        }

    override suspend fun updateFriends(page: Int, perPage: Int): Result<Unit> = safeApiCall {
        val response = reqresRemoteDataSource.getUsers(page, perPage)
            .handleReqresApiResponse()
            .getOrThrow()

        val entities = response.toEntity()

        reqresLocalDataSource.saveFriends(entities)
    }

    override suspend fun deleteAllFriends() =
        reqresLocalDataSource.deleteAllFriends()

}