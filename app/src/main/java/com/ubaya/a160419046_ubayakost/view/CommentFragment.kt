package com.ubaya.a160419046_ubayakost.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.ubaya.a160419046_ubayakost.R
import com.ubaya.a160419046_ubayakost.databinding.FragmentCommentBinding
import com.ubaya.a160419046_ubayakost.viewModel.KostDetailViewModel
import kotlinx.android.synthetic.main.fragment_comment.*
import kotlinx.android.synthetic.main.fragment_facility.*


/**
 * A simple [Fragment] subclass.
 * Use the [CommentFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CommentFragment : Fragment() {
    private lateinit var viewModel: KostDetailViewModel
    private lateinit var dataBinding: FragmentCommentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = FragmentCommentBinding.inflate(inflater, container, false)
        return dataBinding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var kostid = 0
        arguments?.let {
            kostid = CommentFragmentArgs.fromBundle(requireArguments()).kostid
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