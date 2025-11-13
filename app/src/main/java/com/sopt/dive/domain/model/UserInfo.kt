package com.sopt.dive.domain.model

data class UserInfo(
    val id: String = "",
    val password: String = "",
    val name: String = "",
    val email: String = "",
    val age: Int? = null,
    val profileImageUrl: String = ""
)
