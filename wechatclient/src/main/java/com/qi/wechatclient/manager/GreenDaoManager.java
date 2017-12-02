package com.qi.wechatclient.manager;

import android.database.sqlite.SQLiteDatabase;

import com.qi.wechatclient.common.WechatApplication;
import com.qi.wechatclient.greendao.DaoMaster;


/**
 * 數據庫的管理類
 * Created by baoqi on 2016/11/11.
 */
public class GreenDaoManager {
    public final static String DB_NAME = "wechat.db";
    private static DaoMaster.DevOpenHelper mDevOpenHelper;
    private static GreenDaoManager mGreenDaoManager;

    private GreenDaoManager() {
        mDevOpenHelper = new DaoMaster.DevOpenHelper(WechatApplication.getWechatApplication(), DB_NAME, null);
    }

    public static GreenDaoManager getGreenDaoManagerInstance() {
        if (mGreenDaoManager == null) {
            synchronized (GreenDaoManager.class) {
                if (mGreenDaoManager == null) {
                    mGreenDaoManager = new GreenDaoManager();
                    return mGreenDaoManager;
                }
            }
        }
        return mGreenDaoManager;
    }

    /**
     * 获取可读数据库
     */
    private SQLiteDatabase getReadableDatabase() {
        if (mDevOpenHelper == null || mGreenDaoManager == null) {
            GreenDaoManager.getGreenDaoManagerInstance();
        }
        SQLiteDatabase db = mDevOpenHelper.getReadableDatabase();
        return db;
    }

    /**
     * 获取可寫数据库
     * @return
     */
    public static  SQLiteDatabase getWritableDatabase(){
        if(mDevOpenHelper==null||mGreenDaoManager==null){
            GreenDaoManager.getGreenDaoManagerInstance();
        }
        SQLiteDatabase writableDatabase = mDevOpenHelper.getWritableDatabase();
        return writableDatabase;
    }


}
