package com.qi.wechatclient.service.readTableService;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import com.qi.wechatclient.bo.Friend;
import com.qi.wechatclient.cache.UserCache;
import com.qi.wechatclient.model.request.ReadTableRequest;
import com.qi.wechatclient.utils.LogUtil;

import java.util.List;

public class ReadTableService extends Service {
    private static final String TAG = ReadTableService.class.getSimpleName();

    public ReadTableService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        ReadTableBinder readTableBinder = new ReadTableBinder();
        return readTableBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    public void readTable() {
        ReadTableRequest readTableRequest = new ReadTableRequest() {
            @Override
            public void onReadTableRequestResult(int result, int messageType, List<Friend> friendList) {
                LogUtil.d(TAG, "onReadTableRequestResult():result=" + result + ",friendList=" + friendList);

            }
        };
        readTableRequest.startReadTableRequest(UserCache.getAppUserId());
    }

   public class ReadTableBinder extends Binder {
        public ReadTableService getService() {
            return ReadTableService.this;
        }

    }
}
