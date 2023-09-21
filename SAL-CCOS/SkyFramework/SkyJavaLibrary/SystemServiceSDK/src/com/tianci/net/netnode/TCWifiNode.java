/**
 * Copyright (C) 2012 The SkyTvOS Project
 *
 * Version     Date           Author
 * ─────────────────────────────────────
 *           2014-10-23          mikan
 *
 */

package com.tianci.net.netnode;

import com.tianci.net.define.NetworkDefs.NetworkDevices;
import com.tianci.net.interfaces.IHotspot;
import com.tianci.net.interfaces.IWifiNode;
import com.tianci.net.interfaces.IWps;

/**
 * <p>
 * Description:
 * </p>
 * <p>
 * write something
 * </p>
 * 
 * @ClassName TCWifiNode
 * @author mikan
 * @date 2014-10-23
 * @version V1.0.0
 */
public abstract class TCWifiNode extends TCNetNode implements IWifiNode, IWps, IHotspot
{
    public TCWifiNode()
    {
        super(NetworkDevices.WIFI);
    }
}
