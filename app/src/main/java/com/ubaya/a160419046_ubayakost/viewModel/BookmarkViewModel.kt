package com.ubaya.a160419046_ubayakost.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.ubaya.a160419046_ubayakost.model.Bookmark
import com.ubaya.a160419046_ubayakost.model.Kost
import com.ubaya.a160419046_ubayakost.util.buildDb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class BookmarkViewModel(application: Application) :AndroidViewModel(application), CoroutineScope {
    val bookmarkLiveData = MutableLiveData<List<Kost>>()
    val bookmarkLoadErrorLiveData = MutableLiveData<Boolean>()
    val loadingLiveData = MutableLiveData<Boolean>()

    private var job = Job()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main
    fun getAllBookmark(){

        launch {
            val db = buildDb(getApplication())
            bookmarkLiveData.value = db.bookmarkDao().selectAllBookmark()
        }
    }


}