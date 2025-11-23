package com.sopt.dive.data.util

import com.sopt.dive.data.network.BaseReqresResponse
import com.sopt.dive.data.network.BaseResponse
import retrofit2.HttpException

internal suspend fun <T> safeApiCall(block: suspend () -> T): Result<T> {
    return try {
        Result.success(block())
    } catch (e: HttpException) {
        Result.failure(Exception(parseHttpException(e)))
    } catch (e: Exception) {
        Result.failure(e)
    }
}

fun <T> BaseResponse<T>.handleApiResponse(): Result<T> {
    return if (this.success) {
        val data = this.data ?: return Result.failure(Exception("Successful response but data field was null."))
        Result.success(data)
    } else {
        Result.failure(Exception(this.message))
    }
}

fun <T> BaseResponse<T>.handleNullableApiResponse(): Result<T?> {
    return if (this.success) {
        Result.success(this.data)
    } else {
        Result.failure(Exception(this.message))
    }
}

fun <T> BaseReqresResponse<T>.handleReqresApiResponse(): Result<T> {
    return Result.success(this.data)
}