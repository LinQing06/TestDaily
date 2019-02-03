package com.example.linqing.zhihumvp.detail

import com.example.linqing.zhihumvp.Sevice.RetrofitService
import com.example.linqing.zhihumvp.entitybean.ArticleDetailBean

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DetailModel {

    private val BASEURL = "https://news-at.zhihu.com/"

    //    public void setIntent(DetailActivity detailActivity){
    //         Intent intent = detailActivity.getIntent();
    //     }


    private val retrofit = Retrofit.Builder()
            .baseUrl(BASEURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    private val webviewApi = retrofit.create(RetrofitService::class.java)

    //waiting for modify
    fun getId(id: String): Call<ArticleDetailBean> {

        return webviewApi.getId(id)

    }

}
