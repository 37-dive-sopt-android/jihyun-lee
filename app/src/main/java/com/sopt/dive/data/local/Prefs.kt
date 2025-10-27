package com.sopt.dive.data.local

import android.content.Context
import androidx.core.content.edit

object Prefs {
    private const val FILE = "user_prefs"
    private const val IS_LOGGED_IN = "is_logged_in"
    private const val ID = "id"
    private const val PASSWORD = "password"
    private const val NAME = "name"
    private const val NICKNAME = "nickname"
    private const val MBTI = "mbti"

    private fun sp(context: Context) = context.getSharedPreferences(FILE, Context.MODE_PRIVATE)

    fun saveUserInfo(
        context: Context,
        id: String,
        password: String,
        name: String,
        nickname: String,
        mbti: String,
    ) {
        sp(context).edit {
            putString(ID, id)
            putString(PASSWORD, password)
            putString(NAME, name)
            putString(NICKNAME, nickname)
            putString(MBTI, mbti)
        }
    }

    fun setLoggedIn(
        context: Context,
        value: Boolean,
    ) = sp(context).edit { putBoolean(IS_LOGGED_IN, value) }

    fun isLoggedIn(context: Context): Boolean = sp(context).getBoolean(IS_LOGGED_IN, false)

    fun getId(context: Context): String? = sp(context).getString(ID, null)

    fun getPassword(context: Context): String? = sp(context).getString(PASSWORD, null)

    fun getName(context: Context): String? = sp(context).getString(NAME, null)

    fun getNickname(context: Context): String? = sp(context).getString(NICKNAME, null)

    fun getMbti(context: Context): String? = sp(context).getString(MBTI, null)

    data class User(
        val id: String,
        val password: String,
        val name: String,
        val nickname: String,
        val mbti: String,
    )

    fun loadUser(context: Context): User? {
        val id = getId(context) ?: return null
        val pw = getPassword(context) ?: ""
        val name = getName(context) ?: ""
        val nick = getNickname(context) ?: ""
        val mbti = getMbti(context) ?: ""
        return User(id, pw, name, nick, mbti)
    }

    fun logout(context: Context) {
        sp(context).edit { clear() }
    }
}
