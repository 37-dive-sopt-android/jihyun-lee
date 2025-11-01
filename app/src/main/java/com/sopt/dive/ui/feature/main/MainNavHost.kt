package com.sopt.dive.ui.feature.main

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sopt.dive.data.local.UserPrefs


@Composable
fun MainNavHost() {
    val navController = rememberNavController()
    val startDestination = if (UserPrefs.isLoggedIn()) RootRoute.Main else RootRoute.Auth

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable<RootRoute.Auth> {
            AuthScreen(
                navigator = rememberAuthNavigator(
                    onNavigateToHome = {
                        navController.navigate(RootRoute.Main) {
                            popUpTo(RootRoute.Auth) { inclusive = true }
                        }
                    }
                )
            )
        }
        composable<RootRoute.Main> {
            AppScreen(
                navigator = rememberAppNavigator(
                    onNavigateToLogin = {
                        navController.navigate(RootRoute.Auth) {
                            popUpTo(RootRoute.Main) { inclusive = true }
                        }
                    }
                )
            )
        }
    }
}
