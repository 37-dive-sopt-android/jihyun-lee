package com.sopt.dive.ui.feature.search

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class SearchViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(
        SearchUiState(
            flipCardFrontImageUrl = "https://i.pinimg.com/736x/39/e4/83/39e483cc2769b34faa081680c3741c7c.jpg",
            flipCardBackImageUrl = "https://i.pinimg.com/736x/e4/1f/3c/e41f3cb1c9d36dda02709861e57d00ed.jpg",
            stackedFlipCardImageUrl = "https://i.pinimg.com/736x/13/fc/14/13fc149bd618a181cf1542c3140c5f40.jpg",
            stackedFlipCardText = "안녕하세요".repeat(100)
        )
    )
    val uiState: StateFlow<SearchUiState> = _uiState.asStateFlow()
}