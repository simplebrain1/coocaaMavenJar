package com.tianci.tv.framework.implement.system.listener;

import com.tianci.tv.define.object.ChannelInfo;
import com.tianci.tv.define.object.SourceInfo;

import java.util.List;

/**
 * Created by hq on 2018/1/22.
 */

public abstract class OnSystemStatusChangedListener {
    public abstract void onSwitchSourceStart(SourceInfo from, SourceInfo to);

    public abstract void onSwitchSourceDone(SourceInfo from, SourceInfo to);

    public abstract void onSwitchChannelStart(ChannelInfo channel);

    public abstract void onSwitchChannelDone(ChannelInfo channel);

    public abstract void onTVRestart(SourceInfo source);

    public abstract void onTVReleased(SourceInfo source);

    public abstract void onSourceSignalChanged(SourceInfo source, int state);

    public abstract void onSkyTvWindowFocusChanged(boolean hasFocus);

    public abstract void onSignalFormatChanged(List<String> infoList);
}
