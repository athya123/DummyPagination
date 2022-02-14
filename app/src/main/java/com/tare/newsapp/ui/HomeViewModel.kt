package com.tare.newsapp.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.rxjava3.cachedIn
import com.google.android.gms.ads.AdRequest
import com.tare.newsapp.pojo.entities.Article
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.core.Flowable
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
class HomeViewModel
@Inject constructor(
    homeRepository: HomeRepository,
) : ViewModel() {


    val clickedNews = MutableLiveData<Article>()

    val flowable = homeRepository.getNews().cachedIn(viewModelScope)

    fun onClickNews(item: Article) {
        clickedNews.postValue(item)
    }

}