package com.qi.customview.calendarView.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;



import java.util.Calendar;


/**
 * Created by zhouyou on 2016/7/25.
 * Class desc:
 * <p>
 * 自定义日历View，可多选
 */
public class CalendarView extends View {
    private static final String TAG = CalendarView.class.getSimpleName();
    // 列的数量
    private static final int NUM_COLUMNS = 7;
    // 行的数量
    private static final int NUM_ROWS = 6;
    // 背景颜色
    private int mBgColor = Color.parseColor("#F7F7F7");
    private int mDayNotOptColor = Color.parseColor("#000000");
    // 天数字体大小
    private int mDayTextSize = 14;
    // 是否可以被点击状态
    private boolean mClickable = true;
    private DisplayMetrics mMetrics;
    private Paint mPaint;
    //当前时间变量
    private int mCurYear;
    private int mCurMonth;
    private int mCurDate;
    //选择日期对应的时间变量
    private int mSelYear;
    private int mSelMonth;
    private int mSelDate;

    private int mColumnSize;
    private int mRowSize;

    //该月份的所有日期集合
    private int[][] mDays;

    // 当月一共有多少天
    private int mMonthDays;
    // 当月第一天位于周几
    private int mWeekNumber;
    // 已选中背景Bitmap
    private Bitmap mBgOptBitmap;
    // 未选中背景Bitmap
    private Bitmap mBgNotOptBitmap;
    //设置成静态全局变量
    private static int[] minYearMonthDay = new int[3];
    private static int[] maxYearMonthDay = new int[3];

    public CalendarView(Context context) {
        super(context);
        init();
    }

    public CalendarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CalendarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        // 获取手机屏幕参数
        mMetrics = getResources().getDisplayMetrics();
        // 创建画笔
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        // 获取当前日期
        Calendar calendar = Calendar.getInstance();
        mSelYear = mCurYear = calendar.get(Calendar.YEAR);
        mSelMonth = mCurMonth = calendar.get(Calendar.MONTH);
        mSelDate = mCurDate = calendar.get(Calendar.DATE);
        //默认选中的最小日期是今天
        minYearMonthDay[0] = mCurYear;
        minYearMonthDay[1] = mCurMonth;
        minYearMonthDay[2] = mCurDate;
        //默认选中的最大日期是明天
        if (mCurMonth == 11 && DateUtils.getMonthDays(mCurYear, mCurMonth) == mCurDate) {
            maxYearMonthDay[0] = mCurYear+1;
            maxYearMonthDay[1] = 0;
            maxYearMonthDay[2] =1;
        } else {
            maxYearMonthDay[0] = mCurYear;
            maxYearMonthDay[1] = mCurMonth;
            maxYearMonthDay[2] = mCurDate + 1;
        }
        Log.d(TAG, "mCurYear=" + mCurYear + ",mCurMonth=" + mCurMonth + ",mCurDate=" + mCurDate);
        //setSelYMD(mCurYear, mCurMonth, mCurDate);


    }

    @Override
    protected void onDraw(Canvas canvas) {
        initSize();

        // 绘制背景
        mPaint.setColor(mBgColor);
        canvas.drawRect(0, 0, canvas.getWidth(), canvas.getHeight(), mPaint);

        mDays = new int[6][7];
        // 设置绘制字体大小
        mPaint.setTextSize(mDayTextSize * mMetrics.scaledDensity);
        // 设置绘制字体颜色

        String dayStr;
        // 获取当月一共有多少天
        mMonthDays = DateUtils.getMonthDays(mSelYear, mSelMonth);
        // 获取当月第一天位于周几
        mWeekNumber = DateUtils.getFirstDayWeek(mSelYear, mSelMonth);

        for (int day = 0; day < mMonthDays; day++) {
            dayStr = String.valueOf(day + 1);
            int column = (day + mWeekNumber - 1) % 7;//(0~6列共7列)
            int row = (day + mWeekNumber - 1) / 7;//(0~5共6行)
            mDays[row][column] = day + 1;
            //計算日期的位置
            int startX = (int) (mColumnSize * column + (mColumnSize - mPaint.measureText(dayStr)) / 2);
            int startY = (int) (mRowSize * row + mRowSize / 2 - (mPaint.ascent() + mPaint.descent()) / 2);
            //    canvas.drawBitmap(mBgNotOptBitmap, startX - (mBgNotOptBitmap.getWidth() / 3), startY - (mBgNotOptBitmap.getHeight() / 2), mPaint);
            if (minYearMonthDay[0] == maxYearMonthDay[0] && minYearMonthDay[1] == maxYearMonthDay[1]) {//同年同月
                if (maxYearMonthDay[1] == mSelMonth) {//绘制选中的月份
                    if (mDays[row][column] >= minYearMonthDay[2] && mDays[row][column] <= maxYearMonthDay[2]) {
                        drawBackgroundColor(canvas, mColumnSize * column, mRowSize * row, mColumnSize * (1 + column), mRowSize * (1 + row), mPaint);
                        mPaint.setColor(Color.parseColor("#ffffff"));
                        if (mDays[row][column] == minYearMonthDay[2] && mDays[row][column] == maxYearMonthDay[2]) {
                            startX = (int) (mColumnSize * column + (mColumnSize - mPaint.measureText("入住")) / 2);
                            canvas.drawText("入住", startX, startY, mPaint);
                        } else if (mDays[row][column] == minYearMonthDay[2]) {
                            startX = (int) (mColumnSize * column + (mColumnSize - mPaint.measureText("入住")) / 2);
                            canvas.drawText("入住", startX, startY, mPaint);
                        } else if (mDays[row][column] == maxYearMonthDay[2]) {
                            startX = (int) (mColumnSize * column + (mColumnSize - mPaint.measureText("退房")) / 2);
                            canvas.drawText("退房", startX, startY, mPaint);
                        } else {
                            canvas.drawText(dayStr, startX, startY, mPaint);
                        }

                    } else {
                        if (mSelYear == mCurYear && mSelMonth == mCurMonth && mDays[row][column] < mCurDate) {
                            mPaint.setColor(Color.parseColor("#33000000"));
                        } else {
                            mPaint.setColor(mDayNotOptColor);
                        }
                        canvas.drawText(dayStr, startX, startY, mPaint);
                    }
                } else {//绘制没有选中的月份
                    if (mSelYear == mCurYear && mSelMonth == mCurMonth && mDays[row][column] < mCurDate) {
                        mPaint.setColor(Color.parseColor("#33000000"));
                    } else {
                        mPaint.setColor(mDayNotOptColor);
                    }
                    canvas.drawText(dayStr, startX, startY, mPaint);
                }
            } else if (minYearMonthDay[0] == maxYearMonthDay[0] && minYearMonthDay[1] < maxYearMonthDay[1]) {//同年不同月
                if (mSelMonth < maxYearMonthDay[1] && mSelMonth >= minYearMonthDay[1] && mDays[row][column] >= minYearMonthDay[2]) {
                    drawBackgroundColor(canvas, mColumnSize * column, mRowSize * row, mColumnSize * (1 + column), mRowSize * (1 + row), mPaint);
                    mPaint.setColor(Color.parseColor("#ffffff"));
                    if (mDays[row][column] == minYearMonthDay[2]) {
                        startX = (int) (mColumnSize * column + (mColumnSize - mPaint.measureText("入住")) / 2);
                        canvas.drawText("入住", startX, startY, mPaint);
                    } else {

                        canvas.drawText(dayStr, startX, startY, mPaint);
                    }

                } else if (mSelMonth == (minYearMonthDay[1] + maxYearMonthDay[1]) / 2 && (minYearMonthDay[1] + maxYearMonthDay[1]) % 2 == 0) {
                    drawBackgroundColor(canvas, mColumnSize * column, mRowSize * row, mColumnSize * (1 + column), mRowSize * (1 + row), mPaint);
                    mPaint.setColor(Color.parseColor("#ffffff"));
                    canvas.drawText(dayStr, startX, startY, mPaint);
                } else if (mSelMonth == maxYearMonthDay[1] && mDays[row][column] <= maxYearMonthDay[2]) {
                    drawBackgroundColor(canvas, mColumnSize * column, mRowSize * row, mColumnSize * (1 + column), mRowSize * (1 + row), mPaint);
                    mPaint.setColor(Color.parseColor("#ffffff"));
                    if (mDays[row][column] == maxYearMonthDay[2]) {
                        startX = (int) (mColumnSize * column + (mColumnSize - mPaint.measureText("退房")) / 2);
                        canvas.drawText("退房", startX, startY, mPaint);
                    } else {
                        canvas.drawText(dayStr, startX, startY, mPaint);
                    }

                } else {
                    if (mSelYear == mCurYear && mSelMonth == mCurMonth && mDays[row][column] < mCurDate) {
                        mPaint.setColor(Color.parseColor("#33000000"));
                    } else {
                        mPaint.setColor(mDayNotOptColor);
                    }
                    canvas.drawText(dayStr, startX, startY, mPaint);
                }
            } else if (minYearMonthDay[0] < maxYearMonthDay[0]) {//跨年
                if (mSelMonth > maxYearMonthDay[1] &&mSelYear<maxYearMonthDay[0]&& mDays[row][column] >= minYearMonthDay[2]) {
                    drawBackgroundColor(canvas, mColumnSize * column, mRowSize * row, mColumnSize * (1 + column), mRowSize * (1 + row), mPaint);
                    mPaint.setColor(Color.parseColor("#ffffff"));
                    if (mDays[row][column] == minYearMonthDay[2]) {
                        startX = (int) (mColumnSize * column + (mColumnSize - mPaint.measureText("入住")) / 2);
                        canvas.drawText("入住", startX, startY, mPaint);
                    } else {
                        canvas.drawText(dayStr, startX, startY, mPaint);
                    }
                } else if (mSelMonth == 0 && minYearMonthDay[1] == 11 && maxYearMonthDay[1] == 1) {
                    drawBackgroundColor(canvas, mColumnSize * column, mRowSize * row, mColumnSize * (1 + column), mRowSize * (1 + row), mPaint);
                    mPaint.setColor(Color.parseColor("#ffffff"));
                    canvas.drawText(dayStr, startX, startY, mPaint);
                } else if (mSelMonth == maxYearMonthDay[1] && mDays[row][column] <= maxYearMonthDay[2]) {
                    drawBackgroundColor(canvas, mColumnSize * column, mRowSize * row, mColumnSize * (1 + column), mRowSize * (1 + row), mPaint);
                    mPaint.setColor(Color.parseColor("#ffffff"));
                    if (mDays[row][column] == maxYearMonthDay[2]) {
                        startX = (int) (mColumnSize * column + (mColumnSize - mPaint.measureText("退房")) / 2);
                        canvas.drawText("退房", startX, startY, mPaint);
                    } else {
                        canvas.drawText(dayStr, startX, startY, mPaint);
                    }

                } else {
                    if (mSelYear == mCurYear && mSelMonth == mCurMonth && mDays[row][column] < mCurDate) {
                        mPaint.setColor(Color.parseColor("#33000000"));
                    } else {
                        mPaint.setColor(mDayNotOptColor);
                    }
                    canvas.drawText(dayStr, startX, startY, mPaint);
                }
            }
            //给今天设置一个标志颜色
            if (mSelYear == mCurYear && mSelMonth == mCurMonth && mDays[row][column] == mCurDate) {
                if (mCurDate == minYearMonthDay[2] && mCurYear == minYearMonthDay[0] && mCurMonth == minYearMonthDay[1]) {
                    drawBackgroundColor(canvas, mColumnSize * column, mRowSize * row, mColumnSize * (1 + column), mRowSize * (1 + row), mPaint);
                    mPaint.setColor(Color.parseColor("#ffffff"));
                    startX = (int) (mColumnSize * column + (mColumnSize - mPaint.measureText("入住")) / 2);
                    canvas.drawText("入住", startX, startY, mPaint);
                } else {
                    mPaint.setColor(Color.parseColor("#ff0000"));
                    startX = (int) (mColumnSize * column + (mColumnSize - mPaint.measureText(dayStr)) / 2);
                    canvas.drawText(dayStr, startX, startY, mPaint);
                }
            }

        }
    }

    /**
     * 绘制背景色
     *
     * @param canvas
     */
    private void drawBackgroundColor(Canvas canvas, float left, float top, float right, float bottom, Paint paint) {
        paint.setColor(Color.parseColor("#bd9c7b"));
        paint.setStyle(Paint.Style.FILL);
        // paint.setStrokeWidth(20);
        canvas.drawRect(left, top, right, bottom, paint);
    }

    private int downX = 0, downY = 0;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int eventCode = event.getAction();
        switch (eventCode) {
            case MotionEvent.ACTION_DOWN:
                downX = (int) event.getX();
                downY = (int) event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                if (!mClickable) return true;

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

    /**
     * 点击事件
     */
    private void onClick(int x, int y) {
        int row = y / mRowSize;
        int column = x / mColumnSize;
        int day = DateUtils.getMonthDays(mSelYear, mSelMonth);
        int column_end = (day + mWeekNumber - 1) % 7 - 1;//(0~6列共7列)
        int row_end = (day + mWeekNumber - 1) / 7;//(0~5共6行)
        if (row == 0 && column + 1 < mWeekNumber || (row == row_end && column > column_end) || row > row_end) {
            return;
        }
        if ((mSelYear == mCurYear && mSelMonth == mCurMonth && mDays[row][column] < mCurDate)) {
            return;
        }
        setSelYMD(mSelYear, mSelMonth, mDays[row][column]);
        invalidate();
        if (mListener != null) {
            // 执行回调
            mListener.onClickDateListener(mSelYear, (mSelMonth + 1), mSelDate);
        }
    }

    /**
     * 初始化列宽和高
     */
    private void initSize() {
        // 初始化每列的大小
        mColumnSize = getWidth() / NUM_COLUMNS;
        // 初始化每行的大小
        mRowSize = getHeight() / NUM_ROWS;
    }


    /**
     * 设置日历是否可以点击
     */
    @Override
    public void setClickable(boolean clickable) {
        this.mClickable = clickable;
    }

    /**
     * 设置年月日
     *
     * @param year  年
     * @param month 月
     * @param date  日
     */
    private void setSelYMD(int year, int month, int date) {
        this.mSelYear = year;
        this.mSelMonth = month;
        this.mSelDate = date;
        int compareMinToMax = compareSelectDate(minYearMonthDay, maxYearMonthDay[0], maxYearMonthDay[1], maxYearMonthDay[2]);
        int compareToMin = compareSelectDate(minYearMonthDay, year, month, date);
        int compareToMax = compareSelectDate(maxYearMonthDay, year, month, date);
        //只选择了入住日期
        if (compareMinToMax == 0) {
            if (compareToMin == -1) {
                minYearMonthDay[0] = year;
                minYearMonthDay[1] = month;
                minYearMonthDay[2] = date;

                maxYearMonthDay[0] = year;
                maxYearMonthDay[1] = month;
                maxYearMonthDay[2] = date;
            } else if (compareToMin == 1) {
                maxYearMonthDay[0] = year;
                maxYearMonthDay[1] = month;
                maxYearMonthDay[2] = date;
            }
        } else {//选择了住宿期间,重置minYearMonthDay=maxYearMonthDay
            minYearMonthDay[0] = year;
            minYearMonthDay[1] = month;
            minYearMonthDay[2] = date;

            maxYearMonthDay[0] = year;
            maxYearMonthDay[1] = month;
            maxYearMonthDay[2] = date;
        }

        Log.d(TAG, "compareMinToMax=" + compareMinToMax + "，compareToMin=" + compareToMin + ",compareToMax=" + compareToMax);
        Log.d(TAG, "minYearMonthDay=" + minYearMonthDay[0] + "-" + minYearMonthDay[1] + "-" + minYearMonthDay[2] + ",maxYearMonthDay=" + +maxYearMonthDay[0] + "-" + maxYearMonthDay[1] + "-" + maxYearMonthDay[2]);

    }

    /**
     * 设置上一个月日历
     */
    public void setLastMonth() {
        int year = mSelYear;
        int month = mSelMonth;
        int day = mSelDate;
        // 如果是1月份，则变成12月份
        if (month == 0) {
            year = mSelYear - 1;
            month = 11;
        } else if (DateUtils.getMonthDays(year, month) == day) {
            //　如果当前日期为该月最后一点，当向前推的时候，就需要改变选中的日期
            month = month - 1;
            day = DateUtils.getMonthDays(year, month);
        } else {
            month = month - 1;
        }
        setSelYMD(year, month, day);
        invalidate();
    }

    /**
     * 设置下一个日历
     */
    public void setNextMonth() {
        int year = mSelYear;
        int month = mSelMonth;
        int day = mSelDate;
        // 如果是12月份，则变成1月份
        if (month == 11) {
            year = mSelYear + 1;
            month = 0;
        } else if (DateUtils.getMonthDays(year, month) == day) {
            //　如果当前日期为该月最后一点，当向前推的时候，就需要改变选中的日期
            month = month + 1;
            day = DateUtils.getMonthDays(year, month);
        } else {
            month = month + 1;
        }
        setSelYMD(year, month, day);
        invalidate();
    }

    /**
     * 设置下一个日历
     */
    public void setNextMonth(int position) {
        // 获取当前日期
        Calendar calendar = Calendar.getInstance();
        mSelYear = mCurYear = calendar.get(Calendar.YEAR);
        mSelMonth = mCurMonth = calendar.get(Calendar.MONTH);
        mSelDate = mCurDate = calendar.get(Calendar.DATE);

        int year = mSelYear;
        int month = mSelMonth;
        int day = mSelDate;

        // 如果是12月份，则变成1月份
      /*  if (month == 11) {
//            year = mSelYear + 1;
//            month = 0;
            mSelYear = mSelYear + 1;
            mSelMonth = 0;
        } else if (DateUtils.getMonthDays(year, month) == day) {
            //　如果当前日期为该月最后一点，当向前推的时候，就需要改变选中的日期
//              month = month + position;
//              day = DateUtils.getMonthDays(year, month);
            mSelMonth = mSelMonth + position;
            mSelDate = DateUtils.getMonthDays(mSelYear, mSelMonth);
        } else {*/
        // month = month + position;
        mSelMonth = mSelMonth + position;
        if (mSelMonth > 11) {
            mSelYear = mSelYear + 1;
            mSelMonth = mSelMonth - 12;
            //      }

        }
        Log.d(TAG, "mSelYear=" + mSelYear + ",mSelMonth=" + mSelMonth + ",mSelDate=" + mSelDate);
        //   setSelYMD(year, month, day);
        invalidate();
    }

    /**
     * 获取当前展示的年和月份
     *
     * @return 格式：2016-06
     */
    public String getDate() {
        String data;
        if ((mSelMonth + 1) < 10) {
            data = mSelYear + "-0" + (mSelMonth + 1);
        } else {
            data = mSelYear + "-" + (mSelMonth + 1);
        }
        return data;
    }

    /**
     * 获取当前展示的日期
     *
     * @return 格式：20160606
     */
    private String getSelData(int year, int month, int date) {
        String monty, day;
        month = (month + 1);

        // 判断月份是否有非0情况
        if ((month) < 10) {
            monty = "0" + month;
        } else {
            monty = String.valueOf(month);
        }

        // 判断天数是否有非0情况
        if ((date) < 10) {
            day = "0" + (date);
        } else {
            day = String.valueOf(date);
        }
        return year + monty + day;
    }

    private OnClickListener mListener;

    public interface OnClickListener {
        void onClickDateListener(int year, int month, int day);
    }

    /**
     * 设置点击回调
     */
    public void setOnClickDate(OnClickListener listener) {
        this.mListener = listener;
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        recyclerBitmap(mBgOptBitmap);
        recyclerBitmap(mBgNotOptBitmap);
    }

    /**
     * 释放Bitmap资源
     */
    private void recyclerBitmap(Bitmap bitmap) {
        if (bitmap != null && !bitmap.isRecycled()) {
            bitmap.recycle();
        }
    }

    /**
     * 比较两次选择日期的大小
     *
     * @param ymd
     * @param year
     * @param month
     * @param day
     * @return -1：第二次选择的日期小；0：选择的是同一天；1：第二次选择的大
     */
    private int compareSelectDate(int[] ymd, Integer year, Integer month, Integer day) {
        int compareToYear = (year.compareTo((Integer) ymd[0]));
        if (compareToYear == 0) {
            int compareToMonth = (month.compareTo((Integer) ymd[1]));
            if (compareToMonth == 0) {
                return (day.compareTo((Integer) ymd[2]));
            } else {
                return compareToMonth;
            }
        } else {
            return compareToYear;
        }


    }

    public void setMinMaxYearMonthDay(int[] yearMonthDay) {
        //   minYearMonthDay = yearMonthDay;
        invalidate();
    }

//    public void setMaxYearMonthDay(int[] maxYearMonthDay) {
//        this.maxYearMonthDay = maxYearMonthDay;
//        invalidate();
//    }
}
