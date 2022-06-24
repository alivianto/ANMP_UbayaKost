package com.ubaya.a160419046_ubayakost.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.ubaya.a160419046_ubayakost.GlobalData
import com.ubaya.a160419046_ubayakost.R
import com.ubaya.a160419046_ubayakost.databinding.CardKostItemBinding
import com.ubaya.a160419046_ubayakost.model.Kost
import com.ubaya.a160419046_ubayakost.util.loadImage
import kotlinx.android.synthetic.main.card_kost_item.view.*

class KostListAdapter(val kostList:ArrayList<Kost>) : RecyclerView.Adapter<KostListAdapter.KostViewHolder>(), KostListSeeDetailClickListener {
    class KostViewHolder(var view: CardKostItemBinding):RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KostViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = CardKostItemBinding.inflate(inflater, parent, false)
        //val view = inflater.inflate(R.layout.card_kost_item,parent,false)
        return KostViewHolder(view)
    }

    override fun onBindViewHolder(holder: KostViewHolder, position: Int) {
        //val arraykost = kostList[position]
        with(holder.view){
            kost = kostList[position]

            kostListener = this@KostListAdapter
//            textViewKostName.text = arraykost.nama_kos
//            textViewJenis.text = arraykost.jenis
//            buttonReadMore.setOnClickListener {
//                var action:NavDirections
//                if(GlobalData.currentFragment =="dashboard"){
//                    action = DashboardFragmentDirections.actionItemHomeToKostListDetailFragment(arraykost.id.toString())
//                }
//                else{
//                    action = KostListFragmentDirections.actionKostListToKostListDetailFragment(arraykost.id.toString())
//                }
//                Navigation.findNavController(it).navigate(action)
//            }
//            imageViewKost.loadImage(arraykost.photo_url,progressBar)
        }
    }

    override fun getItemCount() = kostList.size
    fun updatekostlist(newkostlist: List<Kost>){
        kostList.clear()
        kostList.addAll(newkostlist)
        notifyDataSetChanged()
    }

    override fun onKostListSeeDetailClick(view: View) {
        var action:NavDirections
        if(GlobalData.currentFragment =="dashboard"){
            action = DashboardFragmentDirections.actionItemHomeToKostListDetailFragment(view.tag.toString().toInt())
        }
        else{
            action = KostListFragmentDirections.actionKostListToKostListDetailFragment(view.tag.toString().toInt())
        }
        Navigation.findNavController(view).navigate(action)
    }
}