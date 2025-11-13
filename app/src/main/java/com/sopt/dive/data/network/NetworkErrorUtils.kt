package com.sopt.dive.data.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import retrofit2.HttpException

fun getErrorMessage(e: HttpException): String {
    val json = Json { ignoreUnknownKeys = true }
    return try {
        val errorBody = e.response()?.errorBody()?.string() ?: ""
        val errorResponse = json.decodeFromString<BaseErrorResponse>(errorBody)
        errorResponse.message
    } catch (jsonErr: Exception) {
        jsonErr.printStackTrace()
        ""
    }
}

@Serializable
private data class BaseErrorResponse(
    @SerialName("success")
    val success: Boolean,
    @SerialName("code")
    val code: String,
    @SerialName("message")
    val message: String
)