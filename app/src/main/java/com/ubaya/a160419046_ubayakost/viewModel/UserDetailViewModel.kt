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
import com.ubaya.a160419046_ubayakost.model.User
import com.ubaya.a160419046_ubayakost.util.buildDb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class UserDetailViewModel(application: Application) : AndroidViewModel(application),CoroutineScope {
    val userLiveData = MutableLiveData<User>()
    private val job = Job()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    fun adduser(list: List<User>) {
        launch {
//            val db = Room.databaseBuilder(
//                getApplication(), KostDatabase::class.java, "kostdatabase"
//            ).build()
            val db = buildDb(getApplication())
            db.userDao().insertAll(*list.toTypedArray())
        }
    }
    fun fetch(id:Int){
        launch {
//            val db = Room.databaseBuilder(
//                getApplication(), KostDatabase::class.java, "kostdatabase"
//            ).build()
            val db = buildDb(getApplication())
            userLiveData.value = db.userDao().selectUser(id)
        }
    }
    fun checklogin(uname:String,pass:String) {
        launch {
//            val db = Room.databaseBuilder(
//                getApplication(), KostDatabase::class.java, "kostdatabase"
//            ).build()
            val db = buildDb(getApplication())
            userLiveData.value = db.userDao().checkLoginUser(uname)
        }
    }

}