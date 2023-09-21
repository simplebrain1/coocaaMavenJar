/**
 * Copyright (C) 2012 The SkyTvOS Project
 *
 * Version     Date           Author
 * ─────────────────────────────────────
 *           2014-1-14          mikan
 *
 */

package com.tianci.system.define;

public class TCSystemConfigDefs
{
    public enum ExternalDev
    {
        USB, SDCARD,
    }
    
    public enum ScreenshotEvent
    {
        EVENT_SCREENSHOT_SUCC,
        EVENT_SCREENSHOT_FAIL,
        EVENT_SCREENSHOT_SUCC_BUT_NOT_SUPPORT_CUSTOM_SIZE,
        EVENT_SCREENSHOT_SECURE
    }
    
    public enum ScreenshotImgFormat
    {
        JPEG, YUV,
    }
}
