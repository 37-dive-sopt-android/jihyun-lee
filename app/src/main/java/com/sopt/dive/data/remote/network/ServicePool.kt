package com.sopt.dive.data.remote.network

import com.sopt.dive.data.remote.datasource.AuthDataSource
import com.sopt.dive.data.remote.datasource.UserDataSource
import com.sopt.dive.data.remote.datasourceimpl.AuthDataSourceImpl
import com.sopt.dive.data.remote.datasourceimpl.UserDataSourceImpl
import com.sopt.dive.data.remote.repositoryimpl.AuthRepositoryImpl
import com.sopt.dive.data.remote.repositoryimpl.UserRepositoryImpl
import com.sopt.dive.data.remote.service.AuthService
import com.sopt.dive.data.remote.service.UserService
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