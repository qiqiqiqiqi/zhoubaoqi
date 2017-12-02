package com.qi.wechatclient.config;

/**
 * Created by 13324 on 2016/11/9.
 */
public class Constants {
    public static final boolean SHOW_LOG = true;
    /**
     * 全局的sharedPrefrence的文件名称
     */
    public static final String SPF_NAME = "appsharedpreferences_name";
    /**
     * 无效数据
     */
    public static final int INVALID_NUM = -1;
    /**
     * app的登陆记录key值
     */
    public static final String KEY_LOGIN = "login";
    /**
     * app的启动记录
     */
    public static final String KEY_LANUCHER = "lanucher";
    /**
     * app的启动记录
     */
    public static final String LOGIN_APPUSERID = "appUserID";
    /**
     * 微信APP ID
     */
    public static final String WEIXIN_APPID = "wx6885ece91f0b3ea7";

    /**
     * toast類型
     */
    public static class ToastType {
        /**
         * 确定图标
         */
        public static final int RIGHT = 0;

        /**
         * error图标
         */
        public static final int ERROR = 1;

        /**
         * 进度对话框图标
         */
        public static final int WAIT = 2;

        /**
         * 不显示图标
         */
        public static final int NULL = 3;
        /**
         * 3.5s
         */
        public static final int LONG = 1;
        /**
         * 2s
         */
        public static final int SHORT = 0;


    }

    public static final String INTENT_FRIEND = "INTENT_FRIEND";
}
