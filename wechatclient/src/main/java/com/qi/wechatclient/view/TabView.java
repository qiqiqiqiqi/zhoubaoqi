package com.qi.wechatclient.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qi.wechatclient.R;
import com.qi.wechatclient.utils.LogUtil;

/**
 * Created by feng on 2017/6/28.
 */

public class TabView extends LinearLayout {
    private static final String TAG = TabView.class.getSimpleName();
    private ImageView mTabIconImageView;
    private ImageView mTabHintImageview;
    private TextView mTabTextView;

    public TabView(Context context) {
        this(context, null);
    }

    public TabView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TabView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }

    private void init(Context context,AttributeSet attrs) {
        View view = LayoutInflater.from(context).inflate(R.layout.tab_view, this, true);
        mTabIconImageView = (ImageView) view.findViewById(R.id.tab_image);
        mTabHintImageview = (ImageView) view.findViewById(R.id.tab_hint);
        mTabTextView = (TextView) view.findViewById(R.id.tab_text);

        TypedArray typedArray = context.obtainStyledAttributes(attrs,R.styleable.TabView);
        Drawable drawable = typedArray.getDrawable(R.styleable.TabView_icon);
        int textColor = typedArray.getColor(R.styleable.TabView_textcolor, 0);
        String text = typedArray.getString(R.styleable.TabView_text);
        LogUtil.d(TAG, "init():text=" + text);
        if (drawable != null) {
            setTabIconImageViewDrawable(drawable);
        }
        if (textColor != 0) {
            setTabTextViewTextColor(textColor);
        }
        if (text != null) {
            setTabTextViewText(text);
        }

    }

    public void setTabIconImageViewRes(int imageViewRes) {
        mTabIconImageView.setImageResource(imageViewRes);
    }

    public void setTabIconImageViewDrawable(Drawable drawable) {
        mTabIconImageView.setImageDrawable(drawable);
    }

    public void setTabTextViewText(String text) {
        mTabTextView.setText(text);
    }

    public void setTabTextViewTextColor(int colorRes) {
        mTabTextView.setTextColor(colorRes);
    }

    public void setSelected(boolean selected) {
        mTabIconImageView.setSelected(selected);
        mTabTextView.setSelected(selected);
    }

    public void setTabHintImageviewVisiable(boolean visiable) {
        mTabHintImageview.setVisibility(visiable ? View.VISIBLE : View.GONE);
    }
}
