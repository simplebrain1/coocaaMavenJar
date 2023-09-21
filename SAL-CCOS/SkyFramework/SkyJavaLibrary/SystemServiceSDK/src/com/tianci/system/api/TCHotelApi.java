package com.tianci.system.api;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import com.skyworth.framework.skysdk.properties.SkySystemProperties;
import com.skyworth.framework.skysdk.util.SkyData;
import com.tianci.system.data.TCInfoSetData;
import com.tianci.system.data.TCRangeSetData;
import com.tianci.system.data.TCSetData;
import com.tianci.system.data.TCSwitchSetData;
import com.tianci.system.define.SkyConfigDefs;
import com.tianci.system.define.SystemProviderDefines;
import com.tianci.system.define.TCEnvKey;
import com.tianci.system.utils.ApiUtil;
import com.tianci.system.utils.ServiceUtil;
import com.tianci.utils.SkySSSLog;

import static com.tianci.system.utils.ApiUtil.KEY_PARAM;
import static com.tianci.system.utils.ServiceUtil.MENU_SELECT_INDEX_KEY;

public class TCHotelApi {
    private static final String TAG = "TCHotelApi";
    private static TCHotelApi sTCHotelApi;
    private final boolean isNewPlatform;
    private Context mContext;

    private TCHotelApi(Context context) {
        Context ctx = context.getApplicationContext();
        if (ctx == null) {
            ctx = context;
        }
        mContext = ctx;
        ApiUtil.setContext(ctx);
        isNewPlatform = ApiUtil.isNewPlatform();
    }

    public static TCHotelApi getInstance(Context context) {
        if (sTCHotelApi == null) {
            synchronized (TCHotelApi.class) {
                if (sTCHotelApi == null) {
                    sTCHotelApi = new TCHotelApi(context);
                }
            }
        }
        return sTCHotelApi;
    }

    /**
     * 设置AP待机开关
     *
     * @param mode:开；false:关
     */
    public boolean setAPStandby(boolean mode) {
        SkySSSLog.i(TAG, "setAPStandby mode=" + mode);
        Bundle bundle = new Bundle();
        Bundle param = new Bundle();
        param.putBoolean(KEY_PARAM, mode);
        ApiUtil.setParams(bundle, SystemProviderDefines.METHOD_HOTEL,
                SystemProviderDefines.METHOD_HOTEL_SET_AP_STANDBY, param, true);
        Boolean ret = ApiUtil.getData(bundle, Boolean.class);
        boolean result = false;
        if (ret != null) {
            result = ret;
        }
        SkySSSLog.i(TAG, "setAPStandby result=" + result);
        return result;
    }

    /**
     * 获取AP待机开关状态
     *
     * @return true：打开, false：关闭
     */
    public boolean getAPStandby() {
        Bundle bundle = new Bundle();
        ApiUtil.setParams(bundle, SystemProviderDefines.METHOD_HOTEL,
                SystemProviderDefines.METHOD_HOTEL_GET_AP_STANDBY, null, false);
        Boolean ret = ApiUtil.getData(bundle, Boolean.class);
        boolean result = false;
        if (ret != null) {
            result = ret;
        }
        SkySSSLog.i(TAG, "getAPStandby result=" + result);
        return result;
    }

    /**
     * 获取AP待机状态
     *
     * @return true:未待机；false:在待机
     */
    public boolean getAPStandbyState() {
        Bundle bundle = new Bundle();
        ApiUtil.setParams(bundle, SystemProviderDefines.METHOD_HOTEL,
                SystemProviderDefines.METHOD_HOTEL_GET_AP_STANDBY_STATE, null, false);
        Boolean ret = ApiUtil.getData(bundle, Boolean.class);
        boolean result = false;
        if (ret != null) {
            result = ret;
        }
        SkySSSLog.i(TAG, "getAPStandbyState result=" + result);
        return result;
    }

    /**
     * 是否支持AP待机
     *
     * @return true:支持；false:不支持
     */
    public boolean isSupportAPStandby() {
        Bundle bundle = new Bundle();
        ApiUtil.setParams(bundle, SystemProviderDefines.METHOD_HOTEL,
                SystemProviderDefines.METHOD_HOTEL_SUPPORT_AP_STANDBY, null, false);
        Boolean ret = ApiUtil.getData(bundle, Boolean.class);
        boolean result = false;
        if (ret != null) {
            result = ret;
        }
        SkySSSLog.i(TAG, "isSupportAPStandby result=" + result);
        return result;
    }

    /**
     * 用于AP待机开关
     */
    public boolean powerApStandby() {
        Bundle bundle = new Bundle();
        ApiUtil.setParams(bundle, SystemProviderDefines.METHOD_HOTEL,
                SystemProviderDefines.METHOD_HOTEL_POWER_AP_STANDBY, null, true);
        Boolean ret = ApiUtil.getData(bundle, Boolean.class);
        boolean result = false;
        if (ret != null) {
            result = ret;
        }
        SkySSSLog.i(TAG, "powerApStandby result=" + result);
        return result;
    }

    /**
     * 获取开机音量
     */
    public int getHotelBootVolume() {
        try {
            TCSetData maxVolumeData = getSetData(SkyConfigDefs.SKY_GFC_TV_HOTEL_BOOT_VOLUME);
            int val = 10;
            SkySSSLog.i(TAG, "TCHotelApi getHotelBootVolume = " + val);
            if (maxVolumeData != null) {
                TCRangeSetData volumeData = (TCRangeSetData) maxVolumeData;
                val = volumeData.getCurrent();
            }
            return val;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 10;
    }

    /**
     * 设置开机音量
     */
    public void setHotelBootVolume(int value) {
        try {
            TCRangeSetData volumeData = new TCRangeSetData();
            volumeData.setName(SkyConfigDefs.SKY_GFC_TV_HOTEL_BOOT_VOLUME);
            volumeData.setCurrent(value);
            SkySSSLog.i(TAG, "TCHotelApi setHotelBootVolume = " + value);
            setData(volumeData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 系统最大音量设置
     */
    public int getHotelMaxmunVolume() {
        try {
            TCSetData maxVolumeData = getSetData(SkyConfigDefs.SKY_CFG_TV_MAXIMUN_VOLUME);
            int current = 80;
            if (maxVolumeData != null) {
                TCRangeSetData volumeData = (TCRangeSetData) maxVolumeData;
                current = volumeData.getCurrent();
            }
            SkySSSLog.i(TAG, "TCHotelApi getHotelMaxmunVolume = " + current);
            return current;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 80;
    }

    /**
     * 设置系统最大音量
     */
    public void setHotelMaxmunVolume(int value) {
        try {
            TCRangeSetData volumeData = new TCRangeSetData();
            volumeData.setName(SkyConfigDefs.SKY_CFG_TV_MAXIMUN_VOLUME);
            volumeData.setCurrent(value);
            SkySSSLog.i(TAG, "TCHotelApi setHotelMaxmunVolume = " + value);
            setData(volumeData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 系统关机接口（深度待机）
     */
    public void startStandby() {
        try {
            SkySSSLog.i(TAG, "TCHotelApi startStandby");
            TCSwitchSetData powerData = new TCSwitchSetData();
            powerData.setName(SkyConfigDefs.SKY_CFG_TV_POWER_OFF);
            powerData.setOn(true);
            setData(powerData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 系统重启接口
     */
    public void rebootSystem() {
        try {
            SkySSSLog.i(TAG, "TCHotelApi rebootSystem");
            TCInfoSetData rebootData = new TCInfoSetData();
            rebootData.setName(SkyConfigDefs.SKY_CFG_TV_REBOOT_SYSTEM);
            rebootData.setCurrent("");
            setData(rebootData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 系统定时开机接口
     */
    public void setSystemAutoStartUp(String date, String time) {
        SkySSSLog.i(TAG, "TCHotelApi setSystemAutoStartUp date = " + date + ",time = " + time);
        TCInfoSetData infoSetData = new TCInfoSetData();
        infoSetData.setName(SkyConfigDefs.SKY_GFC_TV_HOTEL_AUTO_POWER_ON);
        SkyData currentData = new SkyData();
        currentData.add("date", date);
        currentData.add("time", time);
        infoSetData.setCurrent(currentData.toString());
        setData(infoSetData);
    }

    /**
     * 系统时间设置
     */
    public void setHotelSystemTime(String time) {
        SkySSSLog.i(TAG, "TCHotelApi setHotelSystemTime time = " + time);
        TCInfoSetData timeData = new TCInfoSetData();
        timeData.setName(SkyConfigDefs.SKY_GFC_TV_HOTEL_SYSTEM_TIME);
        SkyData currentData = new SkyData();
        currentData.add("time", time);
        timeData.setCurrent(currentData.toString());
        setData(timeData);
    }

    /**
     * 获取酒店机音量设置开关
     */
    public boolean getHotelVolumeSetting() {
        try {
            boolean value = false;
            TCSetData setData = getSetData(SkyConfigDefs.SKY_GFC_TV_HOTEL_VOLUME_SETTING_SWITCH);
            if (setData != null) {
                value = ((TCSwitchSetData) setData).isOn();
            }
            SkySSSLog.i(TAG, "TCHotelApi getHotelVolumeSetting value = " + value);
            return value;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 设置酒店机音量设置开关
     */
    public void setHotelVolumeSetting(boolean mode) {
        try {
            SkySSSLog.i(TAG, "TCHotelApi getHotelFunSwitch value = " + mode);
            TCSwitchSetData setData = new TCSwitchSetData();
            setData.setName(SkyConfigDefs.SKY_GFC_TV_HOTEL_VOLUME_SETTING_SWITCH);
            setData.setOn(mode);
            setData(setData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取定时开机功能开关
     */
    public boolean getHotelAutoStartupSwitch() {
        try {
            boolean value = false;
            TCSetData setData = getSetData(SkyConfigDefs.SKY_GFC_TV_HOTEL_AUTO_START_UP_SWITCH);
            if (setData != null) {
                value = ((TCSwitchSetData) setData).isOn();
            }
            SkySSSLog.i(TAG, "TCHotelApi getHotelVolumeSetting value = " + value);
            return value;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 设置定时开机功能开关
     */
    public void setHotelAutoStartupSwitch(boolean mode) {
        try {
            SkySSSLog.i(TAG, "TCHotelApi getHotelFunSwitch value = " + mode);
            TCSwitchSetData setData = new TCSwitchSetData();
            setData.setName(SkyConfigDefs.SKY_GFC_TV_HOTEL_AUTO_START_UP_SWITCH);
            setData.setOn(mode);
            setData(setData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取音量
     */
    public int getVolume() {
        try {
            TCSetData setData = getSetData(SkyConfigDefs.SKY_CFG_TV_VOLUME);
            int current = 100;
            if (setData != null) {
                TCRangeSetData data = (TCRangeSetData) setData;
                current = data.getCurrent();
            }
            SkySSSLog.i(TAG, "TCHotelApi getVolume = " + current);
            return current;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 设置音量
     */
    public void setVolume(int vol) {
        try {
            TCRangeSetData volumeData = new TCRangeSetData();
            volumeData.setName(SkyConfigDefs.SKY_CFG_TV_VOLUME);
            volumeData.setCurrent(vol);
            SkySSSLog.i(TAG, "TCHotelApi setVolume = " + vol);
            setData(volumeData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置是否打开屏幕
     * 关闭屏幕指的是：关屏，关背光，不影响声音 。与单独听的区别就是，单独听只关屏不关背光。
     *
     * @param onOff <br />
     *              true：打开屏幕, false：关闭屏幕
     */
    public boolean setPanelOnOff(boolean onOff) {
        try {
            SkySSSLog.i(TAG, "TCHotelApi setPanelOnOff onOff = " + onOff);
            TCSwitchSetData setData = new TCSwitchSetData();
            setData.setName(SkyConfigDefs.SKY_CFG_TV_PANEL_ON_OFF);
            setData.setOn(onOff);
            setData(setData);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 获取是否打开屏幕状态
     *
     * @return Boolean
     * true：屏幕打开状态, false：屏幕关闭状态
     */
    public boolean getScreenOn() {
        TCSwitchSetData screenData = (TCSwitchSetData) getSetData(SkyConfigDefs.SKY_CFG_TV_PANEL_ON_OFF);
        if (screenData != null) {
            boolean isOn = screenData.isOn();
            SkySSSLog.i(TAG, "TCHotelApi getScreenOn isOn = " + isOn);
            return isOn;
        }
        return false;
    }

    public boolean startSetting() {
        Intent intent = new Intent("android.settings.SETTINGS");
        boolean ret = true;
        try {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            mContext.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
            ret = false;
        }
        return ret;
    }

    /**
     * 第一张开机图片替换
     * 只能是jpg格式的图片
     */
    public Boolean setFirstBootLogo(String fileAbsolutePath) {
        SkySSSLog.i(TAG, "TCHotelApi setFirstBootLogo:" + fileAbsolutePath);
        if (TextUtils.isEmpty(fileAbsolutePath)) {
            return false;
        }
        String fileSuffix = fileAbsolutePath.substring(fileAbsolutePath.lastIndexOf(".") + 1);
        if (TextUtils.isEmpty(fileSuffix)) {
            return false;
        }
        if (!(fileSuffix.equalsIgnoreCase("jpg"))) {
            return false;
        }
        TCInfoSetData infoSetData = new TCInfoSetData();
        infoSetData.setName(SkyConfigDefs.SKY_GFC_TV_HOTEL_FIRST_BOOT_LOGO);
        SkyData currentData = new SkyData();
        currentData.add("fileName", fileAbsolutePath);
        infoSetData.setCurrent(currentData.toString());
        TCSetData ret = setData(infoSetData);
        return ret != null;
    }

    public boolean setSettingDisableState(boolean onOff) {
        try {
            SkySSSLog.i(TAG, "TCHotelApi setSettingDisableState onOff = " + onOff);
            TCSwitchSetData setData = new TCSwitchSetData();
            setData.setName(TCEnvKey.SKY_SYSTEM_ENV_SETTING_DISABLE);
            setData.setOn(onOff);
            setData(setData);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean getSettingDisableState() {
        try {
            boolean value = false;
            TCSetData setData = getSetData(TCEnvKey.SKY_SYSTEM_ENV_SETTING_DISABLE);
            if (setData != null) {
                value = ((TCSwitchSetData) setData).isOn();
            }
            SkySSSLog.i(TAG, "TCHotelApi getSettingDisableState value = " + value);
            return value;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private TCSetData getSetData(String key) {
        SkySSSLog.d(TAG, "getData key=" + key);
        if (isNewPlatform) {
            return TCSettingApi.getData(key);
        }
        return ServiceUtil.getSetData(key);
    }

    private TCSetData setData(TCSetData data) {
        SkySSSLog.d(TAG, "setData key=" + data.getName());
        if (isNewPlatform) {
            return TCSettingApi.setData(data);
        }
        return ServiceUtil.setData(data);
    }

    /**
     * 进入或退出AI待机
     */
    public void standbyAI() {
        Bundle bundle = new Bundle();
        ApiUtil.setParams(bundle, SystemProviderDefines.METHOD_COMMON,
                SystemProviderDefines.COMMON_STANDBY_AI, null, true);
        ApiUtil.setData(bundle);
        SkySSSLog.i(TAG, "standbyAI");
    }

    /**
     * 获取AI待机状态
     *
     * @return 0：正常；1：AI模式；2：待机中；3：开机中
     */
    public int getStandbyAIState() {
        String state = SkySystemProperties.getProperty("third.get.aistandbymode");
        SkySSSLog.i(TAG, "getStandbyAIState state=" + state);
        int result = 0;
        try {
            result = Integer.parseInt(state);
        } catch (NumberFormatException e) {
            SkySSSLog.e(TAG, "getStandbyAIState e=" + e.getMessage());
        }
        return result;
    }


  /**
     * 设置耳机默认音量
     */
    public boolean setEarphoneDefaultVolume(int volume) {
        Bundle bundle = new Bundle();
        Bundle param = new Bundle();
        param.putInt(MENU_SELECT_INDEX_KEY, volume);
        ApiUtil.setParams(bundle, SystemProviderDefines.METHOD_COMMON,
                SystemProviderDefines.COMMON_SET_GET_EARPHONE_DEFAULT_VOLUME, param, true);
        boolean ret = ApiUtil.setData(bundle);
        SkySSSLog.i(TAG, "setEarphoneDefaultVolume volume=" + volume + ",ret=" + ret);
        return ret;
    }

    /**
     * 获取耳机默认音量
     */
    public int getEarphoneDefaultVolume() {
        Bundle bundle = new Bundle();
        ApiUtil.setParams(bundle, SystemProviderDefines.METHOD_COMMON,
                SystemProviderDefines.COMMON_SET_GET_EARPHONE_DEFAULT_VOLUME, null, false);
        Integer ret = ApiUtil.getData(bundle, Integer.class);
        int volume = -1;
        SkySSSLog.i(TAG, "getEarphoneDefaultVolume ret=" + ret);
        if (ret != null) {
            volume = ret;
        }
        return volume;
    }

    /**
     * 设置定时开关机（只支持AI待机或假待机）
     *
     * @param time 如19：00，若为空会根据isOn取消定时关机或开机功能
     * @param isOn true设置定时开机；false设置定时关机
     */
    public void setPowerOnOffTimer(String time, boolean isOn) {
        Bundle bundle = new Bundle();
        Bundle param = new Bundle();
        param.putString(KEY_PARAM, time);
        param.putBoolean(MENU_SELECT_INDEX_KEY, isOn);
        ApiUtil.setParams(bundle, SystemProviderDefines.METHOD_HOTEL,
                SystemProviderDefines.METHOD_HOTEL_POWERONOFF_TIMER, param, true);
        boolean ret = ApiUtil.setData(bundle);
        SkySSSLog.i(TAG, "setPowerOnOffTimer time=" + time + ",isOn=" + isOn + ",ret=" + ret);
    }

    /**
     * 设置“AI待机开关”状态
     *
     * @param value true：AI待机开；false：AI待机关
     */
    public void setAISwitch(boolean value) {
        try {
            TCSwitchSetData data = new TCSwitchSetData();
            data.setName(SkyConfigDefs.SKY_CFG_TV_AI_CLOSE_SCREEN_SWITCH);
            data.setOn(value);
            SkySSSLog.i(TAG, "setAISwitch = " + value);
            setData(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取“AI待机开关”状态
     *
     * @return true：AI待机开；false：AI待机关
     */
    public boolean getAISwitch() {
        try {
            TCSwitchSetData data = (TCSwitchSetData) getSetData(SkyConfigDefs.SKY_CFG_TV_AI_CLOSE_SCREEN_SWITCH);
            SkySSSLog.i(TAG, "getAISwitch = " + data.isOn());
            return data.isOn();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     *
     * 是否支持ai待机
     * @return true 支持 false 不支持
     */
    public boolean isSupportAIStandby(){
        try {
            TCSetData data = getSetData(SkyConfigDefs.SKY_GFG_TV_IS_SUPPORT_AI_STANDBY);
            boolean isSupportAIStandby = false;
            if (data != null)
            {
                isSupportAIStandby = ((TCSwitchSetData) data).isOn();
            }
            SkySSSLog.i(TAG, "isSupportAIStandby = " + isSupportAIStandby);
            return isSupportAIStandby;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
