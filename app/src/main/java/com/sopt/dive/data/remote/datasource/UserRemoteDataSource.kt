package com.sopt.dive.data.remote.datasource

import com.sopt.dive.data.remote.dto.request.SignUpRequestDto
import com.sopt.dive.data.network.BaseResponse
import com.sopt.dive.data.remote.dto.response.UserResponseDto

interface UserRemoteDataSource {
    suspend fun singUp(
        signUpRequestDto: SignUpRequestDto
    ): BaseResponse<UserResponseDto>

    suspend fun getUserInfo(
        id: Int
    ): BaseResponse<UserResponseDto>

    suspend fun withdraw(
        id: Int
    ): BaseResponse<Unit>
}