package com.sopt.dive.data.repositoryimpl

import com.sopt.dive.data.datasource.UserDataSource
import com.sopt.dive.data.dto.request.SignUpRequestDto
import com.sopt.dive.data.mapper.toDomain
import com.sopt.dive.data.util.handleApiResponse
import com.sopt.dive.data.util.handleNullableApiResponse
import com.sopt.dive.data.util.safeApiCall
import com.sopt.dive.domain.model.UserInfo
import com.sopt.dive.domain.repository.UserRepository

class UserRepositoryImpl(
    private val userDataSource: UserDataSource
): UserRepository {
    override suspend fun signUp(signUpRequestDto: SignUpRequestDto): Result<UserInfo> = safeApiCall {
        userDataSource.singUp(signUpRequestDto)
            .handleApiResponse()
            .getOrThrow()
            .toDomain()
    }

    override suspend fun getUserInfo(id: Int): Result<UserInfo> = safeApiCall {
        userDataSource.getUserInfo(id)
            .handleApiResponse()
            .getOrThrow()
            .toDomain()
    }

    override suspend fun withdraw(id: Int): Result<Unit> = safeApiCall {
        userDataSource.withdraw(id)
            .handleNullableApiResponse()
            .getOrThrow()
    }
}