package com.ubaya.a160419046_ubayakost.model

import androidx.room.*

@Dao
interface BookmarkDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg bookmark: Bookmark)


    @Query("SELECT k.nama_kos, k.jenis, k.harga_per_bulan, k.fasilitas, k.no_telepon, k.photo_url, k.comment, k.rating FROM kost k INNER JOIN bookmark b ON k.kostid = b.bookmarkid INNER JOIN user u ON b.bookmarkid = u.userid WHERE userid= :id")
    suspend fun selectAllBookmark(id: Int): List<Kost>

    @Delete
    suspend fun deleteBookmark(bookmark: Bookmark)
}