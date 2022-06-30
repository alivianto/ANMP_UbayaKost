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
import kotlinx.android.synthetic.main.fragment_dashboard.*
import kotlinx.android.synthetic.main.fragment_kost_list.*

/**
 * A simple [Fragment] subclass.
 * Use the [KostListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class KostListFragment : Fragment() {
    private lateinit var viewModel:KostListViewModel
    private val kostListAdapter = KostListAdapter(arrayListOf())
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_kost_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        GlobalData.currentFragment = "kostlist"
        viewModel = ViewModelProvider(this).get(KostListViewModel::class.java)
        viewModel.refresh()

        recKost.layoutManager = LinearLayoutManager(context)
        recKost.adapter = kostListAdapter

        observeViewModel()

        refreshLayout.setOnRefreshListener {
            recKost.visibility = View.VISIBLE
            textViewErrorKost.visibility = View.GONE
            progressLoadKost.visibility = View.VISIBLE
            viewModel.refresh()
            refreshLayout.isRefreshing = false
        }
    }
    private fun observeViewModel() {
        viewModel.kostLiveData.observe(viewLifecycleOwner){
            kostListAdapter.updatekostlist(it)
            textViewErrorKost.visibility = if(it.isEmpty()) View.VISIBLE else View.GONE
            progressLoadKost.visibility = if(it.isEmpty()) View.VISIBLE else View.GONE
        }

    }

}