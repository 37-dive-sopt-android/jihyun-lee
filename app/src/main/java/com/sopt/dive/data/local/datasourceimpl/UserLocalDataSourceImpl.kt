package com.sopt.dive.data.local.datasourceimpl

import com.sopt.dive.data.local.dao.UserDao
import com.sopt.dive.data.local.datasource.UserLocalDataSource
import com.sopt.dive.data.local.entity.UserEntity

class UserLocalDataSourceImpl(
    private val userDao: UserDao
): UserLocalDataSource {
    override suspend fun saveUser(userEntity: UserEntity) {
        userDao.insertUser(userEntity)
    }

    override suspend fun getUser(): UserEntity? {
        return userDao.getUser()
    }

    override suspend fun deleteUser() {
        userDao.deleteUser()
    }
}