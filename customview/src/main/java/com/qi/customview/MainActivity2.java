package com.qi.customview;


import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;


import com.qi.customview.view.scaleview.HorizontalRecyclerView2;
import com.qi.customview.view.scaleview.ScaleViewItem;

import java.util.ArrayList;

public class MainActivity2 extends Activity implements HorizontalRecyclerView2.OnItemSelectedListener {

    private ArrayList<Integer> mDatas;
    private HorizontalRecyclerView2 mMHorizontalRecyclerView;
    private float mMaxValue;
    private float mMinValue;
    private MyAdapter mAdapter;
    private float mDeta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initView();
    }

    private void initData() {
        mDatas = new ArrayList<>();
        for (int i = 0; i < 24; i++) {
            mDatas.add(i);
        }
        for (int i = 0; i < 3; i++) {
            mDatas.add(0, -1);
        }
        for (int i = 0; i < 3; i++) {
            mDatas.add(-1);
        }
        mMaxValue = mDatas.get(0);
        for (int i = 0; i < mDatas.size(); i++) {
            if (mMaxValue < mDatas.get(i)) {
                mMaxValue = mDatas.get(i);
            }

        }
        mMinValue = 0;
        for (int i = 0; i < mDatas.size(); i++) {
            if (mMinValue > mDatas.get(i)) {
                mMinValue = mDatas.get(i);
            }

        }
        mDeta = mMaxValue - mMinValue;
        if (mAdapter == null) {
            mAdapter = new MyAdapter();
            mMHorizontalRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.notifyDataSetChanged();
        }
        SnapHelper snapHelperCenter = new LinearSnapHelper();
        snapHelperCenter.attachToRecyclerView(mMHorizontalRecyclerView);
    }

    private void initView() {
        mMHorizontalRecyclerView = (HorizontalRecyclerView2) findViewById(R.id.recyclerView);
        mMHorizontalRecyclerView.setOnItemSelectedListener(this);
        mMHorizontalRecyclerView.setDatas(1980, 2018);
        mMHorizontalRecyclerView.setSelectedItem(1991, 1980, 2018);
    }

    @Override
    public void onItemSelected(int id, int value) {
        Toast.makeText(this, value + "", Toast.LENGTH_LONG).show();
    }

    class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
        @Override
        public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(MainActivity2.this).inflate(R.layout.chartviewrect2, null);
            ViewHolder viewHolder = new ViewHolder(view);
            viewHolder.mChartViewItemRect = (ScaleViewItem) view.findViewById(R.id.item_chartview);
            ViewGroup.LayoutParams layoutParams = viewHolder.mChartViewItemRect.getLayoutParams();
            layoutParams.width = caculateItemWidth();
            viewHolder.mChartViewItemRect.setLayoutParams(layoutParams);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(MyAdapter.ViewHolder holder, int position) {

            int value = mDatas.get(position);
            if (position >= 3 && position < mDatas.size() - 3) {
                Log.d("MainActivity", "position_1=" + position + ",value=" + value);
                holder.mChartViewItemRect.setItemValue(value);
                holder.mChartViewItemRect.setVisibility(View.VISIBLE);
            } else {
                Log.d("MainActivity", "position_2=" + position + ",value=" + value);
                holder.mChartViewItemRect.setItemValue(value);
                holder.mChartViewItemRect.setVisibility(View.INVISIBLE);

            }

        }

        @Override
        public int getItemCount() {
            if (mDatas != null) {
                return mDatas.size();
            }
            return 0;
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            ScaleViewItem mChartViewItemRect;
            TextView mTextView;

            public ViewHolder(View itemView) {
                super(itemView);
            }
        }
    }

    private int caculateItemWidth() {
        WindowManager windowManager = (WindowManager) this.getSystemService(WINDOW_SERVICE);
        Display defaultDisplay = windowManager.getDefaultDisplay();
        int width = defaultDisplay.getWidth();
        int itemtWidth = width / 7;

        return itemtWidth;
    }

}
