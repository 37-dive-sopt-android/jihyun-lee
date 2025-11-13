package com.sopt.dive.ui.feature.mypage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.dive.data.local.UserPrefs
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException

class MyPageViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(MyPageUiState())
    val uiState: StateFlow<MyPageUiState> = _uiState.asStateFlow()

    private val _sideEffect = MutableSharedFlow<MyPageSideEffect>()
    val sideEffect: SharedFlow<MyPageSideEffect> = _sideEffect.asSharedFlow()

    init {
        loadUserInfo()
    }

    private fun loadUserInfo() {
        viewModelScope.launch {
            try {

            } catch (e: HttpException) {

            } catch (e: Exception) {

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
            UserPrefs.withdraw()
            _sideEffect.emit(MyPageSideEffect.NavigateToLogin)
        }
    }
}