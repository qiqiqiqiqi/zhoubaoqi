package com.qi.study.unlockview;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnticipateInterpolator;

import com.qi.study.R;

/**
 * Created by feng on 2017/3/31.
 */

public class UnLockView extends View implements GestureDetector.OnGestureListener {
    private static final String TAG = UnLockView.class.getSimpleName();
    private Bitmap mKeyBitmap;
    private Bitmap mHoleKeyBitmap;
    private Paint mPaint;
    private int mWidth;
    private int mHeight;
    private int mLeft;
    private int mTop;
    private int mRight;
    private int mBottom;
    private int mProgress;
    private int mKeyBitmapWidth;
    private boolean isDown;
    private String mContent;
    private GestureDetector mGestureDetector;
    private DisplayMetrics mDisplayMetrics;
    private ValueAnimator mProgressValueAnimator;
    private OnUnLockListener mOnUnLockListener;

    public UnLockView(Context context) {
        super(context);
        init(context);
    }

    public UnLockView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public UnLockView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public void init(Context context) {
        //  BD9C7B
        mKeyBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.live_icon_key);
        mKeyBitmap = Bitmap.createScaledBitmap(mKeyBitmap, (int) (mKeyBitmap.getWidth() * 0.5), (int) (mKeyBitmap.getHeight() * 0.5), true);
        mHoleKeyBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.live_icon_keyhole);
        mHoleKeyBitmap = Bitmap.createScaledBitmap(mHoleKeyBitmap, (int) (mHoleKeyBitmap.getWidth() * 0.5), (int) (mHoleKeyBitmap.getHeight() * 0.5), true);

        mContent = getResources().getString(R.string.unlock_tips);///*"右滑网络开锁 >"*/
        initPaint();
        mGestureDetector = new GestureDetector(context, this);
        initAnimator();
    }

    private void initAnimator() {
        mProgressValueAnimator = ValueAnimator.ofInt(mProgress, 0);
        mProgressValueAnimator.setInterpolator(new AnticipateInterpolator());// AnticipateInterpolator
        mProgressValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mProgress = (int) animation.getAnimatedValue();
                invalidate();
            }
        });
    }

    private void startAnimation(int from, int to) {
        Log.d(TAG, "startAnimation():from=" + from + ",to=" + to);
        mProgressValueAnimator.setIntValues(from, to);
        mProgressValueAnimator.start();
    }

    /**
     * 初始化画笔
     */
    private void initPaint() {
        mDisplayMetrics = getResources().getDisplayMetrics();
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(3);
        mPaint.setTextSize(16 * mDisplayMetrics.scaledDensity);
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.parseColor("#BD9C7B"));
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int mode_width = MeasureSpec.getMode(widthMeasureSpec);
        int mode_height = MeasureSpec.getMode(heightMeasureSpec);
        if (mode_width == MeasureSpec.EXACTLY) {
            mWidth = MeasureSpec.getSize(widthMeasureSpec);
        } else {
            mWidth = Math.max(200, MeasureSpec.getSize(widthMeasureSpec));
        }
        if (mode_height == MeasureSpec.EXACTLY) {
            mHeight = MeasureSpec.getSize(heightMeasureSpec);
        } else {
            mHeight = Math.min(100, MeasureSpec.getSize(heightMeasureSpec));
        }
        setMeasuredDimension(mWidth, mHeight);
        mLeft = getPaddingLeft();
        mTop = getPaddingTop();
        mRight = getMeasuredWidth() - getPaddingRight();
        mBottom = getMeasuredHeight() - getPaddingBottom();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawBackground(canvas);
        drawMiddleText(canvas);
        drawRightBitmap(canvas);
        drawUnLockBitmap(canvas, mProgress);
    }

    private void drawBackground(Canvas canvas) {
        canvas.save();
        RectF rect_out = new RectF(mLeft, mTop, mRight, mBottom);
        RectF rect_int = new RectF(mLeft + 2, mTop + 2, mRight - 2, mBottom - 2);
        mPaint.setColor(Color.parseColor("#BD9C7B"));
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawRoundRect(rect_out, 10, 10, mPaint);
        mPaint.setColor(Color.parseColor("#f8f8f8"));
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawRoundRect(rect_int, 10, 10, mPaint);
        canvas.restore();
    }

    private void drawMiddleText(Canvas canvas) {
        canvas.save();
        mPaint.setColor(Color.parseColor("#66868686"));
        float measureText = mPaint.measureText(mContent);
        canvas.drawText(mContent, mWidth * 9 / 16 - measureText / 2, mHeight / 2 - (mPaint.ascent() + mPaint.descent()) / 2, mPaint);
        canvas.restore();

    }

    private void drawRightBitmap(Canvas canvas) {
        canvas.save();
        int scaledHeight = mHoleKeyBitmap.getScaledHeight(canvas);
        int scaledWidth = mHoleKeyBitmap.getScaledWidth(canvas);
        canvas.drawBitmap(mHoleKeyBitmap, mWidth - scaledWidth - 30, mHeight / 2 - scaledHeight / 2, mPaint);
        canvas.restore();
    }

    private void drawUnLockBitmap(Canvas canvas, int progress) {
        canvas.save();
        int scaledHeight = mKeyBitmap.getScaledHeight(canvas);
        mKeyBitmapWidth = mKeyBitmap.getScaledWidth(canvas);
        mPaint.setStyle(Paint.Style.FILL);
        RectF rect_bg_out = new RectF(mDisplayMetrics.scaledDensity * 1 + progress, mTop + mDisplayMetrics.scaledDensity * 1, mKeyBitmapWidth + mDisplayMetrics.scaledDensity * 45 + progress, mBottom - mDisplayMetrics.scaledDensity * 1);
        mPaint.setColor(Color.parseColor("#BD9C7B"));
        canvas.drawRoundRect(rect_bg_out, 10, 10, mPaint);
        RectF rect_bg_in = new RectF(mDisplayMetrics.scaledDensity * 2 + progress, mTop + mDisplayMetrics.scaledDensity * 2, mKeyBitmapWidth + mDisplayMetrics.scaledDensity * 44 + progress, mBottom - mDisplayMetrics.scaledDensity * 2);
        if (isDown) {
            mPaint.setColor(getResources().getColor(R.color.press_bg));
        } else {
            mPaint.setColor(Color.parseColor("#ffffff"));
        }
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawRoundRect(rect_bg_in, 10, 10, mPaint);
        mPaint.setColor(Color.parseColor("#BD9C7B"));
        canvas.drawBitmap(mKeyBitmap, mDisplayMetrics.scaledDensity * 23 + progress, mHeight / 2 - scaledHeight / 2, mPaint);
        canvas.restore();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                isDown = true;
                float end_range = mKeyBitmapWidth + mDisplayMetrics.scaledDensity * 45 + mProgress;
                float start_range = mProgress;
                float downX = event.getX();
                if (!(downX > start_range && downX < end_range)) {
                    return false;
                }
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                isDown = false;
                if (mProgress < (int) (mWidth - mDisplayMetrics.density * (46) - mKeyBitmapWidth) * 2 / 3) {
                    startAnimation(mProgress, 0);
                } else {
                    startAnimation(mProgress, (int) (mWidth - mDisplayMetrics.density * (46) - mKeyBitmapWidth));
                    if (mOnUnLockListener != null) {
                        mOnUnLockListener.onUnLock();
                    }
                }
                break;
            case MotionEvent.ACTION_CANCEL:
                isDown = false;
                break;
        }
        mGestureDetector.onTouchEvent(event);
        return true;
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return true;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        Log.d(TAG, "onScroll():distanceX=" + distanceX + ",mProgress=" + mProgress + ",mWidth=" + mWidth);
        mProgress += -distanceX;
        if (mProgress + mDisplayMetrics.density * (46 + 2) + mKeyBitmapWidth >= mWidth) {
            mProgress = (int) (mWidth - mDisplayMetrics.density * (46) - mKeyBitmapWidth);
        } else if (mProgress <= 0) {
            mProgress = 0;
        }
        invalidate();
        return true;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }

    public interface OnUnLockListener {
        void onUnLock();
    }

    public void setOnUnLockListener(OnUnLockListener onUnLockListener) {
        mOnUnLockListener = onUnLockListener;
    }
}
