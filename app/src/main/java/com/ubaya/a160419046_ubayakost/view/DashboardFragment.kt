package com.ubaya.a160419046_ubayakost.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.ubaya.a160419046_ubayakost.GlobalData
import com.ubaya.a160419046_ubayakost.R
import com.ubaya.a160419046_ubayakost.model.Kost
import com.ubaya.a160419046_ubayakost.viewModel.KostListViewModel
import kotlinx.android.synthetic.main.fragment_dashboard.*
import kotlinx.android.synthetic.main.fragment_kost_list.*


/**
 * A simple [Fragment] subclass.
 * Use the [DashboardFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DashboardFragment : Fragment() {
    private lateinit var viewModel: KostListViewModel
    private val kostListAdapter = KostListAdapter(arrayListOf())
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        GlobalData.currentFragment = "dashboard"
        viewModel = ViewModelProvider(this).get(KostListViewModel::class.java)
        viewModel.refresh()

        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = kostListAdapter

        observeViewModel()
        refreshLayoutDashboard.setOnRefreshListener {
            recyclerView.visibility = View.GONE
            textViewErrorDashboard.visibility = View.GONE
            progressLoadDashboard.visibility = View.VISIBLE
            viewModel.refresh()
            refreshLayout.isRefreshing = false
        }
        buttonViewMore.setOnClickListener {
            val action = DashboardFragmentDirections.actionItemHomeToKostList()
            Navigation.findNavController(it).navigate(action)
        }
    }
    private fun observeViewModel() {
        viewModel.kostLiveData.observe(viewLifecycleOwner){
            val arraykost:List<Kost> = it.slice(0..3)
            kostListAdapter.updatekostlist(ArrayList(arraykost))
        }
        viewModel.kostLoadErrorLiveData.observe(viewLifecycleOwner){
            textViewErrorDashboard.visibility = if(it) View.VISIBLE else View.GONE
        }
        viewModel.loadingLiveData.observe(viewLifecycleOwner){
            if(it){//sedang loading
                recyclerView.visibility = View.GONE
                progressLoadDashboard.visibility = View.VISIBLE
            }
            else
            {
                recyclerView.visibility = View.VISIBLE
                progressLoadDashboard.visibility = View.GONE
            }
        }
    }

}