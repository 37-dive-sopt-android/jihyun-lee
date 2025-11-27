package com.sopt.dive.presentation.ui.login

import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation

data class LoginUiState(
    val id: String = "",
    val password: String = "",
    val isPasswordVisible: Boolean = false
) {
    val isLoginEnabled: Boolean
        get() = id.isNotBlank() && password.isNotBlank()

    val visualTransformation: VisualTransformation
        get() = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation()
}
