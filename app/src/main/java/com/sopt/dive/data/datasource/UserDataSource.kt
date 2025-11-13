package com.sopt.dive.data.datasource

import com.sopt.dive.data.dto.request.SignUpRequestDto
import com.sopt.dive.data.dto.response.BaseResponseDto
import com.sopt.dive.data.dto.response.UserResponseDto

interface UserDataSource {
    suspend fun singUp(
        signUpRequestDto: SignUpRequestDto
    ): BaseResponseDto<UserResponseDto>

    suspend fun getUserInfo(
        id: Int
    ): BaseResponseDto<UserResponseDto>
}