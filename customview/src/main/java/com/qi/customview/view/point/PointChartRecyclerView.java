package com.qi.customview.view.point;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qi.customview.R;
import com.qi.customview.view.cylinder.CylinderViewItem;

import java.util.ArrayList;

/**
 * Created by baoqi on 2018/3/27.
 */
public class PointChartRecyclerView extends RecyclerView implements ViewTreeObserver.OnGlobalLayoutListener {
    private static final String TAG = PointChartRecyclerView.class.getSimpleName();
    private float selectedValue;
    private int scrollState;
    private OnItemSelectedListener mOnItemSelectedListener;
    private ArrayList<Integer> mDatas;
    private Context mContext;
    private Adapter mAdapter;
    private int showItemCount = 7;
    private int spaceCount;
    private Integer mMaxValue;
    private int mMinValue;
    private int mDeta;
    private int currentMode;
    private OnItemClickListener mOnItemClickListener;
    private LinearLayout scaleView;

    public PointChartRecyclerView(Context context) {
        this(context, null);
    }

    public PointChartRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initLayout();
        spaceCount = showItemCount / 2;
        currentMode = CylinderViewItem.MODE_WEEK;
//        setDatas(1, 100);
//        setMode(currentMode);
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
//        mMaxValue = 100;
        mMinValue = mDatas.get(0);
        for (int i = 0; i < mDatas.size(); i++) {
            if (mMinValue > mDatas.get(i)) {
                mMinValue = mDatas.get(i);
            }

        }

        mDeta = mMaxValue - mMinValue;

    }

    public void bindScaleView(LinearLayout scaleView) {
        this.scaleView = scaleView;
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
        getViewTreeObserver().addOnGlobalLayoutListener(this);
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
                LayoutManager layoutManager = PointChartRecyclerView.this.getLayoutManager();
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
        int childCount = PointChartRecyclerView.this.getLayoutManager().getChildCount();
        int itemCount = PointChartRecyclerView.this.getLayoutManager().getItemCount();
        Log.d(TAG, "onScrolled():childCount=" + childCount + ",itemCount=" + itemCount);
        for (int i = 0; i < childCount; i++) {
            ViewGroup item = (ViewGroup) getChildAt(i);
            PointChartViewItem chartViewItemRect = (PointChartViewItem) item.getChildAt(0);
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

    @Override
    public void onGlobalLayout() {
        int childCount = PointChartRecyclerView.this.getLayoutManager().getChildCount();
        if (childCount > 0) {
            ViewGroup item = (ViewGroup) getChildAt(0);

            PointChartViewItem chartViewItemRect = (PointChartViewItem) item.getChildAt(0);
            int measuredHeight = chartViewItemRect.getMeasuredHeight();
            //  chartViewItemRect.setBackgroundColor(Color.parseColor("#ff0000"));
            int aViewHoldermChartViewItemRectPaddingTop = chartViewItemRect.getPaddingTop();
            int aViewHoldermChartViewItemRectBottomPadding = chartViewItemRect.getBottomPadding();
            int childViewHeight = (measuredHeight - aViewHoldermChartViewItemRectBottomPadding - aViewHoldermChartViewItemRectPaddingTop) / 4;
            for (int i = 0; i < scaleView.getChildCount(); i++) {
                View childView = scaleView.getChildAt(i);
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) childView.getLayoutParams();
                layoutParams.height = childViewHeight;
                childView.setLayoutParams(layoutParams);
            }
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) scaleView.getLayoutParams();
            layoutParams.height = childViewHeight * 5;
            scaleView.setLayoutParams(layoutParams);
            //  scaleView.setBackgroundColor(Color.parseColor("#6600ff00"));
            View scaleViewLastChild = scaleView.getChildAt(4);
            int scaleViewLastChildBottom = scaleViewLastChild.getBottom();
            int aViewHoldermChartViewItemRectBottom = chartViewItemRect.getBottom();
            int scaleViewLastChildCenter = scaleViewLastChildBottom - childViewHeight / 2;
            int aViewHoldermChartViewItemRectContentBottom = aViewHoldermChartViewItemRectBottom - chartViewItemRect.getPaddingBottom();
            int detaY = scaleViewLastChildCenter - aViewHoldermChartViewItemRectContentBottom;
            RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) PointChartRecyclerView.this.getLayoutParams();
            layoutParams2.topMargin = detaY;
            PointChartRecyclerView.this.setLayoutParams(layoutParams2);
        }
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
            View view = LayoutInflater.from(mContext).inflate(R.layout.point_chart_view, null);
            final MyAdapter.ViewHolder viewHolder = new MyAdapter.ViewHolder(view);
            viewHolder.mChartViewItemRect = (PointChartViewItem) view.findViewById(R.id.item_chartview);
            viewHolder.mTextView = (TextView) view.findViewById(R.id.item_text);
//            ViewGroup.LayoutParams layoutParams = viewHolder.mChartViewItemRect.getLayoutParams();
//            layoutParams.width = caculateItemWidth();
//            viewHolder.mChartViewItemRect.setLayoutParams(layoutParams);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(final MyAdapter.ViewHolder holder, final int position) {
//            float startValue = -1f;
//            float value = (mDatas.get(position));
//            float endValue = -1f;
//            if (position == 0) {
//                startValue = -1;
//                endValue = mDatas.get(position + 1);
//            } else if (position == getItemCount() - 1) {
//                startValue = mDatas.get(position - 1);
//                endValue = -1;
//            } else {
//                startValue = mDatas.get(position - 1);
//                endValue = mDatas.get(position + 1);
//            }
            ViewGroup.LayoutParams layoutParams = holder.mChartViewItemRect.getLayoutParams();
            layoutParams.width = caculateItemWidth();
            holder.mChartViewItemRect.setLayoutParams(layoutParams);
            if (position >= spaceCount && position < mDatas.size() - spaceCount) {
                float value = (mDatas.get(position));
                holder.mChartViewItemRect.setItemValue(value / mMaxValue);
                if (currentMode == CylinderViewItem.MODE_DAY) {
                    if (position % 3 == 0) {
                        holder.mTextView.setText((position - spaceCount) + "");
                    } else {
                        holder.mTextView.setText("");
                    }
                } else if (currentMode == CylinderViewItem.MODE_MONTH) {
                    if (position % 5 == 0) {
                        holder.mTextView.setText((position - spaceCount) + "");
                    } else {
                        holder.mTextView.setText("");

                    }
                } else {
                    holder.mTextView.setText((position - spaceCount) + "");
                }
                holder.mChartViewItemRect.setVisibility(View.VISIBLE);
                holder.mTextView.setVisibility(View.VISIBLE);
            } else {
                holder.mChartViewItemRect.setVisibility(View.INVISIBLE);
                holder.mTextView.setVisibility(View.INVISIBLE);
            }
            holder.containerView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    int childCount = PointChartRecyclerView.this.getLayoutManager().getChildCount();
                    for (int i = 0; i < childCount; i++) {
                        ViewGroup item = (ViewGroup) getChildAt(i);
                        PointChartViewItem chartViewItemRect = (PointChartViewItem) item.getChildAt(0);
                        TextView textView = (TextView) item.getChildAt(1);
                        if (chartViewItemRect == holder.mChartViewItemRect) {
                            chartViewItemRect.setCheck(true);
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
            PointChartViewItem mChartViewItemRect;
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
        for (int i = 0; i < spaceCount; i++) {
            mDatas.add(0, -1);
            mDatas.add(-1);
        }
        if (mAdapter == null) {
            mAdapter = new MyAdapter();
            this.setAdapter(mAdapter);
//            SnapHelper snapHelperCenter = new LinearSnapHelper();
//            snapHelperCenter.attachToRecyclerView(this);
        } else {
            mAdapter.notifyDataSetChanged();
        }
        this.scrollToPosition(mDatas.size() - 1);

    }

    public interface OnItemClickListener {
        void onItemClik(int position);
    }

}

