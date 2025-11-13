package com.sopt.dive.ui.feature.login

sealed interface LoginSideEffect {
    data object NavigateToHome : LoginSideEffect
    data class ShowToast(val messageResId: Int) : LoginSideEffect
}