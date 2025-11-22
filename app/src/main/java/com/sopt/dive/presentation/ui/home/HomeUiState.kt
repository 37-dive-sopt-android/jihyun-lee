package com.sopt.dive.presentation.ui.home

import com.sopt.dive.domain.model.ProfileModel
import com.sopt.dive.domain.model.ReqresUserModel

data class HomeUiState(
    val userProfile: ProfileModel = ProfileModel(),
    val friendList: List<ReqresUserModel> = emptyList()
)
