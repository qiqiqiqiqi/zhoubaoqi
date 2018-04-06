package com.qi.customview.view.scaleview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.qi.customview.DisplayUtils;
import com.qi.customview.R;

/**
 * Created by baoqi on 2018/03/27.
 */
public class ScaleViewItem extends View {
    private Context mContext;
    private int mColorDefaut;
    private int mColorStroke;
    private int mColorCheck;
    private int mWidth;
    private int mHeight;
    private int mItemValue;
    private boolean mIsCheck;
    private int srcollState;

    public ScaleViewItem(Context context) {
        super(context);
        initColor(context);
    }

    public ScaleViewItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        initColor(context);
    }


    public ScaleViewItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initColor(context);
    }

    private void initColor(Context context) {
        mContext = context;
        mColorDefaut = Color.parseColor("#9b9b9b");
        mColorStroke = context.getResources().getColor(R.color.colorStroke);
        mColorCheck = Color.parseColor("#00c6b8");

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
            width_size = Math.min(4, MeasureSpec.getSize(widthMeasureSpec));
        }
        if (height_mode == MeasureSpec.EXACTLY) {
            height_size = MeasureSpec.getSize(heightMeasureSpec);
        } else if (height_mode == MeasureSpec.AT_MOST) {
            height_size = Math.min(80, MeasureSpec.getSize(heightMeasureSpec));
        }
        setMeasuredDimension(width_size, height_size);
        mWidth = getMeasuredWidth() /*- getPaddingLeft() - getPaddingRight()*/;
        mHeight = getMeasuredHeight()/* - getPaddingTop() - getPaddingBottom()*/;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mIsCheck) {
            drawCheckItem(canvas);
        } else {
            drawDefaultItem(canvas);
        }
    }

    private void drawDefaultItem(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(mColorDefaut);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(DisplayUtils.dipToPx(mContext, 2.0f));
        paint.setStrokeCap(Paint.Cap.ROUND);
        canvas.drawLine(mWidth / 2, mHeight, mWidth / 2, mHeight - DisplayUtils.dipToPx(mContext, 8), paint);
        paint.setTextSize(DisplayUtils.dipToPx(mContext, 12));
        float measureText = paint.measureText(mItemValue + "");
        canvas.drawText(mItemValue + "", mWidth / 2 - measureText / 2, mHeight - DisplayUtils.dipToPx(mContext, 14), paint);
    }

    private void drawCheckItem(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(mColorCheck);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(4);
        paint.setStrokeCap(Paint.Cap.ROUND);
//        if (!(srcollState == RecyclerView.SCROLL_STATE_DRAGGING || srcollState == RecyclerView.SCROLL_STATE_SETTLING)) {
//            canvas.drawLine(mWidth / 2, mHeight, mWidth / 2, mHeight - 40, paint);
//        }
        paint.setTextSize(DisplayUtils.dipToPx(mContext, 20));
        float measureText = paint.measureText(mItemValue + "");
        canvas.drawText(mItemValue + "", mWidth / 2 - measureText / 2, mHeight - DisplayUtils.dipToPx(mContext, 18), paint);
    }

    /**
     * 设置item对应的数值
     */
    public void setItemValue(int value) {
        Log.d("ScaleViewItem", "value=" + value);
        mItemValue = value;
        invalidate();
    }

    public int getItemValue() {
        return mItemValue;
    }

    /**
     * item居中时设置为选中
     *
     * @param check
     */
    public void setCheck(boolean check, int srcollState) {

        this.srcollState = srcollState;
        mIsCheck = check;
        invalidate();
    }

    public boolean getCheck(int itemValue) {
        return mItemValue == itemValue;
    }

}
