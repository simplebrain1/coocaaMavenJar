package com.skyworth.framework.data;

import android.os.Environment;

import java.io.Serializable;

public class ExternDiskInfo implements Serializable {
    public String devPath = "";
    public String path = "";
    public String label = "";
    public String format = "";
    public String uuid = "";
    public long totalSpace = 0;
    public long usedSpace = 0;
    public long availSpace = 0;
    public String state = Environment.MEDIA_MOUNTED;

    public ExternDiskInfo() {
    }

    public ExternDiskInfo(String devPath, String path, String label,
                          String format, String uuid, long totalSpace,
                          long usedSpace, long availSpace, String state) {
        setDevPath(devPath);
        setPath(path);
        setLabel(label);
        setFormat(format);
        setUuid(uuid);
        setTotalSpace(totalSpace);
        setUsedSpace(usedSpace);
        setAvailSpace(availSpace);
        setState(state);
    }

    public String getDevPath() {
        return devPath;
    }

    public void setDevPath(String devPath) {
        this.devPath = devPath;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public long getTotalSpace() {
        return totalSpace;
    }

    public void setTotalSpace(long totalSpace) {
        this.totalSpace = totalSpace;
    }

    public long getUsedSpace() {
        return usedSpace;
    }

    public void setUsedSpace(long usedSpace) {
        this.usedSpace = usedSpace;
    }

    public long getAvailSpace() {
        return availSpace;
    }

    public void setAvailSpace(long availSpace) {
        this.availSpace = availSpace;
    }

    public String getState()
    {
        return state;
    }

    public void setState(String state)
    {
        this.state = state;
    }
}
