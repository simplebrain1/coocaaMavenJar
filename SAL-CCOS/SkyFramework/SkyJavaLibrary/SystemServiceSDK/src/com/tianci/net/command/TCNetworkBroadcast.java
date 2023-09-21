/**
 * Copyright (C) 2012 The SkyTvOS Project
 *
 * Version     Date           Author
 * ─────────────────────────────────────
 *           2013-12-4         mikan
 *
 */

package com.tianci.net.command;

public enum TCNetworkBroadcast
{
    /**
     * Description: 网络状态改变时发送的广播
     */
    TC_NETWORK_BROADCAST_NET_CONNECTE_STATE_CHANGED,
    /**
     * Description: 网络热点状态改变时发送的广播
     */
    TC_NETWORK_BROADCAST_NET_WIFI_HOT_SPOT_STATE_CHANGED,
    
    /**
     * Description:有线网络状态改变时发送的广播
     */
    TC_NETWORK_BROADCAST_NET_ETH_EVENT,
    /**
     * Description:无线网络状态改变时发送的广播
     */
    TC_NETWORK_BROADCAST_NET_WIFI_EVENT,
    /**
     * Description:启动无线自动连接动作时发送的广播
     */
    TC_NETWORK_BROADCAST_NET_WIFI_START_AUTO_CONNECT,
    /**
     * Description: 无线网络连接过程请求状态改变时发送的广播
     */
    TC_NETWORK_BROADCAST_NET_WIFI_SUPPLICANT_STATE_CHANGED,

    /**
     * Description:PPPoE状态改变时发送的广播
     */
    TC_NETWORK_BROADCAST_NET_PPPOE_EVENT,

    /**
     * Description:移动网络状态改变时发送的广播
     */
    TC_NETWORK_BROADCAST_NET_MOBILE_EVENT,
    
//    /**
//     * Description:网络已断开
//     */
//    TC_NETWORK_BROADCAST_NET_DISCONNECTED,
//    /**
//     * Description:局域网连接成功
//     */
//    TC_NETWORK_BROADCAST_NET_LAN_CONNECTED,
//    /**
//     * Description: 万维网连接成功
//     */
//    TC_NETWORK_BROADCAST_NET_WAN_CONNECTED,
//    /**
//     * Description: 网络连接中
//     */
//    TC_NETWORK_BROADCAST_NET_CONNECTING,
//    /**
//     * Description: 网络断开中
//     */
//    TC_NETWORK_BROADCAST_NET_DISCONNECTING,
//    /**
//     * Description: 未知网络状态
//     */
//    TC_NETWORK_BROADCAST_NET_UNKNOW,
    

    // /**
    // * Description:服务器可连通
    // */
    // TC_NETWORK_BROADCAST_SERVER_CONNECTED,
    // /**
    // * Description:服务器不可连通
    // */
    // TC_NETWORK_BROADCAST_SERVER_DISCONNECTED,
    // /**
    // * Description:网络可连通
    // */
    // TC_NETWORK_BROADCAST_NET_CONNECTED,
    // /**
    // * Description:网络正在连接中
    // */
    // TC_NETWORK_BROADCAST_NET_CONNECTING,
    // /**
    // * Description:网络不可连通
    // */
    // TC_NETWORK_BROADCAST_NET_DISCONNECTED,
    // /**
    // * Description:网络物理连接已好
    // */
    // TC_NETWORK_BROADCAST_NET_PHYSICAL_CONNECTED,
    // /**
    // * Description:网络物理连接已断
    // */
    // TC_NETWORK_BROADCAST_NET_PHYSICAL_DISCONNECTED,
    // /**
    // * Description:有线网络连接成功
    // */
    // TC_NETWORK_BROADCAST_NET_ETH_CONNECT_SUCCEED,
    // /**
    // * Description:无线网络连接成功
    // */
    // TC_NETWORK_BROADCAST_NET_WIFI_CONNECT_SUCCEED,
    // /**
    // * Description:有线网络连接失败
    // */
    // TC_NETWORK_BROADCAST_NET_ETH_CONNECT_FAILED,
    // /**
    // * Description:无线网络连接失败
    // */
    // TC_NETWORK_BROADCAST_NET_WIFI_CONNECT_FAILED,
}
