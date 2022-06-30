package com.ubaya.a160419046_ubayakost.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.ubaya.a160419046_ubayakost.GlobalData
import com.ubaya.a160419046_ubayakost.R
import com.ubaya.a160419046_ubayakost.databinding.CardUserItemBinding
import com.ubaya.a160419046_ubayakost.model.User
import com.ubaya.a160419046_ubayakost.util.loadImage
import kotlinx.android.synthetic.main.card_kost_item.view.*
import kotlinx.android.synthetic.main.card_user_item.view.*

class UserListAdapter(val userList:ArrayList<User>) : RecyclerView.Adapter<UserListAdapter.UserViewHolder>() {
    class UserViewHolder(var view: CardUserItemBinding) : RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = CardUserItemBinding.inflate(inflater, parent, false)

        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.view.user = userList[position]

    }

    override fun getItemCount() = userList.size
    fun updateuserlist(newuserlist:List<User>){
        userList.clear()
        userList.addAll(newuserlist)
        notifyDataSetChanged()
    }
}