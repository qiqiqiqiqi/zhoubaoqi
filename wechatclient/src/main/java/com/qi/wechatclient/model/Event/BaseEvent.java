package com.qi.wechatclient.model.Event;

import java.io.Serializable;

/**
 * Created by feng on 2017/6/20.
 */

public class BaseEvent implements Serializable {
    public int result;
    public int messageType;

    public BaseEvent() {
    }

    public BaseEvent(int result, int messageType) {
        this.result = result;
        this.messageType = messageType;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public int getMessageType() {
        return messageType;
    }

    public void setMessageType(int messageType) {
        this.messageType = messageType;
    }

    @Override
    public String toString() {
        return "BaseEvent{" +
                "result=" + result +
                ", MessageType=" + messageType +
                '}';
    }
}
