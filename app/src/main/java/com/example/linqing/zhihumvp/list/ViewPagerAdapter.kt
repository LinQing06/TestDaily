package com.example.linqing.zhihumvp.list

import android.content.Context
import android.content.Intent
import android.support.constraint.ConstraintLayout
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import com.bumptech.glide.Glide
import com.example.linqing.zhihumvp.R
import com.example.linqing.zhihumvp.detail.DetailActivity
import com.example.linqing.zhihumvp.entitybean.ItemBean
import com.squareup.picasso.Picasso


class ViewPagerAdapter internal constructor(private val context: Context, private val imageViews: MutableList<ItemBean.TopStoriesBean>, private val size: Int) : PagerAdapter() {

    fun add(imageViews: List<ItemBean.TopStoriesBean>) {
        this.imageViews.addAll(imageViews)
    }

    override fun getCount(): Int {
        return imageViews.size
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {

        val view = LayoutInflater.from(context).inflate(R.layout.itembanner, container, false)

        val imageView = view.findViewById<ImageView>(R.id.topimage)
        val textView = view.findViewById<TextView>(R.id.toptext)
        val bean = imageViews[position]
        textView.text = imageViews[position].title
        val viewHolder = ViewHolder(view)
        Picasso.with(context).load(bean.image)
                .fit()
                .centerCrop()
                .into(viewHolder.imageView)
        val id = imageViews[position].id
        view.setOnClickListener {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("id", bean.id)
            context.startActivity(intent)
        }
        container.addView(view)

        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun isViewFromObject(arg0: View, arg1: Any): Boolean {
        return arg0 === arg1
    }

    //maybe emmm
    fun clear() {
        imageViews.clear()
    }

    internal class ViewHolder(view: View) {
        var layout: ConstraintLayout
        var imageView: ImageView
        var textView: TextView

        init {
            layout = view.findViewById(R.id.topbanner)
            imageView = view.findViewById(R.id.topimage)
            textView = view.findViewById(R.id.toptext)
        }
    }

}
