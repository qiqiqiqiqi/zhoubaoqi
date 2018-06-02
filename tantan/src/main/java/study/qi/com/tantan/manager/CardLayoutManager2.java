package study.qi.com.tantan.manager;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by feng on 2017/4/23.
 */

public class CardLayoutManager2 extends RecyclerView.LayoutManager {
    private static final String TAG = CardLayoutManager2.class.getSimpleName();
    private RecyclerView mRecyclerView;
    private ItemTouchHelper mItemTouchHelper;

    public CardLayoutManager2(RecyclerView recyclerView, ItemTouchHelper itemTouchHelper) {
        mRecyclerView = recyclerView;
        mItemTouchHelper = itemTouchHelper;
    }

    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        // 先移除所有view
//        removeAllViews();
        detachAndScrapAttachedViews(recycler);//在重新布局之前先回收之前的view
      //  removeAndRecycleAllViews(recycler);
        int itemCount = getItemCount();
        int showCount = CardConfig.DEFAULT_SHOW_ITEM;
        // 当数据源个数大于最大显示数时
        if (itemCount > showCount) {
            for (int position = showCount; position >= 0; position--) {
                // 把数据源倒着循环，这样，第0个数据就在屏幕最上面了
                final View recyclerViewForPosition = recycler.getViewForPosition(position);//从recycler中获得view
                addView(recyclerViewForPosition);
                measureChildWithMargins(recyclerViewForPosition, 100, 400);
                int withSpace = getWidth() - getDecoratedMeasuredWidth(recyclerViewForPosition);
                int heightSpace = getHeight() - getDecoratedMeasuredHeight(recyclerViewForPosition);
                Log.d(TAG, "withSpace=" + withSpace + ",heightSpace=" + heightSpace);
                // 在这里默认布局是放在 RecyclerView 中心
                layoutDecoratedWithMargins(recyclerViewForPosition,
                        withSpace / 2,
                        heightSpace / 2,
                        withSpace / 2 + recyclerViewForPosition.getMeasuredWidth(),
                        heightSpace / 2 + recyclerViewForPosition.getMeasuredHeight());
                if (position == showCount) { // 第四张卡片主要是为了保持动画的连贯性
                    // 按照一定的规则缩放，并且偏移Y轴。
                    // CardConfig.DEFAULT_SCALE 默认为0.1f，CardConfig.DEFAULT_TRANSLATE_Y 默认为14
                    recyclerViewForPosition.setScaleX(1 - (position - 1) * CardConfig.DEFAULT_SCALE);
                    recyclerViewForPosition.setScaleY(1 - (position - 1) * CardConfig.DEFAULT_SCALE);
                    recyclerViewForPosition.setTranslationY((position - 1) * recyclerViewForPosition.getMeasuredHeight() / CardConfig.DEFAULT_TRANSLATE_Y);
                } else if (position > 0) {
                    recyclerViewForPosition.setScaleX(1 - (position) * CardConfig.DEFAULT_SCALE);
                    recyclerViewForPosition.setScaleY(1 - (position) * CardConfig.DEFAULT_SCALE);
                    recyclerViewForPosition.setTranslationY((position) * recyclerViewForPosition.getMeasuredHeight() / CardConfig.DEFAULT_TRANSLATE_Y);
                } else {
                    // 设置 mTouchListener 的意义就在于我们想让处于顶层的卡片是可以随意滑动的
                    // 而第二层、第三层等等的卡片是禁止滑动的
                    recyclerViewForPosition.setOnTouchListener(new View.OnTouchListener() {
                        @Override
                        public boolean onTouch(View v, MotionEvent event) {
                            if (mItemTouchHelper != null && event.getAction() == MotionEvent.ACTION_DOWN) {
                                // 把触摸事件交给 mItemTouchHelper，让其处理卡片滑动事件
                                mItemTouchHelper.startSwipe(mRecyclerView.getChildViewHolder(v));
                            }
                            return false;
                        }
                    });
                }
            }
        } else {
            // 当数据源个数小于或等于最大显示数时，和上面的代码差不多
            for (int position = itemCount - 1; position >= 0; position--) {
                final View recyclerViewForPosition = recycler.getViewForPosition(position);
                addView(recyclerViewForPosition);
                measureChildWithMargins(recyclerViewForPosition, 100, 400);
                int withSpace = getWidth() - recyclerViewForPosition.getMeasuredWidth();
                int heightSpace = getHeight() - recyclerViewForPosition.getMeasuredHeight();
                Log.d(TAG, "withSpace=" + withSpace + ",heightSpace=" + heightSpace);
                layoutDecorated(recyclerViewForPosition,
                        withSpace / 2,
                        heightSpace / 2,
                        withSpace / 2 + recyclerViewForPosition.getMeasuredWidth(),
                        heightSpace / 2 + recyclerViewForPosition.getMeasuredHeight());
                if (position > 0) {
                    recyclerViewForPosition.setScaleX(1 - (position) * CardConfig.DEFAULT_SCALE);
                    recyclerViewForPosition.setScaleY(1 - (position) * CardConfig.DEFAULT_SCALE);
                    recyclerViewForPosition.setTranslationY((position) * recyclerViewForPosition.getMeasuredHeight() / CardConfig.DEFAULT_TRANSLATE_Y);
                } else {
                    recyclerViewForPosition.setOnTouchListener(new View.OnTouchListener() {
                        @Override
                        public boolean onTouch(View v, MotionEvent event) {
                            if (mItemTouchHelper != null && event.getAction() == MotionEvent.ACTION_DOWN) {
                                mItemTouchHelper.startSwipe(mRecyclerView.getChildViewHolder(v));
                            }
                            return false;
                        }
                    });
                }
            }
        }
    }
}
