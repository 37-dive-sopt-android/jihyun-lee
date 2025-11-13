package com.sopt.dive.ui.feature.signup

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

class SignUpViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(SignUpUiState())
    val uiState: StateFlow<SignUpUiState> = _uiState.asStateFlow()

    private val _sideEffect = MutableSharedFlow<SignUpSideEffect>()
    val sideEffect: SharedFlow<SignUpSideEffect> = _sideEffect.asSharedFlow()

    fun updateId(newId: String) {
        _uiState.update { it.copy(id = newId) }
    }

    fun updatePassword(newPassword: String) {
        _uiState.update { it.copy(password = newPassword) }
    }

    fun updateName(newName: String) {
        _uiState.update { it.copy(name = newName, isNameTouched = true) }
    }

    fun updateNickname(newNickname: String) {
        _uiState.update { it.copy(nickname = newNickname, isNicknameTouched = true) }
    }

    fun updateMbti(newMbti: String) {
        _uiState.update { it.copy(mbti = newMbti) }
    }

    fun signUp() {
        if (!_uiState.value.isSignUpEnabled) return

        val currentState = _uiState.value

        viewModelScope.launch {
            UserPrefs.saveUserInfo(
                id = currentState.id,
                password = currentState.password,
                name = currentState.name,
                nickname = currentState.nickname,
                mbti = currentState.mbti
            )
            _sideEffect.emit(SignUpSideEffect.ShowToast(R.string.signup_success_message))
            _sideEffect.emit(SignUpSideEffect.NavigateToLogin)
        }
    }
}