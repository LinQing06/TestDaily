package com.example.linqing.zhihumvp.list;

import com.example.linqing.zhihumvp.entitybean.ItemBean;

import java.util.List;

public interface ListContract {
    interface ListView{
        void updateList(ItemBean storiesBeanList);

        void refreshList();//init list
        void getItemData(ItemBean itemBean);
        void notifyChange();



    }
    interface ListPresenterInter{
        void getListData();
        void getPrevious(String date);


    }

}
