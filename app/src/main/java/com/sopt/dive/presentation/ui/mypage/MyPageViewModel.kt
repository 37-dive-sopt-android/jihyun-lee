package com.sopt.dive.presentation.ui.mypage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.dive.data.local.UserPrefs
import com.sopt.dive.data.network.ServicePool
import com.sopt.dive.domain.model.UserModel
import com.sopt.dive.domain.repository.UserRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MyPageViewModel : ViewModel() {
    private val userRepository: UserRepository = ServicePool.userRepository

    private val _uiState = MutableStateFlow(MyPageUiState())
    val uiState: StateFlow<MyPageUiState> = _uiState.asStateFlow()

    private val _sideEffect = MutableSharedFlow<MyPageSideEffect>()
    val sideEffect: SharedFlow<MyPageSideEffect> = _sideEffect.asSharedFlow()

    init {
        loadUserInfo()
    }

    private fun loadUserInfo() {
        viewModelScope.launch {
            val id = UserPrefs.getId()
            if (id != null) {
                userRepository.getUserInfo(id)
                    .onSuccess { response ->
                        _uiState.update {
                            it.copy(
                                userInfo = UserModel(
                                    id = response.id,
                                    userId = response.userId,
                                    name = response.name,
                                    email = response.email,
                                    age = response.age
                                )
                            )
                        }
                    }
                    .onFailure { exception ->
                        exception.printStackTrace()
                    }
            }
        }
    }

    fun logout() {
        viewModelScope.launch {
            UserPrefs.logout()
            _sideEffect.emit(MyPageSideEffect.NavigateToLogin)
        }
    }

    fun withdraw() {
        viewModelScope.launch {
            val id = UserPrefs.getId()
            if (id != null) {
                userRepository.withdraw(id)
                    .onFailure { exception ->
                        exception.printStackTrace()
                    }
            }
            UserPrefs.withdraw()
            _sideEffect.emit(MyPageSideEffect.NavigateToLogin)
        }
    }
}