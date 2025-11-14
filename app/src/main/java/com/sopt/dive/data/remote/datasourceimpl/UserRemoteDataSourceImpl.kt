package com.sopt.dive.data.remote.datasourceimpl

import com.sopt.dive.data.remote.datasource.UserRemoteDataSource
import com.sopt.dive.data.remote.dto.request.SignUpRequestDto
import com.sopt.dive.data.remote.dto.response.BaseResponseDto
import com.sopt.dive.data.remote.dto.response.UserResponseDto
import com.sopt.dive.data.remote.service.UserService

class UserRemoteDataSourceImpl(
    private val userService: UserService
) : UserRemoteDataSource {
    override suspend fun singUp(signUpRequestDto: SignUpRequestDto): BaseResponseDto<UserResponseDto> {
        return userService.singUp(signUpRequestDto)
    }

    override suspend fun getUserInfo(id: Int): BaseResponseDto<UserResponseDto> {
       return userService.getUserInfo(id)
    }

    override suspend fun withdraw(id: Int): BaseResponseDto<Unit> {
        return userService.withdraw(id)
    }
}