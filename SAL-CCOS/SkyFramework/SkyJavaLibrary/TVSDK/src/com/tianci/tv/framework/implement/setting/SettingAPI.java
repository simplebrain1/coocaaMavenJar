package com.tianci.tv.framework.implement.setting;

import com.tianci.tv.define.object.ChannelInfo;
import com.tianci.tv.define.object.Source;

import java.util.List;

/**
 * Created by hq on 2017/12/2.
 */

public abstract class SettingAPI {
    public boolean setBootsource(Source source) {
        return false;
    }

    public Source getBootsource() {
        return null;
    }

    public boolean setTvModeSource(Source source) {
        return false;
    }

    public Source getTvModeSource()  {
        return null;
    }

    public List<Source> getBootsourceList() {
        return null;
    }

    public boolean setBootChannel(ChannelInfo channelInfo) {
        return false;
    }

    public ChannelInfo getBootChannel() {
        return null;
    }

    public List<ChannelInfo> getBootChannelList() {
        return null;
    }

    public boolean setAutoSwitchSource(boolean on) {
        return false;
    }

    public boolean getAutoSwitchSource() {
        return false;
    }

    public boolean setSourceTypePurpose(Source source, int purpose) {
        return false;
    }

    public int getSourceTypePurpose(Source source) {
        return 0;
    }

    public boolean setSourceTypeName(Source source, String name) {
        return false;
    }

    public String getSourceTypeName(Source source) {
        return "";
    }

    public boolean setDisPlayerQuickKey(boolean isBack,int key){
        return false;
    }

    public int getDisPlayerQuickKey(boolean isBack){
        return 0;
    }

    public int[] getDisplayModes(Source source){return new int[0];};

    public int getDisplayMode(){return 0;};

    public boolean setDisplayMode(int mode){return false;}

    public boolean sourceRename(Source source,String newName){return false;}

    public String getSourceRename(Source source){
        return null;
    }
}


