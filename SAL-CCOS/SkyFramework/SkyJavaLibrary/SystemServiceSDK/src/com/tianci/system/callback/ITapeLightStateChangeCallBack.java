package com.tianci.system.callback;

import com.tianci.system.data.TapeLightModeData;
import com.tianci.system.data.TapeLightStatusData;
import com.tianci.system.define.SkyConfigDefs;

/**
 * Created on 2021/7/28
 *
 * @author whw
 */
public interface ITapeLightStateChangeCallBack {
    void onTapeLightStateChange(TapeLightModeData modeData);

    void onDeviceConnectStatusChange(TapeLightStatusData status);
}
