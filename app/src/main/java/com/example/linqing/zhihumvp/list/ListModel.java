package com.example.linqing.zhihumvp.list;

import com.example.linqing.zhihumvp.Sevice.RetrofitService;
import com.example.linqing.zhihumvp.entitybean.ItemBean;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListModel {
    private String date;
    private String BASEURL = "https://news-at.zhihu.com/";


    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASEURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    private RetrofitService listApi = retrofit.create(RetrofitService.class);

    public Call<ItemBean> update(){
        return listApi.getListData();
    }
    public Call<ItemBean> updatePrevious(String date){
        return listApi.getDate(date);
    }


}
