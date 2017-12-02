package study.qi.com.tantan.manager;

import android.graphics.Canvas;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;

import java.util.List;

/**
 * Created by feng on 2017/4/23.
 */

public class CardCallback extends ItemTouchHelper.Callback {
    private static final String TAG = CardCallback.class.getSimpleName();
    private OnSwipedListener mOnSwipedListener;
    private RecyclerView.Adapter mAdapter;
    private List<Integer> mDatas;

    public CardCallback(RecyclerView.Adapter adapter, List<Integer> datas) {
        mAdapter = adapter;
        mDatas = datas;
    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        int dragFlags = 0;
        int swipeFlags = 0;
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof CardLayoutManager) {
            swipeFlags = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
        }
        return makeMovementFlags(dragFlags, swipeFlags);
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public boolean isItemViewSwipeEnabled() {
        return false;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        viewHolder.itemView.setOnTouchListener(null);
        int adapterPosition = viewHolder.getAdapterPosition();
        mDatas.remove(adapterPosition);
        mAdapter.notifyDataSetChanged();
        if (mDatas != null && mDatas.isEmpty()) {
            if (mOnSwipedListener != null) {
                mOnSwipedListener.onSwipedClear();
            }
        }
    }

    @Override
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        super.clearView(recyclerView, viewHolder);
        viewHolder.itemView.setRotation(0);
    }

    public interface OnSwipedListener {
        void onSwipeing();

        void onSwiped();

        void onSwipedClear();
    }

    public void setOnSwipedListener(OnSwipedListener onSwipedListener) {
        mOnSwipedListener = onSwipedListener;
    }

    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        View recyclerViewForPosition = viewHolder.itemView;
        if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
            float v = dX / getThreshold(recyclerView, viewHolder);
            if (v > 1) {
                v = 1;
            } else if (v < -1) {
                v = -1;
            }
            float rotate = v * CardConfig.DEFAULT_ROTATE_DEGREE;
            recyclerViewForPosition.setRotation(rotate);
            int itemCount = recyclerView.getChildCount();//在cardlayoutmanager中通过addview添加了多少个view这里就是多少个
            Log.d(TAG, "itemCount=" + itemCount);
            int showCount = CardConfig.DEFAULT_SHOW_ITEM;
            if (itemCount > showCount) {
                //在cardlayoutmanager中通过addview
                for (int position = 1; position < itemCount - 1; position++) {//最上层的view和最底层（动画的连贯性）的view不做处理
                    View view = recyclerView.getChildAt(position);
                    int index = itemCount - 1 - position;
                    view.setScaleX(1 - index * CardConfig.DEFAULT_SCALE + Math.abs(v) * CardConfig.DEFAULT_SCALE);
                    view.setScaleY(1 - index * CardConfig.DEFAULT_SCALE + Math.abs(v) * CardConfig.DEFAULT_SCALE);
                    view.setTranslationY((index - Math.abs(v)) * view.getMeasuredHeight() / CardConfig.DEFAULT_TRANSLATE_Y);
                }
            } else {
                for (int position = 0; position < itemCount - 1; position++) {//最上层的view不做处理
                    View view = recyclerView.getChildAt(position);
                    int index = itemCount - 1 - position;
                    view.setScaleX(1 - index * CardConfig.DEFAULT_SCALE + Math.abs(v) * CardConfig.DEFAULT_SCALE);
                    view.setScaleY(1 - index * CardConfig.DEFAULT_SCALE + Math.abs(v) * CardConfig.DEFAULT_SCALE);
                    view.setTranslationY((index - Math.abs(v)) * view.getMeasuredHeight() / CardConfig.DEFAULT_TRANSLATE_Y);
                }
            }

        }
    }

    private float getThreshold(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        return recyclerView.getWidth() * getSwipeThreshold(viewHolder);
    }
}
