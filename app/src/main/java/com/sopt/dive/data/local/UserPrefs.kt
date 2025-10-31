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
    private const val NICKNAME = "nickname"
    private const val MBTI = "mbti"

    private lateinit var prefs: SharedPreferences

    fun init(context: Context) {
        prefs = context.applicationContext.getSharedPreferences(FILE, Context.MODE_PRIVATE)
    }

    fun saveUserInfo(
        id: String,
        password: String,
        name: String,
        nickname: String,
        mbti: String,
    ) {
        prefs.edit {
            putString(ID, id)
            putString(PASSWORD, password)
            putString(NAME, name)
            putString(NICKNAME, nickname)
            putString(MBTI, mbti)
        }
    }

    fun setLoggedIn(
        value: Boolean,
    ) = prefs.edit { putBoolean(IS_LOGGED_IN, value) }

    fun isLoggedIn(): Boolean = prefs.getBoolean(IS_LOGGED_IN, false)

    fun getId(): String? = prefs.getString(ID, null)

    fun getPassword(): String? = prefs.getString(PASSWORD, null)

    fun getName(): String? = prefs.getString(NAME, null)

    fun getNickname(): String? = prefs.getString(NICKNAME, null)

    fun getMbti(): String? = prefs.getString(MBTI, null)

    fun loadUser(): UserInfo? {
        val id = getId() ?: return null
        val pw = getPassword() ?: ""
        val name = getName() ?: ""
        val nick = getNickname() ?: ""
        val mbti = getMbti() ?: ""
        return UserInfo(id, pw, name, nick, mbti)
    }

    fun logout() {
        prefs.edit { clear() }
    }
}
