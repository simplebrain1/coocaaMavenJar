/**
 * Copyright (C) 2012 The SkyTvOS Project
 *
 * Version     Date           Author
 * ─────────────────────────────────────
 *           2014-7-7          mikan
 *
 */

package com.tianci.system.utils;

import android.util.Log;

import com.skyworth.framework.utils.LogUtil;
import com.tianci.net.define.NetConst;

public class SysLog
{
    private static boolean IS_SKY_LOG = true;

    public static void info(String msg)
    {
        info(NetConst.TAG, msg);
    }

    public static void warn(String msg)
    {
        warn(NetConst.TAG, msg);
    }

    public static void error(String msg)
    {
        error(NetConst.TAG, msg);
    }

    public static void info(String tag, String msg)
    {
        if (IS_SKY_LOG)
        {
            LogUtil.i(tag, msg);
        }
        else
        {
            Log.i(tag, msg);
        }
    }

    public static void warn(String tag, String msg)
    {
        if (IS_SKY_LOG)
        {
            LogUtil.w(tag, msg);
        }
        else
        {
            Log.w(tag, msg);
        }
    }

    public static void error(String tag, String msg)
    {
        if (IS_SKY_LOG)
        {
            LogUtil.e(tag, msg);
        }
        else
        {
            Log.e(tag, msg);
        }
    }
}
