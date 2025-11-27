package com.sopt.dive.data.repositoryimpl

import com.sopt.dive.data.local.datasource.UserLocalDataSource
import com.sopt.dive.data.mapper.toDomain
import com.sopt.dive.data.mapper.toDto
import com.sopt.dive.data.mapper.toEntity
import com.sopt.dive.data.remote.datasource.UserRemoteDataSource
import com.sopt.dive.data.util.handleApiResponse
import com.sopt.dive.data.util.handleNullableApiResponse
import com.sopt.dive.data.util.safeApiCall
import com.sopt.dive.domain.model.SignUpModel
import com.sopt.dive.domain.model.UserModel
import com.sopt.dive.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserRepositoryImpl(
    private val userRemoteDataSource: UserRemoteDataSource,
    private val userLocalDataSource: UserLocalDataSource
): UserRepository {

    override fun getUserStream(id: Int): Flow<UserModel?> {
        return userLocalDataSource.getUserStream(id).map { entity ->
            entity?.toDomain()
        }
    }

    override suspend fun updateUserInfo(id: Int): Result<Unit> = safeApiCall {
        val remoteData = userRemoteDataSource.getUserInfo(id)
            .handleApiResponse()
            .getOrThrow()

        val entityToSave = remoteData.toEntity().copy(
            profileUrl = "https://i.pinimg.com/736x/96/37/2d/96372ded13d1e6b17cdf10b4ecb23483.jpg"
        )

        userLocalDataSource.saveUser(entityToSave)
    }

    override suspend fun signUp(signUpModel: SignUpModel): Result<UserModel> = safeApiCall {
        val requestDto = signUpModel.toDto()

        userRemoteDataSource.singUp(requestDto)
            .handleApiResponse()
            .getOrThrow()
            .toDomain()
    }

    override suspend fun withdraw(id: Int): Result<Unit> = safeApiCall {
        userRemoteDataSource.withdraw(id)
            .handleNullableApiResponse()
            .getOrThrow()
        userLocalDataSource.deleteUser()
    }

    override suspend fun clearLocalUserData(id: Int) =
        userLocalDataSource.deleteUser()

}