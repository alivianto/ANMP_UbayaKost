package com.ubaya.a160419046_ubayakost.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.ubaya.a160419046_ubayakost.R
import com.ubaya.a160419046_ubayakost.databinding.FragmentFormAddKostBinding
import com.ubaya.a160419046_ubayakost.model.Kost
import com.ubaya.a160419046_ubayakost.viewModel.KostDetailViewModel

/**
 * A simple [Fragment] subclass.
 * Use the [FormAddKostFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FormAddKostFragment : Fragment(), AddKostListener {

    private lateinit var viewModel: KostDetailViewModel
    private lateinit var dataBinding: FragmentFormAddKostBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = FragmentFormAddKostBinding.inflate(inflater, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(KostDetailViewModel::class.java)

        dataBinding.kost = Kost("","","","","","","","","")
        dataBinding.addKostListener = this
    }

    override fun onClickAddKost(view: View) {
        dataBinding.kost?.let{
            val list = listOf(it)
            viewModel.addKost(list)
            Navigation.findNavController(view).popBackStack()
        }
    }


}