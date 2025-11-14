package com.sopt.dive.presentation.ui.mypage

sealed interface MyPageSideEffect {
    data object NavigateToLogin : MyPageSideEffect
}