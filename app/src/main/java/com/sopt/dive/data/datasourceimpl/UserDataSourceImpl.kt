package com.sopt.dive.data.datasourceimpl

import com.sopt.dive.data.datasource.UserDataSource
import com.sopt.dive.data.dto.request.SignUpRequestDto
import com.sopt.dive.data.dto.response.BaseResponseDto
import com.sopt.dive.data.dto.response.UserResponseDto
import com.sopt.dive.data.service.UserService

class UserDataSourceImpl(
    private val userService: UserService
) : UserDataSource {
    override suspend fun singUp(signUpRequestDto: SignUpRequestDto): BaseResponseDto<UserResponseDto> {
        return userService.singUp(signUpRequestDto)
    }
}