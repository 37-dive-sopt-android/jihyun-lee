package com.sopt.dive.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
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
): MainNavigator = remember(navController) {
    MainNavigator(navController)
}

@Stable
class MainNavigator(
    val navController: NavHostController
) {
    val currentTab: DiveTab
        @Composable get() {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRouteName = navBackStackEntry?.destination?.route

            return when (currentRouteName) {
                Home::class.qualifiedName -> DiveTab.HOME
                Search::class.qualifiedName -> DiveTab.SEARCH
                MyPage::class.qualifiedName -> DiveTab.MYPAGE
                else -> DiveTab.HOME
            }
        }

    private val bottomBarRoutes = listOf(
        Home::class.qualifiedName,
        Search::class.qualifiedName,
        MyPage::class.qualifiedName
    )

    val bottomBarIsVisible: Boolean
        @Composable get() {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRouteName = navBackStackEntry?.destination?.route
            return currentRouteName in bottomBarRoutes
        }

    fun navigateToTab(tab: DiveTab) {
        val route = when (tab) {
            DiveTab.HOME -> Home
            DiveTab.SEARCH -> Search
            DiveTab.MYPAGE -> MyPage
        }

        navController.navigate(route) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }

    fun navigateToSignUp() {
        navController.navigate(SignUp)
    }

    fun navigateToLogin() {
        navController.navigate(LogIn)
    }

    fun navigateToHome() {
        navController.navigate(Home)
    }
}