package com.ubaya.a160419046_ubayakost.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ubaya.a160419046_ubayakost.model.User

class UserListViewModel(application: Application) : AndroidViewModel(application) {
    val userLiveData = MutableLiveData<ArrayList<User>>()
    val userLoadErrorLiveData = MutableLiveData<Boolean>()
    val loadingLiveData = MutableLiveData<Boolean>()
    val TAG = "volleyTag"
    private var queue: RequestQueue?=null

    fun refresh(){
        userLoadErrorLiveData.value = false
        loadingLiveData.value = true

        queue = Volley.newRequestQueue(getApplication())
        val url = "https://cleonard712.github.io/kostJson/user.json"

        val stringRequest = StringRequest(
            Request.Method.GET,url,{
                val sType = object : TypeToken<ArrayList<User>>(){}.type
                val result = Gson().fromJson<ArrayList<User>>(it,sType)
                var arruser:ArrayList<User> = arrayListOf()
                for (useritem in result)
                {
                    if (useritem.active == "1")
                    {
                        arruser.add(useritem)
                    }
                }
                userLiveData.value = arruser
                loadingLiveData.value = false
                Log.d("showvolley",it)
            },
            {
                loadingLiveData.value = false
                userLoadErrorLiveData.value = true
                Log.d("errorvolley",it.toString())
            }
        ).apply {
            tag = "TAG"
        }
        queue?.add(stringRequest)
    }

    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }
}