package com.qi.wechatclient.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.qi.wechatclient.R;

/**
 * Created by feng on 2017/6/29.
 */

public class MainTabViewGroup extends LinearLayout implements View.OnClickListener {
    public static final int MESSAGE = 0;
    public static final int FRIEND = 1;
    public static final int MINE = 2;
    private int currentTab = MESSAGE;
    private TabView mMessageTabView;
    private TabView mFriendsTabView;
    private TabView mMineTabView;
    private OnTabViewSelectedListener mOnTabViewSelectedListener;

    public MainTabViewGroup(Context context) {
        this(context, null);
    }

    public MainTabViewGroup(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MainTabViewGroup(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.tab_group_view, this, true);
        mMessageTabView = (TabView) view.findViewById(R.id.main_message);
        mFriendsTabView = (TabView) view.findViewById(R.id.main_friend);
        mMineTabView = (TabView) view.findViewById(R.id.main_mine);
        mMessageTabView.setSelected(true);
        mMessageTabView.setOnClickListener(this);
        mFriendsTabView.setOnClickListener(this);
        mMineTabView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_message:
                if (currentTab == MESSAGE) {//防止重复选择
                    return;
                }
                currentTab = MESSAGE;
                mMessageTabView.setSelected(true);
                mFriendsTabView.setSelected(false);
                mMineTabView.setSelected(false);
                break;
            case R.id.main_friend:
                if (currentTab == FRIEND) {
                    return;
                }
                currentTab = FRIEND;
                mMessageTabView.setSelected(false);
                mFriendsTabView.setSelected(true);
                mMineTabView.setSelected(false);
                break;
            case R.id.main_mine:
                if (currentTab == MINE) {
                    return;
                }
                currentTab = MINE;
                mMessageTabView.setSelected(false);
                mFriendsTabView.setSelected(false);
                mMineTabView.setSelected(true);
                break;
        }
        if (mOnTabViewSelectedListener != null) {
            mOnTabViewSelectedListener.onTabViewSelected(currentTab);
        }
    }

    public interface OnTabViewSelectedListener {
        void onTabViewSelected(int positon);
    }

    public void setOnTabViewSelectedListener(OnTabViewSelectedListener onTabViewSelectedListener) {
        mOnTabViewSelectedListener = onTabViewSelectedListener;
    }
}
