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


import com.qi.wechatclient.bo.Friend;
import com.qi.wechatclient.greendao.DaoMaster;
import com.qi.wechatclient.greendao.DaoSession;
import com.qi.wechatclient.manager.GreenDaoManager;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

/**
 * Created by 13324 on 2016/11/11.
 */
public class FriendDao {
    private static FriendDao mFriendDao;
    private com.qi.wechatclient.greendao.FriendDao mFriendGreenDao;

    private FriendDao() {
        DaoMaster daoMaster = new DaoMaster(GreenDaoManager.getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        mFriendGreenDao = daoSession.getFriendDao();
    }

    public static FriendDao getFriendDaoInstance() {
        if (mFriendDao == null) {
            synchronized (FriendDao.class) {
                if (mFriendDao == null) {
                    mFriendDao = new FriendDao();
                    return mFriendDao;
                }
            }
        }
        return mFriendDao;
    }

    /**
     * 插入一条记录
     *
     * @param friend
     */
    public void insertFriend(Friend friend) {
        getFriendDaoInstance();
        if (friend != null) {
            mFriendGreenDao.insertOrReplaceInTx(friend);
        }
    }

    /**
     * 插入多條条记录
     */
    public void insertFriends(List<Friend> friends) {
        getFriendDaoInstance();
        if (friends != null && !friends.isEmpty()) {
            //  mFriendGreenDao.insertInTx(friends);
            mFriendGreenDao.insertOrReplaceInTx(friends);
        }
    }

    /**
     * 删除一条记录
     *
     * @param friend
     */
    public void deleteFriend(Friend friend) {
        getFriendDaoInstance();
        if (friend != null) {
            mFriendGreenDao.delete(friend);
        }
    }

    /**
     * 删除全部记录
     */
    public void deleteAllFriend() {
        getFriendDaoInstance();
        mFriendGreenDao.deleteAll();
    }

    /**
     * 删除多条记录
     *
     * @param friends
     */
    public void deleteAcconnts(List<Friend> friends) {
        getFriendDaoInstance();
        if (friends != null && !friends.isEmpty()) {
            mFriendGreenDao.deleteInTx(friends);
        }
    }


    /**
     * 更新一条记录
     *
     * @param friend
     */
    public void updateFriend(Friend friend) {
        getFriendDaoInstance();
        if (friend != null) {
            mFriendGreenDao.update(friend);
        }
    }

    /**
     * 更新多条记录
     *
     * @param friends
     */
    public void updateFriends(List<Friend> friends) {
        getFriendDaoInstance();
        if (friends != null && !friends.isEmpty()) {
            mFriendGreenDao.updateInTx(friends);
        }
    }

    /**
     * 查询用户某个好友
     */
    public Friend queryFriend(String userId, String friendID) {
        if (userId == null) {
            return null;
        }
        QueryBuilder<Friend> qb = mFriendGreenDao.queryBuilder();
        qb.where(qb.and(com.qi.wechatclient.greendao.FriendDao.Properties.UserID.eq(userId), com.qi.wechatclient.greendao.FriendDao.Properties.FriendID.eq(friendID)));
        List<Friend> friends = qb.list();
        if (friends != null && !friends.isEmpty()) {
            return friends.get(0);
        }
        return null;
    }

    /**
     * 查询用户好友列表
     */
    public List<Friend> queryFriendList(String userID) {
        if (userID == null) {
            return null;
        }
        QueryBuilder<Friend> qb = mFriendGreenDao.queryBuilder();
        qb.where(com.qi.wechatclient.greendao.FriendDao.Properties.UserID.eq(userID));
        List<Friend> list = qb.list();
        return list;
    }


}
