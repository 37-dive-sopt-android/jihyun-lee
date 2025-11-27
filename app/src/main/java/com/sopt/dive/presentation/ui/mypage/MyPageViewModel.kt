package com.sopt.dive.presentation.ui.mypage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.dive.data.local.datastore.UserPreferencesRepository
import com.sopt.dive.domain.model.UserModel
import com.sopt.dive.domain.repository.UserRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import timber.log.Timber

class MyPageViewModel(
    private val userRepository: UserRepository,
    private val userPreferencesRepository: UserPreferencesRepository
) : ViewModel() {
    val uiState: StateFlow<MyPageUiState> = userPreferencesRepository.id
        .flatMapLatest { id ->
            if (id==0) {
                flowOf(null)
            } else {
                userRepository.getUserStream(id)
            }
        }
        .map { userModel ->
            MyPageUiState(
                userInfo = userModel ?: UserModel(
                    id = 0,
                    userId = "",
                    name = "",
                    email = "",
                    age = 0
                )
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = MyPageUiState()
        )


    private val _sideEffect = MutableSharedFlow<MyPageSideEffect>()
    val sideEffect: SharedFlow<MyPageSideEffect> = _sideEffect.asSharedFlow()

    fun loadUserInfo() {
        viewModelScope.launch {
            val id = userPreferencesRepository.id.first()
            if (id==0) return@launch

            val currentUser = userRepository.getUserStream(id).first()

            if (currentUser==null) {
                userRepository.updateUserInfo(id)
                    .onFailure { e -> e.printStackTrace() }
            } else {
                Timber.tag("TEST").d("MyPage: 로컬 사용자 데이터가 존재하여 서버 통신을 건너뜁니다.")
            }
        }
    }

    fun refreshUserInfo() {
        viewModelScope.launch {
            val id = userPreferencesRepository.id.first()
            if (id==0) return@launch
            userRepository.updateUserInfo(id)
        }
    }

    fun logout() {
        viewModelScope.launch {
            userPreferencesRepository.logOut()
            _sideEffect.emit(MyPageSideEffect.NavigateToLogin)
        }
    }

    fun withdraw() {
        viewModelScope.launch {
            val id = userPreferencesRepository.id.first()
            if (id==0) return@launch

            userRepository.withdraw(id)
                .onSuccess {
                    userPreferencesRepository.withdraw()
                    _sideEffect.emit(MyPageSideEffect.NavigateToLogin)
                }
                .onFailure { exception ->
                    exception.printStackTrace()
                }
        }
    }
}