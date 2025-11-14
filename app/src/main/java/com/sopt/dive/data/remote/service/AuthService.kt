package com.sopt.dive.data.remote.service

import com.sopt.dive.data.remote.dto.response.BaseResponseDto
import com.sopt.dive.data.remote.dto.request.LoginRequestDto
import com.sopt.dive.data.remote.dto.response.LoginResponseDto
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("/api/v1/auth/login")
    suspend fun login(
        @Body loginRequestDto: LoginRequestDto
    ): BaseResponseDto<LoginResponseDto>
}