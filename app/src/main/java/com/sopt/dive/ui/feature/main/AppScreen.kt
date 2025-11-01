package com.sopt.dive.ui.feature.main

import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.sopt.dive.ui.components.DiveBottomBar
import com.sopt.dive.ui.feature.home.HomeRoute
import com.sopt.dive.ui.feature.mypage.MyPageRoute
import com.sopt.dive.ui.feature.search.SearchRoute

@Composable
fun AppScreen(
    navigator: AppNavigator
) {
    Scaffold(
        bottomBar = {
            DiveBottomBar(
                currentTab = navigator.currentTab,
                onTabSelected = { navigator.navigateToTab(it) },
                modifier = Modifier.navigationBarsPadding()
            )
        }
    ) { paddingValues ->
        NavHost(
            navController = navigator.navController,
            startDestination = MainRoute.Home,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable<MainRoute.Home> {
                HomeRoute()
            }
            composable<MainRoute.Search> {
                SearchRoute()
            }
            composable<MainRoute.MyPage> {
                MyPageRoute(onNavigateToLogin = navigator.onNavigateToLogin)
            }
        }
    }
}