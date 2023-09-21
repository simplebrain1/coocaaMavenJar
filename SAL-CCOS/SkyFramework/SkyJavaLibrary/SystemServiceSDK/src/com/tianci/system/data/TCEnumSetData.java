/**
 * Copyright (C) 2012 The SkyTvOS Project
 * <p>
 * Version     Date           Author
 * ─────────────────────────────────────
 * 2013-11-20         xingkong207
 */

package com.tianci.system.data;

import android.os.Bundle;
import android.util.Log;

import com.skyworth.framework.skysdk.util.SkyDataComposer;
import com.skyworth.framework.skysdk.util.SkyDataDecomposer;
import com.tianci.system.utils.ApiUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>Description:</p>
 * <p>write something</p>
 *
 * @author wei li
 * @version V*.*.*
 * @ClassName TCEnumSetData
 * @date 2013-11-01
 */
public class TCEnumSetData extends TCSetData implements Serializable {
    private int enumCount = 0;
    private List<String> enumList = null;
    private List<Serializable> enumSerialList = null;
    private String current = null;
    private Serializable curSerializable = null;
    private int currentIndex = -1;
    private boolean isItemID = true;
    private boolean serialize;
    private String userData = null;

    public TCEnumSetData() {
        super(SkyConfigType.SKY_CONFIG_ENUM.toString());
    }

    public TCEnumSetData(byte[] bytes) {
        super(SkyConfigType.SKY_CONFIG_ENUM.toString());
        enumList = new ArrayList<String>();
        if (bytes != null) {
            deserialize(new String(bytes));
        }
    }

    public TCEnumSetData(String value) {
        super(SkyConfigType.SKY_CONFIG_ENUM.toString());
        if (value != null) {
            enumList = new ArrayList<String>();
            deserialize(value);
        }
    }

    public TCEnumSetData(Bundle value) {
        super(SkyConfigType.SKY_CONFIG_ENUM.toString());
        if (value != null) {
            enumList = new ArrayList<String>();
            deserialize(value);
        }
    }

    public TCEnumSetData(Bundle value, byte[] bytes) {
        super(SkyConfigType.SKY_CONFIG_ENUM.toString());
        deserialize(new String(bytes));
        this.curSerializable = value.getSerializable("current");
        this.enumSerialList = (List<Serializable>) value.getSerializable(ApiUtil.KEY_RET_SERIALIZE);
    }

    private void deserialize(Bundle data) {
        this.pluginValue = data;
        this.type = data.getString("type");
        this.enumCount = data.getInt("enumCount");
        this.userData = data.getString("userdata");
        this.serialize = data.getBoolean("serialize");
        if (serialize) {
            this.curSerializable = data.getSerializable("current");
            if (enumSerialList == null) {
                enumSerialList = new ArrayList<Serializable>();
            }
            Serializable enumObj;
            for (int i = 0; i < enumCount; i++) {
                enumObj = data.getSerializable("enum" + i);
                enumSerialList.add(enumObj);
                if (current == null) {
                    currentIndex = 0;
                } else if (current.equals(enumObj)) {
                    currentIndex = i;
                }
            }
        } else {
            this.current = data.getString("current");
            String enumStr;
            for (int i = 0; i < enumCount; i++) {
                enumStr = data.getString("enum" + i);
                enumList.add(enumStr);
                if (current == null) {
                    currentIndex = 0;
                } else if (current.equals(enumStr)) {
                    currentIndex = i;
                }
            }
        }

    }

    private void deserialize(String value) {
        SkyDataDecomposer data = new SkyDataDecomposer(value);
        this.value = value;
        this.type = data.getStringValue("type");
        this.name = data.getStringValue("name");
        this.enumCount = data.getIntValue("enumCount");
        this.current = data.getStringValue("current");
        this.userData = data.getStringValue("userdata");
        this.enumList = data.getStringListValue("enumlist");
        this.currentIndex = data.getIntValue("currentIndex");
        String strEnable = data.getStringValue("enable");
        this.enable = "true".equals(strEnable);
        if (data.getStringValue("start") != null) {
            this.startProcessTimestamp = Long.valueOf(data.getStringValue("start"));
            this.endProcessTimestamp = Long.valueOf(data.getStringValue("end"));
        }
        String enumStr = null;
        for (int i = 0; i < enumCount; i++) {
            if (enumList == null) {
                break;
            }
            enumStr = enumList.get(i);
            if (current == null) {
                currentIndex = 0;
            } else if (current.equals(enumStr)) {
                currentIndex = i;
            }
        }
    }

    public int getEnumCount() {
        return enumCount;
    }

    public void setEnumCount(int enumCount) {
        this.enumCount = enumCount;
    }

    public List<String> getEnumList() {
        return enumList;
    }

    public List<? extends Serializable> getEnumSerialList() {
        return enumSerialList;
    }

    public void setEnumList(List<String> enumList) {
        this.enumList = enumList;
        if (enumList != null) {
            this.enumCount = enumList.size();
        }
    }

    public Serializable getCurSerializable() {
        return curSerializable;
    }

    public String getCurrent() {
        return current;
    }

    public void setCurrent(String current) {
        this.current = current;
        if (enumList != null) {
            for (int i = 0; i < enumList.size(); i++) {
                if (enumList.get(i).equals(current)) {
                    this.currentIndex = i;
                    break;
                }
            }
        }
    }

    public int getCurrentIndex() {
        return currentIndex;
    }

    public void setCurrentIndex(int currentIndex) {
        Log.d("uilogic", "currentindex=" + currentIndex);
        if (currentIndex > -1) {
            this.currentIndex = currentIndex;
            if (enumList != null) {
                try {
                    this.current = this.enumList.get(currentIndex);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public boolean isItemID() {
        return isItemID;
    }

    public void setItemID(boolean isItemID) {
        this.isItemID = isItemID;
    }

    public void setUserData(String userData) {
        this.userData = userData;
    }

    public String getUserData() {
        return userData;
    }

    public boolean isSerialize() {
        return this.serialize;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        SkyDataComposer composer = new SkyDataComposer();
        composer.addValue("type", this.type);
        composer.addValue("name", this.name);
        composer.addValue("enumCount", this.enumCount);
        composer.addValue("current", this.current);
        composer.addValue("userdata", this.userData);
        composer.addValue("enable", this.enable);
        composer.addValue("currentIndex", this.currentIndex);
        composer.addValue("start", String.valueOf(this.startProcessTimestamp));
        composer.addValue("end", String.valueOf(this.endProcessTimestamp));
        if (this.enumList != null && this.enumList.size() >= 1) {
            composer.addValue("enumlist", this.enumList);
        }
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
        TCEnumSetData data0 = new TCEnumSetData();
        data0.setCurrent("current");
        data0.setEnumCount(2);
        List<String> myList = new ArrayList<String>();
        myList.add("enum0");
        myList.add("enum1");
        data0.setEnumList(myList);
        System.out.println("data0=" + data0.toString());

        byte[] bytes = data0.toBytes();
        TCSetData data1 = TCSetDataFactory.createSetData(bytes);
        System.out.println("data1=" + data1.toString());
    }
}
