package com.example.linqing.zhihumvp.list

import com.example.linqing.zhihumvp.entitybean.ItemBean
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListPresenter//deliver view
(//hold view and model
        private val uiView: ListContract.ListView) : ListContract.ListPresenterInter {
    private val listModel = ListModel()
    private var date: String? = null
    //get article list
    override fun getListData() {
        val call = listModel.update()
        call.enqueue(object : Callback<ItemBean> {
            override fun onResponse(call: Call<ItemBean>, response: Response<ItemBean>) {
                val data = response.body()
                uiView.updateList(data!!)
                uiView.getItemData(data)
                uiView.refreshList()

            }

            override fun onFailure(call: Call<ItemBean>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }


    override fun getPrevious(date: String) {
        this.date = date
        val call = listModel.updatePrevious(date)
        call.enqueue(object : Callback<ItemBean> {

            override fun onResponse(call: Call<ItemBean>, response: Response<ItemBean>) {
                val data = response.body()
                uiView.getItemData(data!!)
                uiView.notifyChange()

            }

            override fun onFailure(call: Call<ItemBean>, t: Throwable) {
                t.printStackTrace()
            }
        })

    }

}
