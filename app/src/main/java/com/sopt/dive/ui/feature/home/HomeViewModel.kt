package com.sopt.dive.ui.feature.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.sopt.dive.data.local.UserPrefs
import com.sopt.dive.domain.model.ProfileInfo

class HomeViewModel: ViewModel() {
    private var _uiState by mutableStateOf(HomeUiState())
    val uiState
        get() = _uiState

    init {
        loadData()
    }

    private fun loadData() {
        val dummyUserProfile = ProfileInfo(
            profileImageUrl = "https://i.pinimg.com/736x/96/37/2d/96372ded13d1e6b17cdf10b4ecb23483.jpg",
            name = UserPrefs.getName() ?: ""
        )
        val dummyFriendList = listOf(
            ProfileInfo(
                profileImageUrl = "https://i.pinimg.com/736x/22/42/9b/22429b5137154dbefb84162d58d3f76b.jpg",
                name = "이지현",
                statusMessage = "안녕하세요",
                music = "COLOR - NCT WISH"
            ),
            ProfileInfo(
                name = "이지현",
                isBirthday = true
            ),
            ProfileInfo(
                profileImageUrl = "https://i.pinimg.com/736x/e6/17/fa/e617fa85c330cba8abbf56e11c542913.jpg",
                name = "이지현",
                music = "Drowning - WOODZ"
            ),
            ProfileInfo(
                profileImageUrl = "https://i.pinimg.com/736x/22/42/9b/22429b5137154dbefb84162d58d3f76b.jpg",
                name = "이지현",
                statusMessage = "안녕하세요"
            ),
            ProfileInfo(
                name = "이지현",
                statusMessage = "생일이다아",
                isBirthday = true
            ),
            ProfileInfo(
                profileImageUrl = "https://i.pinimg.com/736x/e6/17/fa/e617fa85c330cba8abbf56e11c542913.jpg",
                name = "이지현",
                music = "FAMOUS - ALLDAY PROJECT"
            ),
            ProfileInfo(
                profileImageUrl = "https://i.pinimg.com/736x/22/42/9b/22429b5137154dbefb84162d58d3f76b.jpg",
                name = "이지현",
                statusMessage = "안녕하세요",
            ),
            ProfileInfo(
                name = "이지현",
                isBirthday = true
            ),
            ProfileInfo(
                profileImageUrl = "https://i.pinimg.com/736x/e6/17/fa/e617fa85c330cba8abbf56e11c542913.jpg",
                name = "이지현",
                isBirthday = true,
                music = "Drowning - WOODZ"
            ),
            ProfileInfo(
                profileImageUrl = "https://i.pinimg.com/736x/22/42/9b/22429b5137154dbefb84162d58d3f76b.jpg",
                name = "이지현",
                statusMessage = "안녕하세요",
                music = "Blue Valentine - NMIXX"
            ),
            ProfileInfo(
                name = "이지현",
                isBirthday = true
            ),
            ProfileInfo(
                profileImageUrl = "https://i.pinimg.com/736x/e6/17/fa/e617fa85c330cba8abbf56e11c542913.jpg",
                name = "이지현"
            )
        )

        _uiState = _uiState.copy(
            userProfile = dummyUserProfile,
            friendList = dummyFriendList
        )
    }
}