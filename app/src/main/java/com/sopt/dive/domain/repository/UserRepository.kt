package com.sopt.dive.domain.repository

import com.sopt.dive.data.dto.request.SignUpRequestDto
import com.sopt.dive.data.dto.response.UserResponseDto

interface UserRepository {
    suspend fun signUp(
        signUpRequestDto: SignUpRequestDto
    ): Result<UserResponseDto>

    suspend fun getUserInfo(
        id: Int
    ): Result<UserResponseDto>

    suspend fun withdraw(
        id: Int
    ): Result<Unit>
}