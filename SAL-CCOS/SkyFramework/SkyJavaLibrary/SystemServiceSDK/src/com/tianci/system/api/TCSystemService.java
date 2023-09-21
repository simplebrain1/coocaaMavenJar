package com.tianci.system.api;

import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;

import com.skyworth.framework.event.BroadcastKey;
import com.skyworth.framework.skysdk.ipc.SkyApplication;
import com.skyworth.framework.skysdk.ipc.SkyContext;
import com.skyworth.framework.skysdk.util.SkyData;
import com.skyworth.framework.skysdk.util.SkyObjectByteSerialzie;
import com.tianci.net.command.TCNetworkCmd;
import com.tianci.net.data.SkyIpInfo;
import com.tianci.net.data.SkyWifiAPItem;
import com.tianci.net.define.NetworkDefs.NetworkDevices;
import com.tianci.system.callback.OnInfraredLearnedCallback;
import com.tianci.system.callback.OldIPCCallback;
import com.tianci.system.callback.ScreenShotCallback;
import com.tianci.system.command.TCSystemCmd;
import com.tianci.system.data.PowerTimeEvent;
import com.tianci.system.data.ScreenshotData;
import com.tianci.system.data.SkyScreenshotRetData;
import com.tianci.system.data.TCEnumSetData;
import com.tianci.system.data.TCInfoSetData;
import com.tianci.system.data.TCRangeSetData;
import com.tianci.system.data.TCSetData;
import com.tianci.system.data.TCSwitchSetData;
import com.tianci.system.define.SkyConfigDefs;
import com.tianci.system.define.TCEnvKey;
import com.tianci.system.helper.TCSystemServiceHelper;
import com.tianci.system.utils.ApiUtil;
import com.tianci.system.utils.ServiceUtil;
import com.tianci.system.utils.SysLog;
import com.tianci.utils.SkySSSLog;

import java.util.List;

import static com.tianci.system.utils.ServiceUtil.addBroadCallback;
import static com.tianci.system.utils.ServiceUtil.addIPCCallback;
import static com.tianci.system.utils.ServiceUtil.executeSysCmd;
import static com.tianci.system.utils.ServiceUtil.executeUICmd;
import static com.tianci.system.utils.ServiceUtil.getSetData;
import static com.tianci.system.utils.ServiceUtil.removeBroadCallback;
import static com.tianci.system.utils.ServiceUtil.setData;

/**
 * 系统接口
 */
public class TCSystemService {
    private static final String TAG = "TCSystemService";
    private static TCSystemService instance = null;
    private Context context;
    private TCSystemServiceHelper helper;
    private TCSystemApi tcSystemApi;

    public static TCSystemService getInstance(Context context) {
        synchronized (TCSystemService.class) {
            if (instance == null) {
                instance = new TCSystemService(context);
            }
            return instance;
        }
    }

    private TCSystemService(Context context) {
        this.context = context.getApplicationContext();
        if (this.context == null) {
            this.context = context;
        }
        if (ApiUtil.isNewPlatform()) {
            helper = new TCSystemServiceHelper(this.context);
            tcSystemApi = new TCSystemApi(this.context);
        }
    }

    /**
     * 注册关机广告事件
     *
     * @param event 关机广告或者业务的结构体
     */
    public void regPowerTimeEvent(PowerTimeEvent event) {
        if (helper != null) {
            helper.regPowerTimeEvent(event);
            return;
        }
        executeSysCmd(TCSystemCmd.TC_SYSTEM_CMD_REG_POWER_TIME_EVENT.toString(), SkyObjectByteSerialzie.toBytes(event));
    }

    /**
     * 注销关机广告事件
     *
     * @param event 注册时定义的业务
     */
    public void unRegPowerTimeEvent(PowerTimeEvent event) {
        if (helper != null) {
            helper.unRegPowerTimeEvent(event);
            return;
        }
        executeSysCmd(TCSystemCmd.TC_SYSTEM_CMD_UN_REG_POWER_TIME_EVENT.toString(), SkyObjectByteSerialzie.toBytes(event));
    }

    /**
     * 获取system session id.
     *
     * @return system session id
     */
    public String getSystemSessionId() {
        if (helper != null) {
            return helper.getSystemSessionId();
        }
        byte[] ackData = executeSysCmd(TCSystemCmd.TC_SYSTEM_CMD_GET_SYSTEM_SESSION_ID.toString(), null);
        if (ackData != null) {
            return new String(ackData);
        }
        return "";
    }

    /**
     * 设置音量（显示音量条，不显示音量条请走Android原生接口）
     *
     * @param volume 音量值
     */
    public void setVolume(int volume) {
        if (helper != null) {
            helper.setVolume(volume);
            return;
        }
        SkyData params = new SkyData();
        params.add("showui", "true");
        params.add("volume", volume);
        executeUICmd("SYSTEM_CMD_SET_VOLUME", params.toBytes());
    }

    /**
     * 获取音量
     *
     * @return 当前音量
     */
    public int getVolume() {
        if (helper != null) {
            return helper.getVolume();
        }
        try {
            TCSetData setData = getConfigData(SkyConfigDefs.SKY_CFG_TV_VOLUME);
            int current = 100;
            if (setData != null) {
                TCRangeSetData data = (TCRangeSetData) setData;
                current = data.getCurrent();
            }
            SysLog.info("getVolume = " + current);
            return current;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 设置静音(显示UI)
     *
     * @param mute true是静音，false取消静音
     */
    public void setMute(boolean mute) {
        if (helper != null) {
            helper.setMute(mute);
            return;
        }
        executeUICmd("SYSTEM_CMD_SET_MUTE", String.valueOf(mute).getBytes());
    }

    /**
     * 获取是否静音, 获取声音打开关闭状态
     *
     * @return true：静音
     */
    public boolean getMute() {
        if (helper != null) {
            return helper.getMute();
        }
        try {
            boolean value = false;
            TCSetData setData = getConfigData(SkyConfigDefs.SKY_CFG_TV_AUDIO_MUTE);
            if (setData != null) {
                value = ((TCSwitchSetData) setData).isOn();
            }
            SkySSSLog.d(TAG, "getMute value=" + value);
            return value;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 设置静音 声音打开关闭的接口(不显示UI)
     *
     * @param mute true是静音，false取消静音
     */
    public void setMuteNoUI(boolean mute) {
        if (helper != null) {
            helper.setMuteNoUI(mute);
            return;
        }
        try {
            SkySSSLog.d(TAG, "setMuteNoUI mute=" + mute);
            TCSwitchSetData setData = new TCSwitchSetData();
            setData.setName(SkyConfigDefs.SKY_CFG_TV_AUDIO_MUTE);
            setData.setOn(mute);
            setData(setData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置单独听模式
     *
     * @param mode true进入，false退出
     */
    public void setAudioOnlyMode(boolean mode) {
        if (helper != null) {
            helper.setAudioOnlyMode(mode);
            return;
        }
        SkyData data = new SkyData();
        data.add("EnterAudioOnly", String.valueOf(mode));
        executeUICmd(TCSystemCmd.TC_SYSTEM_CMD_SET_AUDIO_ONLY_MODE.toString(), data.toBytes());
    }

    /**
     * 当前是否在单独听
     *
     * @return boolean true表示在单独听
     */
    public boolean isInAudioOnlyMode() {
        if (helper != null) {
            return helper.isInAudioOnlyMode();
        }
        return false;
    }

    /**
     * 平台是否支持截屏
     *
     * @return boolean true表示支持
     */
    public boolean isSupportScreenshot() {
        if (helper != null) {
            return helper.isSupportScreenshot();
        }
        byte[] ackData = executeSysCmd(TCSystemCmd.TC_SYSTEM_CMD_IS_SUPPORT_SCREENSHOT.toString(), null);
        if (ackData != null) {
            Boolean result = SkyObjectByteSerialzie.toObject(ackData, boolean.class);
            if (result != null) {
                return result;
            }
        }
        return false;
    }

    /**
     * 获取视频层截屏(不含UI)
     * 截图过程为异步过程, 所以调用此方法后, 将把截图相关数据保存到SkyScreenshotRetData,
     * 并通过callback回调；当width=0 & height=0时, 则根据平台分辨率进行截图
     *
     * @param width    截图宽
     * @param height   截图高
     * @param callback 截图成功失败回调，{@link ScreenShotCallback#onScreenShotCallback(SkyScreenshotRetData)}
     */
    public boolean getVideoScreenshot(int width, int height, ScreenShotCallback callback) {
        if (helper != null) {
            return helper.getScreenshot(width, height, callback);
        }
        OldIPCCallback callback1 = new OldIPCCallback(callback, new String[]{"onScreenShotCallback"},
                new Class[][]{{SkyScreenshotRetData.class}}, true) {
            @Override
            public void handle(byte[] data) {
                SkyScreenshotRetData result = SkyObjectByteSerialzie.toObject(data, SkyScreenshotRetData.class);
                handle(result);
            }
        };
        addIPCCallback(TCSystemCmd.TC_SYSTEM_CMD_GET_SCREENSHOT.toString(), callback1);
        ScreenshotData data = new ScreenshotData(width, height);
        byte[] ackData = executeSysCmd(TCSystemCmd.TC_SYSTEM_CMD_GET_SCREENSHOT.toString(),
                SkyObjectByteSerialzie.toBytes(data));
        if (ackData != null) {
            Boolean result = SkyObjectByteSerialzie.toObject(ackData, boolean.class);
            if (result != null) {
                return result;
            }
        }
        return false;
    }

    /**
     * 截屏(含UI)
     * 截图过程为异步过程, 所以调用此方法后, 将把截图相关数据保存到SkyScreenshotRetData,
     * 并通过callback回调；当width=0 & height=0时, 则根据平台分辨率进行截图
     *
     * @param width    截图宽
     * @param height   截图高
     * @param callback 截图成功失败回调，{@link ScreenShotCallback#onScreenShotCallback(SkyScreenshotRetData)}
     * @return boolean true表示截图成功执行
     */
    public boolean getScreenshotWithUI(int width, int height, ScreenShotCallback callback) {
        if (helper != null) {
            return helper.getScreenshotWithUI(width, height, callback);
        }
        OldIPCCallback callback1 = new OldIPCCallback(callback, new String[]{"onScreenShotCallback"},
                new Class[][]{{SkyScreenshotRetData.class}}, true) {
            @Override
            public void handle(byte[] data) {
                SkyScreenshotRetData result = SkyObjectByteSerialzie.toObject(data, SkyScreenshotRetData.class);
                handle(result);
            }
        };
        addIPCCallback(TCSystemCmd.TC_SYSTEM_CMD_GET_SCREENSHOT_WITH_UI.toString(), callback1);
        ScreenshotData data = new ScreenshotData(width, height);
        byte[] ackData = executeSysCmd(TCSystemCmd.TC_SYSTEM_CMD_GET_SCREENSHOT_WITH_UI.toString(),
                SkyObjectByteSerialzie.toBytes(data));
        if (ackData != null) {
            Boolean result = SkyObjectByteSerialzie.toObject(ackData, boolean.class);
            if (result != null) {
                return result;
            }
        }
        return false;
    }

    /**
     * 截屏（包含UI及Video）
     * 截图过程为异步过程, 所以调用此方法后, 将把截图相关数据保存到SkyScreenshotRetData,
     * 并通过callback回调；当width=0 & height=0时, 则根据平台分辨率进行截图
     *
     * @param width    截图宽
     * @param height   截图高
     * @param callback 截图成功失败回调，{@link ScreenShotCallback#onScreenShotCallback(SkyScreenshotRetData)}
     * @return boolean true表示截图成功执行
     */
    public boolean getScreenshotCustomSize(int width, int height, ScreenShotCallback callback) {
        if (helper != null) {
            return helper.getScreenshotCustomSize(width, height, callback);
        }
        OldIPCCallback callback1 = new OldIPCCallback(callback, new String[]{"onScreenShotCallback"},
                new Class[][]{{SkyScreenshotRetData.class}}, true) {
            @Override
            public void handle(byte[] data) {
                SkyScreenshotRetData result = SkyObjectByteSerialzie.toObject(data, SkyScreenshotRetData.class);
                handle(result);
            }
        };
        addIPCCallback(TCSystemCmd.TC_SYSTEM_CMD_GET_SCREENSHOT_CUSTOM_SIZE.toString(), callback1);
        ScreenshotData data = new ScreenshotData(width, height);
        byte[] ackData = executeSysCmd(TCSystemCmd.TC_SYSTEM_CMD_GET_SCREENSHOT_CUSTOM_SIZE.toString(),
                SkyObjectByteSerialzie.toBytes(data));
        if (ackData != null) {
            Boolean result = SkyObjectByteSerialzie.toObject(ackData, boolean.class);
            if (result != null) {
                return result;
            }
        }
        return false;
    }

    /**
     * AI待机开屏接口
     *
     * @param isNeedAd true表示启动开屏广告
     */
    public void setAIScreenMode(boolean isNeedAd) {
        if (helper != null) {
            helper.setAIScreenMode(isNeedAd);
            return;
        }
        executeSysCmd(TCSystemCmd.TC_SYSTEM_CMD_SET_AI_SCREEN_MODE.toString(), String.valueOf(isNeedAd).getBytes());
    }

    /**
     * 定义给关机广告用的关机接口
     */
    public void adServiceSetStandby() {
        if (helper != null) {
            helper.adServiceSetStandby();
            return;
        }
        SkySSSLog.d(TAG, "adServiceSetStandby");
        executeSysCmd(TCSystemCmd.TC_SYSTEM_CMD_SET_AD_TO_STANDBY.toString(), null);
    }

    /**
     * 获取ip信息
     *
     * @return SkyIpInfo中包含了当前连接设备的ip, mac, dns, gateway, mask
     */
    public SkyIpInfo getIpInfo() {
        if (helper != null) {
            return helper.getIpInfo();
        }
        byte[] ackData = executeSysCmd(TCNetworkCmd.TC_NETWORK_CMD_GET_IP_INFO.toString(), null);
        if (ackData != null)
            return SkyObjectByteSerialzie.toObject(ackData, SkyIpInfo.class);
        return null;
    }

    /**
     * 获取网络类型
     *
     * @return {@link NetworkDevices#ETHERNET}, {@link NetworkDevices#WIFI},
     * {@link NetworkDevices#MOBILE},{@link NetworkDevices#PPPOE},{@link NetworkDevices#UNKNOW}
     */
    public String getNetType() {
        if (helper != null) {
            return helper.getNetType();
        }
        byte[] ackData = executeSysCmd(TCNetworkCmd.TC_NETWORK_CMD_GET_NET_TYPE.toString(), null);
        if (ackData != null)
            return SkyObjectByteSerialzie.toObject(ackData, String.class);
        return null;
    }

    /**
     * 是否连网
     *
     * @return true表示已连上，false未连接
     */
    public boolean isNetworkConnected() {
        if (helper != null) {
            return helper.isNetworkConnected();
        }
        byte[] ackData = executeSysCmd(TCNetworkCmd.TC_NETWORK_CMD_GET_IS_CONNECTD.toString(), null);
        if (ackData != null) {
            Boolean result = SkyObjectByteSerialzie.toObject(ackData, boolean.class);
            if (result != null) {
                return result;
            }
        }
        return false;
    }

    /**
     * 获取网线是否插上
     *
     * @return true 插上
     */
    public boolean isCableConnected() {
        if (helper != null) {
            return helper.isCableConnected();
        }
        byte[] ackData = executeSysCmd(TCNetworkCmd.TC_NETWORK_CMD_ETHERNET_IS_CABLE_CONNECT.toString(), null);
        if (ackData != null) {
            Boolean result = SkyObjectByteSerialzie.toObject(ackData, boolean.class);
            if (result != null) {
                return result;
            }
        }
        return false;
    }

    /**
     * 请求网络连接，进入网络设置界面
     *
     * @return true表示请求成功，false表示请求失败
     */
    public boolean connectNetwork() {
        boolean ret = false;
        SkySSSLog.w(TAG, "connectNetwork");
        try {
            Intent intent = new Intent("com.tianci.setting.SettingApi.connectNet.noConfirmUI");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
            ret = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

    /**
     * 弹出确定/取消对话框UI，在点击确定后，进入网络设置界面
     *
     * @return true表示请求成功 ，false表示请求失败
     */
    public boolean connectNetworkWithConfirmUI() {
        boolean ret = false;
        SkySSSLog.d(TAG, "connectNetworkWithConfirmUI");
        try {
            Intent intent = new Intent("com.tianci.setting.SettingApi.connectNet");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("type", "connetNet");
            context.startActivity(intent);
            ret = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

    /**
     * 动态配置连接无线网络
     *
     * @param wifiApItem the wifi ap 配置参数
     */
    public void connectWifiByDhcp(SkyWifiAPItem wifiApItem) {
        if (helper != null) {
            helper.connectWifiByDhcp(wifiApItem);
            return;
        }
        executeSysCmd(TCNetworkCmd.TC_NETWORK_CMD_WIFI_CONNECT_BY_DHCP.toString(),
                SkyObjectByteSerialzie.toBytes(wifiApItem));
    }

    /**
     * 获取TVID
     *
     * @return TVID
     */
    public String getTVID() {
        if (helper != null) {
            return helper.getTVID();
        }
        TCInfoSetData ret = (TCInfoSetData) getSetData(TCEnvKey.SKY_SYSTEM_ENV_MACHINE_CODE);
        String value = "";
        if (ret != null) {
            value = ret.getCurrent();
        }
        SkySSSLog.d(TAG, "getTVID value=" + value);
        return value;
    }

    /**
     * 获取位置信息
     *
     * @return 位置信息
     */
    public String getLocation() {
        if (helper != null) {
            return helper.getLocation();
        }
        TCInfoSetData ret = (TCInfoSetData) getSetData(TCEnvKey.SKY_SYSTEM_ENV_LOCATION);
        String value = "";
        if (ret != null) {
            value = ret.getCurrent();
        }
        SkySSSLog.d(TAG, "getLocation value=" + value);
        return value;
    }

    /**
     * 获取激活ID
     *
     * @return 激活ID
     */
    public String getActiveId() {
        if (helper != null) {
            return helper.getActiveId();
        }
        TCInfoSetData ret = (TCInfoSetData) getSetData(TCEnvKey.SKY_SYSTEM_ENV_ACTIVE_ID);
        String value = "";
        if (ret != null) {
            value = ret.getCurrent();
        }
        SkySSSLog.d(TAG, "getActiveId value=" + value);
        return value;
    }

    /**
     * 获取tv名称
     *
     * @return tv名称
     */
    public String getTVName() {
        if (helper != null) {
            return helper.getTVName();
        }
        TCInfoSetData ret = (TCInfoSetData) getSetData(TCEnvKey.SKY_SYSTEM_ENV_TVNAME);
        String value = "";
        if (ret != null) {
            value = ret.getCurrent();
        }
        SkySSSLog.d(TAG, "getTVName value=" + value);
        return value;
    }

    /**
     * 设置tv名称
     *
     * @param name tv名称
     */
    public void setTVName(String name) {
        if (helper != null) {
            helper.setTVName(name);
            return;
        }
        SkySSSLog.d(TAG, "setTVName name=" + name);
        if (!TextUtils.isEmpty(name)) {
            TCInfoSetData setData = new TCInfoSetData();
            setData.setName(TCEnvKey.SKY_SYSTEM_ENV_TVNAME);
            setData.setCurrent(name);
            setData(setData);
        }
    }

    /**
     * 获取天气
     *
     * @return 天气
     */
    public String getWeather() {
        if (helper != null) {
            return helper.getWeather();
        }
        TCInfoSetData ret = (TCInfoSetData) getSetData(TCEnvKey.SKY_SYSTEM_ENV_WEATHER);
        String value = "";
        if (ret != null) {
            value = ret.getCurrent();
        }
        SkySSSLog.d(TAG, "getWeather value=" + value);
        return value;
    }

    /**
     * 获取品保时间选项
     *
     * @return {@link SkyConfigDefs.SKY_SYSTEM_ENV_START_SCREEN_SAVER_MODE_ENUM_TYPE#ONE_MIN},
     * {@link SkyConfigDefs.SKY_SYSTEM_ENV_START_SCREEN_SAVER_MODE_ENUM_TYPE#TWO_MIN},
     * {@link SkyConfigDefs.SKY_SYSTEM_ENV_START_SCREEN_SAVER_MODE_ENUM_TYPE#FIVE_MIN},
     * {@link SkyConfigDefs.SKY_SYSTEM_ENV_START_SCREEN_SAVER_MODE_ENUM_TYPE#TEN_MIN},
     * {@link SkyConfigDefs.SKY_SYSTEM_ENV_START_SCREEN_SAVER_MODE_ENUM_TYPE#THIRTY_MIN}
     * {@link SkyConfigDefs.SKY_SYSTEM_ENV_START_SCREEN_SAVER_MODE_ENUM_TYPE#NONE}
     */
    public int getSaveScreenTime() {
        if (helper != null) {
            return helper.getSaveScreenTime();
        }
        TCEnumSetData ret = (TCEnumSetData) getSetData(TCEnvKey.SKY_SYSTEM_ENV_START_SCREEN_SAVER_MODE);
        String value = "";
        if (ret != null) {
            value = ret.getCurrent();
        }
        SkySSSLog.d(TAG, "getSaveScreenTime value=" + value);
        SkyConfigDefs.SKY_SYSTEM_ENV_START_SCREEN_SAVER_MODE_ENUM_TYPE result =
                SkyConfigDefs.SKY_SYSTEM_ENV_START_SCREEN_SAVER_MODE_ENUM_TYPE.valueOf(value);
        return result.ordinal();
    }

    private TCSetData getConfigData(String configName) {
        TCSetData setData = ServiceUtil.getSetData(configName);
        if (setData != null && TextUtils.isEmpty(setData.getName()))
            setData.setName(configName);
        return setData;
    }

    /**
     * 给到智慧家庭的接口，code：红外码值
     */
    public void sendInfraredCode(byte[] code) {
        SkySSSLog.d(TAG, "sendInfraredCode code=" + code);
        if (helper != null) {
            helper.sendInfraredCode(code);
            return;
        }
        //根据红外码，组装串口协议数据，发送
        //接受数据，等待最长500MS，如果没有回复则超时
        TCInfoSetData data = new TCInfoSetData();
        data.setName(SkyConfigDefs.SKY_GFG_TV_INFRARED_LEARNING);
        SkyData skyData = new SkyData();
        skyData.add("infraredCode", code);
        data.setCurrent(skyData.toString());
        setData(data);
    }

    /**
     * 给到智慧家庭的接口，回调接口,调用这个接口等待结果
     *
     * @param callBack 结果回调
     * @param register true:注册；false:解注册
     */
    public void setLearnInfraredCallBack(Context context, OnInfraredLearnedCallback callBack,
                                         boolean register) {
        SkySSSLog.d(TAG, "setLearnInfraredCallBack callBack=" + callBack + ",register="
                + register);
        if (helper != null) {
            helper.setLearnInfraredCallBack(callBack, register);
            return;
        }
        OldIPCCallback cb1 = new OldIPCCallback(callBack, new String[]{
                "onInfraredLearnedFailed"},
                new Class[][]{{int.class}}, false) {
            @Override
            public void handle(byte[] data) {
                Integer result = SkyObjectByteSerialzie.toObject(data, Integer.class);
                SkySSSLog.d(TAG, "setLearnInfraredCallBack cb1 result=" + result);
                handle(result);
            }
        };
        OldIPCCallback cb2 = new OldIPCCallback(callBack, new String[]{
                "onInfraredLearnedSuccess"},
                new Class[][]{{byte[].class}}, false) {
            @Override
            public void handle(byte[] data) {
                SkyData skyData = new SkyData();
                skyData.deserialize(data);
                SkySSSLog.d(TAG, "setLearnInfraredCallBack cb2 skyData=" + skyData);
                byte[] bytes = skyData.getBytes("infraredCode");
                handle((Object) bytes);
            }
        };
        String cmd = SkyConfigDefs.SKY_GFC_TV_INFRARED_LEARNING_LISTENER;
        if (register) {
            addBroadCallback(context, TCSystemCmd.TC_SYSTEM_CMD_INFRARED_LEARNING_FAILED.toString(), cb1);
            addBroadCallback(context, TCSystemCmd.TC_SYSTEM_CMD_INFRARED_LEARNING_SUCCESS.toString(), cb2);
            SkyData skyData = new SkyData();
            TCInfoSetData setData = new TCInfoSetData();
            setData.setName(cmd);
            setData.setCurrent(skyData.toString());
            setData(setData);
        } else {
            removeBroadCallback(context,
                    TCSystemCmd.TC_SYSTEM_CMD_INFRARED_LEARNING_FAILED.toString(), callBack);
            removeBroadCallback(context,
                    TCSystemCmd.TC_SYSTEM_CMD_INFRARED_LEARNING_SUCCESS.toString(), callBack);
        }
    }

    /**
     * standby
     * apk need system permission ,and declare <uses-permission android:name="android.permission.INJECT_EVENTS" />
     */
    public void startStandby() {
        new Thread(new StandbyRunnable()).start();
    }
    private class StandbyRunnable implements Runnable
    {
        @Override
        public void run() {
            try {
                Log.e(TAG, ""+context.getPackageName()+" startStandby");
                Instrumentation inst = new Instrumentation();
                inst.sendKeyDownUpSync(BroadcastKey.KEYCODE_POWER);
            } catch (Exception e) {
                Log.e(TAG, e.toString());
            }
        }
    }

    /**
     * 获取当前连接wifi的相关信息
     *
     * @return Ap状态
     */
    public SkyWifiAPItem getWifiInfo(){
        if (helper != null && tcSystemApi !=null) {
            return tcSystemApi.getWifiInfo();
        }
        byte[] ackData = executeSysCmd(TCNetworkCmd.TC_NETWORK_CMD_WIFI_GET_CURRENT_CONNECTED_AP_INFO.toString(), null);
        if (ackData != null) {
            SkyWifiAPItem skyWifiAPItem = SkyObjectByteSerialzie.toObject(ackData, SkyWifiAPItem.class);
            if (skyWifiAPItem != null) {
                return skyWifiAPItem;
            }
        }
        return null;
    }


    /**
     * 获取某个ap是否配置过
     *
     * @return true 配置过
     */
    public boolean isAPConfigured(SkyWifiAPItem item) {
        if (helper != null && tcSystemApi !=null) {
            return tcSystemApi.isAPConfigured(item);
        }
        byte[] ackData = executeSysCmd(TCNetworkCmd.TC_NETWORK_CMD_WIFI_GET_AP_IS_CONFIG.toString(), SkyObjectByteSerialzie.toBytes(item));
        if (ackData != null)
        {
            return SkyObjectByteSerialzie.toObject(ackData, Boolean.class);
        }
        return false;
    }

    /**
     * 获取当前系统已存在的无线列表
     *
     * @return 无线列表
     */
    public List<SkyWifiAPItem> getWifiList() {
        if (helper != null && tcSystemApi !=null) {
            return tcSystemApi.getWifiList();
        }
        byte[] ackData = executeSysCmd(TCNetworkCmd.TC_NETWORK_CMD_WIFI_GET_CURRENT_WIFI_INFO_LIST.toString(), null);
        if (ackData != null) {
            return (List<SkyWifiAPItem>) SkyObjectByteSerialzie.toObject(ackData, List.class);
        }
        return null;
    }

    /**
     * 忽略某个指定的ap
     *
     * @param ssid 被忽略的ssid
     * @return true 操作成功
     */
    public boolean forgetAp(String ssid) {
        if (helper != null && tcSystemApi !=null) {
            return tcSystemApi.forgetAp(ssid);
        }
        byte[] ackData = executeSysCmd(TCNetworkCmd.TC_NETWORK_CMD_WIFI_FORGET_AP.toString(), null);
        if (ackData != null)
        {
            return SkyObjectByteSerialzie.toObject(ackData, Boolean.class);
        }
        return false;
    }
}