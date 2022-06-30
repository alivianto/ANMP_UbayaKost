package com.ubaya.a160419046_ubayakost.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Kost(
    @ColumnInfo(name="nama_kos")
    var nama_kos:String?,
    @ColumnInfo(name="jenis")
    var jenis:String?,
    @ColumnInfo(name="alamat")
    var alamat:String?,
    @ColumnInfo(name="harga_per_bulan")
    var harga_per_bulan:String?,
    @ColumnInfo(name="fasilitas")
    var fasilitas:String?,
    @ColumnInfo(name="no_telepon")
    var no_telepon:String?,
    @ColumnInfo(name="photo_url")
    var photo_url:String?,
    @ColumnInfo(name="comment")
    var comment:String?,
    @ColumnInfo(name="rating")
    var rating:String?
) {
    @PrimaryKey(autoGenerate = true)
    var kostid: Int = 0
}

@Entity
data class Comment(
    @ColumnInfo(name="kostid")
    var kostid: Int,
    @ColumnInfo(name="userid")
    var userid: Int,
    @ColumnInfo(name="comment")
    var comment: String
){
    @PrimaryKey(autoGenerate = true)
    var commentid: Int = 0
}

@Entity
data class Bookmark(
    @ColumnInfo(name="userid")
    var userid: Int,
    @ColumnInfo(name="kostid")
    var kostid: Int
){
    @PrimaryKey(autoGenerate = true)
    var bookmarkid: Int = 0
}

@Entity
data class User(
    @ColumnInfo(name="name")
    var name:String,
    @ColumnInfo(name="email")
    var email:String,
    @ColumnInfo(name="username")
    var username:String,
    @ColumnInfo(name="phone")
    var phone:String,
    @ColumnInfo(name="password")
    var password:String,
    @ColumnInfo(name="active")
    var active:String
) {
    @PrimaryKey(autoGenerate = true)
    var userid: Int = 0
}

data class Search(
    var kataPencarian: String
)

