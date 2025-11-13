package com.sopt.dive.data.repositoryimpl

import com.sopt.dive.data.datasource.AuthDataSource
import com.sopt.dive.data.dto.request.LoginRequestDto
import com.sopt.dive.data.dto.response.LoginResponseDto
import com.sopt.dive.data.network.getErrorMessage
import com.sopt.dive.domain.repository.AuthRepository
import retrofit2.HttpException

class AuthRepositoryImpl(
    private val authDataSource: AuthDataSource
): AuthRepository {
    override suspend fun login(loginRequestDto: LoginRequestDto): Result<LoginResponseDto> {
        return try {
            val response = authDataSource.login(loginRequestDto)

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