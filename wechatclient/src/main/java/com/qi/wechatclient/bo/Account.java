package com.qi.wechatclient.bo;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Unique;

import java.io.Serializable;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by feng on 2017/6/20.
 */
@Entity
public class Account implements Serializable {
    private static final long serialVersionUID = 2592446524681489587L;
    @Unique
    private String userID;
    private String userName;
    private Long creatTime;

    @Generated(hash = 119029383)
    public Account(String userID, String userName, Long creatTime) {
        this.userID = userID;
        this.userName = userName;
        this.creatTime = creatTime;
    }

    @Generated(hash = 882125521)
    public Account() {
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Long creatTime) {
        this.creatTime = creatTime;
    }

    @Override
    public String toString() {
        return "Account{" +
                "userID='" + userID + '\'' +
                ", userName='" + userName + '\'' +
                ", creatTime=" + creatTime +
                '}';
    }
}
