package com.sopt.dive.presentation.ui.signup

import android.util.Patterns
import com.sopt.dive.R
import com.sopt.dive.presentation.model.TextFieldValidState

data class SignUpUiState(
    val id: String = "",
    val password: String = "",
    val name: String = "",
    val email: String = "",
    val age: Int? = null,
    val isIdTouched: Boolean = false,
    val isPasswordTouched: Boolean = false,
    val isNameTouched: Boolean = false,
    val isEmailTouched: Boolean = false,
    val isAgeTouched: Boolean = false
) {
    companion object {
        private val PASSWORD_REGEX =
            Regex("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!_.,?])(?=\\S+$).{8,64}$")
    }

    val idValidType: TextFieldValidState
        get() = when {
            !isIdTouched -> TextFieldValidState.DEFAULT
            id.length in 6..10 -> TextFieldValidState.VALID
            else -> TextFieldValidState.INVALID(R.string.signup_id_fail_message)
        }

    val passwordValidType: TextFieldValidState
        get() = when {
            !isPasswordTouched -> TextFieldValidState.DEFAULT
            PASSWORD_REGEX.matches(password) -> TextFieldValidState.VALID
            else -> TextFieldValidState.INVALID(R.string.signup_pw_fail_message)
        }

    val nameValidType: TextFieldValidState
        get() = when {
            !isNameTouched -> TextFieldValidState.DEFAULT
            name.isNotBlank() -> TextFieldValidState.VALID
            else -> TextFieldValidState.INVALID(R.string.signup_name_fail_message)
        }

    val emailValidType: TextFieldValidState
        get() = when {
            !isEmailTouched -> TextFieldValidState.DEFAULT
            Patterns.EMAIL_ADDRESS.matcher(email).matches() -> TextFieldValidState.VALID
            else -> TextFieldValidState.INVALID(R.string.signup_email_fail_message)
        }

    val ageValidType: TextFieldValidState
        get() = when {
            !isAgeTouched -> TextFieldValidState.DEFAULT
            (age == null) -> TextFieldValidState.INVALID(R.string.signup_age_fail_message)
            (age > 0) -> TextFieldValidState.VALID
            else -> TextFieldValidState.INVALID(R.string.signup_age_fail_message)
        }

    val isSignUpEnabled: Boolean
        get() = idValidType == TextFieldValidState.VALID &&
                passwordValidType == TextFieldValidState.VALID &&
                nameValidType == TextFieldValidState.VALID &&
                emailValidType == TextFieldValidState.VALID &&
                ageValidType == TextFieldValidState.VALID
}
