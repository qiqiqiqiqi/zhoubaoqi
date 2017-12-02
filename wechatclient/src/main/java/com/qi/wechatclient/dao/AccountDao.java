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



import com.qi.wechatclient.bo.Account;
import com.qi.wechatclient.greendao.DaoMaster;
import com.qi.wechatclient.greendao.DaoSession;
import com.qi.wechatclient.manager.GreenDaoManager;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

/**
 * Created by 13324 on 2016/11/11.
 */
public class AccountDao {
    private static AccountDao mAccountDao;
    private com.qi.wechatclient.greendao.AccountDao  mAccountGreenDao;

    private AccountDao() {
        DaoMaster daoMaster = new DaoMaster(GreenDaoManager.getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        mAccountGreenDao = daoSession.getAccountDao();
    }

    public static AccountDao getAccountDaoInstance() {
        if (mAccountDao == null) {
            synchronized (AccountDao.class) {
                if (mAccountDao == null) {
                    mAccountDao = new AccountDao();
                    return mAccountDao;
                }
            }
        }
        return mAccountDao;
    }

    /**
     * 插入一条记录
     *
     * @param account
     */
    public void insertAccount(Account account) {
        getAccountDaoInstance();
        if (account != null) {
            mAccountGreenDao.insertOrReplaceInTx(account);
        }
    }

    /**
     * 插入多條条记录
     */
    public void insertAccounts(List<Account> accounts) {
        getAccountDaoInstance();
        if (accounts != null && !accounts.isEmpty()) {
            //  mAccountGreenDao.insertInTx(accounts);
            mAccountGreenDao.insertOrReplaceInTx(accounts);
        }
    }

    /**
     * 删除一条记录
     *
     * @param account
     */
    public void deleteAccount(Account account) {
        getAccountDaoInstance();
        if (account != null) {
            mAccountGreenDao.delete(account);
        }
    }
    /**
     * 删除全部记录
     *
     */
    public void deleteAllAccount() {
        getAccountDaoInstance();
            mAccountGreenDao.deleteAll();
    }

    /**
     * 删除多条记录
     *
     * @param accounts
     */
    public void deleteAcconnts(List<Account> accounts) {
        getAccountDaoInstance();
        if (accounts != null && !accounts.isEmpty()) {
            mAccountGreenDao.deleteInTx(accounts);
        }
    }


    /**
     * 更新一条记录
     *
     * @param account
     */
    public void updateAccount(Account account) {
        getAccountDaoInstance();
        if (account != null) {
            mAccountGreenDao.update(account);
        }
    }

    /**
     * 更新多条记录
     *
     * @param accounts
     */
    public void updateAccounts(List<Account> accounts) {
        getAccountDaoInstance();
        if (accounts != null && !accounts.isEmpty()) {
            mAccountGreenDao.updateInTx(accounts);
        }
    }

    /**
     * 查询用户列表
     */
    public Account queryAccount(String userId) {
        if (userId == null) {
            return null;
        }
        QueryBuilder<Account> qb = mAccountGreenDao.queryBuilder();
        qb.where(com.qi.wechatclient.greendao.AccountDao .Properties.UserID.eq(userId));
        List<Account> accounts = qb.list();
        if (accounts != null && !accounts.isEmpty()) {
            return accounts.get(0);
        }
        return null;
    }

    /**
     * 查询用户列表
     */
    public List<Account> queryAccountList() {
        QueryBuilder<Account> qb = mAccountGreenDao.queryBuilder();
        List<Account> list = qb.list();
        return list;
    }


}
