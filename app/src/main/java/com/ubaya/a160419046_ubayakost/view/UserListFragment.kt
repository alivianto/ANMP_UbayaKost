package com.ubaya.a160419046_ubayakost.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ubaya.a160419046_ubayakost.GlobalData
import com.ubaya.a160419046_ubayakost.R
import com.ubaya.a160419046_ubayakost.viewModel.KostListViewModel
import com.ubaya.a160419046_ubayakost.viewModel.UserListViewModel
import kotlinx.android.synthetic.main.fragment_kost_list.*
import kotlinx.android.synthetic.main.fragment_user_list.*

/**
 * A simple [Fragment] subclass.
 * Use the [UserListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class UserListFragment : Fragment() {
    private lateinit var viewModel:UserListViewModel
    private val userListAdapter = UserListAdapter(arrayListOf())
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_list, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this).get(UserListViewModel::class.java)
        viewModel.refresh()

        recView_user.layoutManager = LinearLayoutManager(context)
        recView_user.adapter = userListAdapter

        observeViewModel()

        refreshlayoutuserlist.setOnRefreshListener {
            recView_user.visibility = View.GONE
            textViewError.visibility = View.GONE
            progressLoadUser.visibility = View.VISIBLE
            viewModel.refresh()
            refreshlayoutuserlist.isRefreshing = false
        }
    }
    private fun observeViewModel() {
        viewModel.userLiveData.observe(viewLifecycleOwner){
            userListAdapter.updateuserlist(it)
        }
        viewModel.userLoadErrorLiveData.observe(viewLifecycleOwner){
            textViewError.visibility = if(it) View.VISIBLE else View.GONE
        }
        viewModel.loadingLiveData.observe(viewLifecycleOwner){
            if(it){//sedang loading
                recView_user.visibility = View.GONE
                progressLoadUser.visibility = View.VISIBLE
            }
            else
            {
                recView_user.visibility = View.VISIBLE
                progressLoadUser.visibility = View.GONE
            }
        }
    }
}