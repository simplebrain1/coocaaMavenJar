/**
 * Copyright (C) 2012 The SkyTvOS Project
 *
 * Version     Date           Author
 * ─────────────────────────────────────
 *           2012-10-18         Wei li
 *
 */

package com.tianci.net.interfaces;

/**
 * <p>Description:</p> 
 * <p>write something</p>
 * @ClassName ISkyEthernetManager
 * @author mikan
 * @date 2013-12-10
 * @version V*.*.*
 */
public interface ISkyEthernet
{
    /**
     * 概述：是否插入网线<br/>
     * 		适用条件：<br/>
     * 		执行流程：<br/>
     * 		使用方法：<br/>
     * 		注意事项：<br/>
     * @return boolean
     * @date 2013-12-9
     */
    public boolean isCableConnected();
}
