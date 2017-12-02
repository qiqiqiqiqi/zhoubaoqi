package com.qi.wechatclient.common;


import android.app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.qi.wechatclient.cache.LoginCache;
import com.qi.wechatclient.service.CoreService;

public class LauncherActivity extends Activity {
    private static final int WHAT_ENTER = 0;
    private static final long WAIT_TIME = 500L;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int what = msg.what;
            switch (what) {
                case WHAT_ENTER:
                    if (!LoginCache.getLoginCache()) {
                        enterLogin();
                       // enterMain();
                    } else {
                        enterMain();
                    }
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startService();
        mHandler.sendEmptyMessageDelayed(WHAT_ENTER, WAIT_TIME);
    }

    private void startService() {
        Intent intent = new Intent(LauncherActivity.this, CoreService.class);
        startService(intent);
    }

    private void enterMain() {
        Intent intent = new Intent(LauncherActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void enterLogin() {
        Intent intent = new Intent(LauncherActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}


