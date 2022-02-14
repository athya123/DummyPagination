package com.tare.newsapp.network

import com.tare.newsapp.pojo.response.ResponseGetNews
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface Services {

    @Headers("X-Api-Key: 1120ca2de3f6495f83caef9b575d68ea")
    @GET("v2/everything")
    fun fetchNews(
        @Query("page") page: Int,
        @Query("q") query: String
    ): Single<ResponseGetNews>

}