package com.ubaya.a160419046_ubayakost.model

import androidx.room.*

@Dao
interface BookmarkDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg bookmark: Bookmark)

    @Query("SELECT * FROM bookmark b INNER JOIN kost k on b.kostid = k.kostid")
    suspend fun selectAllBookmark(): List<Kost>

    @Delete
    suspend fun deleteBookmark(bookmark: Bookmark)
}