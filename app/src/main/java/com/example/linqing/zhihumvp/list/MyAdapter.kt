package com.example.linqing.zhihumvp.list

import android.content.Context
import android.content.Intent
import android.support.v4.view.ViewPager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.linqing.zhihumvp.R
import com.example.linqing.zhihumvp.detail.DetailActivity
import com.example.linqing.zhihumvp.entitybean.ItemBean
import java.util.ArrayList

class MyAdapter(internal var mContext: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    // 要在Item上显示的数据
    private var mDataSet: MutableList<ItemBean.StoriesBean>? = ArrayList()
    private var mTopStories: MutableList<ItemBean.TopStoriesBean>? = ArrayList()
    private var itemBean: ItemBean? = null
    private val TYPE_TOP = 0
    private val TYPE_ARTICLE = 1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View
        if (viewType == 0) {
            view = LayoutInflater.from(mContext).inflate(R.layout.top, parent, false)

            return viewPagerHolder(view)
        } else {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_layout, parent, false)
            return MyViewHolder(view)

        }


    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        if (holder is viewPagerHolder) {
            val viewPagerAdapter = ViewPagerAdapter(mContext, mTopStories!!, mTopStories!!.size)
            holder.viewPager.adapter = viewPagerAdapter
            holder.viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
                override fun onPageScrolled(i: Int, v: Float, i1: Int) {

                }

                override fun onPageSelected(i: Int) {

                }

                override fun onPageScrollStateChanged(i: Int) {

                }
            })

        } else if (holder is MyViewHolder) {
//final StoriesBean storyBean =;
            holder.textView.text = mDataSet!![position].title
            Glide.with(mContext)
                    .load(mDataSet!![position].images!![0])
                    .into(holder.imageView)
            holder.itemView.setOnClickListener {
                val intent = Intent(mContext, DetailActivity::class.java)
                intent.putExtra("id", mDataSet!![holder.adapterPosition].id.toString())
                mContext.startActivity(intent)
            }

        }

    }


    override fun getItemViewType(position: Int): Int {
        return if (position == 0) {
            TYPE_TOP
        } else TYPE_ARTICLE
    }


    // 获取Item的数量
    override fun getItemCount(): Int {
        return mDataSet!!.size
    }


    fun refreshItems(itemData: ItemBean) {

        //mDataSet.addAll(itemData);
        this.itemBean = itemData
        mDataSet = itemBean!!.stories as MutableList<ItemBean.StoriesBean>?
        mTopStories = itemBean!!.top_stories as MutableList<ItemBean.TopStoriesBean>?

        notifyDataSetChanged()
    }

    fun refreshPrevious(previousItem: List<ItemBean.StoriesBean>) {

        mDataSet!!.addAll(previousItem)
        notifyDataSetChanged()
    }


    fun getData(data: ItemBean) {
        this.itemBean = data

    }


    fun deleteItem(position: Int) {
        mDataSet!!.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(0, mDataSet!!.size - 1)
    }

    // ViewHolder用于获取Item上的控件
    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var textView: TextView
        internal var imageView: ImageView

        init {
            textView = itemView.findViewById<View>(R.id.item_text) as TextView
            imageView = itemView.findViewById<View>(R.id.item_image) as ImageView

        }
    }

    inner class viewPagerHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var viewPager: ViewPager

        init {
            viewPager = itemView.findViewById(R.id.viewpager_)
        }
    }

}
