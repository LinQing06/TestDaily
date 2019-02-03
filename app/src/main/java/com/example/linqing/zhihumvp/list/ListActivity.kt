package com.example.linqing.zhihumvp.list

import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.LinearLayout

import com.example.linqing.zhihumvp.R
import com.example.linqing.zhihumvp.entitybean.ItemBean
import java.util.Objects

class ListActivity : AppCompatActivity(), ListContract.ListView {
    private var adapter: MyAdapter? = null
    private var mSwipeRefresh: SwipeRefreshLayout? = null
    private var recyclerView: RecyclerView? = null
    private val linearLayout: LinearLayout? = null
    private var itemBean: ItemBean? = null
    private var totalCount: Int = 0
    private var mLastVisibleItem: Int = 0
    private var visibleCount: Int = 0
    private var isLoadData = false
    private val presenter = ListPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initId()
        initRecyclerView()
        presenter.getListData()

    }

    private fun initId() {

        recyclerView = findViewById(R.id.recycler)

    }

    private fun initRecyclerView() {
        val linearLayout = LinearLayoutManager(this)
        linearLayout.orientation = LinearLayoutManager.VERTICAL
        adapter = MyAdapter(this)
        recyclerView!!.layoutManager = linearLayout
        recyclerView!!.adapter = adapter

    }

    //init
    override fun refreshList() {
        downRefresh()
        setUpLoad()
        adapter!!.getData(itemBean)
        mSwipeRefresh!!.isRefreshing = false
        adapter!!.notifyDataSetChanged()
    }

    //get article list
    override fun updateList(storiesBeanList: ItemBean) {
        adapter!!.refreshItems(storiesBeanList)

    }

    //wanna set downrefresh function
    fun downRefresh() {
        mSwipeRefresh = findViewById<View>(R.id.refreshLayout) as SwipeRefreshLayout?
        mSwipeRefresh!!.setOnRefreshListener { presenter.getListData() }

    }


    //deliver data,waiting for modify,maybe appear nullpointerexception
    override fun getItemData(itemBean: ItemBean) {
        this.itemBean = itemBean

    }

    //wanna implement upload function
    fun setUpLoad() {
        recyclerView!!.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                val layoutManager = recyclerView.layoutManager
                visibleCount = layoutManager!!.childCount
                totalCount = layoutManager.itemCount
                mLastVisibleItem = (layoutManager as LinearLayoutManager).findLastVisibleItemPosition()
                if (visibleCount > 0 && newState == RecyclerView.SCROLL_STATE_IDLE && !isLoadData && mLastVisibleItem >= totalCount - 1) {
                    presenter.getPrevious(itemBean!!.date)
                }
            }


        })
    }

    //notify data rrfresh when upload
    override fun notifyChange() {
        adapter!!.refreshPrevious(itemBean!!.stories)
        adapter!!.notifyDataSetChanged()
        isLoadData = false
    }


}
