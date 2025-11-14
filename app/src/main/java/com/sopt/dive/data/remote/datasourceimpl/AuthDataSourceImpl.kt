package com.sopt.dive.data.remote.datasourceimpl

import com.sopt.dive.data.remote.datasource.AuthDataSource
import com.sopt.dive.data.remote.dto.request.LoginRequestDto
import com.sopt.dive.data.remote.dto.response.BaseResponseDto
import com.sopt.dive.data.remote.dto.response.LoginResponseDto
import com.sopt.dive.data.remote.service.AuthService

class AuthDataSourceImpl(
    private val authService: AuthService
): AuthDataSource {
    override suspend fun login(loginRequestDto: LoginRequestDto): BaseResponseDto<LoginResponseDto> {
        return authService.login(loginRequestDto)
    }
}