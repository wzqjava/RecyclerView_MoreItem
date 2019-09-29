package com.alex.recyclerview_demo;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    //1、查找设置ID
    @BindView(R.id.rv_container)
    RecyclerView rvContainer;
    private Context context;
    private RecyclerView.LayoutManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        context = MainActivity.this;


        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Constant.INFO_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //解析数据
                Gson gson = new Gson();
                HomeBean homeBean = gson.fromJson(response, HomeBean.class);
                //数据源
                final ArrayList<HomeBean.ResultBean> beanList = (ArrayList<HomeBean.ResultBean>) homeBean.getResult();

                //2、设置布局管理器
                //线性条目的布局管理
                manager = new LinearLayoutManager(context, RecyclerView.VERTICAL, false);
                //多条目布局管理器
                //manager = new GridLayoutManager(context, 2);
                //瀑布流布局管理器
//                manager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
                //***必须将布局管理器设置RecyclerView
                rvContainer.setLayoutManager(manager);
                //3、设置适配器
                HomeAdapter homeAdapter = new HomeAdapter(context, beanList);
                rvContainer.setAdapter(homeAdapter);
                //设置点击事件
                homeAdapter.setOnItemClickListener(new HomeAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(String itemInfo, int layoutPosition) {
                        Toast.makeText(context, itemInfo+"第"+layoutPosition+"个条目被点击了", Toast.LENGTH_SHORT).show();
                    }
                });

                //设置快速适配器
                /*HomeDetailAdapter homeDetailAdapter = new HomeDetailAdapter(R.layout.item_home, beanList);
                rvContainer.setAdapter(homeDetailAdapter);
                homeDetailAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        Toast.makeText(context, beanList.get(position).getJumpUrl() + "第" + position + "个条目被点击了", Toast.LENGTH_SHORT).show();
                    }
                });*/


                //多条目适配器
                /*HomeMutilpartAdapter homeMutilpartAdapter = new HomeMutilpartAdapter(context, beanList);
                rvContainer.setAdapter(homeMutilpartAdapter);*/

              /*
                //简化版本的多条目
                HomeQuickMutilpartAdapter homeQuickMutilpartAdapter = new HomeQuickMutilpartAdapter(beanList);
                //通过RecyclerView设置动画效果
                homeQuickMutilpartAdapter.openLoadAnimation(HomeQuickMutilpartAdapter.ALPHAIN);
                rvContainer.setAdapter(homeQuickMutilpartAdapter);
                homeQuickMutilpartAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        Toast.makeText(context, "当前的位置是：" + position, Toast.LENGTH_SHORT).show();
                    }
                });*/
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(stringRequest);

    }
}
