package com.sopt.dive.data.repositoryimpl

import com.sopt.dive.data.remote.dto.request.LoginRequestDto
import com.sopt.dive.data.mapper.toDomain
import com.sopt.dive.data.remote.datasource.AuthRemoteDataSource
import com.sopt.dive.data.util.handleApiResponse
import com.sopt.dive.data.util.safeApiCall
import com.sopt.dive.domain.model.LoginModel
import com.sopt.dive.domain.repository.AuthRepository

class AuthRepositoryImpl(
    private val authRemoteDataSource: AuthRemoteDataSource
): AuthRepository {
    override suspend fun login(loginRequestDto: LoginRequestDto): Result<LoginModel> = safeApiCall {
        authRemoteDataSource.login(loginRequestDto)
            .handleApiResponse()
            .getOrThrow()
            .toDomain()
    }
}