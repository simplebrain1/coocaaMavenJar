/**
 * Copyright (C) 2012 The SkyTvOS Project
 *
 * Version     Date           Author
 * ─────────────────────────────────────
 *           2012-6-18         Xia
 *
 */

package com.tianci.net.data;

import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.SystemProperties;
import android.text.TextUtils;
import com.skyworth.framework.skysdk.util.SkyDataComposer;
import com.skyworth.framework.skysdk.util.SkyDataDecomposer;
import com.tianci.net.define.NetworkDefs;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author Xia
 * @ClassName SkyWifiAPItem
 * @Description Wifi AP info
 * @date 2012-6-18
 */
public class SkyWifiAPItem implements Serializable, Comparable
{
    /**
     * Description:
     */
    private static final long serialVersionUID = 3039555505430836213L;
    public String ssid;
    public String bssid;
    public String capabilities;
    public int level;
    public int frequency;
    public String ssid_short;
    public int encrypt;
    public int signal_level;
    public String pwd;
    public boolean isConfig;
    public boolean isWifi6 = false;

    public SkyWifiAPItem()
    {
        level = 0;
        frequency = 0;
        encrypt = 0;
        signal_level = 0;
        isConfig = false;
    }

    public SkyWifiAPItem(WifiConfiguration config)
    {
        if (config == null)
        {
            return;
        }

        ssid = config.SSID;
        bssid = config.BSSID;
        capabilities = getCapabilitiesByCfg(config);
        initEncrypt();
        level = 0;
        signal_level = getSignal_level();
        isConfig = true;
    }

    public SkyWifiAPItem(String ssid, String bssid, String capabilities)
    {
        this.ssid = ssid;
        this.bssid = bssid;
//        this.capabilities = capabilities;
        this.capabilities = coverCapabilities(capabilities);
        initEncrypt();
        this.level = 0;
        signal_level = getSignal_level();
        isConfig = true;
    }

    public SkyWifiAPItem(String ssid, String bssid, String capabilities, int level, int frequency)
    {
        this.ssid = ssid;
        this.bssid = bssid;
//        this.capabilities = capabilities;
        this.capabilities = coverCapabilities(capabilities);
        initEncrypt();
        this.level = level;
        signal_level = getSignal_level();
        this.frequency = frequency;
        isConfig = false;
    }

    public SkyWifiAPItem(ScanResult result)
    {
        if (result == null)
        {
            return;
        }

        ssid = result.SSID;
        bssid = result.BSSID;
//        capabilities = result.capabilities;
        this.capabilities = coverCapabilities(result.capabilities);
        initEncrypt();
        level = result.level;
        signal_level = getSignal_level();
        frequency = result.frequency;
        isConfig = false;
        checkWifiStandard(result);
    }

    // FIXME bug, ssid带有空格的时候会导致问题, 不建议使用
    public SkyWifiAPItem(String apstr)
    {
        SkyDataDecomposer decomposer = new SkyDataDecomposer(apstr);
        ssid = decomposer.getStringValue("ssid");
        bssid = decomposer.getStringValue("bssid");
        capabilities = decomposer.getStringValue("capabilities");
        ssid_short = decomposer.getStringValue("ssid_short");
        signal_level = decomposer.getIntValue("signal_level");
        level = decomposer.getIntValue("level");
        frequency = decomposer.getIntValue("frequency");
        encrypt = decomposer.getIntValue("encrypt");
        pwd = decomposer.getStringValue("pwd");
    }

    public void checkWifiStandard(ScanResult result) {
        int wifiStandard = -1;
        Class cls = result.getClass();
        Method[] methods = cls.getMethods();
        try {
            for (Method method : methods) {
                if ("getWifiStandard".equals(method.getName())) {
                    wifiStandard =  (Integer) method.invoke(result);
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        if(wifiStandard!=-1){
            //WIFI_STANDARD_11AX (@see android11 ScanResult)
            isWifi6 = (wifiStandard == 6  && SystemProperties.getBoolean("persist.vendor.wifi6", false));
        } else if (!TextUtils.isEmpty(result.capabilities)){
            if (result.capabilities.contains("802.11-ax")) {
                isWifi6 = true;
            }
        }
    }

    // FIXME bug, 由于fan序列化存在问题, 不建议使用
    public String toString()
    {
        SkyDataComposer composer = new SkyDataComposer();
        composer.addValue("ssid", ssid);
        composer.addValue("bssid", bssid);
        composer.addValue("capabilities", capabilities);
        composer.addValue("ssid_short", ssid_short);
        composer.addValue("signal_level", signal_level);
        composer.addValue("level", level);
        composer.addValue("frequency", frequency);
        composer.addValue("encrypt", encrypt);
        composer.addValue("pwd", pwd);
        return composer.toString();
    }

    public static void main(String[] args)
    {
        SkyDataComposer composer = new SkyDataComposer();
        composer.addValue("ssid", "ssid");
        composer.addValue("bssid", "bssid");
        composer.addValue("int", -2);
        System.out.println(composer.toString());
        SkyDataDecomposer datacom = new SkyDataDecomposer(composer.toString());
        SkyWifiAPItem item = new SkyWifiAPItem();
        item.bssid = "bssid";
        item.level = -3;
        SkyWifiAPItem item1 = new SkyWifiAPItem(item.toString());
        System.out.println(item1.toString());
    }

    @Override
    public int compareTo(Object another)
    {
        SkyWifiAPItem other = (SkyWifiAPItem) another;

        if (isConfig && !other.isConfig)
        {
            return -1;
        }
        if (!isConfig && other.isConfig)
        {
            return 1;
        }

        // Reachable one goes before unreachable one.
        if (level != Integer.MAX_VALUE && other.level == Integer.MAX_VALUE)
        {
            return -1;
        }
        if (level == Integer.MAX_VALUE && other.level != Integer.MAX_VALUE)
        {
            return 1;
        }

        int encryptDiff = other.encrypt - encrypt;
        if (encryptDiff != 0)
        {
            return encryptDiff;
        }

        int diff = other.signal_level - signal_level;
        if (diff != 0)
        {
            //            SysLog.info(ssid + " - " + other.ssid + " = " + diff);
            return diff;
        }

        return ssid.compareToIgnoreCase(other.ssid);
    }

    //    @Override
    //    public boolean equals(Object o)
    //    {
    //        if (o instanceof ScanResult)
    //        {
    //            SysLog.info("o instanceof ScanResult");
    //            ScanResult result = (ScanResult) o;
    //            return ssid != null && ssid.equals(result.SSID);
    //        }
    //        SysLog.info("nonononononononononono");
    //        return super.equals(o);
    //    }

    public void updte(ScanResult result)
    {
//        if (result.level > level)
//        {
            level = result.level;
            signal_level = getSignal_level();
//        }

//        capabilities = result.capabilities;
        capabilities = coverCapabilities(result.capabilities);
        frequency = result.frequency;
        initEncrypt();
    }

    public void updte(String ssid, String bssid, String capabilities, int level, int frequency)
    {
        if (this.level > level)
        {
            this.level = level;
            signal_level = getSignal_level();
        }

//        this.capabilities = capabilities;
        capabilities = coverCapabilities(capabilities);
        this.frequency = frequency;
        initEncrypt();
    }

    private int getSignal_level()
    {
        return WifiManager.calculateSignalLevel(level, 4);
    }

    private void initEncrypt()
    {
        if (capabilities == null)
        {
            encrypt = 0;
        }
        else if (capabilities.contains("WPA") || capabilities.contains("WEP") || capabilities.contains("SAE") )
        {
            encrypt = 1;
        }
        else
        {
            encrypt = 0;
        }
    }

    private String coverCapabilities(String capabilities)
    {
        String ret = NetworkDefs.WifiCapabilities.NONE.toString();
        if (capabilities == null)
        {
            ret = NetworkDefs.WifiCapabilities.NONE.toString();
        }
        else if (capabilities.contains("WEP"))
        {
            ret = NetworkDefs.WifiCapabilities.WEP.toString();
        }
        else if (capabilities.contains("PSK"))
        {
            ret = NetworkDefs.WifiCapabilities.WPA_PSK.toString();
        }
        else if (capabilities.contains("EAP"))
        {
            ret = NetworkDefs.WifiCapabilities.WPA_EAP.toString();
        }
        else if (capabilities.contains("SAE"))
        {
            ret = NetworkDefs.WifiCapabilities.SAE.toString();
        }

        if (capabilities != null && capabilities.contains("WPS-PBC"))
        {
            ret = ret + " WPS-PBC";
        }

        return ret;
    }

    private String getCapabilitiesByCfg(WifiConfiguration config)
    {
        if (config.allowedKeyManagement.get(WifiConfiguration.KeyMgmt.WPA_PSK))
        {
            return NetworkDefs.WifiCapabilities.WPA_PSK.toString();
        }
        if (config.allowedKeyManagement.get(WifiConfiguration.KeyMgmt.WPA_EAP)
                || config.allowedKeyManagement.get(WifiConfiguration.KeyMgmt.IEEE8021X))
        {
            return NetworkDefs.WifiCapabilities.WPA_EAP.toString();
        }
        //SAE加密方式
        if (config.allowedKeyManagement.get(8))
        {
            return NetworkDefs.WifiCapabilities.SAE.toString();
        }
        return (config.wepKeys[0] != null) ?
                NetworkDefs.WifiCapabilities.WEP.toString() :
                NetworkDefs.WifiCapabilities.NONE.toString();
    }
}
