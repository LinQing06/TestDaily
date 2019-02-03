package com.example.linqing.zhihumvp.list;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import com.example.linqing.zhihumvp.R;
import com.example.linqing.zhihumvp.entitybean.ItemBean;

import java.util.List;
import java.util.Objects;

public class ListActivity extends AppCompatActivity implements ListContract.ListView {
    private MyAdapter adapter;
    private SwipeRefreshLayout mSwipeRefresh;
    private RecyclerView recyclerView;
    private LinearLayout linearLayout;
    private ItemBean itemBean;
    private int totalCount;
    private int mLastVisibleItem;
    private int visibleCount;
    private boolean isLoadData = false;
    private ListContract.ListPresenterInter presenter = new ListPresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initId();
        initRecyclerView();
        presenter.getListData();

    }

    private void initId() {

        recyclerView = findViewById(R.id.recycler);

    }

    private void initRecyclerView() {
        LinearLayoutManager linearLayout = new LinearLayoutManager(this);
        linearLayout.setOrientation(LinearLayoutManager.VERTICAL);
        adapter = new MyAdapter(this);
        recyclerView.setLayoutManager(linearLayout);
        recyclerView.setAdapter(adapter);

    }

    //init
    @Override
    public void refreshList() {
        downRefresh();
        setUpLoad();
        adapter.getData(itemBean);
        mSwipeRefresh.setRefreshing(false);
        adapter.notifyDataSetChanged();
    }

    //get article list
    @Override
    public void updateList(ItemBean storiesBeanList) {
        adapter.refreshItems(storiesBeanList);

    }

    //wanna set downrefresh function
    public void downRefresh() {
        mSwipeRefresh = (SwipeRefreshLayout) findViewById(R.id.refreshLayout);
        mSwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.getListData();
            }
        });

    }


    //deliver data,waiting for modify,maybe appear nullpointerexception
    @Override
    public void getItemData(ItemBean itemBean) {
        this.itemBean = itemBean;

    }

    //wanna implement upload function
    public void setUpLoad() {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
                visibleCount = layoutManager.getChildCount();
                totalCount = layoutManager.getItemCount();
                mLastVisibleItem = ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
                if (visibleCount > 0 && newState == RecyclerView.SCROLL_STATE_IDLE && !isLoadData && mLastVisibleItem >= totalCount - 1) {
                    presenter.getPrevious(itemBean.getDate());
                }
            }


        });
    }

    //notify data rrfresh when upload
    @Override
    public void notifyChange() {
        adapter.refreshPrevious(itemBean.getStories());
        adapter.notifyDataSetChanged();
        isLoadData = false;
    }


}
