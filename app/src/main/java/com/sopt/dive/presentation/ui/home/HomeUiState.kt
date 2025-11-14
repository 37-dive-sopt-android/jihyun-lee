package com.sopt.dive.presentation.ui.home

import com.sopt.dive.domain.model.ProfileInfo

data class HomeUiState(
    val userProfile: ProfileInfo = ProfileInfo(),
    val friendList: List<ProfileInfo> = emptyList()
)
