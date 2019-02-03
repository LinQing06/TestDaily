package com.example.linqing.zhihumvp.detail


import com.example.linqing.zhihumvp.entitybean.ArticleDetailBean
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailPresenter(private val detailView: DetailContract.DetailView) : DetailContract.DetailPresenterInter {
    private val detailModel = DetailModel()
    lateinit private  var id: String

    override fun getIdP(id: String) {
        this.id = id

    }


    override fun getWebviewData() {

        val call = detailModel.getId(id)
        call.enqueue(object : Callback<ArticleDetailBean> {

            override fun onResponse(call: Call<ArticleDetailBean>, response: Response<ArticleDetailBean>) {


                val data = response.body()
                detailView.loadWebDetail(data!!)


            }

            override fun onFailure(call: Call<ArticleDetailBean>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }
}
