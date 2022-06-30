package com.ubaya.a160419046_ubayakost.view

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ubaya.a160419046_ubayakost.GlobalData
import com.ubaya.a160419046_ubayakost.R
import com.ubaya.a160419046_ubayakost.model.Kost
import com.ubaya.a160419046_ubayakost.viewModel.KostListViewModel
import kotlinx.android.synthetic.main.fragment_dashboard.*
import kotlinx.android.synthetic.main.fragment_kost_list.*
import java.util.*


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

        buttonViewMore.setOnClickListener {
            val action = DashboardFragmentDirections.actionItemHomeToKostList()
            Navigation.findNavController(it).navigate(action)
        }

        setHasOptionsMenu(true)

        fabAddKost.setOnClickListener {
            val action = DashboardFragmentDirections.actionItemHomeToFormAddKostFragment()
            Navigation.findNavController(it).navigate(action)
        }
    }
    private fun observeViewModel() {
        viewModel.kostLiveData.observe(viewLifecycleOwner){
            val arraykost:List<Kost> = it.slice(0..3)
            kostListAdapter.updatekostlist(ArrayList(arraykost))
            textViewErrorDashboard.visibility = if(it.isEmpty()) View.VISIBLE else View.GONE
            progressLoadDashboard.visibility = if(it.isEmpty()) View.VISIBLE else View.GONE

        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_search, menu)
        val item = menu?.findItem(R.id.itemSearch)

        return super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.itemSearch){
            val action = DashboardFragmentDirections.actionItemHomeToSearchFragment()
            findNavController().navigate(action)
        }
        return super.onOptionsItemSelected(item)
    }



}