package com.sopt.dive.data.local.datasourceimpl

import com.sopt.dive.data.local.dao.UserDao
import com.sopt.dive.data.local.datasource.UserLocalDataSource
import com.sopt.dive.data.local.entity.UserEntity
import kotlinx.coroutines.flow.Flow

class UserLocalDataSourceImpl(
    private val userDao: UserDao
): UserLocalDataSource {
    override suspend fun saveUser(userEntity: UserEntity) {
        userDao.insertUser(userEntity)
    }

    override fun getUserStream(id: Int): Flow<UserEntity?> {
        return userDao.getUserStream(id)
    }

    override suspend fun deleteUser() {
        userDao.deleteUser()
    }
}