package com.skyworth.framework.utils.internel.log;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <p>Description:</p>
 * <p>日志内容包含的各种信息，本次添加了tag字段，为了方便android log 的应用</p>
 *
 * @author CRuby
 * @version V1.0.0
 * @ClassName LogInfo
 * @date 2013-10-22
 */
public class LogInfo {
    /**
     * Description:表示不带任何附加信息
     */
    public final static int RI_NONE = 0x0;
    /**
     * Description:表示日志头部包含所有附加信息：日期、类名、方法名、行号
     */
    public final static int RI_ALL = 0xFFFFFFFF;
    /**
     * Description:表示日志头部包含日期
     */
    public final static int RI_DATE = 0x1;
    /**
     * Description:表示 日志头部包含类名
     */
    public final static int RI_CLASS = 0x2;
    /**
     * Description:表示日志头部包含方法名
     */
    public final static int RI_METHOD = 0x4;
    /**
     * Description:表示日志头部包含行号
     */
    public final static int RI_LINE = 0x8;
    /**
     * Description:时间
     */
    public String time = "";
    /**
     * Description:类名
     */
    public String className = "";
    /**
     * Description:方法名
     */
    public String methodName = "";
    /**
     * Description:行号
     */
    public String lineNumber = "";
    /**
     * Description:日志内容
     */
    public String message = "";
    /**
     * Description:日志标签
     */
    public String tag = null;
    /**
     * Description:附加信息标志位
     */
    protected int rti;

    /**
     * TODO()
     */
    public LogInfo() {

    }

    /**
     * TODO(构造打印消息体)
     *
     * @param rti 设置打印的附加信息，有 日期，类名，方法名，行数等。<br/>
     * @param msg 打印的内容体
     */
    public LogInfo(int rti, String msg) {
        this(rti, msg, null);
    }

    /**
     * TODO(构造打印消息体)
     *
     * @param rti 设置打印的附件信息，有 日期，类名，方法名，行数等。<br/>
     * @param msg 打印的内容体
     * @param tag 打印的标签
     */
    public LogInfo(int rti, String msg, String tag) {
        this.rti = rti;
        if (rti == RI_NONE) {
            message = msg;
            this.tag = tag;
        } else {
            if ((rti & RI_DATE) > 0) {
                SimpleDateFormat format = new SimpleDateFormat
                        ("yyyy-MM-dd HH:mm:ss");
                time += format.format(new Date());
            }
            StackTraceElement ste = new Throwable().getStackTrace()[3];
            if ((rti & RI_CLASS) > 0) {
                className = ste.getClassName();
            }
            if ((rti & RI_METHOD) > 0) {
                methodName = ste.getMethodName();
            }
            if ((rti & RI_LINE) > 0) {
                lineNumber = "" + ste.getLineNumber();
            }
            message = msg;
            this.tag = tag;
        }

    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     * 概述：包含 header + msg
     */
    @Override
    public String toString() {
        String header = "";
        if (rti == 0) {
            return message;
        }
        header += "[";
        if ((rti & RI_DATE) > 0) {
            header += time;
        }
        if ((rti & RI_CLASS) > 0) {
            header += " " + className;
        }
        if ((rti & RI_METHOD) > 0) {
            header += ":" + methodName;
        }
        if ((rti & RI_LINE) > 0) {
            header += " " + lineNumber;
        }
        header += "]";
        return header + message;
    }
};