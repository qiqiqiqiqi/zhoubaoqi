package com.qi.customview.view.point;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.qi.customview.DisplayUtils;


/**
 * Created by feng on 2018/4/1.
 */

public class PointChartViewItem extends View {
    public static final int MODE_DAY = 0;
    public static final int MODE_WEEK = 1;
    public static final int MODE_MONTH = 2;
    public static final int MODE_YEAR = 3;
    private int current_mode = MODE_DAY;
    private static final String TAG = PointChartViewItem.class.getSimpleName();
    private Context mContext;
    private int mWidth;
    private int mHeight;
    private float mItemValue;
    private boolean mIsCheck;
    private Paint mPaint;
    private float normalRadius, checkRadius;
    private int itemWidth;
    private Point mPoint;

    public PointChartViewItem(Context context) {
        this(context, null);
    }

    public PointChartViewItem(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PointChartViewItem(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mContext = context;
        normalRadius = DisplayUtils.dipToPx(context, 2.5f);
        checkRadius = DisplayUtils.dipToPx(context, 5.5f);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
        //  mPaint.setStrokeWidth(DisplayUtils.dipToPx(context, 2));
        itemWidth = DisplayUtils.dipToPx(mContext, 2);
        mPoint = new Point();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width_mode = MeasureSpec.getMode(widthMeasureSpec);
        int height_mode = MeasureSpec.getMode(heightMeasureSpec);
        int width_size = 0;
        int height_size = 0;
        if (width_mode == MeasureSpec.EXACTLY) {
            width_size = MeasureSpec.getSize(widthMeasureSpec);
        } else if (width_mode == MeasureSpec.AT_MOST) {
            width_size = Math.min(60, MeasureSpec.getSize(widthMeasureSpec));
        }
        if (height_mode == MeasureSpec.EXACTLY) {
            height_size = MeasureSpec.getSize(heightMeasureSpec);
        } else if (height_mode == MeasureSpec.AT_MOST) {
            height_size = Math.min(200, MeasureSpec.getSize(heightMeasureSpec));
        }
        setMeasuredDimension(width_size, height_size);
        mWidth = getMeasuredWidth() /*- getPaddingLeft() - getPaddingRight()*/;
        mHeight = getMeasuredHeight() /*- getPaddingTop() - getPaddingBottom()*/;
        Log.d(TAG, "onMeasure():mWidth=" + mWidth + ",mHeight=" + mHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Log.d(TAG, "onDraw():mWidth=" + mWidth + ",mHeight=" + mHeight);
        super.onDraw(canvas);
        caculatePosition(mItemValue);
        if (mIsCheck) {
            drawCircle(canvas, mPoint, (int) checkRadius);
        } else {
            drawCircle(canvas, mPoint, (int) normalRadius);
        }
    }


    private void drawCircle(Canvas canvas, Point middlePoint, int radius) {
        LinearGradient linearGradient = new LinearGradient(mWidth / 2, mHeight - getPaddingBottom(), mWidth / 2, getPaddingTop(),
                new int[]{Color.parseColor("#7bad86"), Color.parseColor("#fff1f5"), Color.parseColor("#f2bbca"), Color.parseColor("#c65d7a"), Color.parseColor("#a0304f")},
                null, Shader.TileMode.CLAMP);
        mPaint.setShader(linearGradient);
        canvas.drawCircle(middlePoint.x, middlePoint.y, radius, mPaint);
        mPaint.setShader(null);
    }

    /**
     * 设置item对应的数值
     *
     * @param itemValue
     */
    public void setItemValue(float itemValue) {
        Log.d(TAG, "itemValues=" + itemValue);
        if (itemValue < 0) {
            return;
        }
        mItemValue = itemValue;

        invalidate();
    }

    private void caculatePosition(float itemValue) {
        int height = mHeight - getPaddingBottom() - getPaddingTop();
        int spaceHeight = height / 4;
        mPoint.x = mWidth / 2;
        if (itemValue == 0) {//
            mPoint.y = mHeight - getPaddingBottom();
        } else if (itemValue > 0 && itemValue < 0.25f) {
            mPoint.y = mHeight - getPaddingBottom() - spaceHeight;
        } else if (itemValue >= 0.25f && itemValue < 0.5f) {
            mPoint.y = mHeight - getPaddingBottom() - 2 * spaceHeight;
        } else if (itemValue >= 0.5f && itemValue < 0.75f) {
            mPoint.y = mHeight - getPaddingBottom() - 3 * spaceHeight;
        } else if (itemValue >= 0.75f) {
            mPoint.y = mHeight - getPaddingBottom() - 4 * spaceHeight;
        }
        Log.d(TAG, "caculatePosition():mWidth=" + mWidth + ",mHeight=" + mHeight + ",mPoint.x=" + mPoint.x + ",mPoint.y=" + mPoint.y);
    }

    public float getItemValue() {
        return mItemValue;
    }

    /**
     * item居中时设置为选中
     *
     * @param check
     */
    public void setCheck(boolean check) {
        mIsCheck = check;
        invalidate();
    }

    public void setMode(int mode) {
        if (mode == current_mode) {
            return;
        }
        current_mode = mode;
        switch (mode) {
            case MODE_DAY:
                itemWidth = DisplayUtils.dipToPx(mContext, 2);

                break;
            case MODE_WEEK:
                itemWidth = DisplayUtils.dipToPx(mContext, 6);

                break;
            case MODE_MONTH:
                itemWidth = DisplayUtils.dipToPx(mContext, 1.5f);

                break;
            case MODE_YEAR:
                itemWidth = DisplayUtils.dipToPx(mContext, 4);
                break;
        }

        invalidate();
    }

    public int getBottomPadding() {
        return getPaddingBottom();
    }

}
