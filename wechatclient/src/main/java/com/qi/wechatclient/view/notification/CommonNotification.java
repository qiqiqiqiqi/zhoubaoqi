package com.qi.wechatclient.view.notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.BitmapFactory;
import androidx.core.app.NotificationCompat;

import com.qi.wechatclient.R;
import com.qi.wechatclient.common.WechatApplication;

/**
 * Created by feng on 2017/8/5.
 */

public class CommonNotification {

    private static NotificationManager mNotificationManager;

    public static void showNotificaton(String title, String content) {
        if (mNotificationManager == null) {
            mNotificationManager = (NotificationManager) WechatApplication.getWechatApplication().getSystemService(Context.NOTIFICATION_SERVICE);
        }
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(WechatApplication.getWechatApplication());
        mBuilder.setTicker(title);
        mBuilder.setAutoCancel(true);
        mBuilder.setContentTitle(title);
        mBuilder.setContentText(content);
        mBuilder.setWhen(System.currentTimeMillis());
        mBuilder.setPriority(Notification.PRIORITY_MAX);
        mBuilder.setLargeIcon(BitmapFactory.decodeResource(WechatApplication.getWechatApplication().getResources(), R.drawable.app_logo));
        mBuilder.setSmallIcon(R.drawable.notification_logo);
        Notification notification = mBuilder.build();
        mNotificationManager.notify(0, notification);

    }
}
