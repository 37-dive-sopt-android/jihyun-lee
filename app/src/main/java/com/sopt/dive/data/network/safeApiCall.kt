package com.sopt.dive.data.network

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.ensureActive
import retrofit2.HttpException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

internal suspend fun <T> safeApiCall(block: suspend () -> T): Result<T> {
    return try {
        Result.success(block())
    } catch (e: TimeoutCancellationException) {
        Result.failure(NetworkError.NetworkConnection)
    } catch (e: CancellationException) {
        throw e
    } catch (e: HttpException) {
        Result.failure(parseHttpException(e))
    } catch (e: UnknownHostException) {
        Result.failure(NetworkError.NetworkConnection)
    } catch (e: SocketTimeoutException) {
        Result.failure(NetworkError.NetworkConnection)
    } catch (e: Throwable) {
        currentCoroutineContext().ensureActive()
        Result.failure(NetworkError.Unknown)
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