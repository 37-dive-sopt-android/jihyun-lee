package com.sopt.dive.ui.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.dive.data.local.UserPrefs
import com.sopt.dive.data.network.ServicePool
import com.sopt.dive.domain.model.ProfileInfo
import com.sopt.dive.domain.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel: ViewModel() {
    private val userRepository: UserRepository = ServicePool.userRepository

    private var _uiState = MutableStateFlow(HomeUiState())
    val uiState = _uiState.asStateFlow()

    fun loadData(id: Int) {
        viewModelScope.launch {
            UserPrefs.setProfileImageUrl("https://i.pinimg.com/736x/96/37/2d/96372ded13d1e6b17cdf10b4ecb23483.jpg")

            userRepository.getUserInfo(id)
                .onSuccess { response ->
                    _uiState.update {
                        it.copy(
                            userProfile = ProfileInfo(
                                profileImageUrl = UserPrefs.getProfileImageUrl(),
                                name = response.name
                            ),
                            friendList = dummyFriendList
                        )
                    }
                }
                .onFailure { exception ->
                    exception.printStackTrace()
                }
        }
    }
}