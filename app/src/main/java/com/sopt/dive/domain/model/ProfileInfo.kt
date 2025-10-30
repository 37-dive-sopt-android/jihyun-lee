package com.sopt.dive.domain.model

data class ProfileInfo(
    val imageUrl: String? = null,
    val name: String = "",
    val introduction: String? = null,
    val isBirth: Boolean = false,
    val music: Music? = null
)