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
import com.ubaya.a160419046_ubayakost.model.KostDatabase
import com.ubaya.a160419046_ubayakost.model.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class UserListViewModel(application: Application) : AndroidViewModel(application),CoroutineScope {
    val userLiveData = MutableLiveData<List<User>>()
    val userLoadErrorLiveData = MutableLiveData<Boolean>()
    val loadingLiveData = MutableLiveData<Boolean>()
    val TAG = "volleyTag"
    private var queue: RequestQueue?=null

    private var job = Job()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main


    fun refresh(){
        userLoadErrorLiveData.value = false
        loadingLiveData.value = true

        launch {
            val db = Room.databaseBuilder(
                getApplication(),
                KostDatabase::class.java, "kostdatabase"
            ).build()

            userLiveData.value = db.userDao().selectAllUser()
        }
    }

    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }
}