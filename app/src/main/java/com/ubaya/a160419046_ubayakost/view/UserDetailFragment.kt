package com.ubaya.a160419046_ubayakost.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.ubaya.a160419046_ubayakost.GlobalData
import com.ubaya.a160419046_ubayakost.R
import com.ubaya.a160419046_ubayakost.databinding.FragmentUserDetailBinding
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
    private lateinit var databinding:FragmentUserDetailBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        databinding = FragmentUserDetailBinding.inflate(inflater,container,false)
        // Inflate the layout for this fragment
        return databinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this).get(UserDetailViewModel::class.java)
        viewModel.getUserData(GlobalData.username)

        observeViewModel()
    }
    private fun observeViewModel() {
        viewModel.userLiveData.observe(viewLifecycleOwner){
            databinding.users = it
        }
    }
}