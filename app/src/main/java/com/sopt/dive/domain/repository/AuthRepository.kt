package com.sopt.dive.domain.repository

import com.sopt.dive.data.remote.dto.request.LoginRequestDto
import com.sopt.dive.domain.model.LoginModel

interface AuthRepository {
    suspend fun login(
        loginRequestDto: LoginRequestDto
    ): Result<LoginModel>
}