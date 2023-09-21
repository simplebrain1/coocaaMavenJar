package com.tianci.system.data;

import com.skyworth.framework.skysdk.util.SkyData;

/**
@Date : 2016年1月23日
@Author : Zhan Yu
@Description : 儿童模式的观看时长控制/设置（家长控制）
 */
public class ChildModeTimeLimitData
{
	private int timeRangeStart = 8; //观看时段开始时间
	private int timeRangeEnd = 21; //观看时段结束时间
	
	private int timeDuration = TIME_DURATION_INFINITY; //每次观看时长
	
	/** 不限 */
	public final static int TIME_DURATION_INFINITY = 0;
	
	public ChildModeTimeLimitData()
	{
		
	}
	
	public ChildModeTimeLimitData(String toString)
	{
		SkyData data = new SkyData(toString);
		timeRangeStart = data.getInt("timeRangeStart");
		timeRangeEnd = data.getInt("timeRangeEnd");
		timeDuration = data.getInt("timeDuration");
	}
	
	@Override
	public String toString()
	{
		SkyData data = new SkyData();
		data.add("timeRangeStart", timeRangeStart);
		data.add("timeRangeEnd", timeRangeEnd);
		data.add("timeDuration", timeDuration);
		return data.toString();
	}

	/**
	 * 获取观看时长起始时间
	 * @return
	 */
	public int getTimeRangeStart()
	{
		return timeRangeStart;
	}

	/**
	 * 设置观看时长起始时间
	 * @return
	 */
	public void setTimeRangeStart(int timeRangeStart)
	{
		if(timeRangeStart < 0 || timeRangeStart > 23)
		{
			throw new IllegalArgumentException("Illegal params, TimeRangeStart should be a value at [0, 23]");
		}
		this.timeRangeStart = timeRangeStart;
	}

	/**
	 * 获取观看时长结束时间
	 * @return
	 */
	public int getTimeRangeEnd()
	{
		return timeRangeEnd;
	}

	/**
	 * 设置观看时长结束时间
	 * @return
	 */
	public void setTimeRangeEnd(int timeRangeEnd)
	{
		if(timeRangeStart < 1 || timeRangeStart > 24)
		{
			throw new IllegalArgumentException("Illegal params, TimeRangeEnd should be a value at [1, 24]");
		}
		this.timeRangeEnd = timeRangeEnd;
	}

	/**
	 * 获取每次观看时长
	 * @return
	 */
	public int getTimeDuration()
	{
		return timeDuration;
	}

	/**
	 * 设置每次观看时长
	 * @return
	 */
	public void setTimeDuration(int timeDuration)
	{
		if(timeRangeStart < 0 || timeRangeStart > 60)
		{
			throw new IllegalArgumentException("Illegal params, TimeRangeEnd should be a value at [0, 60] and divided by 10");
		}
		this.timeDuration = timeDuration;
	}
	
	public static void main(String[] args)
	{
		ChildModeTimeLimitData data = new ChildModeTimeLimitData();
		data.setTimeRangeStart(11);
		data.setTimeRangeEnd(18);
		data.setTimeDuration(20);
		
		System.out.println(data.toString());
		
		ChildModeTimeLimitData data2 = new ChildModeTimeLimitData(data.toString());
		System.out.println(data2.toString());
		System.out.println("time_range_start=" + data2.getTimeRangeStart());
		System.out.println("time_range_end=" + data2.getTimeRangeEnd());
		System.out.println("time_duration=" + data2.getTimeDuration());
		
		ChildModeTimeLimitData data3 = new ChildModeTimeLimitData();
		data3.setTimeRangeStart(-1);
	}
}


