package com.sopt.dive.presentation.ui.signup

sealed interface SignUpSideEffect {
    data object NavigateToLogin : SignUpSideEffect
    data class ShowToastResId(val messageResId: Int) : SignUpSideEffect
    data class ShowToastString(val message: String) : SignUpSideEffect
}