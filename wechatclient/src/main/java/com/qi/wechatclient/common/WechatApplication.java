package com.qi.wechatclient.common;

import android.app.Application;
import android.content.Context;

import com.mob.MobSDK;

/**
 * Created by feng on 2017/6/20.
 */

public class WechatApplication extends Application {
    private static Context mApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        init();
        MobSDK.init(this);
    }

    private void init() {
        mApplication = getApplicationContext();
    }

    public static Context getWechatApplication() {
        return mApplication;
    }
}
