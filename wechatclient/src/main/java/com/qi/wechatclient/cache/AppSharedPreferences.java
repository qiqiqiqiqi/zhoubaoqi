package com.qi.wechatclient.cache;

import android.content.Context;
import android.content.SharedPreferences;


import com.qi.wechatclient.common.WechatApplication;
import com.qi.wechatclient.config.Constants;

/**
 * Created by baoqi on 2016/11/9.
 */
public class AppSharedPreferences {
    private static AppSharedPreferences mAppSharedPreferences;
    private SharedPreferences mSharedPreferences;
    private static Context mContext = WechatApplication.getWechatApplication();
    ////区分在同一个手机上不同登陆用户
    private static String userId;

    private AppSharedPreferences(Context context) {
        mSharedPreferences = context.getSharedPreferences(Constants.SPF_NAME, Context.MODE_PRIVATE);
    }

    /**
     * SharedPreferences设置成全局唯一
     *
     * @param context
     * @return
     */
    public static AppSharedPreferences getAppSharedPreferencesInstance(Context context) {
        if (mAppSharedPreferences == null) {
            synchronized (AppSharedPreferences.class) {
                if (mAppSharedPreferences == null) {
                    mAppSharedPreferences = new AppSharedPreferences(context);
                    return mAppSharedPreferences;
                }
            }
        }
        return mAppSharedPreferences;
    }

    /**
     * put string preferences
     *
     * @param key   The name of the preference to modify
     * @param value The new value for the preference
     * @return True if the new values were successfully written to persistent storage.
     */
    public static boolean putString(String key, String value) {
        SharedPreferences settings = getAppSharedPreferencesInstance(mContext).mSharedPreferences;
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(key, value);
        return editor.commit();
    }

    /**
     * get string preferences
     *
     * @param key The name of the preference to retrieve
     * @return The preference value if it exists, or null. Throws ClassCastException if there is a preference with this
     * name that is not a string
     */
    public static String getString(String key) {
        return getString(key, null);
    }

    /**
     * get string preferences
     *
     * @param key          The name of the preference to retrieve
     * @param defaultValue Value to return if this preference does not exist
     * @return The preference value if it exists, or defValue. Throws ClassCastException if there is a preference with
     * this name that is not a string
     */
    public static String getString(String key, String defaultValue) {
        SharedPreferences settings = getAppSharedPreferencesInstance(mContext).mSharedPreferences;
        return settings.getString(key, defaultValue);
    }

    /**
     * put int preferences
     *
     * @param key   The name of the preference to modify
     * @param value The new value for the preference
     * @return True if the new values were successfully written to persistent storage.
     */
    public static boolean putInt(String key, int value) {
        SharedPreferences settings = getAppSharedPreferencesInstance(mContext).mSharedPreferences;
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt(key, value);
        return editor.commit();
    }

    /**
     * get int preferences
     *
     * @param key The name of the preference to retrieve
     * @return The preference value if it exists, or -1. Throws ClassCastException if there is a preference with this
     * name that is not a int
     */
    public static int getInt(String key) {
        return getInt(key, -1);
    }

    /**
     * get int preferences
     *
     * @param key          The name of the preference to retrieve
     * @param defaultValue Value to return if this preference does not exist
     * @return The preference value if it exists, or defValue. Throws ClassCastException if there is a preference with
     * this name that is not a int
     */
    public static int getInt(String key, int defaultValue) {
        SharedPreferences settings = getAppSharedPreferencesInstance(mContext).mSharedPreferences;
        return settings.getInt(key, defaultValue);
    }

    /**
     * put long preferences
     *
     * @param key   The name of the preference to modify
     * @param value The new value for the preference
     * @return True if the new values were successfully written to persistent storage.
     */
    public static boolean putLong(String key, long value) {
        SharedPreferences settings = getAppSharedPreferencesInstance(mContext).mSharedPreferences;
        SharedPreferences.Editor editor = settings.edit();
        editor.putLong(key, value);
        return editor.commit();
    }

    /**
     * get long preferences
     *
     * @param key The name of the preference to retrieve
     * @return The preference value if it exists, or -1. Throws ClassCastException if there is a preference with this
     * name that is not a long
     */
    public static long getLong(String key) {
        return getLong(key, -1);
    }

    /**
     * get long preferences
     *
     * @param key          The name of the preference to retrieve
     * @param defaultValue Value to return if this preference does not exist
     * @return The preference value if it exists, or defValue. Throws ClassCastException if there is a preference with
     * this name that is not a long
     */
    public static long getLong(String key, long defaultValue) {
        SharedPreferences settings = getAppSharedPreferencesInstance(mContext).mSharedPreferences;
        return settings.getLong(key, defaultValue);
    }

    /**
     * put float preferences
     *
     * @param key   The name of the preference to modify
     * @param value The new value for the preference
     * @return True if the new values were successfully written to persistent storage.
     */
    public static boolean putFloat(String key, float value) {
        SharedPreferences settings = getAppSharedPreferencesInstance(mContext).mSharedPreferences;
        SharedPreferences.Editor editor = settings.edit();
        editor.putFloat(key, value);
        return editor.commit();
    }

    /**
     * get float preferences
     *
     * @param key The name of the preference to retrieve
     * @return The preference value if it exists, or -1. Throws ClassCastException if there is a preference with this
     * name that is not a float
     * @see #getFloat(String, float)
     */
    public static float getFloat(String key) {
        return getFloat(key, -1);
    }

    /**
     * get float preferences
     *
     * @param key          The name of the preference to retrieve
     * @param defaultValue Value to return if this preference does not exist
     * @return The preference value if it exists, or defValue. Throws ClassCastException if there is a preference with
     * this name that is not a float
     */
    public static float getFloat(String key, float defaultValue) {
        SharedPreferences settings = getAppSharedPreferencesInstance(mContext).mSharedPreferences;
        return settings.getFloat(key, defaultValue);
    }

    /**
     * put boolean preferences
     *
     * @param key   The name of the preference to modify
     * @param value The new value for the preference
     * @return True if the new values were successfully written to persistent storage.
     */
    public static boolean putBoolean(String key, boolean value) {
        SharedPreferences settings = getAppSharedPreferencesInstance(mContext).mSharedPreferences;
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean(key, value);
        return editor.commit();
    }

    /**
     * get boolean preferences, default is false
     *
     * @param key The name of the preference to retrieve
     * @return The preference value if it exists, or false. Throws ClassCastException if there is a preference with this
     * name that is not a boolean
     * @see #getBoolean(String, boolean)
     */
    public static boolean getBoolean(String key) {
        return getBoolean(key, false);
    }

    /**
     * get boolean preferences
     *
     * @param key          The name of the preference to retrieve
     * @param defaultValue Value to return if this preference does not exist
     * @return The preference value if it exists, or defValue. Throws ClassCastException if there is a preference with
     * this name that is not a boolean
     */
    public static boolean getBoolean(String key, boolean defaultValue) {
        SharedPreferences settings = getAppSharedPreferencesInstance(mContext).mSharedPreferences;
        return settings.getBoolean(key, defaultValue);
    }

    public static void clear(String key) {
        SharedPreferences settings = getAppSharedPreferencesInstance(mContext).mSharedPreferences;
        SharedPreferences.Editor editor = settings.edit();
        editor.remove(key);
        editor.commit();


    }

    /**
     * 设置登陆记录
     *
     * @param isLogined
     */
    public static void setLoginedCache(String key, boolean isLogined) {
        putBoolean(key, isLogined);

    }

    /**
     * 获得登陆记录
     *
     * @return
     */
    public static boolean getLoginedCache(String key) {
        boolean isLogined = getBoolean(key);
        return isLogined;
    }

    /**
     * 设置第一次启动记录
     *
     * @param
     */
    public static void setAppLauncherCache(String key, int appVersionCode) {
        putInt(key, appVersionCode);

    }

    /**
     * 获得启动记录,，如果已经启动过返回非-1的值，否则默认返回-1
     *
     * @return
     */
    public static int getApplauncherCache(String key) {
        int versionCode = getInt(key);
        return versionCode;
    }


    public static String getKey(String uid, String key) {
        return uid + key;
    }

   /* protected static String getKey(String key) {
        return UID + key;
    }*/
}
