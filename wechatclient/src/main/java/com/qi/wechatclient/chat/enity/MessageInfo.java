package com.qi.wechatclient.chat.enity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Unique;
import org.greenrobot.greendao.annotation.Generated;

import java.io.Serializable;

/**
 * 作者：Rance on 2016/12/14 14:13
 * 邮箱：rance935@163.com
 */
@Entity
public class MessageInfo implements Serializable {
    private static final long serialVersionUID = 3633985418526089927L;
    @Id
    private Long id;
    private String userID;
    private String friendID;
    private int type;
    private String content;
    private String filepath;
    private int sendState;
    private String time;
    private String header;
    private String imageUrl;
    private long voiceTime;
    private String createTime;
    @Unique
    private String messageInfoID;

    @Generated(hash = 803517190)
    public MessageInfo(Long id, String userID, String friendID, int type,
            String content, String filepath, int sendState, String time,
            String header, String imageUrl, long voiceTime, String createTime,
            String messageInfoID) {
        this.id = id;
        this.userID = userID;
        this.friendID = friendID;
        this.type = type;
        this.content = content;
        this.filepath = filepath;
        this.sendState = sendState;
        this.time = time;
        this.header = header;
        this.imageUrl = imageUrl;
        this.voiceTime = voiceTime;
        this.createTime = createTime;
        this.messageInfoID = messageInfoID;
    }

    @Generated(hash = 1292770546)
    public MessageInfo() {
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

    public int getType() {
        return this.type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFilepath() {
        return this.filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public int getSendState() {
        return this.sendState;
    }

    public void setSendState(int sendState) {
        this.sendState = sendState;
    }

    public String getTime() {
        return this.time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getHeader() {
        return this.header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public long getVoiceTime() {
        return this.voiceTime;
    }

    public void setVoiceTime(long voiceTime) {
        this.voiceTime = voiceTime;
    }

    public String getMessageInfoID() {
        return this.messageInfoID;
    }

    public void setMessageInfoID(String messageInfoID) {
        this.messageInfoID = messageInfoID;
    }

    public String getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "MessageInfo{" +
                "userID='" + userID + '\'' +
                ", friendID='" + friendID + '\'' +
                ", type=" + type +
                ", content='" + content + '\'' +
                ", filepath='" + filepath + '\'' +
                ", sendState=" + sendState +
                ", time='" + time + '\'' +
                ", header='" + header + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", voiceTime=" + voiceTime +
                ", createTime='" + createTime + '\'' +
                ", messageInfoID='" + messageInfoID + '\'' +
                '}';
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
