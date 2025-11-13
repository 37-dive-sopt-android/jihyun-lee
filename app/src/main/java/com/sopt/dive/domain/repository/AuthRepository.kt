package com.sopt.dive.domain.repository

import com.sopt.dive.data.dto.request.LoginRequestDto
import com.sopt.dive.data.dto.response.LoginResponseDto

interface AuthRepository {
    suspend fun login(
        loginRequestDto: LoginRequestDto
    ): Result<LoginResponseDto>
}