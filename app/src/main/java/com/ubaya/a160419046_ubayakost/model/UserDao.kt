package com.ubaya.a160419046_ubayakost.model

import androidx.room.*

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg user: User)

    @Query("SELECT * FROM user")
    suspend fun selectAllUser(): List<User>

    @Query("SELECT * FROM user WHERE userid= :id")
    suspend fun selectUser(id: Int): User

    @Query("SELECT * FROM user WHERE username =:uname and password =:pass")
    suspend fun checkLoginUser(uname: String,pass:String): User

    @Delete
    suspend fun deleteUser(user: User)
}