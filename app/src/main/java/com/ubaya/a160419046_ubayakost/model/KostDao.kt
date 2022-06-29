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

    @Delete
    suspend fun deleteKost(kost: Kost)
}