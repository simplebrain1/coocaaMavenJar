package com.tianci.net.data;

import com.skyworth.framework.skysdk.util.SkyDataComposer;
import com.skyworth.framework.skysdk.util.SkyDataDecomposer;

import java.io.Serializable;


/**
 * PPPoE数据，连接成功后可以获取到的ip地址、网关、子网掩码、DNS、MAC等信息.
 * @author Administrator
 *
 */
public class SkyPppoeInfo implements Serializable
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 123456789L;
	
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
	public String netInterface = null;
	
	/**
     * Description:ip addr
     */
    public String ipAddress = null;
    /**
     * Description:网关
     */
    public String gateway = null;
    /**
     * Description:子网掩码
     */
    public String netMask = null;
    /**
     * Description:首选dns
     */
    public String dns = null;
    /**
     * Description:备用dns
     */
    public String dns2 = null;
    /**
     * Description:物理地址
     */
    public String mac = null;
    
    public SkyPppoeInfo()
    {
    	
    }
    
    public SkyPppoeInfo(String info)
    {
        SkyDataDecomposer decomposer = new SkyDataDecomposer(info);
        ipAddress = decomposer.getStringValue("ipAddress");
        gateway = decomposer.getStringValue("gateway");
        netMask = decomposer.getStringValue("netmask");
        dns = decomposer.getStringValue("dns");
        dns2 = decomposer.getStringValue("dns2");
        mac = decomposer.getStringValue("mac");
        username = decomposer.getStringValue("username");
        password = decomposer.getStringValue("password");
        netInterface = decomposer.getStringValue("netInterface");
    }

    public String toString()
    {
        SkyDataComposer composer = new SkyDataComposer();
        composer.addValue("ipAddress", ipAddress);
        composer.addValue("gateway", gateway);
        composer.addValue("netmask", netMask);
        composer.addValue("dns", dns);
        composer.addValue("dns2", dns2);
        composer.addValue("mac", mac);
        composer.addValue("username", username);
        composer.addValue("password", password);
        composer.addValue("netInterface", netInterface);
        return composer.toString();
    }
    
}
