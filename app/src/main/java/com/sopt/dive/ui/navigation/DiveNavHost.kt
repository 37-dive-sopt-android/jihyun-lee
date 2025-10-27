package com.sopt.dive.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.sopt.dive.ui.feature.home.HomeRoute
import com.sopt.dive.ui.feature.mypage.MyPageRoute

@Composable
fun DiveNavHost(
    navController: NavHostController,
    padding: PaddingValues
) {
    NavHost(
        navController = navController,
        startDestination = Route.Home
    ) {
        composable<Route.Home> {
            HomeRoute(
                modifier = Modifier.padding(padding)
            )
        }
        composable<Route.Search> {

        }
        composable<Route.MyPage> {
            MyPageRoute(
                modifier = Modifier.padding(padding)
            )
        }
    }
}