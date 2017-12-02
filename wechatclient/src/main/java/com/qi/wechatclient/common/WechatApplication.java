package com.qi.wechatclient.common;

import android.app.Application;
import android.content.Context;

/**
 * Created by feng on 2017/6/20.
 */

public class WechatApplication extends Application {
    private static Context mApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }

    private void init() {
        mApplication = getApplicationContext();
    }

    public static Context getWechatApplication() {
        return mApplication;
    }
}
