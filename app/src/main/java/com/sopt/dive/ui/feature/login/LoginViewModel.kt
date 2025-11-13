package com.sopt.dive.ui.feature.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.dive.R
import com.sopt.dive.data.dto.request.LoginRequestDto
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

class LoginViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    private val _sideEffect = MutableSharedFlow<LoginSideEffect>()
    val sideEffect: SharedFlow<LoginSideEffect> = _sideEffect.asSharedFlow()

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
            try {
                val loginRequest = LoginRequestDto(
                    username = currentState.id,
                    password = currentState.password
                )
                val response = ServicePool.authService.login(loginRequest)

                if (response.success){
                    UserPrefs.setLoggedIn(true)
                    _sideEffect.emit(LoginSideEffect.ShowToastResId(R.string.login_success_message))
                    _sideEffect.emit(LoginSideEffect.NavigateToHome)
                }
            } catch (e: HttpException) {
                e.printStackTrace()
                val errorMessage = getErrorMessage(e)
                if (errorMessage.isNotBlank()) _sideEffect.emit(LoginSideEffect.ShowToastString(errorMessage))
                else _sideEffect.emit(LoginSideEffect.ShowToastResId(R.string.login_fail_message))
            } catch (e: Exception) {
                e.printStackTrace()
                _sideEffect.emit(LoginSideEffect.ShowToastResId(R.string.login_fail_message))
            }
        }
    }
}