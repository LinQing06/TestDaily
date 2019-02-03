package com.example.linqing.zhihumvp.list

import com.example.linqing.zhihumvp.entitybean.ItemBean

interface ListContract {
    interface ListView {
        fun updateList(storiesBeanList: ItemBean)

        fun refreshList() //init list
        fun getItemData(itemBean: ItemBean)
        fun notifyChange()


    }

    interface ListPresenterInter {
        fun getListData()
        fun getPrevious(date: String)


    }

}
