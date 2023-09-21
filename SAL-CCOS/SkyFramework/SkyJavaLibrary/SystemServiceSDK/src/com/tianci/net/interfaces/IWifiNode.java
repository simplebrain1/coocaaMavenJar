/**
 * Copyright (C) 2012 The SkyTvOS Project
 *
 * Version     Date           Author
 * ─────────────────────────────────────
 *           2014-10-23          mikan
 *
 */

package com.tianci.net.interfaces;

import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import com.tianci.net.data.SkyWifiAPItem;
import com.tianci.net.data.SkyWifiAPStaticItem;

import java.util.List;

/**
 * <p>
 * Description:
 * </p>
 * <p>
 * write something
 * </p>
 * 
 * @ClassName IWifiNode
 * @author mikan
 * @date 2014-10-23
 * @version V1.0.0
 */
public interface IWifiNode
{
    /**
     * 概述：动态连接wifi<br/>
     * 适用条件：<br/>
     * 执行流程：<br/>
     * 使用方法：<br/>
     * 注意事项：<br/>
     * 
     * @param info
     * @return boolean
     * @date 2014-4-10
     */
    public boolean connectNetworkByDhcp(SkyWifiAPItem info);

    /**
     * 概述：静态连接wifi<br/>
     * 适用条件：<br/>
     * 执行流程：<br/>
     * 使用方法：<br/>
     * 注意事项：<br/>
     * 
     * @param item
     * @return boolean
     * @date 2014-10-23
     */
    public boolean connectNetworkByStatic(SkyWifiAPStaticItem item);

    /**
     * 概述：动态连接wifi<br/>
     * 适用条件：<br/>用于连接非隐藏wifi
     * 执行流程：<br/>
     * 使用方法：<br/>
     * 注意事项：<br/>
     *
     * @param info
     * @return boolean
     * @date 2014-4-10
     */
    public boolean connectNetworkByDhcpNotHidden(SkyWifiAPItem info);

    /**
     * 概述：静态连接wifi<br/>
     * 适用条件：<br/>用于连接非隐藏wifi
     * 执行流程：<br/>
     * 使用方法：<br/>
     * 注意事项：<br/>
     *
     * @param item
     * @return boolean
     * @date 2014-10-23
     */
    public boolean connectNetworkByStaticNotHidden(SkyWifiAPStaticItem item);

    /**
     * 概述：断开当前连接wifi<br/>
     * 适用条件：<br/>
     * 执行流程：<br/>
     * 使用方法：<br/>
     * 注意事项：<br/>
     * 
     * @return boolean
     * @date 2014-4-10
     */
    public boolean disconnect();

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
     * 概述：获取wifi搜索ap结果<br/>
     * 适用条件：<br/>
     * 执行流程：<br/>
     * 使用方法：<br/>
     * 注意事项：<br/>
     * 
     * @return List<ScanResult>
     * @date 2014-4-10
     */
    public List<ScanResult> getScanResults();

    /**
     * 概述：获取当前连接网络的信号强度<br/>
     * 适用条件：<br/>
     * 执行流程：<br/>
     * 使用方法：<br/>
     * 注意事项：返回值必须参照安卓标准的5个强度等级<br/>
     * 
     * @return int
     * @date 2014-7-30
     */
    public int getRisi();

    /**
     * 概述：获取当前连接ap的pwd<br/>
     * 适用条件：<br/>
     * 执行流程：<br/>
     * 使用方法：<br/>
     * 注意事项：<br/>
     * 
     * @return String
     * @date 2014-10-24
     */
    public String getCurrApPwd(String ssid);

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
    public boolean forgetAp(String ssid);

    /**
     * 概述：获取保存的网络信息<br/>
     * 适用条件：<br/>
     * 执行流程：<br/>
     * 使用方法：<br/>
     * 注意事项：<br/>
     *
     * @return List
     * @date 2015-5-06
     */
    public List<WifiConfiguration> getConfiguredNetworks();

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
