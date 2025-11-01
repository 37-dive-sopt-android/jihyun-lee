package com.sopt.dive.ui.feature.main

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sopt.dive.ui.feature.login.LoginRoute
import com.sopt.dive.ui.feature.signup.SignUpRoute

@Composable
fun AuthScreen(
    onNavigateToHome: () -> Unit
) {
    val authNavController = rememberNavController()

    NavHost(
        navController = authNavController,
        startDestination = AuthRoute.LogIn
    ) {
        composable<AuthRoute.LogIn> {
            LoginRoute(
                onNavigateToSignUp = {
                    authNavController.navigate(AuthRoute.SignUp)
                },
                onNavigateToHome = onNavigateToHome
            )
        }
        composable<AuthRoute.SignUp> {
            SignUpRoute(
                onNavigateToLogin = {
                    authNavController.navigate(AuthRoute.LogIn)
                }
            )
        }
    }
}