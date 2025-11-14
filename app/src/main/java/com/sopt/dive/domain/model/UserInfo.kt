package com.sopt.dive.domain.model

data class UserInfo(
    val id: Int,
    val userId: String,
    val name: String,
    val email: String,
    val age: Int,
    val profileImageUrl: String = ""
)
