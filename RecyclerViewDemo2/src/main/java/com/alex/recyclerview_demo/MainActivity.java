package com.alex.recyclerview_demo;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
  //1、查找设置ID
  @BindView(R.id.recycler)
  RecyclerView recyclerView;
  private Context context;

  private ArrayList<HomeBean.ResultBean> beanList;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);
    context = MainActivity.this;
    initData();

  }

  private void initData() {
    RecyclerView.LayoutManager manager;
    //解析数据
    Gson gson = new Gson();
    HomeBean homeBean = gson.fromJson(Constant.jsonString, HomeBean.class);
    //数据源
    beanList = (ArrayList<HomeBean.ResultBean>) homeBean.getResult();
    //2、设置布局管理器
    //线性条目的布局管理
    manager = new LinearLayoutManager(context, RecyclerView.VERTICAL, false);
    //多条目布局管理器
    //manager = new GridLayoutManager(context, 2);
    //瀑布流布局管理器
    //  manager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
    //***必须将布局管理器设置RecyclerView
    recyclerView.setLayoutManager(manager);

        setAdapter1();  // 自己写的常规adapter
//    setAdapter2();
//    setAdapter3();
//    setAdapter4();

  }

  private void setAdapter1() {
    //3、设置适配器
    HomeAdapter homeAdapter = new HomeAdapter(context, beanList);
    recyclerView.setAdapter(homeAdapter);
    //设置点击事件
    homeAdapter.setOnItemClickListener(new HomeAdapter.OnItemClickListener() {
      @Override
      public void onItemClick(String itemInfo, int layoutPosition) {
        Toast.makeText(context, itemInfo + "第" + layoutPosition + "个条目被点击了", Toast.LENGTH_SHORT).show();
      }
    });
  }

  private void setAdapter2() {
    //设置快速适配器
    HomeDetailAdapter homeDetailAdapter = new HomeDetailAdapter(R.layout.item_home, beanList);
    recyclerView.setAdapter(homeDetailAdapter);
    homeDetailAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
      @Override
      public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        Toast.makeText(context, beanList.get(position).getTitle() + "第" + position + "个条目被点击了", Toast.LENGTH_SHORT).show();
      }
    });
  }

  private void setAdapter3() {
    //多条目适配器
    HomeMutilpartAdapter homeMutilpartAdapter = new HomeMutilpartAdapter(context, beanList);
    recyclerView.setAdapter(homeMutilpartAdapter);
  }

  private void setAdapter4() {
    //简化版本的多条目
    HomeQuickMutilpartAdapter homeQuickMutilpartAdapter = new HomeQuickMutilpartAdapter(beanList);
    //通过RecyclerView设置动画效果
    homeQuickMutilpartAdapter.openLoadAnimation(HomeQuickMutilpartAdapter.ALPHAIN);
    recyclerView.setAdapter(homeQuickMutilpartAdapter);
    homeQuickMutilpartAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
      @Override
      public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        Toast.makeText(context, "当前的位置是：" + position, Toast.LENGTH_SHORT).show();
      }
    });
  }
}
