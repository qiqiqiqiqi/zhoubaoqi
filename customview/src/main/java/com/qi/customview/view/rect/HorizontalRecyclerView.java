package com.qi.customview.view.rect;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;
import android.view.ViewGroup;

/**
 * Created by 13324 on 2016/9/29.
 */
public class HorizontalRecyclerView extends RecyclerView {
    public HorizontalRecyclerView(Context context) {
        this(context, null);
    }

    public HorizontalRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initLayout();
        initEvent();
    }

    private void initLayout() {
        this.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL));
    }

    private void initEvent() {

        this.setOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int childCount = getChildCount();
                for (int i = 0; i < childCount; i++) {
                    ViewGroup item = (ViewGroup) getChildAt(i);
                    ChartViewItemRect chartViewItemRect = (ChartViewItemRect) item.getChildAt(0);
                    if (item.getLeft() > 3 * item.getWidth() - item.getWidth() / 2 && item.getLeft() < 3 * item.getWidth() + item.getWidth() / 2) {
                        chartViewItemRect.setCheck(true);
                    }else{
                        chartViewItemRect.setCheck(false);
                    }
                }
            }
        });
    }

    @Override
    protected boolean overScrollBy(int deltaX, int deltaY, int scrollX, int scrollY, int scrollRangeX, int scrollRangeY, int maxOverScrollX, int maxOverScrollY, boolean isTouchEvent) {
        return super.overScrollBy(deltaX, deltaY, scrollX, scrollY, scrollRangeX, scrollRangeY, 200, maxOverScrollY, isTouchEvent);
    }
}
