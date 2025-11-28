package com.sopt.dive.core.di

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import com.sopt.dive.data.local.AppDatabase
import com.sopt.dive.data.local.datasource.UserPreferenceDataSource
import com.sopt.dive.data.local.datasourceimpl.ReqresLocalDataSourceImpl
import com.sopt.dive.data.local.datasourceimpl.UserLocalDataSourceImpl
import com.sopt.dive.data.local.datasourceimpl.UserPreferenceDataSourceImpl
import com.sopt.dive.data.remote.datasourceimpl.AuthRemoteDataSourceImpl
import com.sopt.dive.data.remote.datasourceimpl.ReqresRemoteDataSourceImpl
import com.sopt.dive.data.remote.datasourceimpl.UserRemoteDataSourceImpl
import com.sopt.dive.data.network.ApiFactory
import com.sopt.dive.data.remote.datasource.AuthRemoteDataSource
import com.sopt.dive.data.remote.service.AuthService
import com.sopt.dive.data.remote.service.ReqresService
import com.sopt.dive.data.remote.service.UserService
import com.sopt.dive.data.repositoryimpl.AuthRepositoryImpl
import com.sopt.dive.data.repositoryimpl.ReqresRepositoryImpl
import com.sopt.dive.data.repositoryimpl.UserRepositoryImpl
import com.sopt.dive.domain.repository.AuthRepository
import com.sopt.dive.domain.repository.ReqresRepository
import com.sopt.dive.domain.repository.UserRepository

private val Context.dataStore by preferencesDataStore(name = "user_preferences")

class AppContainer(
    context: Context
) {
    private val dataStore by lazy { context.dataStore }
    private val database by lazy { AppDatabase.getDatabase(context) }

    private val userPreferenceDataSource: UserPreferenceDataSource by lazy { UserPreferenceDataSourceImpl(dataStore) }


    private val authService: AuthService by lazy { ApiFactory.createDiveService<AuthService>() }
    private val authRemoteDataSource: AuthRemoteDataSource by lazy { AuthRemoteDataSourceImpl(authService) }

    val authRepository: AuthRepository by lazy {
        AuthRepositoryImpl(
            authRemoteDataSource = authRemoteDataSource,
            userPreferenceDataSource = userPreferenceDataSource
        )
    }


    private val userDao by lazy { database.userDao() }
    private val userLocalDataSource by lazy { UserLocalDataSourceImpl(userDao) }

    private val userService: UserService by lazy { ApiFactory.createDiveService<UserService>() }
    private val userRemoteDataSource by lazy { UserRemoteDataSourceImpl(userService) }

    val userRepository: UserRepository by lazy {
        UserRepositoryImpl(
            userRemoteDataSource = userRemoteDataSource,
            userLocalDataSource = userLocalDataSource,
            userPreferenceDataSource = userPreferenceDataSource
        )
    }

    private val friendDao by lazy { database.friendDao() }
    private val reqresLocalDataSource by lazy { ReqresLocalDataSourceImpl(friendDao) }

    private val reqresService: ReqresService by lazy { ApiFactory.createOpenApiService<ReqresService>() }
    private val reqresRemoteDataSource by lazy { ReqresRemoteDataSourceImpl(reqresService) }

    val reqresRepository: ReqresRepository by lazy {
        ReqresRepositoryImpl(
            reqresRemoteDataSource = reqresRemoteDataSource,
            reqresLocalDataSource = reqresLocalDataSource
        )
    }
}