/*
 * Copyright (C) 2016 The SkyTvOS Project
 *
 * Version     Date              Author
 * ─────────────────────────────────────
 *   V1.0     16-7-11 下午2:34     mikan
 *
 */

package com.tianci.system.pluginFunction;

import com.tianci.system.data.SkyUsageEvent;

/**
 * <p>
 * Description:
 * </p>
 *
 * @author mikan
 * @version V1.0.0
 * @className BaseCommonFunction
 * @date 16/7/11
 */
public abstract class BaseCommonFunction extends BaseFunction
{
	private static final String TAG = "BaseCommonFunction";

	/**
	 * 概述：获取顶层应用信息<br/>
	 * 适用条件：<br/>
	 * 执行流程：<br/>
	 * 使用方法：<br/>
	 * 注意事项：<br/>
	 *
	 * @return 应用信息
	 * date 16/7/11
	 */
	public abstract SkyUsageEvent getSkyUsageEvent();
	/*
	*调用framework方法直接待机
	*/
    public abstract void powerOffByInputManager();
}
