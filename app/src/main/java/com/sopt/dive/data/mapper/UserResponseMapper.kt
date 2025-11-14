package com.sopt.dive.data.mapper

import com.sopt.dive.data.local.entity.UserEntity
import com.sopt.dive.data.remote.dto.response.UserResponseDto
import com.sopt.dive.domain.model.UserModel

fun UserEntity.toDomain() : UserModel {
    return UserModel(
        id = id,
        userId = userId,
        name = name,
        email = email,
        age = age,
        profileImageUrl = profileUrl
    )
}

fun UserResponseDto.toEntity(): UserEntity {
    return UserEntity(
        id = id,
        userId = username,
        name = name,
        email = email,
        age = age,
        profileUrl = ""
    )
}

fun UserResponseDto.toDomain(): UserModel {
    return UserModel(
        id = id,
        userId = username,
        name = name,
        email = email,
        age = age
    )
}