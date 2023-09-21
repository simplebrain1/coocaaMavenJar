package com.skyworth.framework.impl;

import android.content.ContentProviderClient;
import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import com.skyworth.api.utils.LogApi;
import com.skyworth.framework.define.SystemDefines;
import com.skyworth.framework.data.ExternDiskInfo;
import com.skyworth.framework.data.Mode;
import com.skyworth.framework.skysdk.android.SkySystemUtil;
import com.skyworth.framework.skysdk.ipc.SkyApplication;
import com.skyworth.framework.skysdk.ipc.SkyCmdURI;
import com.skyworth.framework.skysdk.util.SkyData;
import com.skyworth.framework.utils.TransUtil;
import com.tianci.system.api.TCSettingApi;
import com.tianci.system.command.TCSystemCmd;
import com.tianci.system.data.TCEnumSetData;
import com.tianci.system.data.TCInfoSetData;
import com.tianci.system.data.TCRangeSetData;
import com.tianci.system.data.TCRetData;
import com.tianci.system.data.TCSetData;
import com.tianci.system.data.TCSetDataFactory;
import com.tianci.system.data.TCSwitchSetData;
import com.tianci.system.define.SkyConfigDefs;
import com.tianci.system.define.SystemProviderDefines;
import com.tianci.system.define.TCEnvKey;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class IPCServiceSystemApiImpl extends ISystemApi {
    private final static String TAG = "IPCServiceSystemApiImpl";
    private final static String KEY_RESULT = "ret";
    private final static String cmdHeader = "tianci://com.tianci.system/com.tianci.system.SystemService?cmd=";
    protected Context mContext;
    private ContentResolver mContentResolver;
    SkyApplication.SkyCmdConnectorListener mListener;

    public IPCServiceSystemApiImpl(Context context, SkyApplication.SkyCmdConnectorListener listener) {
        mContext = context;
        LogApi.w(TAG, "created");
        Context ctx = context.getApplicationContext();
        if (ctx == null) {
            ctx = context;
        }
        mContentResolver = ctx.getContentResolver();
        mListener = listener;
    }

    @Override
    public int getOledScreenFixState() {
        Bundle ret = null;
        int result = -1;
        try {
            ContentProviderClient client = mContentResolver.acquireUnstableContentProviderClient(
                    Uri.parse(SystemProviderDefines.URI_PATH_METHOD));
            if (client == null) {
                return -1;
            }
            ret = client.call(SystemProviderDefines.METHOD_AOD_SCREEN_FIX, null, null);
        } catch (Exception e) {
            LogApi.e(TAG, "getOledScreenFixState err=" + e.getMessage());
        }
        if (ret != null) {
            result = ret.getInt(KEY_RESULT);
        }
        LogApi.d(TAG, "getOledScreenFixState result=" + result);
        return result;
    }

    @Override
    public String getSystemVersion() {
        return getPropData(TCEnvKey.SKY_SYSTEM_ENV_TIANCI_VER);
    }

    @Override
    public String getBuildVersion() {
        return getPropData(TCEnvKey.SKY_SYSTEM_ENV_SOFT_VER);
    }

    @Override
    public String getDeviceType() {
        return getPropData(TCEnvKey.SKY_SYSTEM_ENV_TYPE);
    }

    @Override
    public String getDeviceModel() {
        return getPropData(TCEnvKey.SKY_SYSTEM_ENV_MODEL);
    }

    @Override
    public String getChipId() {
        return getPropData(TCEnvKey.SKY_SYSTEM_ENV_CHIPID);
    }

    @Override
    public String getBarcode() {
        return getPropData(TCEnvKey.SKY_SYSTEM_ENV_BARCODE);
    }

    @Override
    public String getMacAddress() {
        return getPropData(TCEnvKey.SKY_SYSTEM_ENV_MAC);
    }

    @Override
    public String getEmmcId() {
        return getPropData(SkyConfigDefs.SKY_CFG_EMMC_CID);
    }

    @Override
    public void setVoiceLogoControl(int mode, int arg) {
        SkyCmdURI cmdURI;
        try {
            SkyData data = new SkyData();
            data.add("mode", mode);
            data.add("arg", arg);
            cmdURI = new SkyCmdURI(cmdHeader
                    + TCSystemCmd.TC_SYSTEM_CMD_SET_VOICE_CONTROL_LOGO.toString());
            SkyApplication.sendCommand(mListener, cmdURI, data.toBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean setBacklight(int backlight) {
        try {
            TCRangeSetData data = new TCRangeSetData();
            data.setName(SkyConfigDefs.SKY_CFG_TV_MANUAL_BACKLIGHT_ADJUST);
            data.setCurrent(backlight);
            setData(data);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public int getBacklight() {
        try {
            TCSetData data = getSetData(SkyConfigDefs.SKY_CFG_TV_MANUAL_BACKLIGHT_ADJUST);
            int backLight = 0;
            if (data != null) {
                TCRangeSetData backLightData = (TCRangeSetData) data;
                backLight = backLightData.getCurrent();
            }
            return backLight;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public boolean setColor(int color) {
        try {
            TCRangeSetData data = new TCRangeSetData();
            data.setName(SkyConfigDefs.SKY_CFG_TV_COLOR);
            data.setCurrent(color);
            setData(data);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public int getColor() {
        try {
            TCSetData data = getSetData(SkyConfigDefs.SKY_CFG_TV_COLOR);
            int color = 0;
            if (data != null) {
                TCRangeSetData colorData = (TCRangeSetData) data;
                color = colorData.getCurrent();
            }
            return color;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public boolean getSportMode() {
        return getSwitchSetData(SkyConfigDefs.SKY_CFG_TV_SPORT_MODE_SWITCH, false);
    }

    @Override
    public boolean setSportMode(boolean mode) {
        return setSwitchSetData(SkyConfigDefs.SKY_CFG_TV_SPORT_MODE_SWITCH, mode);
    }

    @Override
    public List<Mode> getPictureModeList() {
        List<Mode> ret = null;
        if (mListener != null) {
            TCSetData setData = getSetData(SkyConfigDefs.SKY_CFG_TV_PICTURE_MODE);
            if (setData != null) {
                ret = new ArrayList<Mode>();
                List<String> modeStrList = ((TCEnumSetData) setData).getEnumList();
                for (String modeStr : modeStrList) {
                    SkyConfigDefs.SKY_CFG_TV_PICTURE_MODE_ENUM_TYPE emode = SkyConfigDefs.SKY_CFG_TV_PICTURE_MODE_ENUM_TYPE.valueOf(modeStr);
                    Mode mode = new Mode();
                    mode.id = TransUtil.tranPictureModeToLocal(emode);
                    ret.add(mode);
                }
            }
        }
        return ret;
    }

    @Override
    public Mode getPictureMode() {
        Mode ret = null;
        if (mListener != null) {
            TCSetData setData = getSetData(SkyConfigDefs.SKY_CFG_TV_PICTURE_MODE);
            if (setData != null) {
                SkyConfigDefs.SKY_CFG_TV_PICTURE_MODE_ENUM_TYPE emode = SkyConfigDefs.SKY_CFG_TV_PICTURE_MODE_ENUM_TYPE.valueOf(((TCEnumSetData) setData).getCurrent());
                ret = new Mode();
                ret.id = TransUtil.tranPictureModeToLocal(emode);
            }
        }
        return ret;
    }

    @Override
    public boolean setPictureMode(Mode mode) {
        try {
            TCEnumSetData data = new TCEnumSetData();
            data.setName(SkyConfigDefs.SKY_CFG_TV_PICTURE_MODE);
            SkyConfigDefs.SKY_CFG_TV_PICTURE_MODE_ENUM_TYPE emode = TransUtil.tranPictureModeToSdk(mode.id);
            data.setCurrent(emode.toString());
            setData(data);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public List<Mode> getSoundModeList() {
        List<Mode> ret = null;
        if (mListener != null) {
            TCSetData setData = getSetData(SkyConfigDefs.SKY_CFG_TV_SOUND_MODE);
            if (setData != null) {
                ret = new ArrayList<Mode>();
                List<String> modeStrList = ((TCEnumSetData) setData).getEnumList();
                for (String modeStr : modeStrList) {
                    SkyConfigDefs.SKY_CFG_TV_SOUND_MODE_ENUM_TYPE emode = SkyConfigDefs.SKY_CFG_TV_SOUND_MODE_ENUM_TYPE.valueOf(modeStr);
                    Mode mode = new Mode();
                    mode.id = TransUtil.tranSoundModeToLocal(emode);
                    ret.add(mode);
                }
            }
        }
        return ret;
    }

    @Override
    public Mode getSoundMode() {
        Mode ret = null;
        if (mListener != null) {
            TCSetData setData = getSetData(SkyConfigDefs.SKY_CFG_TV_SOUND_MODE);
            if (setData != null) {
                SkyConfigDefs.SKY_CFG_TV_SOUND_MODE_ENUM_TYPE emode = SkyConfigDefs.SKY_CFG_TV_SOUND_MODE_ENUM_TYPE.valueOf(((TCEnumSetData) setData).getCurrent());
                ret = new Mode();
                ret.id = TransUtil.tranSoundModeToLocal(emode);
            }
        }
        return ret;
    }

    @Override
    public boolean setSoundMode(Mode mode) {
        try {
            TCEnumSetData data = new TCEnumSetData();
            data.setName(SkyConfigDefs.SKY_CFG_TV_SOUND_MODE);
            SkyConfigDefs.SKY_CFG_TV_SOUND_MODE_ENUM_TYPE emode = TransUtil.tranSoundModeToSdk(mode.id);
            data.setCurrent(emode.toString());
            setData(data);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean getQuickDemoMode() {
        return getSwitchSetData(TCEnvKey.SKY_SYSTEM_ENV_QUICK_DEMO_MODE, false);
    }

    @Override
    public boolean setQuickDemoMode(boolean mode) {
        return getSwitchSetData(TCEnvKey.SKY_SYSTEM_ENV_QUICK_DEMO_MODE, mode);
    }

    @Override
    public String getPanelSize() {
        return getPropData(TCEnvKey.SKY_SYSTEM_ENV_PANEL_SIZE);
    }

    @Override
    public int getStreamType() {
        int ret = SystemDefines.STREAM_TYPE_UNKNOWN;
        if (mListener != null) {
            TCSetData setData = getSetData(SkyConfigDefs.SKY_CFG_TV_VIDEO_STREAM_TYPE);
            if (setData != null) {
                SkyConfigDefs.SKY_CFG_TV_VIDEO_STREAM_TYPE_ENUM_TYPE emode = SkyConfigDefs.SKY_CFG_TV_VIDEO_STREAM_TYPE_ENUM_TYPE.valueOf(((TCEnumSetData) setData).getCurrent());
                ret = TransUtil.tranStreamTypeToLocal(emode);
            }
        }
        return ret;
    }

    @Override
    public boolean setContentSceneMode(Mode mode) {
        try {
            TCEnumSetData data = new TCEnumSetData();
            data.setName(SkyConfigDefs.SKY_CFG_TV_CONTENT_SCENE_OTHER);
            data.setCurrentIndex(mode.id);
            setData(data);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private String getPropData(String configName) {
        String result = null;
        TCSetData setData = getSetData(configName);
        if (setData != null) {
            TCInfoSetData infoSetData = (TCInfoSetData) setData;
            result = infoSetData.getCurrent();
        }
        return result;
    }

    private boolean getSwitchSetData(String configName, boolean def) {
        boolean ret = def;
        if (mListener != null) {
            TCSetData setData = getSetData(configName);
            if (setData != null) {
                ret = ((TCSwitchSetData) setData).isOn();
            }
        }
        return ret;
    }

    private boolean setSwitchSetData(String configName, boolean value) {
        try {
            TCSwitchSetData data = new TCSwitchSetData();
            data.setName(configName);
            data.setOn(value);
            setData(data);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public String getSystemOwner() {
        return getPropData(SkyConfigDefs.SKY_CFG_TV_SYSTEM_OWNER);
    }

    public List<ExternDiskInfo> getExternalDisks() {
        List<ExternDiskInfo> path = new ArrayList<ExternDiskInfo>();
        TCEnumSetData data = (TCEnumSetData) getSetData(SkyConfigDefs.SKY_CFG_DISKINFO_SETTING_EXCEPT_INTERNAL_SDCARD);
        if (null == data) {
            data = (TCEnumSetData) getSetData(SkyConfigDefs.SKY_CFG_DISKINFO_SETTING);
        }
        if (data != null) {
            ArrayList<String> diskbyte = (ArrayList<String>) data.getEnumList();
            if (diskbyte != null && diskbyte.size() > 0) {
                for (String disk : diskbyte) {
                    SkySystemUtil.ExternDiskInfo info = new SkySystemUtil.ExternDiskInfo(disk);
                    ExternDiskInfo externDiskInfo = new ExternDiskInfo();
                    externDiskInfo.devPath = info.diskInfo.devPath;
                    externDiskInfo.path = info.diskInfo.path;
                    externDiskInfo.label = info.diskInfo.label;
                    externDiskInfo.format = info.diskInfo.format;
                    externDiskInfo.uuid = info.diskInfo.uuid;
                    externDiskInfo.totalSpace = info.diskInfo.totalSpace;
                    externDiskInfo.usedSpace = info.diskInfo.usedSpace;
                    externDiskInfo.availSpace = info.diskInfo.availSpace;
                    path.add(externDiskInfo);
                }
            }
        }
        return path;
    }

    @Override
    public void reboot() {
        try {
            TCInfoSetData rebootData = new TCInfoSetData();
            rebootData.setName(SkyConfigDefs.SKY_CFG_TV_REBOOT_SYSTEM);
            rebootData.setCurrent("");
            setData(rebootData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 概述：获取设置的值<br/>
     * 适用条件：应用程序需继承SkyService或SkyActiviy<br/>
     * 执行流程：<br/>
     * 使用方法：在系统中，数据类型被分为4大类，枚举数据类型(TCEnumSetData)、区域数据类型(TCRangeSetData)、开关数据类型(TCSwitchSetData)、
     * 单信息类型(TCInfoSetData)<br/>
     * 注意事项：1. 应用程序需继承SkyService或SkyActiviy 2. 获取TCSetData后，需getType判断此数据类型后，利用java多态性转换为实际类型使用 <br/>
     *
     * @return TCSetData
     * @date 2013-11-01
     */
    private TCSetData getSetData(String key) {
        SkyCmdURI cmdUrl;
        try {
            cmdUrl = new SkyCmdURI(cmdHeader + TCSystemCmd.TC_SYSTEM_CMD_GET_CONFIG.toString());
            byte[] ackData = SkyApplication.execCommand(mListener, cmdUrl, key.getBytes());
            if (ackData != null) {
                return TCSetDataFactory.createSetData(ackData);
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (SkyCmdURI.SkyCmdPathErrorException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 概述：设置系统数据<br/>
     * 适用条件：应用程序需继承SkyService或SkyActiviy<br/>
     * 执行流程：<br/>
     * 使用方法：在系统中，数据类型被分为4大类，枚举数据类型(TCEnumSetData)、区域数据类型(TCRangeSetData)、开关数据类型(TCSwitchSetData)、
     * 单信息类型(TCInfoSetData)<br/>
     * 注意事项：1. 应用程序需继承SkyService或SkyActiviy 2. 参数data需是实际的数据类型，并有调用setName和setCurrent设置真实的数据值。3.
     * 返回值TCRetData为返回专用值，可根据TCRetData. isSuccess()判断是否设置成功 <br/>
     *
     * @param data
     * @return TCRetData
     * @date 2013-11-01
     */
    private TCRetData setData(TCSetData data) {
        SkyCmdURI cmdUrl;
        try {
            cmdUrl = new SkyCmdURI(cmdHeader + TCSystemCmd.TC_SYSTEM_CMD_SET_CONFIG.toString());
            SkyApplication.execCommand(mListener, cmdUrl, data.toBytes());
            return new TCRetData(true);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (SkyCmdURI.SkyCmdPathErrorException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public List<String> getTemperatureColorList() {
        List<String> ret = new ArrayList<String>();
        if (mListener != null) {
            TCSetData setData = getSetData(SkyConfigDefs.SKY_CFG_TV_COLOR_TEMPERATURE);
            if (setData != null) {
               return  ((TCEnumSetData) setData).getEnumList();
            }
        }
        return ret;
    }

    @Override
    public boolean setTemperatureColor(String colorMode) {
        try {
            TCEnumSetData data = new TCEnumSetData();
            data.setName(SkyConfigDefs.SKY_CFG_TV_COLOR_TEMPERATURE);
            String mode = SkyConfigDefs.SKY_CFG_TV_COLOR_TEMPERATURE_ENUM_TYPE.valueOf(colorMode).name();
            data.setCurrent(mode);
            setData(data);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


    @Override
    public String getCurrentTemperatureColor() {
        String ret = "";
        if (mListener != null) {
            TCSetData setData = getSetData(SkyConfigDefs.SKY_CFG_TV_COLOR_TEMPERATURE);
            try {
                if (setData != null) {
                    return ((TCEnumSetData) setData).getCurrent();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return ret;
    }
}
