package com.tianci.tv.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;

import com.skyworth.framework.skysdk.logger.SkyLogger;
import com.skyworth.framework.skysdk.properties.SkyGeneralProperties;
import com.skyworth.framework.skysdk.properties.SkySystemProperties;
import com.skyworth.framework.skysdk.util.Base64;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.List;

public class SkyTvUtils
{
    @SuppressWarnings("unchecked")
    public static <T> T toObject(byte[] bytes, Class<T> clazz)
    {
        try
        {
            Object obj = null;
            ByteArrayInputStream bi = new ByteArrayInputStream(bytes);
            ObjectInputStream oi = new ObjectInputStream(bi);
            obj = oi.readObject();
            bi.close();
            oi.close();
            return (T) obj;
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public static byte[] toBytes(Object obj)
    {
        byte[] bytes = null;
        try
        {
            ByteArrayOutputStream bo = new ByteArrayOutputStream();
            ObjectOutputStream oo = new ObjectOutputStream(bo);
            oo.writeObject(obj);
            bytes = bo.toByteArray();
            bo.close();
            oo.close();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return bytes;
    }

    public static String object2String(Object obj)
    {
        byte[] bytes = toBytes(obj);
        if (bytes == null)
            return "";
        return Base64.encodeToString(bytes);
    }

    public static <T> T string2Object(String str, Class<T> clazz)
    {
        byte[] bytes = Base64.decode(str);
        return SkyTvUtils.toObject(bytes, clazz);
    }

    public enum COUNT_TIMER_NAME
    {
        TV_CREATE, LOAD_PLUGIN, RELEASE_TV, SWITCH_SOURCE, SWITCH_CHANNEL,LAUNCHER_onCreate,LAUNCHER_onResume
    }
    
    private static HashMap<String, Long> countTimerMap = new HashMap<String, Long>();

    public static void startCountTimer(String name)
    {
        countTimerMap.put(name, System.currentTimeMillis());
    }

    public static void stopCountTimer(String name, String info)
    {
        long cur = System.currentTimeMillis();
        Long st = countTimerMap.get(name);
        if (st != null)
            TVSDKDebug.debug("COUNT_TIME", name + "(" + info + ") cost " + (cur - st) + "ms");
    }

    public static boolean isAppInstalled(Context context, String packageName) {
        if (context == null || packageName == null) {
            return false;
        }
        final PackageManager packageManager = context.getPackageManager();
        if (packageManager == null) {
            return false;
        }
        List<PackageInfo> packageInfos = packageManager.getInstalledPackages(0);
        if (packageInfos != null) {
            for (int i = 0; i < packageInfos.size(); i++) {
                PackageInfo packageInfo = packageInfos.get(i);
                if (packageInfo == null) {
                    continue;
                }
                if (packageName.equals(packageInfo.packageName)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isTcCoocaaOSUpe6_50()
    {
        boolean ret = false;
        String tc_version = readFileByLines("/system/vendor/TianciVersion").trim();
        SkyLogger.i("msg_tag", "tc_version=" + tc_version);
        String[] versions = tc_version.split("\\.");
        if (versions != null && versions.length > 1)
        {
            try
            {
                if (Integer.parseInt(versions[0]) == 6 && Integer.parseInt(versions[1]) >= 50)
                {
                    ret = true;
                } else if (Integer.parseInt(versions[0]) >= 7) {
                    ret = true;
                }

            } catch (NumberFormatException e)
            {
                e.printStackTrace();
            }
        }
        return ret;
    }


    private static String systemVersion;
    /**
     * 系统平台版本
     *
     * @return String 系统平台版本字符串
     * @since 1
     */
    public static String getSystemVersion() {
        if (systemVersion != null) {
            return systemVersion;
        }
        try {
            String version = readFileByLines("/system/vendor/TianciVersion").trim();
            systemVersion = version.trim();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return systemVersion;
    }

    /**
     * 概述：以行为单位读取文件，常用于读面向行的格式化文件<br/>
     * 适用条件：<br/>
     * 执行流程：<br/>
     * 使用方法：<br/>
     * 注意事项：<br/>
     *
     * @param fileName
     * @return String
     * @date 2014-8-5
     */
    private static String readFileByLines(String fileName) {
        String content = "";
        File file = new File(fileName);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            int line = 1;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                // 显示行号
                content += tempString;
                System.out.println("line " + line + ": " + tempString);
                line++;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        return content;
    }

    public static boolean checkProcessIsRunning(Context context, String processName) {
        boolean isRunning = false;
        if("com.skyworth.tv".equalsIgnoreCase(processName)  &&
                SkyGeneralProperties.getBoolProperty("SUPPORT_TVEXIST_PROP")){
            String tvexistStr =SkySystemProperties.getProperty("third.get.tvexist");
            TVSDKDebug.debug("tvexist:" + tvexistStr);
            if ("1".equals(tvexistStr)){
                isRunning = true;
            }
            else {
                isRunning = false;
            }
        }else {
            isRunning = isRunningByAm(context, processName);
        }
        TVSDKDebug.debug("isRunning " + isRunning);
        return isRunning;
    }

    private static boolean isRunningByAm(Context context, String processName) {
        ActivityManager mActivityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        if(mActivityManager == null) {
            TVSDKDebug.error("isRunning mActivityManager null");
            return false;
        }
        //获得系统运行的进程
        List<ActivityManager.RunningAppProcessInfo> appList = mActivityManager.getRunningAppProcesses();
        if(appList == null){
            TVSDKDebug.error("isRunning appList null");
            return false;
        }
        boolean isRunning = false;
        for (ActivityManager.RunningAppProcessInfo running : appList) {
            if(running == null) {
                continue;
            }
            TVSDKDebug.debug("isRunning running:" + running.processName);
            if(processName.equals(running.processName)) {
                isRunning = true;
                TVSDKDebug.debug("isRunning processName:" + processName);
                break;
            }
        }
        return isRunning;
    }

    public static boolean isStartTV() {
        String currentApk = SkySystemProperties.getProperty("sky.current.apk");
        return "com.skyworth.tv".equals(currentApk)
                ||"com.tianci.tv".equals(currentApk);
    }
}
