/**
 * Copyright (C) 2012 The SkyTvOS Project
 *
 * Version     Date           Author
 * ─────────────────────────────────────
 *           2012-11-21         Wei li
 *
 */

package com.tianci.system.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager.NameNotFoundException;

/**
 * @ClassName SkyShareData
 * @Description TODO (write something)
 * @author Wei li
 * @date 2012-11-21
 * @version TODO (write something)
 */
public class SkyShareDataStable
{
    
    public static void setSharedValue(Context context,String name, String value){
        try
        {
            if(value == null)
                return;
            SharedPreferences pwdPrefs = context.getSharedPreferences("system_datast",
                    Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = pwdPrefs.edit();
            editor.putString(name, value);
            editor.commit();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public static String getSharedValue(Context context,String name){
        try
        {
            SharedPreferences pwdPrefs = context.getSharedPreferences("system_datast", 0);
            return pwdPrefs.getString(name, "");
        } catch (Exception e)
        {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * android7.0以后，MODE_WORLD_READABLE被废弃，会读取不到写入的数据
     */
    public static int getIntSharedValue(Context context,String name)
    {
        try
        {
            SharedPreferences pwdPrefs = context.getSharedPreferences("system_datast", Context.MODE_PRIVATE);
            return pwdPrefs.getInt(name, -1);
        } catch (Exception e)
        {
            e.printStackTrace();
            return 0;
        }
    }
    
    public static void setIntSharedValue(Context context,String name, int value)
    {
    	try
        {
            SharedPreferences pwdPrefs = context.getSharedPreferences("system_datast",
                    Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = pwdPrefs.edit();
            editor.putInt(name, value);
            editor.commit();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public static boolean getBooleanSharedValue(Context context,String name)
    {
        try
        {
            SharedPreferences pwdPrefs = context.getSharedPreferences("system_datast", 0);
            return pwdPrefs.getBoolean(name, false);
        } catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }
    
    public static boolean getBooleanSharedValue(Context context,String name, boolean defaultValue)
    {
        try
        {
            SharedPreferences pwdPrefs = context.getSharedPreferences("system_datast", 0);
            return pwdPrefs.getBoolean(name, defaultValue);
        } catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }
    
    public static void setBooleanSharedValue(Context context,String name, boolean value)
    {
    	try
        {
            SharedPreferences pwdPrefs = context.getSharedPreferences("system_datast",
                    Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = pwdPrefs.edit();
            editor.putBoolean(name, value);
            editor.commit();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public static void cleanShareValue(Context context)
    {
        try
        {
            SharedPreferences pwdPrefs = context.getSharedPreferences("system_datast", 0);
            SharedPreferences.Editor editor = pwdPrefs.edit();
            editor.clear();
            editor.commit();
        } 
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static long getLongSharedValue(Context context,String name)
    {
        try
        {
            SharedPreferences pwdPrefs = context.getSharedPreferences("system_datast", 0);
            return pwdPrefs.getLong(name, 0);
        } catch (Exception e)
        {
            e.printStackTrace();
            return 0;
        }
    }

    public static void setLongSharedValue(Context context,String name, long value)
    {
        try
        {
            SharedPreferences pwdPrefs = context.getSharedPreferences("system_datast",
                    Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = pwdPrefs.edit();
            editor.putLong(name, value);
            editor.commit();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static float getFloatSharedValue(Context context,String name) {
        try {
            SharedPreferences pwdPrefs = context.getSharedPreferences("system_datast", 0);
            return pwdPrefs.getFloat(name, 0);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static void setFloatSharedValue(Context context,String name, float value) {
        try {
            SharedPreferences pwdPrefs = context.getSharedPreferences("system_datast",
                    Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = pwdPrefs.edit();
            editor.putFloat(name, value);
            editor.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
