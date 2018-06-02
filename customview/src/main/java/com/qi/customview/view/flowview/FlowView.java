package com.qi.customview.view.flowview;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.graphics.PathMeasure;
import android.graphics.Point;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.qi.customview.DisplayUtils;
import com.qi.customview.R;

/**
 * Created by feng on 2018/5/5.
 */

public class FlowView extends View {
    private static final String TAG = FlowView.class.getSimpleName();
    private Context mContext;
    private String[] mFlowContents, mFlowContents2;
    private int mWidth;
    private int mHeight;
    private Paint mPaint;
    private int minRaduis;
    private int progressRaduis;
    private int mItemHeight;
    private int mTopOffset;
    private ValueAnimator mScaleValueAnimator, mProgressAnimator;
    private int maxRaduis;
    private int progress;
    private Point startPoint, middlePoint, endPoint;
    private Path mDstPath;
    private PathMeasure mPathMeasure;
    private int currentItem = -1;

    public FlowView(Context context) {
        this(context, null);
    }

    public FlowView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FlowView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(context);
    }

    private void init(Context context) {
        mContext = context;
        mFlowContents = mContext.getResources().getStringArray(R.array.flow_contents);
        mFlowContents2 = mContext.getResources().getStringArray(R.array.flow_contents2);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setTextSize(DisplayUtils.sp2px(mContext, 16));
        mPaint.setStyle(Paint.Style.FILL);
        minRaduis = DisplayUtils.dipToPx(mContext, 20);
        initAnimation();
        startPoint = new Point();
        middlePoint = new Point();
        endPoint = new Point();
        mDstPath = new Path();
        mPathMeasure = new PathMeasure();
    }

    private void initAnimation() {
        mScaleValueAnimator = ValueAnimator.ofInt(minRaduis, maxRaduis);
        mScaleValueAnimator.setDuration(500);
        mScaleValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                progressRaduis = (int) animation.getAnimatedValue();
                Log.d(TAG, "initAnimation()--mScaleValueAnimator:progressRaduis=" + progressRaduis + ",currentItem=" + currentItem);
                invalidate();
            }
        });
        mScaleValueAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                statrProgressAnimation();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

        mProgressAnimator = ValueAnimator.ofInt(0, 100);
        mProgressAnimator.setDuration(500);
        mProgressAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                progress = (int) animation.getAnimatedValue();
                Log.e(TAG, "initAnimation()--mProgressAnimator:progress=" + progress + ",currentItem=" + currentItem);
                invalidate();
            }
        });
        mProgressAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                progress = 0;
                mDstPath.reset();
                currentItem++;
                if (currentItem >= mFlowContents.length) {
                    // currentItem=-1;
                    stopAnimation();
                } else {
                    startAnimation();
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });


    }

    public void startAnimation(int start, int end) {
        if (mScaleValueAnimator.isRunning()) {
            return;
        }
        mScaleValueAnimator.setIntValues(start, end);
        mScaleValueAnimator.start();
    }

    public void startAnimation() {
        if (mScaleValueAnimator.isRunning()) {
            return;
        }
        mScaleValueAnimator.cancel();
        mProgressAnimator.cancel();
        progressRaduis = minRaduis = mItemHeight / 6;
        maxRaduis = 3 * minRaduis;
        if (currentItem < 0 || currentItem >= mFlowContents.length) {
            currentItem = 0;
        }
        startAnimation(minRaduis, maxRaduis);
    }

    private void statrProgressAnimation() {
        progress = 0;
        mProgressAnimator.start();
    }

    public void stopAnimation() {
        mScaleValueAnimator.cancel();
        mProgressAnimator.cancel();
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
            height_size = Math.min(600, MeasureSpec.getSize(heightMeasureSpec));
        }
        setMeasuredDimension(width_size, height_size);
        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();
        mItemHeight = (mHeight - getPaddingBottom() - getPaddingTop() - 2 * minRaduis - DisplayUtils.dipToPx(mContext, 100)) / (mFlowContents.length  /*- 1*/);
        mTopOffset = mHeight / 2 - (mItemHeight * (mFlowContents.length - 1) / 2);
        progressRaduis = minRaduis = mItemHeight / 4;
        maxRaduis = 3 * minRaduis;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        drawLine(canvas);
        drawItem(canvas);
    }

    private void drawItem(Canvas canvas) {
        canvas.save();
        int innerRaduis = progressRaduis;
        int outRaduis = progressRaduis + DisplayUtils.dipToPx(mContext, 2);
        for (int i = 0; i < mFlowContents.length; i++) {
            if (i != currentItem) {//非当前执行动画的item
                innerRaduis = mItemHeight / 6;
                outRaduis = innerRaduis + DisplayUtils.dipToPx(mContext, 2);
            } else {
                innerRaduis = progressRaduis;
                outRaduis = progressRaduis + DisplayUtils.dipToPx(mContext, 2);
            }

            drawInnerBackground(canvas, innerRaduis, i);
            drawInnerWhiteCircle(canvas, innerRaduis, i);
            drawProgressBackground(canvas, outRaduis, i);
            drawProgress(canvas, outRaduis, innerRaduis, i);
            drawText(canvas, innerRaduis, i);
        }
        canvas.restore();
    }

    private void drawProgress(Canvas canvas, int outRaduis, int innerRaduis, int i) {
        canvas.save();
        if (i == currentItem && progress > 0) {
            //进度环
            RectF rectF = new RectF(mWidth / 2 - outRaduis, mTopOffset - outRaduis + mItemHeight * i,
                    mWidth / 2 + outRaduis, mTopOffset - outRaduis + mItemHeight * i + 2 * outRaduis);
            SweepGradient sweepGradient = new SweepGradient(mWidth / 2, mTopOffset + mItemHeight * i,
                    new int[]{Color.parseColor("#20C6BA"), Color.parseColor("#00D3FF"), Color.parseColor("#08cfeb"), Color.parseColor("#00d2fe")}, null);
            Matrix matrix = new Matrix();
            matrix.postRotate(-90, mWidth / 2, mTopOffset + mItemHeight * i);
            sweepGradient.setLocalMatrix(matrix);
            mPaint.setStrokeCap(Paint.Cap.ROUND);
            mPaint.setStyle(Paint.Style.STROKE);
            mPaint.setStrokeWidth(DisplayUtils.dipToPx(mContext, 4));
            mPaint.setShader(sweepGradient);
            canvas.drawArc(rectF, -90, progress * 360 / 100, false, mPaint);

            mPaint.setShader(null);

            //透明度从0到1的渐变圆
            float v = progress * 255 / 100;
            int argb1 = Color.argb((int) v, 10, 207, 233);
            int argb2 = Color.argb((int) v, 32, 198, 187);
            LinearGradient linearGradient = new LinearGradient(mWidth / 2, mTopOffset - outRaduis + mItemHeight * i,
                    mWidth / 2, mTopOffset - outRaduis + mItemHeight * i + 2 * outRaduis,
                    new int[]{argb1, argb2}, null, Shader.TileMode.CLAMP);
            mPaint.setShader(linearGradient);
            mPaint.setStyle(Paint.Style.FILL);
            canvas.drawCircle(mWidth / 2, mTopOffset + mItemHeight * i, innerRaduis, mPaint);
            mPaint.setShader(null);

            //画√
            caculatePoint(i, progressRaduis);
            mPaint.setStrokeJoin(Paint.Join.ROUND);
            mPaint.setStyle(Paint.Style.STROKE);
            mPaint.setColor(Color.parseColor("#ffffff"));
            mDstPath.reset();
            float length = mPathMeasure.getLength();
            float stopD = (progress + 0.0f) / 100 * length;
            float startD = 0;
            //获取当前进度的路径，同时赋值给传入的mDstPath
            mPathMeasure.getSegment(startD, stopD, mDstPath, true);
            canvas.drawPath(mDstPath, mPaint);
        } else if (i < currentItem) {
            mPaint.reset();
            mPaint.setAntiAlias(true);
            mPaint.setStrokeWidth(DisplayUtils.dipToPx(mContext, 2));
            Path path = caculatePoint(i, minRaduis);
            LinearGradient linearGradient = new LinearGradient(mWidth / 2, mTopOffset - outRaduis + mItemHeight * i,
                    mWidth / 2, mTopOffset - outRaduis + mItemHeight * i + 2 * outRaduis,
                    new int[]{Color.parseColor("#0acee7"), Color.parseColor("#1ec7be")}, null, Shader.TileMode.CLAMP);
            mPaint.setShader(linearGradient);
            mPaint.setStyle(Paint.Style.FILL);
            canvas.drawCircle(mWidth / 2, mTopOffset + mItemHeight * i, outRaduis + DisplayUtils.dipToPx(mContext, 2), mPaint);

            mPaint.setShader(null);
            mPaint.setStrokeJoin(Paint.Join.ROUND);
            mPaint.setStyle(Paint.Style.STROKE);
            mPaint.setColor(Color.parseColor("#ffffff"));
            canvas.drawPath(path, mPaint);
        } else {

        }
        canvas.restore();
    }

    private void drawText(Canvas canvas, int innerRaduis, int i) {
        canvas.save();
        TextPaint textPaint = new TextPaint();
        textPaint.setAntiAlias(true);
        textPaint.setColor(Color.parseColor("#ffffff"));
        textPaint.setTextSize(DisplayUtils.sp2px(mContext, 16));
        StaticLayout staticLayout = null;
        if (i != currentItem) {
            staticLayout = new StaticLayout(mFlowContents[i], textPaint, 600,
                    Layout.Alignment.ALIGN_NORMAL, 1, 0, true);
            float measureText = textPaint.measureText(mFlowContents[i]);
            float offsetY = -staticLayout.getHeight() / 2;
            canvas.translate(mWidth / 2 - measureText / 2 + (innerRaduis * 2 + measureText / 2), mTopOffset + mItemHeight * i + offsetY);
        } else {
            staticLayout = new StaticLayout(mFlowContents2[i], textPaint, 600,
                    Layout.Alignment.ALIGN_NORMAL, 1, 0, true);
            float measureText = textPaint.measureText(mFlowContents2[i]);
            float offsetY = -staticLayout.getHeight() / 2;
            float offsetX = -measureText / staticLayout.getLineCount() / 2;
            Log.d(TAG, "drawItem():staticLayout.getLineCount() =" + staticLayout.getLineCount());
            float i2 = maxRaduis - innerRaduis + 0.0f;
            float i1 = maxRaduis - mItemHeight / 6 + 0.0f;
            textPaint.setARGB(((100 - progress) * 255 / 100), 255, 255, 255);
            canvas.translate(mWidth / 2 + offsetX + (innerRaduis * 2 + measureText / 2) * ((i2) / (i1)), mTopOffset + mItemHeight * i + offsetY);

        }
        staticLayout.draw(canvas);
        canvas.restore();
    }

    private void drawInnerWhiteCircle(Canvas canvas, int innerRaduis, int i) {
        canvas.save();
        if (i != currentItem) {
            mPaint.setColor(Color.parseColor("#ffffff"));
        } else {
            float i2 = maxRaduis - innerRaduis + 0.0f;
            float i1 = maxRaduis - mItemHeight / 6 + 0.0f;
            float v = i2 * 255 / i1;
            int argb = Color.argb((int) v, 255, 255, 255);
            mPaint.setColor(argb);
        }
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(mWidth / 2, mTopOffset + mItemHeight * i, innerRaduis, mPaint);
        canvas.restore();
    }

    private void drawProgressBackground(Canvas canvas, int outRaduis, int i) {
        canvas.save();
        mPaint.setColor(Color.parseColor("#415D83"));
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(DisplayUtils.dipToPx(mContext, 4));
        RectF rectF = new RectF(mWidth / 2 - outRaduis, mTopOffset - outRaduis + mItemHeight * i,
                mWidth / 2 + outRaduis, mTopOffset - outRaduis + mItemHeight * i + 2 * outRaduis);
        canvas.drawArc(rectF, -90, 360, false, mPaint);
        canvas.restore();
    }

    private void drawInnerBackground(Canvas canvas, int innerRaduis, int i) {
        canvas.save();
        //为了遮盖白线
        mPaint.setColor(Color.parseColor("#182845"));
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(mWidth / 2, mTopOffset + mItemHeight * i, innerRaduis, mPaint);
        canvas.restore();
    }

    private Path caculatePoint(int i, int raduis) {
        Path mPath = new Path();
        middlePoint.x = mWidth / 2 - raduis / 8;
        middlePoint.y = mTopOffset + mItemHeight * i + raduis / 4;
        startPoint.x = middlePoint.x - raduis / 3;
        startPoint.y = middlePoint.y - raduis / 3;
        endPoint.x = middlePoint.x + raduis / 3 * 2;
        endPoint.y = middlePoint.y - raduis / 3 * 2;
        mPath.moveTo(startPoint.x, startPoint.y);
        mPath.lineTo(middlePoint.x, middlePoint.y);
        mPath.lineTo(endPoint.x, endPoint.y);
        mPathMeasure.setPath(mPath, false);
        Log.d(TAG, "caculatePoint():i=" + i + ",middlePoint=" + middlePoint);
        return mPath;
    }

    private void drawLine(Canvas canvas) {
        canvas.save();
        mPaint.setColor(Color.parseColor("#415D83"));
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(DisplayUtils.dipToPx(mContext, 4));
        mPaint.setStyle(Paint.Style.FILL);
        PathEffect pathEffect = new DashPathEffect(new float[]{10, 30}, 0);
        mPaint.setPathEffect(pathEffect);
        if (currentItem >= mFlowContents.length) {
            canvas.drawLine(mWidth / 2, mTopOffset, mWidth / 2, mTopOffset + mItemHeight * (mFlowContents.length - 1), mPaint);
        } else {
            canvas.drawLine(mWidth / 2, mTopOffset, mWidth / 2, mTopOffset + mItemHeight * (currentItem < 0 ? 0 : currentItem), mPaint);
        }
        mPaint.setPathEffect(null);
        if (currentItem >= mFlowContents.length) {
//            canvas.drawLine(mWidth / 2, mTopOffset, mWidth / 2, mTopOffset + mItemHeight * (mFlowContents.length - 1), mPaint);
        } else {
            canvas.drawLine(mWidth / 2, mTopOffset + mItemHeight * (currentItem < 0 ? 0 : currentItem), mWidth / 2, mTopOffset + mItemHeight * (mFlowContents.length - 1), mPaint);
        }
        canvas.restore();
    }
}
