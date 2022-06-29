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
import com.ubaya.a160419046_ubayakost.util.buildDb
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

    fun refresh(){
        loadingLiveData.value = true
        kostLoadErrorLiveData.value = false

        launch {
//            val db = Room.databaseBuilder(
//                getApplication(),
//                KostDatabase::class.java, "kostdatabase"
//            ).build()

            val db = buildDb(getApplication())
            kostLiveData.value = db.kostDao().selectAllKost()
        }
    }

    fun searchKost(kataPencarian: String){
        loadingLiveData.value = true
        kostLoadErrorLiveData.value = false

        launch {
            val db = buildDb(getApplication())
            kostLiveData.value = db.kostDao().searchKost(kataPencarian)
        }
    }



    fun deleteKost(kost: Kost) {
        launch {
//            val db = Room.databaseBuilder(
//                getApplication(),
//                KostDatabase::class.java, "kostdatabase"
//            ).build()
            val db = buildDb(getApplication())
            db.kostDao().deleteKost(kost)

            kostLiveData.value = db.kostDao().selectAllKost()
        }
    }


}