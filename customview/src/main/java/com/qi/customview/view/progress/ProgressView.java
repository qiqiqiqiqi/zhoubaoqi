package com.qi.customview.view.progress;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by feng on 2018/5/21.
 */

public class ProgressView extends View {

    private int mWidth;
    private int mHeight;
    private int mRaduis;
    private Paint mPaint;
    private int progress=15;
    private int maxProgress;
    private int dtAngle = 5;
    private int mAngleSum;

    public ProgressView(Context context) {
        this(context, null);
    }

    public ProgressView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ProgressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        dtAngle = 10;
        mAngleSum = 360 - 4 * dtAngle*2;
        maxProgress = 20;
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(12);
        mPaint.setStrokeCap(Paint.Cap.ROUND);


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
            width_size = Math.min(160, MeasureSpec.getSize(widthMeasureSpec));
        }
        if (height_mode == MeasureSpec.EXACTLY) {
            height_size = MeasureSpec.getSize(heightMeasureSpec);
        } else if (height_mode == MeasureSpec.AT_MOST) {
            height_size = Math.min(160, MeasureSpec.getSize(heightMeasureSpec));
        }
        setMeasuredDimension(width_size, height_size);
        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();
        mRaduis = (mWidth - getPaddingLeft() - getPaddingRight()) / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawBackground(canvas);
        drawProgress(canvas);
    }

    private void drawProgress(Canvas canvas) {
        mPaint.setColor(Color.parseColor("#00B2BF"));
        int progressAngle = progress * mAngleSum / 20;
        int i = mAngleSum / 4;
        if (progressAngle >= 0 && progressAngle <= i) {
            drawAcr(canvas, -90 + dtAngle, progressAngle);
        } else if (progressAngle > i && progressAngle <= 2 * i) {
            drawAcr(canvas, -90 + dtAngle, i);
            drawAcr(canvas, 0 + dtAngle, progressAngle - i);
        } else if (progressAngle > 2 * i && progressAngle <= i * 3) {
            drawAcr(canvas, -90 + dtAngle, i);
            drawAcr(canvas, 0 + dtAngle, i);
            drawAcr(canvas, 90 + dtAngle, progressAngle - 2 * i);
        } else if (progressAngle > 3 * i && progressAngle <= i * 4) {
            drawAcr(canvas, -90 + dtAngle, i);
            drawAcr(canvas, 0 + dtAngle, i);
            drawAcr(canvas, 90 + dtAngle, i);
            drawAcr(canvas, 180 + dtAngle, progressAngle - 3 * i);
        }

    }

    private void drawBackground(Canvas canvas) {
        mPaint.setColor(Color.parseColor("#66ffffff"));
        int i1 = mAngleSum / 4;
        for (int i = 0; i < 4; i++) {
            drawAcr(canvas, -90 + i * 90 + dtAngle, i1);
        }
    }

    private void drawAcr(Canvas canvas, int startAnge, int sweepAngle) {

        RectF rectF = new RectF(getPaddingLeft(), getPaddingTop(), mWidth - getPaddingRight(), mHeight - getPaddingBottom());
        canvas.drawArc(rectF, startAnge, sweepAngle, false, mPaint);
    }


}
