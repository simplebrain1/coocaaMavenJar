package com.tianci.system.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.tianci.system.define.SkyConfigDefs;

import java.io.Serializable;

/**
 * Created on 2021/7/28
 *
 * @author whw
 */
public class TapeLightStatusData implements Serializable {
    private static final long serialVersionUID = 7092876225735676634L;
    String status;
    String macAddress;

    public TapeLightStatusData(String status, String macAddress) {
        this.status = status;
        this.macAddress = macAddress;
    }

    public String getStatus() {
        return status;
    }

    public String getMacAddress() {
        return macAddress;
    }

    protected TapeLightStatusData(Parcel in) {
        status = in.readString();
        macAddress = in.readString();
    }

    @Override
    public String toString() {
        return "TapeLightStatusData{" +
                "status='" + status + '\'' +
                ", macAddress='" + macAddress + '\'' +
                '}';
    }
}
