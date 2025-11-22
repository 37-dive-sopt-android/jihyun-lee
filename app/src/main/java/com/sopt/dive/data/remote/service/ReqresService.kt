package com.sopt.dive.data.remote.service

import com.sopt.dive.data.remote.dto.response.BaseReqresResponse
import com.sopt.dive.data.remote.dto.response.ReqresUserResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ReqresService {
    @GET("users")
    suspend fun getUsers(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
        ): BaseReqresResponse<List<ReqresUserResponseDto>>
}