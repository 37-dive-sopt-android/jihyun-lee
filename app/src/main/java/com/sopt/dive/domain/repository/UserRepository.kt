package com.sopt.dive.domain.repository

import com.sopt.dive.data.dto.request.SignUpRequestDto
import com.sopt.dive.data.dto.response.UserResponseDto

interface UserRepository {
    suspend fun signUp(
        signUpRequestDto: SignUpRequestDto
    ): Result<UserResponseDto>
}