package com.sopt.dive.domain.repository

import com.sopt.dive.data.remote.dto.request.LoginRequestDto
import com.sopt.dive.domain.model.LoginModel
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    val isLoggedIn: Flow<Boolean>
    suspend fun login(loginRequestDto: LoginRequestDto): Result<LoginModel>
}