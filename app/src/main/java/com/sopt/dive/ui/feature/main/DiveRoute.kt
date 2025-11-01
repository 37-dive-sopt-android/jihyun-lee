package com.sopt.dive.ui.feature.main

import kotlinx.serialization.Serializable

@Serializable
sealed interface RootRoute {
    @Serializable
    data object Auth : RootRoute

    @Serializable
    data object Main : RootRoute
}

sealed interface AuthRoute {
    @Serializable
    data object LogIn : AuthRoute

    @Serializable
    data object SignUp : AuthRoute
}

sealed interface MainRoute {
    @Serializable
    data object Home : MainRoute

    @Serializable
    data object Search : MainRoute

    @Serializable
    data object MyPage : MainRoute
}
