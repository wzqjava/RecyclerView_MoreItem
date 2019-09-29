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
public class HomeMutilpartAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    ArrayList<HomeBean.ResultBean> beanList;

    public HomeMutilpartAdapter(Context context, ArrayList<HomeBean.ResultBean> beanList) {
        this.context = context;
        this.beanList = beanList;
    }

    @Override
    //条目类型
    public int getItemViewType(int position) {
        return (position % 2);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //1、需要ViewHolder，传入视图布局
        View rootView = null;
        //获取ViewHolder
        RecyclerView.ViewHolder holder = null;
        int itemViewType = getItemViewType(i);
        switch (itemViewType) {
            case 0:
                rootView = View.inflate(context, R.layout.item_home, null);
                holder = new HomeViewHolder(rootView);
                break;
            case 1:
                rootView = View.inflate(context, R.layout.item_home_another, null);
                holder = new AnotherViewHolder(rootView);
                break;
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        if (viewHolder instanceof HomeViewHolder) {
            HomeViewHolder homeViewHolder = (HomeViewHolder) viewHolder;
            //3、绑定ViewHolder内容中设置具体
            Glide.with(context).load(beanList.get(position).getImageUrl()).into(homeViewHolder.iv_homeIcon);
            homeViewHolder.tv_homeInfo.setText(beanList.get(position).getJumpUrl());
        } else if (viewHolder instanceof AnotherViewHolder) {
            AnotherViewHolder anotherViewHolder = (AnotherViewHolder) viewHolder;
            //3、绑定ViewHolder内容中设置具体
            Glide.with(context).load(beanList.get(position).getImageUrl()).into(anotherViewHolder.iv_homeAnotherIcon);
            anotherViewHolder.tv_homeAnotherInfo.setText(beanList.get(position).getJumpUrl());
        }
    }


    @Override
    public int getItemCount() {
        return beanList.size();
    }

    class HomeViewHolder extends RecyclerView.ViewHolder {
        public ImageView iv_homeIcon;
        public TextView tv_homeInfo;

        public HomeViewHolder(@NonNull View itemView) {
            //2、在适配器内部找条目
            super(itemView);
            iv_homeIcon = itemView.findViewById(R.id.iv_homeIcon);
            tv_homeInfo = itemView.findViewById(R.id.tv_homeInfo);
        }
    }

    class AnotherViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_homeAnotherInfo;
        public ImageView iv_homeAnotherIcon;

        public AnotherViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_homeAnotherInfo = itemView.findViewById(R.id.tv_homeAnotherInfo);
            iv_homeAnotherIcon = itemView.findViewById(R.id.iv_homeAnotherIcon);
        }
    }


}
