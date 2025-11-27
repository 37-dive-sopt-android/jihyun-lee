package com.sopt.dive.presentation.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.dive.data.local.datastore.UserPreferencesRepository
import com.sopt.dive.domain.model.ProfileModel
import com.sopt.dive.domain.repository.ReqresRepository
import com.sopt.dive.domain.repository.UserRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import timber.log.Timber

class HomeViewModel(
    private val userRepository: UserRepository,
    private val reqresRepository: ReqresRepository,
    private val userPreferencesRepository: UserPreferencesRepository
) : ViewModel() {

    private val userProfileFlow = userPreferencesRepository.id
        .flatMapLatest { id ->
            if (id == 0) flowOf(null)
            else userRepository.getUserStream(id)
        }

    private val friendListFlow = reqresRepository.getFriendsStream()

    val uiState: StateFlow<HomeUiState> = combine(
        userProfileFlow,
        friendListFlow
    ) { user, friends ->
        HomeUiState(
            userProfile = user?.let {
                ProfileModel(
                    profileImageUrl = it.profileImageUrl,
                    name = it.name
                )
            } ?: ProfileModel(),
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

            val currentUser = userRepository.getUserStream(id).first()

            if (currentUser == null) {
                userRepository.updateUserInfo(id)
                    .onFailure { e -> e.printStackTrace() }
            } else {
                Timber.tag("TEST").d("Home: 로컬 사용자 데이터가 존재하여 서버 통신을 건너뜁니다.")
            }
        }

        viewModelScope.launch {
            val currentFriends = reqresRepository.getFriendsStream().first()

            if (currentFriends.isEmpty()) {
                reqresRepository.updateFriends(0, 0)
                    .onFailure { e -> e.printStackTrace() }
            } else {
                Timber.tag("TEST").d("Home: 로컬 DB에 친구  목록 데이터가 있어서 서버 통신을 스킵합니다.")
            }
        }
    }

    fun refreshData() {
        viewModelScope.launch {
            val id = userPreferencesRepository.id.first()
            if (id != 0) {
                userRepository.updateUserInfo(id)
            }
            reqresRepository.updateFriends(0, 0)
        }
    }
}