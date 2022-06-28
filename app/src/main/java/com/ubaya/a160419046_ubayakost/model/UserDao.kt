package com.ubaya.a160419046_ubayakost.model

import androidx.room.*

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg user: User)

    @Query("SELECT * FROM user")
    suspend fun selectAllUser(): List<User>

    @Query("SELECT * FROM user WHERE username= :uname")
    suspend fun selectUser(uname: String): User

    @Query("SELECT * FROM user WHERE username = :uname")
    suspend fun checkLoginUser(uname: String): User

    @Delete
    suspend fun deleteUser(user: User)
}