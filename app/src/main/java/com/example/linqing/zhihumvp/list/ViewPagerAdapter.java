package com.example.linqing.zhihumvp.list;

import android.content.Context;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.linqing.zhihumvp.R;
import com.example.linqing.zhihumvp.detail.DetailActivity;
import com.example.linqing.zhihumvp.entitybean.ItemBean;
import com.squareup.picasso.Picasso;

import java.util.List;




public class ViewPagerAdapter extends PagerAdapter {

    private List<ItemBean.TopStoriesBean> imageViews;
    private Context context;
    private int size;
    ViewPagerAdapter(Context context,List<ItemBean.TopStoriesBean> topStoriesBeans,int size){
        this.context=context;
        this.imageViews=topStoriesBeans;
        this.size=size;

    }

    public  void add(List<ItemBean.TopStoriesBean> imageViews) {
        this.imageViews.addAll(imageViews);
    }

    @Override
    public int getCount() {
        return imageViews.size();
    }

    @Override
    public Object instantiateItem(final ViewGroup container, final int position) {

        View view = LayoutInflater.from(context).inflate(R.layout.itembanner, container, false);

        ImageView imageView=view.findViewById(R.id.topimage);
        TextView textView=view.findViewById(R.id.toptext);
        final ItemBean.TopStoriesBean bean = imageViews.get(position);
        textView.setText(imageViews.get(position).getTitle());
        ViewHolder viewHolder = new ViewHolder(view);
        Picasso.with(context).load(bean.getImage())
                .fit()
                .centerCrop()
                .into(viewHolder.imageView);
        final int id=imageViews.get(position).getId();
        view.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("id", bean.getId());
                context.startActivity(intent);
            }

        });
        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }
    //maybe emmm
    public void clear(){
        imageViews.clear();
    }

    static class ViewHolder {
        ConstraintLayout layout;
        ImageView imageView;
        TextView textView;

        public ViewHolder(View view) {
            layout = view.findViewById(R.id.topbanner);
            imageView = view.findViewById(R.id.topimage);
            textView = view.findViewById(R.id.toptext);
        }
    }

}
