package com.tianci.system.data;

import com.skyworth.framework.skysdk.util.SkyDataComposer;
import com.skyworth.framework.skysdk.util.SkyDataDecomposer;

import java.io.Serializable;

/**
@Date : 2015年3月23日
@Author : Zhan Yu
@Description : 睡眠时间数据
 */
public class SleepTimerData implements Serializable
{
	/**
	 * 睡眠时间开关，true表示打开，false表示关闭
	 */
	public boolean state;
	
	/**
	 * 小时
	 */
	public int hour;
	
	/**
	 * 分钟
	 */
	public int minute;
	
	public SleepTimerData()
	{
		
	}
	
	public SleepTimerData(String data)
	{
		deserialize(data);
	}
	
    private void deserialize(String value)
    {
        SkyDataDecomposer data = new SkyDataDecomposer(value);
        state = data.getBooleanValue("state");
        hour = data.getIntValue("hour");
        minute = data.getIntValue("minute");
    }
	
    @Override
    public String toString()
    {
        SkyDataComposer composer = new SkyDataComposer();
        composer.addValue("state", this.state);
        composer.addValue("hour", this.hour);
        composer.addValue("minute", this.minute);
        return composer.toString();
    }
    
    public static void main(String[] args)
	{
    	SleepTimerData data = new SleepTimerData();
    	data.state = true;
    	data.hour = 99;
    	data.minute = 66;
    	
    	String toS = data.toString();
    	System.out.println("toString == " + toS);
    	
    	SleepTimerData data2 = new SleepTimerData(toS);
    	System.out.println(data2.state);
    	System.out.println(data2.hour);
    	System.out.println(data2.minute);
	}
}


