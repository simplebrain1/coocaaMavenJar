package com.tianci.tv.define.object;

/**
 * Created by hq on 2017/12/22.
 */

public class SourceInfo {
    public static final int ATV = 0x00;
    public static final int DVBC = 0x01;
    public static final int DTMB = 0x02;
    public static final int AV = 0x03;
    public static final int YUV = 0x04;
    public static final int VGA = 0x05;
    public static final int HDMI = 0x06;
    public static final int TV = 0xFC;
    public static final int EXTERNAL = 0xFD;
    public static final int REMEMBER = 0xFE;
    public static final int EXPAND = 0xFF;

    public static final int DEFAULT_INDEX = -1;

    public String name;
    public int index;
    public String displayName;
    public int signalState;
    public int type = EXTERNAL;
    public int flag;

    public SourceInfo(String name) {
        this.name = name;
        this.index = DEFAULT_INDEX;
        this.type = getType(name);
    }

    public SourceInfo(String name, int index) {
        this.name = name;
        this.index = index;
        this.type = getType(name);
    }

    private int getType(String name) {
        int type = EXPAND;
        if ("ATV".equals(name)) {
            type = ATV;
        } else if ("DVBC".equals(name)) {
            type = DVBC;
        } else if ("DTMB".equals(name)) {
            type = DTMB;
        } else if ("AV".equals(name)) {
            type = AV;
        } else if ("YUV".equals(name)) {
            type = YUV;
        } else if ("VGA".equals(name)) {
            type = VGA;
        } else if ("HDMI".equals(name)) {
            type = HDMI;
        } else if ("TV".equals(name)) {
            type = TV;
        } else if ("EXTERNAL".equals(name)) {
            type = EXTERNAL;
        } else if ("REMEMBER".equals(name)) {
            type = REMEMBER;
        }
        return type;
    }

    public static SourceInfo ATV() {
        return new SourceInfo("ATV");
    }

    public static SourceInfo DVBC() {
        return new SourceInfo("DVBC");
    }

    public static SourceInfo DTMB() {
        return new SourceInfo("DTMB");
    }

    public static SourceInfo AV(int index) {
        return new SourceInfo("AV", index);
    }

    public static SourceInfo YUV(int index) {
        return new SourceInfo("YUV", index);
    }

    public static SourceInfo VGA(int index) {
        return new SourceInfo("VGA", index);
    }

    public static SourceInfo HDMI(int index) {
        return new SourceInfo("HDMI", index);
    }

    public static SourceInfo External() {
        return new SourceInfo("EXTERNAL");
    }

    @Override
    public String toString() {
        if (index != Source.DEFAULT_INDEX) {
            return name + (index + 1);
        } else {
            return name;
        }
    }

    public boolean equals(SourceInfo sourceInfo) {
        if (sourceInfo == null) {
            return false;
        }
        if (this.type == sourceInfo.type && this.index == sourceInfo.index) {
            return true;
        }
        return false;
    }
}
