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

class KostDetailViewModel(application: Application) : AndroidViewModel(application), CoroutineScope {
    val kostLiveData = MutableLiveData<Kost>()

    private val job = Job()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

//    val TAG = "volleyTag"
//    private var queue: RequestQueue?=null

    fun addKost(list: List<Kost>) {
        launch {
            val db = Room.databaseBuilder(
                getApplication(), KostDatabase::class.java, "kostdatabase"
            ).build()
            db.kostDao().insertAll(*list.toTypedArray())
        }
    }

    fun fetch(uuid: Int){
        launch {
            val db = Room.databaseBuilder(
                getApplication(), KostDatabase::class.java, "kostdatabase"
            ).build()
            kostLiveData.value = db.kostDao().selectKost(uuid)
        }

//        queue = Volley.newRequestQueue(getApplication())
//        val url = "https://cleonard712.github.io/kostJson/kost.json"
//
//        val stringRequest = StringRequest(
//            Request.Method.GET,url,{
//                val sType = object : TypeToken<ArrayList<Kost>>(){}.type
//                val result = Gson().fromJson<ArrayList<Kost>>(it,sType)
//                for (kostitem in result)
//                {
//                    if (kostitem.id == uuid)
//                    {
//                        kostLiveData.value = kostitem
//                    }
//                }
//                Log.d("showvolley",it)
//            },
//            {
//                Log.d("errorvolley",it.toString())
//            }
//        ).apply {
//            tag = "TAG"
//        }
//        queue?.add(stringRequest)
    }

//    override fun onCleared() {
//        super.onCleared()
//        queue?.cancelAll(TAG)
//    }
}