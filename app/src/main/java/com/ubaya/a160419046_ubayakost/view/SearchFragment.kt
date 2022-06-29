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
import com.ubaya.a160419046_ubayakost.databinding.FragmentSearchBinding
import com.ubaya.a160419046_ubayakost.viewModel.KostListViewModel
import kotlinx.android.synthetic.main.fragment_kost_list.*
import kotlinx.android.synthetic.main.fragment_search.*

class SearchFragment : Fragment(), SearchKostListener {
    private lateinit var viewModel: KostListViewModel
    private lateinit var dataBinding: FragmentSearchBinding
    private val kostListAdapter = KostListAdapter(arrayListOf())


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = FragmentSearchBinding.inflate(inflater, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        GlobalData.currentFragment = "SearchFragment"

        viewModel = ViewModelProvider(this).get(KostListViewModel::class.java)

        recViewSearchResult.layoutManager = LinearLayoutManager(context)
        recViewSearchResult.adapter = kostListAdapter

        observeViewModel()

        dataBinding.search = this
    }

    private fun observeViewModel() {
        viewModel.kostLiveData.observe(viewLifecycleOwner){
            kostListAdapter.updatekostlist(it)
//            textViewErrorKost.visibility = if(it.isEmpty()) View.VISIBLE else View.GONE
//            progressLoadKost.visibility = if(it.isEmpty()) View.VISIBLE else View.GONE
        }
    }

    override fun onClickSearchKost(view: View) {
        var kataPencarian = inputNamaKost.text.toString()
        viewModel.searchKost(kataPencarian)
    }
}