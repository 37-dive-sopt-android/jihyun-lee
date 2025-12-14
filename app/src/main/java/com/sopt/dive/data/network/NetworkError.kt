package com.sopt.dive.data.network

import java.io.IOException

sealed class NetworkError(override val message: String?): IOException(message) {
    data class BadRequest(val serverMsg: String) : NetworkError(serverMsg)
    data class Unauthorized(val serverMsg: String) : NetworkError(serverMsg)
    data class Forbidden(val serverMsg: String) : NetworkError(serverMsg)
    data class NotFound(val serverMsg: String) : NetworkError(serverMsg)
    data class Conflict(val serverMsg: String) : NetworkError(serverMsg)
    data class ServerError(val serverMsg: String) : NetworkError(serverMsg)
    object NetworkConnection : NetworkError("인터넷 연결을 확인해주세요.")
    object Unknown : NetworkError("알 수 없는 에러")
}