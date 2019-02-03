package com.example.linqing.zhihumvp.detail;

import android.content.Intent;
import android.webkit.WebView;

import com.example.linqing.zhihumvp.entitybean.ArticleDetailBean;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailPresenter implements DetailContract.DetailPresenterInter {

    private DetailContract.DetailView detailView;
    private DetailModel detailModel = new DetailModel();
    String id;




    public DetailPresenter(DetailContract.DetailView detailView) {
        this.detailView = detailView;
    }

    @Override
    public void getIdP(String id){
        this.id=id;

    }



    @Override
    public void getWebviewData() {

        Call<ArticleDetailBean> call = detailModel.getId(id);
        call.enqueue(new Callback<ArticleDetailBean>() {

            @Override
            public void onResponse(Call<ArticleDetailBean> call, Response<ArticleDetailBean> response) {


                ArticleDetailBean data = response.body();
                detailView.loadWebDetail(data);


            }

            @Override
            public void onFailure(Call<ArticleDetailBean> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
