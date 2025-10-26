package com.sopt.dive.ui.signup

import com.sopt.dive.domain.type.TextFieldValidState

data class SignUpUiState(
    val id: String = "",
    val password: String = "",
    val name: String = "",
    val nickname: String = "",
    val mbti: String = "",
    val idValidType: TextFieldValidState = TextFieldValidState.DEFAULT,
    val passwordValidType: TextFieldValidState = TextFieldValidState.DEFAULT,
    val nameValidType: TextFieldValidState = TextFieldValidState.DEFAULT,
    val nicknameValidType: TextFieldValidState = TextFieldValidState.DEFAULT,
    val mbtiValidType: TextFieldValidState = TextFieldValidState.DEFAULT
) {
    val isSignUpEnabled: Boolean
        get() = idValidType == TextFieldValidState.VALID &&
                passwordValidType == TextFieldValidState.VALID &&
                nameValidType == TextFieldValidState.VALID &&
                nicknameValidType == TextFieldValidState.VALID &&
                mbtiValidType == TextFieldValidState.VALID
}
