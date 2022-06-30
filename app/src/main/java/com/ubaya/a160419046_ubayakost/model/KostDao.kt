package com.ubaya.a160419046_ubayakost.model

import androidx.room.*

@Dao
interface KostDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg kost: Kost)

    @Query("SELECT * FROM kost")
    suspend fun selectAllKost(): List<Kost>

    @Query("SELECT * FROM kost WHERE kostid= :id")
    suspend fun selectKost(id: Int): Kost

    @Query("SELECT * FROM kost WHERE nama_kos LIKE '%' || :kataPencarian || '%'")
    suspend fun searchKost(kataPencarian: String): List<Kost>

    @Query("SELECT COUNT(*) FROM bookmark b INNER JOIN kost k ON b.kostid = k.kostid WHERE k.kostid = :idKost")
    suspend fun checkBookmarkKost(idKost: Int): Int

//    @Query("UPDATE kost set nama_kos = :nama, jenis= :jenis_kos, alamat = :alamat_kos, harga_per_bulan = :harga, fasilitas =:fasi,no_telepon = :no, photo_url = :foto, comment = :komentar, rating = :komentar where kostid = :id")
    @Update
    suspend fun update(kost:Kost)

    @Delete
    suspend fun deleteKost(kost: Kost)
}