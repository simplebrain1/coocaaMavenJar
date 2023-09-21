package com.tianci.system.api;

import android.content.Context;
import android.text.TextUtils;

import com.skyworth.framework.skysdk.util.SkyData;
import com.tianci.system.data.TCInfoSetData;
import com.tianci.system.data.TCRangeSetData;
import com.tianci.system.data.TCSetData;
import com.tianci.system.data.TCSwitchSetData;
import com.tianci.system.define.SkyConfigDefs;
import com.tianci.system.utils.ApiUtil;
import com.tianci.system.utils.ServiceUtil;
import com.tianci.system.utils.SysLog;

/**
 * <p>
 * Description:此接口提供系统数据接口给到第三方应用
 * </p>
 *
 * @Date : 2019/5/21
 * @Author : WJ
 * @Description :
 */
public class TCHotelSystemService {
    public static TCHotelSystemService instance = null;
    private TCHotelApi tcHotelApi;

    public static TCHotelSystemService getInstance(Context context) {
        synchronized (TCHotelSystemService.class) {
            if (instance == null) {
                instance = new TCHotelSystemService(context);
            }
            return instance;
        }
    }

    private TCHotelSystemService(Context context) {
        if (ApiUtil.isNewPlatform()) {
            tcHotelApi = TCHotelApi.getInstance(context);
        }
    }

    /**
     * 获取开机音量
     */
    public int getHotelBootVolume() {
        if (tcHotelApi != null) {
            return tcHotelApi.getHotelBootVolume();
        }
        try {
            TCSetData maxVolumeData = getConfigData(SkyConfigDefs.SKY_GFC_TV_HOTEL_BOOT_VOLUME);
            int val = 10;
            SysLog.info("TCHotelSystemService getHotelBootVolume = " + val);
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
        if (tcHotelApi != null) {
            tcHotelApi.setHotelBootVolume(value);
            return;
        }
        try {
            TCRangeSetData volumeData = new TCRangeSetData();
            volumeData.setName(SkyConfigDefs.SKY_GFC_TV_HOTEL_BOOT_VOLUME);
            volumeData.setCurrent(value);
            SysLog.info("TCHotelSystemService setHotelBootVolume = " + value);
            ServiceUtil.setData(volumeData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 系统最大音量设置
     */
    public int getHotelMaxmunVolume() {
        if (tcHotelApi != null) {
            return tcHotelApi.getHotelMaxmunVolume();
        }
        try {
            TCSetData maxVolumeData = getConfigData(SkyConfigDefs.SKY_CFG_TV_MAXIMUN_VOLUME);
            int current = 80;
            if (maxVolumeData != null) {
                TCRangeSetData volumeData = (TCRangeSetData) maxVolumeData;
                current = volumeData.getCurrent();
            }
            SysLog.info("TCHotelSystemService getHotelMaxmunVolume = " + current);
            return current;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 80;
    }

    /**
     * 系统最大音量获取
     */
    public void setHotelMaxmunVolume(int value) {
        if (tcHotelApi != null) {
            tcHotelApi.setHotelBootVolume(value);
            return;
        }
        try {
            TCRangeSetData volumeData = new TCRangeSetData();
            volumeData.setName(SkyConfigDefs.SKY_CFG_TV_MAXIMUN_VOLUME);
            volumeData.setCurrent(value);
            SysLog.info("TCHotelSystemService setHotelMaxmunVolume = " + value);
            ServiceUtil.setData(volumeData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 系统关机接口（深度待机）
     */
    public void startStandby() {
        if (tcHotelApi != null) {
            tcHotelApi.startStandby();
            return;
        }
        try {
            SysLog.info("TCHotelSystemService startStandby");
            TCSwitchSetData powerData = new TCSwitchSetData();
            powerData.setName(SkyConfigDefs.SKY_CFG_TV_POWER_OFF);
            powerData.setOn(true);
            ServiceUtil.setData(powerData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 系统重启接口
     */
    public void rebootSystem() {
        if (tcHotelApi != null) {
            tcHotelApi.rebootSystem();
            return;
        }
        try {
            SysLog.info("TCHotelSystemService rebootSystem");
            TCInfoSetData rebootData = new TCInfoSetData();
            rebootData.setName(SkyConfigDefs.SKY_CFG_TV_REBOOT_SYSTEM);
            rebootData.setCurrent("");
            ServiceUtil.setData(rebootData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 系统定时开机接口
     */
    public void setSystemAutoStartUp(String date, String time) {
        if (tcHotelApi != null) {
            tcHotelApi.setSystemAutoStartUp(date, time);
            return;
        }
        SysLog.info("TCHotelSystemService setSystemAutoStartUp date = " + date + ",time = " + time);
        TCInfoSetData infoSetData = new TCInfoSetData();
        infoSetData.setName(SkyConfigDefs.SKY_GFC_TV_HOTEL_AUTO_POWER_ON);
        SkyData currentData = new SkyData();
        currentData.add("date", date);
        currentData.add("time", time);
        infoSetData.setCurrent(currentData.toString());
        ServiceUtil.setData(infoSetData);
    }

    /**
     * 系统时间设置
     */
    public void setHotelSystemTime(String time) {
        if (tcHotelApi != null) {
            tcHotelApi.setHotelSystemTime(time);
            return;
        }
        SysLog.info("TCHotelSystemService setHotelSystemTime time = " + time);
        TCInfoSetData timeData = new TCInfoSetData();
        timeData.setName(SkyConfigDefs.SKY_GFC_TV_HOTEL_SYSTEM_TIME);
        SkyData currentData = new SkyData();
        currentData.add("time", time);
        timeData.setCurrent(currentData.toString());
        ServiceUtil.setData(timeData);
    }

    /**
     * 获取酒店机音量设置开关
     */
    public boolean getHotelVolumeSetting() {
        if (tcHotelApi != null) {
            return tcHotelApi.getHotelVolumeSetting();
        }
        try {
            boolean value = false;
            TCSetData setData = getConfigData(SkyConfigDefs.SKY_GFC_TV_HOTEL_VOLUME_SETTING_SWITCH);
            if (setData != null) {
                value = ((TCSwitchSetData) setData).isOn();
            }
            SysLog.info("TCHotelSystemService getHotelVolumeSetting value = " + value);
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
        if (tcHotelApi != null) {
            tcHotelApi.setHotelVolumeSetting(mode);
            return;
        }
        try {
            SysLog.info("TCHotelSystemService getHotelFunSwitch value = " + mode);
            TCSwitchSetData setData = new TCSwitchSetData();
            setData.setName(SkyConfigDefs.SKY_GFC_TV_HOTEL_VOLUME_SETTING_SWITCH);
            setData.setOn(mode);
            ServiceUtil.setData(setData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取定时开机功能开关
     */
    public boolean getHotelAutoStartupSwitch() {
        if (tcHotelApi != null) {
            return tcHotelApi.getHotelAutoStartupSwitch();
        }
        try {
            boolean value = false;
            TCSetData setData = getConfigData(SkyConfigDefs.SKY_GFC_TV_HOTEL_AUTO_START_UP_SWITCH);
            if (setData != null) {
                value = ((TCSwitchSetData) setData).isOn();
            }
            SysLog.info("TCHotelSystemService getHotelVolumeSetting value = " + value);
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
        if (tcHotelApi != null) {
            tcHotelApi.setHotelAutoStartupSwitch(mode);
            return;
        }
        try {
            SysLog.info("TCHotelSystemService getHotelFunSwitch value = " + mode);
            TCSwitchSetData setData = new TCSwitchSetData();
            setData.setName(SkyConfigDefs.SKY_GFC_TV_HOTEL_AUTO_START_UP_SWITCH);
            setData.setOn(mode);
            ServiceUtil.setData(setData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置音量(无UI)
     */
    public void setVolume(int vol) {
        if (tcHotelApi != null) {
            tcHotelApi.setVolume(vol);
            return;
        }
        try {
            TCRangeSetData volumeData = new TCRangeSetData();
            volumeData.setName(SkyConfigDefs.SKY_CFG_TV_VOLUME);
            volumeData.setCurrent(vol);
            SysLog.info("TCHotelSystemService setVolume = " + vol);
            ServiceUtil.setData(volumeData);
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
        if (tcHotelApi != null) {
            return tcHotelApi.setPanelOnOff(onOff);
        }
        try {
            SysLog.info("TCHotelSystemService setPanelOnOff onOff = " + onOff);
            TCSwitchSetData setData = new TCSwitchSetData();
            setData.setName(SkyConfigDefs.SKY_CFG_TV_PANEL_ON_OFF);
            setData.setOn(onOff);
            ServiceUtil.setData(setData);
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
        if (tcHotelApi != null) {
            return tcHotelApi.getScreenOn();
        }
        TCSwitchSetData screenData = (TCSwitchSetData) getConfigData(SkyConfigDefs.SKY_CFG_TV_PANEL_ON_OFF);
        if (screenData != null) {
            boolean isOn = screenData.isOn();
            SysLog.info("TCHotelSystemService getScreenOn isOn = " + isOn);
            return isOn;
        }
        return false;
    }

    private TCSetData getConfigData(String configName) {
        TCSetData setData = ServiceUtil.getSetData(configName);
        if (setData != null && TextUtils.isEmpty(setData.getName()))
            setData.setName(configName);
        return setData;
    }
}
