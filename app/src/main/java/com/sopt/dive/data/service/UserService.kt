package com.sopt.dive.data.service

import com.sopt.dive.data.dto.base.ApiResponse
import com.sopt.dive.data.dto.request.SignUpRequestDto
import com.sopt.dive.data.dto.response.SignUpResponseDto
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface UserService {
    @POST("/api/v1/users")
    suspend fun singUp(
        @Body signUpRequestDto: SignUpRequestDto
    ): ApiResponse<SignUpResponseDto>

    @GET("/api/v1/users/{id}")
    suspend fun getUserInfo(
        @Path("id") id: Int
    ): ApiResponse<SignUpResponseDto>

    @DELETE("/api/v1/users/{id}")
    suspend fun windraw(
        @Path("id") id: Int
    ): ApiResponse<Unit>
}