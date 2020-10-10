package com.wzq.recycler_countdown;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {
    MyAdapter mAdapter;
    RecyclerView mListView;
    Button button, button2;
    private static List<TimerItem> listTimerItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        mListView = (RecyclerView) findViewById(R.id.recycleFriends);
        button = (Button) findViewById(R.id.button);
        button2 = (Button) findViewById(R.id.button2);
        mAdapter = new MyAdapter(this, initData());
        mListView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new MyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(MainActivity2.this, "点击了时间", Toast.LENGTH_SHORT).show();
            }
        });

        // 增加
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addItem();
            }
        });
        // 删除
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                delItem();
            }
        });

    }

    public static List<TimerItem> initData() {
        listTimerItems = new ArrayList<>();
        listTimerItems.add(new TimerItem("A", System.currentTimeMillis() + 30 * 1000));
        listTimerItems.add(new TimerItem("B", System.currentTimeMillis() + 30 * 1000));
        listTimerItems.add(new TimerItem("C1", System.currentTimeMillis() + 30 * 1000));
        listTimerItems.add(new TimerItem("Cw", System.currentTimeMillis() + 30 * 1000));
        listTimerItems.add(new TimerItem("Csa", System.currentTimeMillis() + 30 * 1000));
        listTimerItems.add(new TimerItem("C2", System.currentTimeMillis() + 30 * 1000));
        listTimerItems.add(new TimerItem("Csa", System.currentTimeMillis() + 30 * 1000));
        listTimerItems.add(new TimerItem("Csa", System.currentTimeMillis() + 30 * 1000));
        listTimerItems.add(new TimerItem("Csa", System.currentTimeMillis() + 30 * 1000));
        listTimerItems.add(new TimerItem("Csa", System.currentTimeMillis() + 30 * 1000));
        listTimerItems.add(new TimerItem("asaC", System.currentTimeMillis() + 30 * 1000));
        listTimerItems.add(new TimerItem("daC", System.currentTimeMillis() + 30 * 1000));
        listTimerItems.add(new TimerItem("Chd", System.currentTimeMillis() + 30 * 1000));
        listTimerItems.add(new TimerItem("Cgd", System.currentTimeMillis() + 30 * 1000));
        listTimerItems.add(new TimerItem("gdC", System.currentTimeMillis() + 30 * 1000));
        listTimerItems.add(new TimerItem("sfsC", System.currentTimeMillis() + 30 * 1000));
        listTimerItems.add(new TimerItem("sfC", System.currentTimeMillis() + 30 * 1000));
        listTimerItems.add(new TimerItem("sfsC", System.currentTimeMillis() + 30 * 1000));
        listTimerItems.add(new TimerItem("sfsC", System.currentTimeMillis() + 30 * 1000));
        listTimerItems.add(new TimerItem("sfsC", System.currentTimeMillis() + 30 * 1000));
        listTimerItems.add(new TimerItem("Cfsf", System.currentTimeMillis() + 30 * 1000));
        listTimerItems.add(new TimerItem("fsC", System.currentTimeMillis() + 30 * 1000));
        listTimerItems.add(new TimerItem("Chf", System.currentTimeMillis() + 30 * 1000));
        listTimerItems.add(new TimerItem("Chfg", System.currentTimeMillis() + 30 * 1000));
        listTimerItems.add(new TimerItem("fhfC", System.currentTimeMillis() + 30 * 1000));
        listTimerItems.add(new TimerItem("hfgC", System.currentTimeMillis() + 30 * 1000));
        listTimerItems.add(new TimerItem("hfghC", System.currentTimeMillis() + 30 * 1000));
        listTimerItems.add(new TimerItem("Chfgh", System.currentTimeMillis() + 30 * 1000));
        return listTimerItems;

    }

    public void addItem() {
        listTimerItems.add(1, new TimerItem("wzq", System.currentTimeMillis() + 30 * 1000));
        mAdapter.notifyItemInserted(1);

    }

    public void delItem() {
        listTimerItems.remove(1);
        mAdapter.notifyItemRemoved(1);
//        mAdapter.notifyItemRangeRemoved(1, mAdapter.getItemCount());

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mAdapter != null) {
            mAdapter.cancelAllTimers();
        }
    }
}