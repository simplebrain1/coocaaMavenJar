package com.tianci.tv.framework.implement.system.manager.ipcservice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import com.tianci.tv.api.system.SystemApiParamsOnSignalChanged;
import com.tianci.tv.api.system.SystemApiParamsOnSwitchSourceDone;
import com.tianci.tv.define.SkyTvCommand;
import com.tianci.tv.define.object.Channel;
import com.tianci.tv.define.object.Source;
import com.tianci.tv.framework.implement.system.manager.BaseSystemMessageManager;
import com.tianci.tv.utils.SkyTvUtils;

import java.util.List;

/**
 * Created by hq on 2018/1/16.
 */

public class IPCServiceMessageManager extends BaseSystemMessageManager {
    public IPCServiceMessageManager(Context context) {
        IntentFilter filterKey = new IntentFilter();
        filterKey.addAction(SkyTvCommand.TV_CMD.TV_CMD_SYSTEM_ON_SWITCHSOURCE_START.toString());
        filterKey.addAction(SkyTvCommand.TV_CMD.TV_CMD_SYSTEM_ON_SWITCHSOURCE_DONE.toString());
        filterKey.addAction(SkyTvCommand.TV_CMD.TV_CMD_SYSTEM_ON_SWITCHCHANNEL_START.toString());
        filterKey.addAction(SkyTvCommand.TV_CMD.TV_CMD_SYSTEM_ON_SWITCHCHANNEL_DONE.toString());
        filterKey.addAction(SkyTvCommand.TV_CMD.TV_CMD_SYSTEM_ON_SWITCHSOURCE_START.toString());
        filterKey.addAction(SkyTvCommand.TV_CMD.TV_CMD_SYSTEM_ON_TV_RESTART.toString());
        filterKey.addAction(SkyTvCommand.TV_CMD.TV_CMD_SYSTEM_ON_TV_RELEASED.toString());
        filterKey.addAction(SkyTvCommand.TV_CMD.TV_CMD_SYSTEM_ON_SIGNAL_CHANGED.toString());
        filterKey.addAction(SkyTvCommand.TV_CMD.TV_CMD_SYSTEM_ON_TV_START.toString());
        filterKey.addAction(SkyTvCommand.TV_CMD.TV_CMD_SYSTEM_ON_WINDOWFOCUS_CHANGED.toString());
        filterKey.addAction(SkyTvCommand.TV_CMD.TV_CMD_SYSTEM_ON_SIGNALFORMAT_CHANGED.toString());
        BroadcastReceiver receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                try {
                    String action = intent.getAction();
                    SkyTvCommand.TV_CMD tvcmd = SkyTvCommand.TV_CMD.valueOf(action);
                    byte[] body = intent.getByteArrayExtra("params");
                    switch (tvcmd) {
                        case TV_CMD_SYSTEM_ON_SWITCHSOURCE_START:
                            SystemApiParamsOnSwitchSourceDone params0 = SkyTvUtils.toObject(body, SystemApiParamsOnSwitchSourceDone.class);
                            mSkyTvSystemAndroidListener.onSwitchSourceStart(params0.from, params0.to);
                            break;
                        case TV_CMD_SYSTEM_ON_SWITCHSOURCE_DONE:
                            SystemApiParamsOnSwitchSourceDone params1 = SkyTvUtils.toObject(body, SystemApiParamsOnSwitchSourceDone.class);
                            mSkyTvSystemAndroidListener.onSwitchSourceDone(params1.from, params1.to);
                            break;
                        case TV_CMD_SYSTEM_ON_SWITCHCHANNEL_START:
                            Channel channel0 = SkyTvUtils.toObject(body, Channel.class);
                            channel0.afterDeserialize();
                            mSkyTvSystemAndroidListener.onSwitchChannelStart(channel0);
                            break;
                        case TV_CMD_SYSTEM_ON_SWITCHCHANNEL_DONE:
                            Channel channel1 = SkyTvUtils.toObject(body, Channel.class);
                            channel1.afterDeserialize();
                            mSkyTvSystemAndroidListener.onSwitchChannelDone(channel1);
                            break;
                        case TV_CMD_SYSTEM_ON_TV_RESTART:
                            Source source1 = SkyTvUtils.toObject(body, Source.class);
                            source1.afterDeserialize();
                            mSkyTvSystemAndroidListener.onSkyTvRestart(source1);
                            break;
                        case TV_CMD_SYSTEM_ON_TV_RELEASED:
                            Source source2 = SkyTvUtils.toObject(body, Source.class);
                            source2.afterDeserialize();
                            mSkyTvSystemAndroidListener.onSkyTvReleased(source2);
                            break;
                        case TV_CMD_SYSTEM_ON_SIGNAL_CHANGED:
                            SystemApiParamsOnSignalChanged params2 = SkyTvUtils.toObject(body, SystemApiParamsOnSignalChanged.class);
                            params2.source.afterDeserialize();
                            mSkyTvSystemAndroidListener.onSkyTvSignalChanged(params2.source, params2.state);
                            break;
                        case TV_CMD_SYSTEM_ON_TV_START:
                            Source source = SkyTvUtils.toObject(body, Source.class);
                            source.afterDeserialize();
                            mSkyTvSystemAndroidListener.onSkyTvServiceStart(source);
                            break;
                        case TV_CMD_SYSTEM_ON_WINDOWFOCUS_CHANGED:
                            Boolean hasFocus = SkyTvUtils.toObject(body, Boolean.class);
                            mSkyTvSystemAndroidListener.onSkyTvWindowFocusChanged(hasFocus);
                            break;
                        case TV_CMD_SYSTEM_ON_SIGNALFORMAT_CHANGED:
                            List<String> infoList = SkyTvUtils.toObject(body, List.class);
                            mSkyTvSystemAndroidListener.onSkyTvSignalFormatChanged(infoList);
                            break;
                        default:
                            break;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        context.registerReceiver(receiver, filterKey);
    }
}
