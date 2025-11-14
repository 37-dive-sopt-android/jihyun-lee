package com.sopt.dive.data.remote.repositoryimpl

import com.sopt.dive.data.remote.datasource.AuthDataSource
import com.sopt.dive.data.remote.dto.request.LoginRequestDto
import com.sopt.dive.data.remote.mapper.toDomain
import com.sopt.dive.data.util.handleApiResponse
import com.sopt.dive.data.util.safeApiCall
import com.sopt.dive.domain.model.LoginModel
import com.sopt.dive.domain.repository.AuthRepository

class AuthRepositoryImpl(
    private val authDataSource: AuthDataSource
): AuthRepository {
    override suspend fun login(loginRequestDto: LoginRequestDto): Result<LoginModel> = safeApiCall {
        authDataSource.login(loginRequestDto)
            .handleApiResponse()
            .getOrThrow()
            .toDomain()
    }
}