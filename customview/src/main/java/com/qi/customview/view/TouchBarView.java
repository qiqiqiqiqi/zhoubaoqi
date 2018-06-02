package com.qi.customview.view;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.qi.customview.DisplayUtils;
import com.qi.customview.R;


/**
 * Created by feng on 2018/4/6.
 */

public class TouchBarView extends View  {
    private static final String TAG = TouchBarView.class.getSimpleName();
    public static final int MODE_DAY = 0;
    public static final int MODE_WEEK = 1;
    public static final int MODE_MONTH = 2;
    public static final int MODE_YEAR = 3;
    private int mWidth;
    private int mHeight;
    private int mProgress;
    private Paint mPaint;
    private Context mContext;
    private String[] mDayWeekMonthYear;
    private GestureDetector mGestureDetector;
    private int mItemWidth;
    private OnItemCheckListener mOnItemCheckListener;
    private int currentMode = MODE_WEEK;
    private ValueAnimator mValueAnimator;
    private long mDownTime;
    private boolean animationing;

    public TouchBarView(Context context) {
        this(context, null);
    }

    public TouchBarView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TouchBarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
        mDayWeekMonthYear = mContext.getResources().getStringArray(R.array.day_week_month_year);
        mGestureDetector = new GestureDetector(mContext, new GestureDetector.SimpleOnGestureListener(){
            @Override
            public boolean onDown(MotionEvent e) {
                return true;
            }

            @Override
            public void onShowPress(MotionEvent e) {
                Log.d(TAG, "onShowPress():");
            }

            @Override
            public boolean onSingleTapUp(MotionEvent e) {
//        float x = e.getX();
//        if (x >= 0 && x < mItemWidth) {
//            currentMode = MODE_DAY;
//            mProgress = 0;
//        } else if (x >= mItemWidth && x < 2 * mItemWidth) {
//            currentMode = MODE_WEEK;
//            mProgress = mItemWidth;
//        } else if (x > 2 * mItemWidth && x < 3 * mItemWidth) {
//            currentMode = MODE_MONTH;
//            mProgress = 2 * mItemWidth;
//        } else if (x >= 3 * mItemWidth && x < mWidth) {
//            currentMode = MODE_YEAR;
//            mProgress = 3 * mItemWidth;
//        }
//        invalidate();
//        if (mOnItemCheckListener != null) {
//            mOnItemCheckListener.onItemCheck(currentMode);
//        }
                return false;
            }



            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                mProgress -= distanceX;
                if (mProgress >= mWidth - mItemWidth) {
                    mProgress = mWidth - mItemWidth;
                } else if (mProgress <= 0) {
                    mProgress = 0;
                }
                invalidate();
                Log.d(TAG, "onScroll():mProgress=" + mProgress + ",distanceX=" + distanceX);

                return true;
            }

            @Override
            public void onLongPress(MotionEvent e) {

            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                return false;
            }
        });
        mValueAnimator = ValueAnimator.ofInt(mProgress, 0);
        mValueAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mProgress = (int) animation.getAnimatedValue();
                invalidate();

            }
        });
        mValueAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                animationing = true;
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                animationing = false;
                if (mOnItemCheckListener != null) {
                    mOnItemCheckListener.onItemCheck(currentMode);
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                animationing = false;
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                animationing = false;
            }
        });
    }

    public void startAnimation(int from, int to) {
        mValueAnimator.setDuration(Math.abs(from - to) * 500 / (3 * mItemWidth));
        mValueAnimator.setIntValues(from, to);
        mValueAnimator.start();
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
            width_size = Math.min(200, MeasureSpec.getSize(widthMeasureSpec));
        }
        if (height_mode == MeasureSpec.EXACTLY) {
            height_size = MeasureSpec.getSize(heightMeasureSpec);
        } else if (height_mode == MeasureSpec.AT_MOST) {
            height_size = Math.min(48, MeasureSpec.getSize(heightMeasureSpec));
        }
        setMeasuredDimension(width_size, height_size);
        mWidth = getMeasuredWidth() /*- getPaddingLeft() - getPaddingRight()*/;
        mHeight = getMeasuredHeight() /*- getPaddingTop() - getPaddingBottom()*/;
        mItemWidth = mWidth / 4;
        Log.d(TAG, "onMeasure():mWidth=" + mWidth + ",mHeight=" + mHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawBackground(canvas);
        drawLine(canvas);
        drawTouchBar(canvas);
        drawText(canvas);

    }

    private void drawLine(Canvas canvas) {
        canvas.save();
        mPaint.setStrokeWidth(1);
        mPaint.setColor(Color.parseColor("#66868D9A"));
        int padding = mHeight / 3;
        for (int i = 0; i < mDayWeekMonthYear.length - 1; i++) {
            canvas.drawLine(mItemWidth * (i + 1), mHeight - padding, mItemWidth * (i + 1), padding, mPaint);
        }
        canvas.restore();
    }

    private void drawTouchBar(Canvas canvas) {
        canvas.save();
        mPaint.setColor(Color.parseColor("#30d4cb"));
        RectF rect = new RectF(mProgress, 0, mProgress + mItemWidth, mHeight);
        canvas.drawRoundRect(rect, mHeight / 2, mHeight / 2, mPaint);
        canvas.restore();
    }

    private void drawText(Canvas canvas) {
        canvas.save();
        mPaint.setColor(Color.parseColor("#FF868D9A"));
        mPaint.setTextSize(DisplayUtils.dipToPx(mContext, 16));
        for (int i = 0; i < mDayWeekMonthYear.length; i++) {
            float measureText = mPaint.measureText(mDayWeekMonthYear[i]);
            canvas.drawText(mDayWeekMonthYear[i], mItemWidth * i + mItemWidth / 2 - measureText / 2, mHeight / 2 - (mPaint.descent() + mPaint.ascent()) / 2, mPaint);
        }
        canvas.restore();
    }

    private void drawBackground(Canvas canvas) {
        canvas.save();
        mPaint.setColor(Color.parseColor("#0D1C36"));
        RectF rect = new RectF(0, 0, mWidth, mHeight);
        canvas.drawRoundRect(rect, mHeight / 2, mHeight / 2, mPaint);
        canvas.restore();
    }
    public void setSelectedIndex(int index) {
        mProgress = index * mItemWidth;
        invalidate();
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mDownTime = System.currentTimeMillis();
                if (animationing) {
                    return false;
                }
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                float x = event.getX();
                int toProgress = mProgress;
                if (x >= 0 && x < mItemWidth) {
                    currentMode = MODE_DAY;
                    toProgress = 0;
                } else if (x >= mItemWidth && x < 2 * mItemWidth) {
                    currentMode = MODE_WEEK;
                    toProgress = mItemWidth;
                } else if (x > 2 * mItemWidth && x < 3 * mItemWidth) {
                    currentMode = MODE_MONTH;
                    toProgress = 2 * mItemWidth;
                } else if (x >= 3 * mItemWidth && x < mWidth) {
                    currentMode = MODE_YEAR;
                    toProgress = 3 * mItemWidth;
                }
                startAnimation(mProgress, toProgress);

                break;
        }
        mGestureDetector.onTouchEvent(event);
        return true;

    }



    public interface OnItemCheckListener {
        void onItemCheck(int mode);
    }

    public void setOnItemCheckListener(OnItemCheckListener onItemCheckListener) {
        mOnItemCheckListener = onItemCheckListener;
    }
}
