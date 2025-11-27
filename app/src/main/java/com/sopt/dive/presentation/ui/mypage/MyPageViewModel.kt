package com.sopt.dive.presentation.ui.mypage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.dive.data.local.datastore.UserPreferencesRepository
import com.sopt.dive.domain.model.UserModel
import com.sopt.dive.domain.repository.UserRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MyPageViewModel(
    private val userRepository: UserRepository,
    private val userPreferencesRepository: UserPreferencesRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(MyPageUiState())
    val uiState: StateFlow<MyPageUiState> = _uiState.asStateFlow()

    private val _sideEffect = MutableSharedFlow<MyPageSideEffect>()
    val sideEffect: SharedFlow<MyPageSideEffect> = _sideEffect.asSharedFlow()

    fun loadUserInfo() {
        viewModelScope.launch {
            val id = userPreferencesRepository.id.first()
            if(id == 0) return@launch

            userRepository.getUserInfo(id)
                .onSuccess { result ->
                    _uiState.update {
                        it.copy(
                            userInfo = UserModel(
                                id = result.id,
                                userId = result.userId,
                                name = result.name,
                                email = result.email,
                                age = result.age,
                                profileImageUrl = result.profileImageUrl
                            )
                        )
                    }
                }
                .onFailure { exception ->
                    exception.printStackTrace()
                }
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
            if(id == 0) return@launch

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