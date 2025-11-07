package com.sopt.dive.ui.feature.home

import androidx.lifecycle.ViewModel
import com.sopt.dive.data.local.UserPrefs
import com.sopt.dive.domain.model.ProfileInfo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class HomeViewModel: ViewModel() {
    private var _uiState = MutableStateFlow(HomeUiState())
    val uiState = _uiState.asStateFlow()

    init {
        loadData()
    }

    private fun loadData() {
        val dummyUserProfile = ProfileInfo(
            profileImageUrl = "https://i.pinimg.com/736x/96/37/2d/96372ded13d1e6b17cdf10b4ecb23483.jpg",
            name = UserPrefs.getName() ?: ""
        )

        _uiState.update { currentState ->
            currentState.copy(
                userProfile = dummyUserProfile,
                friendList = dummyFriendList
            )
        }
    }
}