package com.tianci.net.data;

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
 * @ClassName SkyRouteConfig
 * @date 15/5/19
 */
public class SkyRouteConfig implements Serializable
{
    private static final long serialVersionUID = -7505774235390029944L;

    public String routeName = null;
    public String ip_assignment = null;
    public String ip = null;
    public String netmask = null;
    public String gateway = null;
    public String dns = null;
    public String capabilities = null;

    public SkyRouteConfig()
    {

    }


}
