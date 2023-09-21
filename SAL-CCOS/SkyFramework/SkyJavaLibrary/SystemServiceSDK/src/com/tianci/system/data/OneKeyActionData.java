/**
 * Copyright (C) 2014 The SkyTvOS Project
 *
 * Version     Date           Author
 * ─────────────────────────────────────
 *           2017年8月17日         SimplySu
 *
 */

package com.tianci.system.data;

import java.io.Serializable;

/**
 * 
 * @ClassName OneKeyActionData
 * @Description 按键一键启动数据类 <br/>
 *              如：遥控蓝键->启动本地媒体<br/>
 *              遥控红键->启动影视中心
 * 
 */
public class OneKeyActionData implements Serializable
{

    /**
     * @Fields serialVersionUID TODO(write something)
     */
    private static final long serialVersionUID = 2031631134663798935L;

    /**
     * 遥控按键的index索引
     */
    private int keyIndex;
    /**
     * 遥控按键的启动程序索引
     */
    private String keyActionValue;

    public OneKeyActionData(int keyIndex, String keyActionValue)
    {
        this.keyIndex = keyIndex;
        this.keyActionValue = keyActionValue;
    }

    public int getKeyIndex()
    {
        return keyIndex;
    }

    public void setKeyIndex(int keyIndex)
    {
        this.keyIndex = keyIndex;
    }

    public String getKeyActionValue()
    {
        return keyActionValue;
    }

    public void setKeyActionIndex(String keyActionValue)
    {
        this.keyActionValue = keyActionValue;
    }
}
