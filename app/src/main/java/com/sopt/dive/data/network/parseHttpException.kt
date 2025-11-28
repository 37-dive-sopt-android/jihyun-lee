package com.sopt.dive.data.network

import kotlinx.serialization.json.Json
import retrofit2.HttpException

fun parseHttpException(e: HttpException): NetworkError {
    val json = Json { ignoreUnknownKeys = true }
    val errorBody = e.response()?.errorBody()?.string()
    val errorResponse = errorBody?.let { json.decodeFromString<BaseResponse<Unit>>(it) }

    val serverMsg = errorResponse?.message ?: ""
    val code = e.code()

    return when(code) {
        400 -> NetworkError.BadRequest(serverMsg)
        401 -> NetworkError.Unauthorized(serverMsg)
        403 -> NetworkError.Forbidden(serverMsg)
        404 -> NetworkError.NotFound(serverMsg)
        409 -> NetworkError.Conflict(serverMsg)
        in 500..599 -> NetworkError.ServerError(serverMsg)
        else -> NetworkError.Unknown
    }
}