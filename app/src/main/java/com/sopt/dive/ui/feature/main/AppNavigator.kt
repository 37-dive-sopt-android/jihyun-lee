package com.sopt.dive.ui.feature.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.sopt.dive.ui.model.DiveTab

@Composable
fun rememberMainNavigator(
    navController: NavHostController = rememberNavController()
): AppNavigator = remember(navController) {
    AppNavigator(navController)
}

class AppNavigator(
    val navController: NavHostController
) {
    val currentTab: DiveTab
        @Composable get() {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRouteName = navBackStackEntry?.destination?.route

            return when (currentRouteName) {
                MainRoute.Home::class.qualifiedName -> DiveTab.HOME
                MainRoute.Search::class.qualifiedName -> DiveTab.SEARCH
                MainRoute.MyPage::class.qualifiedName -> DiveTab.MYPAGE
                else -> DiveTab.HOME
            }
        }

    fun navigateToTab(tab: DiveTab) {
        val route = when (tab) {
            DiveTab.HOME -> MainRoute.Home
            DiveTab.SEARCH -> MainRoute.Search
            DiveTab.MYPAGE -> MainRoute.MyPage
        }

        navController.navigate(route) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }
}