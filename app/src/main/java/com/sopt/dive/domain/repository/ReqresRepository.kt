package com.sopt.dive.domain.repository

import com.sopt.dive.domain.model.ReqresUserModel

interface ReqresRepository {
    suspend fun getUsers(
        page: Int,
        perPage: Int
    ): Result<List<ReqresUserModel>>
}