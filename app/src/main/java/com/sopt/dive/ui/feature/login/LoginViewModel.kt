package com.sopt.dive.ui.feature.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.dive.R
import com.sopt.dive.data.local.UserPrefs
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    private val _sideEffect = MutableSharedFlow<LoginSideEffect>()
    val sideEffect: SharedFlow<LoginSideEffect> = _sideEffect.asSharedFlow()

    fun updateId(newId: String) {
        _uiState.update { currentState ->
            currentState.copy(id = newId)
        }
    }

    fun updatePassword(newPassword: String) {
        _uiState.update { currentState ->
            currentState.copy(password = newPassword)
        }
    }

    fun togglePasswordVisibility() {
        _uiState.update { currentState ->
            val newVisibility = !currentState.isPasswordVisible
            currentState.copy(isPasswordVisible = newVisibility)
        }
    }

    fun login() {
        val currentId = _uiState.value.id
        val currentPassword = _uiState.value.password
        val registeredId = UserPrefs.getId()
        val registeredPassword = UserPrefs.getPassword()

        viewModelScope.launch {
            if (currentId == registeredId && currentPassword == registeredPassword) {
                UserPrefs.setLoggedIn(value = true)
                _sideEffect.emit(LoginSideEffect.ShowToast(R.string.login_success_message))
                _sideEffect.emit(LoginSideEffect.NavigateToHome)
            } else {
                _sideEffect.emit(LoginSideEffect.ShowToast(R.string.login_fail_message))
            }
        }
    }
}