package com.ubaya.a160419046_ubayakost.model

data class Kost(
    val id:String?,
    val nama_kos:String?,
    val  jenis:String?,
    val harga_per_bulan:String?,
    val fasilitas:String?,
    val no_telepon:String?,
    val photo_url:String?,
    val comment:String?,
    val rating:String?
)
data class User(
    val id:String?,
    val name:String?,
    val email:String?,
    val username:String?,
    val phone:String?,
    val password:String?,
    val active:String?
)