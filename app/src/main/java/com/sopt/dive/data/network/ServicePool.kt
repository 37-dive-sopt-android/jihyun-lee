package com.sopt.dive.data.network

import com.sopt.dive.data.service.AuthService
import com.sopt.dive.data.service.UserService

object ServicePool {
    val userService: UserService by lazy {
        ApiFactory.create<UserService>()
    }
    val authService: AuthService by lazy {
        ApiFactory.create<AuthService>()
    }
}