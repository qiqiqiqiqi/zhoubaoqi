package com.qi.wechatclient.cache;


/**
 * 一旦用户登录成功就保存userId
 * 当用户退出登录时就把该值置空
 * Created by 13324 on 2016/11/25.
 */

public class UserCache {
    public static final String LOGIN_APPUSERID = "appUserID";
    public static final String LOGIN_TYPE = "loginType";

    public static void setAppUserId(String userId) {
        AppSharedPreferences.putString(LOGIN_APPUSERID, userId);
    }

    public static String getAppUserId() {
        return AppSharedPreferences.getString(LOGIN_APPUSERID);
    }

    public static void setAppLoginType(String userId) {
        AppSharedPreferences.putString(LOGIN_TYPE, userId);
    }

    public static String getAppLoginType() {
        return AppSharedPreferences.getString(LOGIN_TYPE);
    }
}
