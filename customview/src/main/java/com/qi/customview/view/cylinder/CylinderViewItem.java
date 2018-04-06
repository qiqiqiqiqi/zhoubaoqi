package com.qi.customview.view.cylinder;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.qi.customview.DisplayUtils;
import com.qi.customview.R;

/**
 * Created by baoqi on 2018/3/29.
 */
public class CylinderViewItem extends View {
    private static final String TAG = CylinderViewItem.class.getSimpleName();
    public static final int MODE_DAY = 0;
    public static final int MODE_WEEK = 1;
    public static final int MODE_MONTH = 2;
    public static final int MODE_YEAR = 3;
    private int current_mode = MODE_DAY;
    private Context mContext;
    private int mColorDefaut;
    private int mColorStroke;
    private int mColorCheck;
    private int mWidth;
    private int mHeight;
    private float mItemValue;
    private boolean mIsCheck;
    private int itemWidth;
    private int bottomOffset;

    public CylinderViewItem(Context context) {
        this(context, null);

    }

    public CylinderViewItem(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }


    public CylinderViewItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mContext = context;
        mColorDefaut = context.getResources().getColor(R.color.colorDefault);
        mColorStroke = context.getResources().getColor(R.color.colorStroke);
        mColorCheck = context.getResources().getColor(R.color.colorCheck);
        itemWidth = DisplayUtils.dipToPx(mContext, 2);
        setMode(MODE_WEEK);
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
            height_size = Math.min(240, MeasureSpec.getSize(heightMeasureSpec));
        }
//        width_size = MeasureSpec.getSize(widthMeasureSpec);
//        height_size = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(width_size, height_size);
        mWidth = getMeasuredWidth() /*- getPaddingLeft() - getPaddingRight()*/;
        mHeight = getMeasuredHeight() /*- getPaddingTop() - getPaddingBottom()*/;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mItemValue <= 0) {
            return;
        }

        if (mIsCheck) {
            drawCheckItem(canvas);
        } else {
            drawDefaultItem(canvas);
        }


    }

    private void drawDefaultItem(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(mColorDefaut);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(DisplayUtils.dipToPx(mContext, itemWidth));
        paint.setStrokeCap(Paint.Cap.ROUND);
        canvas.drawLine(mWidth / 2, mHeight - getPaddingBottom() - bottomOffset, mWidth / 2, (1 - mItemValue) * (mHeight - getPaddingBottom() - getPaddingTop()) + getPaddingBottom() - bottomOffset, paint);


    }

    private void drawCheckItem(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(mColorCheck);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(DisplayUtils.dipToPx(mContext, itemWidth));
        paint.setStrokeCap(Paint.Cap.ROUND);
        float endY = (1 - mItemValue) * (mHeight - getPaddingBottom() - getPaddingTop()) + getPaddingBottom();
        LinearGradient linearGradient = new LinearGradient(mWidth / 2, mHeight - getPaddingBottom(), mWidth / 2, endY, new
                int[]{Color.parseColor("#00e7b7"), Color.parseColor("#1cefff")},
                null, Shader.TileMode.CLAMP);
        paint.setShader(linearGradient);
        int startY = mHeight - getPaddingBottom() - bottomOffset;
        Log.d(TAG, "drawCheckItem():startY=" + startY + ",endY=" + endY);
        canvas.drawLine(mWidth / 2, startY, mWidth / 2, endY - bottomOffset, paint);

    }

    /**
     * 设置item对应的数值
     *
     * @param percent
     */
    public void setItemValue(float percent) {
        Log.d("ChartViewItemRect", "percent=" + percent);
        if (percent < 0) {
            return;
        }
        mItemValue = percent;
        invalidate();
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

    public boolean getCheck() {
        return mIsCheck;
    }

    public void setMode(int mode) {
        if (mode == current_mode) {
            return;
        }
        current_mode = mode;
        switch (mode) {
            case MODE_DAY:
                itemWidth = DisplayUtils.dipToPx(mContext, 2);
                bottomOffset = 0;
                break;
            case MODE_WEEK:
                itemWidth = DisplayUtils.dipToPx(mContext, 6);
                bottomOffset = DisplayUtils.dipToPx(mContext, 7);
                break;
            case MODE_MONTH:
                itemWidth = DisplayUtils.dipToPx(mContext, 1.5f);
                bottomOffset = 0;
                break;
            case MODE_YEAR:
                itemWidth = DisplayUtils.dipToPx(mContext, 4);
                bottomOffset = DisplayUtils.dipToPx(mContext, 5f);
                break;
        }

        invalidate();
    }
}
