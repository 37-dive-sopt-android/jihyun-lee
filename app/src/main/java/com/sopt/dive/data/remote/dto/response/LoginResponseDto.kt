package com.sopt.dive.data.remote.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginResponseDto(
    @SerialName("userId")
    val userId: Int,
    @SerialName("message")
    val message: String
)
