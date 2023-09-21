/**
 * Copyright (C) 2012 The SkyTvOS Project
 *
 * Version     Date           Author
 * ─────────────────────────────────────
 *           2013-11-6        wei li
 *
 */

package com.tianci.system.data;

/**
 * <p>
 * Description:
 * </p>
 * <p>
 * write something
 * </p>
 * 
 * @ClassName ModuleVerItem
 * @author wei li
 * @date 2013-11-6
 * @version V*.*.*
 */
public class ModuleVerItem
{
    String pkgname = null;
    String local_ver = null;
    String online_ver = null;
    String type = null;

    public String getPkgname()
    {
        return pkgname;
    }

    public void setPkgname(String pkgname)
    {
        this.pkgname = pkgname;
    }

    public String getLocal_ver()
    {
        return local_ver;
    }

    public void setLocal_ver(String local_ver)
    {
        this.local_ver = local_ver;
    }

    public String getOnline_ver()
    {
        return online_ver;
    }

    public void setOnline_ver(String online_ver)
    {
        this.online_ver = online_ver;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public String toString()
    {
        return  "pkgname=" + pkgname + "local_ver=" + local_ver + "online_ver=" + online_ver + "type=" + type;
    }
}
