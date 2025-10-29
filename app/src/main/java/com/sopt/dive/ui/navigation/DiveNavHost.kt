package com.sopt.dive.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.sopt.dive.ui.feature.home.HomeRoute
import com.sopt.dive.ui.feature.mypage.MyPageRoute
import com.sopt.dive.ui.feature.search.SearchRoute

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
                padding = padding
            )
        }
        composable<Route.Search> {
            SearchRoute(
                padding = padding
            )
        }
        composable<Route.MyPage> {
            MyPageRoute(
                padding = padding
            )
        }
    }
}