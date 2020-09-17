package com.wzq.recycler_countdown

import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*


class Main2Activity : Activity() {
    //设置点击事件
    interface OnItemClickListener {
        fun onItemClick( position: Int)
    }

    private lateinit var recycleView: RecyclerView
    private lateinit var adapter: MAdapter
    private lateinit var manager: LinearLayoutManager
    private val myList: MutableList<UserBasicInfo> = ArrayList()
    private val tempList: MutableList<UserBasicInfo> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initList()
        initView()
    }

    private fun initList() {
        for (i in 100..129) {
            myList.add(UserBasicInfo())
        }
    }

    private fun initView() {
        recycleView = findViewById<View>(R.id.recycle) as RecyclerView
        manager = LinearLayoutManager(this)
        manager.orientation = LinearLayoutManager.VERTICAL
        recycleView.layoutManager = manager
        adapter = MAdapter(this@Main2Activity ,myList)
        recycleView.adapter = adapter
        adapter.setOnItemClickListener(object :OnItemClickListener{
            override fun onItemClick(position: Int) {
                tempList.add(myList.get(position))
                Log.e("wzq-size:", tempList.size.toString())
            }

        })
        //设置点击事件
        MyThread().start()

    }

    internal inner class MyThread : Thread() {
        override fun run() {
            while (true) {
                try {
                    sleep(1000)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
                val firstVisible = manager.findFirstVisibleItemPosition()
                val lastVisible = manager.findLastVisibleItemPosition()
                for (i in myList.indices) {
                    if (myList.size > 0) {
                        if (myList[i].time > 0) {
                            myList[i].time = myList[i].time - 1
                        }

                        if (i >= firstVisible && i <= lastVisible) {
                            val vh = recycleView.findViewHolderForAdapterPosition(i) as MAdapter.MViewHolder

                            runOnUiThread {
                                val time = myList[i].time

                                if (time <= 0) {
                                    vh.tvTime!!.text = "已邀请(30s)"
                                    vh.tvTime!!.setTextColor(Color.GRAY)
                                } else {
                                    vh.tvTime!!.text = "已邀请($time s)"
//                                vh!!.tvTime!!.visibility = View.GONE
                                }
                            }
                        }
                    }
                }
            }
        }
    }


}