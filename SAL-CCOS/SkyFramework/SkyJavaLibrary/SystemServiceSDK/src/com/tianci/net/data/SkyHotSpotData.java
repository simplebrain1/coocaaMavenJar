/**
 * Copyright (C) 2012 The SkyTvOS Project
 *
 * Version     Date           Author
 * ─────────────────────────────────────
 *           2014-1-4          mikan
 *
 */

package com.tianci.net.data;

import java.io.Serializable;

public class SkyHotSpotData implements Serializable
{
    /**
     * Description:
     */
    private static final long serialVersionUID = 5469856988565736033L;

    private String ssid = null;
    private String pwd = null;
    private boolean needPwdSetting = false; // 密码设置项, true->加密, false->公开

    public String getSsid()
    {
        return ssid;
    }

    public void setSsid(String ssid)
    {
        this.ssid = ssid;
    }

    public String getPwd()
    {
        return pwd;
    }

    public void setPwd(String pwd)
    {
        this.pwd = pwd;
    }

    public boolean isNeedPwdSetting()
    {
        return needPwdSetting;
    }

    public void setNeedPwdSetting(boolean needPwdSetting)
    {
        this.needPwdSetting = needPwdSetting;
    }
}
