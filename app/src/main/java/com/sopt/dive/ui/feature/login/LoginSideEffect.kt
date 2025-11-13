package com.sopt.dive.ui.feature.login

sealed interface LoginSideEffect {
    data object NavigateToHome : LoginSideEffect
    data class ShowToastString(val message: String) : LoginSideEffect
    data class ShowToastResId(val messageResId: Int) : LoginSideEffect
}