/**
 * Copyright (C) 2012 The SkyTvOS Project
 *
 * Version     Date           Author
 * ─────────────────────────────────────
 *           2014-10-24          mikan
 *
 */

package com.tianci.net.interfaces;

import com.tianci.net.define.NetworkDefs.NetworkDevices;

public interface INetSystem
{
//    /**
//     * 概述：获取当前活跃的网络类型<br/>
//     * 适用条件：<br/>
//     * 执行流程：<br/>
//     * 使用方法：<br/>
//     * 注意事项：<br/>
//     *
//     * @return SkyNetType
//     * @date 2014-4-10
//     */
//    public NetworkDevices getCurrNetworkType();
//
//    /**
//     * 概述：判断当前网络是否已经连接<br/>
//     * 适用条件：<br/>
//     * 执行流程：<br/>
//     * 使用方法：<br/>
//     * 注意事项：<br/>
//     *
//     * @return boolean
//     * @date 2014-8-20
//     */
//    public boolean isConnect();

    /**
     * 概述：连接本设备网络前是否需要断开其他网络<br/>
     * 适用条件：<br/>
     * 执行流程：<br/>
     * 使用方法：<br/>
     * 注意事项：<br/>
     *
     * @return boolean
     * @date 2014-5-9
     */
    public abstract boolean isNeedReleaseDeviceNetwork(NetworkDevices device);
}
