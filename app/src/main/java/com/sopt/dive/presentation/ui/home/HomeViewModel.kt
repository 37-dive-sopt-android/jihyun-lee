package com.sopt.dive.presentation.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.dive.data.local.datastore.UserPreferencesRepository
import com.sopt.dive.domain.model.ProfileModel
import com.sopt.dive.domain.repository.ReqresRepository
import com.sopt.dive.domain.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber

class HomeViewModel(
    private val userRepository: UserRepository,
    private val reqresRepository: ReqresRepository,
    private val userPreferencesRepository: UserPreferencesRepository
) : ViewModel() {
    private var _uiState = MutableStateFlow(HomeUiState())

    val uiState: StateFlow<HomeUiState> = combine(
        _uiState,
        reqresRepository.getFriendsStream()
    ) { state, friends ->
        state.copy(
            friendList = friends
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = HomeUiState()
    )

    fun loadData() {
        viewModelScope.launch {
            val id = userPreferencesRepository.id.first()
            if(id == 0) return@launch

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
            val currentFriends = reqresRepository.getFriendsStream().first()

            if (currentFriends.isEmpty()) {
                reqresRepository.updateFriends(0, 0)
                    .onFailure { e -> e.printStackTrace() }
            } else {
                Timber.tag("LOG").d("로컬 DB에 데이터가 있어서 서버 통신을 스킵합니다.")
            }
        }
    }

    fun refreshData() {
        viewModelScope.launch {
            reqresRepository.updateFriends(0, 0)
                .onFailure { e -> e.printStackTrace() }
        }
    }
}