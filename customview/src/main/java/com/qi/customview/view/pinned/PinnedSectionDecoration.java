package com.qi.customview.view.pinned;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.qi.customview.R;


/**
 * Created by feng on 2017/5/17.
 */

public class PinnedSectionDecoration extends RecyclerView.ItemDecoration {
    private static final String TAG = "PinnedSectionDecoration";
    private int spaceGap;
    private DecorationCallback callback;
    private TextPaint textPaint;
    private Paint paint;
    private int topGap;
    private Paint.FontMetrics fontMetrics;


    public PinnedSectionDecoration(Context context, DecorationCallback decorationCallback) {
        Resources res = context.getResources();
        this.callback = decorationCallback;

        paint = new Paint();
        paint.setColor(res.getColor(R.color.colorPrimary));

        textPaint = new TextPaint();
        textPaint.setTypeface(Typeface.DEFAULT_BOLD);
        textPaint.setAntiAlias(true);
        textPaint.setStrokeWidth(2);
        textPaint.setTextSize(res.getDimensionPixelSize(R.dimen.activity_horizontal_margin));
        textPaint.setColor(Color.parseColor("#b2b2b2"));
        textPaint.getFontMetrics(fontMetrics);
        textPaint.setTextAlign(Paint.Align.LEFT);
        fontMetrics = new Paint.FontMetrics();
        topGap = res.getDimensionPixelSize(R.dimen.activity_horizontal_margin);
        // spaceGap = res.getDimensionPixelSize(R.dimen.activity_horizontal_margin);

    }


    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        int pos = parent.getChildAdapterPosition(view);
        long groupId = callback.getGroupId(pos);
        if (groupId < 0) return;
        if (pos == 0 || isFirstInGroup(pos)) {
            outRect.top = topGap + spaceGap;
        } else {
            outRect.top = 0;
        }
        outRect.bottom = 1;
    }

    /**
     * 需要注意的一点是 getItemOffsets 是针对每一个 ItemView，
     * 而 onDraw 方法却是针对 RecyclerView 本身，所以在 onDraw 方法中需要遍历屏幕上可见的 ItemView，
     * 分别获取它们的位置信息，然后分别的绘制对应的分割线
     * @param c
     * @param parent
     * @param state
     */
    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        //屏幕上可见的 ItemView
        int childCount = parent.getChildCount();
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();

        for (int i = 0; i < childCount - 1; i++) {
            View view = parent.getChildAt(i);
            float top = view.getBottom();
            float bottom = view.getBottom() + 1;
            c.drawRect(left, top, right, bottom, paint);
        }

    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        //item总数
        int itemCount = state.getItemCount();
        //屏幕上可见的 ItemView
        int childCount = parent.getChildCount();

        long preGroupId, groupId = -1;
        for (int i = 0; i < childCount; i++) {

            View view = parent.getChildAt(i);
            int left = parent.getPaddingLeft() + view.getPaddingLeft();
            int right = parent.getWidth();
            int position = parent.getChildAdapterPosition(view);

            preGroupId = groupId;
            groupId = callback.getGroupId(position);
            if (groupId < 0 || groupId == preGroupId) {
                continue;
            }

            String textLine = callback.getGroupFirstLine(position).toUpperCase();
            if (TextUtils.isEmpty(textLine)) {
                continue;
            }

            int viewBottom = view.getBottom();
            Log.d(TAG, "topGap=" + topGap + ",view.getTop()=" + view.getTop() + ",view.getBottom()=" + view.getBottom());
            float rectBottom = Math.max(topGap, view.getTop());

            if (position + 1 < itemCount) { //下一个和当前不一样移动当前
                long nextGroupId = callback.getGroupId(position + 1);
                if (nextGroupId != groupId && viewBottom < rectBottom) {//组内最后一个view进入了header
                    rectBottom = viewBottom;
                }
            }

            c.drawRect(0, rectBottom - topGap, right, rectBottom, paint);
            c.drawText(textLine, left, (rectBottom + (rectBottom - topGap)) / 2 - (textPaint.ascent() + textPaint.descent()) / 2, textPaint);
            c.drawLine(0, rectBottom - topGap, right, rectBottom - topGap + 1, textPaint);
            c.drawLine(0, rectBottom, right, rectBottom + 1, textPaint);
        }

    }

    private boolean isFirstInGroup(int pos) {
        if (pos == 0) {
            return true;
        } else {
            long prevGroupId = callback.getGroupId(pos - 1);
            long groupId = callback.getGroupId(pos);
            return prevGroupId != groupId;
        }
    }

    public interface DecorationCallback {

        long getGroupId(int position);

        String getGroupFirstLine(int position);
    }
}
