/**
 * Copyright (C) 2012 The SkyTvOS Project
 *
 * Version     Date           Author
 * ─────────────────────────────────────
 *           2013-12-9         mikan
 *
 */

package com.tianci.net.data;

import com.skyworth.framework.skysdk.util.SkyObjectByteSerialzie;

import java.io.Serializable;

public class SkyWifiAPStaticItem implements Serializable
{
    /**
     * Description:
     */
    private static final long serialVersionUID = 4074460787455845754L;
    
    private SkyIpInfo ipInfo;
    private SkyWifiAPItem wifiAPItem;

    public SkyIpInfo getIpInfo()
    {
        return ipInfo;
    }

    public void setIpInfo(SkyIpInfo ipInfo)
    {
        this.ipInfo = ipInfo;
    }

    public SkyWifiAPItem getWifiAPItem()
    {
        return wifiAPItem;
    }

    public void setWifiAPItem(SkyWifiAPItem wifiAPItem)
    {
        this.wifiAPItem = wifiAPItem;
    }
    
    public static void main(String[] args)
    {
        SkyIpInfo ipInfo = new SkyIpInfo();
        ipInfo.ip = "12345";
        ipInfo.gateway = "1";
        ipInfo.mac = "2";
        ipInfo.dns0 = "3";
        
        SkyWifiAPItem wifiAPItem = new SkyWifiAPItem();
        wifiAPItem.bssid = "1";
        wifiAPItem.capabilities = "2";
        wifiAPItem.encrypt = 3;
        
        SkyWifiAPStaticItem wifiAPStaticItem = new SkyWifiAPStaticItem();
        wifiAPStaticItem.setIpInfo(ipInfo);
        wifiAPStaticItem.setWifiAPItem(wifiAPItem);
        
        byte[] temp = SkyObjectByteSerialzie.toBytes(wifiAPStaticItem);
        System.out.println(temp);
        
        SkyWifiAPStaticItem wifiAPStaticItem2 = SkyObjectByteSerialzie.toObject(temp, SkyWifiAPStaticItem.class);
        System.out.println(wifiAPStaticItem2.getIpInfo().toString());
        System.out.println(wifiAPStaticItem2.getWifiAPItem().toString());
    }
}
