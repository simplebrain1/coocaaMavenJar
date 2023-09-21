package com.skyworth.framework.utils;

import android.util.Log;

import com.skyworth.framework.utils.internel.log.Logger;

/**
 * 打印工具类
 *
 * @since 1
 */
public class LogUtil {
    /**
     * 打印info信息
     *
     * @param tag 日志标签
     * @param msg 日志内容
     * @since 1
     */
    public static void i(String tag, String msg) {
        Logger.i(tag, msg);
    }

    /**
     * 打印debug日志
     *
     * @param tag 日志标签
     * @param msg 日志内容
     * @since 1
     */
    public static void d(String tag, String msg) {
        Logger.d(tag, msg);
    }

    /**
     * 打印warning日志
     *
     * @param tag 日志标签
     * @param msg 日志内容
     * @since 1
     */
    public static void w(String tag, String msg) {
        Logger.w(tag, msg);
    }

    /**
     * 打印error日志
     *
     * @param tag 日志标签
     * @param msg 日志内容
     * @since 1
     */
    public static void e(String tag, String msg) {
        Logger.e(tag, msg);
    }

    /**
     * 强制打印日志
     *
     * @param tag 日志标签
     * @param msg 日志内容
     * @since 1
     */
    public static void f(String tag, String msg) {
        Log.d(tag, msg);
    }
}
