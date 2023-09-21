/**
 * Copyright (C) 2012 The SkyTvOS Project
 *
 * Version     Date           Author
 * ─────────────────────────────────────
 *           2013-12-4         mikan
 *
 */

package com.tianci.net.netnode;

import com.tianci.net.define.NetworkDefs.NetworkDevices;
import com.tianci.net.interfaces.IEthNode;

/**
 * <p>
 * Description:有线网络功能实现 & 数据获取/设置
 * </p>
 * <p>
 * write something
 * </p>
 * 
 * @ClassName EthernetSetting
 * @author mikan
 * @date 2013-12-4
 * @version V1.0.0
 */
public abstract class TCEthNode extends TCNetNode implements IEthNode
{
    public TCEthNode()
    {
        super(NetworkDevices.ETHERNET);
    }
}
