package com.sopt.dive.presentation.util

import com.sopt.dive.data.network.NetworkError

fun NetworkError.toUserErrorMessage(): String {
    return when(this) {
        is NetworkError.BadRequest -> "요청 값이 유효하지 않습니다."
        is NetworkError.Unauthorized -> "로그인 정보가 일치하지 않습니다."
        is NetworkError.Forbidden -> "비활성(탈퇴) 사용자입니다."
        is NetworkError.NotFound -> "해당 아이디가 존재하지 않습니다."
        is NetworkError.Conflict -> "이미 존재하는 아이디입니다."
        is NetworkError.NetworkConnection -> "인터넷 연결 상태를 확인해주세요."
        is NetworkError.ServerError -> "서버 점검 중입니다. 잠시 후 다시 시도해주세요."
        is NetworkError.Unknown -> "알 수 없는 오류가 발생했습니다."
    }
}