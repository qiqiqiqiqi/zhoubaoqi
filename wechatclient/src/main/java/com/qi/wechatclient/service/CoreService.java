package com.qi.wechatclient.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.qi.wechatclient.config.ConnectionConfig;
import com.qi.wechatclient.manager.ConnectionManager;


public class CoreService extends Service {
    public static final String TAG = "CoreService";
    private ConnectionThread thread;
    ConnectionManager mManager;

    public CoreService() {
    }

    @Override
    public void onCreate() {
        ConnectionConfig config = new ConnectionConfig.Builder(getApplicationContext())
             // .setIp("10.0.2.2")//连接的IP地址
                .setIp("192.168.1.101")//连接的IP地址
                .setPort(8081)//连接的端口号
                .setReadBufferSize(1024)
                .setConnectionTimeout(10000).builder();
        mManager = new ConnectionManager(config);
        thread = new ConnectionThread();
        thread.start();
        super.onCreate();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    class ConnectionThread extends Thread {
        boolean isConnection;

        @Override
        public void run() {
            for (; ; ) {
                isConnection = mManager.connect();
                if (isConnection) {
                    Log.d(TAG, "连接成功跳出循环");
                    break;
                }
                try {
                    Log.d(TAG, "尝试重新连接");
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void disConnect() {
        mManager.disConnect();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        disConnect();
        thread = null;
    }
}
