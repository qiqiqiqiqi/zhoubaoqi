package com.qi.wechatclient.model.Event;

import com.qi.wechatclient.bo.Account;

/**
 * Created by feng on 2017/7/2.
 */

public class LoginEvent extends BaseEvent {
    public Account mAccount;

    public LoginEvent(int result, int number, Account account) {
        super(result, number);
        mAccount = account;
    }
}
