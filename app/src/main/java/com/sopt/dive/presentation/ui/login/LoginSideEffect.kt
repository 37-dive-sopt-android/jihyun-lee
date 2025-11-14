package com.sopt.dive.presentation.ui.login

sealed interface LoginSideEffect {
    data object NavigateToHome : LoginSideEffect
    data class ShowToastString(val message: String) : LoginSideEffect
    data class ShowToastResId(val messageResId: Int) : LoginSideEffect
}