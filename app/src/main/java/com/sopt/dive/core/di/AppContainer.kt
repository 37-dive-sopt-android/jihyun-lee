package com.sopt.dive.core.di

import android.content.Context
import com.sopt.dive.data.local.AppDatabase
import com.sopt.dive.data.local.datasourceimpl.ReqresLocalDataSourceImpl
import com.sopt.dive.data.local.datasourceimpl.UserLocalDataSourceImpl
import com.sopt.dive.data.local.datastore.UserPreferencesRepository
import com.sopt.dive.data.remote.datasourceimpl.AuthRemoteDataSourceImpl
import com.sopt.dive.data.remote.datasourceimpl.ReqresRemoteDataSourceImpl
import com.sopt.dive.data.remote.datasourceimpl.UserRemoteDataSourceImpl
import com.sopt.dive.data.network.ApiFactory
import com.sopt.dive.data.remote.service.AuthService
import com.sopt.dive.data.remote.service.ReqresService
import com.sopt.dive.data.remote.service.UserService
import com.sopt.dive.data.repositoryimpl.AuthRepositoryImpl
import com.sopt.dive.data.repositoryimpl.ReqresRepositoryImpl
import com.sopt.dive.data.repositoryimpl.UserRepositoryImpl
import com.sopt.dive.domain.repository.AuthRepository
import com.sopt.dive.domain.repository.ReqresRepository
import com.sopt.dive.domain.repository.UserRepository

class AppContainer(
    context: Context
) {
    private val database by lazy { AppDatabase.getDatabase(context) }

    val userPreferencesRepository: UserPreferencesRepository by lazy {
        UserPreferencesRepository(context)
    }

    private val authService: AuthService = ApiFactory.createDiveService<AuthService>()
    private val authRemoteDataSource = AuthRemoteDataSourceImpl(authService)

    val authRepository: AuthRepository by lazy {
        AuthRepositoryImpl(authRemoteDataSource)
    }


    private val userDao = database.userDao()
    private val userLocalDataSource = UserLocalDataSourceImpl(userDao)

    private val userService: UserService = ApiFactory.createDiveService<UserService>()
    private val userRemoteDataSource = UserRemoteDataSourceImpl(userService)

    val userRepository: UserRepository by lazy {
        UserRepositoryImpl(
            userRemoteDataSource = userRemoteDataSource,
            userLocalDataSource = userLocalDataSource
        )
    }

    private val friendDao = database.friendDao()
    private val reqresLocalDataSource = ReqresLocalDataSourceImpl(friendDao)

    private val reqresService: ReqresService = ApiFactory.createOpenApiService<ReqresService>()
    private val reqresRemoteDataSource = ReqresRemoteDataSourceImpl(reqresService)

    val reqresRepository: ReqresRepository by lazy {
        ReqresRepositoryImpl(
            reqresRemoteDataSource = reqresRemoteDataSource,
            reqresLocalDataSource = reqresLocalDataSource
        )
    }
}