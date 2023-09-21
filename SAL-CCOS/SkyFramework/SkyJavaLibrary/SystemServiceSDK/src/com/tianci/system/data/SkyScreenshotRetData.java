/**
 * Copyright (C) 2012 The SkyTvOS Project
 *
 * Version     Date           Author
 * ─────────────────────────────────────
 *           2014-7-30          mikan
 *
 */

package com.tianci.system.data;

import com.tianci.system.define.TCSystemConfigDefs.ScreenshotEvent;
import com.tianci.system.define.TCSystemConfigDefs.ScreenshotImgFormat;

import java.io.Serializable;

public class SkyScreenshotRetData implements Serializable
{

    /**
     * Description:
     */
    private static final long serialVersionUID = 3998009194198938162L;

    private  int width;
    private  int height;
    private  String path;
    private  ScreenshotEvent event;
    private ScreenshotImgFormat format = ScreenshotImgFormat.JPEG;
    private int offset = 0;

    private int port;
    private String ip;

    public SkyScreenshotRetData(){}

    public SkyScreenshotRetData(int width, int height, String path, ScreenshotEvent event)
    {
        this.width = width;
        this.height = height;
        this.path = path;
        this.event = event;
    }

    @Override
    public String toString() {
        return "SkyScreenshotRetData{" +
                "width=" + width +
                ", height=" + height +
                ", path='" + path + '\'' +
                ", event=" + event +
                ", format=" + format +
                ", offset=" + offset +
                ", port=" + port +
                ", ip='" + ip + '\'' +
                '}';
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public ScreenshotEvent getEvent() {
        return event;
    }

    public void setEvent(ScreenshotEvent event) {
        this.event = event;
    }

    public ScreenshotImgFormat getFormat() {
        return format;
    }

    public void setFormat(ScreenshotImgFormat format) {
        this.format = format;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
