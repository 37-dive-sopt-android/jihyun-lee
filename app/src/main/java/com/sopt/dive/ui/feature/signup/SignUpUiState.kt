package com.sopt.dive.ui.feature.signup

import com.sopt.dive.R
import com.sopt.dive.ui.model.TextFieldValidState

data class SignUpUiState(
    val id: String = "",
    val password: String = "",
    val name: String = "",
    val nickname: String = "",
    val mbti: String = "",
    val isNameTouched: Boolean = false,
    val isNicknameTouched: Boolean = false
) {
    val idValidType: TextFieldValidState
        get() = when {
            id.isEmpty() -> TextFieldValidState.DEFAULT
            id.length in 6..10 -> TextFieldValidState.VALID
            else -> TextFieldValidState.INVALID(R.string.signup_id_fail_message)
        }

    val passwordValidType: TextFieldValidState
        get() = when {
            password.isEmpty() -> TextFieldValidState.DEFAULT
            password.length in 8..12 -> TextFieldValidState.VALID
            else -> TextFieldValidState.INVALID(R.string.signup_pw_fail_message)
        }

    val nameValidType: TextFieldValidState
        get() = when {
            !isNameTouched -> TextFieldValidState.DEFAULT
            name.isNotBlank() -> TextFieldValidState.VALID
            else -> TextFieldValidState.INVALID(R.string.signup_name_fail_message)
        }

    val nicknameValidType: TextFieldValidState
        get() = when {
            !isNicknameTouched -> TextFieldValidState.DEFAULT
            nickname.isNotBlank() -> TextFieldValidState.VALID
            else -> TextFieldValidState.INVALID(R.string.signup_nickname_fail_message)
        }

    val mbtiValidType: TextFieldValidState
        get() = when {
            mbti.isEmpty() -> TextFieldValidState.DEFAULT
            mbti.length == 4 -> TextFieldValidState.VALID
            else -> TextFieldValidState.INVALID(R.string.signup_mbti_fail_message)
        }

    val isSignUpEnabled: Boolean
        get() = idValidType == TextFieldValidState.VALID &&
                passwordValidType == TextFieldValidState.VALID &&
                nameValidType == TextFieldValidState.VALID &&
                nicknameValidType == TextFieldValidState.VALID &&
                mbtiValidType == TextFieldValidState.VALID
}
