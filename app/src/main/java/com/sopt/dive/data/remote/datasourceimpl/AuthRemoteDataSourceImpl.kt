package com.sopt.dive.data.remote.datasourceimpl

import com.sopt.dive.data.remote.datasource.AuthRemoteDataSource
import com.sopt.dive.data.remote.dto.request.LoginRequestDto
import com.sopt.dive.data.remote.dto.response.BaseResponseDto
import com.sopt.dive.data.remote.dto.response.LoginResponseDto
import com.sopt.dive.data.remote.service.AuthService

class AuthRemoteDataSourceImpl(
    private val authService: AuthService
): AuthRemoteDataSource {
    override suspend fun login(loginRequestDto: LoginRequestDto): BaseResponseDto<LoginResponseDto> {
        return authService.login(loginRequestDto)
    }
}