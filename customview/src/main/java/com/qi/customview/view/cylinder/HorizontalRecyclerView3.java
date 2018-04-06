package com.qi.customview.view.cylinder;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.qi.customview.R;

import java.util.ArrayList;

/**
 * Created by baoqi on 2018/3/27.
 */
public class HorizontalRecyclerView3 extends RecyclerView {
    private static final String TAG = HorizontalRecyclerView3.class.getSimpleName();
    private float selectedValue;
    private int scrollState;
    private OnItemSelectedListener mOnItemSelectedListener;
    private ArrayList<Integer> mDatas;
    private Context mContext;
    private Adapter mAdapter;
    private int showItemCount = 31;
    private int spaceCount;
    private Integer mMaxValue;
    private int mMinValue;
    private int mDeta;
    private int currentMode;
    private OnItemClickListener mOnItemClickListener;

    public HorizontalRecyclerView3(Context context) {
        this(context, null);
    }

    public HorizontalRecyclerView3(Context context, AttributeSet attrs) {
        super(context, attrs);
        initLayout();
        mContext = context;
        spaceCount = showItemCount / 2;
        currentMode = CylinderViewItem.MODE_MONTH;
        setDatas(1, 5);
        initEvent();

    }


    public void setDatas(int minData, int maxData) {
        mDatas = new ArrayList<>();
        for (int i = minData; i <= maxData; i++) {

            mDatas.add((int) (Math.random() * 100));
        }
        mMaxValue = 0;
        for (int i = 0; i < mDatas.size(); i++) {
            if (mMaxValue < mDatas.get(i)) {
                mMaxValue = mDatas.get(i);
            }

        }
        mMinValue = mDatas.get(0);
        for (int i = 0; i < mDatas.size(); i++) {
            if (mMinValue > mDatas.get(i)) {
                mMinValue = mDatas.get(i);
            }

        }


        mDeta = mMaxValue - mMinValue;
        if (mAdapter == null) {
            mAdapter = new MyAdapter();
            this.setAdapter(mAdapter);
//            SnapHelper snapHelperCenter = new LinearSnapHelper();
//            snapHelperCenter.attachToRecyclerView(this);
        } else {
            mAdapter.notifyDataSetChanged();
        }

    }

    public void setSelectedItem(int selectedValue, int minValue, int maxValue) {
        int range = selectedValue - minValue;
        if (range >= spaceCount) {
            //    selectedValue-spaceCount-minValue+spaceCount;
            this.scrollToPosition(range);
            this.selectedValue = selectedValue;
        } else {
            this.scrollToPosition(0);
        }
    }

    private void initLayout() {
        this.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL));
    }

    private void initEvent() {

        this.addOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                refreshItem();
                //判断是否最后一item个显示出来
                RecyclerView.LayoutManager layoutManager = HorizontalRecyclerView3.this.getLayoutManager();
                //可见的item个数
                int visibleChildCount = layoutManager.getChildCount();
                if (visibleChildCount > 0) {
                    View firstVisibleView = recyclerView.getChildAt(0);
                    int firstVisiblePosition = recyclerView.getChildLayoutPosition(firstVisibleView);
                    View lastVisibleView = recyclerView.getChildAt(recyclerView.getChildCount() - 1);
                    int lastVisiblePosition = recyclerView.getChildLayoutPosition(lastVisibleView);
                    Log.d(TAG, "onScrolled():visibleChildCount=" + visibleChildCount + ",lastVisiblePosition=" + lastVisiblePosition + ",firstVisiblePosition=" + firstVisiblePosition);
                    if (lastVisiblePosition >= layoutManager.getItemCount() - 1) {


                    } else {

                    }
                }
            }

        });
    }

    private void refreshItem() {
        int childCount = HorizontalRecyclerView3.this.getLayoutManager().getChildCount();
        int itemCount = HorizontalRecyclerView3.this.getLayoutManager().getItemCount();
        Log.d(TAG, "onScrolled():childCount=" + childCount + ",itemCount=" + itemCount);
        for (int i = 0; i < childCount; i++) {
            ViewGroup item = (ViewGroup) getChildAt(i);
            CylinderViewItem chartViewItemRect = (CylinderViewItem) item.getChildAt(0);
            chartViewItemRect.setMode(currentMode);
            TextView textView = (TextView) item.getChildAt(1);
//            if (item.getLeft() - getPaddingLeft() >= spaceCount * item.getWidth() - (int) ((item.getWidth() + 0.5f) / 2) && item.getLeft() - getPaddingLeft() <= spaceCount * item.getWidth() + (int) ((item.getWidth() + 0.5f) / 2)) {
//                chartViewItemRect.setCheck(true);
//                selectedValue = chartViewItemRect.getItemValue();
//                textView.setTextColor(Color.parseColor("#00e7b7"));
//            } else {
//                chartViewItemRect.setCheck(false);
//                textView.setTextColor(Color.parseColor("#ffd4eaff"));
//            }
            int measuredWidth = getMeasuredWidth();
            int left = item.getLeft();
            int right = item.getRight();

            if (Math.abs((left + right) / 2 - measuredWidth / 2) <= item.getWidth() / 2) {
                chartViewItemRect.setCheck(true);
                selectedValue = chartViewItemRect.getItemValue();
                textView.setTextColor(Color.parseColor("#00e7b7"));
            } else {
                chartViewItemRect.setCheck(false);
                textView.setTextColor(Color.parseColor("#ffd4eaff"));
            }

        }
    }

    @Override
    protected boolean overScrollBy(int deltaX, int deltaY, int scrollX, int scrollY, int scrollRangeX, int scrollRangeY, int maxOverScrollX, int maxOverScrollY, boolean isTouchEvent) {
        return super.overScrollBy(deltaX, deltaY, scrollX, scrollY, scrollRangeX, scrollRangeY, 200, maxOverScrollY, isTouchEvent);
    }

    public interface OnItemSelectedListener {
        void onItemSelected(int id, float value);
    }

    public void setOnItemSelectedListener(OnItemSelectedListener onItemSelectedListener) {
        mOnItemSelectedListener = onItemSelectedListener;
    }

    class MyAdapter extends Adapter<MyAdapter.ViewHolder> {
        @Override
        public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.chartviewrect3, null);
            MyAdapter.ViewHolder viewHolder = new MyAdapter.ViewHolder(view);
            viewHolder.mChartViewItemRect = (CylinderViewItem) view.findViewById(R.id.item_chartview);
            viewHolder.mTextView = (TextView) view.findViewById(R.id.item_text);
            ViewGroup.LayoutParams layoutParams = viewHolder.mChartViewItemRect.getLayoutParams();
            layoutParams.width = caculateItemWidth();
            viewHolder.mChartViewItemRect.setLayoutParams(layoutParams);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(final MyAdapter.ViewHolder holder, final int position) {

            int value = mDatas.get(position);
            Log.d("MainActivity", "position_1=" + position + ",value=" + value + ",maxValue=" + mMaxValue + ",minValue=" + mMinValue + ",mDeta=" + mDeta);
            ViewGroup.LayoutParams layoutParams = holder.mChartViewItemRect.getLayoutParams();
            layoutParams.width = caculateItemWidth();
            holder.mChartViewItemRect.setLayoutParams(layoutParams);
            holder.mChartViewItemRect.setItemValue((float) (value - mMinValue) / mDeta);
            holder.mChartViewItemRect.setMode(currentMode);
            if (currentMode == CylinderViewItem.MODE_DAY) {
                if (position % 3 == 0) {
                    holder.mTextView.setText(position + "");
                } else {
                    holder.mTextView.setText("");
                }
            } else if (currentMode == CylinderViewItem.MODE_MONTH) {
                if (position % 5 == 0) {
                    holder.mTextView.setText(position + "");
                } else {
                    holder.mTextView.setText("");
                }
            } else {
                holder.mTextView.setText(position + "");
            }
            holder.mChartViewItemRect.setVisibility(View.VISIBLE);
            holder.mTextView.setVisibility(View.VISIBLE);
            holder.containerView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    int childCount = HorizontalRecyclerView3.this.getLayoutManager().getChildCount();
                    for (int i = 0; i < childCount; i++) {
                        ViewGroup item = (ViewGroup) getChildAt(i);
                        CylinderViewItem chartViewItemRect = (CylinderViewItem) item.getChildAt(0);
                        chartViewItemRect.setMode(currentMode);
                        TextView textView = (TextView) item.getChildAt(1);
                        if (chartViewItemRect == holder.mChartViewItemRect) {
                            chartViewItemRect.setCheck(true);
                            selectedValue = chartViewItemRect.getItemValue();
                            textView.setTextColor(Color.parseColor("#00e7b7"));
                        } else {
                            chartViewItemRect.setCheck(false);
                            textView.setTextColor(Color.parseColor("#ffd4eaff"));
                        }

                    }
                    if (mOnItemClickListener != null) {
                        mOnItemClickListener.onItemClik(position);
                    }
                }
            });

        }

        @Override
        public int getItemCount() {
            if (mDatas != null) {
                return mDatas.size();
            }
            return 0;
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            CylinderViewItem mChartViewItemRect;
            TextView mTextView;
            View containerView;

            public ViewHolder(View itemView) {
                super(itemView);
                containerView = itemView;
            }
        }
    }

    private int caculateItemWidth() {
//        WindowManager windowManager = (WindowManager) mContext.getSystemService(WINDOW_SERVICE);
//        Display defaultDisplay = windowManager.getDefaultDisplay();
//        int width = defaultDisplay.getWidth();
        int width = getMeasuredWidth() - getPaddingLeft() - getPaddingRight();
        int itemtWidth = width / showItemCount;
        Log.d(TAG, "caculateItemWidth():itemtWidth=" + itemtWidth + ",width=" + width);
        return itemtWidth;
    }

    public void setMode(int mode) {
        if (mode == currentMode) {
            return;
        }
        currentMode = mode;
        switch (mode) {
            case CylinderViewItem.MODE_DAY:
                showItemCount = 24;
                break;
            case CylinderViewItem.MODE_WEEK:
                showItemCount = 7;
                break;
            case CylinderViewItem.MODE_MONTH:
                showItemCount = 31;
                break;
            case CylinderViewItem.MODE_YEAR:
                showItemCount = 12;
                break;
        }
        spaceCount = showItemCount / 2;

    }

    public interface OnItemClickListener {
        void onItemClik(int position);
    }

}

