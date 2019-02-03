package com.example.linqing.zhihumvp.list;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.example.linqing.zhihumvp.R;
import com.example.linqing.zhihumvp.detail.DetailActivity;
import com.example.linqing.zhihumvp.entitybean.ItemBean;
import java.util.ArrayList;
import java.util.List;
public class MyAdapter extends RecyclerView.Adapter {
    // 要在Item上显示的数据
    private List<ItemBean.StoriesBean> mDataSet = new ArrayList<ItemBean.StoriesBean>();
    private List<ItemBean.TopStoriesBean> mTopStories = new ArrayList<>();
    Context mContext;
    private ItemBean itemBean;
    private final int TYPE_TOP = 0;
    private final int TYPE_ARTICLE = 1;

    public MyAdapter(Context context) {
        mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if(viewType==0){
            view = LayoutInflater.from(mContext).inflate(R.layout.top, parent, false);

            return new viewPagerHolder(view);
        }
        else {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_layout, parent, false);
            return new MyViewHolder(view);

        }


    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if(holder instanceof viewPagerHolder){
            final viewPagerHolder viewPagerHolderimpl=(viewPagerHolder)holder;
            ViewPagerAdapter viewPagerAdapter=new ViewPagerAdapter(mContext,mTopStories,mTopStories.size());
            viewPagerHolderimpl.viewPager.setAdapter(viewPagerAdapter);
            viewPagerHolderimpl.viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int i, float v, int i1) {

                }

                @Override
                public void onPageSelected(int i) {

                }

                @Override
                public void onPageScrollStateChanged(int i) {

                }
            });

        }
        else if(holder instanceof MyViewHolder){
            final MyViewHolder mHolder = (MyViewHolder) holder;
            //final StoriesBean storyBean =;
            mHolder.textView.setText(mDataSet.get(position).getTitle());
            Glide.with(mContext)
                    .load(mDataSet.get(position).getImages().get(0))
                    .into(mHolder.imageView);
            mHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(mContext, DetailActivity.class);
                    intent.putExtra("id", String.valueOf(mDataSet.get(mHolder.getAdapterPosition()).getId()));
                    mContext.startActivity(intent);

                }

            });

        }

    }




    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_TOP;
        }
        return TYPE_ARTICLE;
    }



    // 获取Item的数量
    @Override
    public int getItemCount() {
        return mDataSet.size();
    }


    public void refreshItems(ItemBean itemData) {

        //mDataSet.addAll(itemData);
        this.itemBean = itemData;
        mDataSet = itemBean.getStories();
        mTopStories = itemBean.getTop_stories();

        notifyDataSetChanged();
    }

    public void refreshPrevious(List<ItemBean.StoriesBean> previousItem) {

        mDataSet.addAll(previousItem);
        notifyDataSetChanged();
    }


    public void getData(ItemBean data) {
        this.itemBean = data;

    }


    public void deleteItem(int position) {
        mDataSet.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(0, mDataSet.size() - 1);
    }

    // ViewHolder用于获取Item上的控件
    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView imageView;

        public MyViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.item_text);
            imageView = (ImageView) itemView.findViewById(R.id.item_image);

        }
    }

     public  class viewPagerHolder extends RecyclerView.ViewHolder {
        ViewPager viewPager;

        viewPagerHolder(@NonNull View itemView) {
            super(itemView);
            viewPager = itemView.findViewById(R.id.viewpager_);
        }
    }

}
