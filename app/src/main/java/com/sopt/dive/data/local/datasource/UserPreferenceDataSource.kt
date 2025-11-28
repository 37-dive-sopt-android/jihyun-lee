package com.sopt.dive.data.local.datasource

import kotlinx.coroutines.flow.Flow

interface UserPreferenceDataSource {
    val id: Flow<Int>
    val isLoggedIn: Flow<Boolean>

    suspend fun setId(id: Int)
    suspend fun setLoggedIn(isLoggedIn: Boolean)
    suspend fun clear()
}