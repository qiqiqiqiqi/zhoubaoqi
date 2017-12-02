package com.qi.wechatclient.packet;

import com.qi.wechatclient.utils.DataTypeTranslater;

import java.io.IOException;
import java.util.Arrays;


/**
 * 客户端要发给服务器的包
 *
 * @author Feng
 */
public class PacketFromClient {
    private byte[] messageID = null;
    private int messageType = -1;
    private byte[] messageBoty = null;


    public PacketFromClient(byte[] messageID, int messageType, byte[] messageBoty) {
        setMessageID(messageID);
        setMessageType(messageType);
        setMessageBody(messageBoty);
    }

    public byte[] getMessageID() {
        return messageID;
    }

    public void setMessageID(byte[] messageID) {
        this.messageID = messageID;
    }

    public int getMessageType() {
        return messageType;
    }

    public byte[] getMessageTypeBytes() throws IOException {
        return DataTypeTranslater.intToByte(getMessageType());
    }

    public void setMessageType(int messageType) {
        this.messageType = messageType;
    }

    public byte[] getMessageBody() {
        return messageBoty;
    }

    public void setMessageBody(byte[] messageBoty) {
        this.messageBoty = messageBoty;
    }

    @Override
    public String toString() {
        return "PacketFromClient{" +
                "messageID=" + Arrays.toString(messageID) +
                ", messageType=" + messageType +
                ", messageBoty=" + Arrays.toString(messageBoty) +
                '}';
    }
}
