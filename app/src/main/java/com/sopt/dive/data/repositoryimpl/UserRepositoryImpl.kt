package com.sopt.dive.data.repositoryimpl

import com.sopt.dive.data.local.datasource.UserLocalDataSource
import com.sopt.dive.data.mapper.toDomain
import com.sopt.dive.data.mapper.toEntity
import com.sopt.dive.data.remote.datasource.UserRemoteDataSource
import com.sopt.dive.data.remote.dto.request.SignUpRequestDto
import com.sopt.dive.data.util.handleApiResponse
import com.sopt.dive.data.util.handleNullableApiResponse
import com.sopt.dive.data.util.safeApiCall
import com.sopt.dive.domain.model.UserModel
import com.sopt.dive.domain.repository.UserRepository

class UserRepositoryImpl(
    private val userRemoteDataSource: UserRemoteDataSource,
    private val userLocalDataSource: UserLocalDataSource
): UserRepository {
    override suspend fun signUp(signUpRequestDto: SignUpRequestDto): Result<UserModel> = safeApiCall {
        userRemoteDataSource.singUp(signUpRequestDto)
            .handleApiResponse()
            .getOrThrow()
            .toDomain()
    }

    override suspend fun getUserInfo(id: Int): Result<UserModel> = safeApiCall {
        val cachedEntity = userLocalDataSource.getUser(id)
        if(cachedEntity == null) {
            val remoteData = userRemoteDataSource.getUserInfo(id)
                .handleApiResponse()
                .getOrThrow()
            val entityToSave = remoteData.toEntity().copy(
                profileUrl = "https://i.pinimg.com/736x/96/37/2d/96372ded13d1e6b17cdf10b4ecb23483.jpg"
            )
            userLocalDataSource.saveUser(entityToSave)
            entityToSave.toDomain()
        } else {
            cachedEntity.toDomain()
        }
    }

    override suspend fun withdraw(id: Int): Result<Unit> = safeApiCall {
        userRemoteDataSource.withdraw(id)
            .handleNullableApiResponse()
            .getOrThrow()
        userLocalDataSource.deleteUser(id)
    }
}