/**
 * Copyright (C) 2012 The SkyTvOS Project
 *
 * Version     Date           Author
 * ─────────────────────────────────────
 *           2013-8-3         xingkong207
 *
 */

package com.tianci.system.data;

import java.util.UUID;

/**
 * <p>
 * Description:
 * </p>
 * <p>
 * write something
 * </p>
 * 
 * @ClassName UpgradeBaseItem
 * @author wei li
 * @date 2013-8-3
 * @version V1.0.0
 */
public abstract class UpgradeBaseItem
{
    public enum UpgradeItemType
    {
        SYSTEM_UPGRADE, APP_UPGRADE, DTV_UPGRADE,
    }

    UpgradeItemType mType = null;
    private String id = UUID.randomUUID().toString();
    private String downloadUrl;
    private String desc;
    private String md5;
    private int fileSize;
    private String packageName;// 比如：com.skyworth.appstore
    private String moduleName;// 比如：应用商城

    public UpgradeBaseItem()
    {
    }

    public UpgradeBaseItem(UpgradeItemType type)
    {
        this.mType = type;
    }

    public UpgradeItemType getUpgradeType()
    {
        return this.mType;
    }

    public void setUpgradeType(UpgradeItemType type)
    {
        this.mType = type;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String i)
    {
        this.id = i;
    }

    public String getDownloadUrl()
    {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl)
    {
        this.downloadUrl = downloadUrl;
    }

    public String getDesc()
    {
        if (UpgradeItemType.SYSTEM_UPGRADE.equals(mType))
        {
            return "";
        }
        else
        {
            return desc;
        }
    }

    public void setDesc(String desc)
    {
        this.desc = desc;
    }

    public String getMd5()
    {
        return md5;
    }

    public void setMd5(String md5)
    {
        this.md5 = md5;
    }

    public int getFileSize()
    {
        return fileSize;
    }

    public void setFileSize(int fileSize)
    {
        this.fileSize = fileSize;
    }

    public String getPackageName()
    {
        return packageName;
    }

    public void setPackageName(String packageName)
    {
        this.packageName = packageName;
    }

    public String getModuleName()
    {
        return moduleName;
    }

    public void setModuleName(String moduleName)
    {
        this.moduleName = moduleName;
    }
}
