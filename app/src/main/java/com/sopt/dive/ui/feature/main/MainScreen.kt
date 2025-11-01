package com.sopt.dive.ui.feature.main

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.sopt.dive.ui.components.DiveBottomBar
import com.sopt.dive.ui.feature.home.HomeRoute
import com.sopt.dive.ui.feature.mypage.MyPageRoute
import com.sopt.dive.ui.feature.search.SearchRoute
import com.sopt.dive.ui.model.DiveTab

@Composable
fun MainScreen(
    onNavigateToLogin: () -> Unit
) {
    val mainNavController = rememberNavController()
    val navBackStackEntry by mainNavController.currentBackStackEntryAsState()
    val currentRouteName = navBackStackEntry?.destination?.route

    val currentTab = when (currentRouteName) {
        MainRoute.Home::class.qualifiedName -> DiveTab.HOME
        MainRoute.Search::class.qualifiedName -> DiveTab.SEARCH
        MainRoute.MyPage::class.qualifiedName -> DiveTab.MYPAGE
        else -> DiveTab.HOME
    }

    Scaffold(
        bottomBar = {
            DiveBottomBar(
                navController = mainNavController,
                currentTab = currentTab
            )
        }
    ) { paddingValues ->
        NavHost(
            navController = mainNavController,
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
                MyPageRoute(
                    onNavigateToLogin = onNavigateToLogin
                )
            }
        }
    }
}