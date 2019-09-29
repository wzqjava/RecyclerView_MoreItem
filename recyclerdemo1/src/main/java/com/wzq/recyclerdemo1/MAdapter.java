package com.wzq.recyclerdemo1;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>作者：wzq<p>
 * <p>创建时间：2019-09-29<p>
 * <p>文件描述：<p>
 */
public class MAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

  //定义三种常量  表示三种条目类型
  private static final int TYPE_PULL_IMAGE = 0;
  private static final int TYPE_RIGHT_IMAGE = 1;
  private static final int TYPE_THREE_IMAGE = 2;
  private List<MoreTypeBean> mData;

  MAdapter(List<MoreTypeBean> mData) {
    if (mData == null) {
      this.mData = new ArrayList<>();
    } else {
      this.mData = mData;
    }
  }

  public void setData(List<MoreTypeBean> mData) {
    if (mData != null) {
      this.mData.addAll(mData);
      notifyDataSetChanged();
    }
  }


  @NonNull
  @Override
  public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//      return new MHolder(View.inflate(parent.getContext(), android.R.layout.activity_mData_item,null));
//    return new MHolder(LayoutInflater.from(activity).inflate(android.R.layout.activity_mData_item, parent, false));
    View itemView;  //  创建不同的 ViewHolder
    if (viewType == TYPE_PULL_IMAGE) { //根据viewtype来创建条目
      itemView = View.inflate(parent.getContext(), R.layout.item_pull_img, null);
      return new PullImageHolder(itemView);
    } else if (viewType == TYPE_RIGHT_IMAGE) {
      itemView = View.inflate(parent.getContext(), R.layout.item_right_img, null);
      return new RightImageHolder(itemView);
    } else {
      itemView = View.inflate(parent.getContext(), R.layout.item_three_img, null);
      return new ThreeImageHolder(itemView);
    }

  }

  @Override
  public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
    MoreTypeBean moreTypeBean = mData.get(position);

    switch (moreTypeBean.type) {  //  比if性能好;
      case TYPE_PULL_IMAGE:
        PullImageHolder pullImageHolder = (PullImageHolder) holder;
        pullImageHolder.tv_title.setText("多条目-图居中");
        break;
      case TYPE_RIGHT_IMAGE:
        RightImageHolder rightImageHolder = (RightImageHolder) holder;
        rightImageHolder.tv_title.setText("多条目-图在右边");
        break;
      case TYPE_THREE_IMAGE:
        ThreeImageHolder threeImageHolder = (ThreeImageHolder) holder;
        threeImageHolder.tv_title.setText("多条目-我是三张图");
        break;

    }
  }

  @Override
  public int getItemCount() {
    if (mData != null) {
      return mData.size();
    }
    return 0;
  }

  @Override
  public int getItemViewType(int position) {
    MoreTypeBean moreTypeBean = mData.get(position);
    if (moreTypeBean.type == 0) {
      return TYPE_PULL_IMAGE;
    } else if (moreTypeBean.type == 1) {
      return TYPE_RIGHT_IMAGE;
    } else {
      return TYPE_THREE_IMAGE;
    }

  }


  /**
   * 创建三种ViewHolder
   */
  class PullImageHolder extends RecyclerView.ViewHolder {
    View itemView;
    TextView tv_title;

    public PullImageHolder(View itemView) {
      super(itemView);
      this.itemView = itemView;
      tv_title = itemView.findViewById(R.id.tv_title);
    }
  }

  class RightImageHolder extends RecyclerView.ViewHolder {
    View itemView;
    TextView tv_title;

    public RightImageHolder(View itemView) {
      super(itemView);
      this.itemView = itemView;
      tv_title = itemView.findViewById(R.id.tv_title);
    }
  }

  class ThreeImageHolder extends RecyclerView.ViewHolder {
    View itemView;
    TextView tv_title;

    public ThreeImageHolder(View itemView) {
      super(itemView);
      this.itemView = itemView;
      tv_title = itemView.findViewById(R.id.tv_title);
    }
  }
}




