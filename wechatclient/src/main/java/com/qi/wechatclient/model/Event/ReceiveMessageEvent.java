package com.qi.wechatclient.model.Event;

import com.qi.wechatclient.chat.enity.MessageInfo;

import java.util.List;

/**
 * Created by feng on 2017/7/23.
 */

public class ReceiveMessageEvent extends BaseEvent {
    public List<MessageInfo> mMessageInfos;

    public ReceiveMessageEvent(int result, int messageType,  List<MessageInfo> messageInfos) {
        super(result, messageType);
        mMessageInfos = messageInfos;
    }
}
