package com.sopt.dive.data.mapper

import com.sopt.dive.data.dto.response.UserResponseDto
import com.sopt.dive.domain.model.UserInfo

fun UserResponseDto.toDomain(): UserInfo {
    return UserInfo(
        id = id,
        userId = username,
        name = name,
        email = email,
        age = age
    )
}