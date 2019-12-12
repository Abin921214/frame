package com.jzhl.frame01.common.utils;

import java.util.UUID;

/**
 * 获取 UUID
 * @author xiaobin
 */
public class UUIDUtils {

    /**
     * 获取 uuid
     * @return
     */
    public static String getUuid(){
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * 获取 uuid 【转大写】
     * @return
     */
    public static String getUuidToUpperCase(){
        return UUID.randomUUID().toString().replace("-", "").toUpperCase();
    }

    /**
     * 获取 uuid 【转小写】
     * @return
     */
    public static String getUuidToLowerCase(){
        return UUID.randomUUID().toString().replace("-", "").toLowerCase();
    }
}
