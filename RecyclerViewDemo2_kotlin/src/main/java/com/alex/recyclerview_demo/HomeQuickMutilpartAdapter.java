package com.alex.recyclerview_demo;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * @author Alex
 * @date 2019/6/14.
 * GitHub：https://github.com/wangshuaialex
 */
public class HomeQuickMutilpartAdapter extends BaseMultiItemQuickAdapter<HomeBean.ResultBean, BaseViewHolder> {
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public HomeQuickMutilpartAdapter(List<HomeBean.ResultBean> data) {
        super(data);
        addItemType(HomeBean.ResultBean.LEFT_IMG, R.layout.item_home);
        addItemType(HomeBean.ResultBean.RIGHT_IMG, R.layout.item_home_another);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeBean.ResultBean item) {
        switch (helper.getItemViewType()) {
            case HomeBean.ResultBean.LEFT_IMG:
                //第一种样式的处理
                ImageView iv_homeIcon = helper.getView(R.id.iv_homeIcon);
                Glide.with(mContext).load(item.getImageUrl()).into(iv_homeIcon);
                helper.setText(R.id.tv_homeInfo, item.getTitle());
                break;
            case HomeBean.ResultBean.RIGHT_IMG:
                //第二种样式的处理
                ImageView iv_homeAnotherIcon = helper.getView(R.id.iv_homeAnotherIcon);
                Glide.with(mContext).load(item.getImageUrl()).into(iv_homeAnotherIcon);
                helper.setText(R.id.tv_homeAnotherInfo, item.getTitle());
                break;
        }
    }
}
