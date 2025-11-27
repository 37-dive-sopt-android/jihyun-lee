package com.sopt.dive.presentation.ui.mypage

import com.sopt.dive.domain.model.UserModel

data class MyPageUiState(
    val userInfo: UserModel = UserModel(
        id = 0,
        userId = "",
        name = "",
        email = "",
        age = 0
    )
)
