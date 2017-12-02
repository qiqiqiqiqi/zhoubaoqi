package com.qi.wechatclient.model.request;

import com.qi.wechatclient.utils.DataTypeTranslater;
import com.qi.wechatclient.bo.Account;
import com.qi.wechatclient.dao.AccountDao;
import com.qi.wechatclient.data.ErrorCode;
import com.qi.wechatclient.manager.SessionManager;
import com.qi.wechatclient.model.Event.RegisterEvent;
import com.qi.wechatclient.packet.PacketFromClient;
import com.qi.wechatclient.utils.LogUtil;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import protocol.ProtoHead;
import protocol.Register;

/**
 * Created by feng on 2017/6/20.
 */

public abstract class RegisterRequest extends BaseRequest {
    public RegisterRequest() {
        super();
    }

    private static final String TAG = RegisterRequest.class.getSimpleName();

    public void startRegisterRequest(String userName, String md5Password) {
        Register.RegisterReq.Builder registerBuilder = Register.RegisterReq.newBuilder();
        registerBuilder.setUserName(userName);
        registerBuilder.setUserPassword(md5Password);
        Register.RegisterReq registerBuild = registerBuilder.build();
        byte[] bytes = registerBuild.toByteArray();
        PacketFromClient packetFromClient = new PacketFromClient(DataTypeTranslater.floatToBytes((float) Math.random()), ProtoHead.ENetworkMessage.REGISTER_REQ.getNumber(), bytes);
        LogUtil.d(TAG, "startRegisterRequest():packetFromClient=" + packetFromClient);
        SessionManager.getInstance().writeToServer(packetFromClient);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public final void onEventMainThread(RegisterEvent event) {
        if (event != null) {
            if (event.result == ErrorCode.SUCCESS) {
                unregisterEvent(this);
                AccountDao.getAccountDaoInstance().insertAccount(event.mAccount);
            }
            onRegisterRequestResult(event.result, event.messageType, event.mAccount);
        }

    }

    public abstract void onRegisterRequestResult(int result, int messageType, Account account);
}
