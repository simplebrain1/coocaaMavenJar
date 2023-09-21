package com.tianci.tv.framework.implement.system.listener;

import com.tianci.tv.define.object.CECDeviceInfo;

import java.util.List;

public abstract class OnCECDeviceChangedListener {

    public abstract void onChange(List<CECDeviceInfo> list);

}
