package com.example.linqing.zhihumvp.detail;

import com.example.linqing.zhihumvp.entitybean.ArticleDetailBean;

public interface DetailContract {
    interface DetailView{
        void loadWebDetail(ArticleDetailBean data);


    }

    interface DetailPresenterInter{

        void getWebviewData();
        void getIdP(String id);
    }
}
