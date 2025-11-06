package com.sopt.dive.ui.feature.signup

import com.sopt.dive.ui.model.TextFieldValidState

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
