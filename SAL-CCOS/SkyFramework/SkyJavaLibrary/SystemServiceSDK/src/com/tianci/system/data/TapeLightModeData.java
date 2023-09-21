package com.tianci.system.data;

import java.io.Serializable;

/**
 * Created on 2021/8/4
 *
 * @author whw
 */
public class TapeLightModeData implements Serializable {
    private static final long serialVersionUID = -5302697732643723714L;
    String status;
    int value;

    public TapeLightModeData(String status, int value) {
        this.status = status;
        this.value = value;
    }

    public String getStatus() {
        return status;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "TapeLightModeData{" +
                "status='" + status + '\'' +
                ", value=" + value +
                '}';
    }
}
