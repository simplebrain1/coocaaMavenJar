/**
 * Copyright (C) 2012 The SkyTvOS Project
 *
 * Version     Date           Author
 * ─────────────────────────────────────
 *           2013-12-13         mikan
 *
 */

package com.tianci.net.define;

public class NetworkDefs
{
    // 网络状态变化定义key
    public static final String KEY_NET_STATE_FROM = "from";
    public static final String KEY_NET_STATE_TARGET = "target";
    public static final String KEY_NET_CURRENT_CONNECT_DEVICE = "device";
    public static final String KEY_NET_STATE_INTENT = "netState";

    /**
     * <p>
     * Description:网络状态
     * </p>
     * <p>
     * write something
     * </p>
     * 
     * @ClassName NetworkState
     * @author mikan
     * @date 2013-12-13
     * @version V1.0.0
     */
    public enum NetworkState
    {
        UNKNOW_STATE, // 未知状态
        DISCONNECT, // 未连接
        DISCONNECTING, // 断开中
        CONNECTING, // 连接中
        CONNECT_LAN, // 只连通局域网
        CONNECT_WAN, // 外网连通
    }

    /**
     * <p>
     * Description:设备连接状态
     * </p>
     * <p>
     * write something
     * </p>
     * 
     * @ClassName DevConnectStatus
     * @author mikan
     * @date 2013-12-13
     * @version V1.0.0
     */
    public enum DevConnectStatus
    {
        CONNECTING, CONNECTED, DISCONNECTING, DISCONNECTED, UNKNOWN
    }

    /**
     * <p>
     * Description:连接网络设备
     * </p>
     * <p>
     * write something
     * </p>
     * 
     * @ClassName NetworkConnType
     * @author mikan
     * @date 2013-12-14
     * @version V1.0.0
     */
    public enum NetworkDevices
    {
        // new added type MOBILE is placed in the tail
        ETHERNET, WIFI, PPPOE, UNKNOW, MOBILE
    }

    /**
     * <p>
     * Description:IP配置方式
     * </p>
     * <p>
     * write something
     * </p>
     * 
     * @ClassName SkyIpAssignment
     * @author mikan
     * @date 2013-12-17
     * @version V1.0.0
     */
    public enum SkyIpAssignment
    {
        STATIC, DHCP, STATIC_1000M, STATIC_100M, DHCP_1000M, DHCP_100M, PPPOE, UNASSIGNMENT,STATIC_NOT_HIDDEN,DHCP_NOT_HIDDEN
    }

    /**
     * <p>
     * Description:热点状态
     * </p>
     * <p>
     * write something
     * </p>
     * 
     * @ClassName HotspotState
     * @author mikan
     * @date 2014-1-14
     * @version V1.0.0
     */
    public enum HotspotState
    {
        AP_STATE_DISABLING(0),
        AP_STATE_DISABLED(1),
        AP_STATE_ENABLING(2),
        AP_STATE_ENABLED(3),
        AP_STATE_FAILED(4);

        public int type;

        HotspotState(int type) {
            this.type = type;
        }

        public static HotspotState typeOf(int type) {
            switch (type) {
                case 0:
                    return AP_STATE_DISABLING;
                case 1:
                    return AP_STATE_DISABLED;
                case 2:
                    return AP_STATE_ENABLING;
                case 3:
                    return AP_STATE_ENABLED;
                case 4:
                    return AP_STATE_FAILED;
            }
            return AP_STATE_DISABLING;
        }
    }

    /**
     * <p>
     * Description:以太网事件
     * </p>
     * <p>
     * write something
     * </p>
     * 
     * @ClassName EthEvent
     * @author mikan
     * @date 2014-1-14
     * @version V*.*.*
     */
    public enum EthEvent
    {
        /**
         * Description: 网络已连接成功
         */
        EVENT_ETH_CONNECT_SUCCEEDED,
        /**
         * Description: 网络连接失败
         */
        EVENT_ETH_CONNECT_FAILD,
        /**
         * Description: 网线已插上
         */
        EVENT_ETH_CABLE_CONNECTED,
        /**
         * Description: 网线已断开
         */
        EVENT_ETH_CABLE_DISCONNECTED,
        /**
         * Description: 未知
         */
        EVENT_ETH_UNKNOW,
//        /**
//         * Description: IP配置成功
//         */
//        EVENT_ETH_IP_CONFIGURATION_SUCCEEDED,
//        /**
//         * Description: IP配置失败
//         */
//        EVENT_ETH_IP_CONFIGURATION_FAILED,
        /**
         * Description: 连接初始化失败
         */
        EVENT_ETH_CONNECT_INIT_FAIL,
        /**
         * Description: 由于网线未连接导致连接失败
         */
        EVENT_ETH_CONNECT_FAILD_BY_CABLE_NOT_CONNECT,
        /**
         * Description: 连接以太网超时
         */
        EVENT_ETH_CONNECT_FAILD_BY_TIMEOUT,
    }

    /**
     * <p>
     * Description:无线网络事件
     * </p>
     * <p>
     * write something
     * </p>
     * 
     * @ClassName WifiEvent
     * @author mikan
     * @date 2014-2-11
     * @version V1.0.0
     */
    public enum WifiEvent
    {
        /**
         * Description: 网络已连接成功
         */
        EVENT_WIFI_CONNECT_SUCCEEDED,
        /**
         * Description: 网络连接中
         */
        EVENT_WIFI_CONNECT_CONNECTING,
        /**
         * Description: 无线已断开
         */
        EVENT_WIFI_CONNECT_DISCONNECTED,
        /**
         * Description: 无线断开中
         */
        EVENT_WIFI_CONNECT_DISCONNECTING,
        /**
         * Description: 无线连接失败
         */
        EVENT_WIFI_CONNECT_FAILD,
        /**
         * Description: 未知状态
         */
        EVENT_WIFI_CONNECT_UNKNOW,
        /**
         * @Description : WIFI强度变化
         */
        EVENT_WIFI_RSSI_CHANGED,
        /**
         * @Description : WIFI请求搜索出现失败
         */
        EVENT_WIFI_SCAN_FAIL,
    }

    public enum PPPoEEvent {
        EVENT_PPPOE_CONNECT_SUCCESSED,

        EVENT_PPPOE_CONNECT_FAILED,

        EVENT_PPPOE_CONNECT_FAILED_WRONG_PASSWORD,

        EVENT_PPPOE_DISCONNECT_SUCCESSED,

        EVENT_PPPOE_DISCONNECT_FAILED,

        EVENT_PPPOE_CONNECTING,

        EVENT_PPPOE_AUTORECONNECTING,

        EVENT_PPPOE_UNKNOW,

        EVENT_PPPOE_CONNECT_INIT_FAIL,

        EVENT_PPPOE_CONNECT_FAILD_BY_TIMEOUT,
    }

    public enum MobileEvent
    {
        /**
         * Description: 网络已连接成功
         */
        EVENT_MOBILE_CONNECT_SUCCEEDED,
        /**
         * Description: 网络连接中
         */
        EVENT_MOBILE_CONNECT_CONNECTING,
        /**
         * Description: MOBILE 已断开
         */
        EVENT_MOBILE_CONNECT_DISCONNECTED,
        /**
         * Description: MOBILE 断开中
         */
        EVENT_MOBILE_CONNECT_DISCONNECTING,

    }

    /**
     * <p>
     * Description:设备可用状态
     * </p>
     * <p>
     * write something
     * </p>
     * 
     * @ClassName TCDevEnableState
     * @author mikan
     * @date 2014-4-10
     * @version V1.0.0
     */
    public enum DevEnableState
    {
        DISABLING, DISABLED, ENABLING, ENABLED, UNKNOWN,
    }

    public enum SkySupplicantState
    {
        /**
         * @Description : 当前ap不可联
         */
        DISCONNECTED,

        /**
         * @Description : 接口不可用
         */
        INTERFACE_DISABLED,

        /**
         * @Description : 非活动状态
         */
        INACTIVE,

        /**
         * @Description : 搜索ap中
         */
        SCANNING,

        /**
         * @Description : 验证中
         */
        AUTHENTICATING,

        /**
         * @Description : 正在关联
         */
        ASSOCIATING,

        /**
         * @Description : 关联完成
         */
        ASSOCIATED,

        /**
         * @Description : WPA 4次握手
         */
        FOUR_WAY_HANDSHAKE,

        /**
         * @Description : WPA group握手完成
         */
        GROUP_HANDSHAKE,

        /**
         * @Description : 所有认证完成
         */
        COMPLETED,

        /**
         * @Description : 暂停
         */
        DORMANT,

        /**
         * @Description : 未初始化
         */
        UNINITIALIZED,

        /**
         * @Description : 无效
         */
        INVALID,

        /**
         * @Description : 密码错误
         */
        PWD_ERROR,
    }

    public enum WifiCapabilities
    {
        WPA_PSK,
        WEP,
        NONE,
        WPA_EAP,
        SAE,
    }
}
