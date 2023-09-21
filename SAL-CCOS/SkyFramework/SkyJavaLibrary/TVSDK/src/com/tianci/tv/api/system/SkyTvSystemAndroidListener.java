/**
 * Copyright (C) 2012 The SkyTvOS Project
 *
 * Version     Date           Author
 * ─────────────────────────────────────
 *           2014年5月30日         Root.Lu
 *
 */

package com.tianci.tv.api.system;

import android.content.Context;

import com.tianci.tv.define.SkyTvDefine.SOURCE_SIGNAL_STATE;
import com.tianci.tv.define.object.Channel;
import com.tianci.tv.define.object.Source;
import com.tianci.tv.framework.implement.system.manager.BaseSystemMessageManager;
import com.tianci.tv.framework.implement.system.manager.contentprovider.ContentProviderMessageManager;
import com.tianci.tv.framework.implement.system.manager.ipcservice.IPCServiceMessageManager;
import com.tianci.tv.utils.SkyTvUtils;
import com.tianci.tv.utils.TVSDKDebug;

import java.util.List;

public class SkyTvSystemAndroidListener
{
    public interface ISkyTvSystemAndroidListener
    {
        byte[] onSwitchSourceStart(Source from, Source to);

        byte[] onSwitchSourceDone(Source from, Source to);

        byte[] onSwitchChannelStart(Channel channel);

        byte[] onSwitchChannelDone(Channel channel);

        byte[] onSkyTvRestart(Source source);

        byte[] onSkyTvReleased(Source source);

        byte[] onSkyTvSignalChanged(Source source, SOURCE_SIGNAL_STATE signalState);

        byte[] onSkyTvServiceStart(Source source);

        byte[] onSkyTvWindowFocusChanged(boolean hasFocus);

        byte[] onSkyTvSignalFormatChanged(List<String> infoList);
    }

    private static SkyTvSystemAndroidListener instance = null;
    private BaseSystemMessageManager mSystemMessageManager;

    public static void create(Context context, ISkyTvSystemAndroidListener listener)
    {
        if (instance == null) {
            Context ctx = context.getApplicationContext();
            if (ctx == null) {
                ctx = context;
            }
            instance = new SkyTvSystemAndroidListener(ctx, listener);
        }
    }

    private ISkyTvSystemAndroidListener listener = null;

    private SkyTvSystemAndroidListener(Context context, ISkyTvSystemAndroidListener listener)
    {
        this.listener = listener;
        boolean isIPCService = SkyTvUtils.isAppInstalled(context, "com.tianci.tv");
        TVSDKDebug.info("SkyTvSystemAndroidListener isIPCService?" + isIPCService);
        if (isIPCService) {
            mSystemMessageManager = new IPCServiceMessageManager(context);
        } else {
            mSystemMessageManager = new ContentProviderMessageManager(context);
        }
        if (mSystemMessageManager != null) {
            mSystemMessageManager.setSkyTvSystemAndroidListener(listener);
        }
    }
}
