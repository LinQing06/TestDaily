package com.example.linqing.zhihumvp.Sevice

import com.example.linqing.zhihumvp.entitybean.ArticleDetailBean
import com.example.linqing.zhihumvp.entitybean.ItemBean

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitService {

    @get:GET("api/3/news/latest")
    val listData: Call<ItemBean>

    @GET("api/3/news/{id}")
    fun getId(@Path("id") id: String): Call<ArticleDetailBean>

    @GET("api/3/news/before/{date}")
    fun getDate(@Path("date") date: String): Call<ItemBean>


}
