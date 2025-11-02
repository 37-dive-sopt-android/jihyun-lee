package com.sopt.dive.ui.feature.main

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.sopt.dive.data.local.UserPrefs
import com.sopt.dive.ui.feature.home.HomeRoute
import com.sopt.dive.ui.feature.login.LoginRoute
import com.sopt.dive.ui.feature.mypage.MyPageRoute
import com.sopt.dive.ui.feature.search.SearchRoute
import com.sopt.dive.ui.feature.signup.SignUpRoute


@Composable
fun MainNavHost(
    navigator: MainNavigator,
    paddingValues: PaddingValues
) {
    val startDestination = if (UserPrefs.isLoggedIn()) Home else LogIn

    NavHost(
        navController = navigator.navController,
        startDestination = startDestination
    ) {
        composable<Home> {
            HomeRoute(
                modifier = Modifier.padding(paddingValues)
            )
        }
        composable<Search> {
            SearchRoute(
                modifier = Modifier.padding(paddingValues)
            )
        }
        composable<MyPage> {
            MyPageRoute(
                onNavigateToLogin = navigator::navigateToLogin,
                modifier = Modifier.padding(paddingValues)
            )
        }
        composable<LogIn> {
            LoginRoute(
                onNavigateToSignUp = navigator::navigateToSignUp,
                onNavigateToHome = navigator::navigateToHome,
                modifier = Modifier
                    .navigationBarsPadding()
                    .statusBarsPadding()
            )
        }
        composable<SignUp> {
            SignUpRoute(
                onNavigateToLogin = navigator::navigateToLogin,
                modifier = Modifier
                    .navigationBarsPadding()
                    .statusBarsPadding()
            )
        }
    }
}
