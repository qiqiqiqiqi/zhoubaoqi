package com.qi.wechatclient.model.request;

import com.qi.wechatclient.bo.Friend;
import com.qi.wechatclient.dao.FriendDao;
import com.qi.wechatclient.data.ErrorCode;
import com.qi.wechatclient.manager.SessionManager;
import com.qi.wechatclient.model.Event.ReadTableEvent;
import com.qi.wechatclient.packet.PacketFromClient;
import com.qi.wechatclient.utils.DataTypeTranslater;
import com.qi.wechatclient.utils.LogUtil;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import protocol.LoginMsg;
import protocol.ProtoHead;
import protocol.ReadTable;

/**
 * 读重要的表：用户登录成功后向后台读取重要的表（好友表）
 * Created by feng on 2017/6/20.
 */

public abstract class ReadTableRequest extends BaseRequest {
    public ReadTableRequest() {
        super();
    }

    private static final String TAG = ReadTableRequest.class.getSimpleName();

    public void startReadTableRequest(String userID) {

        ReadTable.ReadImportantTableReq.Builder readImportantTableBuilder = ReadTable.ReadImportantTableReq.newBuilder();
        readImportantTableBuilder.setUserID(userID);
        ReadTable.ReadImportantTableReq readImportantTablebuild = readImportantTableBuilder.build();
        byte[] bytes = readImportantTablebuild.toByteArray();
        PacketFromClient packetFromClient = new PacketFromClient(DataTypeTranslater.floatToBytes((float) Math.random()), ProtoHead.ENetworkMessage.GET_FRIEND_LIST_REQ.getNumber(), bytes);
        LogUtil.d(TAG, "startReadTableRequest():packetFromClient=" + packetFromClient);
        SessionManager.getInstance().writeToServer(packetFromClient);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public final void onEventMainThread(ReadTableEvent event) {
        if (event != null) {
            if (event.result == ErrorCode.SUCCESS) {
                unregisterEvent(this);
                if (event.mFriends != null && !event.mFriends.isEmpty()) {
                    FriendDao friendDaoInstance = FriendDao.getFriendDaoInstance();
                    friendDaoInstance.insertFriends(event.mFriends);
                }
            }

            onReadTableRequestResult(event.result, event.messageType, event.mFriends);
        }

    }

    public abstract void onReadTableRequestResult(int result, int messageType, List<Friend> friendList);
}
