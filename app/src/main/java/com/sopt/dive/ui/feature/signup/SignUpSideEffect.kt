package com.sopt.dive.ui.feature.signup

sealed interface SignUpSideEffect {
    data object NavigateToLogin : SignUpSideEffect
    data class ShowToast(val messageResId: Int) : SignUpSideEffect
}