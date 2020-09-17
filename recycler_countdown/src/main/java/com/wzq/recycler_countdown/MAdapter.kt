package com.wzq.recycler_countdown

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView


class MAdapter(context: Context, private val list: List<UserBasicInfo>) : RecyclerView.Adapter<MAdapter.MViewHolder>() {
    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var onItemClickListener: Main2Activity.OnItemClickListener? = null

    //设置接收条目点击事件方法
    fun setOnItemClickListener(onItemClickListener: Main2Activity.OnItemClickListener?) {
        this.onItemClickListener = onItemClickListener
    }

    inner class MViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvTime: TextView = itemView.findViewById(R.id.tvTime)

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MViewHolder {
        val view = inflater.inflate(R.layout.list_item, null)
        val holder = MViewHolder(view)
        return holder
    }

    override fun onBindViewHolder(holder: MViewHolder, position: Int) {
        var userBasicInfo = list[position]
        // TODO: 2020/9/17 需要判断状态
        if (userBasicInfo.user_status == 0) {
            holder.tvTime.visibility = View.VISIBLE
            holder.tvTime.text = "邀请"
        } else {
            holder.tvTime.visibility = View.GONE
            /*  if (userBasicInfo.time == 30) {
                  holder.tvTime!!.text = "邀请"
                  holder.tvTime!!.visibility = View.VISIBLE
              } else if (userBasicInfo.time <= 0) {
                  holder.tvTime!!.text = "已邀请(30s)"
              } else {
                  holder.tvTime!!.text = "已邀请(${userBasicInfo.time} s)"
              }
              holder.tvTime!!.text = userBasicInfo.time.toString()*/
        }
        holder.tvTime.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                Toast.makeText(p0?.context, "点击了$position", Toast.LENGTH_SHORT).show()
                onItemClickListener?.onItemClick(position)
            }
        })


    }

    override fun getItemCount(): Int {
        return list.size
    }

}