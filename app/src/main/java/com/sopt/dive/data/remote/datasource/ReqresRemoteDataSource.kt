package com.sopt.dive.data.remote.datasource

import com.sopt.dive.data.remote.dto.response.BaseReqresResponse
import com.sopt.dive.data.remote.dto.response.ReqresUserResponseDto

interface ReqresRemoteDataSource {
    suspend fun getUsers(page: Int, perPage: Int): BaseReqresResponse<List<ReqresUserResponseDto>>
}