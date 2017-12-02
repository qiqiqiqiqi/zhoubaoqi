package com.qi.study.bezierview;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.PathInterpolator;

import com.qi.study.R;


/**
 * 自定义一个点击分裂的view
 * Created by feng on 2017/3/29.
 */

public class SplitView extends View {
    private static final String TAG = SplitView.class.getSimpleName();
    private final int CLICK_LEFT = 0;
    private final int CLICK_MIDDLE = 1;
    private final int CLICK_RIGHT = 2;
    private Paint mPaint;
    private int mWidth;
    private int mHeight;
    private HorizontalLine mTopHorizontalLine;
    private HorizontalLine mBottomHorizontalLine;
    private VerticalLine mLeftVrticalLine;
    private VerticalLine mRightVrticalLine;
    private Path mPath;
    private int mRadius;
    private int mSize_width;
    private int mSize_height;
    private int mMinSize;
    private ValueAnimator mSplitValueAnimator;
    private float mAnimatedValue;
    private boolean isSplit = false;
    private ValueAnimator mValueAnimator;
    private float mSplitAnimatedValue;
    private boolean isLeftRightClickable;
    private float mDownX;
    private float mDownY;
    private ValueAnimator mMergeValueAnimator;
    private String offWrok;
    private String task;
    private DisplayMetrics mMetrics;
    private Bitmap mAdd_bitmap;

    public SplitView(Context context) {
        super(context);
        init(context);
    }

    public SplitView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public SplitView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public void init(Context context) {
        // 获取手机屏幕参数
        mMetrics = getResources().getDisplayMetrics();
        mAdd_bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.clean_order_add);
        //初始化画笔
        offWrok = "下班";
        task = "任务";
        mPaint = new Paint();
        mPaint.setFilterBitmap(true);
        mPaint.setColor(Color.parseColor("#59c3e2"));
        mPaint.setTextSize(16 * mMetrics.scaledDensity);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);


    }

    private void initAnimation() {
        initSplitAnimator();
        initShapeChangeAnimator();

        initMergeAnimator();
    }

    private void initShapeChangeAnimator() {
        mValueAnimator = ValueAnimator.ofFloat(1.0f, 2.0f, 1.0f);
        mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mAnimatedValue = (float) animation.getAnimatedValue();
                Log.d(TAG, "mAnimatedValue=" + mAnimatedValue);
                postInvalidate();
            }
        });
        mValueAnimator.setDuration(1000);
        mValueAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                isLeftRightClickable = false;
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                isLeftRightClickable = true;
                if (isSplit) {
                    postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            isSplit = false;
                            mMergeValueAnimator.start();
                            mValueAnimator.start();
                        }
                    }, 3000);
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                isLeftRightClickable = false;
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                isLeftRightClickable = false;
            }
        });
    }

    private void initSplitAnimator() {
        float v = mWidth / 2 - mMinSize / 2 + 0.0f;
        Log.d(TAG, "initSplitAnimator（）：v=" + v);
        mSplitValueAnimator = ValueAnimator.ofFloat(0.0f, v);
        mSplitValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mSplitAnimatedValue = (float) animation.getAnimatedValue();
                Log.d(TAG, "initSplitAnimator（）：mSplitAnimatedValue=" + mSplitAnimatedValue);
                postInvalidate();
            }
        });

        mSplitValueAnimator.setDuration(1000);


    }

    private void initMergeAnimator() {
        float v = mWidth / 2 - mMinSize / 2 + 0.0f;
        Log.d(TAG, "initMergeAnimator（）：v=" + v);
        mMergeValueAnimator = ValueAnimator.ofFloat(v, 0.0f);
        mMergeValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mSplitAnimatedValue = (float) animation.getAnimatedValue();
                Log.d(TAG, "initMergeAnimator（）：mSplitAnimatedValue=" + mSplitAnimatedValue);
                postInvalidate();
            }
        });
        mMergeValueAnimator.setDuration(1000);
    }

    public void startSplitAnimator() {
        isSplit = true;
        mSplitValueAnimator.start();
        mValueAnimator.start();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int mode_width = MeasureSpec.getMode(widthMeasureSpec);
        int mode_height = MeasureSpec.getMode(heightMeasureSpec);
        if (mode_width == MeasureSpec.EXACTLY) {
            mSize_width = MeasureSpec.getSize(widthMeasureSpec);
        } else {
            mSize_width = Math.min(100, MeasureSpec.getSize(widthMeasureSpec));
        }

        if (mode_height == MeasureSpec.EXACTLY) {
            mSize_height = MeasureSpec.getSize(heightMeasureSpec);
        } else {
            mSize_height = Math.min(100, MeasureSpec.getSize(heightMeasureSpec));
        }
        setMeasuredDimension(mSize_width, mSize_height);
        //计算实际的绘图区域
        mWidth = mSize_width;
        mHeight = mSize_height;
        Log.d(TAG, "mWidth=" + mWidth + ",mHeight=" + mHeight);
        mMinSize = Math.min(mWidth, mHeight);
        mRadius = (mMinSize - getPaddingLeft() - getPaddingRight()) / 2;
        initLine(mRadius);
        initAnimation();
    }

    private void initLine(int radius) {
        mTopHorizontalLine = new HorizontalLine(0, -mMinSize / 2 + getPaddingTop(), radius);
        mBottomHorizontalLine = new HorizontalLine(0, mMinSize / 2 - getPaddingBottom(), radius);
        mLeftVrticalLine = new VerticalLine(-mMinSize / 2 + getPaddingLeft(), 0, radius);
        mRightVrticalLine = new VerticalLine(mMinSize / 2 - getPaddingRight(), 0, radius);

        mPath = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPath.reset();
        drawLeftPath(canvas);
        drawRightPath(canvas);

    }

    private void drawMilddlePath(Canvas canvas) {
        canvas.save();
        // canvas.translate(mWidth / 2, mMinSize / 2);
        canvas.translate(mWidth / 2, mMinSize / 2);
        mPath.moveTo(mTopHorizontalLine.middlePoint.x, mTopHorizontalLine.middlePoint.y);
        mPath.cubicTo(mTopHorizontalLine.rightPoint.x, mTopHorizontalLine.rightPoint.y, mRightVrticalLine.topPoint.x, mRightVrticalLine.topPoint.y, mRightVrticalLine.middlePoint.x, mRightVrticalLine.middlePoint.y);
        mPath.cubicTo(mRightVrticalLine.bottomPoint.x, mRightVrticalLine.bottomPoint.y, mBottomHorizontalLine.rightPoint.x, mBottomHorizontalLine.rightPoint.y, mBottomHorizontalLine.middlePoint.x, mBottomHorizontalLine.middlePoint.y);
        mPath.cubicTo(mBottomHorizontalLine.leftPoint.x, mBottomHorizontalLine.leftPoint.y, mLeftVrticalLine.bottomPoint.x, mLeftVrticalLine.bottomPoint.y, mLeftVrticalLine.middlePoint.x, mLeftVrticalLine.middlePoint.y);
        mPath.cubicTo(mLeftVrticalLine.topPoint.x, mLeftVrticalLine.topPoint.y, mTopHorizontalLine.leftPoint.x, mTopHorizontalLine.leftPoint.y, mTopHorizontalLine.middlePoint.x, mTopHorizontalLine.middlePoint.y);
        canvas.drawPath(mPath, mPaint);
        canvas.restore();
    }

    private void drawLeftPath(Canvas canvas) {
        canvas.save();
        mPath.reset();
        //  canvas.translate(mMinSize / 2, mMinSize / 2);
        mPaint.setColor(Color.parseColor("#59c3e2"));
        setLeftVrticalLineX(-1);
        setRightVrticalLineX(mAnimatedValue);
        canvas.translate(mWidth / 2 - mSplitAnimatedValue, mMinSize / 2);
        mPath.moveTo(mTopHorizontalLine.middlePoint.x, mTopHorizontalLine.middlePoint.y);
        mPath.cubicTo(mTopHorizontalLine.rightPoint.x, mTopHorizontalLine.rightPoint.y, mRightVrticalLine.topPoint.x, mRightVrticalLine.topPoint.y, mRightVrticalLine.middlePoint.x, mRightVrticalLine.middlePoint.y);
        mPath.cubicTo(mRightVrticalLine.bottomPoint.x, mRightVrticalLine.bottomPoint.y, mBottomHorizontalLine.rightPoint.x, mBottomHorizontalLine.rightPoint.y, mBottomHorizontalLine.middlePoint.x, mBottomHorizontalLine.middlePoint.y);
        mPath.cubicTo(mBottomHorizontalLine.leftPoint.x, mBottomHorizontalLine.leftPoint.y, mLeftVrticalLine.bottomPoint.x, mLeftVrticalLine.bottomPoint.y, mLeftVrticalLine.middlePoint.x, mLeftVrticalLine.middlePoint.y);
        mPath.cubicTo(mLeftVrticalLine.topPoint.x, mLeftVrticalLine.topPoint.y, mTopHorizontalLine.leftPoint.x, mTopHorizontalLine.leftPoint.y, mTopHorizontalLine.middlePoint.x, mTopHorizontalLine.middlePoint.y);
        canvas.drawPath(mPath, mPaint);
        mPaint.setColor(Color.WHITE);


        if (mSplitAnimatedValue == 0) {
            canvas.drawBitmap(mAdd_bitmap, -mAdd_bitmap.getScaledHeight(canvas) / 2, -mAdd_bitmap.getScaledHeight(canvas) / 2, mPaint);
        } else {
            float v = mWidth / 2 - mMinSize / 2 + 0.0f;
            mPaint.setTextScaleX((mSplitAnimatedValue / v));
            float measureText = mPaint.measureText(offWrok);
            canvas.drawText(offWrok, -measureText / 2, -(mPaint.ascent() + mPaint.descent()) / 2, mPaint);

        }
        canvas.restore();
    }

    private void drawRightPath(Canvas canvas) {
        canvas.save();
        mPath.reset();
        mPaint.setColor(Color.parseColor("#59c3e2"));
        setLeftVrticalLineX(-mAnimatedValue);
        setRightVrticalLineX(1);
        // canvas.translate(mWidth - mMinSize / 2, mMinSize / 2);
        canvas.translate(mWidth / 2 + mSplitAnimatedValue, mMinSize / 2);
        mPath.moveTo(mTopHorizontalLine.middlePoint.x, mTopHorizontalLine.middlePoint.y);
        mPath.cubicTo(mTopHorizontalLine.rightPoint.x, mTopHorizontalLine.rightPoint.y, mRightVrticalLine.topPoint.x, mRightVrticalLine.topPoint.y, mRightVrticalLine.middlePoint.x, mRightVrticalLine.middlePoint.y);
        mPath.cubicTo(mRightVrticalLine.bottomPoint.x, mRightVrticalLine.bottomPoint.y, mBottomHorizontalLine.rightPoint.x, mBottomHorizontalLine.rightPoint.y, mBottomHorizontalLine.middlePoint.x, mBottomHorizontalLine.middlePoint.y);
        mPath.cubicTo(mBottomHorizontalLine.leftPoint.x, mBottomHorizontalLine.leftPoint.y, mLeftVrticalLine.bottomPoint.x, mLeftVrticalLine.bottomPoint.y, mLeftVrticalLine.middlePoint.x, mLeftVrticalLine.middlePoint.y);
        mPath.cubicTo(mLeftVrticalLine.topPoint.x, mLeftVrticalLine.topPoint.y, mTopHorizontalLine.leftPoint.x, mTopHorizontalLine.leftPoint.y, mTopHorizontalLine.middlePoint.x, mTopHorizontalLine.middlePoint.y);
        canvas.drawPath(mPath, mPaint);
        mPaint.setColor(Color.WHITE);
        //  mPaint.setAlpha((int) (mSplitAnimatedValue / v));

        if (mSplitAnimatedValue == 0) {
            canvas.drawBitmap(mAdd_bitmap, -mAdd_bitmap.getScaledHeight(canvas) / 2, -mAdd_bitmap.getScaledHeight(canvas) / 2, mPaint);
        } else {
            float v = mWidth / 2 - mMinSize / 2 + 0.0f;
            mPaint.setTextScaleX((mSplitAnimatedValue / v));
            float measureText = mPaint.measureText(task);
            canvas.drawText(task, -measureText / 2, -(mPaint.ascent() + mPaint.descent()) / 2, mPaint);

        }
        canvas.restore();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mDownX = event.getX();
                mDownY = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                if (!(isSplit && isLeftRightClickable)) {
                    return true;
                }
                float upX = event.getX();
                float upY = event.getY();
                performClick();
                if (Math.abs(upX - mDownX) < 10 && Math.abs(upY - mDownY) < 10) {
                    float middleX = (mDownX + upX) / 2;
                    float middleY = (mDownY + upY) / 2;
                    if (middleX > mMinSize / 2 - mRadius && middleX < mMinSize / 2 + mRadius && middleY > 0 && middleY < mMinSize) {
                        onClick(CLICK_LEFT);
                    } else if (middleX < mWidth - mMinSize / 2 + mRadius && middleX > mWidth - mMinSize / 2 - mRadius && middleY > 0 && middleY < mMinSize) {
                        onClick(CLICK_RIGHT);
                    } else if (middleY > 0 && middleY < mMinSize) {//TODO
                        onClick(CLICK_MIDDLE);
                    }
                }
                break;
        }
        return true;
    }

    public void onClick(int position) {
        Log.d(TAG, "onClick():position=" + position);
        switch (position) {
            case CLICK_LEFT:
                break;
            case CLICK_MIDDLE:
                break;
            case CLICK_RIGHT:
                break;

        }

    }

    public float getRadius() {
        return mRadius;
    }

    public void setRightVrticalLineX(float x) {
        if (mRightVrticalLine != null) {
            mRightVrticalLine.setX(x * mRadius);
        }

    }

    public void setLeftVrticalLineX(float x) {
        if (mLeftVrticalLine != null) {
            mLeftVrticalLine.setX(x * mRadius);
        }

    }

    public void setTopHorizontalLineY(float y) {
        if (mTopHorizontalLine != null) {
            mTopHorizontalLine.setY(y * mRadius);
        }

    }

    public void setBottomHorizontalLineY(float y) {
        if (mBottomHorizontalLine != null) {
            mBottomHorizontalLine.setY(y * mRadius);
        }

    }
}
