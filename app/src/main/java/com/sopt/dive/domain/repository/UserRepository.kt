package com.sopt.dive.domain.repository

import com.sopt.dive.domain.model.SignUpModel
import com.sopt.dive.domain.model.UserModel
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getUserStream(id: Int): Flow<UserModel?>
    suspend fun updateUserInfo(id: Int): Result<Unit>
    suspend fun signUp(signUpModel: SignUpModel): Result<UserModel>
    suspend fun withdraw(id: Int): Result<Unit>
    suspend fun clearLocalUserData(id: Int)

}