/**
 * Copyright (C) 2012 The SkyTvOS Project
 * <p>
 * Version     Date           Author
 * ─────────────────────────────────────
 * 2013-11-08         wei li
 */

package com.tianci.system.data;

import android.os.Bundle;

import com.skyworth.framework.skysdk.util.SkyDataComposer;
import com.skyworth.framework.skysdk.util.SkyDataDecomposer;

import java.io.Serializable;

/**
 * <p>Description:</p>
 * <p>write something</p>
 *
 * @author wei li
 * @version V1.0.0
 * @ClassName TCInfoSetData
 * @date 2013-11-08
 */
public class TCInfoSetData extends TCSetData implements Serializable {
    public TCInfoSetData() {
        super(SkyConfigType.SKY_CONFIG_INFO.toString());
        // TODO Auto-generated constructor stub
    }

    private String current = null;

    public TCInfoSetData(byte[] bytes) {
        super(SkyConfigType.SKY_CONFIG_INFO.toString());
        if (bytes != null) {
            deserialize(new String(bytes));
        }

    }

    public TCInfoSetData(String value) {
        super(SkyConfigType.SKY_CONFIG_INFO.toString());
        if (value != null) {
            deserialize(value);
        }
    }

    public TCInfoSetData(Bundle value) {
        super(SkyConfigType.SKY_CONFIG_INFO.toString());
        if (value != null) {
            deserialize(value);
        }
    }

    private void deserialize(Bundle data) {
        this.pluginValue = data;
        this.type = data.getString("type");
        this.current = data.getString("current");
    }

    private void deserialize(String value) {
        SkyDataDecomposer data = new SkyDataDecomposer(value);
        this.value = value;
        this.type = data.getStringValue("type");
        this.name = data.getStringValue("name");
        this.current = data.getStringValue("current");
        String strEnable = data.getStringValue("enable");
        this.enable = "true".equals(strEnable);
        if (data.getStringValue("start") != null) {
            this.startProcessTimestamp = Long.valueOf(data.getStringValue("start"));
            this.endProcessTimestamp = Long.valueOf(data.getStringValue("end"));
        }
    }

    public String getCurrent() {
        return current;
    }

    public void setCurrent(String current) {
        this.current = current;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        SkyDataComposer composer = new SkyDataComposer();
        composer.addValue("type", this.type);
        composer.addValue("name", this.name);
        if (this.current != null) {
            composer.addValue("current", this.current);
        } else {
            composer.addValue("current", "");
        }
        composer.addValue("enable", this.enable);
        composer.addValue("start", String.valueOf(this.startProcessTimestamp));
        composer.addValue("end", String.valueOf(this.endProcessTimestamp));
        return composer.toString();
    }

    /* (non-Javadoc)
     * @see com.skyworth.system.data.TCSetData#toBytes()
     */
    @Override
    public byte[] toBytes() {
        // TODO Auto-generated method stub
        String temp = this.toString();
        if (temp != null) {
            return temp.getBytes();
        }
        return null;
    }

    public static void main(String[] argv) {
        TCInfoSetData data0 = new TCInfoSetData();
        data0.setCurrent("current");
        System.out.println("data0=" + data0.toString());

        byte[] bytes = data0.toBytes();
        TCSetData data1 = TCSetDataFactory.createSetData(bytes);
        System.out.println("data1=" + data1.toString());
    }
}
