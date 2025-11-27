package com.sopt.dive.data.repositoryimpl

import com.sopt.dive.data.mapper.toDomain
import com.sopt.dive.data.remote.datasource.ReqresRemoteDataSource
import com.sopt.dive.data.util.handleReqresApiResponse
import com.sopt.dive.data.util.safeApiCall
import com.sopt.dive.domain.model.ReqresUserModel
import com.sopt.dive.domain.repository.ReqresRepository

class ReqresRepositoryImpl(
    private val reqresRemoteDataSource: ReqresRemoteDataSource
): ReqresRepository {
    override suspend fun getUsers(page: Int, perPage: Int): Result<List<ReqresUserModel>> = safeApiCall {
        reqresRemoteDataSource.getUsers(page, perPage)
            .handleReqresApiResponse()
            .getOrThrow()
            .toDomain()
    }
}