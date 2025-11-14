package com.sopt.dive.data.remote.datasource

import com.sopt.dive.data.remote.dto.request.SignUpRequestDto
import com.sopt.dive.data.remote.dto.response.BaseResponseDto
import com.sopt.dive.data.remote.dto.response.UserResponseDto

interface UserDataSource {
    suspend fun singUp(
        signUpRequestDto: SignUpRequestDto
    ): BaseResponseDto<UserResponseDto>

    suspend fun getUserInfo(
        id: Int
    ): BaseResponseDto<UserResponseDto>

    suspend fun withdraw(
        id: Int
    ): BaseResponseDto<Unit>
}