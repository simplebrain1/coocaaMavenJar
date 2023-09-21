/**
 * Copyright (C) 2012 The SkyTvOS Project
 *
 * Version     Date           Author
 * ─────────────────────────────────────
 *           2013-12-4         mikan
 *
 */

package com.tianci.net.netnode;

import com.tianci.net.define.NetConst;
import com.tianci.net.define.NetworkDefs.DevConnectStatus;
import com.tianci.net.define.NetworkDefs.DevEnableState;
import com.tianci.net.define.NetworkDefs.NetworkDevices;
import com.tianci.net.define.NetworkDefs.SkyIpAssignment;
import com.tianci.system.utils.SysLog;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Description:网络节点功能基类</p> 
 * <p>write something</p>
 * @ClassName TCBaseNetNode
 * @author mikan
 * @date 2014-10-23
 * @version V1.0.0
 */
public abstract class TCNetNode
{
    protected final static String TAG = NetConst.TAG;
    
    protected final NetworkDevices nodeName;
    
    public TCNetNode(NetworkDevices nodeName)
    {
        this.nodeName = nodeName;
    }
    
    /**
     * 概述：获取IP地址<br/>
     * 适用条件：<br/>
     * 执行流程：<br/>
     * 使用方法：<br/>
     * 注意事项：<br/>
     * 
     * @return String
     * @date 2014-4-10
     */
    public abstract String getIpAddress();

    /**
     * 概述：获取子网掩码<br/>
     * 适用条件：<br/>
     * 执行流程：<br/>
     * 使用方法：<br/>
     * 注意事项：<br/>
     * 
     * @return String
     * @date 2014-4-10
     */
    public abstract String getNetMask();

    /**
     * 概述：获取网关<br/>
     * 适用条件：<br/>
     * 执行流程：<br/>
     * 使用方法：<br/>
     * 注意事项：<br/>
     * 
     * @return String
     * @date 2014-4-10
     */
    public abstract String getGateway();

    /**
     * 概述：获取主DNS<br/>
     * 适用条件：<br/>
     * 执行流程：<br/>
     * 使用方法：<br/>
     * 注意事项：<br/>
     * 
     * @return String
     * @date 2014-4-10
     */
    public abstract String getDns();

    /**
     * 概述：获取备用DNS<br/>
     * 适用条件：<br/>
     * 执行流程：<br/>
     * 使用方法：<br/>
     * 注意事项：<br/>
     * 
     * @return String
     * @date 2014-4-10
     */
    public abstract String getDns2();
    
    /**
     * 概述：获取MAC<br/>
     * 适用条件：<br/>
     * 执行流程：<br/>
     * 使用方法：<br/>
     * 注意事项：<br/>
     * 
     * @return String
     * @date 2014-4-10
     */
    public abstract String getMac();

    /**
     * 概述：获取连接配置模式(动态配置DHCP/静态配置STATIC)<br/>
     * 适用条件：<br/>
     * 执行流程：<br/>
     * 使用方法：<br/>
     * 注意事项：<br/>
     * 
     * @return SkyIpAssignment
     * @date 2013-12-5
     */
    public abstract SkyIpAssignment getConnectMode();

    /**
     * 概述：设置设备可用状态<br/>
     * 适用条件：<br/>
     * 执行流程：<br/>
     * 使用方法：<br/>
     * 注意事项：<br/>
     * 
     * @return boolean
     * @date 2013-12-5
     */
    public abstract boolean setEnabled(boolean data);
    
    /**
     * 概述：获取连网设备当前可用状态<br/>
     * 适用条件：<br/>
     * 执行流程：<br/>
     * 使用方法：<br/>
     * 注意事项：<br/>
     * 
     * @return SkyDevEnableState
     * @date 2014-4-10
     */
    public abstract DevEnableState getEnableState();
    
    /**
     * 概述：获取网络名称<br/>
     * 适用条件：<br/>
     * 执行流程：<br/>
     * 使用方法：<br/>
     * 注意事项：<br/>
     * 
     * @return String
     * @date 2014-4-10
     */
    public abstract String getApName();

    /**
     * 概述：设置网络名称<br/>
     * 适用条件：<br/>
     * 执行流程：<br/>
     * 使用方法：<br/>
     * 注意事项：<br/>
     * 
     * @param apName
     * @return boolean
     * @date 2014-4-10
     */
    public abstract boolean setApName(String apName);

    /**
     * 概述：网络连接状态<br/>
     * 适用条件：<br/>
     * 执行流程：<br/>
     * 使用方法：<br/>
     * 注意事项：<br/>
     * 
     * @return TCDevConnectStatus
     * @date 2013-12-13
     */
    public abstract DevConnectStatus getConnectState();

    /**
     * 概述：获取当前设备是否被配置过<br/>
     * 适用条件：<br/>
     * 执行流程：<br/>
     * 使用方法：<br/>
     * 注意事项：<br/>
     * 
     * @return boolean
     * @date 2014-4-10
     */
    public abstract boolean isConfigured();

    /**
     * 概述：清除当前设备的配置记录<br/>
     * 适用条件：<br/>
     * 执行流程：<br/>
     * 使用方法：<br/>
     * 注意事项：<br/>
     * 
     * @return boolean
     * @date 2014-4-10
     */
    public abstract boolean clearSavedConfig();

    /**
     * 概述：IPV6 releated<br/>
     * 适用条件：<br/>
     * 执行流程：<br/>
     * 使用方法：Need to be Override in ipv6 system.  <br/>
     *           Not need to be Override in ipv4 only system<br/>
     * 注意事项：<br/>
     * @return boolean
     * @date 2020-6-23
     */

    public boolean checkIpv6Status(){
        return false;
    }

    public String getIpV6Address(){
        String ipv6Address = "";
        if(nodeName == null){
            return ipv6Address;
        }
        switch(nodeName){
            case WIFI:
                ipv6Address = IPV6Util.getInstance().getIpV6Address("wlan0");
                break;
            case ETHERNET:
            case PPPOE:
                ipv6Address = IPV6Util.getInstance().getIpV6Address("eth0");
                break;
                default:
                    break;
        }
        SysLog.info("IPV6", "getIpV6Address:" + ipv6Address);
        return ipv6Address;
    }

    public int getIpV6Prefixlen(){
        int ipV6Prefixlen = -1;
        return ipV6Prefixlen;
    }

    public String getIpV6Gateway(){
        if(nodeName == null){
            return "";
        }
        switch(nodeName){
            case WIFI:
                return IPV6Util.getInstance().getIpV6Gateway("wlan0");
            case ETHERNET:
            case PPPOE:
                return IPV6Util.getInstance().getIpV6Gateway("eth0");
            default:
                break;
        }
        return "";
    }

    public String getIpv6Dns(){
        if(nodeName == null){
            return "";
        }
        List<String> ipv6DnsList = new ArrayList<String>();
        switch(nodeName){
            case WIFI:
                ipv6DnsList = IPV6Util.getInstance().getIpv6Dns("wlan0");
                break;
            case ETHERNET:
            case PPPOE:
                ipv6DnsList = IPV6Util.getInstance().getIpv6Dns("eth0");
                break;
            default:
                break;
        }
        if(ipv6DnsList!=null && ipv6DnsList.size()>=1)
        {
            return ipv6DnsList.get(0);
        }
        return "";
    }

    public String getIpv6Dns2(){
        if(nodeName == null){
            return "";
        }
        List<String> ipv6DnsList = new ArrayList<String>();
        switch(nodeName){
            case WIFI:
                ipv6DnsList = IPV6Util.getInstance().getIpv6Dns("wlan0");
                break;
            case ETHERNET:
            case PPPOE:
                ipv6DnsList = IPV6Util.getInstance().getIpv6Dns("eth0");
                break;
            default:
                break;
        }
        if(ipv6DnsList!=null && ipv6DnsList.size()>=2)
        {
            return ipv6DnsList.get(1);
        }
        return "";
    }
}
