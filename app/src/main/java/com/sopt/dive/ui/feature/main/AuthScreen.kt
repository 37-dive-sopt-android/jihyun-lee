package com.sopt.dive.ui.feature.main

import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.sopt.dive.ui.feature.login.LoginRoute
import com.sopt.dive.ui.feature.signup.SignUpRoute

@Composable
fun AuthScreen(
    navigator: AuthNavigator
) {
    NavHost(
        navController = navigator.navController,
        startDestination = AuthRoute.LogIn,
        modifier = Modifier.systemBarsPadding()
    ) {
        composable<AuthRoute.LogIn> {
            LoginRoute(
                onNavigateToSignUp = navigator::navigateToSignUp,
                onNavigateToHome = navigator.onNavigateToHome
            )
        }
        composable<AuthRoute.SignUp> {
            SignUpRoute(
                onNavigateToLogin = navigator::navigateToLogin
            )
        }
    }
}