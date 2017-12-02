package com.qi.wechatclient.model.Event;

import com.qi.wechatclient.bo.Friend;

import java.util.List;

/**
 * Created by feng on 2017/7/2.
 */

public class ReadTableEvent extends BaseEvent {
    public List<Friend> mFriends;

    public ReadTableEvent(int result, int number, List<Friend> friends) {
        super(result, number);
        mFriends = friends;
    }
}
