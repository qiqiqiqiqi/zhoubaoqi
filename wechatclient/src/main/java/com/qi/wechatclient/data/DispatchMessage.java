package com.qi.wechatclient.data;

import com.qi.wechatclient.utils.DataTypeTranslater;
import com.qi.wechatclient.packet.NetworkPacket;
import com.qi.wechatclient.utils.LogUtil;

import java.io.UnsupportedEncodingException;

import protocol.ProtoHead;


/**
 * Created by feng on 2017/6/20.
 */

public class DispatchMessage {
    private static final String TAG = DispatchMessage.class.getSimpleName();

    public static void dispatchMessageFromClient(NetworkPacket networkPacket) {
        int messageID = DataTypeTranslater.bytesToInt(networkPacket.getMessageID(), 0);
        int messageType = networkPacket.getMessageType().getNumber();
        byte[] messageBody = networkPacket.getMessageObjectBytes();
        try {
            String bodyString = new String(messageBody, "UTF-8");
            LogUtil.d(TAG, "dispatchMessageFromClient():(receive data):messageID=" + messageID + ",messageType=" + messageType + ",messageBody=" + bodyString);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        switch (networkPacket.getMessageType().getNumber()) {
            case ProtoHead.ENetworkMessage.REGISTER_RSP_VALUE:
                ProcessPayload.register(networkPacket);
                break;
            case ProtoHead.ENetworkMessage.LOGIN_RSP_VALUE:
                ProcessPayload.login(networkPacket);
                break;
            case ProtoHead.ENetworkMessage.GET_FRIEND_LIST_RSP_VALUE:
                ProcessPayload.readTable(networkPacket);
                break;
            case ProtoHead.ENetworkMessage.SEND_CHAT_RSP_VALUE:
                ProcessPayload.sendMessageRsp(networkPacket);
                break;
            case ProtoHead.ENetworkMessage.RECEIVE_CHAT_SYNC_VALUE:
                ProcessPayload.receiveMessage(networkPacket);
                break;


        }
    }
}
