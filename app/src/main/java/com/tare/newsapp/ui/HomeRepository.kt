package com.tare.newsapp.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.rxjava3.flowable
import com.google.android.gms.ads.AdLoader
import com.google.android.gms.ads.nativead.NativeAd
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.tare.newsapp.pojo.entities.Article
import io.reactivex.rxjava3.core.Flowable
import javax.inject.Inject
import com.tare.newsapp.network.PagingSource

class HomeRepository @Inject constructor(
    private val pagingSource: PagingSource,
) {
    fun getNews(): Flowable<PagingData<Article>> {

        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = true,
                maxSize = 100,
                prefetchDistance = 4,
                initialLoadSize = 40,
            ), pagingSourceFactory = {pagingSource}
        ).flowable
    }
}