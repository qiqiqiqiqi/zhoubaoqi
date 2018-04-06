package com.qi.customview.view.scaleview;

import android.content.Context;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qi.customview.R;

import java.util.ArrayList;

/**
 * Created by baoqi on 2018/3/27.
 */
public class HorizontalRecyclerView2 extends RecyclerView {
    private static final String TAG = HorizontalRecyclerView2.class.getSimpleName();
    private int selectedPositon;
    private int scrollState;
    private OnItemSelectedListener mOnItemSelectedListener;
    private ArrayList<Integer> mDatas;
    private Context mContext;
    private Adapter mAdapter;
    private int showItemCount = 5;
    private int spaceCount;

    public HorizontalRecyclerView2(Context context) {
        this(context, null);
    }

    public HorizontalRecyclerView2(Context context, AttributeSet attrs) {
        super(context, attrs);
        initLayout();
        mContext = context;
        spaceCount = showItemCount / 2;
        setDatas(1, 5);
        initEvent();
    }


    public void setDatas(int minData, int maxData) {
        mDatas = new ArrayList<>();
        for (int i = minData; i <= maxData; i++) {
            mDatas.add(i);
        }
        for (int i = 0; i < spaceCount; i++) {
            mDatas.add(0, -1);
        }
        for (int i = 0; i < spaceCount; i++) {
            mDatas.add(-1);
        }
        if (mAdapter == null) {
            mAdapter = new MyAdapter();
            this.setAdapter(mAdapter);
            SnapHelper snapHelperCenter = new LinearSnapHelper();
            snapHelperCenter.attachToRecyclerView(this);
        } else {
            mAdapter.notifyDataSetChanged();
        }
    }

    public void setSelectedItem(int selectedValue, int minValue, int maxValue) {
        int range = selectedValue - minValue;
        if (range >= spaceCount) {
            //    selectedValue-spaceCount-minValue+spaceCount;
            this.scrollToPosition(range);
            selectedPositon = selectedValue;
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
                int childCount = getChildCount();
                scrollState = newState;
                Log.d(TAG, "onScrollStateChanged():newState=" + newState + ",childCount=" + childCount + ",selectedPositon=" + selectedPositon);
                if (newState == SCROLL_STATE_IDLE) { //停止滚动
                    for (int i = 0; i < childCount; i++) {
                        ViewGroup item = (ViewGroup) getChildAt(i);
                        ScaleViewItem chartViewItemRect = (ScaleViewItem) item.getChildAt(0);
                        if (item.getLeft() - getPaddingLeft() > spaceCount * item.getWidth() - item.getWidth() / 2 && item.getLeft() - getPaddingLeft() < spaceCount * item.getWidth() + item.getWidth() / 2) {
                            chartViewItemRect.setCheck(true, newState);
                            selectedPositon = chartViewItemRect.getItemValue();
                            if (mOnItemSelectedListener != null) {
                                mOnItemSelectedListener.onItemSelected(getId(), selectedPositon);
                            }
                        } else {
                            chartViewItemRect.setCheck(false, newState);
                        }

                    }
                } else if (newState == SCROLL_STATE_DRAGGING) {//正在被外部拖拽,一般为用户正在用手指滚动
                    // TODO: 2018/3/27 view 的复用
                    for (int i = 0; i < childCount; i++) {
                        ViewGroup item = (ViewGroup) getChildAt(i);
                        ScaleViewItem chartViewItemRect = (ScaleViewItem) item.getChildAt(0);
                        if (chartViewItemRect.getCheck(selectedPositon)) {
                            chartViewItemRect.setCheck(true, newState);
                        } else {
                            chartViewItemRect.setCheck(false, newState);
                        }

                    }

                } else if (newState == SCROLL_STATE_SETTLING) {//自动滚动开始
                    for (int i = 0; i < childCount; i++) {
                        ViewGroup item = (ViewGroup) getChildAt(i);
                        ScaleViewItem chartViewItemRect = (ScaleViewItem) item.getChildAt(0);
                        if (chartViewItemRect.getCheck(selectedPositon)) {
                            chartViewItemRect.setCheck(true, newState);
                        } else {
                            chartViewItemRect.setCheck(false, newState);
                        }

                    }
                }

            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
               /* int childCount = getChildCount();
                for (int i = 0; i < childCount; i++) {
                    ViewGroup item = (ViewGroup) getChildAt(i);
                    ScaleViewItem chartViewItemRect = (ScaleViewItem) item.getChildAt(0);
                    if (item.getLeft() > 3 * item.getWidth() - item.getWidth() / 2 && item.getLeft() < 3 * item.getWidth() + item.getWidth() / 2) {
                        chartViewItemRect.setCheck(true);
                    } else {
                        chartViewItemRect.setCheck(false);
                    }
                }*/
                int childCount = getChildCount();
                for (int i = 0; i < childCount; i++) {
                    ViewGroup item = (ViewGroup) getChildAt(i);
                    ScaleViewItem chartViewItemRect = (ScaleViewItem) item.getChildAt(0);
                    if (chartViewItemRect.getCheck(selectedPositon)) {
                        chartViewItemRect.setCheck(true, scrollState);
                    } else {
                        chartViewItemRect.setCheck(false, scrollState);
                    }

                }
            }

        });
    }

    @Override
    protected boolean overScrollBy(int deltaX, int deltaY, int scrollX, int scrollY, int scrollRangeX, int scrollRangeY, int maxOverScrollX, int maxOverScrollY, boolean isTouchEvent) {
        return super.overScrollBy(deltaX, deltaY, scrollX, scrollY, scrollRangeX, scrollRangeY, 200, maxOverScrollY, isTouchEvent);
    }

    public interface OnItemSelectedListener {
        void onItemSelected(int id, int value);
    }

    public void setOnItemSelectedListener(OnItemSelectedListener onItemSelectedListener) {
        mOnItemSelectedListener = onItemSelectedListener;
    }

    class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
        @Override
        public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.chartviewrect2, null);
            MyAdapter.ViewHolder viewHolder = new MyAdapter.ViewHolder(view);
            viewHolder.mChartViewItemRect = (ScaleViewItem) view.findViewById(R.id.item_chartview);
            ViewGroup.LayoutParams layoutParams = viewHolder.mChartViewItemRect.getLayoutParams();
            layoutParams.width = caculateItemWidth();
            viewHolder.mChartViewItemRect.setLayoutParams(layoutParams);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(MyAdapter.ViewHolder holder, int position) {

            int value = mDatas.get(position);
            if (position >= spaceCount && position < mDatas.size() - spaceCount) {
                Log.d("MainActivity", "position_1=" + position + ",value=" + value);
                holder.mChartViewItemRect.setItemValue(value);
                if (value == selectedPositon) {
                    holder.mChartViewItemRect.setCheck(true, 0);
                }
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

            public ViewHolder(View itemView) {
                super(itemView);
            }
        }
    }

    private int caculateItemWidth() {
//        WindowManager windowManager = (WindowManager) mContext.getSystemService(WINDOW_SERVICE);
//        Display defaultDisplay = windowManager.getDefaultDisplay();
//        int width = defaultDisplay.getWidth();
        int width = getMeasuredWidth() - getPaddingLeft() - getPaddingRight();
        int itemtWidth = width / showItemCount;

        return itemtWidth;
    }
}
