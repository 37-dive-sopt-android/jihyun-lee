package com.sopt.dive.ui.feature.signup

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
import com.sopt.dive.R
import com.sopt.dive.data.local.UserPrefs
import com.sopt.dive.ui.model.TextFieldValidState

@Composable
fun rememberSignUpState(
    onNavigateToLogin: () -> Unit,
    context: Context = LocalContext.current
): SignUpState = remember(onNavigateToLogin, context) {
    SignUpState(
        context = context,
        onNavigateToLogin = onNavigateToLogin
    )
}

@Stable
class SignUpState (
    private val context: Context,
    private val onNavigateToLogin: () -> Unit
) {
    var id by mutableStateOf("")
    var password by mutableStateOf("")
    var name by mutableStateOf("")
    var nickname by mutableStateOf("")
    var mbti by mutableStateOf("")
    private var isNameTouched by mutableStateOf(false)
    private var isNicknameTouched by mutableStateOf(false)

    val idValidType by derivedStateOf {
        when {
            id.isEmpty() -> TextFieldValidState.DEFAULT
            id.length in 6..10 -> TextFieldValidState.VALID
            else -> TextFieldValidState.INVALID(R.string.signup_id_fail_message)
        }
    }
    val passwordValidType by derivedStateOf {
        when {
            password.isEmpty() -> TextFieldValidState.DEFAULT
            password.length in 8..12 -> TextFieldValidState.VALID
            else -> TextFieldValidState.INVALID(R.string.signup_pw_fail_message)
        }
    }
    val nameValidType by derivedStateOf {
        when {
            !isNameTouched -> TextFieldValidState.DEFAULT
            name.isNotBlank() -> TextFieldValidState.VALID
            else -> TextFieldValidState.INVALID(R.string.signup_name_fail_message)
        }
    }
    val nicknameValidType by derivedStateOf {
        when {
            !isNicknameTouched -> TextFieldValidState.DEFAULT
            nickname.isNotBlank() -> TextFieldValidState.VALID
            else -> TextFieldValidState.INVALID(R.string.signup_nickname_fail_message)
        }
    }
    val mbtiValidType by derivedStateOf {
        when {
            mbti.isEmpty() -> TextFieldValidState.DEFAULT
            mbti.length == 4 -> TextFieldValidState.VALID
            else -> TextFieldValidState.INVALID(R.string.signup_mbti_fail_message)
        }
    }

    val uiState: SignUpUiState
        get() = SignUpUiState(
            id = id,
            password = password,
            name = name,
            nickname = nickname,
            mbti = mbti,
            idValidType = idValidType,
            passwordValidType = passwordValidType,
            nameValidType = nameValidType,
            nicknameValidType = nicknameValidType,
            mbtiValidType = mbtiValidType
        )

    fun onIdChange(newId: String) { id = newId }
    fun onPwChange(newPw: String) { password = newPw }
    fun onNameChange(newName: String) {
        name = newName
        isNameTouched = true
    }
    fun onNicknameChange(newNickname: String) {
        nickname = newNickname
        isNicknameTouched = true
    }
    fun onMbtiChange(newMbti: String) { mbti = newMbti }

    fun onSignUpButtonClick() {
        if (!uiState.isSignUpEnabled) return

        UserPrefs.saveUserInfo(
            id = id,
            password = password,
            name = name,
            nickname = nickname,
            mbti = mbti
        )
        Toast.makeText(
            context,
            context.getString(R.string.signup_success_message),
            Toast.LENGTH_SHORT,
        ).show()
        onNavigateToLogin()
    }
}