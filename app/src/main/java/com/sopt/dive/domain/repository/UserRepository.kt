package com.sopt.dive.domain.repository

import com.sopt.dive.data.remote.dto.request.SignUpRequestDto
import com.sopt.dive.domain.model.UserModel

interface UserRepository {
    suspend fun signUp(
        signUpRequestDto: SignUpRequestDto
    ): Result<UserModel>

    suspend fun getUserInfo(
        id: Int
    ): Result<UserModel>

    suspend fun withdraw(
        id: Int
    ): Result<Unit>
}