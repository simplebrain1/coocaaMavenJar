/**
 * Copyright (C) 2012 The SkyTvOS Project
 *
 * Version     Date           Author
 * ─────────────────────────────────────
 *           2014-10-23          mikan
 *
 */

package com.tianci.net.interfaces;

public interface IPppoeNode
{
    /**
     * 概述：开始连接PPPoE<br/>
     * 适用条件：<br/>
     * 执行流程：<br/>
     * 使用方法：<br/>
     * 注意事项：<br/>
     *
     * @param username
     *            账号
     * @param password
     *            密码
     * @param netInterface
     *            网络接口
     * @return boolean
     * @date 2014-10-20
     */
    public  boolean connectPppoe(String username, String password, String netInterface);

    /**
     * 概述：断开连接<br/>
     * 适用条件：<br/>
     * 执行流程：<br/>
     * 使用方法：<br/>
     * 注意事项：<br/>
     *
     * @return boolean
     * @date 2014-10-20
     */
    public boolean disConnect();
    /**
     * 概述：获取PPPoE 账号<br/>
     * 适用条件：<br/>
     * 执行流程：<br/>
     * 使用方法：<br/>
     * 注意事项：<br/>
     * @return String
     * @date 2019-8-5
     */
    public String getPPPoEName();

    /**
     * 概述：获取PPPoE密码<br/>
     * 适用条件：<br/>
     * 执行流程：<br/>
     * 使用方法：<br/>
     * 注意事项：<br/>
     * @return String
     * @date 2019-8-5
     */
    public String getPPPoEPassword();

    /**
     * 概述：设置PPPoE 账号<br/>
     * 适用条件：<br/>
     * 执行流程：<br/>
     * 使用方法：<br/>
     * 注意事项：<br/>
     * @return String
     * @date 2019-8-5
     */
    public boolean setPPPoEName(String userName);

    /**
     * 概述：设置PPPoE密码<br/>
     * 适用条件：<br/>
     * 执行流程：<br/>
     * 使用方法：<br/>
     * 注意事项：<br/>
     * @return String
     * @date 2019-8-5
     */
    public boolean setPPPoEPassword(String password);
}
