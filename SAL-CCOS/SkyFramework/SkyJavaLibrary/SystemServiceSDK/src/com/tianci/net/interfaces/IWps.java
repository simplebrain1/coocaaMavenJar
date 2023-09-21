/**
 * Copyright (C) 2012 The SkyTvOS Project
 *
 * Version     Date           Author
 * ─────────────────────────────────────
 *           2013-12-20         mikan
 *
 */

package com.tianci.net.interfaces;

/**
 * <p>Description:wps连接功能接口</p> 
 * <p>write something</p>
 * @ClassName IWps
 * @author mikan
 * @date 2013-12-20
 * @version V1.0.0
 */
public interface IWps
{
    /**
     * 概述：启动无线一键通<br/>
     *      适用条件：<br/>
     *      执行流程：<br/>
     *      使用方法：<br/>
     *      注意事项：<br/>
     * @param wpsSsid boolean
     * @date 2013-12-9
     */
    public boolean startWps(String wpsSsid);
    
    /**
     * 概述：是否支持无线一键通<br/>
     *      适用条件：<br/>
     *      执行流程：<br/>
     *      使用方法：<br/>
     *      注意事项：<br/>
     * @return boolean
     * @date 2013-12-9
     */
    public abstract boolean isSupportWps();
}
