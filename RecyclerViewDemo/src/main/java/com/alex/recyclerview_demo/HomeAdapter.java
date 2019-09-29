package com.alex.recyclerview_demo;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * @author Alex
 * @date 2019/6/14.
 * GitHub：https://github.com/wangshuaialex
 */
public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.HomeViewHolder> {
    Context context;
    ArrayList<HomeBean.ResultBean> beanList;

    public HomeAdapter(Context context, ArrayList<HomeBean.ResultBean> beanList) {
        this.context = context;
        this.beanList = beanList;
    }

    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //1、需要ViewHolder，传入视图布局
        View rootView = View.inflate(context, R.layout.item_home, null);
        HomeViewHolder holder = new HomeViewHolder(rootView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HomeViewHolder homeViewHolder, int position) {
        //包含RecyclererView宽高属性的内容：RecyclerView.LayoutParams
        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.MATCH_PARENT);
        //手动赋值进行设置
        layoutParams.height = (int) (100 * Math.random());
        layoutParams.width = (int) (100 * Math.random());

        //3、绑定ViewHolder内容中设置具体
        Glide.with(context).load(beanList.get(position).getImageUrl()).into(homeViewHolder.iv_homeIcon);
        homeViewHolder.tv_homeInfo.setText(beanList.get(position).getJumpUrl());
    }

    @Override
    public int getItemCount() {
        return beanList.size();
    }

    class HomeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageView iv_homeIcon;
        public TextView tv_homeInfo;

        public HomeViewHolder(@NonNull View itemView) {
            //2、在适配器内部找条目
            super(itemView);
            iv_homeIcon = itemView.findViewById(R.id.iv_homeIcon);
            tv_homeInfo = itemView.findViewById(R.id.tv_homeInfo);
            tv_homeInfo.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            //条目点击的内容方法
            onItemClickListener.onItemClick(tv_homeInfo.getText().toString(),getLayoutPosition());
        }
    }


    //设置点击事件
    public interface OnItemClickListener {
        public void onItemClick(String itemInfo, int layoutPosition);
    }


    //设置接收条目点击事件方法
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    OnItemClickListener onItemClickListener;
}
