package com.skyworth.framework.impl;

import static com.tianci.system.utils.ApiUtil.KEY_PARAM;
import static com.tianci.system.utils.ApiUtil.KEY_PARAM_TYPE;
import static com.tianci.system.utils.ApiUtil.PARAM_RET_BYTES;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;

import com.skyworth.framework.data.ExternDiskInfo;
import com.skyworth.framework.data.Mode;
import com.skyworth.framework.define.SystemDefines;
import com.skyworth.framework.skysdk.android.SkySystemUtil;
import com.skyworth.framework.skysdk.util.SkyData;
import com.skyworth.framework.utils.SystemPropertiesUtil;
import com.skyworth.framework.utils.TransUtil;
import com.skyworth.framework.utils.internel.SystemUtil;
import com.skyworth.os.BuildInfo;
import com.tianci.system.api.TCSettingApi;
import com.tianci.system.command.TCSystemCmd;
import com.tianci.system.data.TCEnumSetData;
import com.tianci.system.data.TCInfoSetData;
import com.tianci.system.data.TCRangeSetData;
import com.tianci.system.data.TCRetData;
import com.tianci.system.data.TCSetData;
import com.tianci.system.data.TCSwitchSetData;
import com.tianci.system.define.SkyConfigDefs;
import com.tianci.system.define.TCEnvKey;
import com.tianci.system.utils.ApiUtil;

import java.util.ArrayList;
import java.util.List;

public class BootPathSystemApiImpl extends ISystemApi {
    private final static String PROP_DEMO_MODE = "persist.sys.sf_mode";

    public BootPathSystemApiImpl(Context context) {
        ApiUtil.setContext(context);
    }

    @Override
    public int getOledScreenFixState() {
        TCSetData data = TCSettingApi.getData(SkyConfigDefs.SKY_CFG_TV_OLED_FIX_STATE);
        if (data != null) {
            try {
                return Integer.parseInt(((TCInfoSetData) data).getCurrent());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return -1;
    }

    @Override
    public String getSystemVersion() {
        return BuildInfo.getSystemVersion();
    }

    @Override
    public String getBuildVersion() {
        return BuildInfo.BUILD_VERSION;
    }

    @Override
    public String getDeviceType() {
        return BuildInfo.TYPE;
    }

    @Override
    public String getDeviceModel() {
        return BuildInfo.MODEL;
    }

    @Override
    public String getChipId() {
        return BuildInfo.CHIP;
    }

    @Override
    public String getBarcode() {
        try {
            TCInfoSetData data = (TCInfoSetData) TCSettingApi.getData(TCEnvKey.SKY_SYSTEM_ENV_BARCODE);
            if (data != null) {
                return data.getCurrent();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    @Override
    public String getMacAddress() {
        try {
            TCInfoSetData data = (TCInfoSetData) TCSettingApi.getData(SkyConfigDefs.SKY_CFG_TV_UNIQUE_IDENTIFICATION_CODE);
            if (data != null) {
                return data.getCurrent();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    @Override
    public String getEmmcId() {
        try {
            TCInfoSetData data = (TCInfoSetData) TCSettingApi.getData(SkyConfigDefs.SKY_CFG_EMMC_CID);
            if (data != null) {
                return data.getCurrent();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    @Override
    public void setVoiceLogoControl(int mode, int arg) {
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_PARAM_TYPE, PARAM_RET_BYTES);
        SkyData data = new SkyData();
        data.add("mode", mode);
        data.add("arg", arg);
        bundle.putByteArray(KEY_PARAM, data.toBytes());
        ApiUtil.executeSystemCmd(TCSystemCmd.TC_SYSTEM_CMD_SET_VOICE_CONTROL_LOGO.toString(), bundle, true);
    }

    @Override
    public boolean setBacklight(int backlight) {
        TCRangeSetData data = new TCRangeSetData();
        data.setName(SkyConfigDefs.SKY_CFG_TV_MANUAL_BACKLIGHT_ADJUST);
        data.setCurrent(backlight);
        try {
            TCRetData ret = (TCRetData) TCSettingApi.setData(data);
            if (ret != null) {
                return ret.isSuccess();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public int getBacklight() {
        try {
            TCRangeSetData data = (TCRangeSetData) TCSettingApi.getData(SkyConfigDefs.SKY_CFG_TV_MANUAL_BACKLIGHT_ADJUST);
            if (data != null) {
                return data.getCurrent();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public boolean setColor(int color) {
        TCRangeSetData data = new TCRangeSetData();
        data.setName(SkyConfigDefs.SKY_CFG_TV_COLOR);
        data.setCurrent(color);
        try {
            TCRetData ret = (TCRetData) TCSettingApi.setData(data);
            if (ret != null) {
                return ret.isSuccess();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public int getColor() {
        try {
            TCRangeSetData data = (TCRangeSetData) TCSettingApi.getData(SkyConfigDefs.SKY_CFG_TV_COLOR);
            if (data != null) {
                return data.getCurrent();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public boolean getSportMode() {
        try {
            TCSetData setData = TCSettingApi.getData(SkyConfigDefs.SKY_CFG_TV_SPORT_MODE_SWITCH);
            if (setData != null) {
                return ((TCSwitchSetData) setData).isOn();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean setSportMode(boolean mode) {
        TCSwitchSetData data = new TCSwitchSetData();
        data.setName(SkyConfigDefs.SKY_CFG_TV_SPORT_MODE_SWITCH);
        data.setOn(mode);
        try {
            TCRetData ret = (TCRetData) TCSettingApi.setData(data);
            if (ret != null) {
                return ret.isSuccess();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    @Override
    public boolean isSupportXDR() {
        try {
            TCSetData setData = TCSettingApi.getData(SkyConfigDefs.SKY_CFG_IS_SUPPORT_XDR_MODE);
            if (setData != null) {
                return ((TCSwitchSetData) setData).isOn();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    @Override
    public boolean getXDRMode() {
        try {
            TCSetData setData = TCSettingApi.getData(SkyConfigDefs.SKY_CFG_TV_XDR_MODE);
            if (setData != null) {
                return ((TCSwitchSetData) setData).isOn();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean setXDRMode(boolean mode) {
        TCSwitchSetData data = new TCSwitchSetData();
        data.setName(SkyConfigDefs.SKY_CFG_TV_XDR_MODE);
        data.setOn(mode);
        try {
            TCRetData ret = (TCRetData) TCSettingApi.setData(data);
            if (ret != null) {
                return ret.isSuccess();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Mode> getPictureModeList() {
        TCSetData setData = TCSettingApi.getData(SkyConfigDefs.SKY_CFG_TV_PICTURE_MODE_OTHER);
        try {
            if (setData != null) {
                return (ArrayList<Mode>) ((TCEnumSetData) setData).getEnumSerialList();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<Mode>();
    }

    @Override
    public Mode getPictureMode() {
        TCSetData setData = TCSettingApi.getData(SkyConfigDefs.SKY_CFG_TV_PICTURE_MODE_OTHER);
        try {
            if (setData != null) {
                return (Mode) ((TCEnumSetData) setData).getCurSerializable();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean setPictureMode(Mode mode) {
        TCEnumSetData data = new TCEnumSetData();
        data.setName(SkyConfigDefs.SKY_CFG_TV_PICTURE_MODE_OTHER);
        data.setCurrentIndex(mode.id);
        try {
            TCRetData ret = (TCRetData) TCSettingApi.setData(data);
            if (ret != null) {
                return ret.isSuccess();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean setContentSceneMode(Mode mode) {
        TCEnumSetData data = new TCEnumSetData();
        data.setName(SkyConfigDefs.SKY_CFG_TV_CONTENT_SCENE_OTHER);
        data.setCurrentIndex(mode.id);
        try {
            TCRetData ret = (TCRetData) TCSettingApi.setData(data);
            if (ret != null) {
                return ret.isSuccess();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Mode getContentSceneMode() {
        TCSetData setData = TCSettingApi.getData(SkyConfigDefs.SKY_CFG_TV_CONTENT_SCENE_OTHER);
        try {
            if (setData != null) {
                return (Mode) ((TCEnumSetData) setData).getCurSerializable();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public List<Mode> getSoundModeList() {
        TCSetData setData = TCSettingApi.getData(SkyConfigDefs.SKY_CFG_TV_SOUND_MODE_OTHER);
        try {
            if (setData != null) {
                return (ArrayList<Mode>) ((TCEnumSetData) setData).getEnumSerialList();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<Mode>();
    }

    @Override
    public Mode getSoundMode() {
        TCSetData setData = TCSettingApi.getData(SkyConfigDefs.SKY_CFG_TV_SOUND_MODE_OTHER);
        try {
            if (setData != null) {
                return (Mode) ((TCEnumSetData) setData).getCurSerializable();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean setSoundMode(Mode mode) {
        TCEnumSetData data = new TCEnumSetData();
        data.setName(SkyConfigDefs.SKY_CFG_TV_SOUND_MODE_OTHER);
        data.setCurrentIndex(mode.id);
        TCSetData ret = TCSettingApi.setData(data);
        try {
            if (ret != null) {
                return ((TCRetData) ret).isSuccess();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean getQuickDemoMode() {
        return TextUtils.equals(SystemPropertiesUtil.getProperty(PROP_DEMO_MODE), "Other");
    }

    @Override
    public boolean setQuickDemoMode(boolean mode) {
        if (mode) {
            SystemPropertiesUtil.setProperty(PROP_DEMO_MODE, "Other");
        } else {
            SystemPropertiesUtil.setProperty(PROP_DEMO_MODE, "Default");
        }
        return true;
    }

    @Override
    public String getPanelSize() {
        return SystemUtil.getPanelSize();
    }

    @Override
    public int getStreamType() {
        TCSetData setData = TCSettingApi.getData(SkyConfigDefs.SKY_CFG_TV_VIDEO_STREAM_TYPE);
        try {
            if (setData != null) {
                SkyConfigDefs.SKY_CFG_TV_VIDEO_STREAM_TYPE_ENUM_TYPE emode = SkyConfigDefs.SKY_CFG_TV_VIDEO_STREAM_TYPE_ENUM_TYPE.valueOf(((TCEnumSetData) setData).getCurrent());
                return TransUtil.tranStreamTypeToLocal(emode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SystemDefines.STREAM_TYPE_UNKNOWN;
    }

    @Override
    public List<ExternDiskInfo> getExternalDisks() {
        List<ExternDiskInfo> path = new ArrayList<ExternDiskInfo>();
        TCEnumSetData data = null;
        try {
            data = (TCEnumSetData) TCSettingApi.getData(SkyConfigDefs.SKY_CFG_DISKINFO_SETTING_EXCEPT_INTERNAL_SDCARD);
            if (null == data) {
                data = (TCEnumSetData) TCSettingApi.getData(SkyConfigDefs.SKY_CFG_DISKINFO_SETTING);
            }
        } catch (Exception e) {
            e.printStackTrace();
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
    public boolean isSupportCareMode() {
        try {
            TCSetData setData = TCSettingApi.getData(SkyConfigDefs.SKY_CFG_TV_SUPPORT_CARE_MODE);
            if (setData != null) {
                return ((TCSwitchSetData) setData).isOn();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void reboot() {
        TCInfoSetData rebootData = new TCInfoSetData();
        rebootData.setName(SkyConfigDefs.SKY_CFG_TV_REBOOT_SYSTEM);
        rebootData.setCurrent("");
        TCSettingApi.setData(rebootData);
    }

    @Override
    public List<String> getTemperatureColorList() {
        TCSetData setData = TCSettingApi.getData(SkyConfigDefs.SKY_CFG_TV_COLOR_TEMPERATURE);
        try {
            if (setData != null) {
                return ((TCEnumSetData) setData).getEnumList();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<String>();
    }

    @Override
    public boolean setTemperatureColor(String colorMode) {
        TCEnumSetData data = new TCEnumSetData();
        data.setName(SkyConfigDefs.SKY_CFG_TV_COLOR_TEMPERATURE);
        try {
            String mode = SkyConfigDefs.SKY_CFG_TV_COLOR_TEMPERATURE_ENUM_TYPE.valueOf(colorMode).name();
            data.setCurrent(mode);
            TCRetData ret = (TCRetData) TCSettingApi.setData(data);
            if (ret != null) {
                return ret.isSuccess();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public String getCurrentTemperatureColor() {
        TCSetData setData = TCSettingApi.getData(SkyConfigDefs.SKY_CFG_TV_COLOR_TEMPERATURE);
        try {
            if (setData != null) {
                return ((TCEnumSetData) setData).getCurrent();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
