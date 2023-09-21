/**
 * Copyright (C) 2012 The SkyTvOS Project
 *
 * Version     Date           Author
 * ─────────────────────────────────────
 *           2012-7-13         Wei li
 *
 */

package com.tianci.net.data;

/**
 * @ClassName ShowNetSetInfoData
 * @Description TODO (write something)
 * @author Wei li
 * @date 2012-7-13
 * @version TODO (write something)
 */
public class ShowNetSetInfoData
{
    public String ssid;
    public SkyIpInfo ipInfo;
    public String connectMode;
    public boolean bwifi;
    public boolean bdhcp;
    public boolean isWifiHot;   //判断当前是否启动了wifi热点功能

    @Override
    public String toString()
    {
        return this.getClass().getSimpleName() + ", ssid = " + ssid + ", bwifi = " + bwifi
                + ", bdhcp = " + bdhcp + ", skyIpInfo = " + ipInfo+ ", isWifiHot = " + isWifiHot;
    }
}
