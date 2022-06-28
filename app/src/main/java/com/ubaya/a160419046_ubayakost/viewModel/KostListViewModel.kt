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
import com.ubaya.a160419046_ubayakost.model.Kost
import com.ubaya.a160419046_ubayakost.model.KostDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class KostListViewModel(application: Application) : AndroidViewModel(application), CoroutineScope {
    val kostLiveData = MutableLiveData<List<Kost>>()
    val kostLoadErrorLiveData = MutableLiveData<Boolean>()
    val loadingLiveData = MutableLiveData<Boolean>()

    private var job = Job()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    val TAG = "volleyTag"
    private var queue: RequestQueue?=null

    fun refresh(){
        kostLoadErrorLiveData.value = false
        loadingLiveData.value = true

        launch {
            val db = Room.databaseBuilder(
                getApplication(),
                KostDatabase::class.java, "kostdatabase"
            ).build()

            kostLiveData.value = db.kostDao().selectAllKost()
        }

//        queue = Volley.newRequestQueue(getApplication())
//        val url = "https://cleonard712.github.io/kostJson/kost.json"
//
//        val stringRequest = StringRequest(
//            Request.Method.GET,url,{
//                val sType = object : TypeToken<ArrayList<Kost>>(){}.type
//                val result = Gson().fromJson<ArrayList<Kost>>(it,sType)
//                kostLiveData.value = result
//                loadingLiveData.value = false
//                Log.d("showvolley",it)
//            },
//            {
//                loadingLiveData.value = false
//                kostLoadErrorLiveData.value = true
//                Log.d("errorvolley",it.toString())
//            }
//        ).apply {
//            tag = "TAG"
//        }
//        queue?.add(stringRequest)
    }

    fun deleteKost(kost: Kost) {
        launch {
            val db = Room.databaseBuilder(
                getApplication(),
                KostDatabase::class.java, "kostdatabase"
            ).build()

            db.kostDao().deleteKost(kost)

            kostLiveData.value = db.kostDao().selectAllKost()
        }
    }

    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }
}