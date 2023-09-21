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
import com.tianci.net.interfaces.IPppoeNode;

/**
 * <p>
 * Description:
 * </p>
 * <p>
 * write something
 * </p>
 * 
 * @ClassName TCPppoeNode
 * @author mikan
 * @date 2014-10-23
 * @version V1.0.0
 */
public abstract class TCPppoeNode extends TCNetNode implements IPppoeNode
{
    public TCPppoeNode()
    {
        super(NetworkDevices.PPPOE);
    }
}
