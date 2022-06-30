package com.ubaya.a160419046_ubayakost.view

import android.util.Log
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

class KostListAdapter(var kostList:ArrayList<Kost>) : RecyclerView.Adapter<KostListAdapter.KostViewHolder>(), KostListSeeDetailClickListener,KostListEditClickListener {
    class KostViewHolder(var view: CardKostItemBinding):RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KostViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = CardKostItemBinding.inflate(inflater, parent, false)
        return KostViewHolder(view)
    }

    override fun onBindViewHolder(holder: KostViewHolder, position: Int) {
        holder.view.kost = kostList[position]
        holder.view.kostListener = this
        holder.view.kostEditListener = this
    }

    override fun getItemCount() = kostList.size

    fun updatekostlist(newkostlist: List<Kost>){
        kostList.clear()
        kostList.addAll(newkostlist)
        Log.d("listberhasil",kostList.toString())
        notifyDataSetChanged()
    }

    override fun onKostListSeeDetailClick(view: View) {
        var action:NavDirections
        if(GlobalData.currentFragment =="dashboard"){
            action = DashboardFragmentDirections.actionItemHomeToKostListDetailFragment(view.tag.toString().toInt())
        }
        else if(GlobalData.currentFragment == "BookmarkFragment"){
            action = BookmarkFragmentDirections.actionItemBookmarkToKostListDetailFragment(view.tag.toString().toInt())
        }
        else if(GlobalData.currentFragment == "SearchFragment"){
            action = SearchFragmentDirections.actionSearchFragmentToKostListDetailFragment(view.tag.toString().toInt())
        }
        else{
            action = KostListFragmentDirections.actionKostListToKostListDetailFragment(view.tag.toString().toInt())
        }
        Navigation.findNavController(view).navigate(action)
    }

    override fun onClickKostListEditClick(view: View) {
        var action:NavDirections
        if(GlobalData.currentFragment =="dashboard"){
            action = DashboardFragmentDirections.actionItemHomeToFormEditKostFragment(view.tag.toString().toInt())
        }
        else if(GlobalData.currentFragment == "BookmarkFragment"){
            action = BookmarkFragmentDirections.actionItemBookmarkToFormEditKostFragment(view.tag.toString().toInt())
        }
        else if(GlobalData.currentFragment == "SearchFragment"){
            action = SearchFragmentDirections.actionSearchFragmentToFormEditKostFragment(view.tag.toString().toInt())
        }
        else{
            action = KostListFragmentDirections.actionKostListToFormEditKostFragment(view.tag.toString().toInt())
        }
        Navigation.findNavController(view).navigate(action)
    }
}