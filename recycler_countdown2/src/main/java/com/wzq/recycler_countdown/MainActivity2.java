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
        listTimerItems.add(new TimerItem("01","A", System.currentTimeMillis() + 30 * 1000));
        listTimerItems.add(new TimerItem("02","B", System.currentTimeMillis() + 30 * 1000));
        listTimerItems.add(new TimerItem("03","C1", System.currentTimeMillis() + 30 * 1000));
        listTimerItems.add(new TimerItem("04","Cw", System.currentTimeMillis() + 30 * 1000));
        listTimerItems.add(new TimerItem("05","Csa", System.currentTimeMillis() + 30 * 1000));
        listTimerItems.add(new TimerItem("06","C2", System.currentTimeMillis() + 30 * 1000));
        listTimerItems.add(new TimerItem("07","Csa", System.currentTimeMillis() + 30 * 1000));
        listTimerItems.add(new TimerItem("08","Csa", System.currentTimeMillis() + 30 * 1000));
        listTimerItems.add(new TimerItem("09","Csa", System.currentTimeMillis() + 30 * 1000));
        listTimerItems.add(new TimerItem("10","Csa", System.currentTimeMillis() + 30 * 1000));
        listTimerItems.add(new TimerItem("11","asaC", System.currentTimeMillis() + 30 * 1000));
        listTimerItems.add(new TimerItem("12","daC", System.currentTimeMillis() + 30 * 1000));
        listTimerItems.add(new TimerItem("13","Chd", System.currentTimeMillis() + 30 * 1000));
        listTimerItems.add(new TimerItem("14","Cgd", System.currentTimeMillis() + 30 * 1000));
        listTimerItems.add(new TimerItem("15","gdC", System.currentTimeMillis() + 30 * 1000));
        listTimerItems.add(new TimerItem("16","sfsC", System.currentTimeMillis() + 30 * 1000));
        listTimerItems.add(new TimerItem("17","sfC", System.currentTimeMillis() + 30 * 1000));
        listTimerItems.add(new TimerItem("18","sfsC", System.currentTimeMillis() + 30 * 1000));
        listTimerItems.add(new TimerItem("19","sfsC", System.currentTimeMillis() + 30 * 1000));
        listTimerItems.add(new TimerItem("20","sfsC", System.currentTimeMillis() + 30 * 1000));
        listTimerItems.add(new TimerItem("21","Cfsf", System.currentTimeMillis() + 30 * 1000));
        listTimerItems.add(new TimerItem("22","fsC", System.currentTimeMillis() + 30 * 1000));
        listTimerItems.add(new TimerItem("23","Chf", System.currentTimeMillis() + 30 * 1000));
        listTimerItems.add(new TimerItem("34","Chfg", System.currentTimeMillis() + 30 * 1000));
        listTimerItems.add(new TimerItem("25","fhfC", System.currentTimeMillis() + 30 * 1000));
        listTimerItems.add(new TimerItem("26","hfgC", System.currentTimeMillis() + 30 * 1000));
        listTimerItems.add(new TimerItem("27","hfghC", System.currentTimeMillis() + 30 * 1000));
        listTimerItems.add(new TimerItem("28","Chfgh", System.currentTimeMillis() + 30 * 1000));
        return listTimerItems;

    }

    public void addItem() {
        listTimerItems.add(1, new TimerItem("99","wzq", System.currentTimeMillis() + 30 * 1000));
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