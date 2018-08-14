package study.qi.com.tantan.manager;

import android.animation.ValueAnimator;
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
    private float mValue;

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
        // removeAllViews();
        detachAndScrapAttachedViews(recycler);//在重新布局之前先回收之前的view
        //  removeAndRecycleAllViews(recycler);
        int itemCount = getItemCount();
        Log.d(TAG, "onLayoutChildren():itemCount=" + itemCount);
        int showCount = CardConfig.DEFAULT_SHOW_ITEM;
        // 当数据源个数大于最大显示数时
        if (itemCount >= showCount + 2) {
            for (int position = showCount + 1; position >= 0; position--) {
                // 把数据源倒着循环，这样，第0个数据就在屏幕最上面了
                final View recyclerViewForPosition = recycler.getViewForPosition(position);//从recycler中获得view
                recyclerViewForPosition.setScaleX(1);
                recyclerViewForPosition.setScaleY(1);
                recyclerViewForPosition.setTranslationY(0);
                addView(recyclerViewForPosition);
                measureChildWithMargins(recyclerViewForPosition, 100, 400);
                int withSpace = getWidth() - getDecoratedMeasuredWidth(recyclerViewForPosition);
                int heightSpace = getHeight() - getDecoratedMeasuredHeight(recyclerViewForPosition);
                Log.d(TAG, "withSpace=" + withSpace + ",heightSpace=" + heightSpace);
                // 在这里默认布局是放在 RecyclerView 中心
                layoutDecoratedWithMargins(recyclerViewForPosition,//第一个和第二个重合
                        withSpace / 2,
                        heightSpace / 2,
                        withSpace / 2 + recyclerViewForPosition.getMeasuredWidth(),
                        heightSpace / 2 + recyclerViewForPosition.getMeasuredHeight());
                if (position > showCount) {
                    // 按照一定的规则缩放，并且偏移Y轴。
                    // CardConfig.DEFAULT_SCALE 默认为0.1f，CardConfig.DEFAULT_TRANSLATE_Y 默认为14
                    recyclerViewForPosition.setScaleX(0);
                    recyclerViewForPosition.setScaleY(0);
                    recyclerViewForPosition.setTranslationY((position - 1) * recyclerViewForPosition.getMeasuredHeight() / CardConfig.DEFAULT_TRANSLATE_Y);
                } else if (position > 1) {
                    recyclerViewForPosition.setScaleX(1 - (position - 1) * (CardConfig.DEFAULT_SCALE));
                    recyclerViewForPosition.setScaleY(1 - (position - 1) * (CardConfig.DEFAULT_SCALE));
                    recyclerViewForPosition.setTranslationY((position - 1) * recyclerViewForPosition.getMeasuredHeight() / CardConfig.DEFAULT_TRANSLATE_Y);
                }
            }
        } else if (itemCount < showCount + 2 && itemCount > 3) {
            // 当数据源个数小于或等于最大显示数时，和上面的代码差不多

            for (int position = itemCount - 1; position >= 0; position--) {
                final View recyclerViewForPosition = recycler.getViewForPosition(position);
                recyclerViewForPosition.setScaleX(1);
                recyclerViewForPosition.setScaleY(1);
                recyclerViewForPosition.setTranslationY(0);
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
                if (position > 1) {
                    recyclerViewForPosition.setScaleX(1 - (position - 1) * CardConfig.DEFAULT_SCALE);
                    recyclerViewForPosition.setScaleY(1 - (position - 1) * CardConfig.DEFAULT_SCALE);
                    recyclerViewForPosition.setTranslationY((position - 1) * recyclerViewForPosition.getMeasuredHeight() / CardConfig.DEFAULT_TRANSLATE_Y);
                }
            }
        }else {
            for (int position = itemCount - 1; position >= 0; position--) {
                final View recyclerViewForPosition = recycler.getViewForPosition(position);
                recyclerViewForPosition.setScaleX(1);
                recyclerViewForPosition.setScaleY(1);
                recyclerViewForPosition.setTranslationY(0);
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
                    recyclerViewForPosition.setScaleX(1 - (position ) * CardConfig.DEFAULT_SCALE);
                    recyclerViewForPosition.setScaleY(1 - (position ) * CardConfig.DEFAULT_SCALE);
                    recyclerViewForPosition.setTranslationY((position) * recyclerViewForPosition.getMeasuredHeight() / CardConfig.DEFAULT_TRANSLATE_Y);
                }
            }
        }
    }


}
