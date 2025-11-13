package com.sopt.dive.data.repositoryimpl

import com.sopt.dive.data.datasource.UserDataSource
import com.sopt.dive.data.dto.request.SignUpRequestDto
import com.sopt.dive.data.dto.response.UserResponseDto
import com.sopt.dive.data.network.getErrorMessage
import com.sopt.dive.domain.repository.UserRepository
import retrofit2.HttpException

class UserRepositoryImpl(
    private val userDataSource: UserDataSource
): UserRepository {
    override suspend fun signUp(signUpRequestDto: SignUpRequestDto): Result<UserResponseDto> {
        return try {
            val response = userDataSource.singUp(signUpRequestDto)

            if (response.success) {
                val nonNullData = response.data ?: throw Exception("Successful response but data field was null.")
                Result.success(nonNullData)
            } else {
                Result.failure(Exception(response.message))
            }
        } catch (e: HttpException) {
            Result.failure(Exception(getErrorMessage(e)))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getUserInfo(id: Int): Result<UserResponseDto> {
        return try {
            val response = userDataSource.getUserInfo(id)

            if (response.success) {
                val nonNullData = response.data ?: throw Exception("Successful response but data field was null.")
                Result.success(nonNullData)
            } else {
                Result.failure(Exception(response.message))
            }
        } catch (e: HttpException) {
            Result.failure(Exception(getErrorMessage(e)))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}