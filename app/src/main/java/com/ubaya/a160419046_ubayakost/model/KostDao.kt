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

    @Delete
    suspend fun deleteKost(kost: Kost)
}