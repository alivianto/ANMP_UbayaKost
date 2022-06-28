package com.ubaya.a160419046_ubayakost.view

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ubaya.a160419046_ubayakost.GlobalData
import com.ubaya.a160419046_ubayakost.R
import com.ubaya.a160419046_ubayakost.model.User
import com.ubaya.a160419046_ubayakost.viewModel.UserDetailViewModel
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    private lateinit var viewModel:UserDetailViewModel
    var uname = ""
    var pwd = ""
    private fun observeViewModel(){
        viewModel.userLiveData.observe(this, Observer {
            uname = it.username
            pwd = it.password
        })
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getSupportActionBar()?.hide()
        setContentView(R.layout.activity_login)
        viewModel = ViewModelProvider(this).get(UserDetailViewModel::class.java)
        observeViewModel()
        buttonRegister.setOnClickListener {
            val username = inputName.text.toString()
            val pass = inputPassword.text.toString()
            viewModel.checklogin(username,pass)

            Toast.makeText(this,"$uname",Toast.LENGTH_SHORT).show()
            if (uname == username && pwd == pass){
                Toast.makeText(this,"berhasil login",Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(this,"username atau password salah",Toast.LENGTH_SHORT).show()
            }
            observeViewModel()
        }
        textViewRegister.setOnClickListener {
            val intent = Intent(this,RegisterActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}