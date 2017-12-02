package com.qi.wechatclient.model.request;

import com.qi.wechatclient.bo.Account;
import com.qi.wechatclient.cache.UserCache;
import com.qi.wechatclient.data.ErrorCode;
import com.qi.wechatclient.manager.SessionManager;
import com.qi.wechatclient.model.Event.LoginEvent;
import com.qi.wechatclient.packet.PacketFromClient;
import com.qi.wechatclient.utils.DataTypeTranslater;
import com.qi.wechatclient.utils.LogUtil;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import protocol.LoginMsg;
import protocol.ProtoHead;
import protocol.Register;

/**
 * Created by feng on 2017/6/20.
 */

public abstract class LoginRequest extends BaseRequest {
    public LoginRequest() {
        super();
    }

    private static final String TAG = LoginRequest.class.getSimpleName();

    public void startLoginRequest(String userName, String md5Password) {

        LoginMsg.LoginReq.Builder loginBuilder = LoginMsg.LoginReq.newBuilder();
        loginBuilder.setUserName(userName);
        loginBuilder.setUserPassword(md5Password);
        LoginMsg.LoginReq build = loginBuilder.build();
        byte[] bytes = build.toByteArray();
        PacketFromClient packetFromClient = new PacketFromClient(DataTypeTranslater.floatToBytes((float) Math.random()), ProtoHead.ENetworkMessage.LOGIN_REQ.getNumber(), bytes);
        LogUtil.d(TAG, "startLoginRequest():packetFromClient=" + packetFromClient);
        SessionManager.getInstance().writeToServer(packetFromClient);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public final void onEventMainThread(LoginEvent event) {
        if (event != null) {
            if (event.result == ErrorCode.SUCCESS) {
                UserCache.setAppUserId(event.mAccount.getUserID());
                unregisterEvent(this);
            }
            onLoginRequestResult(event.result, event.messageType);
        }

    }

    public abstract void onLoginRequestResult(int result, int messageType);
}
