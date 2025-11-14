package com.sopt.dive.presentation.ui.mypage

import com.sopt.dive.domain.model.UserInfo

data class MyPageUiState(
    val userInfo: UserInfo = UserInfo(
        id = 0,
        userId = "",
        name = "",
        email = "",
        age = 0
    )
)
