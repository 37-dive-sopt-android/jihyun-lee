package com.sopt.dive.data.mapper

import com.sopt.dive.data.remote.dto.request.SignUpRequestDto
import com.sopt.dive.domain.model.SignUpModel

fun SignUpModel.toDto(): SignUpRequestDto {
    return SignUpRequestDto(
        username = this.id,
        password = this.password,
        name = this.name,
        email = this.email,
        age = this.age
    )
}