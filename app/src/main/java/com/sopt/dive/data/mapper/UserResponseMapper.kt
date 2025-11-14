package com.sopt.dive.data.mapper

import com.sopt.dive.data.dto.response.UserResponseDto
import com.sopt.dive.domain.model.UserModel

fun UserResponseDto.toDomain(): UserModel {
    return UserModel(
        id = id,
        userId = username,
        name = name,
        email = email,
        age = age
    )
}