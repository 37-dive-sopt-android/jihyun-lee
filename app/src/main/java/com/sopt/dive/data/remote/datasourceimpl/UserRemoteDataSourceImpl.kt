package com.sopt.dive.data.remote.datasourceimpl

import com.sopt.dive.data.remote.datasource.UserRemoteDataSource
import com.sopt.dive.data.remote.dto.request.SignUpRequestDto
import com.sopt.dive.data.remote.dto.response.BaseResponse
import com.sopt.dive.data.remote.dto.response.UserResponseDto
import com.sopt.dive.data.remote.service.UserService

class UserRemoteDataSourceImpl(
    private val userService: UserService
) : UserRemoteDataSource {
    override suspend fun singUp(signUpRequestDto: SignUpRequestDto): BaseResponse<UserResponseDto> {
        return userService.singUp(signUpRequestDto)
    }

    override suspend fun getUserInfo(id: Int): BaseResponse<UserResponseDto> {
       return userService.getUserInfo(id)
    }

    override suspend fun withdraw(id: Int): BaseResponse<Unit> {
        return userService.withdraw(id)
    }
}