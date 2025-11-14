package com.sopt.dive.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sopt.dive.data.local.dao.UserDao
import com.sopt.dive.data.local.entity.UserEntity

@Database(entities = [UserEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao() : UserDao
}