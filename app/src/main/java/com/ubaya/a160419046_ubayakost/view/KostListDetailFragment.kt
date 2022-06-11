package com.ubaya.a160419046_ubayakost.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.ubaya.a160419046_ubayakost.GlobalData
import com.ubaya.a160419046_ubayakost.R
import com.ubaya.a160419046_ubayakost.util.loadImage
import com.ubaya.a160419046_ubayakost.viewModel.KostDetailViewModel
import com.ubaya.a160419046_ubayakost.viewModel.UserDetailViewModel
import kotlinx.android.synthetic.main.fragment_kost_list_detail.*
import kotlinx.android.synthetic.main.fragment_user_detail.*

/**
 * A simple [Fragment] subclass.
 * Use the [KostListDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class KostListDetailFragment : Fragment() {
    private lateinit var viewModel: KostDetailViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_kost_list_detail, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var kostid = ""
        arguments?.let {
            kostid = KostListDetailFragmentArgs.fromBundle(requireArguments()).kostid
        }
        viewModel = ViewModelProvider(this).get(KostDetailViewModel::class.java)
        viewModel.fetch(kostid)

        observeViewModel()
        buttonFasilitas.setOnClickListener {
            val action = KostListDetailFragmentDirections.actionKostListDetailFragmentToFacilityFragment(kostid)
            Navigation.findNavController(it).navigate(action)
        }
        buttonComment.setOnClickListener {
            val action = KostListDetailFragmentDirections.actionKostListDetailFragmentToCommentFragment(kostid)
            Navigation.findNavController(it).navigate(action)
        }
        buttonRating.setOnClickListener {
            val action = KostListDetailFragmentDirections.actionKostListDetailFragmentToRatingFragment(kostid)
            Navigation.findNavController(it).navigate(action)
        }
        var status = false
        imageButtonBookmark.setOnClickListener {
            Toast.makeText(context, "Hello", Toast.LENGTH_SHORT).show()
            if(status){
                imageButtonBookmark.setBackgroundResource(R.drawable.ic_baseline_bookmark_border_24)
            } else {
                imageButtonBookmark.setBackgroundResource(R.drawable.ic_baseline_bookmark_24)
            }
            status = !status
        }
    }
    private fun observeViewModel() {
        viewModel.kostLiveData.observe(viewLifecycleOwner){
            textViewNameOfKost.text = it.nama_kos
            textViewJenisKost.text = it.jenis
            textViewHarga.text = it.harga_per_bulan
            textViewNoTelepon.text = it.no_telepon
            imageViewKostDetail.loadImage(it.photo_url,progressBarPhotoDetail)
        }
    }

}