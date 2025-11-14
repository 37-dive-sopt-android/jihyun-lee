package com.sopt.dive.data.util

import com.sopt.dive.data.remote.dto.response.BaseResponseDto
import kotlinx.serialization.json.Json
import retrofit2.HttpException

fun parseHttpException(e: HttpException): String {
    val json = Json { ignoreUnknownKeys = true }
    return try {
        val errorBody = e.response()?.errorBody()?.string()
        val errorResponse = errorBody?.let { json.decodeFromString<BaseResponseDto<Unit>>(it) }
        errorResponse?.message ?: "알 수 없는 에러가 발생했습니다."
    } catch (jsonErr: Exception) {
        jsonErr.printStackTrace()
        "서버와 통신 중 오류가 발생했습니다."
    }
}