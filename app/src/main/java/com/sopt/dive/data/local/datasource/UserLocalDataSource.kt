package com.sopt.dive.data.local.datasource

import com.sopt.dive.data.local.entity.UserEntity

interface UserLocalDataSource {
    suspend fun saveUser(userEntity: UserEntity)
    suspend fun getUser(id: Int): UserEntity?
    suspend fun deleteUser(id: Int)
}