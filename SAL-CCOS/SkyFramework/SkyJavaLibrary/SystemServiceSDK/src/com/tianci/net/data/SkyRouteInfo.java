/**
 * Copyright (C) 2012 The SkyTvOS Project
 *
 * Version     Date           Author
 * ─────────────────────────────────────
 *           2012-11-19         Wei li
 *
 */

package com.tianci.net.data;

import com.skyworth.framework.skysdk.util.SkyDataComposer;

import java.io.Serializable;

/**
 * @ClassName SkyRouteInfo
 * @Description TODO (write something)
 * @author Wei li
 * @date 2012-11-19
 * @version TODO (write something)
 */
public class SkyRouteInfo implements Serializable
{
    /**
     * Description:
     */
    private static final long serialVersionUID = -1582668294686817210L;
    public int id = 0;
    public String name = null;
    public String ipAssignment = null;
    public SkyIpInfo ipInfo = null;
    public String password = null;
    public String capabilities = null;

    public String toString()
    {
        SkyDataComposer composer = new SkyDataComposer();
        composer.addValue("id", id);
        composer.addValue("name", name);
        composer.addValue("ipAssignment", ipAssignment);
        composer.addValue("ipInfo", ipInfo.toString());
        composer.addValue("password", password);
        composer.addValue("capabilities", capabilities);
        return composer.toString();
    }

}
