package com.qi.wechatclient.model.Event;

import com.qi.wechatclient.bo.Account;


/**
 * Created by feng on 2017/6/20.
 */

public class RegisterEvent extends BaseEvent {
    public Account mAccount;

    public RegisterEvent(int result, int messageType, Account account) {
        super(result, messageType);
        mAccount = account;
    }
}
