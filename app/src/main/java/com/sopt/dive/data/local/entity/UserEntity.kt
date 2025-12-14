package com.sopt.dive.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class UserEntity(
    @PrimaryKey val id: Int,
    val userId: String,
    val name: String,
    val email: String,
    val age: Int,
    val profileUrl: String
)
