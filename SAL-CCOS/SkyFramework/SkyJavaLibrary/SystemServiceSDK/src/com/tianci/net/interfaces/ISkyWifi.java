/**
 * Copyright (C) 2012 The SkyTvOS Project
 *
 * Version     Date           Author
 * ─────────────────────────────────────
 *           2013-12-09         mikan
 *
 */

package com.tianci.net.interfaces;

import com.tianci.net.data.SkyWifiAPItem;
import com.tianci.net.data.SkyWifiAPStaticItem;

import java.util.ArrayList;

/**
 * <p>
 * 无线网络功能接口
 * </p>
 * 
 * @ClassName ISkyWifiManager
 * @author mikan
 * @date 2013-12-9
 * @version V1.0.0
 */
public interface ISkyWifi
{

    /**
     * 概述：获取已连接的网络名称<br/>
     * 适用条件：<br/>
     * 执行流程：<br/>
     * 使用方法：<br/>
     * 注意事项：<br/>
     * 
     * @return String
     * @date 2013-12-9
     */
    public String getConnectSSID();

    /**
     * 概述：搜索无线列表<br/>
     * 适用条件：<br/>
     * 执行流程：<br/>
     * 使用方法：<br/>
     * 注意事项：<br/>
     * 
     * @return boolean
     * @date 2013-12-19
     */
    public boolean startScan();

    /**
     * 概述：获取Wifi状态<br/>
     * 适用条件：<br/>
     * 执行流程：<br/>
     * 使用方法：<br/>
     * 注意事项：<br/>
     * 
     * @return String
     * @date 2013-12-9
     */
    public String getWifiState();

    /**
     * 概述：获取ap列表<br/>
     * 适用条件：<br/>
     * 执行流程：<br/>
     * 使用方法：<br/>
     * 注意事项：<br/>
     * 
     * @return ArrayList<SkyWifiAPItem>
     * @date 2013-12-12
     */
    public ArrayList<SkyWifiAPItem> getApList();

    /**
     * 概述：获取保存的ap列表<br/>
     * 适用条件：<br/>
     * 执行流程：<br/>
     * 使用方法：<br/>
     * 注意事项：<br/>
     * 
     * @return ArrayList<String>
     * @date 2013-12-12
     */
    public ArrayList<String> getHistoryApList();

    /**
     * <p>
     * Description:搜索ap回调
     * </p>
     * <p>
     * write something
     * </p>
     * 
     * @ClassName IScanApListener
     * @author mikan
     * @date 2014-1-18
     * @version V1.0.0
     */
    public interface IWifiScanApListener
    {
        /**
         * 概述：ap搜索完成事件处理<br/>
         * 适用条件：<br/>
         * 执行流程：<br/>
         * 使用方法：<br/>
         * 注意事项：<br/>
         * void
         * 
         * @param wifiAPItems
         * 
         * @date 2014-1-18
         */
        void onScanApFinished(ArrayList<SkyWifiAPItem> wifiAPItems);
    }

    /**
     * 概述：获取当前连接的ap的信号强度<br/>
     * 适用条件：<br/>
     * 执行流程：<br/>
     * 使用方法：<br/>
     * 注意事项：<br/>
     * 
     * @return int
     * @date 2014-7-30
     */
    public int getRssi();

    /**
     * 概述：忽略指定的wifi<br/>
     * 适用条件：<br/>
     * 执行流程：<br/>
     * 使用方法：<br/>
     * 注意事项：<br/>
     *
     * @return boolean
     * @date 2015-3-16
     */
    public abstract boolean forgetAp(String ssid);

    /**
     * 概述：获取某个ap是否曾经配置过<br/>
     * 适用条件：<br/>
     * 执行流程：<br/>
     * 使用方法：<br/>
     * 注意事项：<br/>
     *
     * @return boolean
     * @date 2015-3-16
     */
    public boolean isConfigured(SkyWifiAPItem item);

    /**
     * 概述：清除某个ap曾经的配置<br/>
     * 适用条件：<br/>
     * 执行流程：<br/>
     * 使用方法：<br/>
     * 注意事项：<br/>
     *
     * @return boolean
     * @date 2015-3-16
     */
    public boolean removeNetwork(SkyWifiAPItem item);

    /**
     * 概述：保存某个ap配置<br/>
     * 适用条件：<br/>
     * 执行流程：<br/>
     * 使用方法：<br/>
     * 注意事项：<br/>
     *
     * @return boolean
     * @date 2015-3-16
     */
    public boolean saveConfig(SkyWifiAPStaticItem config);
}
