package com.qi.customview.view.line;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PorterDuffXfermode;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.qi.customview.DisplayUtils;


/**
 * Created by feng on 2018/4/1.
 */

public class LineChartViewItem extends View {
    public static final int MODE_DAY = 0;
    public static final int MODE_WEEK = 1;
    public static final int MODE_MONTH = 2;
    public static final int MODE_YEAR = 3;
    private int current_mode = MODE_DAY;
    private static final String TAG = LineChartViewItem.class.getSimpleName();
    private Context mContext;
    private int mWidth;
    private int mHeight;
    private float[] mBottomItemValues, mTopItemValues;
    private boolean mIsCheck;
    private Paint mPaint;
    private float normalRadius, checkRadius;
    private int itemWidth;
    private PopupWindow mPopupWindow;
    private TextView mTextView;
    private boolean showPopu;

    public LineChartViewItem(Context context) {
        this(context, null);
    }

    public LineChartViewItem(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LineChartViewItem(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
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
        mPaint.setStrokeWidth(DisplayUtils.dipToPx(context, 2));
        itemWidth = DisplayUtils.dipToPx(mContext, 2);
        mBottomItemValues = new float[3];
        mTextView = new TextView(mContext);
        mTextView.setBackgroundColor(Color.parseColor("#ff0000"));
        mPopupWindow = new PopupWindow(mTextView, 100, 100);
        mPopupWindow.setFocusable(false);
        mPopupWindow.setOutsideTouchable(false);
        // 如果不设置PopupWindow的背景，有些版本就会出现一个问题：无论是点击外部区域还是Back键都无法dismiss弹框
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
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
        mWidth = getMeasuredWidth() /*- getPaddingLeft() - getPaddingRight()*/;
        mHeight = getMeasuredHeight() /*- getPaddingTop() - getPaddingBottom()*/;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawTopPath(canvas);
        drawBottomPath(canvas);

    }

    private void drawBottomPath(Canvas canvas) {
        mPaint.setXfermode(new PorterDuffXfermode(
                android.graphics.PorterDuff.Mode.SRC_ATOP));//取下层图像非交集部门与上层图像交集部门
        Path bottomPath = new Path();
        LinearGradient linearGradient = new LinearGradient(mWidth / 2, 0, mWidth / 2, mHeight,
                new int[]{Color.parseColor("#99B8E986"), Color.parseColor("#ffffffff")},
                null, Shader.TileMode.CLAMP);
        mPaint.setShader(linearGradient);
        Point startPoint = new Point((int) (mBottomItemValues[0] < 0 ? (mWidth + 0.5f) / 2 : (-mWidth + 0.5f) / 2), (int) (mBottomItemValues[0] < 0 ? (mHeight * (1 - mBottomItemValues[1])) + 0.5f : (mHeight * (1 - mBottomItemValues[0])) + 0.5f));
        Point middlePoint = new Point((int) ((mWidth + 0.5f) / 2), (int) (mHeight * (1 - mBottomItemValues[1]) + 0.5f));
        Point endPoint = new Point((int) (mBottomItemValues[2] < 0 ? (mWidth + 0.5f) / 2 : mWidth + (mWidth + 0.5f) / 2), (int) (mBottomItemValues[2] < 0 ? mHeight * (1 - mBottomItemValues[1]) + 0.5f : mHeight * (1 - mBottomItemValues[2]) + 0.5f));

        bottomPath.moveTo(startPoint.x, startPoint.y);
        bottomPath.lineTo(middlePoint.x, middlePoint.y);
        bottomPath.lineTo(endPoint.x, endPoint.y);
        bottomPath.lineTo(endPoint.x, mHeight);
        bottomPath.lineTo(startPoint.x, mHeight);
        bottomPath.close();
        canvas.drawPath(bottomPath, mPaint);
        mPaint.setShader(null);
        if (mBottomItemValues[0] > 0) {
            mPaint.setColor(Color.parseColor("#B8E986"));
            canvas.drawLine(startPoint.x, startPoint.y, middlePoint.x, middlePoint.y, mPaint);
        }
        if (mBottomItemValues[2] > 0) {
            mPaint.setColor(Color.parseColor("#B8E986"));
            canvas.drawLine(middlePoint.x, middlePoint.y, endPoint.x, endPoint.y, mPaint);
        }
        if (mIsCheck) {
            drawCircle(canvas, middlePoint, (int) checkRadius, new int[]{Color.parseColor("#B8E986"), Color.parseColor("#A1D868")});
        } else {
            drawCircle(canvas, middlePoint, (int) normalRadius, new int[]{Color.parseColor("#B8E986"), Color.parseColor("#A1D868")});
        }

    }

    private void drawTopPath(Canvas canvas) {

        mPaint.setXfermode(new PorterDuffXfermode(
                android.graphics.PorterDuff.Mode.SRC_ATOP));//取下层图像非交集部门与上层图像交集部门
        Path bottomPath = new Path();
        LinearGradient linearGradient = new LinearGradient(mWidth / 2, 0, mWidth / 2, mHeight,
                new int[]{Color.parseColor("#9943E5DE"), Color.parseColor("#ffffffff")},
                null, Shader.TileMode.CLAMP);
        mPaint.setShader(linearGradient);
        Point startPoint = new Point((int) (mTopItemValues[0] < 0 ? (mWidth + 0.5f) / 2 : (-mWidth + 0.5f) / 2), (int) (mTopItemValues[0] < 0 ? (mHeight * (1 - mTopItemValues[1])) + 0.5f : (mHeight * (1 - mTopItemValues[0])) + 0.5f));
        Point middlePoint = new Point((int) ((mWidth + 0.5f) / 2), (int) (mHeight * (1 - mTopItemValues[1]) + 0.5f));
        Point endPoint = new Point((int) (mTopItemValues[2] < 0 ? (mWidth + 0.5f) / 2 : mWidth + (mWidth + 0.5f) / 2), (int) (mTopItemValues[2] < 0 ? mHeight * (1 - mTopItemValues[1]) + 0.5f : mHeight * (1 - mTopItemValues[2]) + 0.5f));

        bottomPath.moveTo(startPoint.x, startPoint.y);
        bottomPath.lineTo(middlePoint.x, middlePoint.y);
        bottomPath.lineTo(endPoint.x, endPoint.y);
        bottomPath.lineTo(endPoint.x, mHeight);
        bottomPath.lineTo(startPoint.x, mHeight);
        bottomPath.close();
        canvas.drawPath(bottomPath, mPaint);
        mPaint.setShader(null);
        if (mTopItemValues[0] > 0) {
            mPaint.setColor(Color.parseColor("#43E5DE"));
            canvas.drawLine(startPoint.x, startPoint.y, middlePoint.x, middlePoint.y, mPaint);
        }
        if (mTopItemValues[2] > 0) {
            mPaint.setColor(Color.parseColor("#43E5DE"));
            canvas.drawLine(middlePoint.x, middlePoint.y, endPoint.x, endPoint.y, mPaint);
        }
        if (mIsCheck) {
            drawCircle(canvas, middlePoint, (int) checkRadius, new int[]{Color.parseColor("#43E5DE"), Color.parseColor("#20C6BA")});
        } else {
            drawCircle(canvas, middlePoint, (int) normalRadius, new int[]{Color.parseColor("#43E5DE"), Color.parseColor("#20C6BA")});
        }

    }

    private void drawCircle(Canvas canvas, Point middlePoint, int radius, int[] colos) {
        RadialGradient radialGradient = new RadialGradient(middlePoint.x, middlePoint.y, radius,
                colos,
                null, Shader.TileMode.CLAMP);
        mPaint.setShader(radialGradient);
        canvas.drawCircle(middlePoint.x, middlePoint.y, radius, mPaint);
        mPaint.setShader(null);
    }

    /**
     * 设置item对应的数值
     *
     * @param itemValues
     */
    public void setItemValue(float[] itemValues) {
        Log.d(TAG, "itemValues={" + itemValues[0] + "," + itemValues[1] + "," + itemValues[2] + "}");
        if (itemValues[1] < 0) {
            return;
        }
        mBottomItemValues = itemValues;
        mTopItemValues = new float[]{itemValues[0] + 0.15f, itemValues[1] + 0.15f, itemValues[2] + 0.15f};
        invalidate();
    }

    public float getItemValue() {
        return mBottomItemValues[1];
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

    public void setShowPopu(boolean showPopu) {
        this.showPopu = showPopu;
    }

    public void setContent(String content) {
        mTextView.setText(content);
    }

    public void showBottomPopu() {
        if (showPopu) {

            int[] location = new int[2];
            this.getLocationOnScreen(location);
            if (!mPopupWindow.isShowing()) {
                mPopupWindow.showAtLocation(mTextView, Gravity.NO_GRAVITY, location[0] + getMeasuredWidth() / 2 - mTextView.getWidth() / 2, getMeasuredHeight() + location[1]);
            } else {
                mPopupWindow.update(location[0] + getMeasuredWidth() / 2 - mTextView.getWidth() / 2, getMeasuredHeight() + location[1], -1, -1, false);
            }
        } else {
            mPopupWindow.dismiss();
        }
    }
}
