package com.sopt.dive.ui.feature.main

import kotlinx.serialization.Serializable

@Serializable
sealed interface Route

@Serializable
data object LogIn : Route

@Serializable
data object SignUp : Route

@Serializable
data object Home : Route

@Serializable
data object Search : Route

@Serializable
data object MyPage : Route

