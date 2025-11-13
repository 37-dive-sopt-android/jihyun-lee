package com.sopt.dive.data.local

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

object UserPrefs {
    private const val FILE = "user_prefs"
    private const val IS_LOGGED_IN = "is_logged_in"
    private const val ID = "id"
    private const val NAME = "name"
    private const val PROFILE_IMAGE_URL = "profile_image_url"

    private lateinit var prefs: SharedPreferences

    fun init(context: Context) {
        prefs = context.applicationContext.getSharedPreferences(FILE, Context.MODE_PRIVATE)
    }

    fun setLoggedIn(
        value: Boolean,
    ) = prefs.edit { putBoolean(IS_LOGGED_IN, value) }

    fun isLoggedIn(): Boolean = prefs.getBoolean(IS_LOGGED_IN, false)

    fun getId(): Int? {
        val id = prefs.getInt(ID, -1)
        return if (id == -1) null else id
    }

    fun setId(id: Int) {
        prefs.edit { putInt(ID, id) }
    }

    fun getName(): String? = prefs.getString(NAME, null)

    fun getProfileImageUrl(): String? = prefs.getString(PROFILE_IMAGE_URL, null)
    fun setProfileImageUrl(url: String) {
        prefs.edit {
            putString(PROFILE_IMAGE_URL, url)
        }
    }

    fun logout() {
        setLoggedIn(false)
    }

    fun withdraw() {
        prefs.edit { clear() }
    }
}
