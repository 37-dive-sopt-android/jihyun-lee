package com.sopt.dive.ui.feature.mypage

sealed interface MyPageSideEffect {
    data object NavigateToLogin : MyPageSideEffect
}