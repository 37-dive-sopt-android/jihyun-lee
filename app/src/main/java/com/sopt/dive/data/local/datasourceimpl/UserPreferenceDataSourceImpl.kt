package com.sopt.dive.data.local.datasourceimpl

import androidx.datastore.core.DataStore
import androidx.datastore.core.IOException
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.intPreferencesKey
import com.sopt.dive.data.local.datasource.UserPreferenceDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

class UserPreferenceDataSourceImpl(
    private val dataStore: DataStore<Preferences>
) : UserPreferenceDataSource {
    private val ID = intPreferencesKey("id")
    private val IS_LOGGED_IN = booleanPreferencesKey("is_logged_in")

    override val id: Flow<Int> = dataStore.data
        .catch { exception ->
            if (exception is IOException) emit(emptyPreferences())
            else throw exception
        }
        .map { preferences ->
            preferences[ID] ?: 0
        }

    override val isLoggedIn: Flow<Boolean> = dataStore.data
        .catch { exception ->
            if (exception is IOException) emit(emptyPreferences())
            else throw exception
        }
        .map { preferences ->
            preferences[IS_LOGGED_IN]==true
        }

    override suspend fun setId(id: Int) {
        dataStore.edit { preferences ->
            preferences[ID] = id
        }
    }

    override suspend fun setLoggedIn(isLoggedIn: Boolean) {
        dataStore.edit { preferences ->
            preferences[IS_LOGGED_IN] = isLoggedIn
        }
    }

    override suspend fun clear() {
        dataStore.edit { preferences ->
            preferences.clear()
        }
    }

}