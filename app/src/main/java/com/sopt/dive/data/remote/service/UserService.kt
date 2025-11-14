package com.sopt.dive.data.remote.service

import com.sopt.dive.data.remote.dto.response.BaseResponseDto
import com.sopt.dive.data.remote.dto.request.SignUpRequestDto
import com.sopt.dive.data.remote.dto.response.UserResponseDto
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface UserService {
    @POST("/api/v1/users")
    suspend fun singUp(
        @Body signUpRequestDto: SignUpRequestDto
    ): BaseResponseDto<UserResponseDto>

    @GET("/api/v1/users/{id}")
    suspend fun getUserInfo(
        @Path("id") id: Int
    ): BaseResponseDto<UserResponseDto>

    @DELETE("/api/v1/users/{id}")
    suspend fun withdraw(
        @Path("id") id: Int
    ): BaseResponseDto<Unit>
}