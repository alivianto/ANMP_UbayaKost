package com.ubaya.a160419046_ubayakost.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ubaya.a160419046_ubayakost.GlobalData
import com.ubaya.a160419046_ubayakost.R
import com.ubaya.a160419046_ubayakost.model.Kost
import com.ubaya.a160419046_ubayakost.viewModel.BookmarkViewModel
import kotlinx.android.synthetic.main.fragment_bookmark.*


class BookmarkFragment : Fragment() {
    private lateinit var viewModel: BookmarkViewModel
    private var kostListAdapter = KostListAdapter(arrayListOf())
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bookmark, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        GlobalData.currentFragment = "BookmarkFragment"
        viewModel = ViewModelProvider(this).get(BookmarkViewModel::class.java)
        viewModel.getAllBookmark()

        recViewBookmark.layoutManager = LinearLayoutManager(context)
        recViewBookmark.adapter = kostListAdapter
        observeViewModel()
    }

    private fun observeViewModel(){
        viewModel.bookmarkLiveData.observe(viewLifecycleOwner){
            kostListAdapter.updatekostlist(it)
        }


    }
}