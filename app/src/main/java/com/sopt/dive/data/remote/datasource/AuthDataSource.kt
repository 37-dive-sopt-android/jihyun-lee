package com.sopt.dive.data.remote.datasource

import com.sopt.dive.data.remote.dto.request.LoginRequestDto
import com.sopt.dive.data.remote.dto.response.BaseResponseDto
import com.sopt.dive.data.remote.dto.response.LoginResponseDto

interface AuthDataSource {
    suspend fun login(
        loginRequestDto: LoginRequestDto
    ): BaseResponseDto<LoginResponseDto>
}