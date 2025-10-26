package com.sopt.dive.ui.signup

import com.sopt.dive.type.TextFieldValidType

data class SignUpUiState(
    val id: String = "",
    val password: String = "",
    val name: String = "",
    val nickname: String = "",
    val mbti: String = "",
    val idError: String? = null,
    val passwordError: String? = null,
    val nameError: Boolean = false,
    val nicknameError: Boolean = false,
    val mbtiError: Boolean = false,
    val textFieldValidType: TextFieldValidType = TextFieldValidType.DEFAULT
) {
    val isSignUpEnabled: Boolean =
        idError == null && passwordError == null && !nameError && !nicknameError && !mbtiError
}
