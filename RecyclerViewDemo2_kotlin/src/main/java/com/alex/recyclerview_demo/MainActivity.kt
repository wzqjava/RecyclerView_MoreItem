package com.alex.recyclerview_demo

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {


    private var context: Context? = null

    private var beanList: ArrayList<HomeBean.ResultBean>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        context = this@MainActivity
        initData()

    }

    private fun initData() {
        val manager: RecyclerView.LayoutManager
        //解析数据
        val gson = Gson()
//        val homeBean = gson.fromJson<HomeBean>(Constant.jsonString, HomeBean::class.java!!)
        val homeBean = gson.fromJson<HomeBean>(Constant.jsonString, HomeBean::class.java)
        //数据源
        beanList = homeBean.result as ArrayList<HomeBean.ResultBean>
        //2、设置布局管理器
        //线性条目的布局管理
        manager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        //多条目布局管理器
        //manager = new GridLayoutManager(context, 2);
        //瀑布流布局管理器
        //  manager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        //***必须将布局管理器设置RecyclerView
        recyclerView!!.layoutManager = manager

//        setAdapter1()  // 自己写的常规adapter
            setAdapter2();
        //    setAdapter3();
        //    setAdapter4();

    }

    private fun setAdapter1() {
        //3、设置适配器
        val homeAdapter = HomeAdapter(context, beanList)
        recyclerView!!.adapter = homeAdapter
        //设置点击事件
        homeAdapter.setOnItemClickListener { itemInfo, layoutPosition -> Toast.makeText(context, itemInfo + "第" + layoutPosition + "个条目被点击了", Toast.LENGTH_SHORT).show() }
    }

    private fun setAdapter2() {
        //设置快速适配器
        val homeDetailAdapter = HomeDetailAdapter(R.layout.item_home, beanList)
        recyclerView!!.adapter = homeDetailAdapter
        homeDetailAdapter.onItemClickListener = BaseQuickAdapter.OnItemClickListener { adapter, view, position -> Toast.makeText(context, beanList!![position].title + "第" + position + "个条目被点击了", Toast.LENGTH_SHORT).show() }
    }

    private fun setAdapter3() {
        //多条目适配器
        val homeMutilpartAdapter = HomeMutilpartAdapter(context, beanList)
        recyclerView!!.adapter = homeMutilpartAdapter
    }

    private fun setAdapter4() {
        //简化版本的多条目
        val homeQuickMutilpartAdapter = HomeQuickMutilpartAdapter(beanList)
        //通过RecyclerView设置动画效果
        homeQuickMutilpartAdapter.openLoadAnimation(HomeQuickMutilpartAdapter.ALPHAIN)
        recyclerView!!.adapter = homeQuickMutilpartAdapter
        homeQuickMutilpartAdapter.onItemClickListener = BaseQuickAdapter.OnItemClickListener { adapter, view, position -> Toast.makeText(context, "当前的位置是：$position", Toast.LENGTH_SHORT).show() }
    }
}
