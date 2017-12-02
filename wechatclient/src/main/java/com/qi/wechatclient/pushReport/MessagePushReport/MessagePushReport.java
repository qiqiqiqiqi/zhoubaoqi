package com.qi.wechatclient.pushReport.MessagePushReport;


import com.qi.wechatclient.chat.enity.MessageInfo;
import com.qi.wechatclient.dao.MessageInfoDao;
import com.qi.wechatclient.data.ErrorCode;
import com.qi.wechatclient.model.Event.ReceiveMessageEvent;
import com.qi.wechatclient.utils.LogUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by feng on 2017/2/17.
 */


public class MessagePushReport {
    private static final String TAG = MessagePushReport.class.getSimpleName();
    private static MessagePushReport sMessagePushReport;
    //观察者接口集合
    private List<MessagePushReportListener> mMessagePushReportListeners = new ArrayList<>();

    /**
     * 单例
     */
    public static MessagePushReport getInstance() {
        if (sMessagePushReport == null) {
            synchronized (MessagePushReport.class) {
                if (sMessagePushReport == null) {
                    sMessagePushReport = new MessagePushReport();
                }
            }
        }
        return sMessagePushReport;
    }

    public MessagePushReport() {
        EventBus.getDefault().register(this);
    }

    /**
     * 加入监听队列
     */

    public void registerPushReportListener(MessagePushReportListener messagePushReportListener) {
        if (messagePushReportListener == null) {
            try {
                throw new Exception("messagePushReportListener is null");
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
        synchronized (mMessagePushReportListeners) {
            if (mMessagePushReportListeners.contains(messagePushReportListener)) {
                return;
            }
            mMessagePushReportListeners.add(messagePushReportListener);
            LogUtil.d(TAG, "messagePushReportListener register success");
        }
    }

    /**
     * 通知观察者刷新数据
     */

    public void notifyPushReportListeners(List<MessageInfo> messageInfos) {
        for (MessagePushReportListener observerListener : mMessagePushReportListeners) {
            observerListener.onMessagePushReportListener(messageInfos);
        }
    }

    /**
     * 监听队列中移除
     */
    public void unRegisterPushReportListener(MessagePushReportListener messagePushReportListener) {
        if (messagePushReportListener == null) {
            try {
                throw new Exception("The messagePushReportListener is null.");
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
        synchronized (mMessagePushReportListeners) {
            if (mMessagePushReportListeners.contains(messagePushReportListener)) {
                mMessagePushReportListeners.remove(messagePushReportListener);
                LogUtil.d(TAG, "messagePushReportListener unregister success");
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMainThreadEvent(ReceiveMessageEvent receiveMessageEvent) {
        if (receiveMessageEvent != null && receiveMessageEvent.result == ErrorCode.SUCCESS) {
            MessageInfoDao.getMessageInfoDaoInstance().insertMessageInfos(receiveMessageEvent.mMessageInfos);
            notifyPushReportListeners(receiveMessageEvent.mMessageInfos);
        }

    }
}

