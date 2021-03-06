package com.ubaya.a160419046_ubayakost.view

import android.icu.text.DecimalFormat
import android.view.View
import com.ubaya.a160419046_ubayakost.model.Kost

interface KostListSeeDetailClickListener {
    fun onKostListSeeDetailClick(view: View)
}

interface KostSeeDetailClickListener {
    fun onKostSeeFasilitasClick(view: View)
    fun onKostSeeRatingClick(view: View)
    fun onKostSeeCommentClick(view: View)
}
interface RegisterListener{
    fun onClickRegisterUser(view:View)
}

interface AddKostListener{
    fun onClickAddKost(view:View)
}

interface AddBookMarkListener{
    fun onClickBookmark(view: View)
}

interface SearchKostListener{
    fun onClickSearchKost(view: View)
}

interface EditKostListener{
    fun onClickEditKost(view:View,obj:Kost)
}
interface KostListEditClickListener{
    fun onClickKostListEditClick(view: View)
}