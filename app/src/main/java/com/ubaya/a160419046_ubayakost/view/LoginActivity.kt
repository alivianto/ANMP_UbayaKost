package com.ubaya.a160419046_ubayakost.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ubaya.a160419046_ubayakost.GlobalData
import com.ubaya.a160419046_ubayakost.R
import com.ubaya.a160419046_ubayakost.viewModel.UserDetailViewModel
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    private lateinit var viewModel:UserDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getSupportActionBar()?.hide()
        setContentView(R.layout.activity_login)
        viewModel = ViewModelProvider(this).get(UserDetailViewModel::class.java)
        buttonRegister.setOnClickListener {
            val username = inputNamaKost.text.toString()
            val pass = inputPassword.text.toString()
            viewModel.checklogin(username,pass)
            var check = false
            viewModel.userLiveData.observe(this, Observer {
                check = it.username == username && it.password == pass
                Log.d("checkLoginData",check.toString())
                if(check){
                    GlobalData.username = username
                    Toast.makeText(this,"Berhasil login",Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                else
                {
                    Toast.makeText(this,"Username atau password salah",Toast.LENGTH_SHORT).show()
                }
            })
        }

        textViewRegister.setOnClickListener {
            val intent = Intent(this,RegisterActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}