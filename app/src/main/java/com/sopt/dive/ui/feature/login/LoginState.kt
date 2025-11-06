package com.sopt.dive.ui.feature.login

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.sopt.dive.R
import com.sopt.dive.data.local.UserPrefs

@Composable
fun rememberLoginState(
    onNavigateToHome: () -> Unit,
    context: Context = LocalContext.current
): LoginState = remember(context, onNavigateToHome) {
    LoginState(context, onNavigateToHome)
}

@Stable
class LoginState(
    private val context: Context,
    private val onNavigateToHome: () -> Unit
) {
    var userId by mutableStateOf("")
    var userPassword by mutableStateOf("")
    val isLoginEnabled by derivedStateOf {
        userId.isNotBlank() && userPassword.isNotBlank()
    }
    var isPasswordVisible by mutableStateOf(false)
    val visualTransformation: VisualTransformation by derivedStateOf {
        if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation()
    }

    val uiState by derivedStateOf {
        LoginUiState(
            id = userId,
            password = userPassword,
            isLoginEnabled = isLoginEnabled,
            isPasswordVisible = isPasswordVisible,
            visualTransformation = visualTransformation
        )
    }

    fun onIdChange(newId:String) {
        userId = newId
    }

    fun onPasswordChange(newPassword: String) {
        userPassword = newPassword
    }

    fun onPasswordToggleClick(){
        isPasswordVisible = !isPasswordVisible
    }

    fun onLoginClick(){
        val registeredId = UserPrefs.getId()
        val registeredPassword = UserPrefs.getPassword()

        if (userId == registeredId && userPassword == registeredPassword) {
            Toast.makeText(context, context.getString(R.string.login_success_message), Toast.LENGTH_SHORT,).show()
            UserPrefs.setLoggedIn(value = true)
            onNavigateToHome()
        } else {
            Toast.makeText(context, context.getString(R.string.login_fail_message), Toast.LENGTH_SHORT).show()
        }

    }
}