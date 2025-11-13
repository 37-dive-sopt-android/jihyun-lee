package com.sopt.dive.data.datasource

import com.sopt.dive.data.dto.request.LoginRequestDto
import com.sopt.dive.data.dto.response.BaseResponseDto
import com.sopt.dive.data.dto.response.LoginResponseDto

interface AuthDataSource {
    suspend fun login(
        loginRequestDto: LoginRequestDto
    ): BaseResponseDto<LoginResponseDto>
}