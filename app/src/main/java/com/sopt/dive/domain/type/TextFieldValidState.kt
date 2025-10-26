package com.sopt.dive.domain.type

import androidx.annotation.StringRes
import androidx.compose.ui.graphics.Color

sealed class TextFieldValidState(
    val color: Color = Color.LightGray
) {
    data object DEFAULT : TextFieldValidState()
    data object VALID : TextFieldValidState()
    data class INVALID(
        @StringRes val messageResId: Int,
        val errorColor: Color = Color.Red
    ) : TextFieldValidState(color = errorColor)
}