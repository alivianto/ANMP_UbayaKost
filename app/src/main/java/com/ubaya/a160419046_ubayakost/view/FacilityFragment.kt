package com.ubaya.a160419046_ubayakost.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.ubaya.a160419046_ubayakost.R
import com.ubaya.a160419046_ubayakost.databinding.FragmentFacilityBinding
import com.ubaya.a160419046_ubayakost.util.loadImage
import com.ubaya.a160419046_ubayakost.viewModel.KostDetailViewModel
import kotlinx.android.synthetic.main.fragment_facility.*
import kotlinx.android.synthetic.main.fragment_kost_list_detail.*


/**
 * A simple [Fragment] subclass.
 * Use the [FacilityFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FacilityFragment : Fragment() {
    private lateinit var viewModel: KostDetailViewModel
    private lateinit var dataBinding: FragmentFacilityBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = FragmentFacilityBinding.inflate(inflater, container, false)
        return dataBinding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var kostid = 0
        arguments?.let {
            kostid = FacilityFragmentArgs.fromBundle(requireArguments()).kostid
        }
        viewModel = ViewModelProvider(this).get(KostDetailViewModel::class.java)
        viewModel.fetch(kostid)

        observeViewModel()
    }
    private fun observeViewModel() {
        viewModel.kostLiveData.observe(viewLifecycleOwner){
            dataBinding.kost = it
        }
    }

}