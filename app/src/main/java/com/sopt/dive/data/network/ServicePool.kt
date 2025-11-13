package com.sopt.dive.data.network

import com.sopt.dive.data.datasource.AuthDataSource
import com.sopt.dive.data.datasource.UserDataSource
import com.sopt.dive.data.datasourceimpl.AuthDataSourceImpl
import com.sopt.dive.data.datasourceimpl.UserDataSourceImpl
import com.sopt.dive.data.repositoryimpl.AuthRepositoryImpl
import com.sopt.dive.data.repositoryimpl.UserRepositoryImpl
import com.sopt.dive.data.service.AuthService
import com.sopt.dive.data.service.UserService
import com.sopt.dive.domain.repository.AuthRepository
import com.sopt.dive.domain.repository.UserRepository

object ServicePool {
    private val userService: UserService by lazy {
        ApiFactory.create<UserService>()
    }
    private val authService: AuthService by lazy {
        ApiFactory.create<AuthService>()
    }

    private val userDataSource: UserDataSource by lazy {
        UserDataSourceImpl(userService)
    }
    private val authDataSource: AuthDataSource by lazy {
        AuthDataSourceImpl(authService)
    }

    val userRepository: UserRepository by lazy {
        UserRepositoryImpl(userDataSource)
    }
    val authRepository: AuthRepository by lazy {
        AuthRepositoryImpl(authDataSource)
    }
}