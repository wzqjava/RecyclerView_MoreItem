package com.wzq.recycler_countdown;

import android.content.Context;
import android.os.CountDownTimer;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * <p>作者：wzq<p>
 * <p>创建时间：2020/9/30<p>
 * <p>文件描述：<p>
 */
//适配器
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    //设置点击事件
    interface OnItemClickListener {
        public void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    private OnItemClickListener onItemClickListener;

    //设置接收条目点击事件方法

    private List<TimerItem> mDatas;
    //用于退出activity,避免countdown，造成资源浪费。
    private SparseArray<CountDownTimer> countDownMap;

    public MyAdapter(Context context, List<TimerItem> datas) {
        mDatas = datas;
        countDownMap = new SparseArray<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.tvTime.setText("邀请");

        //将前一个缓存清除
        if (holder.countDownTimer != null) {
            holder.countDownTimer.cancel();
        }
        holder.tvTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final TimerItem timerItem = mDatas.get(position);
                if (!"邀请".equals(holder.tvTime.getText())) { // 正在倒计时
                    return;
                }
                long time = System.currentTimeMillis() + 80 * 1000; // 过期时间
                time = time - System.currentTimeMillis();

                if (time > 0) {
                    holder.countDownTimer = new CountDownTimer(time, 1000) {
                        public void onTick(long millisUntilFinished) {
                            holder.tvTime.setText("邀请(" + millisUntilFinished + ")");
                            Log.e("TAG", timerItem.name + " :  " + millisUntilFinished);
                        }

                        public void onFinish() {
                            Log.e("wzq","onFinish");
                            holder.tvTime.setText("邀请");

                        }
                    }.start();

                    countDownMap.put(holder.tvTime.hashCode(), holder.countDownTimer);
                } else {
//                    holder.tvTime.setText("邀请");
                }
                onItemClickListener.onItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (mDatas != null && !mDatas.isEmpty()) {
            return mDatas.size();
        }
        return 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvStatus;
        public TextView tvTime;
        public CountDownTimer countDownTimer;

        public ViewHolder(View itemView) {
            super(itemView);
            tvStatus = (TextView) itemView.findViewById(R.id.tvStatus);
            tvTime = (TextView) itemView.findViewById(R.id.tvTime);
        }
    }


    /**
     * 清空资源
     */
    public void cancelAllTimers() {
        if (countDownMap == null) {
            return;
        }
        Log.e("TAG", "size :  " + countDownMap.size());
        for (int i = 0, length = countDownMap.size(); i < length; i++) {
            CountDownTimer cdt = countDownMap.get(countDownMap.keyAt(i));
            if (cdt != null) {
                cdt.cancel();
            }
        }
    }

}