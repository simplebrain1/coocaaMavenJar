package com.tianci.net.data;

import com.skyworth.framework.skysdk.util.SkyDataComposer;
import com.skyworth.framework.skysdk.util.SkyDataDecomposer;
import java.io.Serializable;

public class SkyPPPoEData implements Serializable
{
    /**
     *
     */
    private static final long serialVersionUID = 123456788L;

    /**
     * Description:账号
     */
    public String username = null;
    /**
     * Description:密码
     */
    public String password = null;
    /**
     * Description:网络接口
     */
    public String netInterface = "eth0";


    public SkyPPPoEData()
    {

    }

    public SkyPPPoEData(String info)
    {
        SkyDataDecomposer decomposer = new SkyDataDecomposer(info);
        username = decomposer.getStringValue("username");
        password = decomposer.getStringValue("password");
        netInterface = decomposer.getStringValue("netInterface");
    }

    public String toString()
    {
        SkyDataComposer composer = new SkyDataComposer();
        composer.addValue("username", username);
        composer.addValue("password", password);
        composer.addValue("netInterface", netInterface);
        return composer.toString();
    }
}