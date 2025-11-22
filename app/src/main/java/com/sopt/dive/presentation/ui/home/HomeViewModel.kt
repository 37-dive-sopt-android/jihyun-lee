package com.sopt.dive.presentation.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.dive.data.local.UserPrefs
import com.sopt.dive.domain.model.ProfileModel
import com.sopt.dive.domain.repository.ReqresRepository
import com.sopt.dive.domain.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val userRepository: UserRepository,
    private val reqresRepository: ReqresRepository
) : ViewModel() {
    private var _uiState = MutableStateFlow(HomeUiState())
    val uiState = _uiState.asStateFlow()

    fun loadData() {
        viewModelScope.launch {
            val id = UserPrefs.getId() ?: return@launch

            userRepository.getUserInfo(id)
                .onSuccess { result ->
                    _uiState.update {
                        it.copy(
                            userProfile = ProfileModel(
                                profileImageUrl = result.profileImageUrl,
                                name = result.name
                            )
                        )
                    }
                }
                .onFailure { exception ->
                    exception.printStackTrace()
                }
        }

        viewModelScope.launch {
            reqresRepository.getUsers(0, 0)
                .onSuccess { result ->
                    _uiState.update {
                        it.copy(
                            friendList = result
                        )
                    }
                }
                .onFailure { e ->
                    e.printStackTrace()
                }
        }
    }
}