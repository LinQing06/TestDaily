package com.example.linqing.zhihumvp.list;

import com.example.linqing.zhihumvp.entitybean.ItemBean;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListPresenter implements ListContract.ListPresenterInter {
    //hold view and model
    private ListContract.ListView uiView;
    private ListModel listModel = new ListModel();
    private String date;
    //deliver view
    public ListPresenter(ListContract.ListView uiView) {
        this.uiView = uiView;
    }
    //get article list
    @Override
    public void getListData() {
        Call<ItemBean> call = listModel.update();
        call.enqueue(new Callback<ItemBean>() {
            @Override
            public void onResponse(Call<ItemBean> call, Response<ItemBean> response) {
                ItemBean data = response.body();
                uiView.updateList(data);
                uiView.getItemData(data);
                uiView.refreshList();

            }

            @Override
            public void onFailure(Call<ItemBean> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }


    @Override
    public void getPrevious(String date) {
        this.date = date;
        Call<ItemBean> call = listModel.updatePrevious(date);
        call.enqueue(new Callback<ItemBean>() {

            @Override
            public void onResponse(Call<ItemBean> call, Response<ItemBean> response) {
                ItemBean data = response.body();
                uiView.getItemData(data);
                uiView.notifyChange();

            }

            @Override
            public void onFailure(Call<ItemBean> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }

}
