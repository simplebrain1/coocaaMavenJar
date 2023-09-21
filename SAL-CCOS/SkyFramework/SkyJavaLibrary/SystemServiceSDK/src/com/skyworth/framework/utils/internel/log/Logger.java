/**
 * Copyright (C) 2012 The SkyTvOS Project
 * <p>
 * Version     Date           Author
 * ─────────────────────────────────────
 * 2013-10-10         CRuby
 */

package com.skyworth.framework.utils.internel.log;

import com.skyworth.framework.config.GeneralConfig;

/**
 * <p>Description:</p>
 * <p>外部调用接口</p>
 *
 * @author CRuby
 * @version V1.0.0
 * @ClassName Logger
 * @date 2013-10-22
 */
public class Logger {
    private static LogManager logManager = null;

    /**
     * Description:log 输出器控制
     */
    private static final String gLog_Appender = "LOG_APPENDER";

    /**
     * 概述：获取日志输出的实例<br/>
     *
     * @date 2013-10-22
     */
    private static void checkManager() {
        if (logManager == null) {
            logManager = new LogManager(new AndroidLogAppender(), getLogLevel(), LogInfo.RI_NONE);
        }
    }

    /**
     * 概述：打印info信息<br/>
     *
     * @param msg void
     * @date 2013-10-22
     */
    public static void info(String msg) {
        checkManager();
        logManager.logInfo(null, msg);
    }

    /**
     * 概述：打印info信息<br/>
     *
     * @param tag 日志标签
     * @param msg 日志内容
     * @date 2013-10-22
     */
    public static void info(String tag, String msg) {
        checkManager();
        logManager.logInfo(tag, msg);
    }

    /**
     * 概述：打印info信息<br/>
     *
     * @param msg 日志内容
     * @date 2013-10-22
     */
    public static void i(String msg) {
        checkManager();
        logManager.logInfo(null, msg);
    }

    /**
     * 概述：打印info信息<br/>
     *
     * @param tag 日志标签
     * @param msg 日志内容
     * @date 2013-10-22
     */
    public static void i(String tag, String msg) {
        checkManager();
        logManager.logInfo(tag, msg);
    }


    /**
     * 概述：打印debug信息<br/>
     *
     * @param msg 消息内容
     * @date 2013-10-22
     */
    public static void debug(String msg) {
        checkManager();
        logManager.logDebug(null, msg);
    }

    /**
     * 概述：打印debug信息<br/>
     *
     * @param tag 日志标签
     * @param msg 日志内容
     * @date 2013-10-22
     */
    public static void debug(String tag, String msg) {
        checkManager();
        logManager.logDebug(tag, msg);
    }

    /**
     * 概述：打印debug信息<br/>
     *
     * @param msg 消息内容
     * @date 2013-10-22
     */
    public static void d(String msg) {
        checkManager();
        logManager.logDebug(null, msg);
    }

    /**
     * 概述：打印debug信息<br/>
     *
     * @param tag 日志标签
     * @param msg 日志内容
     * @date 2013-10-22
     */
    public static void d(String tag, String msg) {
        checkManager();
        logManager.logDebug(tag, msg);
    }

    /**
     * 概述：打印error日志<br/>
     *
     * @param msg 日志内容
     * @date 2013-10-22
     */
    public static void error(String msg) {
        checkManager();
        logManager.logError(null, msg);
    }

    /**
     * 概述：打印Error日志<br/>
     *
     * @param tag 日志标签
     * @param msg 日志内容
     * @date 2013-10-22
     */
    public static void error(String tag, String msg) {
        checkManager();
        logManager.logError(tag, msg);
    }

    /**
     * 概述：打印error信息<br/>
     *
     * @param msg 日志内容
     * @date 2013-10-22
     */
    public static void e(String msg) {
        checkManager();
        logManager.logError(null, msg);
    }

    /**
     * 概述：打印error日志<br/>
     *
     * @param tag 日志标签
     * @param msg 日志内容
     * @date 2013-10-22
     */
    public static void e(String tag, String msg) {
        checkManager();
        logManager.logError(tag, msg);
    }

    /**
     * 概述：打印warning日志<br/>
     *
     * @param msg void
     * @date 2013-10-22
     */
    public static void warning(String msg) {
        checkManager();
        logManager.logWarning(null, msg);
    }

    /**
     * 概述：打印warning信息<br/>
     *
     * @param tag
     * @param msg void
     * @date 2013-10-22
     * @see Logger#w(String, String)
     */
    public static void warning(String tag, String msg) {
        checkManager();
        logManager.logWarning(tag, msg);
    }

    /**
     * 概述：打印warning信息<br/>
     *
     * @param msg 日志内容
     * @date 2013-10-22
     * @see Logger#warning(String)
     */
    public static void w(String msg) {
        checkManager();
        logManager.logWarning(null, msg);
    }

    /**
     * 概述：打印warning日志<br/>
     *
     * @param tag 日志标签
     * @param msg 日志内容
     * @date 2013-10-22
     */
    public static void w(String tag, String msg) {
        checkManager();
        logManager.logWarning(tag, msg);
    }

    /**
     * 从general_config.xml中获取log的配置
     */
    private static int getLogLevel() {
        int level = LogManager.LOG_VERBOSE;

        if (DebugUtil.isDebugMode()) {
            return 0xF;
        }

        String content = GeneralConfig.getConfig(gLog_Appender);
        if (null == content || content.equals("")) {
            return level;
        } else {
            if ("debug".equalsIgnoreCase(content)) {
                return 0xF;
            }
            if ("info".equalsIgnoreCase(content)) {
                return 0x7;
            }
            if ("warning".equalsIgnoreCase(content)) {
                return 0x3;
            }
            if ("error".equalsIgnoreCase(content)) {
                return 0x1;
            }
            if ("none".equalsIgnoreCase(content)) {
                return 0x0;
            }
        }
        return level;
    }
}
