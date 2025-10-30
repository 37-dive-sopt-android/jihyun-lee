package com.sopt.dive.domain.model

data class ProfileInfo(
    val name: String = "",
    val profileImageUrl: String? = null,
    val statusMessage: String? = null,
    val music: String? = null,
    val isBirthday: Boolean = false
)