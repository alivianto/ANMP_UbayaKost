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
import com.ubaya.a160419046_ubayakost.databinding.FragmentKostListDetailBinding
import com.ubaya.a160419046_ubayakost.model.Bookmark
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
class KostListDetailFragment : Fragment(), KostSeeDetailClickListener,AddBookMarkListener {
    private lateinit var viewModel: KostDetailViewModel
    private lateinit var dataBinding: FragmentKostListDetailBinding
    var kostid = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = FragmentKostListDetailBinding.inflate(inflater, container, false)
        //return inflater.inflate(R.layout.fragment_kost_list_detail, container, false)
        return dataBinding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        arguments?.let {
            kostid = KostListDetailFragmentArgs.fromBundle(requireArguments()).kostid
        }
        viewModel = ViewModelProvider(this).get(KostDetailViewModel::class.java)
        viewModel.fetch(kostid)
        observeViewModel()
//        buttonFasilitas.setOnClickListener {
//            val action = KostListDetailFragmentDirections.actionKostListDetailFragmentToFacilityFragment(kostid)
//            Navigation.findNavController(it).navigate(action)
//        }
//        buttonComment.setOnClickListener {
//            val action = KostListDetailFragmentDirections.actionKostListDetailFragmentToCommentFragment(kostid)
//            Navigation.findNavController(it).navigate(action)
//        }
//        buttonRating.setOnClickListener {
//            val action = KostListDetailFragmentDirections.actionKostListDetailFragmentToRatingFragment(kostid)
//            Navigation.findNavController(it).navigate(action)
//        }


        dataBinding.kostListener = this
        dataBinding.bookmarkListener = this
    }
    private fun observeViewModel() {
        viewModel.kostLiveData.observe(viewLifecycleOwner){
            dataBinding.kost = it
//            textViewNameOfKost.text = it.nama_kos
//            textViewJenisKost.text = it.jenis
//            textViewHarga.text = it.harga_per_bulan
//            textViewNoTelepon.text = it.no_telepon
//            imageViewKostDetail.loadImage(it.photo_url,progressBarPhotoDetail)
        }
    }

    override fun onKostSeeFasilitasClick(view: View) {
        val action = KostListDetailFragmentDirections.actionKostListDetailFragmentToFacilityFragment(kostid)
        Navigation.findNavController(view).navigate(action)
    }

    override fun onKostSeeRatingClick(view: View) {
        val action = KostListDetailFragmentDirections.actionKostListDetailFragmentToRatingFragment(kostid)
        Navigation.findNavController(view).navigate(action)
    }

    override fun onKostSeeCommentClick(view: View) {
        val action = KostListDetailFragmentDirections.actionKostListDetailFragmentToCommentFragment(kostid)
        Navigation.findNavController(view).navigate(action)
    }

    override fun onClickBookmark(view: View) {
        var status = true
        dataBinding.bookmark = Bookmark(GlobalData.userid, kostid)
        imageButtonBookmark.setOnClickListener {
            Toast.makeText(context, "Hello", Toast.LENGTH_SHORT).show()
            if(status){
                imageButtonBookmark.setBackgroundResource(R.drawable.ic_baseline_bookmark_border_24)
                dataBinding.bookmark?.let {
                    viewModel.deteleBookmark(it)
                }
            } else {
                imageButtonBookmark.setBackgroundResource(R.drawable.ic_baseline_bookmark_24)
                dataBinding.bookmark?.let {
                    val list = listOf(it)
                    viewModel.addBookmark(list)
                }
            }
            status = !status
        }
    }

}