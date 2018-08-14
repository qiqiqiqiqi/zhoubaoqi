package com.qi.customview.view.somaticdata;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.PixelFormat;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.qi.customview.DisplayUtils;
import com.qi.customview.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by feng on 2018/8/10.
 */
//必须实现SurfaceHolder.Callback接口和Runnable接口
public class SomaticDataSurfaceView extends SurfaceView implements SurfaceHolder.Callback, Runnable {
    private static final String TAG = SomaticDataSurfaceView.class.getSimpleName();
    private static final int MODE_SOMATIC = 0;
    private static final int MODE_COMPOSITION = 1;
    private int current_mode = MODE_SOMATIC;
    private Context mContext;
    private SurfaceHolder surfaceHolder;
    private Canvas canvas;
    //子线程绘制标记
    private volatile boolean isDrawing;
    private Bitmap mBodyBitmap;
    private Paint mPaint;
    private PathMeasure mPathMeasure;
    private Path mDestPath;
    private ValueAnimator mSomaticDataValueAnimator, mCompositionValueAnimator;
    private int mSomaticDataProgress, mCompositionProgress;
    private int animationRepeat;
    private List<PointF> mPointFS;
    private boolean visiable;
    private boolean isStartCompositionAnimatoin;
    private Bitmap mBmiBitmap;
    private Bitmap mkcalBitmap;
    private Bitmap mMuscleBitmap;
    private Bitmap mMeatBitmap;
    private Bitmap mAbnormalLeg;
    private Bitmap mAbnormalShoulder;
    private Bitmap mAbnormalHead;
    private Bitmap mAbnormalFoot;
    private Bitmap mAbnormalSmallLeg;
    private Bitmap mHeartBitmap;
    private Bitmap mHurtHeadBitmap;
    private Bitmap mHurtMuscleBitmap;
    private Bitmap mHurtHasletBitmap;
    private Bitmap mHurtArthrosisBitmap;
    private Bitmap mHurtSkinBitmap;
    private Bitmap mHurtBoneBitmap;
    private Bitmap mHurtKneeBitmap;
    private int downX;
    private int downY;
    private RectF mSwitchRectF;

    public SomaticDataSurfaceView(Context context) {
        this(context, null);

    }

    public SomaticDataSurfaceView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SomaticDataSurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        init();
    }

    public void onVisiable(boolean visiable) {
        this.visiable = visiable;
        isDrawing = true;
        startAimation(current_mode);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        Log.d(TAG, "onAttachedToWindow()");

    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Log.d(TAG, "onDetachedFromWindow()");
    }

    private void init() {
        surfaceHolder = getHolder();
        setZOrderOnTop(true);//设置画布  背景透明
        surfaceHolder.setFormat(PixelFormat.TRANSLUCENT);
        surfaceHolder.addCallback(this);
        setFocusable(true);
//        setFocusableInTouchMode(true);
//        setKeepScreenOn(true);
        mPointFS = new ArrayList<>();
        mPathMeasure = new PathMeasure();
        mDestPath = new Path();
        initBitmap();
        mPaint = new Paint();
        initAnimation();
    }

    private void initBitmap() {
        //背景
        mBodyBitmap = BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.bg_data_boy);
        //身体成分
        mBmiBitmap = BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.bg_data_bmi);
        mkcalBitmap = BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.bg_data_kcal);
        mMuscleBitmap = BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.bg_data_muscle);
        mMeatBitmap = BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.bg_data_meat);
        mHeartBitmap = BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.img_data_heart);
        //异常
        mAbnormalLeg = BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.icon_abnormal_leg);
        mAbnormalShoulder = BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.icon_abnormal_shoulder);
        mAbnormalHead = BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.icon_abnormal_head);
        mAbnormalFoot = BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.icon_abnormal_foot);
        mAbnormalSmallLeg = BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.icon_abnormal_small_leg);
        //损伤
        mHurtHeadBitmap = BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.icon_hurt_head);
        mHurtMuscleBitmap = BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.icon_hurt_muscle);
        mHurtHasletBitmap = BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.icon_hurt_haslet);
        mHurtArthrosisBitmap = BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.icon_hurt_arthrosis);
        mHurtSkinBitmap = BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.icon_hurt_skin);
        mHurtBoneBitmap = BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.icon_hurt_bone);
        mHurtKneeBitmap = BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.icon_hurt_knee);


    }

    private void initAnimation() {
        mSomaticDataValueAnimator = ValueAnimator.ofInt(0, 100);
        mSomaticDataValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mSomaticDataProgress = (int) animation.getAnimatedValue();
                Log.d(TAG, "onAnimationUpdate():mSomaticDataProgress=" + mSomaticDataProgress);
            }
        });
        mSomaticDataValueAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                super.onAnimationRepeat(animation);
                animationRepeat += 1;
                Log.d(TAG, "onAnimationRepeat():animationRepeat=" + animationRepeat);
            }
        });


        mCompositionValueAnimator = ValueAnimator.ofInt(0, 100);
        mCompositionValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mCompositionProgress = (int) animation.getAnimatedValue();
                Log.d(TAG, "onAnimationUpdate():mCompositionValueAnimator--mCompositionProgress=" + mCompositionProgress);
            }
        });
    }


    public void startAimation(int animationMode) {

        if (animationMode == MODE_SOMATIC) {
            if (mSomaticDataValueAnimator.isRunning()) {
                return;
            }
            mSomaticDataValueAnimator.setDuration(2000);
            if (mSomaticDataValueAnimator.getRepeatCount() == 0) {
                mSomaticDataValueAnimator.setRepeatCount(5);
            }
            mSomaticDataValueAnimator.start();
        } else {
            if (mCompositionValueAnimator.isRunning()) {
                return;
            }
            if (mCompositionValueAnimator.getRepeatCount() == 0) {
                mCompositionValueAnimator.setDuration(2000);
                mCompositionValueAnimator.setRepeatCount(1);
            }
            mCompositionValueAnimator.start();
        }

    }


    //当SurfaceView被创建的时候被调用
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Log.d(TAG, "surfaceCreated()");
        isDrawing = true;
        new Thread(this).start();
        startAimation(current_mode);
    }

    //当SurfaceView的视图发生改变，比如横竖屏切换时，这个方法被调用
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        Log.d(TAG, "surfaceChanged()");
    }

    //当SurfaceView被销毁的时候，比如不可见了，会被调用
    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        Log.d(TAG, "surfaceDestroyed()");
        isDrawing = false;
        // TODO: 2018/8/11  bug 点击Home键会回调
        // surfaceHolder.removeCallback(this);//
    }

    @Override
    public void run() {
        while (isDrawing) {
            draw();
        }
    }

    private void draw() {
        try {
            float bodyBitmapWhRatio = (mBodyBitmap.getWidth() + 0.0f) / mBodyBitmap.getHeight();
            Rect srcRectF = new Rect(0, 0, mBodyBitmap.getWidth(), mBodyBitmap.getHeight());
            int destWidth = getWidth() * 8 / 10;
            int destHeight = (int) (destWidth / bodyBitmapWhRatio);
            int leftOffset = (getWidth() - destWidth) / 2;
            int topOffset = (getHeight() - destHeight) / 2;
            Rect destRectF = new Rect(leftOffset, topOffset, leftOffset + destWidth, topOffset + destHeight);
            canvas = surfaceHolder.lockCanvas();
            mPaint.reset();
            mPaint.setAntiAlias(true);
            mPaint.setStyle(Paint.Style.STROKE);
            if ((mSomaticDataValueAnimator != null && mSomaticDataValueAnimator.isRunning())) {

                if (canvas != null) {
                    //执行具体的绘制操作
                    drawBackground(canvas, srcRectF, destRectF);
                    if (current_mode == MODE_SOMATIC) {
                        drawSomaticException(canvas, leftOffset, topOffset, destRectF);
                        drawSomaticData(canvas, leftOffset, topOffset, destRectF);
                        drawComposition(canvas, leftOffset, topOffset, destRectF, mCompositionProgress);
                    }

                }
            } else if (mCompositionValueAnimator != null && mCompositionValueAnimator.isRunning()) {
                if (canvas != null) {
                    drawComposition(canvas, leftOffset, topOffset, destRectF, mCompositionProgress);
                }
            }
            try {
                surfaceHolder.unlockCanvasAndPost(canvas);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void drawSomaticException(Canvas canvas, int leftOffset, int topOffset, Rect destRectF) {
        canvas.save();
        //异常**********************************
        //头部
        drawItemSomaticException(canvas, mAbnormalHead, leftOffset, topOffset, destRectF, 0.445f, 0.170f, 0.555f, 0.208f);
        //头部
        drawItemSomaticException(canvas, mAbnormalShoulder, leftOffset, topOffset, destRectF, 0.404f, 0.447f, 0.596f, 0.546f);
        //大腿
        drawItemSomaticException(canvas, mAbnormalLeg, leftOffset, topOffset, destRectF, 0.374f, 0.479f, 0.626f, 0.648f);
        //小腿
        drawItemSomaticException(canvas, mAbnormalSmallLeg, leftOffset, topOffset, destRectF, 0.386f, 0.632f, 0.614f, 0.787f);
        //脚
        drawItemSomaticException(canvas, mAbnormalFoot, leftOffset, topOffset, destRectF, 0.391f, 0.761f, 0.605f, 0.822f);
        //异常**********************************

        //心脏
        drawItemSomaticException(canvas, mHeartBitmap, leftOffset, topOffset, destRectF, 0.518f, 0.315f, 0.577f, 0.363f);

        //损伤
        drawItemSomaticException(canvas, mHurtHeadBitmap, leftOffset, topOffset, destRectF, 0.461f, 0.172f, 0.539f, 0.208f);
        drawItemSomaticException(canvas, mHurtMuscleBitmap, leftOffset, topOffset, destRectF, 0.596f, 0.301f, 0.678f, 0.406f);
        drawItemSomaticException(canvas, mHurtHasletBitmap, leftOffset, topOffset, destRectF, 0.422f, 0.379f, 0.578f, 0.456f);
        drawItemSomaticException(canvas, mHurtArthrosisBitmap, leftOffset, topOffset, destRectF, 0.311f, 0.399f, 0.402f, 0.435f);
        drawItemSomaticException(canvas, mHurtSkinBitmap, leftOffset, topOffset, destRectF, 0.612f, 0.406f, 0.691f, 0.467f);
        drawItemSomaticException(canvas, mHurtBoneBitmap, leftOffset, topOffset, destRectF, 0.393f, 0.630f, 0.477f, 0.796f);
        drawItemSomaticException(canvas, mHurtKneeBitmap, leftOffset, topOffset, destRectF, 0.513f, 0.621f, 0.621f, 0.661f);

        canvas.restore();
    }

    private void drawItemSomaticException(Canvas canvas, Bitmap itemBitmap, int leftOffset, int topOffset, Rect destRectF,
                                          float leftPercent, float topPercent, float rihgtPercent, float bottomPercent) {
        canvas.save();
        Rect srcRectF = new Rect(0, 0, itemBitmap.getWidth(), itemBitmap.getHeight());
        RectF itemDestRectF = new RectF(leftOffset + destRectF.width() * leftPercent,
                topOffset + destRectF.height() * topPercent,
                leftOffset + destRectF.width() * rihgtPercent,
                topOffset + destRectF.height() * bottomPercent);
        canvas.drawBitmap(itemBitmap, srcRectF, itemDestRectF, mPaint);
        canvas.restore();
    }

    private void drawComposition(Canvas canvas, int leftOffset, int topOffset, Rect destRectF, int compositionProgress) {
        canvas.save();
        PointF startPointF = new PointF(leftOffset + destRectF.width() * 0.979f, topOffset + destRectF.height() * 0.938f);
        PointF bmiPointF = new PointF(leftOffset + destRectF.width() * 0.114f, topOffset + destRectF.height() * 0.146f);
        PointF basalMetabolicRatePointF = new PointF(leftOffset + destRectF.width() * 0.893f, topOffset + destRectF.height() * 0.359f);
        PointF bodyFatPercentagePointF = new PointF(leftOffset + destRectF.width() * 0.081f, topOffset + destRectF.height() * 0.489f);
        PointF muscleWeightPointF = new PointF(leftOffset + destRectF.width() * 0.896f, topOffset + destRectF.height() * 0.715f);

        float startRaduis = DisplayUtils.dipToPx(mContext, 25.25f);
        mSwitchRectF = new RectF(startPointF.x - startRaduis, startPointF.y - startRaduis, startPointF.x + startRaduis, startPointF.y + startRaduis);
        float bmiRaduis = DisplayUtils.dipToPx(mContext, 40);
        float basalMetabolicRateRaduis = DisplayUtils.dipToPx(mContext, 54);
        float bodyFatPercentageRaduis = DisplayUtils.dipToPx(mContext, 42);
        float muscleWeightRaduis = DisplayUtils.dipToPx(mContext, 38.5f);

        float startDataValueTextSize = DisplayUtils.sp2px(mContext, 11f);
        float bmiDataValueTextSize = DisplayUtils.sp2px(mContext, 24f);
        float basalMetabolicRateDataValueTextSize = DisplayUtils.sp2px(mContext, 30f);
        float bodyFatPercentageDataValueTextSize = DisplayUtils.sp2px(mContext, 28f);
        float muscleWeightDataValueTextSize = DisplayUtils.sp2px(mContext, 23f);

        drawExpandButton(canvas, startPointF, startRaduis * compositionProgress / 100);
        drawCompositionItem(canvas, compositionProgress, mkcalBitmap, startPointF, basalMetabolicRatePointF, startRaduis + (basalMetabolicRateRaduis - startRaduis) * compositionProgress / 100, -240, 20, "基础代谢率", "1290", "kcal", startDataValueTextSize + (basalMetabolicRateDataValueTextSize - startDataValueTextSize) * compositionProgress / 100);
        drawCompositionItem(canvas, compositionProgress, mMeatBitmap, startPointF, bodyFatPercentagePointF, startRaduis + (bodyFatPercentageRaduis - startRaduis) * compositionProgress / 100, -120, 20, "体脂肪率", "25.4", "", startDataValueTextSize + (bodyFatPercentageDataValueTextSize - startDataValueTextSize) * compositionProgress / 100);
        drawCompositionItem(canvas, compositionProgress, mMuscleBitmap, startPointF, muscleWeightPointF, startRaduis + (muscleWeightRaduis - startRaduis) * compositionProgress / 100, 0, 20, "肌肉量", "28.2", "", startDataValueTextSize + (muscleWeightDataValueTextSize - startDataValueTextSize) * compositionProgress / 100);
        drawCompositionItem(canvas, compositionProgress, mBmiBitmap, startPointF, bmiPointF, startRaduis + (bmiRaduis - startRaduis) * compositionProgress / 100, 0, 0, "BMI", "20.8", "", startDataValueTextSize + (bmiDataValueTextSize - startDataValueTextSize) * compositionProgress / 100);
        canvas.restore();

    }

    private void drawExpandButton(Canvas canvas, PointF startPointF, float raduis) {
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.parseColor("#dee1e6"));
        canvas.drawCircle(startPointF.x, startPointF.y, raduis, mPaint);
        String text = "收起";
        mPaint.setColor(Color.parseColor("#263238"));
        mPaint.setTextSize(DisplayUtils.sp2px(mContext, 11));
        float measureText = mPaint.measureText(text);
        canvas.drawText(text, startPointF.x - measureText / 2, startPointF.y - (mPaint.descent() + mPaint.ascent()) / 2, mPaint);

    }

    private void drawCompositionItem(Canvas canvas, int compositionProgress, Bitmap itemBitmap, PointF startPointF, PointF endPointF, float raduis, float rotateAnge, int translate, String dataName, String dataValue, String dataUnit, float dataValueTextSize) {
        canvas.save();
        Path path = new Path();
        float translateX = (float) (translate * Math.cos(rotateAnge * Math.PI / 180));
        float translateY = (float) (translate * Math.sin(rotateAnge * Math.PI / 180));
        Log.d(TAG, "drawCompositionItem():translateX=" + translateX + ",translateY=" + translateY);
        startPointF.x = startPointF.x + translateX;
        startPointF.y = startPointF.y - translateY;
        path.moveTo(startPointF.x, startPointF.y);
        path.lineTo(endPointF.x, endPointF.y);
        mPathMeasure.setPath(path, false);
        float length = mPathMeasure.getLength();
        float stopD = (compositionProgress + 0.0f) / 100 * length;
        if (mPathMeasure.getSegment(0, stopD, mDestPath, true)) {
            float[] pos = new float[2];
            float[] tan = new float[2];
            mPathMeasure.getPosTan(stopD, pos, tan);
            Rect srcRect = new Rect(0, 0, itemBitmap.getWidth(), itemBitmap.getHeight());
            RectF destRect = new RectF(pos[0] - raduis, pos[1] - raduis, pos[0] + raduis, pos[1] + raduis);
            canvas.drawBitmap(itemBitmap, srcRect, destRect, mPaint);
            drawCompositionItemText(canvas, compositionProgress, new PointF(pos[0], pos[1]), dataName, dataValue, dataUnit, dataValueTextSize);
        } else if (mCompositionProgress == 0) {
            Rect srcRect = new Rect(0, 0, itemBitmap.getWidth(), itemBitmap.getHeight());
            RectF destRect = new RectF(startPointF.x - raduis, startPointF.y - raduis, startPointF.x + raduis, startPointF.y + raduis);
            canvas.drawBitmap(itemBitmap, srcRect, destRect, mPaint);
            //  drawCompositionItemText(canvas, compositionProgress, startPointF, dataName, dataValue, dataUnit, dataValueTextSize);
        }
        canvas.restore();
    }

    private void drawCompositionItemText(Canvas canvas, int compositionProgress, PointF pointF, String dataName, String dataValue, String dataUnit, float dataValueTextSize) {
        canvas.save();
        Paint namePaint = new Paint();
        namePaint.setAntiAlias(true);
        namePaint.setTextSize(DisplayUtils.sp2px(mContext, 11 * compositionProgress / 100));
        namePaint.setColor(Color.parseColor("#99ffffff"));
        float measureTextWidthName = namePaint.measureText(dataName);

        Paint paintValue = new Paint();
        paintValue.setAntiAlias(true);
        paintValue.setTextSize(dataValueTextSize);
        float measureTextWidthValue = paintValue.measureText(dataValue);
        Paint.FontMetrics fontMetricsValue = paintValue.getFontMetrics();
        paintValue.setColor(Color.WHITE);

        mPaint.setTextSize(DisplayUtils.sp2px(mContext, 12 * compositionProgress / 100));
        mPaint.setColor(Color.parseColor("#99ffffff"));
        float measureTextWidthUnit = mPaint.measureText(dataUnit);
        Paint.FontMetrics fontMetricsUnit = paintValue.getFontMetrics();
        float offsetY = (fontMetricsValue.descent + fontMetricsValue.ascent) / 2;
        canvas.drawText(dataValue, pointF.x - measureTextWidthValue / 2, pointF.y - offsetY, paintValue);
        canvas.drawText(dataName, pointF.x - measureTextWidthName / 2, pointF.y + offsetY - DisplayUtils.dipToPx(mContext, 8), namePaint);
        canvas.drawText(dataUnit, pointF.x - measureTextWidthUnit / 2, pointF.y - offsetY - (fontMetricsUnit.descent + fontMetricsUnit.ascent) / 2 + DisplayUtils.dipToPx(mContext, 8), mPaint);
        canvas.restore();
    }

    private void drawBackground(Canvas canvas, Rect srcRectF, Rect destRectF) {
        canvas.save();
//        canvas.setDrawFilter(new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG));
        LinearGradient linearGradient = new LinearGradient(0, 0, getWidth(), getHeight(), new int[]{Color.parseColor("#0D1C36"), Color.parseColor("#2B3F7E")}, null, Shader.TileMode.CLAMP);
        mPaint.setShader(linearGradient);
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawRect(new RectF(0, 0, getWidth(), getHeight()), mPaint);
        mPaint.setShader(null);
        canvas.drawBitmap(mBodyBitmap, srcRectF, destRectF, mPaint);
        canvas.restore();
    }

    private void drawSomaticData(Canvas canvas, int leftOffset, int topOffset, Rect destRectF) {
        // mPaint.setFontFeatureSettings("smcp");
        //普通数据
        canvas.save();
        PointF restingHeartRatePointF0 = new PointF(leftOffset + destRectF.width() * 0.585f, topOffset + destRectF.height() * 0.317f);
        PointF restingHeartRatePointF1 = new PointF(leftOffset + destRectF.width() * 0.650f, topOffset + destRectF.height() * 0.272f);
        PointF restingHeartRatePointF2 = new PointF(leftOffset + destRectF.width() * 0.790f, topOffset + destRectF.height() * 0.272f);
        mPointFS.clear();
        mPointFS.add(restingHeartRatePointF0);
        mPointFS.add(restingHeartRatePointF1);
        mPointFS.add(restingHeartRatePointF2);
        drawLine(canvas, mPointFS, animationRepeat, 0);

        PointF chestPointF0 = new PointF(leftOffset + destRectF.width() * 0.403f, topOffset + destRectF.height() * 0.339f);
        PointF chestPointF1 = new PointF(leftOffset + destRectF.width() * 0.250f, topOffset + destRectF.height() * 0.339f);
        PointF chestPointF2 = new PointF(leftOffset + destRectF.width() * 0.077f, topOffset + destRectF.height() * 0.288f);
        mPointFS.clear();
        mPointFS.add(chestPointF0);
        mPointFS.add(chestPointF1);
        mPointFS.add(chestPointF2);
        drawLine(canvas, mPointFS, animationRepeat, 1);

        PointF pressSurePointF0 = new PointF(leftOffset + destRectF.width() * 0.342f, topOffset + destRectF.height() * 0.396f);
        PointF pressSurePointF1 = new PointF(leftOffset + destRectF.width() * 0.077f, topOffset + destRectF.height() * 0.396f);
        mPointFS.clear();
        mPointFS.add(pressSurePointF0);
        mPointFS.add(pressSurePointF1);
        drawLine(canvas, mPointFS, animationRepeat, 2);


        PointF waistPointF0 = new PointF(leftOffset + destRectF.width() * 0.580f, topOffset + destRectF.height() * 0.407f);
        PointF waistPointF1 = new PointF(leftOffset + destRectF.width() * 0.790f, topOffset + destRectF.height() * 0.407f);
        mPointFS.clear();
        mPointFS.add(waistPointF0);
        mPointFS.add(waistPointF1);
        drawLine(canvas, mPointFS, animationRepeat, 3);

        PointF hipPointF0 = new PointF(leftOffset + destRectF.width() * 0.593f, topOffset + destRectF.height() * 0.483f);
        PointF hipPointF1 = new PointF(leftOffset + destRectF.width() * 0.661f, topOffset + destRectF.height() * 0.531f);
        PointF hipPointF2 = new PointF(leftOffset + destRectF.width() * 0.790f, topOffset + destRectF.height() * 0.531f);
        mPointFS.clear();
        mPointFS.add(hipPointF0);
        mPointFS.add(hipPointF1);
        mPointFS.add(hipPointF2);
        drawLine(canvas, mPointFS, animationRepeat, 4);
        if (animationRepeat > 4) {
            drawHeightWeight(canvas, leftOffset, topOffset, destRectF);
        }
        canvas.restore();
    }

    private void drawHeightWeight(Canvas canvas, int leftOffset, int topOffset, Rect destRectF) {
        PointF heightPointF1 = new PointF(leftOffset + destRectF.width() * 0.077f, topOffset + destRectF.height() * 0.088f);
        PointF weightPointF2 = new PointF(leftOffset + destRectF.width() * 0.790f, topOffset + destRectF.height() * 0.088f);
        drawSomaticDataLeftText(canvas, heightPointF1, true, "身高", "170", "cm");
        drawSomaticDataRightText(canvas, weightPointF2, true, "体重", "46.8", "kg");
    }

    private void drawLine(Canvas canvas, List<PointF> pointFS, int animationRepeat, int position) {
        canvas.save();
        mDestPath.reset();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.WHITE);
        int pathProgress = 0;
        if (animationRepeat > position) {
            pathProgress = 100;
        } else if (animationRepeat == position) {
            pathProgress = mSomaticDataProgress;
        }
        Path path = new Path();
        for (int i = 0; i < pointFS.size(); i++) {
            PointF pointF = pointFS.get(i);
            if (i == 0) {
                path.moveTo(pointF.x, pointF.y);
            } else {
                path.lineTo(pointF.x, pointF.y);
            }
        }
        mPathMeasure.setPath(path, false);
        float length = mPathMeasure.getLength();
        float stopD = (pathProgress + 0.0f) / 100 * length;
        if (mPathMeasure.getSegment(0, stopD, mDestPath, true)) {
            float[] pos = new float[2];
            float[] tan = new float[2];
            mPathMeasure.getPosTan(stopD, pos, tan);
            canvas.drawPath(mDestPath, mPaint);
            drawSomaticDataPoints(canvas, position, pointFS, pos);
        }
        drawSomaticDataText(canvas, animationRepeat, position, pointFS);
        canvas.restore();
    }

    private void drawSomaticDataPoints(Canvas canvas, int position, List<PointF> pointFS, float[] pos) {
        canvas.save();
        for (PointF pointF : pointFS) {
            if (((position == 0 || position == 3 || position == 4) && pointF.x < pos[0])
                    || ((position == 1 || position == 2) && pointF.x > pos[0])) {
                mPaint.setStyle(Paint.Style.FILL);
                canvas.drawCircle(pointF.x, pointF.y, 3, mPaint);
                if (pointFS.size() == 3 && pointFS.indexOf(pointF) == 1) {
                    mPaint.setStyle(Paint.Style.STROKE);
                    canvas.drawCircle(pointF.x, pointF.y, 6, mPaint);
                }
            }
        }
        canvas.restore();
    }

    private void drawSomaticDataText(Canvas canvas, int animationRepeat, int position, List<PointF> pointFS) {
        PointF pointF = pointFS.get(pointFS.size() - 1);
        canvas.save();
        String dataName = "";
        String dataValue = "";
        String dataUnit = "";
        if (animationRepeat > position) {
            switch (position) {
                case 0:
                    dataName = "安静心率";
                    dataValue = "78";
                    dataUnit = "bpm";
                    break;
                case 1:
                    dataName = "胸围";
                    dataValue = "88.0";
                    dataUnit = "cm";
                    break;
                case 2:
                    dataName = "血压";
                    dataValue = "78/127";
                    dataUnit = "mmHg";
                    break;
                case 3:
                    dataName = "腰围";
                    dataValue = "34.0";
                    dataUnit = "cm";
                    break;
                case 4:
                    dataName = "臀围";
                    dataValue = "56.0";
                    dataUnit = "cm";
                    break;
            }
        }
        mPaint.setStyle(Paint.Style.FILL);
        if (((position == 0 || position == 3 || position == 4))) {
            drawSomaticDataRightText(canvas, pointF, false, dataName, dataValue, dataUnit);
        } else {
            drawSomaticDataLeftText(canvas, pointF, false, dataName, dataValue, dataUnit);
        }
        canvas.restore();
    }

    private void drawSomaticDataLeftText(Canvas canvas, PointF pointF, boolean drawBg, String dataName, String dataValue, String dataUnit) {
        Paint namePaint = new Paint();
        namePaint.setAntiAlias(true);
        namePaint.setStyle(Paint.Style.FILL);
        namePaint.setColor(Color.parseColor("#99ffffff"));
        namePaint.setTextSize(DisplayUtils.sp2px(mContext, 11));
        float measureTextWidthName = namePaint.measureText(dataName);
        Paint.FontMetrics fontMetricsName = namePaint.getFontMetrics();
        int offsetRight = (int) (DisplayUtils.dipToPx(mContext, 15.6f) + measureTextWidthName);

        Paint valuePaint = new Paint();
        valuePaint.setAntiAlias(true);
        valuePaint.setStyle(Paint.Style.FILL);
        valuePaint.setColor(Color.WHITE);
        valuePaint.setTextSize(DisplayUtils.sp2px(mContext, 20));
        float measureTextWidthValue = valuePaint.measureText(dataValue);
        Paint.FontMetrics fontMetricsValue = valuePaint.getFontMetrics();
        int offsetDataValueLeft = DisplayUtils.dipToPx(mContext, 3f);
        mPaint.setTextSize(DisplayUtils.sp2px(mContext, 12));
        if (drawBg) {
            Path path = new Path();
            int padding = DisplayUtils.dipToPx(mContext, 6);
            path.moveTo(pointF.x - offsetRight - padding, pointF.y - (fontMetricsName.bottom - fontMetricsName.top));
            path.lineTo(pointF.x - offsetRight + measureTextWidthValue + offsetDataValueLeft + measureTextWidthName - padding, pointF.y - (fontMetricsName.bottom - fontMetricsName.top));
            path.lineTo(pointF.x - offsetRight + measureTextWidthValue + offsetDataValueLeft + measureTextWidthName, pointF.y - (fontMetricsName.bottom - fontMetricsName.top) + padding);
            path.lineTo(pointF.x - offsetRight + measureTextWidthValue + offsetDataValueLeft + measureTextWidthName, pointF.y + (fontMetricsValue.bottom - fontMetricsValue.top) + padding);
            path.lineTo(pointF.x - offsetRight - padding, pointF.y + (fontMetricsValue.bottom - fontMetricsValue.top) + padding);
            path.close();
            mPaint.setColor(Color.parseColor("#132342"));
            mPaint.setStyle(Paint.Style.FILL);
            canvas.drawPath(path, mPaint);
        }
        canvas.drawText(dataName, pointF.x - offsetRight, pointF.y, namePaint);
        canvas.drawText(dataValue, pointF.x - offsetRight, pointF.y + (fontMetricsValue.descent - fontMetricsValue.ascent), valuePaint);
        mPaint.setColor(Color.parseColor("#99ffffff"));
        canvas.drawText(dataUnit, pointF.x - offsetRight + measureTextWidthValue + offsetDataValueLeft, pointF.y + (fontMetricsValue.descent - fontMetricsValue.ascent), mPaint);
    }

    private void drawSomaticDataRightText(Canvas canvas, PointF pointF, boolean drawBg, String dataName, String dataValue, String dataUnit) {
        int offsetLeft = DisplayUtils.dipToPx(mContext, 7.6f);
        Paint namePaint = new Paint();
        namePaint.setAntiAlias(true);
        namePaint.setTextSize(DisplayUtils.sp2px(mContext, 11));
        namePaint.setColor(Color.parseColor("#99ffffff"));
        float measureTextWidthName = namePaint.measureText(dataName);
        Paint.FontMetrics fontMetricsName = namePaint.getFontMetrics();

        Paint paintValue = new Paint();
        paintValue.setAntiAlias(true);
        paintValue.setTextSize(DisplayUtils.sp2px(mContext, 20));
        float measureTextWidthValue = paintValue.measureText(dataValue);
        Paint.FontMetrics fontMetricsValue = paintValue.getFontMetrics();
        paintValue.setColor(Color.WHITE);
        mPaint.setAntiAlias(true);
        mPaint.setTextSize(DisplayUtils.sp2px(mContext, 12));
        int offsetDataValueLeft = DisplayUtils.dipToPx(mContext, 3f);

        if (drawBg) {
            Path path = new Path();
            int padding = DisplayUtils.dipToPx(mContext, 6);
            path.moveTo(pointF.x + offsetLeft - padding, pointF.y - (fontMetricsName.bottom - fontMetricsName.top));
            path.lineTo(pointF.x + offsetLeft + measureTextWidthValue + offsetDataValueLeft + measureTextWidthName - padding, pointF.y - (fontMetricsName.bottom - fontMetricsName.top));
            path.lineTo(pointF.x + offsetLeft + measureTextWidthValue + offsetDataValueLeft + measureTextWidthName, pointF.y - (fontMetricsName.bottom - fontMetricsName.top) + padding);
            path.lineTo(pointF.x + offsetLeft + measureTextWidthValue + offsetDataValueLeft + measureTextWidthName, pointF.y + (fontMetricsValue.bottom - fontMetricsValue.top) + padding);
            path.lineTo(pointF.x + offsetLeft - padding, pointF.y + (fontMetricsValue.bottom - fontMetricsValue.top) + padding);
            path.close();
            mPaint.setColor(Color.parseColor("#132342"));
            mPaint.setStyle(Paint.Style.FILL);
            canvas.drawPath(path, mPaint);
        }
        canvas.drawText(dataName, pointF.x + offsetLeft, pointF.y, namePaint);
        canvas.drawText(dataValue, pointF.x + offsetLeft, pointF.y + (fontMetricsValue.descent - fontMetricsValue.ascent), paintValue);
        mPaint.setColor(Color.parseColor("#99ffffff"));
        canvas.drawText(dataUnit, pointF.x + offsetLeft + offsetDataValueLeft + measureTextWidthValue, pointF.y + (fontMetricsValue.descent - fontMetricsValue.ascent), mPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downX = (int) event.getX();
                downY = (int) event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                int upX = (int) event.getX();
                int upY = (int) event.getY();
                if (Math.abs(upX - downX) < 10 && Math.abs(upY - downY) < 10) {
                    performClick();
                    onClick((upX + downX) / 2, (upY + downY) / 2);
                }
                break;
        }
        return true;
    }

    private void onClick(int x, int y) {
        if (x >= mSwitchRectF.left && x < mSwitchRectF.right && y > mSwitchRectF.top && y < mSwitchRectF.bottom) {
            startAimation(MODE_COMPOSITION);
        }

    }
}

