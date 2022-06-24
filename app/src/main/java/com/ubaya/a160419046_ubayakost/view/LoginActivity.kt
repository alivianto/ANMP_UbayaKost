package com.ubaya.a160419046_ubayakost.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ubaya.a160419046_ubayakost.GlobalData
import com.ubaya.a160419046_ubayakost.R
import com.ubaya.a160419046_ubayakost.model.User
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    private var queue: RequestQueue?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getSupportActionBar()?.hide()
        setContentView(R.layout.activity_login)
        buttonRegister.setOnClickListener {
            val username = inputName.text.toString()
            val pass = inputPassword.text.toString()
            queue = Volley.newRequestQueue(getApplication())
            val url = "https://cleonard712.github.io/kostJson/user.json"

            val stringRequest = StringRequest(
                Request.Method.GET,url,{
                    val sType = object : TypeToken<ArrayList<User>>(){}.type
                    val result = Gson().fromJson<ArrayList<User>>(it,sType)
                    var status = false
                    for (useritem in result)
                    {
                        if (useritem.username == username && useritem.password == pass)
                        {
                            GlobalData.userid = useritem.userid.toString()
                            status = true
                            val intent = Intent(this, MainActivity::class.java)
                            startActivity(intent)
                            finish()
                        }

                    }
                    if (status == false)
                    {
                        Toast.makeText(this,"Your username or password is incorrect",Toast.LENGTH_SHORT).show()
                    }
                    else
                    {
                        Toast.makeText(this,"Welcome",Toast.LENGTH_SHORT).show()
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

    }
}