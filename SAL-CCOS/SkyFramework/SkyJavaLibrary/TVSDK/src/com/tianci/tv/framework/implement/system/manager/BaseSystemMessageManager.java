package com.tianci.tv.framework.implement.system.manager;

import com.tianci.tv.api.system.SkyTvSystemAndroidListener;

/**
 * Created by hq on 2018/1/16.
 */

public abstract class BaseSystemMessageManager {
    protected SkyTvSystemAndroidListener.ISkyTvSystemAndroidListener mSkyTvSystemAndroidListener;

    public boolean setSkyTvSystemAndroidListener(SkyTvSystemAndroidListener.ISkyTvSystemAndroidListener listener) {
        mSkyTvSystemAndroidListener = listener;
        return true;
    }
}
