/**
 * Copyright (C) 2012 The SkyTvOS Project
 *
 * Version     Date           Author
 * ─────────────────────────────────────
 *           2015年4月16日         wen
 *
 */

package com.tianci.loader;

import com.skyworth.framework.skysdk.util.SkyJSONUtil;

public class LoaderAppParams extends LoaderParams
{
    private static final long serialVersionUID = -8880733310553655896L;
    public String absPath; // APK绝对路径
    public String appName; // 应用名称
    public String pkgName; // 包名
    public boolean installRes; // 安装结果（成功、失败）

    public LoaderAppParams()
    {
        this.updateType = UpdateType.App;
        this.viewType = LoaderViewStatus.INSTALL;
    }

    private LoaderAppParams(String jsonString)
    {
        System.out.println("app === " + jsonString);
        LoaderAppParams params = SkyJSONUtil.getInstance().parseObject(jsonString,
                LoaderAppParams.class);
        if (params != null)
        {
            copyFileds(params);
        }
    }

    public LoaderAppParams(byte[] bytes)
    {
        this(new String(bytes));
        // LoaderAppParams params = SkyObjectByteSerialzie.toObject(bytes, LoaderAppParams.class);
        // if (params != null)
        // {
        // copyFileds(params);
        // }
    }

    protected void copyFileds(LoaderAppParams params)
    {
        super.copyFileds(params);
        this.absPath = params.absPath;
        this.appName = params.appName;
        this.pkgName = params.pkgName;
        this.installRes = params.installRes;
    }

    // public byte[] getBytes()
    // {
    // // return SkyObjectByteSerialzie.toBytes(this);
    // return toString().getBytes();
    // }

    @Override
    public String toString()
    {
        return SkyJSONUtil.getInstance().compile(this);
    }

}
