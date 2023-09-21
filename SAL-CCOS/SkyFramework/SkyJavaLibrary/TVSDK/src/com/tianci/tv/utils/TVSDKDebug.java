/**
 * Copyright (C) 2012 The SkyTvOS Project
 *
 * Version     Date           Author
 * ─────────────────────────────────────
 *           2012-12-8         Root.Lu
 *
 */

package com.tianci.tv.utils;

import android.util.Log;

import com.skyworth.framework.skysdk.logger.SkyLogger;

import java.util.ArrayList;
import java.util.List;

public class TVSDKDebug
{
    private final static String TAG = "TVSDK";
    private final static String APP_HEADER = "";

    public enum LOG
    {
        INFO, DEBUG, ERROR
    }

    private static final boolean SKYDEBUG = false;

    public TVSDKDebug()
    {
    }

    private static void log(LOG l, String header, String info)
    {
        StackTraceElement[] es = Thread.currentThread().getStackTrace();
        String file_line = "(" + es[4].getClassName() + "." + es[4].getMethodName() + "--"
                + es[4].getFileName() + "@" + es[4].getLineNumber() + ")";
        String log = "<" + header + ">" + info + file_line;
        if (SKYDEBUG)
        {
            switch (l)
            {
                case INFO:
                    Log.i(TAG,log);
                    break;
                case DEBUG:
                    Log.d(TAG,log);
                    break;
                case ERROR:
                default:
                    Log.e(TAG,log);
                    break;
            }
        } else
        {
            switch (l)
            {
                case INFO:
                    SkyLogger.i(TAG, log);
                    break;
                case DEBUG:
                    SkyLogger.d(TAG, log);
                    break;
                case ERROR:
                default:
                    SkyLogger.e(TAG, log);
                    break;
            }
        }
    }

    public static void info(String info)
    {
        log(LOG.INFO, APP_HEADER, info);
    }

    public static void debug(String info)
    {
        log(LOG.DEBUG, APP_HEADER, info);
    }

    public static void error(String info)
    {
        log(LOG.ERROR, APP_HEADER, info);
    }

    public static void info(String header, String info)
    {
        log(LOG.INFO, header, info);
    }

    public static void debug(String header, String info)
    {
        log(LOG.DEBUG, header, info);
    }

    public static void error(String header, String info)
    {
        log(LOG.ERROR, header, info);
    }

    public static void showStackTrace()
    {
        List<String> stackTrace = new ArrayList<String>();
        StackTraceElement[] es = Thread.currentThread().getStackTrace();
        for (StackTraceElement e : es)
        {
            String log = "TVStackTrace---" + e.getClassName() + "." + e.getMethodName() + "("
                    + e.getFileName() + "@" + e.getLineNumber() + ")";
            stackTrace.add(log);
        }
        for (String str : stackTrace)
            SkyLogger.d(TAG,str);
    }
}
