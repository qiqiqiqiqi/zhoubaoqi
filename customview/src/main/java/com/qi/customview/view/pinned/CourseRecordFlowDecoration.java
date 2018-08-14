package com.qi.customview.view.pinned;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.qi.customview.DisplayUtils;
import com.qi.customview.R;

/**
 * Created by feng on 2018/7/13.
 */
public class CourseRecordFlowDecoration extends RecyclerView.ItemDecoration {
    private static final String TAG = CourseRecordFlowDecoration.class.getSimpleName();
    private Context mContext;
    private int offsetLeft, itemOffset;

    private Paint mPaint;
    private PathEffect mPathEffect;

    public CourseRecordFlowDecoration(Context context) {
        super();
        mContext = context;
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPathEffect = new DashPathEffect(new float[]{30, 30}, 0);
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(DisplayUtils.dipToPx(mContext, 0.75f));
        mPaint.setColor(Color.parseColor("#909399"));
        mPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        //outRect
        int position = parent.getChildAdapterPosition(view);
        if (position == 0) {
            outRect.top = DisplayUtils.dipToPx(mContext, 15);
        } else {
            itemOffset = outRect.top = DisplayUtils.dipToPx(mContext, 10);
        }
        if (position == state.getItemCount() - 1) {
            outRect.bottom = DisplayUtils.dipToPx(mContext, 15);
        } else {
            outRect.bottom = 0;
        }
        offsetLeft = outRect.left = DisplayUtils.dipToPx(mContext, 52);
        outRect.right = DisplayUtils.dipToPx(mContext, 10);
    }

    @Override
    public void onDraw(Canvas canvas, RecyclerView parent, RecyclerView.State state) {
        parent.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        super.onDraw(canvas, parent, state);
        int childCount = parent.getChildCount();
        int lineTopY = 0;
        int lineBottomY = 0;
        for (int i = 0; i < childCount; i++) {
            View view = parent.getChildAt(i);
            TextView timeView = (TextView) view.findViewById(R.id.course_time_day);
            int timeViewTop = timeView.getTop() + view.getTop();
            int timeViewBottom = timeView.getBottom() + view.getTop();
            Log.d(TAG, "onDraw():timeViewTop=" + timeViewTop + ",timeViewBottom=" + timeViewBottom);
            int cy = (timeViewTop + timeViewBottom) / 2;
            mPaint.setColor(Color.parseColor("#909399"));
            canvas.drawCircle(offsetLeft / 2, cy, DisplayUtils.dipToPx(mContext, 4), mPaint);
            Path path = new Path();
            path.moveTo(offsetLeft, cy - 16);
            path.lineTo(offsetLeft, cy + 16);
            path.lineTo(offsetLeft - 16, cy);
            mPaint.setColor(Color.parseColor("#ffffff"));
            canvas.drawPath(path, mPaint);
            int position = parent.getChildAdapterPosition(view);
            if (i == 0) {
                if (position == 0) {
                    lineTopY = cy;
                } else {
                    lineTopY = cy - (view.getHeight() + itemOffset);
                }

            }
            if (i == childCount - 1) {
                if (position == state.getItemCount() - 1) {
                    lineBottomY = cy;
                } else {
                    lineBottomY = cy + (view.getHeight() + itemOffset);
                }

            }
        }
        mPaint.setPathEffect(mPathEffect);
        mPaint.setColor(Color.parseColor("#909399"));
        canvas.drawLine(offsetLeft / 2, lineTopY, offsetLeft / 2, lineBottomY, mPaint);
        mPaint.setPathEffect(null);
    }

    @Override
    public void onDrawOver(Canvas canvas, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(canvas, parent, state);

    }


}
