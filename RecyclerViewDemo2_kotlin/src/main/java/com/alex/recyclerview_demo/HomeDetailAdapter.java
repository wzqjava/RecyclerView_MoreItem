package com.alex.recyclerview_demo;


import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * @author Alex
 * @date 2019/6/14.
 * GitHub：https://github.com/wangshuaialex
 */

//泛型参数1：集合中的条目Bean对象
public class HomeDetailAdapter extends BaseQuickAdapter<HomeBean.ResultBean, BaseViewHolder> {

  public HomeDetailAdapter(int layoutResId, @Nullable List<HomeBean.ResultBean> data) {
    super(layoutResId, data);
  }

  @Override
  protected void convert(BaseViewHolder helper, HomeBean.ResultBean item) {
    helper.setText(R.id.tv_homeInfo, item.getTitle());//设置文字
    //找图片控件
    ImageView iv_homeIcon = helper.getView(R.id.iv_homeIcon);
    Glide.with(mContext).load(item.getImageUrl()).into(iv_homeIcon);
  }
}
