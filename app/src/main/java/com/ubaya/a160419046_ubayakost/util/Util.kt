package com.ubaya.a160419046_ubayakost.util

import android.content.Context
import android.media.Image
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.databinding.BindingAdapter
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.ubaya.a160419046_ubayakost.R
import com.ubaya.a160419046_ubayakost.model.KostDatabase
import java.lang.Exception

val DB_NAME = "kostdatabase"

fun buildDb(context: Context) =
    Room.databaseBuilder(context, KostDatabase::class.java, "kostdatabase")
        .addMigrations(MIGRATION_1_2)
        .build()

val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE kost ADD COLUMN alamat VARCHAR(50)")
    }
}



@BindingAdapter("android:imageUrl","android:progressBar")
fun loadImageFromUrl(view: ImageView, url: String?, pb: ProgressBar) {
    view.loadImage(url, pb)
}

fun ImageView.loadImage(url:String?,progressbar: ProgressBar){
    Picasso.get()
        .load(url)
        .resize(400,400)
        .centerCrop()
        .error(R.drawable.ic_baseline_error_24)
        .into(this, object : Callback {
            override fun onSuccess() {
                progressbar.visibility = View.GONE
            }

            override fun onError(e: Exception?) {

            }
        })
}
