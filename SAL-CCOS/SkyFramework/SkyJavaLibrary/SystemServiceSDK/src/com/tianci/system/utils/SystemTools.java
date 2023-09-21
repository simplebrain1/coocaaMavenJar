package com.tianci.system.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.text.TextUtils;

import com.skyworth.framework.skysdk.android.SkySystemUtil;
import com.skyworth.framework.skysdk.logger.SkyLogger;
import com.skyworth.framework.utils.internel.StreamGobbler;
import com.tianci.utils.SkySSSLog;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;
import java.util.concurrent.TimeoutException;

/**
 * <p>
 * Description:
 * </p>
 * <p>
 * write something
 * </p>
 *
 * @author mikan
 * @version V1.0.0
 * @ClassName SystemTools
 * @date 15/4/18
 */
public class SystemTools {
    private static final String TAG = "SystemTools";

    public enum LINUX_CMD {
        CHMOD, MV, CP, PING, PM_INSTALL, PM_UNINSTALL, SYNC, MOUNT, REBOOT, MKDIR
    }

    public static String[] cmds = new String[]{"", "", "", "", "pm install", "", "", "",
            "reboot", "mkdir"};

    public static boolean isSystemApp(Context context) {
        if (context == null) {
            return false;
        }

        ApplicationInfo info = context.getApplicationInfo();
        if ((info.flags & ApplicationInfo.FLAG_SYSTEM) > 0) {
            return true;
        }

        SkySSSLog.w(TAG, "not system app can`t use this api");
        return false;
    }

    public static <T> T toObject(byte[] bytes, Class<T> clazz) {
        try {
            Object obj = null;
            ByteArrayInputStream bi = new ByteArrayInputStream(bytes);
            ObjectInputStream oi = new ObjectInputStream(bi);
            obj = oi.readObject();
            bi.close();
            oi.close();
            return (T) obj;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //      http://api.home.skysrt.com/v1/tvos/  ---> api.home.skysrt.com
    //      http://api.home.skysrt.com/v1/tvos  ---> api.home.skysrt.com/v1/tvos
    //      https://api.home.skysrt.com/v1/tvos/  ---> api.home.skysrt.com/v1/tvos
    //      https://api.home.skysrt.com/v1/tvos  ---> api.home.skysrt.com/v1/tvos
    public static String removePrefix(String httpUrl) {
        if (TextUtils.isEmpty(httpUrl)) {
            return null;
        }
        int startIndex = httpUrl.indexOf("//");
        if (startIndex == -1) {
            return null;
        }
        startIndex += 2;

        int endIndex = httpUrl.indexOf("/", startIndex);
        if (endIndex == -1) {
            endIndex = httpUrl.length();    // not have /
        } else if (endIndex != httpUrl.length() - 1) {
            endIndex = httpUrl.length();  // have /,but not ending with /
        }
        String domain = httpUrl.substring(startIndex, endIndex);
        SkySSSLog.d(TAG, "removePrefix return domain: " + domain);
        return domain;
    }

    /**
     * 执行linux命令，阻塞调用，不能在主线程调用
     *
     * @param cmd    命令，LINUX_CMD的枚举类型
     * @param params 命令的参数，如果不带参数，则传入""
     * @return 异常时返回-1
     */
    public static int execLinuxCmd(LINUX_CMD cmd, String params) {
        SkyLogger.d(TAG, "execLinuxCmd cmd=" + cmd + ",params=" + params);
        SkySystemUtil.LINUX_CMD command = null;
        switch (cmd) {
            case CHMOD:
                command = SkySystemUtil.LINUX_CMD.LINUX_CMD_CHMOD;
                break;
            case MV:
                command = SkySystemUtil.LINUX_CMD.LINUX_CMD_MV;
                break;
            case CP:
                command = SkySystemUtil.LINUX_CMD.LINUX_CMD_CP;
                break;
            case PING:
                command = SkySystemUtil.LINUX_CMD.LINUX_CMD_PING;
                break;
            case SYNC:
                command = SkySystemUtil.LINUX_CMD.LINUX_CMD_SYNC;
                break;
            case MOUNT:
                List<String> result = SkySystemUtil.execCmdWithRes(
                        SkySystemUtil.LINUX_CMD_WITH_RES.LINUX_CMD_MOUNT, params);
                return result != null ? 0 : -1;
        }
        if (command != null) {
            return SkySystemUtil.execCmd(command, params);
        }
        return execCmd(cmd, params);
    }

    private static int execCmd(LINUX_CMD cmd, String params) {
        SkyLogger.d(TAG, "execLinuxCmd cmd=" + cmd + ",params=" + params);
        String command = cmds[cmd.ordinal()];
        if (!TextUtils.isEmpty(params)) {
            command += " " + params;
        }
        SkyLogger.d(TAG, "execLinuxCmd command=" + command);
        if (TextUtils.isEmpty(command)) {
            return -1;
        }
        try {
            Process process = Runtime.getRuntime().exec(command);
            StreamGobbler outGobbler = new StreamGobbler(process);
            outGobbler.setDelaySecond(5);
            return outGobbler.start();
        } catch (IOException e) {
            SkyLogger.e(TAG, "execLinuxCmd e=" + e.getMessage());
        } catch (InterruptedException e) {
            SkyLogger.e(TAG, "execLinuxCmd e=" + e.getMessage());
        } catch (TimeoutException e) {
            SkyLogger.e(TAG, "execLinuxCmd e=" + e.getMessage());
        }
        return -1;
    }
}
