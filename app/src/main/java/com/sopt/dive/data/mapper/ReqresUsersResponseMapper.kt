package com.sopt.dive.data.mapper

import com.sopt.dive.data.remote.dto.response.ReqresUserResponseDto
import com.sopt.dive.domain.model.ReqresUserModel

fun ReqresUserResponseDto.toDomain(): ReqresUserModel {
    return ReqresUserModel(
        id = id,
        email = email,
        firstName = firstName,
        lastName = lastName,
        avatar = avatar
    )
}

fun List<ReqresUserResponseDto>.toDomain(): List<ReqresUserModel> {
    return this.map { it.toDomain() }
}