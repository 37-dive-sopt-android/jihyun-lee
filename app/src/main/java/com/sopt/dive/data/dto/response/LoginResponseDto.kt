package com.sopt.dive.data.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginResponseDto(
    @SerialName("userId")
    val userId: Int,
    @SerialName("message")
    val message: String
)
