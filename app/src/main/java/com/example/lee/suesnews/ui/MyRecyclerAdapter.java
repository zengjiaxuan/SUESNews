package com.example.lee.suesnews.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lee.suesnews.R;
import com.example.lee.suesnews.bean.NewsItem;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * Created by Administrator on 2015/2/5.
 */

public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.ViewHolder>{

    //当前显示的数据
    private List<NewsItem> mNewsList = new ArrayList<NewsItem>();

    public void setmDrawble(Drawable mDrawble) {
        if (this.mDrawble == null) {
            this.mDrawble = mDrawble;
        }
    }

    private Drawable mDrawble;
//    private Context mContext;

    public List<NewsItem> getmNewsList() {
        return mNewsList;
    }

    public MyRecyclerAdapter(Context context){
        this(context,null);
    }


    public MyRecyclerAdapter(Context context, List<NewsItem> myDataset){

        mNewsList = myDataset != null ? myDataset : new ArrayList<NewsItem>();
        Random random = new Random();
        int i = random.nextInt(4);
        setmDrawble(context.getResources().getDrawable(getImageId(i)));
    }

    /**
     * 添加新闻列表
     * @param news 要添加的新闻列表
     */
    public void addNews(List<NewsItem> news){
        mNewsList.addAll(news);
        Log.i("LIXU", "adapter" + mNewsList.size());
    }

    private int getImageId(int id){
        int num = id % 4;
        switch (num){
            case 0:
                return R.drawable.materialdesign_pic_1;
            case 1:
                return R.drawable.materialdesign_pic_2;
            case 2:
                return R.drawable.materialdesign_pic_3;
            case 3:
                return R.drawable.materialdesign_pic_4;
        }
        return R.drawable.materialdesign_pic_1;
    }

    /**
     * 创建ViewHolder
     * @param viewGroup 父View
     * @param i 位置
     * @return 返回得到的ViewHolder
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_recyclerview,
                viewGroup,false);
        TextView titleTextView = (TextView) v.findViewById(R.id.titleTextView);
        TextView dateTextView = (TextView) v.findViewById(R.id.dateTextView);
        ImageView titleImageView = (ImageView) v.findViewById(R.id.titleImageView);
        titleImageView.setImageDrawable(mDrawble);
        return new ViewHolder(v,titleTextView,dateTextView,titleImageView);
    }

    /**
     * 将数据绑定到ViewHolder
     * @param viewHolder    要绑定的ViewHolder
     * @param i             ViewHolder的位置
     */
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.mTitleTextView.setText(mNewsList.get(i).getTitle());
        //如果日期为空，则尝试使用新闻来源
        viewHolder.mDateTextView.setText(mNewsList.get(i).getDate() == null ?
                mNewsList.get(i).getSource():mNewsList.get(i).getDate());
        viewHolder.bindData(mNewsList.get(i));
    }

    @Override
    public int getItemCount() {
        if (mNewsList != null) {
            return mNewsList.size();
        }
        return 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private View mView;
        private TextView mTitleTextView;
        private TextView mDateTextView;
        private ImageView mTitleImageView;

        private NewsItem mNewsItem;

        public ViewHolder(View v){
            super(v);
        }

        public ViewHolder(View v,TextView titleTextView,TextView dateTextView,ImageView imageView){
            this(v);
            v.setOnClickListener(this);
            mTitleTextView = titleTextView;
            mDateTextView = dateTextView;
            mTitleImageView = imageView;
            mView = v;
        }


        /**
         * 将新闻列表绑定至ViewHolder
         * @param newsItem     新闻列表
         */
        public void bindData(NewsItem newsItem){
            mTitleTextView.setText(newsItem.getTitle());
            //如果日期为空，则尝试使用新闻来源
            mDateTextView.setText(newsItem.getDate() == null ?
                    newsItem.getSource():newsItem.getDate());
            //图片随机分配
//            mTitleImageView.setImageDrawable();
            mNewsItem = newsItem;
        }

        @Override
        public void onClick(View view) {
        }
    }
}