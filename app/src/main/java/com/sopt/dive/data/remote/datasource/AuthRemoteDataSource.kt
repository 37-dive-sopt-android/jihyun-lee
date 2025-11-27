package com.sopt.dive.data.remote.datasource

import com.sopt.dive.data.remote.dto.request.LoginRequestDto
import com.sopt.dive.data.network.BaseResponse
import com.sopt.dive.data.remote.dto.response.LoginResponseDto

interface AuthRemoteDataSource {
    suspend fun login(
        loginRequestDto: LoginRequestDto
    ): BaseResponse<LoginResponseDto>
}