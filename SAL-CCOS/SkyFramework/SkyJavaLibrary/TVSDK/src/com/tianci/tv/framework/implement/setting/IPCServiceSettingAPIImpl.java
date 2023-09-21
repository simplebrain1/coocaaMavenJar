package com.tianci.tv.framework.implement.setting;

import com.skyworth.framework.skysdk.ipc.SkyApplication;
import com.skyworth.framework.skysdk.ipc.SkyCmdURI;
import com.tianci.tv.define.SkyTvCommand;
import com.tianci.tv.define.SkyTvCommand.TV_CMD;
import com.tianci.tv.define.object.ChannelInfo;
import com.tianci.tv.define.object.Source;
import com.tianci.tv.utils.SkyTvUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hq on 2017/12/2.
 */

public class IPCServiceSettingAPIImpl extends SettingAPI {
    private SkyApplication.SkyCmdConnectorListener listener;

    public IPCServiceSettingAPIImpl(SkyApplication.SkyCmdConnectorListener listener) {
        this.listener = listener;
    }

    @Override
    public boolean setBootsource(Source source) {
        try {
            String cmd = SkyTvCommand.getCmd(SkyTvCommand.TV_CMD.TV_CMD_SETTING_SET_BOOTSOURCE.toString());
            SkyCmdURI uri = new SkyCmdURI(cmd);
            byte[] bytes = SkyApplication.execCommand(listener, uri, SkyTvUtils.toBytes(source));
            Boolean b = SkyTvUtils.toObject(bytes, Boolean.class);
            return b;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Source getBootsource() {
        Source source = null;
        try {
            String cmd = SkyTvCommand.getCmd(SkyTvCommand.TV_CMD.TV_CMD_SETTING_GET_BOOTSOURCE.toString());
            SkyCmdURI uri = new SkyCmdURI(cmd);
            byte[] bytes = SkyApplication.execCommand(listener, uri, null);
            source = SkyTvUtils.toObject(bytes, Source.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return source;
    }

    @Override
    public List<Source> getBootsourceList() {
        List<Source> ret = new ArrayList<Source>();
        try {
            String cmd = SkyTvCommand.getCmd(SkyTvCommand.TV_CMD.TV_CMD_SETTING_GET_BOOTSOURCELIST.toString());
            SkyCmdURI uri = new SkyCmdURI(cmd);
            byte[] bytes = SkyApplication.execCommand(listener, uri, null);
            ret = (List<Source>) SkyTvUtils.toObject(bytes, List.class);
            for (Source source : ret)
                source.afterDeserialize();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }
    @Override
    public boolean setBootChannel(ChannelInfo channelInfo){
        try {
            String cmd = SkyTvCommand.getCmd(SkyTvCommand.TV_CMD.TV_CMD_SETTING_SET_BOOTCHANNEL.toString());
            SkyCmdURI uri = new SkyCmdURI(cmd);
            byte[] bytes;
            if(channelInfo ==null){
            	bytes = SkyApplication.execCommand(listener, uri, new byte[0]);
            }else {
            	bytes = SkyApplication.execCommand(listener, uri, SkyTvUtils.toBytes(channelInfo));
            }
            Boolean b = SkyTvUtils.toObject(bytes, Boolean.class);
            return b;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    	
    }
    @Override
    public ChannelInfo getBootChannel(){
    	ChannelInfo channelInfo = null;
        try {
            String cmd = SkyTvCommand.getCmd(SkyTvCommand.TV_CMD.TV_CMD_SETTING_GET_BOOTCHANNEL.toString());
            SkyCmdURI uri = new SkyCmdURI(cmd);
            byte[] bytes = SkyApplication.execCommand(listener, uri, null);
            channelInfo = SkyTvUtils.toObject(bytes, ChannelInfo.class);
            return channelInfo;
        } catch (Exception e) {
            e.printStackTrace();
            channelInfo = null;
        }
        return channelInfo;
    }
    
    @Override
    public List<ChannelInfo> getBootChannelList()
    {
        try
        {
            String cmd = SkyTvCommand.getCmd(TV_CMD.TV_CMD_SETTING_GET_BOOTCHANNEL_LIST.toString());
            SkyCmdURI uri = new SkyCmdURI(cmd);
            byte[] bytes = SkyApplication.execCommand(listener, uri,null);
            List<ChannelInfo> ret = (List<ChannelInfo>) SkyTvUtils.toObject(bytes, List.class);
            return ret;
        } catch (Exception e)
        {
            e.printStackTrace();
            return new ArrayList<ChannelInfo>();
        }
    }
}
