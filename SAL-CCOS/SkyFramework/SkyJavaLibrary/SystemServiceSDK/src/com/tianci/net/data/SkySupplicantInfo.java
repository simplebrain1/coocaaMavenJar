package com.tianci.net.data;

import com.tianci.net.define.NetworkDefs;

import java.io.Serializable;

/**
 * <p>
 * Description:
 * </p>
 * <p>
 * write something
 * </p>
 *
 * @author mikan
 * @version V1.0.0
 * @ClassName SkySupplicantInfo
 * @date 15/3/24
 */
public class SkySupplicantInfo implements Serializable
{
    private static final long serialVersionUID = -7062066596636110648L;
    
    public NetworkDefs.SkySupplicantState state;

    public String ssid = null;

    public SkySupplicantInfo()
    {

    }

    public SkySupplicantInfo(NetworkDefs.SkySupplicantState state, String ssid)
    {
        this.state = state;
        this.ssid = ssid;
    }
}
