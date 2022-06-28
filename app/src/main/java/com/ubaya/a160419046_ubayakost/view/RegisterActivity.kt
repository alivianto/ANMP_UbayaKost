package com.ubaya.a160419046_ubayakost.view

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.ubaya.a160419046_ubayakost.R
import com.ubaya.a160419046_ubayakost.databinding.ActivityRegisterBinding
import com.ubaya.a160419046_ubayakost.model.User
import com.ubaya.a160419046_ubayakost.viewModel.UserDetailViewModel

class RegisterActivity : AppCompatActivity(),RegisterListener {
    private lateinit var dataBinding:ActivityRegisterBinding
    private lateinit var viewModel:UserDetailViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this,R.layout.activity_register)
        viewModel = ViewModelProvider(this).get(UserDetailViewModel::class.java)
        dataBinding.user = User("","","","","","1")
        dataBinding.registerListener = this
    }


    override fun onClickRegisterUser(view: View) {
        dataBinding.user?.let {
            val list = listOf(it)
            viewModel.adduser(list)
            Toast.makeText(this,"add register is success",Toast.LENGTH_SHORT).show()
        }
        val intent = Intent(this,LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}