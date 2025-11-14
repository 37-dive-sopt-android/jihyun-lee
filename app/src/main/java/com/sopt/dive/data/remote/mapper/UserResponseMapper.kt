package com.sopt.dive.data.remote.mapper

import com.sopt.dive.data.remote.dto.response.UserResponseDto
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