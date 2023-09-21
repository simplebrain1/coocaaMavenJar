/**
 * Copyright (C) 2012 The SkyTvOS Project
 * <p>
 * Version     Date           Author
 * ─────────────────────────────────────
 * 2013-11-20         xingkong207
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
 * @version V*.*.*
 * @ClassName TCRangeData
 * @date 2013-11-01
 */
public class TCRangeSetData extends TCSetData implements Serializable {
    private int max = 0;
    private int min = 0;
    private int current = 0;

    public TCRangeSetData() {
        super(SkyConfigType.SKY_CONFIG_RANGE.toString());
    }

    public TCRangeSetData(byte[] bytes) {
        super(SkyConfigType.SKY_CONFIG_RANGE.toString());
        if (bytes != null) {
            deserialize(new String(bytes));
        }
    }

    public TCRangeSetData(String value) {
        super(SkyConfigType.SKY_CONFIG_RANGE.toString());
        if (value != null) {
            deserialize(value);
        }
    }

    public TCRangeSetData(Bundle value) {
        super(SkyConfigType.SKY_CONFIG_RANGE.toString());
        if (value != null) {
            deserialize(value);
        }
    }

    private void deserialize(Bundle data) {
        max = data.getInt("max");
        min = data.getInt("min");
        current = data.getInt("current");
    }

    private void deserialize(String value) {
        SkyDataDecomposer data = new SkyDataDecomposer(value);
        max = data.getIntValue("max");
        min = data.getIntValue("min");
        current = data.getIntValue("current");
        name = data.getStringValue("name");
        String strEnable = data.getStringValue("enable");
        this.enable = "true".equals(strEnable);
        if (data.getStringValue("start") != null) {
            this.startProcessTimestamp = Long.valueOf(data.getStringValue("start"));
            this.endProcessTimestamp = Long.valueOf(data.getStringValue("end"));
        }
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        SkyDataComposer composer = new SkyDataComposer();
        composer.addValue("type", this.type);
        composer.addValue("max", this.max);
        composer.addValue("min", this.min);
        composer.addValue("current", this.current);
        composer.addValue("name", this.name);
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
        TCRangeSetData data0 = new TCRangeSetData();
        data0.setMax(100);
        data0.setMin(0);
        data0.setCurrent(45);
        System.out.println("data0=" + data0.toString());

        byte[] bytes = data0.toBytes();
        TCSetData data1 = TCSetDataFactory.createSetData(bytes);
        System.out.println("data1=" + data1.toString());
    }
}
