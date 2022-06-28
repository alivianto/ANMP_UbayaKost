package com.ubaya.a160419046_ubayakost.view

import android.icu.text.DecimalFormat
import android.view.View

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