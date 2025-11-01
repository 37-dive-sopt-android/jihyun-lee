package com.sopt.dive.ui.feature.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun rememberAuthNavigator(
    onNavigateToHome: () -> Unit,
    navController: NavHostController = rememberNavController()
): AuthNavigator = remember(navController, onNavigateToHome) {
    AuthNavigator(navController, onNavigateToHome)
}

class AuthNavigator(
    val navController: NavHostController,
    val onNavigateToHome: () -> Unit
) {
    fun navigateToSignUp() {
        navController.navigate(AuthRoute.SignUp)
    }

    fun navigateToLogin() {
        navController.navigate(AuthRoute.LogIn)
    }
}