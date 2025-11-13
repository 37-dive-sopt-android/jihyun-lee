package com.sopt.dive.data.local

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.sopt.dive.domain.model.UserInfo

object UserPrefs {
    private const val FILE = "user_prefs"
    private const val IS_LOGGED_IN = "is_logged_in"
    private const val ID = "id"
    private const val PASSWORD = "password"
    private const val NAME = "name"
    private const val EMAIL = "email"
    private const val AGE = "age"
    private const val PROFILE_IMAGE_URL = "profile_image_url"

    private lateinit var prefs: SharedPreferences

    fun init(context: Context) {
        prefs = context.applicationContext.getSharedPreferences(FILE, Context.MODE_PRIVATE)
    }

    fun saveUserInfo(
        id: String,
        password: String,
        name: String,
        email: String,
        age: Int,
        profileImageUrl: String = ""
    ) {
        prefs.edit {
            putString(ID, id)
            putString(PASSWORD, password)
            putString(NAME, name)
            putString(EMAIL, email)
            putInt(AGE, age)
            putString(PROFILE_IMAGE_URL, profileImageUrl)
        }
    }

    fun setLoggedIn(
        value: Boolean,
    ) = prefs.edit { putBoolean(IS_LOGGED_IN, value) }

    fun isLoggedIn(): Boolean = prefs.getBoolean(IS_LOGGED_IN, false)

    fun getId(): String? = prefs.getString(ID, null)

    fun getPassword(): String? = prefs.getString(PASSWORD, null)

    fun getName(): String? = prefs.getString(NAME, null)

    fun getEmail(): String? = prefs.getString(EMAIL, null)

    fun getAge(): Int? {
        val age = prefs.getInt(AGE, 0)
        return if (age == 0) null else age
    }

    fun getProfileImageUrl(): String? = prefs.getString(PROFILE_IMAGE_URL, null)
    fun setProfileImageUrl(url: String) {
        prefs.edit {
            putString(PROFILE_IMAGE_URL, url)
        }
    }

    fun loadUser(): UserInfo? {
        val id = getId() ?: return null
        val pw = getPassword() ?: ""
        val name = getName() ?: ""
        val email = getEmail() ?: ""
        val age = getAge()
        val url = getProfileImageUrl() ?: ""
        return UserInfo(id, pw, name, email, age, url)
    }

    fun logout() {
        setLoggedIn(false)
    }

    fun withdraw() {
        prefs.edit { clear() }
    }
}
