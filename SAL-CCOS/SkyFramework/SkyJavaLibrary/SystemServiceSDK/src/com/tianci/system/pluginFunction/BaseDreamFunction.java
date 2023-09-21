/*
 * Copyright (C) 2016 The SkyTvOS Project
 *
 * Version     Date              Author
 * ─────────────────────────────────────
 *   V1.0     16-1-13 下午6:04     mikan
 *
 */

package com.tianci.system.pluginFunction;

/**
 * <p>
 * Description:
 * </p>
 *
 * @author mikan
 * @version V1.0.0
 * @className BaseDreamFunction
 * @date 16/1/13
 */
@Deprecated
public abstract class BaseDreamFunction extends BaseFunction
{
	private static final String TAG = "BaseDreamFunction";

	/**
	 * 概述：启动andriod配置的屏保br/>
	 * 注意事项：当andriod未配置屏保, 则可能无效果<br/>
	 *
	 * date 16/1/13
	 */
	public abstract void startDream();

	/**
	 * 概述：结束andriod配置的屏保br/>
	 * 注意事项：当andriod未配置屏保, 则可能无效果<br/>
	 *
	 * date 16/1/13
	 */
	public abstract void wakeUp();
}
