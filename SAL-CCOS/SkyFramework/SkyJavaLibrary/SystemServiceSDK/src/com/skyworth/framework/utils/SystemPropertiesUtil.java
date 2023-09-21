/**
 * Copyright (C) 2012 The SkyTvOS Project
 * <p>
 * Version     Date           Author
 * ─────────────────────────────────────
 * 2013-10-17         xingkong207
 */

package com.skyworth.framework.utils;

import android.os.SystemProperties;

/**
 * 获取prop的工具类
 */
public class SystemPropertiesUtil {

    /**
     * 获取系统属性值
     *
     * @param key 属性名称
     * @return String 属性值
     */
    public static String getProperty(String key) {
        return SystemProperties.get(key);
    }

    /**
     * 获取系统属性值
     *
     * @param key 属性名称
     * @param def 默认值
     * @return String 属性值
     */
    public static String getProperty(String key, String def) {
        return SystemProperties.get(key, def);
    }

    /**
     * 获取系统属性值
     *
     * @param key 属性名称
     * @param def 默认值
     * @return int 属性值
     */
    public static int getPropertyInt(String key, int def) {
        return SystemProperties.getInt(key, def);
    }

    /**
     * 获取系统属性值
     *
     * @param key 属性名称
     * @param def 默认值
     * @return long 属性值
     */
    public static long getPropertyLong(String key, long def) {
        return SystemProperties.getLong(key, def);
    }

    /**
     * 获取系统属性值
     *
     * @param key 属性名称
     * @param def 默认值
     * @return boolean 属性值
     */
    public static boolean getPropertyBoolean(String key, boolean def) {
        return SystemProperties.getBoolean(key, def);
    }

    /**
     * 设置系统属性值
     * 注意事项：必须拥有系统权限才能生效
     *
     * @param key   属性名称
     * @param value 属性值
     */
    public static void setProperty(String key, String value) {
        try {
            SystemProperties.set(key, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
