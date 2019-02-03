package com.example.linqing.zhihumvp.detail;

import android.content.Intent;

import com.example.linqing.zhihumvp.Sevice.RetrofitService;
import com.example.linqing.zhihumvp.entitybean.ArticleDetailBean;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailModel {

    private String BASEURL = "https://news-at.zhihu.com/";

//    public void setIntent(DetailActivity detailActivity){
//         Intent intent = detailActivity.getIntent();
//     }



    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASEURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    private RetrofitService webviewApi = retrofit.create(RetrofitService.class);

    //waiting for modify
    public Call<ArticleDetailBean> getId(String id) {

        return webviewApi.getId(id);

    }

}
