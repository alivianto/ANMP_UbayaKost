package com.ubaya.a160419046_ubayakost.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.ubaya.a160419046_ubayakost.GlobalData
import com.ubaya.a160419046_ubayakost.R
import com.ubaya.a160419046_ubayakost.viewModel.UserDetailViewModel
import com.ubaya.a160419046_ubayakost.viewModel.UserListViewModel
import kotlinx.android.synthetic.main.fragment_user_detail.*
import kotlinx.android.synthetic.main.fragment_user_list.*


/**
 * A simple [Fragment] subclass.
 * Use the [UserDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class UserDetailFragment : Fragment() {
    private lateinit var viewModel: UserDetailViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this).get(UserDetailViewModel::class.java)
        viewModel.fetch(GlobalData.userid)

        observeViewModel()
    }
    private fun observeViewModel() {
        viewModel.userLiveData.observe(viewLifecycleOwner){
            name_user_detail.setText(it.name)
            email_user_detail.setText(it.email)
            username_user_detail.setText(it.username)
            phone_user_detail.setText(it.phone)
            pass_user_detail.setText(it.password)
        }
    }
}