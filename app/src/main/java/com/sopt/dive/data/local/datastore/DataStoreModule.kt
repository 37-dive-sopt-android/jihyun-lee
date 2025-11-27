package com.sopt.dive.data.local.datastore

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "user_preferences")

class UserPreferencesRepository(private val context: Context) {
    private val dataStore = context.dataStore

    private val ID = intPreferencesKey("id")
    private val IS_LOGGED_IN = booleanPreferencesKey("is_logged_in")

    val id: Flow<Int> = dataStore.data.map { preferences ->
        preferences[ID] ?: 0
    }

    suspend fun setId(id: Int) = dataStore.edit { preferences ->
        preferences[ID] = id
    }

    suspend fun setLoggedIn(isLoggIn: Boolean) = dataStore.edit { preferences ->
        preferences[IS_LOGGED_IN] = isLoggIn
    }

    suspend fun logOut() = dataStore.edit { preferences ->
        preferences.clear()
    }

    suspend fun withdraw() = dataStore.edit { preferences ->
        preferences.clear()
    }
}