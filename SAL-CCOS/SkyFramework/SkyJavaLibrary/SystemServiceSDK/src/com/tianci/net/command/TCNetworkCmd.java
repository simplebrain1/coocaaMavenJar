/**
 * Copyright (C) 2012 The SkyTvOS Project
 *
 * Version     Date           Author
 * ─────────────────────────────────────
 *           2013-12-4         mikan
 *
 */

package com.tianci.net.command;

/**
 * <p>
 * Description:SystemService中提供出去的网络命令
 * </p>
 * <p>
 * write something
 * </p>
 * 
 * @ClassName TCNetworkCmd
 * @author mikan
 * @date 2013-12-4
 * @version V1.0.0
 */
public enum TCNetworkCmd
{
    /********** 通用命令 ***********/
    /**
     * Description:获取IP信息
     */
    TC_NETWORK_CMD_GET_IP_INFO,
    /**
     * Description:获取连接类型(动态/静态配置)
     */
    TC_NETWORK_CMD_CONNECT_MODE,
    /**
     * Description:是否已经连网
     */
    TC_NETWORK_CMD_GET_IS_CONNECTD,
    /**
     * Description:获取当前网络状态
     */
    TC_NETWORK_CMD_GET_CONNECT_STATE,
    /**
     * Description:根据路由名称获取保存的路由信息
     */
    TC_NETWORK_CMD_GET_ROUTE_INFO_BY_NAME,
    /**
     * Description:获取保存的路由配置信息
     */
    TC_NETWORK_CMD_GET_ROUTE_CONFIG,
    /**
     * Description:保存路由配置信息
     */
    TC_NETWORK_CMD_SET_ROUTE_CONFIG,
    /**
     * Description:获取网络兼容性
     */
    TC_NETWORK_CMD_GET_COMPATIBLE,
    
    
    /********** 有线网络命令 ***********/
    /**
     * Description:获取网线是否插上
     */
    TC_NETWORK_CMD_ETHERNET_IS_CABLE_CONNECT,
    /**
     * Description:获取有线模块是否可用
     */
    TC_NETWORK_CMD_ETHERNET_GET_ENABLE,
    /**
     * Description:设置有线模块是否可用
     */
    TC_NETWORK_CMD_ETHERNET_SET_ENABLE,
    /**
     * Description:静态连接以太网
     */
    TC_NETWORK_CMD_ETHERNET_CONNECT_BY_STATIC,
    /**
     * Description:动态连接以太网
     */
    TC_NETWORK_CMD_ETHERNET_CONNECT_BY_DHCP,
    /**
     * Description:以太网连接状态
     */
    TC_NETWORK_CMD_ETHERNET_GET_CONNECT_STATE,

    
    /********** wifi命令 ***********/
    /**
     * Description:获取当前已连接的无线网络的SSID
     */
    TC_NETWORK_CMD_WIFI_GET_CONNECT_SSID,
    /**
     * Description:启动搜索周围无线网络功能
     */
    TC_NETWORK_CMD_WIFI_START_SCAN_WIFI_INFO_LIST,
    /**
     * Description:获取当前系统已存在的无线列表
     */
    TC_NETWORK_CMD_WIFI_GET_CURRENT_WIFI_INFO_LIST,
    /**
     * Description:无线网络连接状态
     */
    TC_NETWORK_CMD_WIFI_GET_CONNECT_STATE,
    /**
     * Description:获取无线模块是否可用
     */
    TC_NETWORK_CMD_WIFI_GET_ENABLE,
    /**
     * Description:设置无线模块是否可用
     */
    TC_NETWORK_CMD_WIFI_SET_ENABLE,
    /**
     * Description:静态配置连接无线网络
     */
    TC_NETWORK_CMD_WIFI_CONNECT_BY_STATIC,
    /**
     * Description:动态配置连接无线网络
     */
    TC_NETWORK_CMD_WIFI_CONNECT_BY_DHCP,
    /**
     * Description:静态配置连接无线网络,非HiddenSSID
     */
    TC_NETWORK_CMD_WIFI_CONNECT_BY_STATIC_NOT_HIDDENSSID,
    /**
     * Description:动态配置连接无线网络,非HiddenSSID
     */
    TC_NETWORK_CMD_WIFI_CONNECT_BY_DHCP_NOT_HIDDENSSID,
    /**
     * Description:获取本地缓存的无线AP列表信息
     */
    TC_NETWORK_CMD_WIFI_GET_LOCAL_AP_LIST,
    /**
     * Description:获取上一次对应SSID的ap保存的密码
     */
    TC_NETWORK_CMD_WIFI_GET_LAST_PWD_BY_SSID,
    
    
    /**
     * Description:是否支持无线一键通
     */
    TC_NETWORK_CMD_WIFI_IS_SUPPORT_WPS,
    /**
     * Description:启动无线一键通
     */
    TC_NETWORK_CMD_WIFI_START_WPS,
    /**
     * Description:关闭无线一键通
     */
    TC_NETWORK_CMD_WIFI_CLOSE_WPS,
    /**
     * Description:是否支持AP
     */
    TC_NETWORK_CMD_WIFI_IS_SUPPORT_HOTSPOT,
    /**
     * Description:启动AP
     */
    TC_NETWORK_CMD_WIFI_START_HOTSPOT,
    /**
     * Description:关闭Ap
     */
    TC_NETWORK_CMD_WIFI_CLOSE_HOTSPOT,
    /**
     * Description:获取Ap状态
     */
    TC_NETWORK_CMD_WIFI_GET_HOTSPOT_STATE,
    /**
     * Description:获取当前连接wifi的信号强度
     */
    TC_NETWORK_CMD_WIFI_GET_RSSI,
    /**
     * Description:忽略某个指定的ap
     */
    TC_NETWORK_CMD_WIFI_FORGET_AP,
    /**
     * Description:获取当前连接wifi的相关信息
     */
    TC_NETWORK_CMD_WIFI_GET_CURRENT_CONNECTED_AP_INFO,
    /**
     * Description:获取某个ap是否配置过
     */
    TC_NETWORK_CMD_WIFI_GET_AP_IS_CONFIG,
    /**
     * Description:保存某个ap的配置
     */
    TC_NETWORK_CMD_WIFI_SAVE_AP_CONFIG,
    /**
     * Description:清空所有ap配置
     */
    TC_NETWORK_CMD_WIFI_CLEAN_ALL_CONFIG,

    
    /********** 备用命令 ***********/
    /**
     * Description:获取网络类型
     */
    TC_NETWORK_CMD_GET_NET_TYPE,
    /**
     * Description:获取网络运营商信息
     */
    TC_NETWORK_CMD_GET_PROVIDERINFO,

    /********** A43相关网络命令 ***********/
    /**
     * Description:以太网已连接消息
     */
    TC_NETWORK_CMD_ETHERNET_EVENT_CONNECTED,
    /**
     * Description:以太网已断开消息
     */
    TC_NETWORK_CMD_ETHERNET_EVENT_DISCONNECTED,
    
//    /**
//     * Description:检测网速
//     */
//    TC_NETWORK_CMD_DETECT_NET_SPEED,
//    /**
//     * Description:网络认证
//     */
//    TC_NETWORK_CMD_NET_CERTIFICATION,
//    /**
//     * Description:设置网络流量统计的开关
//     */
//    TC_NETWORK_CMD_SET_TRAFFIC_STATS,
//    /**
//     * Description:获取热点的名称和密码
//     */
//    TC_NETWORK_CMD_GET_WIFIHOT_NAME_PASSWORD, 

	/********* 工厂相关命令 ***************/
    /**
     * Description:一键连接工厂wifi
     */
    TC_NETWORK_CMD_ONE_KEY_CONNECT_FACTORY_WIFI,
    /**
     * Description:一键连接工厂以太网
     */
    TC_NETWORK_CMD_ONE_KEY_CONNECT_FACTORY_ETH,

    /**
     * Description:获取IP信息
     */
    TC_NETWORK_CMD_GET_IP_INFO_WITH_NET_TYPE_PARAM,
    /**
     * Description:获取 PPPoE 模块是否可用
     */
    TC_NETWORK_CMD_PPPOE_GET_ENABLE,
    /**
     * Description:设置 PPPoE 模块是否可用
     */
    TC_NETWORK_CMD_PPPOE_SET_ENABLE,
    /**
     * Description: PPPOE 连接
     */
    TC_NETWORK_CMD_PPPOE_CONNECT,
    /**
     * Description:PPPoE 连接状态
     */
    TC_NETWORK_CMD_PPPOE_GET_CONNECT_STATE,
    /**
     * Description: PPPoE 获取账号
     */
    TC_NETWORK_CMD_PPPOE_GET_USER_NAME,
    /**
     * Description: PPPoE 获取密码
     */
    TC_NETWORK_CMD_PPPOE_GET_PASSWORD,
    /**
     * Description: PPPoE 设置账号
     */
    TC_NETWORK_CMD_PPPOE_SET_USER_NAME,
    /**
     * Description: PPPoE 设置密码
     */
    TC_NETWORK_CMD_PPPOE_SET_PASSWORD,
    /**
     * Description: PPPOE 取消连接
     */
    TC_NETWORK_CMD_PPPOE_DISCONNECT,

    /**
     * Description: 重置网络
     */
    TC_NETWORK_CMD_RESET_NET,
}
