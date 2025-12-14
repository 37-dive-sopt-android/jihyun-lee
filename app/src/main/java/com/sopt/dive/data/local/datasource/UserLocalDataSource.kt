package com.sopt.dive.data.local.datasource

import com.sopt.dive.data.local.entity.UserEntity
import kotlinx.coroutines.flow.Flow

interface UserLocalDataSource {
    suspend fun saveUser(userEntity: UserEntity)
    fun getUserStream(id: Int): Flow<UserEntity?>
    suspend fun deleteUser()
}