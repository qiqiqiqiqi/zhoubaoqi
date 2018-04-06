package com.qi.customview;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.qi.customview.view.cylinder.CylinderViewItem;
import com.qi.customview.view.cylinder.HorizontalRecyclerView3;

import java.util.ArrayList;

public class MainActivity3 extends Activity implements HorizontalRecyclerView3.OnItemSelectedListener, View.OnClickListener {

    private ArrayList<Integer> mDatas;
    private HorizontalRecyclerView3 mMHorizontalRecyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        initView();
    }


    private void initView() {
        mMHorizontalRecyclerView = (HorizontalRecyclerView3) findViewById(R.id.recyclerView);
        mMHorizontalRecyclerView.setOnItemSelectedListener(this);
        mMHorizontalRecyclerView.setDatas(0, 100);
        mMHorizontalRecyclerView.setSelectedItem(12, 0, 100);
        TextView day = (TextView) findViewById(R.id.day);
        TextView week = (TextView) findViewById(R.id.week);
        TextView month = (TextView) findViewById(R.id.month);
        TextView year = (TextView) findViewById(R.id.year);
        day.setOnClickListener(this);
        week.setOnClickListener(this);
        month.setOnClickListener(this);
        year.setOnClickListener(this);
    }

    @Override
    public void onItemSelected(int id, float value) {
        Toast.makeText(this, value + "", Toast.LENGTH_LONG).show();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.day:
                mMHorizontalRecyclerView.setMode(CylinderViewItem.MODE_DAY);
                mMHorizontalRecyclerView.setDatas(0, 47);
                break;
            case R.id.week:
                mMHorizontalRecyclerView.setMode(CylinderViewItem.MODE_WEEK);
                mMHorizontalRecyclerView.setDatas(0, 13);
                break;
            case R.id.month:
                mMHorizontalRecyclerView.setMode(CylinderViewItem.MODE_MONTH);
                mMHorizontalRecyclerView.setDatas(1, 60);
                break;
            case R.id.year:
                mMHorizontalRecyclerView.setMode(CylinderViewItem.MODE_YEAR);
                mMHorizontalRecyclerView.setDatas(0, 23);
                break;
        }

    }
}
