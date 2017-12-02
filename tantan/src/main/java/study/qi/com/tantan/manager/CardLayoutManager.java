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

public class CardLayoutManager extends RecyclerView.LayoutManager {
    private static final String TAG = CardLayoutManager.class.getSimpleName();
    private RecyclerView mRecyclerView;
    private ItemTouchHelper mItemTouchHelper;

    public CardLayoutManager(RecyclerView recyclerView, ItemTouchHelper itemTouchHelper) {
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
//        detachAndScrapAttachedViews(recycler);//在重新布局之前先回收之前的view
        removeAndRecycleAllViews(recycler);
        int itemCount = getItemCount();
        int showCount = CardConfig.DEFAULT_SHOW_ITEM;
        if (itemCount > showCount) {
            for (int position = showCount; position >= 0; position--) {
                final View recyclerViewForPosition = recycler.getViewForPosition(position);//从recycler中获得view
                addView(recyclerViewForPosition);
                measureChildWithMargins(recyclerViewForPosition, 200, 400);
                int withSpace = getWidth() - getDecoratedMeasuredWidth(recyclerViewForPosition);
                int heightSpace = getHeight() - getDecoratedMeasuredHeight(recyclerViewForPosition);
                Log.d(TAG, "withSpace=" + withSpace + ",heightSpace=" + heightSpace);
                layoutDecoratedWithMargins(recyclerViewForPosition, withSpace / 2, heightSpace / 2, withSpace / 2 + recyclerViewForPosition.getMeasuredWidth(), heightSpace / 2 + recyclerViewForPosition.getMeasuredHeight());
                if (position == showCount) {
                    recyclerViewForPosition.setScaleX(1 - (position - 1) * CardConfig.DEFAULT_SCALE);
                    recyclerViewForPosition.setScaleY(1 - (position - 1) * CardConfig.DEFAULT_SCALE);
                    recyclerViewForPosition.setTranslationY((position - 1) * recyclerViewForPosition.getMeasuredHeight() / CardConfig.DEFAULT_TRANSLATE_Y);
                } else if (position > 0) {
                    recyclerViewForPosition.setScaleX(1 - (position) * CardConfig.DEFAULT_SCALE);
                    recyclerViewForPosition.setScaleY(1 - (position) * CardConfig.DEFAULT_SCALE);
                    recyclerViewForPosition.setTranslationY((position) * recyclerViewForPosition.getMeasuredHeight() / CardConfig.DEFAULT_TRANSLATE_Y);
                } else {
                    recyclerViewForPosition.setOnTouchListener(new View.OnTouchListener() {
                        @Override
                        public boolean onTouch(View v, MotionEvent event) {
                            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                                mItemTouchHelper.startSwipe(mRecyclerView.getChildViewHolder(v));
                            }
                            return false;
                        }
                    });
                }
            }
        } else {
            for (int position = itemCount - 1; position >= 0; position--) {
                final View recyclerViewForPosition = recycler.getViewForPosition(position);
                addView(recyclerViewForPosition);
                measureChildWithMargins(recyclerViewForPosition, 200, 400);
                int withSpace = getWidth() - recyclerViewForPosition.getMeasuredWidth();
                int heightSpace = getHeight() - recyclerViewForPosition.getMeasuredHeight();
                Log.d(TAG, "withSpace=" + withSpace + ",heightSpace=" + heightSpace);
                layoutDecorated(recyclerViewForPosition, withSpace / 2, heightSpace / 2, withSpace / 2 + recyclerViewForPosition.getMeasuredWidth(), heightSpace / 2 + recyclerViewForPosition.getMeasuredHeight());
                if (position > 0) {
                    recyclerViewForPosition.setScaleX(1 - (position) * CardConfig.DEFAULT_SCALE);
                    recyclerViewForPosition.setScaleY(1 - (position) * CardConfig.DEFAULT_SCALE);
                    recyclerViewForPosition.setTranslationY((position) * recyclerViewForPosition.getMeasuredHeight() / CardConfig.DEFAULT_TRANSLATE_Y);
                } else {
                    recyclerViewForPosition.setOnTouchListener(new View.OnTouchListener() {
                        @Override
                        public boolean onTouch(View v, MotionEvent event) {
                            if (event.getAction() == MotionEvent.ACTION_DOWN) {
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
