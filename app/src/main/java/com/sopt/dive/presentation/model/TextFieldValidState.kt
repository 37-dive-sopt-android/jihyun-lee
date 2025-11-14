package com.sopt.dive.presentation.model

import androidx.annotation.StringRes
import androidx.compose.ui.graphics.Color
import com.sopt.dive.presentation.theme.defaultDiveColors

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