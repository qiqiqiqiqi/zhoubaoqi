package com.qi.customview.view.rect;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.qi.customview.R;

/**
 * Created by 13324 on 2016/9/29.
 */
public class ChartViewItemRect extends View {
    private Context mContext;
    private int mColorDefaut;
    private int mColorStroke;
    private int mColorCheck;
    private int mWidth;
    private int mHeight;
    private float mItemValue;
    private boolean mIsCheck;

    public ChartViewItemRect(Context context) {
        super(context);
        initColor(context);
    }

    public ChartViewItemRect(Context context, AttributeSet attrs) {
        super(context, attrs);
        initColor(context);
    }


    public ChartViewItemRect(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initColor(context);
    }

    private void initColor(Context context) {
        mContext = context;
        mColorDefaut = context.getResources().getColor(R.color.colorDefault);
        mColorStroke = context.getResources().getColor(R.color.colorStroke);
        mColorCheck = context.getResources().getColor(R.color.colorCheck);

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
        setMeasuredDimension(width_size, height_size);
        mWidth = getMeasuredWidth() - getPaddingLeft() - getPaddingRight();
        mHeight = getMeasuredHeight() - getPaddingTop() - getPaddingBottom();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mItemValue < 0) {
            return;
        }
        Path path = new Path();
        path.moveTo(0, (1 - mItemValue) * mHeight);
        path.lineTo(mWidth, (1 - mItemValue) * mHeight);
        path.lineTo(mWidth, mHeight);
        path.lineTo(0, mHeight);

        if (mIsCheck) {
            drawCheckItem(canvas, path);
        } else {
            drawDefaultItem(canvas, path);
        }


    }

    private void drawDefaultItem(Canvas canvas, Path path) {
        Paint paint = new Paint();
        paint.setColor(mColorDefaut);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawPath(path, paint);
        paint.setColor(mColorStroke);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawPath(path, paint);

    }

    private void drawCheckItem(Canvas canvas, Path path) {
        Paint paint = new Paint();
        paint.setColor(mColorCheck);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawPath(path, paint);
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

    /**
     * item居中时设置为选中
     *
     * @param check
     */
    public void setCheck(boolean check) {
        mIsCheck = check;
        invalidate();
    }
}
