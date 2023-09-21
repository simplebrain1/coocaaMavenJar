/**
 * Copyright (C) 2012 The SkyTvOS Project
 * <p>
 * Version     Date           Author
 * ─────────────────────────────────────
 * 2013-11-20         wei li
 */

package com.tianci.system.data;

import android.os.Bundle;
import android.util.Log;

import com.skyworth.framework.skysdk.util.SkyDataComposer;
import com.skyworth.framework.skysdk.util.SkyDataDecomposer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>Description:</p>
 * <p>write something</p>
 *
 * @author wei li
 * @version V1.0.0
 * @ClassName TCSwitchSetData
 * @date 2013-11-01
 */
public class TCSwitchSetData extends TCSetData implements Serializable {
    private List<String> associtates = null;
    private boolean isOn = false;
    private int switchType = 0; // 0：标准开关， 1：非标准开关，可点击菜单圆环无动画
    private boolean isUserDefinedOn = false; // 目前用于收藏的图标的显示

    public TCSwitchSetData() {
        super(SkyConfigType.SKY_CONFIG_SWITCH.toString());
    }

    public TCSwitchSetData(byte[] bytes) {
        super(SkyConfigType.SKY_CONFIG_SWITCH.toString());
        if (bytes != null) {
            deserialize(new String(bytes));
        }
    }

    public TCSwitchSetData(String value) {
        super(SkyConfigType.SKY_CONFIG_SWITCH.toString());
        if (value != null) {
            deserialize(value);
        }
    }

    public TCSwitchSetData(Bundle value) {
        super(SkyConfigType.SKY_CONFIG_SWITCH.toString());
        if (value != null) {
            deserialize(value);
        }
    }

    private void deserialize(Bundle data) {
        this.pluginValue = data;
        this.type = data.getString("type");
        this.associtates = data.getStringArrayList("associate");
        String current = null;
        try {
            current = data.getString("current");
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (current == null) {
            this.isOn = data.getBoolean("current");
        } else {
            this.isOn = data.getString("current").contains("ON");
        }
        Log.d("SystemRecon", "switchtype data=" + data);
        try {
            this.switchType = data.getInt("switchtype");
        } catch (Exception e) {
            this.switchType = 0;
        }
    }

    private void deserialize(String value) {
        SkyDataDecomposer data = new SkyDataDecomposer(value);
        this.value = value;
        this.type = data.getStringValue("type");
        this.name = data.getStringValue("name");
        this.associtates = data.getStringListValue("associate");
        this.isOn = data.getBooleanValue("current");
        this.switchType = data.getIntValue("switchtype");
        String strEnable = data.getStringValue("enable");
        this.enable = "true".equals(strEnable);
        if (data.getStringValue("start") != null) {
            this.startProcessTimestamp = Long.valueOf(data.getStringValue("start"));
            this.endProcessTimestamp = Long.valueOf(data.getStringValue("end"));
        }
    }

    public List<String> getAssocitates() {
        return associtates;
    }

    public void setAssocitates(List<String> associtates) {
        this.associtates = associtates;
    }

    public boolean isOn() {
        return isOn;
    }

    public void setOn(boolean isOn) {
        this.isOn = isOn;
    }

    public int getSwitchType() {
        return switchType;
    }

    public void setSwitchType(int switchType) {
        this.switchType = switchType;
    }

    public boolean isUserDefinedOn() {
        return isUserDefinedOn;
    }

    public void setUserDefinedOn(boolean isUserDefinedOn) {
        this.isUserDefinedOn = isUserDefinedOn;
    }

    @Override
    public String toString() {
        SkyDataComposer data = new SkyDataComposer();
        data.addValue("type", type);
        data.addValue("name", this.name);
        if (associtates != null) {
            data.addValue("associate", associtates);
        }
        data.addValue("current", this.isOn);
        data.addValue("switchtype", switchType);
        data.addValue("enable", this.enable);
        data.addValue("start", String.valueOf(this.startProcessTimestamp));
        data.addValue("end", String.valueOf(this.endProcessTimestamp));
        return data.toString();
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
        TCSwitchSetData data0 = new TCSwitchSetData();
        data0.setOn(true);
        data0.setName("switchname");
        List<String> myList = new ArrayList<String>();
        myList.add("acenum0");
        myList.add("acenum1");
        data0.setAssocitates(myList);
        System.out.println("data0=" + data0.toString());

        byte[] bytes = data0.toBytes();
        long start = System.currentTimeMillis();
        System.out.println("start=" + start);
        TCSetData data1 = TCSetDataFactory.createSetData(bytes);
        long end = System.currentTimeMillis();
        System.out.println("end=" + end);
        long expire = end - start;
        System.out.println("expire=" + expire);
        System.out.println("data1=" + data1.toString());
        long end1 = System.currentTimeMillis();
        System.out.println("time1=" + (end1 - end));
    }
}
