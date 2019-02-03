package com.example.linqing.zhihumvp.list

import com.example.linqing.zhihumvp.Sevice.RetrofitService
import com.example.linqing.zhihumvp.entitybean.ItemBean

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ListModel {
    private val date: String? = null
    private val BASEURL = "https://news-at.zhihu.com/"


    private val retrofit = Retrofit.Builder()
            .baseUrl(BASEURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    private val listApi = retrofit.create(RetrofitService::class.java)

    fun update(): Call<ItemBean> {
        return listApi.listData
    }

    fun updatePrevious(date: String): Call<ItemBean> {
        return listApi.getDate(date)
    }


}
