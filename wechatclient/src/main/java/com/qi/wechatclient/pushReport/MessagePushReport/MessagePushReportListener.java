package com.qi.wechatclient.pushReport.MessagePushReport;


import com.qi.wechatclient.chat.enity.MessageInfo;

import java.util.List;

/**
 * Created by feng on 2017/2/17.
 */

public interface MessagePushReportListener {
    void onMessagePushReportListener(List<MessageInfo> messageInfos);
}
