package com.qi.wechatclient.bo;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Unique;

import java.io.Serializable;

/**
 * Created by feng on 2017/7/11.
 */
@Entity
public class Friend implements Serializable {
    private static final long serialVersionUID = 5998217472087639070L;
    private String userID;
    @Unique
    private String friendID;
    private String friendName;
    private String headPath;

    @Generated(hash = 721602969)
    public Friend(String userID, String friendID, String friendName,
                  String headPath) {
        this.userID = userID;
        this.friendID = friendID;
        this.friendName = friendName;
        this.headPath = headPath;
    }

    @Generated(hash = 287143722)
    public Friend() {
    }

    public String getUserID() {
        return this.userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getFriendID() {
        return this.friendID;
    }

    public void setFriendID(String friendID) {
        this.friendID = friendID;
    }

    public String getFriendName() {
        return this.friendName;
    }

    public void setFriendName(String friendName) {
        this.friendName = friendName;
    }

    public String getHeadPath() {
        return this.headPath;
    }

    public void setHeadPath(String headPath) {
        this.headPath = headPath;
    }

    @Override
    public String toString() {
        return "Friend{" +
                "userID='" + userID + '\'' +
                ", friendID='" + friendID + '\'' +
                ", friendName='" + friendName + '\'' +
                ", headPath='" + headPath + '\'' +
                '}';
    }
}
