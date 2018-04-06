//package com.qi.customview.view;
//
//import android.graphics.PorterDuff;
//import android.view.View;
//
///**
// * Created by feng on 2018/4/1.
// */
//
//package comqi.chartviewitem.view;
//
//import android.content.Context;
//import android.content.res.Resources;
//import android.graphics.Canvas;
//import android.graphics.LinearGradient;
//import android.graphics.Paint;
//import android.graphics.Paint.Style;
//import android.graphics.Path;
//import android.graphics.Point;
//import android.graphics.PorterDuff.Mode;
//import android.graphics.PorterDuffXfermode;
//import android.graphics.Shader;
//import android.graphics.Shader.TileMode;
//import android.util.AttributeSet;
//import android.util.Log;
//import android.view.View;
//import android.view.View.MeasureSpec;
//
//public class ChartViewItem extends View {
//    private int checkPosition;
//    private float[][] data;
//    private float endValue;
//    private int height;
//    private Context mContext;
//    private int mEndgreencolor;
//    private boolean mIsCheck = false;
//    private int mLevel1color;
//    private int mLevel2color;
//    private int mLevel3color;
//    private int mLevel4color;
//    private int mLevel5color;
//    private Paint mPathPaint;
//    private int mStartGreencolor;
//    private float maxData;
//    private float middleValue;
//    private int position;
//    private float startValue;
//    private int width;
//
//    public ChartViewItem(Context paramContext) {
//        super(paramContext);
//        initColor(paramContext);
//    }
//
//    public ChartViewItem(Context paramContext, AttributeSet paramAttributeSet) {
//        super(paramContext, paramAttributeSet);
//        initColor(paramContext);
//    }
//
//    public ChartViewItem(Context paramContext, AttributeSet paramAttributeSet, int paramInt) {
//        super(paramContext, paramAttributeSet, paramInt);
//        initColor(paramContext);
//    }
//
//    private void drawCicr(Canvas paramCanvas, Paint paramPaint, Point paramPoint, boolean paramBoolean) {
//        if (!paramBoolean) {
//            paramPaint.setStyle(Paint.Style.FILL);
//        }
//        for (; ; ) {
//            paramPaint.setShader(getLinearGradientByValue(this.maxData, this.middleValue));
//            paramPaint.setAntiAlias(true);
//            paramCanvas.drawCircle(paramPoint.x, paramPoint.y, 18.0F, paramPaint);
//            return;
//            paramPaint.setStyle(Paint.Style.STROKE);
//            paramPaint.setStrokeWidth(6.0F);
//        }
//    }
//
//    private LinearGradient getLinearGradientByValue(float paramFloat1, float paramFloat2) {
//        paramFloat1 = this.height;
//        int i = this.mLevel1color;
//        int j = this.mLevel2color;
//        int k = this.mLevel3color;
//        int m = this.mLevel4color;
//        int n = this.mLevel5color;
//        Shader.TileMode localTileMode = Shader.TileMode.CLAMP;
//        return new LinearGradient(0.0F, 0.0F, 0.0F, paramFloat1, new int[]{i, j, k, m, n}, null, localTileMode);
//    }
//
//    private void initColor(Context paramContext) {
//        this.mContext = paramContext;
//        this.mLevel1color = paramContext.getResources().getColor(2131427360);
//        this.mLevel2color = paramContext.getResources().getColor(2131427361);
//        this.mLevel3color = paramContext.getResources().getColor(2131427362);
//        this.mLevel4color = paramContext.getResources().getColor(2131427363);
//        this.mLevel5color = paramContext.getResources().getColor(2131427364);
//        paramContext.getResources().getColor(2131427355);
//        paramContext.getResources().getColor(2131427390);
//        this.mStartGreencolor = paramContext.getResources().getColor(2131427386);
//        this.mEndgreencolor = paramContext.getResources().getColor(2131427353);
//        paramContext.getResources().getColor(2131427387);
//        paramContext.getResources().getColor(2131427354);
//    }
//
//    protected void onDraw(Canvas paramCanvas) {
//        super.onDraw(paramCanvas);
//        if ((this.middleValue < 0.0F) || (this.data == null)) {
//            return;
//        }
//        int i;
//        label61:
//        Object localObject2;
//        Point localPoint1;
//        if (this.startValue < 0.0F) {
//            i = this.width / 2;
//            if (this.startValue >= 0.0F) {
//                break label888;
//            }
//            j = (int) ((1.0F - this.middleValue) * this.height);
//            localObject2 = new Point(i, j);
//            localPoint1 = new Point(this.width / 2, (int) ((1.0F - this.middleValue) * this.height));
//            if (this.endValue >= 0.0F) {
//                break label905;
//            }
//            i = this.width / 2;
//            label116:
//            if (this.endValue >= 0.0F) {
//                break label920;
//            }
//        }
//        Object localObject1;
//        label888:
//        label905:
//        label920:
//        for (int j = (int) ((1.0F - this.middleValue) * this.height); ; j = (int) ((1.0F - this.endValue) * this.height)) {
//            Point localPoint2 = new Point(i, j);
//            Point localPoint3 = new Point((int) ((((Point) localObject2).x + localPoint1.x) / 2.0F + 0.5F), ((Point) localObject2).y);
//            Point localPoint4 = new Point((int) ((((Point) localObject2).x + localPoint1.x) / 2.0F + 0.5F), localPoint1.y);
//            Point localPoint5 = new Point((localPoint1.x + localPoint2.x) / 2, localPoint1.y);
//            Point localPoint6 = new Point((localPoint1.x + localPoint2.x) / 2, localPoint2.y);
//            localObject1 = new Path();
//            Path localPath1 = new Path();
//            Path localPath2 = new Path();
//            ((Path) localObject1).moveTo(((Point) localObject2).x, ((Point) localObject2).y);
//            ((Path) localObject1).cubicTo(localPoint3.x, localPoint3.y, localPoint4.x, localPoint4.y, localPoint1.x, localPoint1.y);
//            ((Path) localObject1).moveTo(localPoint1.x, localPoint1.y);
//            ((Path) localObject1).cubicTo(localPoint5.x, localPoint5.y, localPoint6.x, localPoint6.y, localPoint2.x, localPoint2.y);
//            ((Path) localObject1).lineTo(localPoint2.x, this.height);
//            ((Path) localObject1).lineTo(((Point) localObject2).x, this.height);
//            ((Path) localObject1).lineTo(((Point) localObject2).x, ((Point) localObject2).y);
//            localPath1.moveTo(((Point) localObject2).x, ((Point) localObject2).y);
//            localPath1.cubicTo(localPoint3.x, localPoint3.y, localPoint4.x, localPoint4.y, localPoint1.x, localPoint1.y);
//            localPath2.moveTo(localPoint1.x, localPoint1.y);
//            localPath2.cubicTo(localPoint5.x, localPoint5.y, localPoint6.x, localPoint6.y, localPoint2.x, localPoint2.y);
//            this.mPathPaint = new Paint();
//            this.mPathPaint.setAntiAlias(true);
//            this.mPathPaint.setStyle(Paint.Style.FILL);
//            this.mPathPaint.setStrokeWidth(1.0F);
//            localObject2 = new LinearGradient(0.0F, 0.0F, this.width, this.height, this.mStartGreencolor, this.mEndgreencolor, Shader.TileMode.CLAMP);
//            this.mPathPaint.setShader((Shader) localObject2);
//            paramCanvas.drawPath((Path) localObject1, this.mPathPaint);
//            localObject1 = new Paint();
//            ((Paint) localObject1).setXfermode(null);
//            ((Paint) localObject1).setAntiAlias(true);
//            ((Paint) localObject1).setStrokeWidth(6.0F);
//            ((Paint) localObject1).setShader(null);
//            ((Paint) localObject1).setStyle(Paint.Style.STROKE);
//            if (this.startValue >= 0.0F) {
//                ((Paint) localObject1).setShader(getLinearGradientByValue(this.maxData, this.middleValue));
//                ((Paint) localObject1).setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_ATOP));
//                paramCanvas.drawPath(localPath1, (Paint) localObject1);
//            }
//            if (this.endValue >= 0.0F) {
//                ((Paint) localObject1).setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_ATOP));
//                ((Paint) localObject1).setShader(getLinearGradientByValue(this.maxData, this.middleValue));
//                paramCanvas.drawPath(localPath2, (Paint) localObject1);
//            }
//            ((Paint) localObject1).setXfermode(null);
//            ((Paint) localObject1).setStyle(Paint.Style.FILL);
//            ((Paint) localObject1).setColor(-1);
//            ((Paint) localObject1).setShader(null);
//            paramCanvas.drawCircle(localPoint1.x, localPoint1.y, 18.0F, (Paint) localObject1);
//            if (!this.mIsCheck) {
//                break label937;
//            }
//            drawCicr(paramCanvas, (Paint) localObject1, localPoint1, false);
//            return;
//            i = -this.width / 2;
//            break;
//            j = (int) ((1.0F - this.startValue) * this.height);
//            break label61;
//            i = this.width + this.width / 2;
//            break label116;
//        }
//        label937:
//        drawCicr(paramCanvas, (Paint) localObject1, localPoint1, true);
//    }
//
//    protected void onMeasure(int paramInt1, int paramInt2) {
//        super.onMeasure(paramInt1, paramInt2);
//        int i = 0;
//        int j = 0;
//        int m = View.MeasureSpec.getMode(paramInt1);
//        int k = View.MeasureSpec.getMode(paramInt2);
//        if (m == 1073741824) {
//            i = View.MeasureSpec.getSize(paramInt1);
//            if (k != 1073741824) {
//                break label111;
//            }
//            paramInt1 = View.MeasureSpec.getSize(paramInt2);
//        }
//        for (; ; ) {
//            setMeasuredDimension(i, paramInt1);
//            this.width = (getMeasuredWidth() - getPaddingLeft() - getPaddingRight());
//            this.height = (getMeasuredHeight() - getPaddingTop() - getPaddingBottom());
//            return;
//            if (m != Integer.MIN_VALUE) {
//                break;
//            }
//            i = Math.min(200, View.MeasureSpec.getSize(paramInt1));
//            break;
//            label111:
//            paramInt1 = j;
//            if (k == Integer.MIN_VALUE) {
//                paramInt1 = Math.min(200, View.MeasureSpec.getSize(paramInt2));
//            }
//        }
//    }
//
//    public void setCheck(boolean paramBoolean) {
//        this.mIsCheck = paramBoolean;
//        invalidate();
//    }
//
//    public void setItemData(float[][] paramArrayOfFloat) {
//        if (paramArrayOfFloat != null) {
//            Log.d("ChartViewItem", paramArrayOfFloat[0][0] + "," + paramArrayOfFloat[0][1] + "," + paramArrayOfFloat[0][2]);
//            this.data = paramArrayOfFloat;
//            this.startValue = paramArrayOfFloat[0][0];
//            this.middleValue = paramArrayOfFloat[0][1];
//            this.endValue = paramArrayOfFloat[0][2];
//            if (this.middleValue >= 0.0F) {
//            }
//        } else {
//            return;
//        }
//        invalidate();
//    }
//
//    public void setMaxData(float paramFloat) {
//        this.maxData = paramFloat;
//    }
//
//    public void setPositon(int paramInt) {
//        this.position = paramInt;
//    }
//}
//
