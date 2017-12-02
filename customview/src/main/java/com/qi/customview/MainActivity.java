package com.qi.customview;


import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import com.qi.customview.view.ChartViewItemRect;
import com.qi.customview.view.HorizontalRecyclerView;

import java.util.ArrayList;

public class  MainActivity extends Activity {

    private ArrayList<Float> mDatas;
    private HorizontalRecyclerView mMHorizontalRecyclerView;
    private float mMaxValue;
    private float mMinValue;
    private MyAdapter mAdapter;
    private float mDeta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initData() {
        mDatas = new ArrayList<>();
        for (int i = 0; i < 24; i++) {
            mDatas.add((float) (Math.random() * 100));
        }
        for (int i = 0; i < 3; i++) {
            mDatas.add(0, -1.0f);
        }
        for (int i = 0; i < 2; i++) {
            mDatas.add(-1.0f);
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
    }

    private void initView() {
        mMHorizontalRecyclerView = (HorizontalRecyclerView) findViewById(R.id.recyclerView);
    }

    class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
        @Override
        public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.chartviewrect, null);
            ViewHolder viewHolder = new ViewHolder(view);
            viewHolder.mChartViewItemRect = (ChartViewItemRect) view.findViewById(R.id.item_chartview);
            viewHolder.mTextView = (TextView) view.findViewById(R.id.item_text);
            ViewGroup.LayoutParams layoutParams = viewHolder.mChartViewItemRect.getLayoutParams();
            layoutParams.width = caculateItemWidth();
            viewHolder.mChartViewItemRect.setLayoutParams(layoutParams);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(MyAdapter.ViewHolder holder, int position) {

            float value = mDatas.get(position);
            if (position >= 3 && position <= mDatas.size() - 4) {
                Log.d("MainActivity", "position_1=" + position + ",value=" + value);
                holder.mChartViewItemRect.setItemValue((value - mMinValue) / mDeta);
                holder.mTextView.setText(position + "");
                holder.mChartViewItemRect.setVisibility(View.VISIBLE);
                holder.mTextView.setVisibility(View.VISIBLE);
            } else {
                Log.d("MainActivity", "position_2=" + position + ",value=" + value);
                holder.mChartViewItemRect.setItemValue(value);
                holder.mTextView.setText("");
                holder.mChartViewItemRect.setVisibility(View.INVISIBLE);
                holder.mTextView.setVisibility(View.INVISIBLE);
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
            ChartViewItemRect mChartViewItemRect;
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
