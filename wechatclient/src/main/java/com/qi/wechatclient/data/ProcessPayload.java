package com.qi.wechatclient.data;

import com.google.protobuf.InvalidProtocolBufferException;
import com.qi.wechatclient.bo.Account;
import com.qi.wechatclient.chat.enity.MessageInfo;
import com.qi.wechatclient.chat.util.Constants;
import com.qi.wechatclient.dao.MessageInfoDao;
import com.qi.wechatclient.model.Event.LoginEvent;
import com.qi.wechatclient.model.Event.ReadTableEvent;
import com.qi.wechatclient.model.Event.ReceiveMessageEvent;
import com.qi.wechatclient.model.Event.RegisterEvent;
import com.qi.wechatclient.model.Event.SendMessageEvent;
import com.qi.wechatclient.packet.NetworkPacket;
import com.qi.wechatclient.pushReport.MessagePushReport.MessagePushReport;
import com.qi.wechatclient.utils.LogUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import protocol.ChatData;
import protocol.Friend;
import protocol.LoginMsg;
import protocol.ReadTable;
import protocol.ReceiveChatMsg;
import protocol.Register;
import protocol.Result;
import protocol.SendChatMsg;
import protocol.UserData;

/**
 * Created by feng on 2017/6/21.
 */

public class ProcessPayload {
    private static final String TAG = ProcessPayload.class.getSimpleName();

    /**
     * 用户注册
     *
     * @param networkPacket
     */
    public static void register(NetworkPacket networkPacket) {
        byte[] messageID = networkPacket.getMessageID();
//        Register.RegisterRsp.Builder registerRspBuilder = Register.RegisterRsp.newBuilder();
//        Register.RegisterRsp registerRspBuild = registerRspBuilder.build();
        int result = ErrorCode.FAIL;
        Account account = new Account();
        try {
            Register.RegisterRsp registerRsp = Register.RegisterRsp.parseFrom(networkPacket.getMessageObjectBytes());
            result = registerRsp.getResultCode().getNumber();
            if (result == Result.ResultCode.SUCCESS.getNumber()) {
                UserData.UserItem userItem = registerRsp.getUserItem();
                account.setUserID(userItem.getUserId());
                account.setUserName(userItem.getUserName());
                account.setCreatTime(userItem.getCreatTime());
            }
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }
        LogUtil.d(TAG, "register():result=" + result + ",account=" + account);
        EventBus.getDefault().post(new RegisterEvent(result, networkPacket.getMessageType().getNumber(), account));
    }

    /**
     * 用户登录
     *
     * @param networkPacket
     */
    public static void login(NetworkPacket networkPacket) {
        int result = ErrorCode.FAIL;
        Account account = null;
        try {
            LoginMsg.LoginRsp loginRsp = LoginMsg.LoginRsp.parseFrom(networkPacket.getMessageObjectBytes());
            result = loginRsp.getResultCode().getNumber();

            if (result == Result.ResultCode.SUCCESS.getNumber()) {
                account = new Account();
                UserData.UserItem userItem = loginRsp.getUserItem();
                account.setUserID(userItem.getUserId());
                account.setUserName(userItem.getUserName());
                account.setCreatTime(userItem.getCreatTime());
            }
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }
        LogUtil.d(TAG, "login():result=" + result);
        EventBus.getDefault().post(new LoginEvent(result, networkPacket.getMessageType().getNumber(), account));
    }

    /**
     * 读表
     *
     * @param networkPacket
     */
    public static void readTable(NetworkPacket networkPacket) {
        int result = ErrorCode.FAIL;
        List<com.qi.wechatclient.bo.Friend> friends = new ArrayList<>();
        try {
            ReadTable.ReadImportantTableRsp readImportantTableRsp = ReadTable.ReadImportantTableRsp.parseFrom(networkPacket.getMessageObjectBytes());
            result = readImportantTableRsp.getResultCode().getNumber();
            if (result == Result.ResultCode.SUCCESS.getNumber()) {
                List<Friend.FriendItem> friendList = readImportantTableRsp.getFriendList();
                for (Friend.FriendItem friendItem : friendList) {
                    com.qi.wechatclient.bo.Friend friend = new com.qi.wechatclient.bo.Friend();
                    friend.setUserID(friendItem.getUserId());
                    friend.setFriendID(friendItem.getFriendId());
                    friend.setFriendName(friendItem.getFriendName());
                    friend.setHeadPath(friendItem.getHeadPath());
                    friends.add(friend);
                }
            }
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }
        LogUtil.d(TAG, "readTable():result=" + result + ",friends=" + friends);
        EventBus.getDefault().post(new ReadTableEvent(result, networkPacket.getMessageType().getNumber(), friends));
    }

    /**
     * 发送聊天消息
     *
     * @param networkPacket
     */
    public static void sendMessageRsp(NetworkPacket networkPacket) {
        int result = ErrorCode.FAIL;
        String messageInfoID = "";
        try {
            SendChatMsg.SendChatRsp sendChatRsp = SendChatMsg.SendChatRsp.parseFrom(networkPacket.getMessageObjectBytes());
            result = sendChatRsp.getResultCode().getNumber();
            if (result == ErrorCode.SUCCESS) {
                messageInfoID = sendChatRsp.getMessageInfoID();
            }
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }
        LogUtil.d(TAG, "sendMessageRsp():result=" + result + ",messageInfoID=" + messageInfoID);
        EventBus.getDefault().post(new SendMessageEvent(result, networkPacket.getMessageType().getNumber(), messageInfoID));
    }

    /**
     * 接收消息（）
     *
     * @param networkPacket
     */
    public static void receiveMessage(NetworkPacket networkPacket) {
        int result = ErrorCode.SUCCESS;
        List<MessageInfo> messageInfos = new ArrayList<>();
        try {
            ReceiveChatMsg.ReceiveChatSync receiveChatSync = ReceiveChatMsg.ReceiveChatSync.parseFrom(networkPacket.getMessageObjectBytes());
            List<ChatData.ChatItem> chatDataList = receiveChatSync.getChatDataList();
            if (chatDataList != null && !chatDataList.isEmpty()) {
                for (ChatData.ChatItem chatItem : chatDataList) {
                    MessageInfo messageInfo = new MessageInfo();
                    messageInfo.setUserID(chatItem.getReceiveUserId());
                    messageInfo.setFriendID(chatItem.getSendUserId());
                    messageInfo.setContent(chatItem.getChatBody());
                    messageInfo.setSendState(Constants.CHAT_ITEM_SEND_SUCCESS);
                    messageInfo.setCreateTime(chatItem.getDate() + "");
                    messageInfo.setType(Constants.CHAT_ITEM_TYPE_LEFT);
                    messageInfo.setHeader("http://tupian.enterdesk.com/2014/mxy/11/2/1/12.jpg");
                    messageInfos.add(messageInfo);
                }
            }
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }
        LogUtil.d(TAG, "receiveMessage():messageInfos=" + messageInfos);
        EventBus.getDefault().post(new ReceiveMessageEvent(result, networkPacket.getMessageType().getNumber(), messageInfos));

    }
}
