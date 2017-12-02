package com.qi.wechatclient.service.readTableService;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;

/**
 * Created by feng on 2017/7/14.
 */

public class ReadTableServiceConnection implements ServiceConnection {
    private ReadTableService.ReadTableBinder mReadTableBinder;

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        mReadTableBinder = (ReadTableService.ReadTableBinder) service;
        mReadTableBinder.getService().readTable();
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {

    }

    public ReadTableService.ReadTableBinder getReadTableBinder() {
        return mReadTableBinder;
    }
}
