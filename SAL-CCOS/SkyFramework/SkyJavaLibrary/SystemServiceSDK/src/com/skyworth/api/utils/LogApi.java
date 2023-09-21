package com.skyworth.api.utils;

import android.util.Log;

import com.skyworth.framework.skysdk.logger.SkyLogger;
import com.skyworth.framework.utils.LogUtil;
import com.tianci.system.utils.ApiUtil;

/**
 * 打印工具类
 *
 * @since 1
 */
public class LogApi {
    private static boolean isBootPathImpl;

    static {
        isBootPathImpl = ApiUtil.isNewPlatform();
    }

    /**
     * 打印info信息<br/>
     *
     * @param tag 日志标签
     * @param msg 日志内容
     * @since 1
     */
    public static void i(String tag, String msg) {
        if (isBootPathImpl) {
            LogUtil.i(tag, msg);
        } else {
            SkyLogger.i(tag, msg);
        }
    }

    /**
     * 打印debug日志<br/>
     *
     * @param tag 日志标签
     * @param msg 日志内容
     * @since 1
     */
    public static void d(String tag, String msg) {
        if (isBootPathImpl) {
            LogUtil.d(tag, msg);
        } else {
            SkyLogger.d(tag, msg);
        }
    }

    /**
     * 打印warning日志<br/>
     *
     * @param tag 日志标签
     * @param msg 日志内容
     * @since 1
     */
    public static void w(String tag, String msg) {
        if (isBootPathImpl) {
            LogUtil.w(tag, msg);
        } else {
            SkyLogger.w(tag, msg);
        }
    }

    /**
     * 打印error日志<br/>
     *
     * @param tag 日志标签
     * @param msg 日志内容
     * @since 1
     */
    public static void e(String tag, String msg) {
        if (isBootPathImpl) {
            LogUtil.e(tag, msg);
        } else {
            SkyLogger.e(tag, msg);
        }
    }

    /**
     * 强制打印日志<br/>
     *
     * @param tag 日志标签
     * @param msg 日志内容
     * @since 1
     */
    public static void f(String tag, String msg) {
        Log.d(tag, msg);
    }
}
