/**
 * Copyright (C) 2012 The SkyTvOS Project
 * <p>
 * Version     Date           Author
 * ─────────────────────────────────────
 * 2017年10月31日         pis
 */

package com.tianci.loader;

import com.tianci.utils.SkySSSLog;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * 酷开版本工具类
 * 2017年10月31日
 * @author pis
 */
public class ForceVersionUtils
{
    private static final String TAG = "upgrade";

    /**
     * 概述：系统酷开版本是否高于6.10<br/>
     *          适用条件：<br/>
     *          执行流程：<br/>
     *          使用方法：<br/>
     *          注意事项：<br/>
     * @param
     * @return boolean
     * @date 2017-10-31
     */
    public static boolean isTcVersionUpper6_10()
    {
        String tc_version = readFileByLines("/system/vendor/TianciVersion").trim();
        String[] versions = tc_version.split("\\.");
        if (versions.length > 0)
        {
            try
            {
                Integer version = Integer.valueOf(versions[0]);
                Integer version2 = Integer.valueOf(versions[1]);
                if ((version >= 6 && version2 >= 10) || (version >= 7))
                {
                    SkySSSLog.d(TAG, "tcversion upper 6.10");
                    return true;
                }
            } catch (NumberFormatException e)
            {
                e.printStackTrace();
            }
        }
        return false;
    }

	 /**
     * 概述：以行为单位读取文件，常用于读面向行的格式化文件<br/>
     *          适用条件：<br/>
     *          执行流程：<br/>
     *          使用方法：<br/>
     *          注意事项：<br/>
     * @param fileName
     * @return String
     * @date 2017-10-31
     */
    private static String readFileByLines(String fileName)
    {
        String content = "";
        File file = new File(fileName);
        BufferedReader reader = null;
        try
        {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            int line = 1;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null)
            {
                // 显示行号
                content += tempString;
                SkySSSLog.d(TAG, "line " + line + ": " + tempString);
                line++;
            }
            reader.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (reader != null)
            {
                try
                {
                    reader.close();
                }
                catch (IOException e1)
                {
                }
            }
        }
        return content;
    }
}
