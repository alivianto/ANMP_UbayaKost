package com.ubaya.a160419046_ubayakost.util

import android.media.Image
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.ubaya.a160419046_ubayakost.R
import java.lang.Exception

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
