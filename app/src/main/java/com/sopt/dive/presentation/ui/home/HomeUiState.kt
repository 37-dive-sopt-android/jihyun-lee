package com.sopt.dive.presentation.ui.home

import com.sopt.dive.domain.model.ProfileModel

data class HomeUiState(
    val userProfile: ProfileModel = ProfileModel(),
    val friendList: List<ProfileModel> = emptyList()
)
