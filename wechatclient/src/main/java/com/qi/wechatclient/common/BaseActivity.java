package com.qi.wechatclient.common;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.qi.wechatclient.cache.UserCache;

/**
 * Created by feng on 2017/6/21.
 */

public class BaseActivity extends AppCompatActivity {
    public String mCurrentUserID;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCurrentUserID= UserCache.getAppUserId();
    }

    public void leftTitleClick(View view) {
        finish();

    }

    public void rightTitleClick(View view) {
        finish();
    }
}
