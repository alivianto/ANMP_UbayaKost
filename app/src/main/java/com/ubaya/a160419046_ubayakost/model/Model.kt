package com.ubaya.a160419046_ubayakost.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Kost(
    @ColumnInfo(name="nama_kos")
    val nama_kos:String?,
    @ColumnInfo(name="jenis")
    val jenis:String?,
    @ColumnInfo(name="harga_per_bulan")
    val harga_per_bulan:String?,
    @ColumnInfo(name="fasilitas")
    val fasilitas:String?,
    @ColumnInfo(name="no_telepon")
    val no_telepon:String?,
    @ColumnInfo(name="photo_url")
    val photo_url:String?,
    @ColumnInfo(name="comment")
    val comment:String?,
    @ColumnInfo(name="rating")
    val rating:String?
) {
    @PrimaryKey(autoGenerate = true)
    var kostid: Int = 0
}

@Entity
data class Comment(
    @ColumnInfo(name="kostid")
    val kostid: Int,
    @ColumnInfo(name="kostid")
    val userid: Int,
    @ColumnInfo(name="comment")
    val comment: String
){
    @PrimaryKey(autoGenerate = true)
    var commentid: Int = 0
}

@Entity
data class Bookmark(
    @ColumnInfo(name="userid")
    val userid: Int,
    @ColumnInfo(name="kostid")
    val kostid: Int
){
    @PrimaryKey(autoGenerate = true)
    var bookmarkid: Int = 0
}

@Entity
data class User(
    @ColumnInfo(name="name")
    val name:String?,
    @ColumnInfo(name="email")
    val email:String?,
    @ColumnInfo(name="username")
    val username:String?,
    @ColumnInfo(name="phone")
    val phone:String?,
    @ColumnInfo(name="password")
    val password:String?,
    @ColumnInfo(name="active")
    val active:String?
) {
    @PrimaryKey(autoGenerate = true)
    var userid: Int = 0
}