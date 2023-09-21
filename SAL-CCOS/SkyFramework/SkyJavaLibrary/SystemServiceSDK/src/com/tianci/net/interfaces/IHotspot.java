/**
 * Copyright (C) 2012 The SkyTvOS Project
 *
 * Version     Date           Author
 * ─────────────────────────────────────
 *           2013-12-20         mikan
 *
 */

package com.tianci.net.interfaces;

import com.tianci.net.data.SkyHotSpotData;
import com.tianci.net.define.NetworkDefs.HotspotState;

/**
 * <p>Description:热点功能接口</p> 
 * <p>write something</p>
 * @ClassName IHotspot
 * @author mikan
 * @date 2013-12-20
 * @version V*.*.*
 */
public interface IHotspot
{
    /**
     * 概述：启动无线热点<br/>
     * 		适用条件：<br/>
     * 		执行流程：<br/>
     * 		使用方法：<br/>
     * 		注意事项：<br/>
     * @param hotSpotData
     * @return boolean
     * @date 2014-1-4
     */
    public boolean startHotSpot(SkyHotSpotData hotSpotData);
    
    /**
     * 概述：关闭无线热点<br/>
     * 		适用条件：<br/>
     * 		执行流程：<br/>
     * 		使用方法：<br/>
     * 		注意事项：<br/>
     * @return boolean
     * @date 2013-12-20
     */
    public boolean closeHotSpot();
    
    /**
     * 概述：获取当前热点状态<br/>
     * 		适用条件：<br/>
     * 		执行流程：<br/>
     * 		使用方法：<br/>
     * 		注意事项：<br/>
     * @return HotspotState
     * @date 2014-1-4
     */
    public HotspotState getCurrHotspotState();
    
    /**
     * 概述：是否支持无线热点<br/>
     *      适用条件：<br/>
     *      执行流程：<br/>
     *      使用方法：<br/>
     *      注意事项：<br/>
     * @return boolean
     * @date 2013-12-9
     */
    public abstract boolean isSupportHotSpot();
}
