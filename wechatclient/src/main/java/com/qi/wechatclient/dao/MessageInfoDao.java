package com.qi.wechatclient.dao;


/**
 * 1.）实体@Entity注解
 * schema：告知GreenDao当前实体属于哪个schema
 * active：标记一个实体处于活动状态，活动实体有更新、删除和刷新方法
 * nameInDb：在数据中使用的别名，默认使用的是实体的类名
 * indexes：定义索引，可以跨越多个列
 * createInDb：标记创建数据库表
 * 2.）基础属性注解
 *
 * @Id :主键 Long型，可以通过@Id(autoincrement = true)设置自增长
 * @Property：设置一个非默认关系映射所对应的列名，默认是的使用字段名 举例：@Property (nameInDb="name")
 * @NotNul：设置数据库表当前列不能为空
 * @Transient ：添加次标记之后不会生成数据库表的列 3.)索引注解
 * @Index：使用@Index作为一个属性来创建一个索引，通过name设置索引别名，也可以通过unique给索引添加约束
 * @Unique：向数据库列添加了一个唯一的约束 7.）关系注解
 * @ToOne：定义与另一个实体（一个实体对象）的关系
 * @ToMany：定义与多个实体对象的关系
 */


import com.qi.wechatclient.chat.enity.MessageInfo;
import com.qi.wechatclient.greendao.DaoMaster;
import com.qi.wechatclient.greendao.DaoSession;
import com.qi.wechatclient.manager.GreenDaoManager;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by 13324 on 2016/11/11.
 */
public class MessageInfoDao {
    private static MessageInfoDao mMessageInfoDao;
    private com.qi.wechatclient.greendao.MessageInfoDao mMessageInfoGreenDao;

    private MessageInfoDao() {
        DaoMaster daoMaster = new DaoMaster(GreenDaoManager.getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        mMessageInfoGreenDao = daoSession.getMessageInfoDao();
    }

    public static MessageInfoDao getMessageInfoDaoInstance() {
        if (mMessageInfoDao == null) {
            synchronized (MessageInfoDao.class) {
                if (mMessageInfoDao == null) {
                    mMessageInfoDao = new MessageInfoDao();
                    return mMessageInfoDao;
                }
            }
        }
        return mMessageInfoDao;
    }

    /**
     * 插入一条记录
     *
     * @param messageInfo
     */
    public void insertMessageInfo(MessageInfo messageInfo) {
        getMessageInfoDaoInstance();
        if (messageInfo != null) {
            mMessageInfoGreenDao.insertOrReplaceInTx(messageInfo);
        }
    }

    /**
     * 插入多條条记录
     */
    public void insertMessageInfos(List<MessageInfo> messageInfos) {
        getMessageInfoDaoInstance();
        if (messageInfos != null && !messageInfos.isEmpty()) {
            //  mMessageInfoGreenDao.insertInTx(messageInfos);
            mMessageInfoGreenDao.insertOrReplaceInTx(messageInfos);
        }
    }

    /**
     * 删除一条记录
     *
     * @param messageInfo
     */
    public void deleteMessageInfo(MessageInfo messageInfo) {
        getMessageInfoDaoInstance();
        if (messageInfo != null) {
            mMessageInfoGreenDao.delete(messageInfo);
        }
    }

    /**
     * 删除用户与某个好友的全部聊天记录
     */
    public void deleteMessageInfos(String userID, String friendID) {
        getMessageInfoDaoInstance();
        List<MessageInfo> messageInfos = queryMessageInfo(userID, friendID);
        if (messageInfos != null && !messageInfos.isEmpty()) {
            mMessageInfoGreenDao.deleteInTx(messageInfos);
        }
    }

    /**
     * 删除全部记录
     */
    public void deleteAllMessageInfo() {
        getMessageInfoDaoInstance();
        mMessageInfoGreenDao.deleteAll();
    }

    /**
     * 删除多条记录
     *
     * @param messageInfos
     */
    public void deleteMessageInfos(List<MessageInfo> messageInfos) {
        getMessageInfoDaoInstance();
        if (messageInfos != null && !messageInfos.isEmpty()) {
            mMessageInfoGreenDao.deleteInTx(messageInfos);
        }
    }


    /**
     * 更新一条记录
     *
     * @param messageInfo
     */
    public void updateMessageInfo(MessageInfo messageInfo) {
        getMessageInfoDaoInstance();
        if (messageInfo != null) {
            mMessageInfoGreenDao.update(messageInfo);
        }
    }

    /**
     * 更新多条记录
     *
     * @param messageInfos
     */
    public void updateMessageInfos(List<MessageInfo> messageInfos) {
        getMessageInfoDaoInstance();
        if (messageInfos != null && !messageInfos.isEmpty()) {
            mMessageInfoGreenDao.updateInTx(messageInfos);
        }
    }

    /**
     * 查询用户与某个好友的聊天信息
     */
    public List<MessageInfo> queryMessageInfo(String userId, String friendID) {
        if (userId == null) {
            return null;
        }
        QueryBuilder<MessageInfo> qb = mMessageInfoGreenDao.queryBuilder();
        qb.where(qb.and(com.qi.wechatclient.greendao.MessageInfoDao.Properties.UserID.eq(userId), com.qi.wechatclient.greendao.MessageInfoDao.Properties.FriendID.eq(friendID)));
        List<MessageInfo> messageInfos = qb.list();
        return messageInfos;
    }

    /**
     * 查询用户与好友们的最近聊天信息(根据时间降序)
     */
    public List<MessageInfo> queryCurrentFriendsMessageInfo(String userId) {
        if (userId == null) {
            return null;
        }
        ConcurrentHashMap<String, MessageInfo> messageInfoConcurrentHashMap = new ConcurrentHashMap<>();
        List<MessageInfo> messageInfoList = new ArrayList<>();
        QueryBuilder<MessageInfo> qb = mMessageInfoGreenDao.queryBuilder();
        qb.where(com.qi.wechatclient.greendao.MessageInfoDao.Properties.UserID.eq(userId)).orderDesc(com.qi.wechatclient.greendao.MessageInfoDao.Properties.CreateTime);
        List<MessageInfo> messageInfos = qb.list();
        if (messageInfos != null && !messageInfos.isEmpty()) {
            for (MessageInfo messageInfo : messageInfos) {
                if (!messageInfoConcurrentHashMap.containsKey(messageInfo.getFriendID())) {
                    messageInfoConcurrentHashMap.put(messageInfo.getFriendID(), messageInfo);
                }
            }
            for (Map.Entry<String, MessageInfo> entry : messageInfoConcurrentHashMap.entrySet()) {
                messageInfoList.add(entry.getValue());
            }

        }
        return messageInfoList;
    }

    /**
     * @param messageInfoID
     * @return
     */
    public MessageInfo queryMessageInfoByMessageInfoID(String messageInfoID) {
        if (messageInfoID == null) {
            return null;
        }
        QueryBuilder<MessageInfo> qb = mMessageInfoGreenDao.queryBuilder();
        qb.where(com.qi.wechatclient.greendao.MessageInfoDao.Properties.MessageInfoID.eq(messageInfoID));
        List<MessageInfo> messageInfos = qb.list();
        if (messageInfos != null && !messageInfos.isEmpty()) {
            return messageInfos.get(0);
        }
        return null;
    }
}
