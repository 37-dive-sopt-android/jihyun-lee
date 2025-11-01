package com.sopt.dive.ui.feature.login

import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation

data class LoginUiState(
    val id: String = "",
    val password: String = "",
    val isLoginEnabled: Boolean = false,
    val isPasswordVisible: Boolean = false,
    val visualTransformation: VisualTransformation = PasswordVisualTransformation()
)
