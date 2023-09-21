/*
 * Copyright (C) 2016 The SkyTvOS Project
 *
 * Version     Date              Author
 * ─────────────────────────────────────
 *   V1.0     16-7-11 下午2:36     mikan
 *
 */

package com.tianci.system.data;

import java.io.Serializable;

/**
 * <p>
 * Description:
 * </p>
 *
 * @author mikan
 * @version V1.0.0
 * @className SkyUsageState
 * @date 16/7/11
 */
public class SkyUsageEvent implements Serializable
{
	private static final String TAG = "SkyUsageState";
	private static final long serialVersionUID = -6357046614591505395L;

	public String className = null;
	public String packageName = null;
}
