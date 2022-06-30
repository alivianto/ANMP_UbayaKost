package com.ubaya.a160419046_ubayakost.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ubaya.a160419046_ubayakost.GlobalData
import com.ubaya.a160419046_ubayakost.model.Bookmark
import com.ubaya.a160419046_ubayakost.model.Kost
import com.ubaya.a160419046_ubayakost.model.KostDatabase
import com.ubaya.a160419046_ubayakost.util.buildDb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class KostDetailViewModel(application: Application) : AndroidViewModel(application), CoroutineScope {
    val kostLiveData = MutableLiveData<Kost>()
    var checkBookmark = MutableLiveData<Int>()
    private val job = Job()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    fun addKost(list: List<Kost>) {
        launch {
            val db = buildDb(getApplication())
            db.kostDao().insertAll(*list.toTypedArray())
        }
    }

    fun addBookmark(list: List<Bookmark>) {
        launch {
            val db = buildDb(getApplication())
            db.bookmarkDao().insertAll(*list.toTypedArray())
        }
    }

    fun deteleBookmark(kostId: Int, userId: Int){
        launch {
            val db = buildDb(getApplication())
            db.bookmarkDao().deleteBookmarkData(kostId, userId)
        }
    }

    fun checkBookmark(kostId: Int){
        launch {
            val db = buildDb(getApplication())
            checkBookmark.value = db.kostDao().checkBookmarkKost(kostId,GlobalData.userid)
        }
    }

    fun update(kost: Kost)
    {
        launch {
            val db = buildDb(getApplication())
            db.kostDao().update(kost)
        }
    }
    fun fetch(uuid: Int){
        launch {
            val db = buildDb(getApplication())
            kostLiveData.value = db.kostDao().selectKost(uuid)
        }
    }
}