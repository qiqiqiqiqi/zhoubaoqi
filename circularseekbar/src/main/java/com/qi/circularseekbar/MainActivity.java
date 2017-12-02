package com.qi.circularseekbar;

import android.app.Activity;

import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.qi.circularseekbar.view.CircularSeekBar;

public class MainActivity extends Activity implements CircularSeekBar.OnProgressChangeListener, CircularSeekBar.OnTouchStateListener {

    private CircularSeekBar mCircuarSeekBar;
    private TextView mSetTextView;
    private GradientDrawable mGradientDrawable;
    private TextView mCurrText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initEvent();
    }

    private void initEvent() {
        mCircuarSeekBar.setOnProgressChangeListener(this);
        mCircuarSeekBar.setOnTouchStateListener(this);
    }

    private void initView() {
        mCircuarSeekBar = (CircularSeekBar) findViewById(R.id.circularseekbar);
        mSetTextView = (TextView) findViewById(R.id.set_text);
        mCurrText = (TextView) findViewById(R.id.curr_text);
        //初始化tvTemperture
        mCurrText.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/digital.ttf"));
        //  mTvTemperature.setKeyName(current_Temperature + getString(R.string.conditioner_temperature_unit));
        mCurrText.setTextColor(Color.GRAY);
        //初始化GradientDrawable.用于tvSetTemperature的背景
        mGradientDrawable = new GradientDrawable();
        mGradientDrawable.setShape(GradientDrawable.OVAL);
        mGradientDrawable.setColor(Color.GREEN);
        //初始化tvSetTemperature
        mSetTextView.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/digital.ttf"));
        mSetTextView.setBackgroundDrawable(mGradientDrawable);

    }

    @Override
    public void onProgressChange(int progress, int color) {
        mSetTextView.setText(progress + "℃");
        mGradientDrawable.setColor(color);
    }

    @Override
    public void onProgrssChangeResult(int progress, int color) {
        mCurrText.setText(28 + "℃");
        mCurrText.setTextColor(color);
    }

    @Override
    public void onTouch(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mCurrText.setVisibility(View.INVISIBLE);
                mSetTextView.setVisibility(View.VISIBLE);
                break;
            case MotionEvent.ACTION_UP:
                mCurrText.setVisibility(View.VISIBLE);
                mSetTextView.setVisibility(View.INVISIBLE);
                break;
        }
    }
}
