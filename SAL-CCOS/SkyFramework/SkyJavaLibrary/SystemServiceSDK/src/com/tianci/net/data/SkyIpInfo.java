/**
 * Copyright (C) 2012 The SkyTvOS Project
 *
 * Version     Date           Author
 * ─────────────────────────────────────
 *           2012-10-16         Wei li
 *
 */

package com.tianci.net.data;

import com.skyworth.framework.skysdk.util.SkyDataComposer;
import com.skyworth.framework.skysdk.util.SkyDataDecomposer;

import java.io.Serializable;

/**
 * @ClassName SkyIpInfo
 * @Description TODO (write something)
 * @author Wei li
 * @date 2012-10-16
 * @version TODO (write something)
 */
public class SkyIpInfo implements Serializable,Comparable<SkyIpInfo>
{
    /**
     * Description:
     */
    private static final long serialVersionUID = 8472628754869296835L;
    public String ip = null;
    public String gateway = null;
    public String netmask = null;
    public String dns0 = null;
    public String dns1 = null;
    public String mac = null;
    
    public SkyIpInfo(){
    }
    
    public SkyIpInfo(String info)
    {
        SkyDataDecomposer decomposer = new SkyDataDecomposer(info);
        ip = decomposer.getStringValue("ip");
        gateway = decomposer.getStringValue("gateway");
        netmask = decomposer.getStringValue("netmask");
        dns0 = decomposer.getStringValue("dns0");
        dns1 = decomposer.getStringValue("dns1");
        mac = decomposer.getStringValue("mac");
    }

    public String toString()
    {
        SkyDataComposer composer = new SkyDataComposer();
        composer.addValue("ip", ip);
        composer.addValue("gateway", gateway);
        composer.addValue("netmask", netmask);
        composer.addValue("dns0", dns0);
        composer.addValue("dns1", dns1);
        composer.addValue("mac", mac);
        return composer.toString();
    }

	@Override
	public int compareTo(SkyIpInfo o) {
		if(this.ip.equals(o.ip)&&
		   this.gateway.equals(o.gateway)&&
		   this.dns0.equals(o.dns0)&&
		   this.netmask.equals(o.netmask)){
			return 1;
		}
		return 0;
	}
}
