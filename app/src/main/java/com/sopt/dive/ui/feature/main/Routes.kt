package com.sopt.dive.ui.feature.main

import kotlinx.serialization.Serializable

@Serializable
sealed interface RootRoute {
    @Serializable
    data object Auth : RootRoute

    @Serializable
    data object Main : RootRoute
}

@Serializable
sealed interface AuthRoute {
    @Serializable
    data object LogIn : AuthRoute

    @Serializable
    data object SignUp : AuthRoute
}

@Serializable
sealed interface AppRoute {
    @Serializable
    data object Home : AppRoute

    @Serializable
    data object Search : AppRoute

    @Serializable
    data object MyPage : AppRoute
}
