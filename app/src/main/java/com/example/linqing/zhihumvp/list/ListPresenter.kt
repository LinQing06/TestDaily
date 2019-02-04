package com.example.linqing.zhihumvp.list

import com.example.linqing.zhihumvp.entitybean.ItemBean
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

class ListPresenter//deliver view
(//hold view and model
        private val uiView: ListContract.ListView) : ListContract.ListPresenterInter {
    private val listModel = ListModel()
    private var date: String? = null
    //get article list
    override fun getListData() {
        val call = listModel.update()
        launch{
            val data:ItemBean?
            try {
                data=call.execute().body()
                uiView.getItemData(data!!)
                launch(UI){
                    uiView.updateList(data!!)
                }

            }catch (t: IOException){
            t.printStackTrace()
        }


        }
//        call.enqueue(object : Callback<ItemBean> {
//            override fun onResponse(call: Call<ItemBean>, response: Response<ItemBean>) {
//                val data = response.body()
//                uiView.updateList(data!!)
//                uiView.getItemData(data)
//                uiView.refreshList()
//
//            }
//
//            override fun onFailure(call: Call<ItemBean>, t: Throwable) {
//                t.printStackTrace()
//            }
//        })
    }


    override fun getPrevious(date: String) {
        this.date = date
        val call = listModel.updatePrevious(date)
        launch {
            val data:ItemBean?
            try {
                data=call.execute().body()
                uiView.getItemData(data!!)
                launch (UI){
                    uiView.updateList(data!!)
                    uiView.notifyChange()
                }

            }catch (t: IOException){
                t.printStackTrace()
            }
        }
//        call.enqueue(object : Callback<ItemBean> {
//
//            override fun onResponse(call: Call<ItemBean>, response: Response<ItemBean>) {
//                val data = response.body()
//                uiView.getItemData(data!!)
//                uiView.notifyChange()
//
//            }
//
//            override fun onFailure(call: Call<ItemBean>, t: Throwable) {
//                t.printStackTrace()
//            }
//        })
//
   }

}
