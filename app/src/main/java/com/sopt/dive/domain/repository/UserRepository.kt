package com.sopt.dive.domain.repository

import com.sopt.dive.domain.model.SignUpModel
import com.sopt.dive.domain.model.UserModel
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    val myProfile: Flow<UserModel?>
    fun getUserStream(id: Int): Flow<UserModel?>
    suspend fun signUp(signUpModel: SignUpModel): Result<UserModel>
    suspend fun withdraw(): Result<Unit>
    suspend fun logout(): Result<Unit>
    suspend fun fetchMyInfo(): Result<Unit>
}