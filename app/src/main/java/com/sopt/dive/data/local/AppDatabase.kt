package com.sopt.dive.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.sopt.dive.data.local.dao.FriendDao
import com.sopt.dive.data.local.dao.UserDao
import com.sopt.dive.data.local.entity.FriendEntity
import com.sopt.dive.data.local.entity.UserEntity

@Database(entities = [UserEntity::class, FriendEntity::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao() : UserDao
    abstract fun friendDao(): FriendDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "dive_database"
                ).build().also { INSTANCE = it }
            }
        }
    }
}