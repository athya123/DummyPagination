package com.tare.newsapp.network

import androidx.paging.PagingState
import androidx.paging.rxjava3.RxPagingSource
import com.tare.newsapp.pojo.entities.Article
import com.tare.newsapp.pojo.response.ResponseGetNews
import com.tare.newsapp.utils.Constants
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class PagingSource
@Inject constructor(
    private val networkServices: Services,
) : RxPagingSource<Int, Article>() {

    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, Article>> {
        val position = params.key ?: 1

        return networkServices.fetchNews(position, "diet")
            .subscribeOn(Schedulers.io())
            .map { toLoadResult(it, position) }
            .onErrorReturn { LoadResult.Error(it) }
    }

    private fun toLoadResult(data: ResponseGetNews, position: Int): LoadResult<Int, Article> {
        return LoadResult.Page(
            data = data.articles,
            prevKey = if (position == 1) null else position - 1,
            nextKey = if (position == data.totalResults / 20) null else position + 1
        )
    }
}