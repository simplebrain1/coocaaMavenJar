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
 * @ClassName VersionItem
 * @author wei li
 * @date 2013-11-6
 * @version V*.*.*
 */
public class VersionItem
{
    int mId = 0;
    String mName = null;
    String mVersion = null;
    String mDate = null;

    public int getmId()
    {
        return mId;
    }

    public void setmId(int mId)
    {
        this.mId = mId;
    }

    public String getmName()
    {
        return mName;
    }

    public void setmName(String mName)
    {
        this.mName = mName;
    }

    public String getmVersion()
    {
        return mVersion;
    }

    public void setmVersion(String mVersion)
    {
        this.mVersion = mVersion;
    }

    public String getmDate()
    {
        return mDate;
    }

    public void setmDate(String mDate)
    {
        this.mDate = mDate;
    }

    public String toString()
    {
        return "id=" + mId + "; pkgname=" + mName + ";version=" + mVersion + ";date=" + mDate;
    }
}
