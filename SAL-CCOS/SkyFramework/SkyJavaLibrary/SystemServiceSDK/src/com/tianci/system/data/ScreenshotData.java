/**
 * Copyright (C) 2012 The SkyTvOS Project
 *
 * Version     Date           Author
 * ─────────────────────────────────────
 *           2014-6-25          mikan
 *
 */

package com.tianci.system.data;

import java.io.Serializable;

/**
 * <p>
 * Description:截屏数据类
 * </p>
 * <p>
 * write something
 * </p>
 * 
 * @ClassName ScreenshotData
 * @author mikan
 * @date 2014-7-30
 * @version V1.0.0
 */
public class ScreenshotData implements Serializable
{
    /**
     * Description:
     */
    private static final long serialVersionUID = -6199144838603418802L;

    private final int width;
    private final int height;
    private String path = null;

    public ScreenshotData(int width, int height)
    {
        this.width = width;
        this.height = height;
    }

    public int getWidth()
    {
        return width;
    }

    public int getHeight()
    {
        return height;
    }

    public String getPath()
    {
        return path;
    }

    public void setPath(String path)
    {
        this.path = path;
    }

    @Override
    public String toString() {
        return "ScreenshotData{" +
                "width=" + width +
                ", height=" + height +
                ", path='" + path + '\'' +
                '}';
    }
}
