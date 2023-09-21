package com.tianci.tv.define.object;

import android.os.Parcel;
import android.os.Parcelable;

public class CECDeviceInfo implements Parcelable {

    public int logAddr; //逻辑地址代表CEC总线中的位置

    public CECDeviceInfo() {
    }

    public CECDeviceInfo(int logAddr, int hdmiDevPort, String phyAddr, String devName, String hdmiListText, int vendorId) {
        this.logAddr = logAddr;
        this.hdmiDevPort = hdmiDevPort;
        this.phyAddr = phyAddr;
        this.devName = devName;
        this.hdmiListText = hdmiListText;
        this.vendorId = vendorId;
    }

    public int hdmiDevPort; //HDMI的通道号
    public String phyAddr; //设备的物理地址
    public String devName; //设备的型号名称
    public String hdmiListText;
    public int vendorId; //厂家信息


    protected CECDeviceInfo(Parcel in) {
        logAddr = in.readInt();
        hdmiDevPort = in.readInt();
        phyAddr = in.readString();
        devName = in.readString();
        hdmiListText = in.readString();
        vendorId = in.readInt();
    }

    public static final Creator<CECDeviceInfo> CREATOR = new Creator<CECDeviceInfo>() {
        @Override
        public CECDeviceInfo createFromParcel(Parcel in) {
            return new CECDeviceInfo(in);
        }

        @Override
        public CECDeviceInfo[] newArray(int size) {
            return new CECDeviceInfo[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(logAddr);
        parcel.writeInt(hdmiDevPort);
        parcel.writeString(phyAddr);
        parcel.writeString(devName);
        parcel.writeString(hdmiListText);
        parcel.writeInt(vendorId);
    }
}
