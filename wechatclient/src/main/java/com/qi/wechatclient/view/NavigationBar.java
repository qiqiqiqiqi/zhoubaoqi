package com.qi.wechatclient.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qi.wechatclient.R;
import com.qi.wechatclient.utils.StringUtil;


public class NavigationBar extends LinearLayout {

    private String mText;
    private String mRightText;
    private int mBgColor;
    private Drawable rightDrawable, leftDrawable, leftToRightDrawable;

    private TextView middleTextView;
    private TextView rightTextView;
    private RelativeLayout navigation_green_ll;

    public static String MIDDLE = "middle";
    public static String RIGHT = "right";

    private TextView backTextView;

    private ImageView backView, rightImageView, iv_left_to_right;

    private View line;//白色底线
    private int mTitleColor;
    private String mLeftText;


    public NavigationBar(Context context, AttributeSet attrs) {
        super(context, attrs);

        LayoutInflater.from(context).inflate(R.layout.title_left_arrow_green_bar,
                this, true);
        backTextView = (TextView) findViewById(R.id.cancle_tv);
        backView = (ImageView) findViewById(R.id.back_iv);
        middleTextView = (TextView) findViewById(R.id.title);
        rightTextView = (TextView) findViewById(R.id.confirm_tv);
        rightImageView = (ImageView) findViewById(R.id.confirm_image_tv);
        iv_left_to_right = (ImageView) findViewById(R.id.iv_left_to_right);
        navigation_green_ll = (RelativeLayout) findViewById(R.id.navigation_green_ll);
        line = findViewById(R.id.line);


        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.NavigationBar);
        mLeftText = a.getString(R.styleable.NavigationBar_left_text);
        mText = a.getString(R.styleable.NavigationBar_title);
        mRightText = a.getString(R.styleable.NavigationBar_right_text);
        mBgColor = a.getColor(R.styleable.NavigationBar_bg_color, getResources().getColor(R.color.common_white));
        mTitleColor = a.getColor(R.styleable.NavigationBar_middle_text_color, getResources().getColor(R.color.black));
        rightDrawable = a.getDrawable(R.styleable.NavigationBar_right_img);
        leftDrawable = a.getDrawable(R.styleable.NavigationBar_ng_left_img);
        leftToRightDrawable = a.getDrawable(R.styleable.NavigationBar_left_to_right_img);

        middleTextView.setText(this.mText);
        if (mTitleColor != 0) {
            middleTextView.setTextColor(mTitleColor);
        }

        if (leftDrawable != null) {
            backView.setImageDrawable(leftDrawable);
            backView.setVisibility(View.VISIBLE);
            backTextView.setVisibility(View.GONE);
        } else {
            backView.setVisibility(View.INVISIBLE);
        }
        if (mLeftText != null && !TextUtils.isEmpty(mLeftText)) {
            backTextView.setText(mLeftText);
            backTextView.setVisibility(View.VISIBLE);
            backView.setVisibility(View.GONE);

        } else {
            backTextView.setVisibility(View.INVISIBLE);
        }

        if (mRightText != null && !TextUtils.isEmpty(mRightText)) {
            rightTextView.setText(this.mRightText);
            rightTextView.setVisibility(View.VISIBLE);
            rightImageView.setVisibility(View.GONE);
        } else {
            rightTextView.setVisibility(View.INVISIBLE);
        }
        if (rightDrawable != null) {
            rightTextView.setVisibility(View.GONE);
            rightImageView.setImageDrawable(rightDrawable);
            rightImageView.setVisibility(VISIBLE);
        } else {
            rightTextView.setVisibility(View.INVISIBLE);
        }

        navigation_green_ll.setBackgroundColor(mBgColor);


        if (leftToRightDrawable != null) {
            iv_left_to_right.setVisibility(View.VISIBLE);
            iv_left_to_right.setImageDrawable(leftToRightDrawable);
        } else {
            iv_left_to_right.setVisibility(View.GONE);
        }

        a.recycle();
    }

    public NavigationBar(Context context) {
        super(context);
    }

    public NavigationBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public String getText() {
        return mText;
    }

    public void setText(String mText) {
        this.mText = mText;
        middleTextView.setText(this.mText);
    }

    public void setMiddleTextColor(int color) {
        middleTextView.setTextColor(color);
    }

    //private ImageView imageView;
    public String getRightText() {
        return mRightText;
    }

    public void setRightText(String mRightText) {
        this.mRightText = mRightText;
        rightTextView.setText(this.mRightText);
    }

    public void setRightImageViewRes(int res) {
        if (rightImageView != null) {
            rightImageView.setImageResource(res);
            rightImageView.setVisibility(VISIBLE);
        }
    }

    public void setLeftImageInvisiable(boolean visiable) {
        if (!visiable) {
            backView.setVisibility(View.INVISIBLE);
        } else {
            backView.setVisibility(View.VISIBLE);
        }

    }

    public void setLeftToRightViewRes(int res) {
        if (iv_left_to_right != null)
            iv_left_to_right.setImageResource(res);
    }

    public void setLeftToRightViewVisibility(int visibility) {
        if (iv_left_to_right != null)
            iv_left_to_right.setVisibility(visibility);
    }

    public void setRightTextVisibility(int visibility) {
        if (rightTextView != null) {
            rightTextView.setVisibility(visibility);
        }
    }

    public void setRightImageViewVisibility(int visibility) {
        if (rightImageView != null) {
            rightImageView.setVisibility(visibility);
        }
    }

    public void setRightTextColor(int color) {
        rightTextView.setTextColor(color);
    }

    public void hide(String location) {
        if (!StringUtil.isEmpty(location) && location.equals(MIDDLE)) {
            middleTextView.setVisibility(View.GONE);
        } else if (!StringUtil.isEmpty(location) && location.equals(RIGHT)) {
            rightTextView.setVisibility(View.GONE);
        }
    }

    public void show(String location) {
        if (!StringUtil.isEmpty(location) && location.equals(MIDDLE)) {
            middleTextView.setVisibility(View.VISIBLE);
        } else if (!StringUtil.isEmpty(location) && location.equals(RIGHT)) {
            rightTextView.setVisibility(View.VISIBLE);
        }
    }

    public void rightTitleClick(View view) {

    }

    public void setBarColor(int color) {
        navigation_green_ll.setBackgroundColor(color);
    }

    public void setBackViewWhite() {

    }

    public void setBackViewGreen() {

    }

    /**
     * 展示文字取消
     */
    public void showTextBack() {
        backView.setVisibility(GONE);
        backTextView.setVisibility(VISIBLE);
    }

    /**
     * 展示文字取消
     */
    public void showLeftTextAndLeftImage(int drawableRes, String text) {
        backView.setVisibility(VISIBLE);
        backTextView.setVisibility(VISIBLE);
        backView.setImageResource(drawableRes);
        backTextView.setText(text);
    }


    /**
     * 展示图片需求
     */
    public void showImageBack() {
        backView.setVisibility(VISIBLE);
        backTextView.setVisibility(GONE);
    }


    public void setRightTextViewEnable(boolean enable) {
        rightTextView.setEnabled(enable);
    }


}
