/**
 * Copyright (C) 2012 The SkyTvOS Project
 *
 * Version     Date           Author
 * ─────────────────────────────────────
 *           2015年3月26日         xeasy
 *
 */

package com.tianci.tv.api.system;

import com.tianci.tv.framework.api.SkyTvApiParams;

public class SystemApiParamsSetNeedShowBootGuidee extends SkyTvApiParams
{
    /**
     * 
     */
    private static final long serialVersionUID = -8788635938769964659L;

    public boolean setValue;

    public SystemApiParamsSetNeedShowBootGuidee(boolean setValue)
    {
        this.setValue = setValue;
    }
}
