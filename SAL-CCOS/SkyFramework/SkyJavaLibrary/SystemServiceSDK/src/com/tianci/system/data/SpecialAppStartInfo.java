/*
 * Copyright (C) 2016 The SkyTvOS Project
 *
 * Version     Date              Author
 * ─────────────────────────────────────
 *   V1.0     16-1-12 上午10:51     mikan
 *
 */

package com.tianci.system.data;

import java.io.Serializable;
import java.util.HashMap;

/**
 * <p>
 * Description:
 * </p>
 *
 * @author mikan
 * @version V1.0.0
 * @className SpecialAppStartInfo
 * @date 16/1/12
 */
public class SpecialAppStartInfo implements Serializable
{
	private static final String TAG = "SpecialAppStartInfo";
	private static final long serialVersionUID = -208939801163331338L;

	public String pkgName = null;
	public String business_type = null;
	public HashMap<String, String> paramMap = null;

	public SpecialAppStartInfo(String pkgName, String business_type, HashMap<String, String> paramMap)
	{
		this.pkgName = pkgName;
		this.business_type = business_type;
		this.paramMap = paramMap;
	}
}
