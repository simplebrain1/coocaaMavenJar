/**
 * Copyright (C) 2012 The SkyTvOS Project
 *
 * Version     Date           Author
 * ─────────────────────────────────────
 *           2013-12-13         mikan
 *
 */

package com.tianci.net.interfaces;

import android.os.Bundle;

import com.tianci.net.define.NetworkDefs.EthEvent;
import com.tianci.net.define.NetworkDefs.HotspotState;
import com.tianci.net.define.NetworkDefs.WifiEvent;
import com.tianci.net.define.NetworkDefs.PPPoEEvent;
import com.tianci.net.define.NetworkDefs.MobileEvent;
public interface NetPluginListener
{
    void onNetStateChanged(String state, Bundle param);
    void onWifiApStateChanged(HotspotState hotspotState);
    void onEthEventHandler(EthEvent event, Bundle param);
    void onWifiEventHandler(WifiEvent event, Bundle param);
    void onPPPoEEventHandler(PPPoEEvent event, Bundle param);
    void onMobileEventHandler(MobileEvent event, Bundle param);
}
