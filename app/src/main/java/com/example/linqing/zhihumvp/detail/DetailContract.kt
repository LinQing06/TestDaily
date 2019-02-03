package com.example.linqing.zhihumvp.detail

import com.example.linqing.zhihumvp.entitybean.ArticleDetailBean

interface DetailContract {
    interface DetailView {
        fun loadWebDetail(data: ArticleDetailBean)


    }

    interface DetailPresenterInter {

        fun getWebviewData()
        fun getIdP(id: String)
    }
}
