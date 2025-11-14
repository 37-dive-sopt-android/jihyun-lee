package com.sopt.dive.domain.repository

import com.sopt.dive.data.dto.request.SignUpRequestDto
import com.sopt.dive.domain.model.UserInfo

interface UserRepository {
    suspend fun signUp(
        signUpRequestDto: SignUpRequestDto
    ): Result<UserInfo>

    suspend fun getUserInfo(
        id: Int
    ): Result<UserInfo>

    suspend fun withdraw(
        id: Int
    ): Result<Unit>
}