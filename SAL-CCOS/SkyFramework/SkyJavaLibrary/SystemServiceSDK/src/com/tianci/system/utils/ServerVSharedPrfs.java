package com.tianci.system.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class ServerVSharedPrfs {
    private static final String FILE = "serverv_cfg";
    private static final int MODE = Activity.MODE_APPEND;
    private static SharedPreferences sp;

    public static final String KEY_CUR_CFG = "cur_cfg";
    public static final String KEY_OTA_CFG = "ota_cfg";
    public static final String KEY_OTA_INFO = "ota_info"; // 新的升级信息
    public static final String KEY_LAST_DESC = "last_desc";
    public static final String KEY_USE_EXTRA_DISK = "use_extra_disk";
    public static final String KEY_DELAY_CFG = "delay_cfg";
    public static final String KEY_DELAY_CFG_NEW = "delay_cfg_new";
    @Deprecated
    public static final String KEY_UPGRADE_STATUS = "uspgrade_status";
    public static final String KEY_LOADER_STATUS = "loader_status"; // 新的update status
    public static final String KEY_OTA_PATH = "ota_path";
    public static final String KEY_HANDLER_TYPE = "upgrade_handler_type";
    public static final String KEY_USER_OPRATE = "user_oprate_sign";
    public static final String KEY_CUR_TC_VER = "cur_tc_ver";
    public static final String KEY_CUR_SYS_VER = "cur_sys_ver";
    /**
     * @Fields KEY_MD5_ERROR_TIMES MD5校验失败的次数（每次开机重置）
     */
    public static final String KEY_MD5_ERROR_TIMES = "md5_error_times";
    private static final String KEY_DOWNLOAD_PERCENT = "upgrade_download_percent";

    public synchronized  static boolean setString(Context context,String key, String value)
    {
        if (getSpInstance(context))
        {
            return sp.edit().putString(key, value).commit();
        }
        return false;
    }

    public synchronized  static String getString(Context context,String key, String value)
    {
        if (getSpInstance(context))
        {
            return sp.getString(key, value);
        }
        return value;
    }

    public synchronized  static boolean rmString(Context context,String key)
    {
        if (getSpInstance(context))
        {
            return sp.edit().remove(key).commit();
        }
        return false;
    }

    public synchronized  static boolean clear(Context context)
    {
        if (getSpInstance(context))
        {
            return sp.edit().clear().commit();
        }
        return false;
    }

    private static boolean getSpInstance(Context context)
    {
        if (sp == null && context != null)
        {
            sp = context.getSharedPreferences(FILE, MODE);
        }
        return sp == null ? false : true;
    }
}
