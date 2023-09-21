package com.tianci.net.interfaces;

public interface ISkyPPPoE {
    /**
     * 概述：获取PPPoE 账号<br/>
     * 适用条件：<br/>
     * 执行流程：<br/>
     * 使用方法：<br/>
     * 注意事项：<br/>
     * @return String
     * @date 2019-8-5
     */
    public abstract String getPPPoEName();

    /**
     * 概述：获取PPPoE密码<br/>
     * 适用条件：<br/>
     * 执行流程：<br/>
     * 使用方法：<br/>
     * 注意事项：<br/>
     * @return String
     * @date 2019-8-5
     */
    public abstract String getPPPoEPassword();
    /**
     * 概述：获取PPPoE 账号<br/>
     * 适用条件：<br/>
     * 执行流程：<br/>
     * 使用方法：<br/>
     * 注意事项：<br/>
     * @return String
     * @date 2019-8-5
     */
    public abstract boolean setPPPoEName(String userName);

    /**
     * 概述：获取PPPoE密码<br/>
     * 适用条件：<br/>
     * 执行流程：<br/>
     * 使用方法：<br/>
     * 注意事项：<br/>
     * @return String
     * @date 2019-8-5
     */
    public abstract boolean setPPPoEPassword(String password);
}
