package com.sopt.dive.data.mapper

import com.sopt.dive.data.local.entity.FriendEntity
import com.sopt.dive.data.remote.dto.response.ReqresUserResponseDto
import com.sopt.dive.domain.model.ReqresUserModel

fun ReqresUserResponseDto.toEntity(): FriendEntity {
    return FriendEntity(
        id = id,
        email = email,
        firstName = firstName,
        lastName = lastName,
        avatar = avatar
    )
}

fun FriendEntity.toDomain(): ReqresUserModel {
    return ReqresUserModel(
        id = id,
        email = email,
        firstName = firstName,
        lastName = lastName,
        avatar = avatar
    )
}

fun List<ReqresUserResponseDto>.toEntity(): List<FriendEntity> = this.map { it.toEntity() }
fun List<FriendEntity>.toDomain(): List<ReqresUserModel> = this.map { it.toDomain() }