package com.tianci.system.data;

import com.skyworth.framework.skysdk.util.SkyDataComposer;
import com.skyworth.framework.skysdk.util.SkyDataDecomposer;

import java.io.Serializable;

/**
@Date : 2015年4月7日
@Author : Zhan Yu
@Description : 首页切换使用到的首页数据
 */
public 	class SupportedHomepageXmlNode implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public String pkgName = "";
	public String clsName = "";
	public String isDefault = "";
	
	public SupportedHomepageXmlNode()
	{
		
	}
	
	public SupportedHomepageXmlNode(String data)
	{
		deserialize(data);
	}
	
    private void deserialize(String value)
    {
        SkyDataDecomposer data = new SkyDataDecomposer(value);
        pkgName = data.getStringValue("pkgName");
        clsName = data.getStringValue("clsName");
        isDefault = data.getStringValue("isDefault");
    }
	
    @Override
    public String toString()
    {
        SkyDataComposer composer = new SkyDataComposer();
        composer.addValue("pkgName", this.pkgName);
        composer.addValue("clsName", this.clsName);
        composer.addValue("isDefault", this.isDefault);
        return composer.toString();
    }
	
}


