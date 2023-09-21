package com.tianci.system.helper;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;

import com.skyworth.framework.skysdk.util.SkyData;
import com.skyworth.framework.skysdk.util.SkyObjectByteSerialzie;
import com.tianci.net.command.TCNetworkCmd;
import com.tianci.net.data.SkyIpInfo;
import com.tianci.net.data.SkyWifiAPItem;
import com.tianci.system.api.TCSystemApi;
import com.tianci.system.callback.ITCLedConnectedStatusCallback;
import com.tianci.system.callback.ITapeLightStateChangeCallBack;
import com.tianci.system.callback.OnInfraredLearnedCallback;
import com.tianci.system.callback.IPCCallback;
import com.tianci.system.callback.ScreenShotCallback;
import com.tianci.system.command.TCSystemCmd;
import com.tianci.system.data.PowerTimeEvent;
import com.tianci.system.data.ScreenshotData;
import com.tianci.system.data.SkyScreenshotRetData;
import com.tianci.system.data.TapeLightModeData;
import com.tianci.system.data.TapeLightStatusData;
import com.tianci.system.define.SkyConfigDefs;
import com.tianci.system.define.SystemProviderDefines;
import com.tianci.system.define.TCEnvKey;
import com.tianci.system.utils.ApiUtil;
import com.tianci.utils.SkySSSLog;

import static com.tianci.system.utils.ApiUtil.KEY_IS_PLUGIN;
import static com.tianci.system.utils.ApiUtil.KEY_PARAM_TYPE;
import static com.tianci.system.utils.ApiUtil.KEY_RET_TYPE;
import static com.tianci.system.utils.ApiUtil.KEY_PARAM;
import static com.tianci.system.utils.ApiUtil.KEY_RESULT;
import static com.tianci.system.utils.ApiUtil.KEY_TC_TYPE;
import static com.tianci.system.utils.ApiUtil.PARAM_RET_BYTES;
import static com.tianci.system.utils.ApiUtil.PARAM_RET_INT;
import static com.tianci.system.utils.ApiUtil.PARAM_RET_STRING;
import static com.tianci.system.utils.ApiUtil.PARAM_RET_BOOL;
import static com.tianci.system.utils.ApiUtil.TC_DATA_INFO;
import static com.tianci.system.utils.ApiUtil.TC_DATA_SWITCH;
import static com.tianci.system.utils.ApiUtil.parseBool;

/**
 * 非ipc调用
 */
public class TCSystemServiceHelper {
    private static final String TAG = "TCSystemServiceHelper";
    private final TCSystemApi tcSystemApi;

    public TCSystemServiceHelper(Context context) {
        tcSystemApi = TCSystemApi.getInstance(context);
        ApiUtil.setContext(context);
    }

    private Bundle executeCmd(String cmd, Bundle param, boolean isSet) {
        Bundle ret = ApiUtil.executeSystemCmd(cmd, param, isSet);
        SkySSSLog.d(TAG, "executeCmd cmd=" + cmd + ",ret=" + ret);
        return ret;
    }

    public void regPowerTimeEvent(PowerTimeEvent event) {
        SkySSSLog.d(TAG, "regPowerTimeEvent");
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_PARAM_TYPE, PARAM_RET_BYTES);
        bundle.putByteArray(KEY_PARAM, SkyObjectByteSerialzie.toBytes(event));
        executeCmd(TCSystemCmd.TC_SYSTEM_CMD_REG_POWER_TIME_EVENT.toString(), bundle, false);
    }

    public void unRegPowerTimeEvent(PowerTimeEvent event) {
        SkySSSLog.d(TAG, "unRegPowerTimeEvent");
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_PARAM_TYPE, PARAM_RET_BYTES);
        bundle.putByteArray(KEY_PARAM, SkyObjectByteSerialzie.toBytes(event));
        executeCmd(TCSystemCmd.TC_SYSTEM_CMD_UN_REG_POWER_TIME_EVENT.toString(), bundle, false);
    }

    public String getSystemSessionId() {
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_RET_TYPE, PARAM_RET_STRING);
        Bundle ret = executeCmd(TCSystemCmd.TC_SYSTEM_CMD_GET_SYSTEM_SESSION_ID.toString(),
                bundle, false);
        String result = ret.getString(KEY_RESULT);
        SkySSSLog.d(TAG, "getSystemSessionId result=" + result);
        return result;
    }

    public boolean getScreenshotCustomSize(int width, int height, ScreenShotCallback callback) {
        String cmd = TCSystemCmd.TC_SYSTEM_CMD_GET_SCREENSHOT_CUSTOM_SIZE.toString();
        IPCCallback callback1 = new IPCCallback(callback, new String[]{"onScreenShotCallback"},
                new Class[][]{{SkyScreenshotRetData.class}}, true);
        tcSystemApi.addIPCCallback(cmd, callback1);
        ScreenshotData data = new ScreenshotData(width, height);
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_PARAM_TYPE, PARAM_RET_BYTES);
        bundle.putByteArray(KEY_PARAM, SkyObjectByteSerialzie.toBytes(data));
        bundle.putInt(KEY_RET_TYPE, PARAM_RET_BYTES);
        Bundle ret = executeCmd(cmd, bundle, false);
        SkySSSLog.d(TAG, "getScreenshotCustomSize width=" + width + ",height=" + height +
                ",callback=" + callback + ",ret=" + ret);
        return parseBool(ret.getByteArray(KEY_RESULT));
    }

    public void setVolume(int volume) {
        SkySSSLog.d(TAG, "setVolume volume=" + volume);
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_PARAM_TYPE, PARAM_RET_BYTES);
        SkyData params = new SkyData();
        params.add("showui", "true");
        params.add("volume", volume);
        bundle.putByteArray(KEY_PARAM, params.toBytes());
        executeCmd("SYSTEM_CMD_SET_VOLUME", bundle, true);
    }

    public int getVolume() {
        Bundle bundle = new Bundle();
        bundle.putBoolean(KEY_IS_PLUGIN, true);
        bundle.putInt(KEY_RET_TYPE, PARAM_RET_BYTES);
        Bundle ret = executeCmd(SkyConfigDefs.SKY_CFG_TV_VOLUME, bundle, false);
        int value = ApiUtil.parseInt(ret.getByteArray(KEY_RESULT));
        SkySSSLog.d(TAG, "getVolume value=" + value);
        return value;
    }

    public void setMute(boolean mute) {
        SkySSSLog.d(TAG, "setMute mute=" + mute);
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_PARAM_TYPE, PARAM_RET_BYTES);
        bundle.putByteArray(KEY_PARAM, String.valueOf(mute).getBytes());
        executeCmd("SYSTEM_CMD_SET_MUTE", bundle, false);
    }

    public boolean getMute() {
        Bundle param = new Bundle();
        param.putInt(KEY_RET_TYPE, PARAM_RET_BOOL);
        Bundle ret = executeCmd(SkyConfigDefs.SKY_CFG_TV_AUDIO_MUTE, param, false);
        boolean result = ret.getBoolean(KEY_RESULT);
        SkySSSLog.d(TAG, "getMute result=" + result);
        return result;
    }

    public void setMuteNoUI(boolean mute) {
        SkySSSLog.d(TAG, " setMuteNoUI mute=" + mute);
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_PARAM_TYPE, PARAM_RET_BYTES);
        bundle.putBoolean(KEY_IS_PLUGIN, true);
        bundle.putInt(KEY_TC_TYPE, TC_DATA_SWITCH);
        bundle.putByteArray(KEY_PARAM, String.valueOf(mute).getBytes());
        executeCmd(SkyConfigDefs.SKY_CFG_TV_AUDIO_MUTE, bundle, true);
    }

    public void setAudioOnlyMode(boolean mode) {
        SkySSSLog.d(TAG, "setAudioOnlyMode mode=" + mode);
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_PARAM_TYPE, PARAM_RET_BYTES);
        bundle.putByteArray(KEY_PARAM, String.valueOf(mode).getBytes());
        executeCmd(TCSystemCmd.TC_SYSTEM_CMD_SET_AUDIO_ONLY_MODE.toString(), bundle, false);
    }

    public boolean isInAudioOnlyMode() {
        Bundle param = new Bundle();
        param.putInt(KEY_RET_TYPE, PARAM_RET_BYTES);
        Bundle ret = executeCmd(TCSystemCmd.TC_SYSTEM_CMD_IS_IN_AUDIO_ONLY_MODE.toString(),
                param, false);
        boolean result = parseBool(ret.getByteArray(KEY_RESULT));
        SkySSSLog.d(TAG, "isInAudioOnlyMode result=" + result);
        return result;
    }

    public boolean getScreenshot(int width, int height, ScreenShotCallback callback) {
        SkySSSLog.d(TAG, "getScreenshot width=" + width + ",height=" + height + ",callback=" + callback);
        String cmd = TCSystemCmd.TC_SYSTEM_CMD_GET_SCREENSHOT.toString();
        IPCCallback callback1 = new IPCCallback(callback, new String[]{"onScreenShotCallback"},
                new Class[][]{{SkyScreenshotRetData.class}}, true);
        tcSystemApi.addIPCCallback(cmd, callback1);
        ScreenshotData data = new ScreenshotData(width, height);
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_RET_TYPE, PARAM_RET_BYTES);
        bundle.putInt(KEY_PARAM_TYPE, PARAM_RET_BYTES);
        bundle.putByteArray(KEY_PARAM, SkyObjectByteSerialzie.toBytes(data));
        Bundle ret = executeCmd(cmd, bundle, false);
        return parseBool(ret.getByteArray(KEY_RESULT));
    }

    public boolean isSupportScreenshot() {
        Bundle param = new Bundle();
        param.putInt(KEY_RET_TYPE, PARAM_RET_BYTES);
        Bundle ret = executeCmd(TCSystemCmd.TC_SYSTEM_CMD_IS_SUPPORT_SCREENSHOT.toString(),
                param, false);
        boolean result = parseBool(ret.getByteArray(KEY_RESULT));
        SkySSSLog.d(TAG, "isSupportScreenshot result=" + result);
        return result;
    }

    public boolean getScreenshotWithUI(int width, int height, ScreenShotCallback callback) {
        SkySSSLog.d(TAG, "getScreenshotWithUI width=" + width + ",height=" + height + ",callback=" + callback);
        String cmd = TCSystemCmd.TC_SYSTEM_CMD_GET_SCREENSHOT_WITH_UI.toString();
        IPCCallback callback1 = new IPCCallback(callback, new String[]{"onScreenShotCallback"},
                new Class[][]{{SkyScreenshotRetData.class}}, true);
        tcSystemApi.addIPCCallback(cmd, callback1);
        ScreenshotData data = new ScreenshotData(width, height);
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_RET_TYPE, PARAM_RET_BYTES);
        bundle.putInt(KEY_PARAM_TYPE, PARAM_RET_BYTES);
        bundle.putByteArray(KEY_PARAM, SkyObjectByteSerialzie.toBytes(data));
        Bundle ret = executeCmd(cmd, bundle, false);
        return parseBool(ret.getByteArray(KEY_RESULT));
    }

    public void setAIScreenMode(boolean isNeedAd) {
        SkySSSLog.d(TAG, "setAIScreenMode isNeedAd=" + isNeedAd);
        Bundle bundle = new Bundle();
        bundle.putByteArray(KEY_PARAM, String.valueOf(isNeedAd).getBytes());
        bundle.putInt(KEY_PARAM_TYPE, PARAM_RET_BYTES);
        executeCmd(TCSystemCmd.TC_SYSTEM_CMD_SET_AI_SCREEN_MODE.toString(), bundle, false);
    }

    public void adServiceSetStandby() {
        SkySSSLog.d(TAG, "adServiceSetStandby");
        executeCmd(TCSystemCmd.TC_SYSTEM_CMD_SET_AD_TO_STANDBY.toString(), null, false);
    }

    public SkyIpInfo getIpInfo() {
        Bundle param = new Bundle();
        param.putInt(KEY_RET_TYPE, PARAM_RET_BYTES);
        Bundle ret = executeCmd(TCNetworkCmd.TC_NETWORK_CMD_GET_IP_INFO.toString(), param, false);
        byte[] bytes = ret.getByteArray(KEY_RESULT);
        SkyIpInfo result = SkyObjectByteSerialzie.toObject(bytes, SkyIpInfo.class);
        SkySSSLog.d(TAG, "getIpInfo result=" + result);
        return result;
    }

    public String getNetType() {
        Bundle param = new Bundle();
        param.putInt(KEY_RET_TYPE, PARAM_RET_BYTES);
        Bundle ret = executeCmd(TCNetworkCmd.TC_NETWORK_CMD_GET_NET_TYPE.toString(), param, false);
        byte[] bytes = ret.getByteArray(KEY_RESULT);
        String result = "";
        if (null != bytes) {
            result = new String(bytes);
        }
        SkySSSLog.d(TAG, "getNetType result=" + result);
        return result;
    }

    public boolean isNetworkConnected() {
        Bundle param = new Bundle();
        param.putInt(KEY_RET_TYPE, PARAM_RET_BYTES);
        Bundle ret = executeCmd(TCNetworkCmd.TC_NETWORK_CMD_GET_IS_CONNECTD.toString(),
                param, false);
        boolean result = parseBool(ret.getByteArray(KEY_RESULT));
        SkySSSLog.d(TAG, "isNetworkConnected result=" + result);
        return result;
    }

    public boolean isCableConnected() {
        Bundle param = new Bundle();
        param.putInt(KEY_RET_TYPE, PARAM_RET_BYTES);
        Bundle ret = executeCmd(TCNetworkCmd.TC_NETWORK_CMD_ETHERNET_IS_CABLE_CONNECT.toString(),
                param, false);
        boolean result = parseBool(ret.getByteArray(KEY_RESULT));
        SkySSSLog.d(TAG, "isCableConnected result=" + result);
        return result;
    }

    public void connectWifiByDhcp(SkyWifiAPItem wifiApItem) {
        SkySSSLog.d(TAG, "connectWifiByDhcp");
        Bundle bundle = new Bundle();
        bundle.putByteArray(KEY_PARAM, SkyObjectByteSerialzie.toBytes(wifiApItem));
        bundle.putInt(KEY_PARAM_TYPE, PARAM_RET_BYTES);
        executeCmd(TCNetworkCmd.TC_NETWORK_CMD_WIFI_CONNECT_BY_DHCP.toString(), bundle, false);
    }

    public String getTVID() {
        Bundle param = new Bundle();
        param.putInt(KEY_RET_TYPE, PARAM_RET_STRING);
        Bundle ret = executeCmd(TCEnvKey.SKY_SYSTEM_ENV_MACHINE_CODE, param, false);
        String result = ret.getString(KEY_RESULT);
        SkySSSLog.d(TAG, "getTVID result=" + result);
        return result;
    }

    public String getLocation() {
        Bundle param = new Bundle();
        param.putInt(KEY_RET_TYPE, PARAM_RET_STRING);
        Bundle ret = executeCmd(TCEnvKey.SKY_SYSTEM_ENV_LOCATION, param, false);
        String result = ret.getString(KEY_RESULT);
        SkySSSLog.d(TAG, "getLocation result=" + result);
        return result;
    }

    public String getTVName() {
        Bundle param = new Bundle();
        param.putInt(KEY_RET_TYPE, PARAM_RET_STRING);
        Bundle ret = executeCmd(TCEnvKey.SKY_SYSTEM_ENV_TVNAME, param, false);
        String result = ret.getString(KEY_RESULT);
        SkySSSLog.d(TAG, "getTVName result=" + result);
        return result;
    }

    public void setTVName(String name) {
        SkySSSLog.d(TAG, "setTVName name=" + name);
        if (TextUtils.isEmpty(name)) {
            return;
        }
        Bundle param = new Bundle();
        param.putByteArray(KEY_PARAM, name.getBytes());
        param.putInt(KEY_PARAM_TYPE, PARAM_RET_BYTES);
        executeCmd(TCEnvKey.SKY_SYSTEM_ENV_TVNAME, param, true);
    }

    /**
     * 客户端解析exmaple code:
     *com.skyworth.webSDK.webservice.weather.domian.WeatherInfo
     * weatherInfo = JSON.parseObject(description, WeatherInfo.class);
     * 客户端依赖:ccWebSDK.jar,fastjson
     * @return
     */
    public String getWeather2() {
        Bundle param = new Bundle();
        param.putInt(KEY_RET_TYPE, PARAM_RET_STRING);
        Bundle ret = executeCmd(TCEnvKey.SKY_SYSTEM_ENV_WEATHER2, param, false);
        String result = ret.getString(KEY_RESULT);
        SkySSSLog.d(TAG, "getWeather result=" + result);
        return result;
    }

    @Deprecated
    public String getWeather() {
        Bundle param = new Bundle();
        param.putInt(KEY_RET_TYPE, PARAM_RET_STRING);
        Bundle ret = executeCmd(TCEnvKey.SKY_SYSTEM_ENV_WEATHER, param, false);
        String result = ret.getString(KEY_RESULT);
        SkySSSLog.d(TAG, "getWeather result=" + result);
        return result;
    }

    public String getActiveId() {
        Bundle param = new Bundle();
        param.putInt(KEY_RET_TYPE, PARAM_RET_STRING);
        Bundle ret = executeCmd(TCEnvKey.SKY_SYSTEM_ENV_ACTIVE_ID, param, false);
        String result = ret.getString(KEY_RESULT);
        SkySSSLog.d(TAG, "getActiveId result=" + result);
        return result;
    }

    public int getSaveScreenTime() {
        Bundle param = new Bundle();
        param.putInt(KEY_RET_TYPE, PARAM_RET_INT);
        Bundle ret = executeCmd(TCEnvKey.SKY_SYSTEM_ENV_START_SCREEN_SAVER_MODE, param, false);
        int result = ret.getInt(KEY_RESULT);
        SkySSSLog.d(TAG, "getSaveScreenTime result=" + result);
        return result;
    }

    public void sendInfraredCode(byte[] code) {
        SkySSSLog.d(TAG, " sendInfraredCode code=" + code);
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_PARAM_TYPE, PARAM_RET_BYTES);
        bundle.putBoolean(KEY_IS_PLUGIN, true);
        bundle.putInt(KEY_TC_TYPE, TC_DATA_INFO);
        bundle.putByteArray(KEY_PARAM, code);
        executeCmd(SkyConfigDefs.SKY_GFG_TV_INFRARED_LEARNING, bundle, true);
    }

    public void setLearnInfraredCallBack(OnInfraredLearnedCallback callBack, boolean register) {
        SkySSSLog.d(TAG, "setLearnInfraredCallBack callBack=" + callBack + ",register="
                + register);
        String cmd = SkyConfigDefs.SKY_GFC_TV_INFRARED_LEARNING_LISTENER;
        if (register) {
            IPCCallback callback1 = new IPCCallback(callBack, new String[]{
                    "onInfraredLearnedFailed", "onInfraredLearnedSuccess"},
                    new Class[][]{{int.class}, {byte[].class}}, false);
            tcSystemApi.addIPCCallback(cmd, callback1);
            Bundle bundle = new Bundle();
            bundle.putBoolean(KEY_IS_PLUGIN, true);
            executeCmd(cmd, bundle, true);
        } else {
            tcSystemApi.removeIPCCallback(cmd, callBack);
        }
    }

    public void setITCLedConnectedStatusCallBack(ITCLedConnectedStatusCallback callBack, boolean register) {
        SkySSSLog.d(TAG, "setITCLedConnectedStatusCallBack callBack=" + callBack + ",register="
                + register);
        String cmd = SystemProviderDefines.SET_ITC_LED_CONNECTED_STATUS_LISTENER;
        if (register) {
            IPCCallback callback1 = new IPCCallback(callBack, new String[]{
                    "onLedConnectedStatusChange"},
                    new Class[][]{{Boolean.class}}, false);
            tcSystemApi.addIPCCallback(cmd, callback1);
            Bundle bundle = new Bundle();
            bundle.putBoolean(KEY_IS_PLUGIN, true);
            executeCmd(cmd, bundle, true);
        } else {
            tcSystemApi.removeIPCCallback(cmd, callBack);
        }
    }

    public void setITapeLightStateCallBack(ITapeLightStateChangeCallBack callBack, boolean register) {
        SkySSSLog.d(TAG, "setITapeLightStateCallBack callBack=" + callBack + ",register="
                + register);
        String cmd = SkyConfigDefs.SKY_CFG_TV_TAPE_LIGHT_STATE_CHANGE_LISTENER;
        if (register) {
            IPCCallback callback1 = new IPCCallback(callBack, new String[]{
                    "onTapeLightStateChange", "onDeviceConnectStatusChange"},
                    new Class[][]{{TapeLightModeData.class}, {TapeLightStatusData.class}}, false);
            tcSystemApi.addIPCCallback(cmd, callback1);
            Bundle bundle = new Bundle();
            bundle.putBoolean(KEY_IS_PLUGIN, true);
            executeCmd(cmd, bundle, true);
        } else {
            tcSystemApi.removeIPCCallback(cmd, callBack);
        }
    }
}
