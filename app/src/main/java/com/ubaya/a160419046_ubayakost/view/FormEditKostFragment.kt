package com.ubaya.a160419046_ubayakost.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.ubaya.a160419046_ubayakost.R
import com.ubaya.a160419046_ubayakost.databinding.FragmentFormEditKostBinding
import com.ubaya.a160419046_ubayakost.model.Kost
import com.ubaya.a160419046_ubayakost.viewModel.KostDetailViewModel


/**
 * A simple [Fragment] subclass.
 * Use the [FormEditKostFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FormEditKostFragment : Fragment(),EditKostListener {
    private lateinit var viewmodel:KostDetailViewModel
    private lateinit var dataBinding:FragmentFormEditKostBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = DataBindingUtil.inflate<FragmentFormEditKostBinding>(inflater,R.layout.fragment_form_edit_kost,container,false)
        // Inflate the layout for this fragment
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewmodel =ViewModelProvider(this).get(KostDetailViewModel::class.java)

        val id =FormEditKostFragmentArgs.fromBundle(requireArguments()).kostId
        viewmodel.fetch(id)
        observeViewModel()
        dataBinding.editKostListener = this
    }
    fun observeViewModel(){
        viewmodel.kostLiveData.observe(viewLifecycleOwner, Observer {
            dataBinding.kost = it
        })

    }

    override fun onClickEditKost(view: View, obj: Kost) {
        viewmodel.update(obj)
        Toast.makeText(view.context, "Kost updated", Toast.LENGTH_SHORT).show()
        Navigation.findNavController(view).popBackStack()
    }


}