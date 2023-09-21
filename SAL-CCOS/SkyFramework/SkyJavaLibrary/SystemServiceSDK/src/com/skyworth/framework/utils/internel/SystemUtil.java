/**
 * Copyright (C) 2012 The SkyTvOS Project
 * <p>
 * Version     Date           Author
 * ─────────────────────────────────────
 * 2013-10-11         thinkhwa
 */

package com.skyworth.framework.utils.internel;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.os.Parcel;
import android.os.StatFs;
import android.os.SystemProperties;
import android.service.wallpaper.WallpaperService;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

import com.skyworth.framework.config.GeneralConfig;
import com.skyworth.framework.data.ExternDiskInfo;
import com.skyworth.framework.utils.internel.log.Logger;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * <p>Description:一些系统相关的执行调用方法类</p>
 *
 * @author thinkhwa
 * @ClassName SkySystemUtil
 * @date 2013-10-22
 */
public class SystemUtil {
    public enum LINUX_CMD {
        LINUX_CMD_CHMOD, LINUX_CMD_MV, LINUX_CMD_CP, LINUX_CMD_PING, LINUX_CMD_PM_INSTALL, LINUX_CMD_SYNC,

        //new command add up from here
        LINUX_CMD_COUNT, LINUX_CMD_PM_UNINSTALL,
    }

    public enum LINUX_CMD_WITH_RES {
        LINUX_CMD_MOUNT, LINUX_CMD_BUSYBOX_BLKID,

        //new command add up from here
        LINUX_CMD_COUNT,
    }

    /**
     * <p>Description:通过ExternDiskInfoSchema系列化和反系列化磁盘的相关信息</p>
     * <p>详细文件请见Schemal中的ExternDiskInfo.avsc</p>
     *
     * @author thinkhwa
     * @ClassName ExternDiskInfo
     * @date 2013-10-22
     */
    static public class ExternDiskInfoInternel {

        public ExternDiskInfoSchema diskInfo;

        public void setPath(String path) {
            diskInfo.path = path;
        }

        public ExternDiskInfoInternel() {
            long zero = 0;

            diskInfo = new ExternDiskInfoSchema("", "", "", "", "", "",zero, zero, zero);
        }

        public ExternDiskInfoInternel(byte[] serializedExternDiskInfo) {
//            AvroData<ExternDiskInfoSchema> skydata = new AvroData<ExternDiskInfoSchema>();
//            diskInfo = skydata.deseriaze(serializedExternDiskInfo).get(0);
        }

        public byte[] toBytes() {
            Parcel parcel1 = Parcel.obtain();
            diskInfo.writeToParcel(parcel1, 0);
            byte content[] = parcel1.marshall();
            parcel1.recycle();
            return content;

        }

        public ExternDiskInfoInternel(String serializedExternDiskInfo) {
            SkyDataDecomposer data = new SkyDataDecomposer(serializedExternDiskInfo);

            long zero = 0;

            diskInfo = new ExternDiskInfoSchema("", "", "", "", "","", zero, zero, zero);

            diskInfo.devPath = data.getStringValue("devPath");
            diskInfo.path = data.getStringValue("path");
            diskInfo.label = data.getStringValue("label");
            diskInfo.format = data.getStringValue("format");
            diskInfo.uuid = data.getStringValue("uuid");

            String totalSpaceStr, usedSpaceStr, availSpaceStr;

            totalSpaceStr = data.getStringValue("totalSpace");
            usedSpaceStr = data.getStringValue("usedSpace");
            availSpaceStr = data.getStringValue("availSpace");

            if (totalSpaceStr != null) {
                diskInfo.totalSpace = Long.parseLong(totalSpaceStr);
            }
            if (usedSpaceStr != null) {
                diskInfo.usedSpace = Long.parseLong(usedSpaceStr);
            }
            if (availSpaceStr != null) {
                diskInfo.availSpace = Long.parseLong(availSpaceStr);
            }
        }

        @Override
        public String toString() {
            SkyDataComposer data = new SkyDataComposer();

            data.addValue("devPath", diskInfo.devPath);
            data.addValue("path", diskInfo.path);
            data.addValue("label", diskInfo.label);
            data.addValue("format", diskInfo.format.toString());
            data.addValue("uuid", diskInfo.uuid.toString());
            data.addValue("totalSpace", Long.toString(diskInfo.totalSpace));
            data.addValue("usedSpace", Long.toString(diskInfo.usedSpace));
            data.addValue("availSpace", Long.toString(diskInfo.availSpace));

            return data.toString();
        }

    }

    /**
     * 概述：return system space in Byte<br/>
     *
     * @param charSequence
     * @return long
     * @date 2013-10-22
     */
    public static long getTotalSpace(CharSequence charSequence) {
        try {
            StatFs sf = new StatFs((String) charSequence);
            long blockSize = sf.getBlockSize();
            long blockCount = sf.getBlockCount();
            return blockSize * blockCount;
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * 概述：return available space in Byte<br/>
     *
     * @param path
     * @return long
     * @date 2013-10-22
     */
    public static long getAvailSpace(CharSequence path) {
        try {
            StatFs sf = new StatFs((String) path);
            long blockSize = sf.getBlockSize();
            long availCount = sf.getAvailableBlocks();
            return blockSize * availCount;
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * 概述：return file or directory size in Byte<br/>
     *
     * @param filePath
     * @return long
     * @date 2013-10-22
     */
    public static long getFileSize(String filePath) {
        File file = new File(filePath);
        if (!file.isDirectory()) {
            return file.length();
        }
        long totalSize = 0;
        if (file.listFiles() == null) {
            return 0;
        }
        for (File subFile : file.listFiles()) {
            totalSize += getFileSize(subFile.getAbsolutePath());
        }
        return totalSize;
    }

    /**
     * 概述：执行一个带参数的LINUX_CMD类型的Linux命令
     *
     * @param cmd    命令，LINUX_CMD的枚举类型
     * @param params 命令的参数，如果不带参数，则传入""
     * @return result，异常时返回-1
     */
    public static int execCmd(LINUX_CMD cmd, String params) {
        String command = "";
        int delay = 5;
        switch (cmd) {
            case LINUX_CMD_CHMOD:
                command = "chmod";
                break;
            case LINUX_CMD_MV:
                command = "mv";
                break;
            case LINUX_CMD_CP:
                command = "cp";
                break;
            case LINUX_CMD_PING:
                command = "ping";
                break;
            case LINUX_CMD_PM_INSTALL:
                command = "pm install";
                delay = 200;//some apks is large,so the delay time need to be long
                break;
            case LINUX_CMD_SYNC:
                command = "sync";
                break;
            case LINUX_CMD_PM_UNINSTALL:
                command = "pm uninstall";
                break;
            default:
                return -1;
        }

        if (params != null && !params.equals("")) {
            command = command + " " + params;
        }
        Logger.info("execCmd: " + command + " and the delay time is " + delay);
        Process process;
        try {
            process = Runtime.getRuntime().exec(command);
            // flush all output avoid blocking ,create a new thread avoid block
            StreamGobbler outGobbler = new StreamGobbler(process);
            outGobbler.setDelaySecond(delay);
            return outGobbler.start();
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    /**
     * 概述：执行一个带参数的LINUX_CMD_WITH_RES类型的Linux命令
     *
     * @param cmd   命令，LINUX_CMD_WITH_RES的枚举类型
     * @param param 命令的参数，如果不带参数，则传入""
     * @return lines of result strings, 异常时返回null
     */
    public static List<String> execCmdWithRes(LINUX_CMD_WITH_RES cmd, String param) {
        String command = "";
        int delay = 5;
        switch (cmd) {
            case LINUX_CMD_MOUNT:
                command = "mount";
                delay = 60;
                break;
            case LINUX_CMD_BUSYBOX_BLKID:
                command = "busybox blkid";
                delay = 300;
                break;
            default:
                return null;
        }

        if (param != null && !param.equals("")) {
            command = command + " " + param;
        }

        try {
            List<String> results = new ArrayList<String>();
            Logger.info("execCmdWithRes cmd " + command);
            Process process = Runtime.getRuntime().exec(command);
            InputStream inStream = process.getInputStream();
            BufferedReader BR = new BufferedReader(new InputStreamReader(inStream, "ISO-8859-1"));
            String line1 = null;
            while ((line1 = BR.readLine()) != null) {
                Logger.info("execCmdWithRes readLine: " + line1);
                results.add(line1);
            }
            BR.close();

//            StreamGobbler outGobbler = new StreamGobbler(process);
//            outGobbler.setDelaySecond(delay);
//            outGobbler.start();

            if (results.size() > 0) {
                return results;
            }

        } catch (Exception e) {
            Logger.error("Failed to exe command:" + cmd + ", error:" + e.toString());
        }
        return null;
    }

    /**
     * 概述：获得所挂载的外部硬盘的磁盘信息<br/>
     *
     * @return List<ExternDiskInfo>
     * @date 2013-10-22
     */
    public static List<ExternDiskInfo> getExternDisks() {
        List<String> mountedDevices = execCmdWithRes(LINUX_CMD_WITH_RES.LINUX_CMD_MOUNT, "");
        List<String> blkidInfos = execCmdWithRes(LINUX_CMD_WITH_RES.LINUX_CMD_BUSYBOX_BLKID, "");
        String LabelStr = "LABEL=\"";
        String UuidStr = "UUID=\"";
        if (mountedDevices == null) {
            Logger.w("Could not find mounted devices");
            return null;
        } else {
            for (String mountedDevice : mountedDevices) {
                Logger.i("mountedDevice:" + mountedDevice);
            }
            if (blkidInfos != null) {
                for (String blkidInfo : blkidInfos) {
                    Logger.i("blkidInfo:" + blkidInfo);
                }
            }

        }
        List<ExternDiskInfo> diskInfos = new ArrayList<ExternDiskInfo>();
        for (String mount : mountedDevices) {
            String[] items = mount.split(" ");
            if (items[0].startsWith("/dev/block/vold")) {
                ExternDiskInfo info = new ExternDiskInfo();
                info.setDevPath(items[0]);
                info.setPath(items[1]);// [2];// [1];
                if (StringEncoder.isUTF8(info.getPath())) {
                    info.setPath(StringEncoder.convertString(info.getPath(),
                            "UTF-8"));
                } else {
                    info.setPath(StringEncoder.convertString(info.getPath(),
                            "GBK"));
                }
                info.setFormat(items[2]);// [4];// [2];
                info.setTotalSpace(getTotalSpace(info.getPath()));
                if (info.getTotalSpace() == 0) {
                    continue;
                }
                info.availSpace = getAvailSpace(info.path);
                info.usedSpace = info.totalSpace - info.availSpace;
                if (blkidInfos != null) {
                    for (String blkid : blkidInfos) {
                        // if (blkid.startsWith(info.devPath))
                        if (blkid.substring(0, blkid.lastIndexOf(":"))
                                .equals(info.devPath)) {
                            int label_pos = blkid.indexOf("LABEL=");
                            int uuid_pos = blkid.indexOf("UUID=");
                            Logger.i("blkid:" + blkid);
                            Logger.i("uuid_pos:" + uuid_pos);
                            Logger.i("label_pos:" + label_pos);
                            if (uuid_pos >= 0) {
                                String uuidItem;
                                if ((blkid.length() - 1) >= (uuid_pos + UuidStr.length())) {
                                    uuidItem = blkid.substring(uuid_pos + UuidStr.length(),
                                            blkid.length() - 1);
                                } else {
                                    uuidItem = blkid.substring(uuid_pos, blkid.length());
                                }
                                info.uuid = uuidItem;
                            } else {
                                continue;
                            }
                            String labelItem;

                            if (label_pos > 0) {
                                if (uuid_pos > label_pos) // in case uuid not exist; uuid exist
                                // before label.
                                {
                                    if ((uuid_pos - 1 - 1) >= (label_pos + LabelStr.length())) {
                                        labelItem = blkid.substring(label_pos + LabelStr.length(),
                                                uuid_pos - 1 - 1);
                                    } else {
                                        labelItem = blkid.substring(label_pos, uuid_pos - 1);
                                    }
                                } else {
                                    if ((label_pos + LabelStr.length()) < (blkid.length() - 1)) {
                                        labelItem = blkid.substring(label_pos + LabelStr.length(),
                                                blkid.length() - 1);
                                    } else {
                                        labelItem = blkid.substring(label_pos);
                                    }
                                }
                                if (StringEncoder.isUTF8(labelItem)) {
                                    labelItem = StringEncoder.convertString(labelItem, "UTF-8");
                                } else {
                                    labelItem = StringEncoder.convertString(labelItem, "GBK");
                                }
                                info.label = labelItem;
                            }

                        }
                    }
                }

                if (info.label == null) {
                    diskInfos.add(info);
                } else if (info.label.length() == 0) {
                    diskInfos.add(info);
                } else if ((info.uuid == null) || (info.uuid.equals(""))) {
                    ; // in order to avoid: blkid:/dev/block/vold/179:34: LABEL="Volumn"
                } else {
                    diskInfos.add(info);
                }
            }
        }
        return diskInfos;
    }

    private final static String ExternalPoint = "/dev/block/vold/8:0";

    /**
     * 概述：获得所挂载的外部硬盘的磁盘信息<br/>
     *
     * @return List<ExternDiskInfo>
     * @date 2013-10-22
     */
    public static List<ExternDiskInfo> getExternDisksMs() {
        List<String> mountedDevices = execCmdWithRes(LINUX_CMD_WITH_RES.LINUX_CMD_MOUNT, "");
        List<String> blkidInfos = execCmdWithRes(LINUX_CMD_WITH_RES.LINUX_CMD_BUSYBOX_BLKID, "");
        String LabelStr = "LABEL=\"";
        String UuidStr = "UUID=\"";
        if (mountedDevices == null) {
            Logger.w("Could not find mounted devices");
            return null;
        } else {
            for (String mountedDevice : mountedDevices) {
                Logger.i("mountedDevice:" + mountedDevice);
            }
            if (blkidInfos != null) {
                for (String blkidInfo : blkidInfos) {
                    Logger.i("blkidInfo:" + blkidInfo);
                }
            }

        }
        List<ExternDiskInfo> diskInfos = new ArrayList<ExternDiskInfo>();
        for (String mount : mountedDevices) {
            String[] items = mount.split(" ");

            if (items[0].startsWith("/dev/block/vold")) {
                ExternDiskInfo info = new ExternDiskInfo();
                info.devPath = items[0];
                info.path = items[1];// [2];// [1];
                if (StringEncoder.isUTF8(info.path)) {
                    info.path = StringEncoder.convertString(info.path, "UTF-8");
                } else {
                    info.path = StringEncoder.convertString(info.path, "GBK");
                }
                info.format = items[2];// [4];// [2];
                info.totalSpace = getTotalSpace(info.path);
                if (info.totalSpace == 0) {
                    continue;
                }
                info.availSpace = getAvailSpace(info.path);
                info.usedSpace = info.totalSpace - info.availSpace;
                if (blkidInfos != null) {
                    for (String blkid : blkidInfos) {
                        if (blkid.substring(0, blkid.lastIndexOf(":"))
                                .equals(info.devPath)) {
                            int label_pos = blkid.indexOf("LABEL=");
                            int uuid_pos = blkid.indexOf("UUID=");
                            Logger.i("blkid:" + blkid);
                            Logger.i("uuid_pos:" + uuid_pos);
                            Logger.i("label_pos:" + label_pos);
                            if (uuid_pos >= 0) {
                                String uuidItem;
                                if ((blkid.length() - 1) >= (uuid_pos + UuidStr.length())) {
                                    uuidItem = blkid.substring(uuid_pos + UuidStr.length(),
                                            blkid.length() - 1);
                                } else {
                                    uuidItem = blkid.substring(uuid_pos, blkid.length());
                                }

                                // uuidItem = uuidItem.substring(6, uuidItem.length() - 1);// remove
                                // UUID=
                                info.uuid = uuidItem;
                            }
//                            else
//                            {
//                                continue;
//                            }
                            String labelItem;

                            if (label_pos > 0) {
                                if (uuid_pos > label_pos) // in case uuid not exist; uuid exist
                                // before label.
                                {
                                    if ((uuid_pos - 1 - 1) >= (label_pos + LabelStr.length())) {
                                        labelItem = blkid.substring(label_pos + LabelStr.length(),
                                                uuid_pos - 1 - 1);
                                    } else {
                                        labelItem = blkid.substring(label_pos, uuid_pos - 1);
                                    }
                                } else {
                                    if ((label_pos + LabelStr.length()) < (blkid.length() - 1)) {
                                        labelItem = blkid.substring(label_pos + LabelStr.length(),
                                                blkid.length() - 1);
                                    } else {
                                        labelItem = blkid.substring(label_pos);
                                    }
                                }
                                // labelItem = labelItem.substring(7, labelItem.length() - 1);//
                                // remove
                                if (StringEncoder.isUTF8(labelItem)) {
                                    labelItem = StringEncoder.convertString(labelItem, "UTF-8");
                                } else {
                                    labelItem = StringEncoder.convertString(labelItem, "GBK");
                                }
                                info.label = labelItem;
                            }
                            if (info.label == null) {
                                Logger.i("======================name:" + info.label);
                                diskInfos.add(info);
                            } else if (info.label.length() == 0) {
                                Logger.i("======================name:" + info.label);
                                diskInfos.add(info);
                            } else if ((info.uuid == null)
                                    || (info.uuid.equals(""))) {
                                diskInfos.add(info); // in order to avoid: blkid:/dev/block/vold/179:34: LABEL="Volumn"
                            } else {
                                Logger.i("======================name:" + info.label);
                                diskInfos.add(info);
                            }
                        }
                    }

                }
            }
        }
        return diskInfos;
    }

    /**
     * 概述：得到当前运行的Android包<br/>
     * require permission android.permission.GET_TASKS
     *
     * @param context
     * @return String
     * @date 2013-10-22
     */
    @Deprecated
    public static String getCurrentRunningPackage(Context context) {
        ComponentName cn = null;
        try {
            cn = ((ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE))
                    .getRunningTasks(1).get(0).topActivity;
            return cn.getPackageName();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }

    }

    /**
     * 概述：获取最顶层activity class name
     *
     * @param context
     * @return String
     * @date 2017-12-19
     */
    public static String getTopActivityName(Context context) {
        ComponentName cn = null;
        try {
            cn = ((ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE))
                    .getRunningTasks(1).get(0).topActivity;
            return cn.getClassName();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 概述：读取CPU的信息值<br/>
     *
     * @return long[]
     * @date 2013-10-22
     */
    private static long[] readCPUValues() {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream("/proc/stat")));
            String line = null;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("cpu")) {
                    line = line.substring(3).trim();
                    String[] values = line.split(" ");
                    long[] result = new long[values.length];
                    for (int i = 0; i < values.length; i++) {
                        result[i] = Long.parseLong(values[i]);
                    }
                    reader.close();
                    return result;
                }

            }
            reader.close();
        } catch (Exception e) {
            return null;
        }
        return null;
    }

    /**
     * 概述：得到CPU的使用率<br/>
     *
     * @return int
     * @date 2013-10-22
     */
    public static int getCpuUsage() {
        long[] valuesBefore = readCPUValues();
        if (valuesBefore == null) {
            return 0;
        }
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }
        long[] valuesAfter = readCPUValues();
        if (valuesAfter == null) {
            return 0;
        }
        long idleTime = valuesAfter[3] - valuesBefore[3];
        long totalTime = (valuesAfter[0] + valuesAfter[1] + valuesAfter[2] + valuesAfter[3]
                + valuesAfter[4] + valuesAfter[5] + valuesAfter[6])
                - (valuesBefore[0] + valuesBefore[1] + valuesBefore[2] + valuesBefore[3]
                + valuesBefore[4] + valuesBefore[5] + valuesBefore[6]);
        return (int) ((1.0f - ((float) idleTime / (float) totalTime)) * 100);
        /*
         * final String UserStart = "User "; final String SystemStart = "System "; List<String>
         * topInfos = execCmdWithRes("top -m 1 -n 1"); int UserUsage = 0; int SystemUsage = 0; for
         * (String info : topInfos) { if (info.startsWith(UserStart)) { String[] items =
         * info.split(","); for (String item : items) { item = item.trim(); if
         * (item.startsWith(UserStart)) { String usage = item.substring(UserStart.length(),
         * item.length() - 1); try { UserUsage = Integer.parseInt(usage); } catch (Exception e) {
         *
         * } } else if (item.startsWith(SystemStart)) { String usage =
         * item.substring(SystemStart.length(), item.length() - 1); try { SystemUsage =
         * Integer.parseInt(usage); } catch (Exception e) {
         *
         * } } } } } return UserUsage + SystemUsage;
         */
    }

    /**
     * 获取CPU核心数
     */
    public static int getCpuCores() {
        // Private Class to display only CPU devices in the directory listing
        class CpuFilter implements FileFilter {
            @Override
            public boolean accept(File pathname) {
                // Check if filename is "cpu", followed by a single digit number
                if (Pattern.matches("cpu[0-9]", pathname.getName())) {
                    return true;
                }
                return false;
            }
        }
        try {
            // Get directory containing CPU info
            File dir = new File("/sys/devices/system/cpu/");
            // Filter to only list the devices we care about
            File[] files = dir.listFiles(new CpuFilter());
            // Return the number of cores (virtual CPU devices)
            return files.length;
        } catch (Exception e) {
            // Default to return 1 core
            return 1;
        }
    }

    /**
     * 获取CPU频率
     */
    public static String getCpuFreq() {
        String result = "";
        ProcessBuilder cmd;
        try {
            String[] args = {"/system/bin/cat",
                    "/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq"};
            cmd = new ProcessBuilder(args);
            Process process = cmd.start();
            InputStream in = process.getInputStream();
            byte[] re = new byte[24];
            while (in.read(re) != -1) {
                result = result + new String(re);
            }
            in.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            result = "N/A";
        }
        try {
            float freq = Float.valueOf(result.trim());
            return freq / 1000000.00f + "GHz";
        } catch (Exception e) {
            return "N/A";
        }
    }

    /**
     * 概述：杀掉当前运行的包<br/>
     * require permission android.permission.FORCE_STOP_PACKAGES
     *
     * @param context
     * @param packageName void
     * @date 2013-10-22
     */
    public static void killCurrentRunningPackage(Context context, String packageName) {
        String homePName = GeneralConfig.getConfig("HOME_PACAKAGE_NAME");
        if (homePName != null) {
            if (packageName.equals(homePName)) {
                return;
            }
        }

        ActivityManager activityManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        Logger.i("Killed PackageName:" + packageName);
        try {
            Method forceStopPackage;
            forceStopPackage = activityManager.getClass().getDeclaredMethod("forceStopPackage",
                    String.class);
            forceStopPackage.setAccessible(true);
            forceStopPackage.invoke(activityManager, packageName);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /**
     * 概述：得到当前的屏幕尺寸<br/>
     *
     * @param context
     * @return Display
     * @date 2013-10-22
     */
    public static Display getCurrentDisplay(Context context) {
        Context thisContext = context;
        if (thisContext == null) {
            return null;
        }
        Display display = ((WindowManager) thisContext.getSystemService(Context.WINDOW_SERVICE))
                .getDefaultDisplay();
        return display;
    }

    /**
     * 概述：得到当前density<br/>
     *
     * @param context
     * @return float
     * @date 2013-12-20
     */
    public static float getDisplayDensity(Context context) {
        Context thisContext = context;
        if (thisContext == null) {
            return 0;
        }
        DisplayMetrics dm = thisContext.getApplicationContext().getResources().getDisplayMetrics();
        float density = dm.density;
        return density;
    }

    /**
     * 概述：得到屏幕的宽度<br/>
     *
     * @param context
     * @return int
     * @date 2013-10-22
     */
    public static int getDisplayWidth(Context context) {
        Context thisContext = context;
        if (thisContext == null) {
            return 0;
        }
        Display display = ((WindowManager) thisContext.getSystemService(Context.WINDOW_SERVICE))
                .getDefaultDisplay();
        if (display == null) {
            return 1920;
        }
        return display.getWidth();
    }

    /**
     * 概述：得到屏幕的高度<br/>
     *
     * @param context
     * @return int
     * @date 2013-10-22
     */
    public static int getDisplayHeight(Context context) {
        Context thisContext = context;
        if (thisContext == null) {
            return 0;
        }
        Display display = ((WindowManager) thisContext.getSystemService(Context.WINDOW_SERVICE))
                .getDefaultDisplay();
        return display.getHeight();
    }

    public static boolean isWallpaper(Context context, String packageName) {
        PackageManager mPackageManager = context.getPackageManager();
        List<ResolveInfo> list = mPackageManager.queryIntentServices(new Intent(
                WallpaperService.SERVICE_INTERFACE), PackageManager.GET_META_DATA);
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).serviceInfo.packageName.equals(packageName)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 概述：杀掉后台进程<br/>
     *
     * @param context               上下文
     * @param notKillList           不被杀掉的应用列表
     * @param useFilterPAackageName 是否进行过滤包功能
     * @date 2013-10-22
     */
    public static void killBackgroundProcesses(Context context, List<String> notKillList,
                                               boolean useFilterPAackageName) {
        if (notKillList != null) {
            for (String item : notKillList) {
                Logger.i("killBackgroundProcesses notKillList:" + item);
            }

        }
        String pName = getCurrentRunningPackage(context);
        String homePName = GeneralConfig.getConfig("HOME_PACAKAGE_NAME");
        boolean bNotKillPnameFounded = false;
        if (homePName != null) {
            if (pName.equals(homePName)) {
                return;
            }
        }
        String returnStr;
        if (useFilterPAackageName) {
            returnStr = GeneralConfig.getConfig("FILTER_PACAKAGE_NAME");
        } else {
            returnStr = null;
        }

        if ((returnStr == null) || (returnStr.equals(""))) {
            List<RunningAppProcessInfo> taskList = null;
            ActivityManager activityManager = (ActivityManager) context
                    .getSystemService(Context.ACTIVITY_SERVICE);
            taskList = activityManager.getRunningAppProcesses();
            if (null != taskList) {
                for (int i = 0; i < taskList.size(); i++) {
                    String packageName = taskList.get(i).processName;
                    Logger.i("PackageName in taskList PackageName:" + packageName);
                    if (isInputMethodApp(context, packageName)) {
                        continue;
                    }
                    bNotKillPnameFounded = false;
                    if (notKillList != null) {
                        if (notKillList.size() > 0) {
                            for (String notKillPname : notKillList) {
                                if (notKillPname != null) {
                                    if (notKillPname.equals(packageName)) {
                                        bNotKillPnameFounded = true;
                                        break;
                                    }
                                }
                            }
                        }

                        if (bNotKillPnameFounded) {
                            continue;
                        }
                    }

                    try {
                        Method forceStopPackage;
                        forceStopPackage = activityManager.getClass().getDeclaredMethod(
                                "forceStopPackage", String.class);
                        forceStopPackage.setAccessible(true);
                        forceStopPackage.invoke(activityManager, packageName);

                        Logger.i("Killed PackageName:" + packageName);

                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    } catch (IllegalArgumentException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }

        } else {
            String filterPacNames[] = returnStr.split("\\|");
            boolean isFilterPac = false;

            List<RunningAppProcessInfo> taskList = null;
            ActivityManager activityManager = (ActivityManager) context
                    .getSystemService(Context.ACTIVITY_SERVICE);
            taskList = activityManager.getRunningAppProcesses();
            if (null != taskList) {
                for (int i = 0; i < taskList.size(); i++) {
                    String packageName = taskList.get(i).processName;
                    Logger.info("taskList.get(i).processName: " + packageName);
                    if (isWallpaper(context, packageName)) {
                        continue;
                    }
                    if (isInputMethodApp(context, packageName)) {
                        continue;
                    }
                    bNotKillPnameFounded = false;
                    if (notKillList != null) {
                        if (notKillList.size() > 0) {
                            for (String notKillPname : notKillList) {
                                if (notKillPname != null) {
                                    if (notKillPname.equals(packageName)) {
                                        bNotKillPnameFounded = true;
                                        break;
                                    }
                                }
                            }
                        }

                        if (bNotKillPnameFounded) {
                            continue;
                        }
                    }

                    for (int j = 0; j < filterPacNames.length; j++) {
                        String filterPacName = filterPacNames[j];
                        Logger.info("----filterPacName----: " + filterPacName);
                        if (!packageName.startsWith(filterPacName)) {
                            continue;
                        }
                        isFilterPac = true;
                        break;
                    }
                    if (isFilterPac) {
                        isFilterPac = false;
                        continue;
                    } else {
                        Logger.info("Killed PackageName:" + packageName);
                        try {
                            Method forceStopPackage;
                            forceStopPackage = activityManager.getClass().getDeclaredMethod(
                                    "forceStopPackage", String.class);
                            forceStopPackage.setAccessible(true);
                            forceStopPackage.invoke(activityManager, packageName);

                        } catch (NoSuchMethodException e) {
                            e.printStackTrace();
                        } catch (IllegalArgumentException e) {
                            e.printStackTrace();
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }

    }

    /**
     * 概述：杀掉所有后台进程<br/>
     * require permission android.permission.FORCE_STOP_PACKAGES
     *
     * @param context void
     * @date 2013-10-22
     */
    public static void killBackgroundProcesses(Context context) {
        killBackgroundProcesses(context, null, true);
    }

    /**
     * 概述：由外部指定不杀的进程，可能更灵活<br/>
     * require permission android.permission.FORCE_STOP_PACKAGES
     *
     * @param context
     * @param pkgs    void
     * @date 2013-10-22
     */
    public static void doNotKillBackgroundProcesses(Context context, String[] pkgs) {
        if (pkgs == null || context == null) {
            Logger.e("are you kidding me????");
            return;
        }
        ActivityManager activityManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        List<RunningAppProcessInfo> taskList = activityManager.getRunningAppProcesses();
        if (taskList != null) {
            for (int i = 0; i < taskList.size(); i++) {
                String packageName = taskList.get(i).processName;
                Logger.info("processName: " + packageName);
                if (!(isWallpaper(context, packageName) || isInputMethodApp(context, packageName))) {
                    int j = 0;
                    for (j = 0; j < pkgs.length; j++) {
                        String filterPacName = pkgs[j];
                        if (packageName.equals(filterPacName))
                            break;
                    }
                    if (j == pkgs.length && pkgs.length != 0) {
                        try {
                            Logger.info("try to kill " + packageName);
                            Method forceStopPackage;
                            forceStopPackage = activityManager.getClass().getDeclaredMethod(
                                    "forceStopPackage", String.class);
                            forceStopPackage.setAccessible(true);
                            forceStopPackage.invoke(activityManager, packageName);
                        } catch (NoSuchMethodException e) {
                            e.printStackTrace();
                        } catch (IllegalArgumentException e) {
                            e.printStackTrace();
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    /**
     * 概述：通过传入的包名判断该应用是否为输入法应用<br/>
     *
     * @param context     上下文
     * @param packageName 传入的包名
     * @return boolean
     * @date 2013-10-22
     */
    public static boolean isInputMethodApp(Context context, String packageName) {
        PackageManager pm = context.getPackageManager();
        boolean isInputMethodApp = false;
        try {
            PackageInfo pkgInfo = pm.getPackageInfo(packageName, PackageManager.GET_SERVICES);
            ServiceInfo[] sInfo = pkgInfo.services;
            if (sInfo != null) {
                for (int i = 0; i < sInfo.length; i++) {
                    ServiceInfo serviceInfo = sInfo[i];
                    if (serviceInfo.permission != null
                            && serviceInfo.permission
                            .equals("android.permission.BIND_INPUT_METHOD")) {
                        isInputMethodApp = true;
                        break;
                    }
                }
            }
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Logger.i("packageName:" + packageName + "is:" + isInputMethodApp);

        return isInputMethodApp;
    }

    /**
     * 概述：得到总的内存数<br/>
     *
     * @return Long 返回的内存数目值
     * @date 2013-10-22
     */
    public static Long getTotalMemory() {
        String str1 = "/proc/meminfo";// 系统内存信息文件
        String str2;
        String[] arrayOfString;
        long initial_memory = 0;

        try {
            FileReader localFileReader = new FileReader(str1);
            BufferedReader localBufferedReader = new BufferedReader(localFileReader, 8192);
            str2 = localBufferedReader.readLine();// 读取meminfo第一行，系统总内存大�?

            Logger.i("mikan str2 = " + str2);
            arrayOfString = str2.split("\\s+");

            for (String stempString : arrayOfString) {
                Logger.i("stempString = " + stempString);
            }

            initial_memory = Integer.valueOf(arrayOfString[1]).intValue() / 1024;// 获得系统总内存，单位是KB，除�?
            // 1024 单位为M
            localBufferedReader.close();

        } catch (IOException e) {
        }
        return initial_memory;
    }

    /**
     * 概述：得到的空闲内存数值<br/>
     *
     * @return long 返回空闲的内存数目值
     * @date 2013-10-22
     */
    public static long getFreeMemory() {
        String str1 = "/proc/meminfo";// 系统内存信息文件
        String str2;
        String[] arrayOfString;
        long memory = 0;

        try {
            FileReader localFileReader = new FileReader(str1);
            BufferedReader localBufferedReader = new BufferedReader(localFileReader, 8192);
            str2 = localBufferedReader.readLine();// 读取meminfo第一行，系统总内存大�?
            while (str2 != null) {
                arrayOfString = str2.split("\\s+");
                if (arrayOfString != null && arrayOfString.length > 0) {
                    if (arrayOfString[0].contains("MemFree")) {
                        memory = Integer.valueOf(arrayOfString[1]).intValue();
                        break;
                    }
                }

                str2 = localBufferedReader.readLine();
            }
            // 1024 单位为M
            localBufferedReader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return memory;
    }

    /**
     * 概述：删除单个文件<br/>
     *
     * @param fileName 被删除文件的文件名
     * @return boolean 单个文件删除成功返回true,否则返回false
     * @date 2013-10-22
     */
    public static boolean deleteFile(String fileName) {
        boolean hasDelete = false;
        File file = new File(fileName);
        if (file.isFile() && file.exists()) {
            hasDelete = file.delete();
            Logger.info("delete file " + file + " result: " + hasDelete);
            return hasDelete;
        } else {
            Logger.error("delete file " + file + " failed.");
            return false;
        }
    }

    /**
     * 概述：删除目录下的所有文件 （或文件夹），不删除该目录本身
     *
     * @param dirName 被删除目录的文件路径
     * @return 目录删除成功返回true, 否则返回false
     * @date 2013-10-22
     */
    public static boolean deleteDirFiles(String dirName) {
        return deleteDirFiles(dirName, false);
    }

    /**
     * 概述：删除目录下的所有文件 （或文件夹），是否删除该目录本身由标志位"delTopDir"决定
     *
     * @param dirName   被删除目录的文件路径
     * @param delTopDir 是否删除顶级目录本身的标志位
     * @return 目录删除成功返回true, 否则返回false
     */
    public static boolean deleteDirFiles(String dirName, boolean delTopDir) {
        boolean hasDelete = false;
        //如果dir不以文件分隔符结尾，自动添加文件分隔符
        if (!dirName.endsWith(File.separator)) {
            dirName = dirName + File.separator;
        }
        File file = new File(dirName);
        if ((!file.exists()) || (!file.isDirectory())) {
            Logger.error("the dir of " + dirName + " not exist, or not a directory.");
            return hasDelete;
        }
        File[] db_files = file.listFiles();
        for (File pFile : db_files) {
            String filePath = pFile.getAbsolutePath();
            Logger.info("filePath: " + filePath);
            //删除子文件
            if (pFile.isFile()) {
                hasDelete = pFile.delete();
            }
            //删除子目录
            else {
                hasDelete = deleteDirFiles(filePath, true);
            }
            if (hasDelete) {
                Logger.info("delete file " + pFile + " success.");
            } else {
                Logger.error("delete file " + pFile + " failed.");
                break;
            }
        }

        //删除当前目录
        if (delTopDir) {
            if (file.delete()) {
                Logger.info("delete dir " + file + " success.");
                return true;
            } else {
                Logger.error("delete dir " + file + " failed.");
                return false;
            }
        }
        return hasDelete;
    }

    /**
     * 获取实时网速，单位 KB/S
     *
     * @return ret[2]数组，ret[0]是下载速度，ret[1] 是上传速度
     */
    public static float[] getNetConnectionSpeed() {
        float ret[] = new float[2];
        try {
            long[] preFlow = readCurrentNetDevFlow();
            long timeInteval = 2;
            Thread.sleep(timeInteval * 1000);
            long[] currentFlow = readCurrentNetDevFlow();
            ret[0] = (float) (Math.round(((currentFlow[0] - preFlow[0]) / (1024f) / ((float) timeInteval)) * 100)) / 100; //精确到后两位
            ret[1] = (float) (Math.round(((currentFlow[1] - preFlow[1]) / (1024f) / ((float) timeInteval)) * 100)) / 100;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ret;
    }

    /**
     * 概述：获取系统是否支持H265,默认支持<br/>
     */
    public static boolean isSupportedH265() {
        boolean support = true;
        String value = SystemProperties.get("third.get.h265");
        if (!isEmptyString(value) && value.equals("0")) {
            support = false;
        }
        return support;
    }

    /**
     * 概述：获取系统是否支持杜比<br/>
     *
     * @date 2016年7月6日
     */
    public static boolean isSupportedDolby() {
        boolean support = true;
        String value = SystemProperties.get("third.get.ms.dobly");
        if (!isEmptyString(value) && value.equals("0")) {
            support = false;
        }
        return support;
    }

    /**
     * 概述：判断电视是否是OLED电视<br/>
     */
    public static boolean isOLED() {
        return isPanelFutureSpecified("OLED");
    }

    /**
     * 概述：获取系统是否支持4K,默认支持<br/>
     */
    public static boolean isSupport4K() {
        boolean ret = isPanelFutureSpecified("4K");
        if (!ret) {
            ret = true;
            String value = SystemProperties.get("third.get.4k");
            if (!isEmptyString(value) && value.equals("0")) {
                ret = false;
            }
        }
        return ret;
    }

    /**
     * 判断系统是否是内测版本，某些功能针对内测版本会对界面和逻辑有所调整
     *
     * @return
     */
    public static boolean isBetaVersion() {
        return new File("/system/vendor/Betaflag").exists();
    }

    public static boolean isEmptyString(String str) {
        if (str == null || str.equals("")) {
            return true;
        }
        return false;
    }

    /**
     * 获取应用返回时是不是返回到Launcher（注：是Android系统的Launcher，而不是酷开系统的HOME）
     *
     * @return <li>true：返回到Launcher</li>
     * <li>false：返回到普通应用</li>
     */
    public static boolean isBackToLauncher(Context context) {
        boolean ret = false;
        if (context == null)
            return ret;
        try {
            ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            Method method = am.getClass().getDeclaredMethod("isBackToLauncher", String.class);
            method.setAccessible(true);
            ret = (Boolean) method.invoke(am, context.getPackageName());
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return ret;
    }

    //获取目前网络流量
    private static long[] readCurrentNetDevFlow() {
        String filePath = "/proc/self/net/dev";
        long totalBytes[] = new long[2];
        try {
            FileReader fr = new FileReader(filePath);
            BufferedReader br = new BufferedReader(fr);
            String line;
            String[] tmpData;
            LinkedList<String> data;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.contains("lo") || line.contains("eth") || line.contains("wlan")
                        || line.contains("rmnet")) {
                    // 累加得到流量
                    tmpData = line.split(":")[1].trim().split(" "); // 分割到各个数字 data[1]是下载和data[9]是上传
                    data = new LinkedList<String>();
                    for (int i = 0; i < tmpData.length; i++) {
                        if (tmpData[i] != null && !tmpData[i].equals("") && !tmpData[i].equals(" ")) {
                            data.add(tmpData[i]);
                        }
                    }
                    try {
                        totalBytes[0] += Long.parseLong(data.get(0));
                        totalBytes[1] += Long.parseLong(data.get(8));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            br.close();
            fr.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return totalBytes;
    }

    public static String getSystemVersion() {
        String version = readFileByLines("/system/vendor/TianciVersion").trim();
        return version;
    }

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

    /**
     * 获取电视尺寸（如42，55），获取不到或者出现异常则返回 null 或者""
     */
    public static String getPanelSize() {
        String panelSize = null;
        //panel size 新流程 20170323
        panelSize = SystemProperties.get("persist.sys.panel_size");
        if (panelSize != null && !panelSize.equals("")) {
            return panelSize;
        }
        //20170323
        String barcode = SystemProperties.get("third.get.barcode");
        String skyType = SystemProperties.get("ro.build.skytype");
        if (barcode != null && skyType != null) {
            String[] barcodes = barcode.split("-");
            if (barcodes == null || barcodes.length <= 0)
                return panelSize;
            String typeAndSize = barcodes[0];
            int start = 0;
            int end = start + 2;
            String[] specialTypes = new String[]{"14KJ", "14A", "15A"};
            String uppercaseType = skyType.toUpperCase();
            boolean isSpecialType = false;
            for (String s : specialTypes) {
                if (uppercaseType.startsWith(s)) {
                    isSpecialType = true;
                    break;
                }
            }
            if (isSpecialType) {
                //特殊某些产品，比如14KJ的是 14K40JX-S000032-Z140801-8552D，但是skyType却是 14KJ
                //类似还有A55，barcode是14A55XX-H000015-Z140903-81AEB，但是skyType却是14A55
                start = 3;
                end = start + 2;
            } else if (barcode.startsWith(skyType)) {
                //酷开某些产品一场，尺寸在中间14K40XX-S000036-Z140818-00D54
                start = typeAndSize.indexOf(skyType) + skyType.length();
                end = start + 2;
            } else {
                //正常的barcode，尺寸在前面 55S9300-S000006-Z150704-5D996
                end = typeAndSize.indexOf(skyType);
            }
            try {
                panelSize = typeAndSize.substring(start, end);
            } catch (java.lang.StringIndexOutOfBoundsException e) {
                e.printStackTrace();
            }
        }
        return panelSize;
    }

    public static boolean isPanelFutureSpecified(final String specifiedPanelFuture) {
        String panel = GeneralConfig.getConfig("PANEL");
        if (specifiedPanelFuture == null || panel == null)
            return false;
        String[] panels = panel.split("\\|");
        if (panels == null)
            return false;
        for (String s : panels) {
            if (s.toUpperCase().equals(specifiedPanelFuture.toUpperCase()))
                return true;
        }
        return false;
    }

    public static String getEmmcIdFromFile() {
        String emmcid = readFileByLines("/sys/block/mmcblk0/device/cid");
        emmcid = emmcid.trim();
        return emmcid;
    }
}
