package com.qi.circularseekbar.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.SweepGradient;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.qi.circularseekbar.R;

/**
 * Created by 13324 on 2016/10/7.
 */
public class CircularSeekBar extends View {
    private Context mContext;
    private Drawable mCircleDrawable;
    private int mCircle_intrinsicWidth;
    private int mCircle_intrinsicHeight;
    private int mWidth;
    private int mHeight;
    private int mCenter_x;
    private int mCenter_y;
    private int mRadius;
    private int outPadding;
    private int maxProgress = 30;
    private int minPorgress = 10;
    private int middleProgress = 20;
    private Paint mPaint;
    private float mAngle = 180;
    private float pre_angle;
    private boolean isOut;
    private OnProgressChangeListener mOnProgressChangeListener;
    private OnTouchStateListener mOnTouchStateListener;

    public CircularSeekBar(Context context) {
        this(context, null);
    }

    public CircularSeekBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initDrawable();
    }

    private void initDrawable() {
        mCircleDrawable = mContext.getResources().getDrawable(R.drawable.condition_circle_on);
        mCircle_intrinsicWidth = mCircleDrawable.getIntrinsicWidth();
        mCircle_intrinsicHeight = mCircleDrawable.getIntrinsicHeight();
        outPadding = pxTodp(200.0f);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width_mode = MeasureSpec.getMode(widthMeasureSpec);
        int height_mode = MeasureSpec.getMode(heightMeasureSpec);
        int width_size = 0;
        int height_size = 0;
        float scale = 1.0f;
        float width_scale = 1.0f;
        float height_scale = 1.0f;
        if (width_mode == MeasureSpec.EXACTLY) {
            width_size = MeasureSpec.getSize(widthMeasureSpec);
        } else if (width_mode == MeasureSpec.AT_MOST) {
            width_size = Math.min(200, MeasureSpec.getSize(widthMeasureSpec));
        }

        if (height_mode == MeasureSpec.EXACTLY) {
            height_size = MeasureSpec.getSize(heightMeasureSpec);
        } else {
            height_size = Math.min(200, MeasureSpec.getSize(heightMeasureSpec));
        }
        if (width_mode != MeasureSpec.UNSPECIFIED && width_size < mCircle_intrinsicWidth) {
            width_scale = (width_size + 0.0f) / mCircle_intrinsicWidth;
        }
        if (height_mode != MeasureSpec.UNSPECIFIED && height_size < mCircle_intrinsicHeight) {
            height_scale = (width_size + 0.0f) / mCircle_intrinsicHeight;
        }
        scale = Math.min(width_scale, height_scale);

        setMeasuredDimension(resolveSizeAndState((int) (mCircle_intrinsicWidth * scale), mCircle_intrinsicWidth, 0), resolveSizeAndState((int) (mCircle_intrinsicHeight * scale), mCircle_intrinsicHeight, 0));
        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();
        mCenter_x = mWidth / 2;
        mCenter_y = mHeight / 2;


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawCircle(canvas);
        drawScale(canvas);
        drawPoint(canvas, mAngle);
    }

    /**
     * 绘制标志点
     *
     * @param canvas
     */
    private void drawPoint(Canvas canvas, float angle) {
        canvas.save();
        // canvas.rotate(90, mCenter_x, mCenter_y);
        int[] ints = calcutePointXY(angle);
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        paint.setColor(Color.RED);
        canvas.drawCircle(ints[0], ints[1], 30, paint);
        canvas.restore();
    }

    private int[] calcutePointXY(float angle) {
        int[] ints = new int[2];
        double v = mRadius * Math.sin((angle / 180) * Math.PI);
        ints[0] = (int) (mCenter_x - mRadius * Math.sin((angle / 180) * Math.PI));
        ints[1] = (int) (mCenter_y + mRadius * Math.cos((angle / 180) * Math.PI));
        return ints;
    }

    /**
     * 绘制刻度
     *
     * @param canvas
     */
    private void drawScale(Canvas canvas) {
        canvas.save();
        int width = mCircleDrawable.getBounds().width();
        int height = mCircleDrawable.getBounds().height() / 2;
        mRadius = width / 2;
        for (int i = middleProgress; i <= maxProgress; i++) {
            if (i == middleProgress || i == 25 || i == maxProgress) {
                Paint paint = new Paint();
                paint.setColor(Color.parseColor("#C7C7C7"));
                paint.setAntiAlias(true);
                paint.setTextSize(40);
                canvas.drawText(i + "", mCenter_x - 20, mCenter_y - height - outPadding / 2, paint);

            } else {
                Paint paint = new Paint();
                paint.setColor(Color.parseColor("#C7C7C7"));
                paint.setStrokeWidth(6);
                paint.setStyle(Paint.Style.FILL);
                paint.setStrokeCap(Paint.Cap.ROUND);
                paint.setAntiAlias(true);
                canvas.drawLine(mCenter_x, mCenter_y - height - outPadding / 2, mCenter_x, mCenter_y - height - outPadding, paint);

            }
            canvas.rotate(15f, mCenter_x, mCenter_y);

        }
        canvas.restore();
        canvas.save();
        for (int i = middleProgress; i >= minPorgress; i--) {
            if (i == 15 || i == minPorgress) {
                Paint paint = new Paint();
                paint.setColor(Color.parseColor("#C7C7C7"));
                paint.setAntiAlias(true);
                paint.setTextSize(40);
                canvas.drawText(i + "", mCenter_x - 20, mCenter_y - height - outPadding / 2, paint);
            } else if (i == middleProgress) {
            } else {
                Paint paint = new Paint();
                paint.setColor(Color.parseColor("#C7C7C7"));
                paint.setStrokeWidth(6);
                paint.setStyle(Paint.Style.FILL);
                paint.setStrokeCap(Paint.Cap.ROUND);
                paint.setAntiAlias(true);
                canvas.drawLine(mCenter_x, mCenter_y - height - outPadding / 2, mCenter_x, mCenter_y - height - outPadding, paint);
            }
            canvas.rotate(-15f, mCenter_x, mCenter_y);
        }
        canvas.restore();
    }

    /**
     * 绘制圆环
     *
     * @param canvas
     */
    private void drawCircle(Canvas canvas) {
        canvas.save();
        mCircleDrawable.setBounds(mCenter_x - mCenter_x + outPadding, mCenter_y - mCenter_y + outPadding / 2, mCenter_x + mCenter_x - outPadding, mCenter_y + mCenter_y - outPadding);
        //  mCircleDrawable.draw(canvas);

        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(20);
        paint.setStrokeCap(Paint.Cap.ROUND);
        SweepGradient sweepGradient = new SweepGradient(mCenter_x, mCenter_y, new int[]{Color.parseColor("#00ADEF"), Color.parseColor("#10BDDE"), Color.parseColor("#3FCDB2"), Color.parseColor("#B1C04F"), Color.parseColor("#F9B512")}, null);
        Matrix matrix = new Matrix();
        matrix.setRotate(-270, mCenter_x, mCenter_y);
        sweepGradient.setLocalMatrix(matrix);
        paint.setShader(sweepGradient);
        RectF rectF = new RectF(mCenter_x - mCenter_x + outPadding, mCenter_y - mCenter_y + outPadding, mCenter_x + mCenter_x - outPadding, mCenter_y + mCenter_y - outPadding);
        canvas.drawArc(rectF, -240, 300, false, paint);
        canvas.restore();
    }

    private int pxTodp(float px) {
        float density = mContext.getResources().getDisplayMetrics().density;
        return (int) (px / density + 0.5f);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                float move_x = event.getX();
                float move_y = event.getY();
                mAngle = getPointAngle(move_x, move_y);
                int colorByAngle = getColorByAngle(mAngle);
                int progressByAngle = getProgressByAngle(mAngle);

                Log.d("CircularSeekBar", "angle=" + mAngle);
                if (!isOut) {
                    if (mOnProgressChangeListener != null) {
                        mOnProgressChangeListener.onProgressChange(progressByAngle, colorByAngle);
                    }
                    postInvalidate();
                }
                break;
            case MotionEvent.ACTION_UP:
                float up_x = event.getX();
                float up_y = event.getY();
                mAngle = getPointAngle(up_x, up_y);
                int colorByAngle1 = getColorByAngle(mAngle);
                int progressByAngle1 = getProgressByAngle(mAngle);
                Log.d("CircularSeekBar", "angle=" + mAngle);
                if (!isOut) {
                    if (mOnProgressChangeListener != null) {
                        mOnProgressChangeListener.onProgrssChangeResult(progressByAngle1, colorByAngle1);
                    }
                    postInvalidate();
                }
                break;
        }
        if (mOnTouchStateListener != null) {
            mOnTouchStateListener.onTouch(event);
        }
        return true;
    }

    /**
     * 根据角度获取圆环上的颜色
     *
     * @param angle
     * @return
     */
    private int getColorByAngle(float angle) {
        int startColor = Color.parseColor("#00ADEF");
        int endColor = Color.parseColor("#F9B512");
        int startred = Color.red(startColor);
        int startgreen = Color.green(startColor);
        int startblue = Color.blue(startColor);
        int endred = Color.red(endColor);
        int endgreen = Color.green(endColor);
        int endblue = Color.blue(endColor);

        int red = (int) (startred + (endred - startred) * (angle / 360) + 0.5f);
        int green = (int) (startgreen + (endgreen - startgreen) * (angle / 360) + 0.5f);
        int blue = (int) (startblue + (endblue - startblue) * (angle / 360) + 0.5f);

        return Color.argb(255, red, green, blue);
    }

    /**
     * 根据角度获取进度
     *
     * @param angle
     * @return
     */
    private int getProgressByAngle(float angle) {
        int progress = (int) (10 + (angle - 30) / 15 + 0.5f);
        return progress;
    }

    /**
     * w
     * 获得标志点的坐标与圆心的夹角
     */
    private float getPointAngle(float x, float y) {
        //   Log.d("CircularSeekBar", "move_x=" + x + ",move_y=" + y);
        float dx = x - mCenter_x;
        float dy = y - mCenter_y;
        Log.d("CircularSeekBar", "dx=" + dx + ",dy=" + dy);
        float angle = 0;
        double atan2 = Math.atan2(Math.abs(dx), Math.abs(dy));
        if (dx < 0 && dy > 0) {
            angle = (float) Math.toDegrees(atan2);
            if (angle < 30 && pre_angle < 330) {
                isOut = true;
                pre_angle = angle;
                return 30;
            } else {
                if (pre_angle > 0 && pre_angle < 30) {
                    isOut = false;
                }
            }
        } else if (dx < 0 && dy < 0) {
            angle = 180 - (float) Math.toDegrees(atan2);
            pre_angle = angle;
        } else if (dx > 0 && dy < 0) {
            angle = (float) Math.toDegrees(atan2) + 180;
            pre_angle = angle;
        } else if (dx > 0 && dy > 0) {
            angle = 360f - (float) Math.toDegrees(atan2);
            if (angle > 330 && pre_angle > 30) {
                isOut = true;
                pre_angle = angle;
                return 330;
            } else {
                if (pre_angle > 330 && pre_angle <= 360) {
                    isOut = false;
                }
            }
        }

        return angle;
    }

    public interface OnProgressChangeListener {
        void onProgressChange(int progress, int color);

        void onProgrssChangeResult(int progress, int color);
    }

    public void setOnProgressChangeListener(OnProgressChangeListener onProgressChangeListener) {
        mOnProgressChangeListener = onProgressChangeListener;
    }

    public interface OnTouchStateListener {
        void onTouch(MotionEvent event);
    }

    public void setOnTouchStateListener(OnTouchStateListener onTouchStateListener) {
        mOnTouchStateListener = onTouchStateListener;
    }
}
