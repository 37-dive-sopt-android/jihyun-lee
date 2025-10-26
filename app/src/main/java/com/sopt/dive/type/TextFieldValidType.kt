package com.sopt.dive.type

import androidx.annotation.StringRes
import androidx.compose.ui.graphics.Color
import com.sopt.dive.R

enum class TextFieldValidType(
    @StringRes val messageResId: Int?,
    val color: Color = Color.Red
) {
    ID_INVALID(messageResId = R.string.signup_id_fail_message),
    PASSWORD_INVALID(messageResId = R.string.signup_pw_fail_message),
    INVALID(messageResId = null),
    DEFAULT(messageResId = null, color = Color.LightGray)
}