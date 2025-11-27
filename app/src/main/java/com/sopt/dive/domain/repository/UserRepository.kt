package com.sopt.dive.domain.repository

import com.sopt.dive.data.remote.dto.request.SignUpRequestDto
import com.sopt.dive.domain.model.UserModel
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getUserStream(id: Int): Flow<UserModel?>

    suspend fun updateUserInfo(id: Int): Result<Unit>

    suspend fun signUp(signUpRequestDto: SignUpRequestDto): Result<UserModel>

    suspend fun withdraw(id: Int): Result<Unit>

}