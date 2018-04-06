package com.qi.customview;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.qi.customview.view.cylinder.CylinderViewItem;
import com.qi.customview.view.line.LineChartRecyclerView;
import com.qi.customview.view.line.LineChartViewItem;
import com.qi.customview.view.point.PointChartRecyclerView;

import java.util.ArrayList;

public class PointActivity extends Activity implements PointChartRecyclerView.OnItemSelectedListener, View.OnClickListener {

    private ArrayList<Integer> mDatas;
    private PointChartRecyclerView mMHorizontalRecyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_point);
        initView();
    }


    private void initView() {
        mMHorizontalRecyclerView = (PointChartRecyclerView) findViewById(R.id.recyclerView);
        LinearLayout scaleContainer = (LinearLayout) findViewById(R.id.scale_container);
        mMHorizontalRecyclerView.bindScaleView(scaleContainer);
        mMHorizontalRecyclerView.setOnItemSelectedListener(this);
        mMHorizontalRecyclerView.setDatas(0, 100);
        mMHorizontalRecyclerView.setMode(LineChartViewItem.MODE_WEEK);

        //  mMHorizontalRecyclerView.setSelectedItem(12, 0, 100);
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
                mMHorizontalRecyclerView.setDatas(0, 47);
                mMHorizontalRecyclerView.setMode(CylinderViewItem.MODE_DAY);
                break;
            case R.id.week:

                mMHorizontalRecyclerView.setDatas(0, 13);
                mMHorizontalRecyclerView.setMode(CylinderViewItem.MODE_WEEK);
                break;
            case R.id.month:

                mMHorizontalRecyclerView.setDatas(0, 61);
                mMHorizontalRecyclerView.setMode(CylinderViewItem.MODE_MONTH);
                break;
            case R.id.year:

                mMHorizontalRecyclerView.setDatas(0, 23);
                mMHorizontalRecyclerView.setMode(CylinderViewItem.MODE_YEAR);
                break;
        }

    }
}
