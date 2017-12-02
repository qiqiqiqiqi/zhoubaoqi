package com.qi.wechatclient.cache;



/**
 * Created by 13324 on 2016/11/9.
 */
public class LoginCache {
    private final static String KEY_LOGIN = "login";

    public static void setLoginCache(boolean login) {
        if (UserCache.getAppUserId() != null) {
            AppSharedPreferences.putBoolean(getKeyLogin(), login);
        }
    }

    public static boolean getLoginCache() {
        return AppSharedPreferences.getBoolean(getKeyLogin());
    }

    public static String getKeyLogin() {
        return AppSharedPreferences.getKey(UserCache.getAppUserId(), KEY_LOGIN);
    }
}
