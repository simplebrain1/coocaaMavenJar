/**
 * Copyright (C) 2012 The SkyTvOS Project
 * <p>
 * Version     Date           Author
 * ─────────────────────────────────────
 * 2015年12月1日         wen
 */

package com.tianci.loader;

import android.text.TextUtils;

import com.skyworth.framework.config.GeneralConfig;
import com.skyworth.framework.skysdk.properties.SkyGeneralProperties;
import com.skyworth.framework.utils.SystemPropertiesUtil;
import com.tianci.utils.SkySSSLog;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

/**
 * 管理强制升级包标志文件 2015年12月1日
 *
 * @author wen
 */
public class ForceFlagUtils
{
    private static final String TAG = "upgrade";

    /**
     * @Fields FORCE_UPGRADE_FLAG 强制升级包是否存在（下载完成并校验成功）文件名
     */
    private static final String FORCE_UPGRADE_FLAG = "force_upgrade";

    /**
     * 删除标志文件<br/> 2015年12月1日
     *
     * @return boolean true 删除成功
     */
    public static boolean deleteFlagFile()
    {
        long time = System.nanoTime();
        boolean deleteRes = true;
        File flagFile = new File(getFlagFilePath());
        if (flagFile.exists())
        {
            System.out.println("deleteFlagFile, exist ");
            deleteRes = flagFile.delete();
        }
        SkySSSLog.d(TAG, "deleteFlagFile, res = " + deleteRes);
        SkySSSLog.d(TAG, "deleteFlagFile, time = " + (System.nanoTime() - time));
        return deleteRes;
    }

    /**
     * 创建标志文件，以版本号为内容<br/> 如果文件已存在，则直接写入新内容，覆盖之前 2015年12月1日
     *
     * @return boolean true 创建成功
     */
    public static boolean createFlagFile(String version)
    {
        long time = System.nanoTime();
        boolean createRes = false;
        File flagFile = new File(getFlagFilePath());
        if (flagFile.exists())
        {
            createRes = true;
        } else
        { // 文件不存在，创建并加全局写权限
            SkySSSLog.d(TAG, "createFlagFile, not exist ");
            try
            {
                createRes = flagFile.createNewFile();
            } catch (IOException e)
            {
                SkySSSLog.e(TAG, "createflagfile, ioexception = " + e.getMessage());
                e.printStackTrace();
            }
            if (createRes)
            {
                boolean readable = flagFile.setReadable(true, false);
                SkySSSLog.d(TAG, "createFlagFile, after setReadable = " + readable);
            }
        }
        SkySSSLog.d(TAG, "createFlagFile, res = " + createRes);
        SkySSSLog.d(TAG, "createFlagFile, time = " + (System.nanoTime() - time));

        if (createRes)
        {
            writeVersion(version);
        }

        return createRes;
    }

    private static void writeVersion(String version)
    {
        long time = System.nanoTime();
        SkySSSLog.d(TAG, "writeVersion(), version = " + version);

        FileWriter writer = null;
        try
        {
            // 打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件
            writer = new FileWriter(getFlagFilePath(), false);
            writer.write(version);
        } catch (IOException e)
        {
            e.printStackTrace();
        } finally
        {
            if (writer != null)
            {
                try
                {
                    writer.close();
                } catch (IOException e)
                {
                }
            }
        }
        SkySSSLog.d(TAG, "write versoin, time = " + (System.nanoTime() - time));
    }

    public static String readVersion()
    {
        BufferedReader reader = null;
        StringBuilder sb = new StringBuilder();
        try
        {
            SkySSSLog.i(TAG, "以行为单位读取文件内容，一次读一整行：");
            reader = new BufferedReader(new FileReader(getFlagFilePath()));
            String tempString = null;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null)
            {
                sb.append(tempString);
            }
            SkySSSLog.i(TAG, "readversion, versoin = " + sb.toString());
        } catch (IOException e)
        {
            SkySSSLog.e(TAG, "read version, exception = " + e.getMessage());
            e.printStackTrace();
        } finally
        {
            if (reader != null)
            {
                try
                {
                    reader.close();
                } catch (IOException e1)
                {
                }
            }
        }
        return sb.toString();
    }

    private static String flagFilePath = null;

    public static String getFlagFilePath()
    {
        if (TextUtils.isEmpty(flagFilePath))
        {
            flagFilePath = SkyGeneralProperties.getProperty(SkyGeneralProperties.GeneralPropKey.RWDIR) + File.separator +
                    FORCE_UPGRADE_FLAG;
            SkySSSLog.i(TAG, "getFlagFilePath, path = " + flagFilePath);
        }
        return flagFilePath;
    }

    /**
     * 检查/cache分区是否挂载（即检查/cache/recovery/last_locale文件是否存在），如果未挂载则保存日志
     */
    public static boolean checkCacheDisk()
    {
        File file = new File("/cache/recovery/last_locale");
        boolean exists = file.exists();
        System.out.println("check /cache/recovery/last_locale, res = " + exists);
        if (!exists)
        {
            String logFileName =
                    SystemPropertiesUtil.getProperty(GeneralConfig.GeneralPropKey.RWDIR.toString()) + File.separator +
                            "cache_partition_log";
            System.out.println("cache_partition_log, file name = " + logFileName);
            File logFile = new File(logFileName);
            FileOutputStream fos = null;
            try
            {
                fos = new FileOutputStream(logFile);
                String content = System.currentTimeMillis() + " - " + new Date();
                fos.write(content.getBytes());
            } catch (IOException e)
            {
                e.printStackTrace();
            } finally
            {
                if (fos != null)
                {
                    try
                    {
                        fos.close();
                    } catch (IOException e)
                    {
                    }
                }
            }
        }

        return exists;
    }
}
