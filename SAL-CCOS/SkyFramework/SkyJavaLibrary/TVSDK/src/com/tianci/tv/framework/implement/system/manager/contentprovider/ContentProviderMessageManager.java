package com.tianci.tv.framework.implement.system.manager.contentprovider;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.text.TextUtils;

import com.tianci.tv.define.SkyTvDefine;
import com.tianci.tv.define.object.Channel;
import com.tianci.tv.define.object.ChannelInfo;
import com.tianci.tv.define.object.Source;
import com.tianci.tv.framework.implement.system.manager.BaseSystemMessageManager;
import com.tianci.tv.utils.TVSDKDebug;

import java.util.List;

/**
 * Created by hq on 2018/1/16.
 */

public class ContentProviderMessageManager extends BaseSystemMessageManager {
    public static final String TVSDK_SWITCH_SOURCE_START = "tvsdk_switch_source_start";
    public static final String TVSDK_SWITCH_SOURCE_DONE = "tvsdk_switch_source_done";
    public static final String TVSDK_SWITCH_CHANNEL_START = "tvsdk_switch_channel_start";
    public static final String TVSDK_SWITCH_CHANNEL_DONE = "tvsdk_switch_channel_done";
    public static final String TVSDK_TV_RESTART = "tvsdk_tv_restart";
    public static final String TVSDK_TV_RELEASE = "tvsdk_tv_release";
    public static final String TVSDK_SIGNAL_STATE_CHANGED = "tvsdk_signal_state_changed";
    public static final String TVSDK_WINDOW_FOCUS_CHANGED = "tvsdk_window_focus_changed";
    public static final String TVSDK_SIGNAL_FORMAT_CHANGED = "tvsdk_signal_format_changed";

    public ContentProviderMessageManager(Context context) {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(TVSDK_SWITCH_SOURCE_START);
        intentFilter.addAction(TVSDK_SWITCH_SOURCE_DONE);
        intentFilter.addAction(TVSDK_SWITCH_CHANNEL_START);
        intentFilter.addAction(TVSDK_SWITCH_CHANNEL_DONE);
        intentFilter.addAction(TVSDK_TV_RESTART);
        intentFilter.addAction(TVSDK_TV_RELEASE);
        intentFilter.addAction(TVSDK_SIGNAL_STATE_CHANGED);
        intentFilter.addAction(TVSDK_WINDOW_FOCUS_CHANGED);
        intentFilter.addAction(TVSDK_SIGNAL_FORMAT_CHANGED);
        BroadcastReceiver receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (intent == null) {
                    return;
                }
                TVSDKDebug.info("action:" + intent.getAction());
                if (TVSDK_SWITCH_SOURCE_START.equals(intent.getAction())) {
                    if (mSkyTvSystemAndroidListener != null) {
                        String sourceNameFrom = intent.getStringExtra("sourceNameFrom");
                        int sourceIndexFrom = intent.getIntExtra("sourceIndexFrom", Source.DEFAULT_INDEX);
                        Source sourceFrom = new Source(sourceNameFrom, sourceIndexFrom);
                        String sourceNameTo = intent.getStringExtra("sourceNameTo");
                        int sourceIndexTo = intent.getIntExtra("sourceIndexTo", Source.DEFAULT_INDEX);
                        Source sourceTo = new Source(sourceNameTo, sourceIndexTo);
                        String sourceFromId = intent.getStringExtra("sourceFromId");
                        String sourceToId = intent.getStringExtra("sourceToId");
                        if (!TextUtils.isEmpty(sourceFromId)){
                            sourceFrom.id = sourceFromId;
                        }
                        if (!TextUtils.isEmpty(sourceToId)){
                            sourceTo.id = sourceToId;
                        }
                        mSkyTvSystemAndroidListener.onSwitchSourceStart(sourceFrom, sourceTo);
                    }
                } else if (TVSDK_SWITCH_SOURCE_DONE.equals(intent.getAction())) {
                    if (mSkyTvSystemAndroidListener != null) {
                        String sourceNameFrom = intent.getStringExtra("sourceNameFrom");
                        int sourceIndexFrom = intent.getIntExtra("sourceIndexFrom", Source.DEFAULT_INDEX);
                        Source sourceFrom = new Source(sourceNameFrom, sourceIndexFrom);
                        String sourceNameTo = intent.getStringExtra("sourceNameTo");
                        int sourceIndexTo = intent.getIntExtra("sourceIndexTo", Source.DEFAULT_INDEX);
                        Source sourceTo = new Source(sourceNameTo, sourceIndexTo);
                        String sourceFromId = intent.getStringExtra("sourceFromId");
                        String sourceToId = intent.getStringExtra("sourceToId");
                        if (!TextUtils.isEmpty(sourceFromId)){
                            sourceFrom.id = sourceFromId;
                        }
                        if (!TextUtils.isEmpty(sourceToId)){
                            sourceTo.id = sourceToId;
                        }
                        mSkyTvSystemAndroidListener.onSwitchSourceDone(sourceFrom, sourceTo);
                    }
                } else if (TVSDK_SWITCH_CHANNEL_START.equals(intent.getAction())) {
                    if (mSkyTvSystemAndroidListener != null) {
                        Channel channel = null;
                        String channelName = intent.getStringExtra("channelName");
                        if (channelName != null) {
                            String channelID = String.valueOf(intent.getIntExtra("channelID", 0));
                            channel = new Channel(channelID, channelName);
                            channel.index = intent.getIntExtra("channelIndex", 0);
                            int channelType = intent.getIntExtra("channelType", ChannelInfo.TV);
                            channel.type = Channel.CHANNEL_TYPE.TV;
                            if (channelType == ChannelInfo.RADIO) {
                                channel.type = Channel.CHANNEL_TYPE.RADIO;
                            }
                            channel.isDeleted = intent.getBooleanExtra("isDeleted", false);
                            channel.bSkip = intent.getBooleanExtra("bSkip", false);
                            channel.bAfcEnable = intent.getBooleanExtra("bAfcEnable", true);
                            channel.invalid = intent.getBooleanExtra("invalid", true);
                        }
                        mSkyTvSystemAndroidListener.onSwitchChannelStart(channel);
                    }
                } else if (TVSDK_SWITCH_CHANNEL_DONE.equals(intent.getAction())) {
                    if (mSkyTvSystemAndroidListener != null) {
                        Channel channel = null;
                        String channelName = intent.getStringExtra("channelName");
                        if (channelName != null) {
                            String channelID = String.valueOf(intent.getIntExtra("channelID", 0));
                            channel = new Channel(channelID, channelName);
                            channel.index = intent.getIntExtra("channelIndex", 0);
                            int channelType = intent.getIntExtra("channelType", ChannelInfo.TV);
                            channel.type = Channel.CHANNEL_TYPE.TV;
                            if (channelType == ChannelInfo.RADIO) {
                                channel.type = Channel.CHANNEL_TYPE.RADIO;
                            }
                            channel.isDeleted = intent.getBooleanExtra("isDeleted", false);
                            channel.bSkip = intent.getBooleanExtra("bSkip", false);
                            channel.bAfcEnable = intent.getBooleanExtra("bAfcEnable", true);
                            channel.invalid = intent.getBooleanExtra("invalid", true);
                        }
                        mSkyTvSystemAndroidListener.onSwitchChannelDone(channel);
                    }
                } else if (TVSDK_TV_RESTART.equals(intent.getAction())) {
                    if (mSkyTvSystemAndroidListener != null) {
                        String sourceName = intent.getStringExtra("sourceName");
                        int sourceIndex = intent.getIntExtra("sourceIndex", Source.DEFAULT_INDEX);
                        Source source = new Source(sourceName, sourceIndex);
                        String sourceId = intent.getStringExtra("sourceId");
                        if (!TextUtils.isEmpty(sourceId)){
                            source.id = sourceId;
                        }
                        mSkyTvSystemAndroidListener.onSkyTvRestart(source);
                    }
                } else if (TVSDK_TV_RELEASE.equals(intent.getAction())) {
                    if (mSkyTvSystemAndroidListener != null) {
                        String sourceName = intent.getStringExtra("sourceName");
                        int sourceIndex = intent.getIntExtra("sourceIndex", Source.DEFAULT_INDEX);
                        Source source = new Source(sourceName, sourceIndex);
                        String sourceId = intent.getStringExtra("sourceId");
                        if (!TextUtils.isEmpty(sourceId)){
                            source.id = sourceId;
                        }
                        mSkyTvSystemAndroidListener.onSkyTvReleased(source);
                    }
                } else if (TVSDK_SIGNAL_STATE_CHANGED.equals(intent.getAction())) {
                    if (mSkyTvSystemAndroidListener != null) {
                        String sourceName = intent.getStringExtra("sourceName");
                        int sourceIndex = intent.getIntExtra("sourceIndex", Source.DEFAULT_INDEX);
                        Source source = new Source(sourceName, sourceIndex);
                        String displayName = intent.getStringExtra("displayName");
                        String sourceId = intent.getStringExtra("sourceId");
                        int flag = intent.getIntExtra("flag", 0);
                        if (!TextUtils.isEmpty(sourceId)){
                            source.id = sourceId;
                        }
                        if (displayName != null) {
                            source.displayName = displayName;
                        }
                        int state = intent.getIntExtra("state", SkyTvDefine.STATE_NOSIGNAL);
                        SkyTvDefine.SOURCE_SIGNAL_STATE signalState = SkyTvDefine.SOURCE_SIGNAL_STATE.NOSIGNAL;
                        if (state == SkyTvDefine.STATE_SIGNALED || state == SkyTvDefine.STATE_UNSUPPORT) {
                            signalState = SkyTvDefine.SOURCE_SIGNAL_STATE.PLAY;
                        }
                        source.signalState = signalState;
                        source.flag = flag;
                        mSkyTvSystemAndroidListener.onSkyTvSignalChanged(source, signalState);
                    }
                } else if (TVSDK_WINDOW_FOCUS_CHANGED.equals(intent.getAction())) {
                    if (mSkyTvSystemAndroidListener != null) {
                        boolean hasFocus = intent.getBooleanExtra("hasFocus", false);
                        mSkyTvSystemAndroidListener.onSkyTvWindowFocusChanged(hasFocus);
                    }
                }else if(TVSDK_SIGNAL_FORMAT_CHANGED.equals(intent.getAction())){
                    if (mSkyTvSystemAndroidListener != null) {
                        List<String> infoList = intent.getStringArrayListExtra("resolutionList");
                        mSkyTvSystemAndroidListener.onSkyTvSignalFormatChanged(infoList);
                    }
                }
            }
        };
        context.registerReceiver(receiver, intentFilter);
    }
}
