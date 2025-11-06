package com.sopt.dive.ui.model

import androidx.annotation.StringRes
import androidx.compose.ui.graphics.Color
import com.sopt.dive.ui.theme.defaultDiveColors

sealed class TextFieldValidState(
    val color: Color = defaultDiveColors.gray300
) {
    data object DEFAULT : TextFieldValidState()
    data object VALID : TextFieldValidState()
    data class INVALID(
        @StringRes val messageResId: Int,
        val errorColor: Color = defaultDiveColors.error
    ) : TextFieldValidState(color = errorColor)
}