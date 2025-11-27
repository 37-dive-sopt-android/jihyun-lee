package com.sopt.dive.data.mapper

import com.sopt.dive.data.remote.dto.response.LoginResponseDto
import com.sopt.dive.domain.model.LoginModel

fun LoginResponseDto.toDomain(): LoginModel {
    return LoginModel(
        id = userId,
        message = message
    )
}