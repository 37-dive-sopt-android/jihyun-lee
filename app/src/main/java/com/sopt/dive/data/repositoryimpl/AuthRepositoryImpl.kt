package com.sopt.dive.data.repositoryimpl

import com.sopt.dive.data.local.datasource.UserPreferenceDataSource
import com.sopt.dive.data.remote.dto.request.LoginRequestDto
import com.sopt.dive.data.mapper.toDomain
import com.sopt.dive.data.remote.datasource.AuthRemoteDataSource
import com.sopt.dive.data.network.handleApiResponse
import com.sopt.dive.data.network.safeApiCall
import com.sopt.dive.domain.model.LoginModel
import com.sopt.dive.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow

class AuthRepositoryImpl(
    private val authRemoteDataSource: AuthRemoteDataSource,
    private val userPreferenceDataSource: UserPreferenceDataSource
): AuthRepository {
    override val isLoggedIn: Flow<Boolean> = userPreferenceDataSource.isLoggedIn

    override suspend fun login(loginRequestDto: LoginRequestDto): Result<LoginModel> = safeApiCall {
        val responseDto = authRemoteDataSource.login(loginRequestDto)
            .handleApiResponse()
            .getOrThrow()

        userPreferenceDataSource.setId(responseDto.userId)
        userPreferenceDataSource.setLoggedIn(true)
        responseDto.toDomain()
    }
}