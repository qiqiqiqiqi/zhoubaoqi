package com.qi.wechatclient.common;


import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
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
