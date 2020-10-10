package com.wzq.recycler_countdown

import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*


class Main2Activity : Activity() {


    private lateinit var recycleFriends: RecyclerView
    private lateinit var friendsAdapter: FriendsAdapter
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
        recycleFriends = findViewById<View>(R.id.recycleFriends) as RecyclerView
        manager = LinearLayoutManager(this)
        manager.orientation = LinearLayoutManager.VERTICAL
        recycleFriends.layoutManager = manager
        friendsAdapter = FriendsAdapter(this@Main2Activity, myList)
        friendsAdapter.setOnItemClickListener(object : FriendsAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                tempList.add(myList.get(position))
                Toast.makeText(this@Main2Activity, "点击了$position", Toast.LENGTH_SHORT).show()
                Log.e("wzq-size:", tempList.size.toString())
            }
        })
        recycleFriends.adapter = friendsAdapter
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

                myList.forEachIndexed { i, userBasicInfo ->
                    if (tempList.contains(userBasicInfo)) {
                        updateTime(i, firstVisible, lastVisible)
                    }
                }
            }
        }

        private fun updateTime(i: Int, firstVisible: Int, lastVisible: Int) {
            if (myList[i].time > 0) {
                myList[i].time = myList[i].time - 1
            }

            if (i >= firstVisible && i <= lastVisible) {
                val vh = recycleFriends.findViewHolderForAdapterPosition(i) as FriendsAdapter.MViewHolder

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