/**
 * Copyright (C) 2012 The SkyTvOS Project
 *
 * Version     Date           Author
 * ─────────────────────────────────────
 *           2013-2-26         fanyanbo
 *
 */

package com.tianci.net.data;

import com.skyworth.framework.skysdk.util.SkyDataComposer;
import com.skyworth.framework.skysdk.util.SkyDataDecomposer;

import java.io.Serializable;

/**
 * @ClassName SkyProviderInfo
 * @Description TODO (write something)
 * @author fanyanbo
 * @date 2013-2-26
 * @version TODO (write something)
 */
public class SkyProviderInfo implements Serializable
{
    /**
     * Description:
     */
    private static final long serialVersionUID = 3924867074621915120L;
    
    public String country = null;
    public String area = null;
    public String province = null;
    public String city = null;
    public String isp = null;
    
    public SkyProviderInfo(){
        
    }
    
    public SkyProviderInfo(String info)
    {
        SkyDataDecomposer decomposer = new SkyDataDecomposer(info);
        country = decomposer.getStringValue("country");
        area = decomposer.getStringValue("area");
        province = decomposer.getStringValue("province");
        city = decomposer.getStringValue("city");
        isp = decomposer.getStringValue("isp");
    }

    public String toString()
    {
        SkyDataComposer composer = new SkyDataComposer();
        composer.addValue("country", country);
        composer.addValue("area", area);
        composer.addValue("province", province);
        composer.addValue("city", city);
        composer.addValue("isp", isp);
        return composer.toString();
    }
}
