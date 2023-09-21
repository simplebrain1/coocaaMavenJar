/**
 * Copyright (C) 2012 The SkyTvOS Project
 * <p>
 * Version     Date           Author
 * ─────────────────────────────────────
 * 2013-11-20         xingkong207
 */

package com.tianci.system.data;

import android.os.Bundle;

import com.skyworth.framework.skysdk.util.SkyDataDecomposer;

import java.io.Serializable;

/**
 * <p>Description:</p> 
 * <p>write something</p>
 * @ClassName TCSetingData
 * @author wei li
 * @date 2013-11-20
 * @version V1.0.0
 */
public abstract class TCSetData implements Serializable {
    public enum SkyConfigType {
        SKY_CONFIG_NONE,
        SKY_CONFIG_SINGLE,
        SKY_CONFIG_RANGE,
        SKY_CONFIG_ENUM,
        SKY_CONFIG_INPUT_VALUE,
        SKY_CONFIG_INFO,
        SKY_CONFIG_SWITCH,
        SKY_CONFIG_RET,
    }

    protected String name = null;
    protected String type = null;
    protected String value = null;
    protected Bundle pluginValue = null;
    protected long startProcessTimestamp = 0;
    protected long endProcessTimestamp = 0;
    protected boolean enable = true;

    public TCSetData(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Bundle getPluginValue() {
        return pluginValue;
    }

    public void setPluginValue(Bundle pluginValue) {
        this.pluginValue = pluginValue;
    }

    public long getStartProcessTimestamp() {
        return startProcessTimestamp;
    }

    public void setStartProcessTimestamp(long startProcessTimestamp) {
        this.startProcessTimestamp = startProcessTimestamp;
    }

    public long getEndProcessTimestamp() {
        return endProcessTimestamp;
    }

    public void setEndProcessTimestamp(long endProcessTimestamp) {
        this.endProcessTimestamp = endProcessTimestamp;
    }


    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public abstract byte[] toBytes();

    protected static SkyConfigType getType(byte[] bytes) {
        try {
            String temp = new String(bytes);
            SkyDataDecomposer composer = new SkyDataDecomposer(temp);
            String type = composer.getStringValue("type");
            return SkyConfigType.valueOf(type);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String toString() {
        return "TCSetData{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", value='" + value + '\'' +
                ", pluginValue=" + pluginValue +
                ", startProcessTimestamp=" + startProcessTimestamp +
                ", endProcessTimestamp=" + endProcessTimestamp +
                ", enable=" + enable +
                '}';
    }
}
