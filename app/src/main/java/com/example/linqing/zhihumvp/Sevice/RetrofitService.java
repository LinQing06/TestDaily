package com.example.linqing.zhihumvp.Sevice;

import com.example.linqing.zhihumvp.entitybean.ArticleDetailBean;
import com.example.linqing.zhihumvp.entitybean.ItemBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RetrofitService {

    @GET("api/3/news/latest")
    public Call<ItemBean> getListData();

    @GET("api/3/news/{id}")
    public Call<ArticleDetailBean> getId(@Path("id") String id);

    @GET("api/3/news/before/{date}")
    public Call<ItemBean> getDate(@Path("date") String date);


}
