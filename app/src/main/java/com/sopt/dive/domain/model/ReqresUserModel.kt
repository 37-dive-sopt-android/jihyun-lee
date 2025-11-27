package com.sopt.dive.domain.model

data class ReqresUserModel(
    val id: Int,
    val email: String,
    val firstName: String,
    val lastName: String,
    val avatar: String,
    val isBirthday: Boolean = false,
    val music: String? = null
)
