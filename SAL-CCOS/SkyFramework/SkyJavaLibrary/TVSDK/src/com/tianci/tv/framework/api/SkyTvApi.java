package com.tianci.tv.framework.api;

import android.content.Context;

import com.skyworth.framework.skysdk.ipc.SkyApplication;
import com.skyworth.framework.skysdk.ipc.SkyApplication.SkyCmdConnectorListener;

public abstract class SkyTvApi
{
    protected SkyCmdConnectorListener listener;
    protected Context mContext;

    public SkyTvApi(Context context) {
        mContext = context;
        this.listener = SkyApplication.getListener();
    }
}
