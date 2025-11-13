package com.sopt.dive.data.service

import com.sopt.dive.data.dto.base.ApiResponse
import com.sopt.dive.data.dto.request.LoginRequestDto
import com.sopt.dive.data.dto.response.LoginResponseDto
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("/api/v1/auth/login")
    suspend fun login(
        @Body loginRequestDto: LoginRequestDto
    ): ApiResponse<LoginResponseDto>
}