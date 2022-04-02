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
import com.ubaya.a160419046_ubayakost.model.Kost

class KostDetailViewModel(application: Application) : AndroidViewModel(application) {
    val kostLiveData = MutableLiveData<Kost>()
    val TAG = "volleyTag"
    private var queue: RequestQueue?=null

    fun fetch(id:String?){

        queue = Volley.newRequestQueue(getApplication())
        val url = "https://cleonard712.github.io/kostJson/kost.json"

        val stringRequest = StringRequest(
            Request.Method.GET,url,{
                val sType = object : TypeToken<ArrayList<Kost>>(){}.type
                val result = Gson().fromJson<ArrayList<Kost>>(it,sType)
                for (kostitem in result)
                {
                    if (kostitem.id == id)
                    {
                        kostLiveData.value = kostitem
                    }
                }
                Log.d("showvolley",it)
            },
            {
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