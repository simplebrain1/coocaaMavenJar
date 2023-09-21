/**
 * Copyright (C) 2012 The SkyTvOS Project
 *
 * Version     Date           Author
 * ─────────────────────────────────────
 *           2014-10-23          mikan
 *
 */

package com.tianci.net.interfaces;

import com.tianci.net.data.SkyIpInfo;

/**
 * <p>
 * Description:eth节点特有功能定义
 * </p>
 * <p>
 * write something
 * </p>
 * 
 * @ClassName IEthNode
 * @author mikan
 * @date 2014-10-23
 * @version V1.0.0
 */
public interface IEthNode
{
    /**
     * 概述：动态连接以太网<br/>
     * 适用条件：<br/>
     * 执行流程：<br/>
     * 使用方法：<br/>
     * 注意事项：<br/>
     * 
     * @return boolean
     * @date 2014-4-10
     */
    public boolean connectNetworkByDhcp();
    
    /**
     * 概述：静态连接以太网<br/>
     * 		适用条件：<br/>
     * 		执行流程：<br/>
     * 		使用方法：<br/>
     * 		注意事项：<br/>
     * @return boolean
     * @date 2014-10-23
     */
    public boolean connectNetworkByStatic(SkyIpInfo ipInfo);

    /**
     * 概述：断开连接以太网<br/>
     * 适用条件：<br/>
     * 执行流程：<br/>
     * 使用方法：<br/>
     * 注意事项：<br/>
     * 
     * @return boolean
     * @date 2014-4-10
     */
    public boolean disconnect();

    /**
     * 概述：网线是否已插入<br/>
     * 适用条件：<br/>
     * 执行流程：<br/>
     * 使用方法：<br/>
     * 注意事项：本接口不应该存在延时<br/>
     * 
     * @return boolean
     * @date 2013-12-9
     */
    public boolean isCableConnected();
}
