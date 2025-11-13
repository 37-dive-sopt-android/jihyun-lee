package com.sopt.dive.ui.feature.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.dive.R
import com.sopt.dive.data.dto.request.SignUpRequestDto
import com.sopt.dive.data.local.UserPrefs
import com.sopt.dive.data.network.ServicePool
import com.sopt.dive.data.network.getErrorMessage
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.HttpException

class SignUpViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(SignUpUiState())
    val uiState: StateFlow<SignUpUiState> = _uiState.asStateFlow()

    private val _sideEffect = MutableSharedFlow<SignUpSideEffect>()
    val sideEffect: SharedFlow<SignUpSideEffect> = _sideEffect.asSharedFlow()

    fun onIdChange(newId: String) {
        _uiState.update { it.copy(id = newId) }
    }

    fun onPwChange(newPassword: String) {
        _uiState.update { it.copy(password = newPassword) }
    }

    fun onNameChange(newName: String) {
        _uiState.update { it.copy(name = newName, isNameTouched = true) }
    }

    fun onEmailChange(newEmail: String) {
        _uiState.update { it.copy(email = newEmail, isEmailTouched = true) }
    }

    fun onAgeChange(newAge: String) {
        _uiState.update { it.copy(age = newAge.toIntOrNull()) }
    }

    fun signUp() {
        if (!_uiState.value.isSignUpEnabled) return

        val currentState = _uiState.value

        viewModelScope.launch {
            try {
                val signUpRequest = SignUpRequestDto(
                    username = currentState.id,
                    password = currentState.password,
                    name = currentState.name,
                    email = currentState.email,
                    age = currentState.age ?: 0
                )
                val response = ServicePool.userService.singUp(signUpRequest)

                if (response.success) {
                    UserPrefs.saveUserInfo(
                        id = currentState.id,
                        password = currentState.password,
                        name = currentState.name,
                        email = currentState.email,
                        age = currentState.age ?: 0
                    )
                    _sideEffect.emit(SignUpSideEffect.ShowToastResId(R.string.signup_success_message))
                    _sideEffect.emit(SignUpSideEffect.NavigateToLogin)
                } else {
                    _sideEffect.emit(SignUpSideEffect.ShowToastString(response.message))
                }
            } catch (e: HttpException) {
                e.printStackTrace()
                val errorMessage = getErrorMessage(e)
                if (errorMessage.isNotBlank()) _sideEffect.emit(SignUpSideEffect.ShowToastString(errorMessage))
                else _sideEffect.emit(SignUpSideEffect.ShowToastResId(R.string.signup_fail_message))
            } catch (e: Exception) {
                e.printStackTrace()
                _sideEffect.emit(SignUpSideEffect.ShowToastResId(R.string.signup_fail_message))
            }
        }
    }
}