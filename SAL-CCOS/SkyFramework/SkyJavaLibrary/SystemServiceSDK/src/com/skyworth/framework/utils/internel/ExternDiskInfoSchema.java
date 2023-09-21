package com.skyworth.framework.utils.internel;

import android.os.Environment;
import android.os.Parcel;
import android.os.Parcelable;

public class ExternDiskInfoSchema implements Parcelable {

    public String devPath = "";
    public String path = "";
    public String label = "";
    public String format = "";
    public String uuid = "";
    public long totalSpace = 0;
    public long usedSpace = 0;
    public long availSpace = 0;
    public String state = Environment.MEDIA_MOUNTED;

    public ExternDiskInfoSchema(String string, String string2, String string3, String string4,
                                String string5, String state,long zero, long zero2, long zero3) {
        setDevPath(string);
        setPath(string2);
        setLabel(string3);
        setFormat(string4);
        setUuid(string5);
        setTotalSpace(zero);
        setUsedSpace(zero2);
        setAvailSpace(zero3);
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    // 1.必须实现Parcelable.Creator接口,否则在获取Person数据的时候，会报错，如下：
    // android.os.BadParcelableException:
    // Parcelable protocol requires a Parcelable.Creator object called CREATOR
    // on class com.um.demo.Person
    // 2.这个接口实现了从Percel容器读取Person数据，并返回Person对象给逻辑层使用
    // 3.实现Parcelable.Creator接口对象名必须为CREATOR，不如同样会报错上面所提到的错；
    // 4.在读取Parcel容器里的数据事，必须按成员变量声明的顺序读取数据，不然会出现获取数据出错
    // 5.反序列化对象
    public static final Creator<ExternDiskInfoSchema> CREATOR = new Creator() {
        @Override
        public ExternDiskInfoSchema createFromParcel(Parcel source) {
            // TODO Auto-generated method stub
            // 必须按成员变量声明的顺序读取数据，不然会出现获取数据出错
            ExternDiskInfoSchema p = new ExternDiskInfoSchema(source.readString(), source.readString(), source.readString(), source.readString(),
                    source.readString(),source.readString(), source.readLong(), source.readLong(), source.readLong());
            return p;
        }

        @Override
        public ExternDiskInfoSchema[] newArray(int size) {
            // TODO Auto-generated method stub
            return new ExternDiskInfoSchema[size];
        }
    };

    @Override
    public int describeContents() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        // TODO Auto-generated method stub
        // 1.必须按成员变量声明的顺序封装数据，不然会出现获取数据出错
        // 2.序列化对象
        dest.writeString(devPath);
        dest.writeString(path);
        dest.writeString(label);
        dest.writeString(format);
        dest.writeString(uuid);
        dest.writeString(state);
        dest.writeLong(totalSpace);
        dest.writeLong(usedSpace);
        dest.writeLong(availSpace);
    }
}
