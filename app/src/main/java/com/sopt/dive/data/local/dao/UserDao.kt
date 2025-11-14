package com.sopt.dive.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sopt.dive.data.local.entity.UserEntity

@Dao
interface UserDao {
    @Query("SELECT * FROM user_table WHERE id = 0 LIMIT 1")
    suspend fun getUser(): UserEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: UserEntity)

    @Query("DELETE FROM user_table WHERE id = 0")
    suspend fun deleteUser()
}