package com.sopt.dive.presentation.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.dive.R
import com.sopt.dive.data.remote.dto.request.LoginRequestDto
import com.sopt.dive.data.local.datastore.UserPreferencesRepository
import com.sopt.dive.domain.repository.AuthRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel(
    private val authRepository: AuthRepository,
    private val userPreferencesRepository: UserPreferencesRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    private val _sideEffect = MutableSharedFlow<LoginSideEffect>()
    val sideEffect: SharedFlow<LoginSideEffect> = _sideEffect.asSharedFlow()

    init {
        viewModelScope.launch {
            val isLoggedIn = userPreferencesRepository.isLoggedIn.first()
            if (isLoggedIn) _sideEffect.emit(LoginSideEffect.NavigateToHome)
        }
    }

    fun onIdChange(newId: String) {
        _uiState.update { currentState ->
            currentState.copy(id = newId)
        }
    }

    fun onPasswordChange(newPassword: String) {
        _uiState.update { currentState ->
            currentState.copy(password = newPassword)
        }
    }

    fun onPasswordToggleClick() {
        _uiState.update { currentState ->
            val newVisibility = !currentState.isPasswordVisible
            currentState.copy(isPasswordVisible = newVisibility)
        }
    }

    fun login() {
        val currentState = _uiState.value

        viewModelScope.launch {
            val loginRequest = LoginRequestDto(
                username = currentState.id,
                password = currentState.password
            )

            authRepository.login(loginRequest)
                .onSuccess { result ->
                    userPreferencesRepository.setId(result.id)
                    userPreferencesRepository.setLoggedIn(true)

                    _sideEffect.emit(LoginSideEffect.ShowToastResId(R.string.login_success_message))
                    _sideEffect.emit(LoginSideEffect.NavigateToHome)
                }
                .onFailure { exception ->
                    exception.printStackTrace()
                    _sideEffect.emit(LoginSideEffect.ShowToastString(exception.message ?: ""))
                }
        }
    }
}