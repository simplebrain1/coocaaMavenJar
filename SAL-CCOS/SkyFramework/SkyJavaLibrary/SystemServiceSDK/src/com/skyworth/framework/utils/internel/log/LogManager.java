package com.skyworth.framework.utils.internel.log;


/**
 * <p>Description:</p>
 * <p>Log输出管理器</p>
 *
 * @author CRuby
 * @version V1.0.0
 * @ClassName LogManager
 * @date 2013-10-22
 */
public class LogManager {
    public final static int LOG_VERBOSE = 0xFFFFFFFF;
    public final static int LOG_NONE = 0x0;
    public final static int LOG_ERROR = 0x1;
    public final static int LOG_WARNING = 0x2;
    public final static int LOG_INFO = 0x4;
    public final static int LOG_DEBUG = 0x8;
    protected int rti = LogInfo.RI_ALL;
    protected int logType = LOG_VERBOSE;
    protected LogAppender logAppender = null;


    /**
     * 概述：(配置打印输出环境和内容)
     *
     * @param appender    输出介质，包括：<br/>
     *                    AndroidLogAppender，<br/>
     *                    ConsoleLogAppender，<br/>
     *                    DBLogAppender，<br/>
     *                    HTMLLogAppender，<br/>
     *                    TextLogAppender，<br/>
     * @param filter      设置打印某个级别的log，eg.  0x2|0x1   打印error和warning信息 <br/>
     *                    public final static int LOG_VERBOSE     = 0xFFFFFFFF; <br/>
     *                    public final static int LOG_NONE        = 0x0;<br/>
     *                    public final static int LOG_ERROR       = 0x1;<br/>
     *                    public final static int LOG_WARNING     = 0x2;<br/>
     *                    public final static int LOG_INFO        = 0x4;<br/>
     *                    public final static int LOG_DEBUG       = 0x8;<br/>
     * @param runtimeInfo 设置打印的附加信息，有 日期，类名，方法名，行数等。<br/>
     *                    public final static int RI_NONE         = 0x0;<br/>
     *                    public final static int RI_ALL          = 0xFFFFFFFF;<br/>
     *                    public final static int RI_DATE         = 0x1;<br/>
     *                    public final static int RI_CLASS        = 0x2;<br/>
     *                    public final static int RI_METHOD       = 0x4;<br/>
     *                    public final static int RI_LINE         = 0x8;<br/>
     */
    public LogManager(LogAppender appender, int filter, int runtimeInfo) {
        logType = filter;
        logAppender = appender;
        rti = runtimeInfo;
    }

    /**
     * 概述：打印debug信息，构造log消息结构体，设置rti级别<br/>
     *
     * @param tag 消息标签
     * @param msg 消息内容
     * @date 2013-10-22
     */
    public void logDebug(String tag, String msg) {
        // TODO Auto-generated method stub
        if (AppDetailLogManager.isAppDetailLogEnable()||(logType & LOG_DEBUG) > 0) {
            logAppender.logDebug(new LogInfo(rti, msg, tag));
        }
    }

    /**
     * 概述：打印error信息，构造log消息结构体，设置rti级别<br/>
     *
     * @param tag 消息标签
     * @param msg 消息内容
     * @date 2013-10-22
     */
    public void logError(String tag, String msg) {
        // TODO Auto-generated method stub
        if (AppDetailLogManager.isAppDetailLogEnable()||(logType & LOG_ERROR) > 0) {
            logAppender.logError(new LogInfo(rti, msg, tag));
        }
    }

    /**
     * 概述：打印info信息，构造log消息结构体，设置rti级别<br/>
     *
     * @param tag 消息标签
     * @param msg 消息内容
     * @date 2013-10-22
     */
    public void logInfo(String tag, String msg) {
        // TODO Auto-generated method stub
        if (AppDetailLogManager.isAppDetailLogEnable()||(logType & LOG_INFO) > 0) {
            logAppender.logInfo(new LogInfo(rti, msg, tag));
        }
    }

    /**
     * 概述：打印warning信息，构造log消息结构体，设置rti级别<br/>
     *
     * @param tag 消息标签
     * @param msg 消息内容
     * @date 2013-10-22
     */
    public void logWarning(String tag, String msg) {
        // TODO Auto-generated method stub
        if (AppDetailLogManager.isAppDetailLogEnable()||(logType & LOG_WARNING) > 0) {
            logAppender.logWarning(new LogInfo(rti, msg, tag));
        }
    }
}
