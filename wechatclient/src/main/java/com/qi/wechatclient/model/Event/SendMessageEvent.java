package com.qi.wechatclient.model.Event;

/**
 * Created by feng on 2017/7/23.
 */

public class SendMessageEvent extends BaseEvent {
    public String messageInfoID;
    public SendMessageEvent(int result, int messageType,String messageInfoID) {
        super(result, messageType);
        this.messageInfoID=messageInfoID;
    }
}
