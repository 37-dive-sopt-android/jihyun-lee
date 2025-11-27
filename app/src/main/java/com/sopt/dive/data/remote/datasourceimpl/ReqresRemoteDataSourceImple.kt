package com.sopt.dive.data.remote.datasourceimpl

import com.sopt.dive.data.remote.datasource.ReqresRemoteDataSource
import com.sopt.dive.data.network.BaseReqresResponse
import com.sopt.dive.data.remote.dto.response.ReqresUserResponseDto
import com.sopt.dive.data.remote.service.ReqresService

class ReqresRemoteDataSourceImpl(
    private val reqresService: ReqresService
): ReqresRemoteDataSource {
    override suspend fun getUsers(
        page: Int,
        perPage: Int
    ): BaseReqresResponse<List<ReqresUserResponseDto>> {
        return reqresService.getUsers(page, perPage)
    }
}