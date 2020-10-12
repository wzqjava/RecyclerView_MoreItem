package com.wzq.recycler_countdown

import android.app.KeyguardManager
import android.content.Context
import android.nfc.Tag
import android.os.CountDownTimer
import android.util.Log
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


//适配器
class MyAdapter(
        context: Context?, //设置接收条目点击事件方法
        private val mDatas: List<TimerItem>
) : RecyclerView.Adapter<MyAdapter.ViewHolder>() {
    //用于退出activity,避免countdown，造成资源浪费。
    private val countDownMap: SparseArray<CountDownTimer>
    val tag = "MyAdapter"

    init {
        countDownMap = SparseArray()
    }

    //设置点击事件
    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener?) {
        this.onItemClickListener = onItemClickListener
    }

    private var onItemClickListener: OnItemClickListener? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        Log.e(tag, "onCreateViewHolder")
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        val viewHolder = ViewHolder(view)
//        viewHolder.setIsRecyclable(false)
        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        holder.setIsRecyclable(false);
        Log.e(tag, "onBindViewHolder:$holder--position:$position")
        val userBasicInfo = mDatas[position]
        if(userBasicInfo.time==null){
            holder.tvInvite.text = "邀请"
//                holder.tvInvite.setBackgroundResource(R.drawable.challenge_shape_invite)
            holder.tvInvite.isClickable = true
            holder.tvInvite.isEnabled = true
        }else{
            holder.tvInvite.text = "邀请${userBasicInfo?.time}"
            holder.tvInvite.isClickable = false
            holder.tvInvite.isEnabled = false
        }
//        holder.tvInvite.text = "邀请"



//        val countDownTimer = countDownMap.get(userBasicInfo.userId.hashCode())
//        val inviteTime = 20000L  // 30秒


//        val timeStamp: Long = System.currentTimeMillis() - userBasicInfo.expirationTime // 已经倒计时了多少秒
        /*if (countDownTimer != null) { // 倒计时结束,再次点击
            Log.e(tag, "countDownTimer不为null")
            //将前一个缓存清除
            countDownTimer.cancel()
            val time: Long = inviteTime - timeStamp // 还需要倒计时的时间
            Log.e("wzq", "countDownTimer不为null,剩余time${time}")
            countDownTimer.onTick(time)
            countDownTimer.start()
        } else {
            Log.e(tag, "countDownTimer是null")
        }*/
        userBasicInfo.startListener={
            notifyDataSetChanged()
        }
        userBasicInfo.finishListener={
            notifyDataSetChanged()
        }

        holder.tvInvite.setOnClickListener(View.OnClickListener {
            Log.d("setOnClickListener","position:$position")
            onItemClickListener!!.onItemClick(position)
            userBasicInfo.start()
//            if (countDownTimer != null) { // 点击或再次点击
//                // 将前一个缓存清除
//                countDownTimer?.cancel()
//                val time: Long = inviteTime - timeStamp // 还需要倒计时的时间
//                countDownTimer?.onTick(time)
//                countDownTimer?.start()
//            } else {
//                val countDownTimer = object : CountDownTimer(inviteTime, 1000) {
//                    override fun onTick(millisUntilFinished: Long) {
//                        val tempSecond = (millisUntilFinished / 1000).toInt().toString()
//                        holder.tvInvite.text = "邀请($tempSecond)"
//                        holder.tvInvite.isClickable = false
//                        holder.tvInvite.isEnabled = false
//                    }
//
//                    override fun onFinish() {
//                        Log.e("wzq", "onFinish")
//                        holder.tvInvite.text = "邀请"
//                        holder.tvInvite.isClickable = true
//                        holder.tvInvite.isEnabled = true
//                    }
//                }.start()
//                countDownMap.put(userBasicInfo.userId.hashCode(), countDownTimer)
//            }

        })
    }

    override fun getItemCount(): Int {
        return if (mDatas != null && !mDatas.isEmpty()) {
            mDatas.size
        } else 0
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvStatus: TextView
        var tvInvite: TextView
//        var countDownTimer: CountDownTimer? = null

        init {
            tvStatus = itemView.findViewById<View>(R.id.tvStatus) as TextView
            tvInvite = itemView.findViewById<View>(R.id.tvTime) as TextView
        }
    }

    /**
     * 清空资源
     */
    fun cancelAllTimers() {
        if (countDownMap == null) {
            return
        }
        Log.e("TAG", "size :  " + countDownMap.size())
        var i = 0
        val length = countDownMap.size()
        while (i < length) {
            val cdt = countDownMap[countDownMap.keyAt(i)]
            cdt?.cancel()
            i++
        }
    }


}