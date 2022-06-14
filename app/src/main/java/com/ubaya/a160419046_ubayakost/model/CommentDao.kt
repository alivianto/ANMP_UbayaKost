package com.ubaya.a160419046_ubayakost.model

import androidx.room.*

@Dao
interface CommentDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg comment: Comment)

    @Delete
    suspend fun deleteBookmark(comment: Comment)
}