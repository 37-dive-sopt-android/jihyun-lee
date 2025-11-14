package com.sopt.dive.data.mapper

import com.sopt.dive.data.dto.response.LoginResponseDto

fun LoginResponseDto.toDomain(): Int {
    return userId
}