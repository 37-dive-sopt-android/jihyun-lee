package com.sopt.dive.domain.repository

import com.sopt.dive.data.dto.request.LoginRequestDto

interface AuthRepository {
    suspend fun login(
        loginRequestDto: LoginRequestDto
    ): Result<Int>
}