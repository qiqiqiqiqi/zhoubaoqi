package com.qi.wechatclient.utils;

import android.content.Context;
import android.util.Log;

import com.qi.wechatclient.config.Constants;


/**
 * Log打印工具类
 *
 */
public class LogUtil {

    public static void d(Context context, String TAG, int strId) {
        if (Constants.SHOW_LOG) {
            Log.d(TAG, context.getResources().getString(strId));
        }
    }

    public static void i(Context context, String TAG, int strId) {
        if (Constants.SHOW_LOG) {
            Log.i(TAG, context.getResources().getString(strId));
        }
    }

    public static void w(Context context, String TAG, int strId) {
        if (Constants.SHOW_LOG) {
            Log.w(TAG, context.getResources().getString(strId));
        }
    }

    public static void e(Context context, String TAG, int strId) {
        if (Constants.SHOW_LOG) {
            Log.e(TAG, context.getResources().getString(strId));
        }
    }

    public static void d(String TAG, String content) {
        if (Constants.SHOW_LOG) {
            Log.d("hotel_"+TAG, content);
        }
    }

    public static void i(String TAG, String content) {
        if (Constants.SHOW_LOG) {
            Log.i("hotel_"+TAG, content);
        }
    }

    public static void w(String TAG, String content) {
        if (Constants.SHOW_LOG) {
            Log.w("hotel_"+TAG, content);
        }
    }

    public static void e(String TAG, String content) {
        if (Constants.SHOW_LOG) {
            Log.e("hotel_"+TAG, content);
        }
    }

}
