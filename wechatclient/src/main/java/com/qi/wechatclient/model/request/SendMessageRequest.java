package com.qi.wechatclient.model.request;

import com.qi.wechatclient.chat.enity.MessageInfo;
import com.qi.wechatclient.chat.util.Constants;
import com.qi.wechatclient.dao.MessageInfoDao;
import com.qi.wechatclient.data.ErrorCode;
import com.qi.wechatclient.manager.SessionManager;
import com.qi.wechatclient.model.Event.SendMessageEvent;
import com.qi.wechatclient.packet.PacketFromClient;
import com.qi.wechatclient.utils.DataTypeTranslater;
import com.qi.wechatclient.utils.LogUtil;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.UnsupportedEncodingException;

import protocol.ChatData;
import protocol.ProtoHead;
import protocol.SendChatMsg;

/**
 * Created by feng on 2017/6/20.
 */

public abstract class SendMessageRequest extends BaseRequest {
    public SendMessageRequest() {
        super();
    }

    private static final String TAG = SendMessageRequest.class.getSimpleName();

    public void startSendMessageRequest(MessageInfo messageInfo) {
        ChatData.ChatItem.Builder chatDataBuilder = ChatData.ChatItem.newBuilder();
        chatDataBuilder.setSendUserId(messageInfo.getUserID());
        chatDataBuilder.setReceiveUserId(messageInfo.getFriendID());
        chatDataBuilder.setChatType(ChatData.ChatItem.ChatType.TEXT);
        chatDataBuilder.setChatBody(messageInfo.getContent());
        chatDataBuilder.setMessageInfoID(messageInfo.getMessageInfoID());
        ChatData.ChatItem chatItem = chatDataBuilder.build();
        SendChatMsg.SendChatReq.Builder sendMessageBuilder = SendChatMsg.SendChatReq.newBuilder();
        sendMessageBuilder.setChatData(chatItem);
        SendChatMsg.SendChatReq build = sendMessageBuilder.build();
        byte[] bytes = build.toByteArray();
        PacketFromClient packetFromClient = new PacketFromClient(DataTypeTranslater.floatToBytes((float) Math.random()), ProtoHead.ENetworkMessage.SEND_CHAT_REQ.getNumber(), bytes);
        try {
            LogUtil.d(TAG, "startLoginRequest():packetFromClient=" +new String(packetFromClient.getMessageBody(),"UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        SessionManager.getInstance().writeToServer(packetFromClient);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public final void onEventMainThread(SendMessageEvent event) {
        if (event != null) {
            if (event.result == ErrorCode.SUCCESS) {
                MessageInfo messageInfo = MessageInfoDao.getMessageInfoDaoInstance().queryMessageInfoByMessageInfoID(event.messageInfoID);
                messageInfo.setSendState(Constants.CHAT_ITEM_SEND_SUCCESS);
                MessageInfoDao.getMessageInfoDaoInstance().updateMessageInfo(messageInfo);
            }
            onSendMessageRequestResult(event.result, event.messageType, event.messageInfoID);
        }

    }

    public abstract void onSendMessageRequestResult(int result, int messageType, String messageInfoID);
}
