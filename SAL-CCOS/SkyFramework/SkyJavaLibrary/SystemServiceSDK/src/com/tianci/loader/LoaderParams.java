/**
 * Copyright (C) 2012 The SkyTvOS Project
 *
 * Version     Date           Author
 * ─────────────────────────────────────
 *           2015年4月13日         wen
 *
 */

package com.tianci.loader;

import com.skyworth.framework.skysdk.util.SkyJSONUtil;

import java.io.Serializable;

public class LoaderParams implements Serializable
{
    public enum UpdateType
    {
        System, App,
    }

    /**
     * @Description 系统升级类型：普通、强制、修复
     * @date 2015年5月7日
     */
    public enum UpdateHandlerType
    {
        COMMON, FORCE, REPAIR,
    }

    private static final long serialVersionUID = -3484676182891693028L;

    public UpdateType updateType = UpdateType.System;
    public String from;
    /**
     * @Fields version 版本
     */
    public String version;
    /**
     * @Fields releaseTime 发布时间
     */
    public String releaseTime;
    /**
     * @Fields description 描述信息
     */
    public String description;
    /**
     * @Fields viewType 界面类型
     */
    public LoaderViewStatus viewType;
    /**
     * @Fields handlerType 升级类型：客户处理方式
     */
    public UpdateHandlerType handlerType = UpdateHandlerType.COMMON;

    /**
     * @Fields isForce 是否强制升级
     */
    // public boolean isForce;

    public LoaderParams()
    {
    }

    private LoaderParams(String jsonString)
    {
        System.out.println("loader params = " + jsonString);
        LoaderParams params = SkyJSONUtil.getInstance().parseObject(jsonString, LoaderParams.class);
        if (params != null)
        {
            copyFileds(params);
        }
    }

    protected void copyFileds(LoaderParams params)
    {
        this.description = params.description;
        this.from = params.from;
        this.releaseTime = params.releaseTime;
        this.updateType = params.updateType;
        this.version = params.version;
        this.viewType = params.viewType;
        this.handlerType = params.handlerType;
        // this.isForce = params.isForce;
    }

    public LoaderParams(byte[] bytes)
    {
        this(new String(bytes));
        // LoaderParams params = SkyObjectByteSerialzie.toObject(bytes, LoaderParams.class);
        // if (params != null)
        // {
        // copyFileds(params);
        // }
    }

    // public byte[] getBytes()
    // {
    // return SkyObjectByteSerialzie.toBytes(this);
    // // return toString().getBytes();
    // }

    @Override
    public String toString()
    {
        return SkyJSONUtil.getInstance().compile(this);
    }
}
