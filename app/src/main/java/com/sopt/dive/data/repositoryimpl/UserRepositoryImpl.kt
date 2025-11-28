package com.sopt.dive.data.repositoryimpl

import com.sopt.dive.data.local.datasource.UserLocalDataSource
import com.sopt.dive.data.local.datasource.UserPreferenceDataSource
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
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

class UserRepositoryImpl(
    private val userRemoteDataSource: UserRemoteDataSource,
    private val userLocalDataSource: UserLocalDataSource,
    private val userPreferenceDataSource: UserPreferenceDataSource
): UserRepository {
    override val myProfile: Flow<UserModel?> = userPreferenceDataSource.id
        .flatMapLatest { id ->
            if (id == 0) flowOf(null)
            else getUserStream(id)
        }

    override fun getUserStream(id: Int): Flow<UserModel?> {
        return userLocalDataSource.getUserStream(id).map { entity ->
            entity?.toDomain()
        }
    }

    override suspend fun fetchMyInfo(): Result<Unit> = safeApiCall {
        val id = userPreferenceDataSource.id.first()
        if (id ==0 ) throw Exception("로그인된 사용자가 없습니다.")

        val remoteDto = userRemoteDataSource.getUserInfo(id)
            .handleApiResponse()
            .getOrThrow()

        val entityToSave = remoteDto.toEntity().copy(
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

    override suspend fun withdraw(): Result<Unit> = safeApiCall {
        val id = userPreferenceDataSource.id.first()
        if (id == 0) throw Exception("로그인 정보 없음")

        userRemoteDataSource.withdraw(id)
            .handleNullableApiResponse()
            .getOrThrow()
        userLocalDataSource.deleteUser()
        userPreferenceDataSource.clear()
    }

    override suspend fun logout() = runCatching {
        userLocalDataSource.deleteUser()
        userPreferenceDataSource.clear()
    }
}