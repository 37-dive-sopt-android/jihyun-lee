package com.sopt.dive.data.repositoryimpl

import com.sopt.dive.data.datasource.AuthDataSource
import com.sopt.dive.data.dto.request.LoginRequestDto
import com.sopt.dive.data.mapper.toDomain
import com.sopt.dive.data.util.handleApiResponse
import com.sopt.dive.data.util.safeApiCall
import com.sopt.dive.domain.repository.AuthRepository

class AuthRepositoryImpl(
    private val authDataSource: AuthDataSource
): AuthRepository {
    override suspend fun login(loginRequestDto: LoginRequestDto): Result<Int> = safeApiCall {
        authDataSource.login(loginRequestDto)
            .handleApiResponse()
            .getOrThrow()
            .toDomain()
    }
}