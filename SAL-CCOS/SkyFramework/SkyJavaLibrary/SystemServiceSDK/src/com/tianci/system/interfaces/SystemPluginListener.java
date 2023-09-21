/**
 * Copyright (C) 2012 The SkyTvOS Project
 *
 * Version     Date           Author
 * ─────────────────────────────────────
 *           2014-6-26          mikan
 *
 */

package com.tianci.system.interfaces;

import com.tianci.system.data.SkyScreenshotRetData;

public interface SystemPluginListener
{
    /**
     * 概述：截屏完成后回调<br/>
     * 适用条件：<br/>
     * 执行流程：<br/>
     * 使用方法：<br/>
     * 注意事项：<br/>
     * 
     * @param data
     * @return String
     * @date 2014-6-26
     */
    public String onScreenshotGetFinish(SkyScreenshotRetData data);

}
