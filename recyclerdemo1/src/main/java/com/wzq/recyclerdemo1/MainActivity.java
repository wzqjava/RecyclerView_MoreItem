package com.wzq.recyclerdemo1;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

  private int[] icons = {R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher};
  private RecyclerView mRecy;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    initView();
    initData();
  }

  private void initView() {
    mRecy = findViewById(R.id.recycler);
  }

  private void initData() {
    ArrayList<MoreTypeBean> mData = new ArrayList<>();
//        随机数 用来标记item界面的类型
    Random random = new Random();
    for (int i = 0; i < icons.length; i++) {
      MoreTypeBean moreTypeBean = new MoreTypeBean();
      moreTypeBean.pic = icons[i];
      moreTypeBean.type = random.nextInt(3);// 012,数据源决定显示那种类型
      mData.add(moreTypeBean);
    }
    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
    mRecy.setLayoutManager(linearLayoutManager);
    MAdapter mAdapter = new MAdapter(mData);
    mRecy.setAdapter(mAdapter);
  }
}

