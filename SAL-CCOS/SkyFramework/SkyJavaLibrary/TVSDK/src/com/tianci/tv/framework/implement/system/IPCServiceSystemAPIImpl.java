package com.tianci.tv.framework.implement.system;

import android.content.Context;
import android.content.pm.PackageManager;

import com.skyworth.framework.skysdk.ipc.SkyApplication;
import com.skyworth.framework.skysdk.ipc.SkyCmdURI;
import com.tianci.media.defines.SystemApiParamResConflict;
import com.tianci.tv.api.system.SystemApiParamEnableExternalSource;
import com.tianci.tv.api.system.SystemApiParamsResetFactoryMode;
import com.tianci.tv.api.system.SystemApiParamsSetNeedShowBootGuidee;
import com.tianci.tv.define.SkyTvCommand;
import com.tianci.tv.define.SkyTvDefine;
import com.tianci.tv.define.object.Channel;
import com.tianci.tv.define.object.Source;
import com.tianci.tv.utils.SkyTvUtils;
import com.tianci.tv.utils.TVSDKDebug;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hq on 2017/12/2.
 */

public class IPCServiceSystemAPIImpl extends SystemAPI {
    private SkyApplication.SkyCmdConnectorListener listener;

    public IPCServiceSystemAPIImpl(SkyApplication.SkyCmdConnectorListener listener) {
        this.listener = listener;
    }

    @Override
    public boolean switchSource(Source source) {
        try {
            String cmd = SkyTvCommand.getCmd(SkyTvCommand.TV_CMD.TV_CMD_SYSTEM_SWITCHSOURCE.toString());
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
    public boolean switchSourceBackGround(Source source) {
        try {
            String cmd = SkyTvCommand.getCmd(SkyTvCommand.TV_CMD.TV_CMD_SYSTEM_SWITCHSOURCE_BACKGROUND.toString());
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
    public Source getCurrentSource() {
        try {
            String cmd = SkyTvCommand.getCmd(SkyTvCommand.TV_CMD.TV_CMD_SYSTEM_GET_CURRENTSOURCE.toString());
            SkyCmdURI uri = new SkyCmdURI(cmd);
            byte[] bytes = SkyApplication.execCommand(listener, uri, null);
            Source source = SkyTvUtils.toObject(bytes, Source.class);
            return source;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Source> getSourceList() {
        List<Source> ret = new ArrayList<Source>();
        try {
            String cmd = SkyTvCommand.getCmd(SkyTvCommand.TV_CMD.TV_CMD_SYSTEM_GET_SOURCELIST.toString());
            SkyCmdURI uri = new SkyCmdURI(cmd);
            byte[] bytes = SkyApplication.execCommand(listener, uri, null);
            ret = (List<Source>) SkyTvUtils.toObject(bytes, List.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

    @Override
    public boolean isReady() {
        try {
            String cmd = SkyTvCommand.getCmd(SkyTvCommand.TV_CMD.TV_CMD_SYSTEM_ISREADY.toString());
            SkyCmdURI uri = new SkyCmdURI(cmd);
            byte[] bytes = SkyApplication.execCommand(listener, uri, null);
            String sss = String.valueOf(bytes);
            Boolean ret = SkyTvUtils.toObject(bytes, Boolean.class);
            return ret;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean isReleased() {
        try {
            String cmd = SkyTvCommand.getCmd(SkyTvCommand.TV_CMD.TV_CMD_SYSTEM_ISRELEASED.toString());
            SkyCmdURI uri = new SkyCmdURI(cmd);
            byte[] bytes = SkyApplication.execCommand(listener, uri, null);
            Boolean b = SkyTvUtils.toObject(bytes, Boolean.class);
            return b;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean releaseAllWithStandby() {
        try {
            String cmd = SkyTvCommand.getCmd(SkyTvCommand.TV_CMD.TV_CMD_SYSTEM_RELEASE_ALL.toString());
            SkyCmdURI uri = new SkyCmdURI(cmd);
            SkyApplication.sendCommand(listener, uri, null);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean backToTv() {
        try {
            String cmd = SkyTvCommand.getCmd(SkyTvCommand.TV_CMD.TV_CMD_SYSTEM_BACKTO_TV.toString());
            SkyCmdURI uri = new SkyCmdURI(cmd);
            byte[] bytes = SkyApplication.execCommand(listener, uri, null);
            Boolean b = SkyTvUtils.toObject(bytes, Boolean.class);
            return b;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean backToTvSource() {
        try {
            String cmd = SkyTvCommand.getCmd(SkyTvCommand.TV_CMD.TV_CMD_SYSTEM_BACKTO_TVSOURCE.toString());
            SkyCmdURI uri = new SkyCmdURI(cmd);
            byte[] bytes = SkyApplication.execCommand(listener, uri, null);
            Boolean b = SkyTvUtils.toObject(bytes, Boolean.class);
            return b;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean canCaptureScreen() {
        try {
            String cmd = SkyTvCommand.getCmd(SkyTvCommand.TV_CMD.TV_CMD_SYSTEM_CAN_CAPTURE_SCREEN.toString());
            SkyCmdURI uri = new SkyCmdURI(cmd);
            byte[] bytes = SkyApplication.execCommand(listener, uri, null);
            Boolean b = SkyTvUtils.toObject(bytes, Boolean.class);
            return b;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean setNeedShowBootGuide(boolean setValue) {
        try {
            SystemApiParamsSetNeedShowBootGuidee params = new SystemApiParamsSetNeedShowBootGuidee(setValue);
            String cmd = SkyTvCommand.getCmd(SkyTvCommand.TV_CMD.TV_CMD_SYSTEM_SET_NEEDSHOW_BOOTGUIDE.toString());
            SkyCmdURI uri = new SkyCmdURI(cmd);
            byte[] bytes = SkyApplication.execCommand(listener, uri, SkyTvUtils.toBytes(params));
            Boolean b = SkyTvUtils.toObject(bytes, Boolean.class);
            return b;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public int setTimeUpdateFlag(int flag) {
        try {
            String cmd = SkyTvCommand.getCmd(SkyTvCommand.TV_CMD.TV_CMD_DTV_SET_TIMEUPDATEFLAG.toString());
            SkyCmdURI uri = new SkyCmdURI(cmd);
            byte[] bytes = SkyApplication.execCommand(listener, uri, SkyTvUtils.toBytes(Integer.valueOf(flag)));
            int value = SkyTvUtils.toObject(bytes, Integer.class);
            return value;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public boolean isTvVideoFocused() {
        try {
            String cmd = SkyTvCommand.getCmd(SkyTvCommand.TV_CMD.TV_CMD_SYSTEM_TVVIDEO_ISFOCUSED.toString());
            SkyCmdURI uri = new SkyCmdURI(cmd);
            byte[] bytes = SkyApplication.execCommand(listener, uri, null);
            Boolean b = SkyTvUtils.toObject(bytes, Boolean.class);
            return b;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public SkyTvDefine.SOURCE_SIGNAL_STATE signalState(Source source) {
        try {
            String cmd = SkyTvCommand.getCmd(SkyTvCommand.TV_CMD.TV_CMD_SYSTEM_HASSIGNAL.toString());
            SkyCmdURI uri = new SkyCmdURI(cmd);
            byte[] bytes = SkyApplication.execCommand(listener, uri, SkyTvUtils.toBytes(source));
            SkyTvDefine.SOURCE_SIGNAL_STATE signalState = SkyTvUtils.toObject(bytes, SkyTvDefine.SOURCE_SIGNAL_STATE.class);
            return signalState;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SkyTvDefine.SOURCE_SIGNAL_STATE.NOSIGNAL;
    }

    @Override
    public boolean canPopupInteractionNow() {
        TVSDKDebug.debug("canPopupInteractionNow ");
        try {
            String cmd = SkyTvCommand.getCmd(SkyTvCommand.TV_CMD.TV_CMD_SYSTEM_CAN_POPUP_INTERACTION.toString());
            SkyCmdURI uri = new SkyCmdURI(cmd);
            byte[] bytes = SkyApplication.execCommand(listener, uri, null);
            Boolean b = SkyTvUtils.toObject(bytes, Boolean.class);
            return b;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean isInteractionEnable() {
        try {
            String cmd = SkyTvCommand.getCmd(SkyTvCommand.TV_CMD.TV_CMD_SYSTEM_INTERACTION_ISENABLED.toString());
            SkyCmdURI uri = new SkyCmdURI(cmd);
            byte[] bytes = SkyApplication.execCommand(listener, uri, null);
            Boolean b = SkyTvUtils.toObject(bytes, Boolean.class);
            TVSDKDebug.debug("isInteractionEnable:" + b);
            return b;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean registerSystemListener() {
        try {
            String cmd = SkyTvCommand.getCmd(SkyTvCommand.TV_CMD.TV_CMD_SYSTEM_REGISTER_SYSTEM_LISTENER.toString());
            SkyCmdURI uri = new SkyCmdURI(cmd);
            byte[] bytes = SkyApplication.execCommand(listener, uri, null);
            Boolean b = SkyTvUtils.toObject(bytes, Boolean.class);
            return b;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean unregisterSystemListener() {
        try {
            String cmd = SkyTvCommand.getCmd(SkyTvCommand.TV_CMD.TV_CMD_SYSTEM_UNREGISTER_SYSTEM_LISTENER.toString());
            SkyCmdURI uri = new SkyCmdURI(cmd);
            byte[] bytes = SkyApplication.execCommand(listener, uri, null);
            Boolean b = SkyTvUtils.toObject(bytes, Boolean.class);
            return b;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean restorePresetChannel(Context context) {
        boolean ret = false;
        if (context == null) {
            return ret;
        }
        try {
            PackageManager pm = context.getPackageManager();
            TVSDKDebug.debug(context.getPackageName() + " uid == " + context.getApplicationInfo().uid);
            if (context.getApplicationInfo().uid != android.os.Process.SYSTEM_UID) {
                TVSDKDebug.debug("context " + context.getPackageName() + " SharedUserID is " + context.getApplicationInfo().uid + " not match android.uid.system ");
                return ret;
            }
            String cmd = SkyTvCommand.getCmd(SkyTvCommand.TV_CMD.TV_CMD_SYSTEM_RESTORE_PRESETCHANNEL.toString());
            SkyCmdURI uri = new SkyCmdURI(cmd);
            byte[] bytes = SkyApplication.execCommand(listener, uri, null);
            Boolean b = SkyTvUtils.toObject(bytes, Boolean.class);
            return b;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Source> getExternalSourceList() {
        List<Source> ret = new ArrayList<Source>();
        try {
            String cmd = SkyTvCommand.getCmd(SkyTvCommand.TV_CMD.TV_CMD_SYSTEM_GET_EXTERNAL_SOURCELIST.toString());
            SkyCmdURI uri = new SkyCmdURI(cmd);
            byte[] bytes = SkyApplication.execCommand(listener, uri, null);
            ret = (List<Source>) SkyTvUtils.toObject(bytes, List.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

    @Override
    public boolean enableExternalSource(Source source, boolean val) {
        try {
            SystemApiParamEnableExternalSource params = new SystemApiParamEnableExternalSource(source, val);
            String cmd = SkyTvCommand.getCmd(SkyTvCommand.TV_CMD.TV_CMD_SYSTEM_ENABLE_EXTERNAL_SOURCE.toString());
            SkyCmdURI uri = new SkyCmdURI(cmd);
            byte[] bytes = SkyApplication.execCommand(listener, uri, SkyTvUtils.toBytes(params));
            Boolean b = SkyTvUtils.toObject(bytes, Boolean.class);
            return b;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean getExternalSourceEnable(Source source) {
        try {
            String cmd = SkyTvCommand.getCmd(SkyTvCommand.TV_CMD.TV_CMD_SYSTEM_GET_EXTERNAL_SOURCE_ENABLE.toString());
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
    public boolean sourceUp() {
        try {
            String cmd = SkyTvCommand.getCmd(SkyTvCommand.TV_CMD.TV_CMD_SYSTEM_SOURCEUP.toString());
            SkyCmdURI uri = new SkyCmdURI(cmd);
            byte[] bytes = SkyApplication.execCommand(listener, uri, null);
            Boolean b = SkyTvUtils.toObject(bytes, Boolean.class);
            return b;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean sourceDown() {
        try {
            String cmd = SkyTvCommand.getCmd(SkyTvCommand.TV_CMD.TV_CMD_SYSTEM_SOURCEDOWN.toString());
            SkyCmdURI uri = new SkyCmdURI(cmd);
            byte[] bytes = SkyApplication.execCommand(listener, uri, null);
            Boolean b = SkyTvUtils.toObject(bytes, Boolean.class);
            return b;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean switchChannel(Channel channel) {
        try {
            String cmd = SkyTvCommand.getCmd(SkyTvCommand.TV_CMD.TV_CMD_SYSTEM_SWITCHCHANNEL.toString());
            SkyCmdURI uri = new SkyCmdURI(cmd);
            byte[] bytes = SkyApplication.execCommand(listener, uri, SkyTvUtils.toBytes(channel));
            Boolean b = SkyTvUtils.toObject(bytes, Boolean.class);
            return b;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Channel getCurrentChannel() {
        try {
            String cmd = SkyTvCommand.getCmd(SkyTvCommand.TV_CMD.TV_CMD_SYSTEM_GET_CURRENTCHANNEL.toString());
            SkyCmdURI uri = new SkyCmdURI(cmd);
            byte[] bytes = SkyApplication.execCommand(listener, uri, null);
            if (bytes == null) {
                return null;
            }
            Channel channel = SkyTvUtils.toObject(bytes, Channel.class);
            return channel;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean isSwitchSource() {
        try {
            String cmd = SkyTvCommand.getCmd(SkyTvCommand.TV_CMD.TV_CMD_SYSTEM_ISSWITCHSOURCE.toString());
            SkyCmdURI uri = new SkyCmdURI(cmd);
            byte[] bytes = SkyApplication.execCommand(listener, uri, null);
            String sss = String.valueOf(bytes);
            Boolean ret = SkyTvUtils.toObject(bytes, Boolean.class);
            return ret;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean isSwitchChannel() {
        try {
            String cmd = SkyTvCommand.getCmd(SkyTvCommand.TV_CMD.TV_CMD_SYSTEM_ISSWITCHCHANNEL.toString());
            SkyCmdURI uri = new SkyCmdURI(cmd);
            byte[] bytes = SkyApplication.execCommand(listener, uri, null);
            String sss = String.valueOf(bytes);
            Boolean ret = SkyTvUtils.toObject(bytes, Boolean.class);
            return ret;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean isSearchingChannel() {
        try {
            String cmd = SkyTvCommand.getCmd(SkyTvCommand.TV_CMD.TV_CMD_SYSTEM_ISSEARCHCHANNEL.toString());
            SkyCmdURI uri = new SkyCmdURI(cmd);
            byte[] bytes = SkyApplication.execCommand(listener, uri, null);
            String sss = String.valueOf(bytes);
            Boolean ret = SkyTvUtils.toObject(bytes, Boolean.class);
            return ret;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void getSignalStateAsync(Source source) {
        try {
            String cmd = SkyTvCommand.getCmd(SkyTvCommand.TV_CMD.TV_CMD_SYSTEM_HASSIGNAL_ASYNC.toString());
            SkyCmdURI uri = new SkyCmdURI(cmd);
            SkyApplication.sendCommand(listener, uri, SkyTvUtils.toBytes(source));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean resetToFactoryMode(boolean restorePresetChannel) {
        TVSDKDebug.debug("resetToFactoryMode restorePresetChannel:" + restorePresetChannel);
        try {
            SystemApiParamsResetFactoryMode params = new SystemApiParamsResetFactoryMode(restorePresetChannel);
            String cmd = SkyTvCommand.getCmd(SkyTvCommand.TV_CMD.TV_CMD_SYSTEM_RESET_TO_FACTORYMODE.toString());
            SkyCmdURI uri = new SkyCmdURI(cmd);
            byte[] bytes = SkyApplication.execCommand(listener, uri, SkyTvUtils.toBytes(params));
            Boolean b = SkyTvUtils.toObject(bytes, Boolean.class);
            return b;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    // 20180829 add 
    @Override
    public boolean forceReleaseSource() {
        try
        {
            SystemApiParamResConflict params = new SystemApiParamResConflict();
            params.isPlayer = true;
            String cmd = SkyTvCommand.getCmd(SkyTvCommand.TV_CMD.TV_CMD_SYSTEM_RESOURCE_CONFLICT_SOLVE
                    .toString());
            SkyCmdURI uri = new SkyCmdURI(cmd);
            byte[] bytes = SkyApplication.execCommand(listener, uri, SkyTvUtils.toBytes(params));
            Boolean b = SkyTvUtils.toObject(bytes, Boolean.class);
            return b;
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }
    
    @Override
    public void setStandbyReason(int reason) {
        try
        {
            String cmd = SkyTvCommand.getCmd(SkyTvCommand.TV_CMD.TV_CMD_SYSTEM_SET_STANDBY_REASON
                    .toString());
            SkyCmdURI uri = new SkyCmdURI(cmd);
            SkyApplication.execCommand(listener, uri, SkyTvUtils.toBytes(Integer.valueOf(reason)));
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return;
    }
   
    @Override
    public boolean isOfflineCheckSupported(){
        try {
        	String cmd = SkyTvCommand.getCmd(SkyTvCommand.TV_CMD.TV_CMD_SYSTEM_IS_OFFLINECHECK_SUPPORTED.toString());
        	SkyCmdURI uri = new SkyCmdURI(cmd);
        	byte[] bytes = SkyApplication.execCommand(listener, uri, null);
        	String sss = String.valueOf(bytes);
        	Boolean ret = SkyTvUtils.toObject(bytes, Boolean.class);
            return ret;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
   }
}