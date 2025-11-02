package com.sopt.dive.ui.feature.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.sopt.dive.ui.components.DiveBottomBar

@Composable
fun MainScreen(
    navigator: MainNavigator = rememberMainNavigator()
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        content = { paddingValues ->
            MainNavHost(
                navigator = navigator,
                paddingValues = paddingValues
            )
        },
        bottomBar = {
            DiveBottomBar(
                currentTab = navigator.currentTab,
                onTabSelected = navigator::navigateToTab,
                isVisible = navigator.bottomBarIsVisible,
                modifier = Modifier.navigationBarsPadding()
            )
        }
    )
}