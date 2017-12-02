package com.qi.wechatclient.utils;

import java.util.UUID;

/**
 * Created by feng on 2017/7/23.
 */

public class UUIDUtils {

    public static String createUUID(){
        UUID uuid = UUID.randomUUID();
        return uuid.toString().replaceAll("\\-", "");
    }
}
