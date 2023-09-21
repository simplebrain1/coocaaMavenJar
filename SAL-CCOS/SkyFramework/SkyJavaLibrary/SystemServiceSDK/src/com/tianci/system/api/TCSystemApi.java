package com.tianci.system.api;

import android.content.ComponentName;
import android.content.ContentProviderClient;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.SystemProperties;
import android.text.TextUtils;
import android.util.Log;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.skyworth.api.SystemManagerApi;
import com.skyworth.framework.config.GeneralConfig;
import com.skyworth.framework.config.SettingConfig;
import com.skyworth.framework.event.BroadcastAction;
import com.skyworth.framework.skysdk.ipc.SkyContext;
import com.skyworth.framework.skysdk.properties.SkySystemProperties;
import com.skyworth.framework.skysdk.util.SkyData;
import com.skyworth.framework.skysdk.util.SkyObjectByteSerialzie;
import com.skyworth.framework.utils.SystemPropertiesUtil;
import com.tianci.net.command.TCNetworkCmd;
import com.tianci.net.data.AppUsageInfo;
import com.tianci.net.data.SkyHotSpotData;
import com.tianci.net.data.SkyIpInfo;
import com.tianci.net.data.SkyRouteConfig;
import com.tianci.net.data.SkyWifiAPItem;
import com.tianci.net.data.SkyWifiAPStaticItem;
import com.tianci.net.define.NetworkDefs;
import com.tianci.system.callback.IPCCallback;
import com.tianci.system.callback.ITapeLightUpgradeStatusChangeCallBack;
import com.tianci.system.callback.OnBackLigthChange;
import com.tianci.system.callback.OnSpecularBoostChange;
import com.tianci.system.callback.OnTVColorChange;
import com.tianci.system.callback.OnWifiScanCallback;
import com.tianci.system.callback.RotateHangerCallback;
import com.tianci.system.callback.SerializableBinder;
import com.tianci.system.command.TCSystemCmd;
import com.tianci.system.data.OneKeyActionData;
import com.tianci.system.data.OneKeyData;
import com.tianci.system.data.TCEnumSetData;
import com.tianci.system.data.TCSwitchSetData;
import com.tianci.system.define.LinuxCmd;
import com.tianci.system.define.SkyConfigDefs;
import com.tianci.system.define.SysConst;
import com.tianci.system.define.SystemProviderDefines;
import com.tianci.system.define.TCEnvKey;
import com.tianci.system.helper.TCSystemApiHelper;
import com.tianci.system.listener.HookFaceCallback;
import com.tianci.system.manager.IHookFaceCallBackManager;
import com.tianci.system.manager.IWisaCallBackManager;
import com.tianci.system.listener.OnAodConfigListener;
import com.tianci.system.listener.OnUartDataListener;
import com.tianci.system.listener.WisaCallback;
import com.tianci.system.manager.UartDataListenerManager;
import com.tianci.system.utils.ApiUtil;
import com.tianci.system.utils.ServiceUtil;
import com.tianci.system.utils.SysLog;
import com.tianci.utils.SkySSSLog;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import static com.tianci.system.utils.ApiUtil.KEY_IS_PLUGIN;
import static com.tianci.system.utils.ApiUtil.KEY_PARAM;
import static com.tianci.system.utils.ApiUtil.KEY_PARAM_TYPE;
import static com.tianci.system.utils.ApiUtil.KEY_RESULT;
import static com.tianci.system.utils.ApiUtil.KEY_RET_TYPE;
import static com.tianci.system.utils.ApiUtil.KEY_TC_TYPE;
import static com.tianci.system.utils.ApiUtil.PARAM_RET_BOOL;
import static com.tianci.system.utils.ApiUtil.PARAM_RET_BYTES;
import static com.tianci.system.utils.ApiUtil.PARAM_RET_INT;
import static com.tianci.system.utils.ApiUtil.PARAM_RET_LONG;
import static com.tianci.system.utils.ApiUtil.PARAM_RET_SERIAL;
import static com.tianci.system.utils.ApiUtil.PARAM_RET_STRING;
import static com.tianci.system.utils.ApiUtil.TC_DATA_ENUM;
import static com.tianci.system.utils.ApiUtil.TC_DATA_SWITCH;
import static com.tianci.system.utils.ApiUtil.parseBool;
import static com.tianci.system.utils.ApiUtil.parseInt;

public class TCSystemApi {
    private final static String TAG = "TCSystemApi";
    private static TCSystemApi sTCSystemApi;
    private TCSystemApiHelper mTCSystemApiHelper;
    private UartDataListenerManager mUartDataListenerManager;
    private Context context;

    public static TCSystemApi getInstance(Context context) {
        if (sTCSystemApi == null) {
            synchronized (TCSystemApi.class) {
                if (sTCSystemApi == null) {
                    sTCSystemApi = new TCSystemApi(context);
                }
            }
        }
        return sTCSystemApi;
    }

    public TCSystemApi(Context context) {
        SkySSSLog.w(TAG, "created");
        Context ctx = context.getApplicationContext();
        this.context = context;
        if (ctx == null) {
            ctx = context;
        }
        ContentResolver mContentResolver = ctx.getContentResolver();

        ApiUtil.setContext(ctx);

        mTCSystemApiHelper = new TCSystemApiHelper(mContentResolver, ctx);
        mUartDataListenerManager = new UartDataListenerManager();
    }

    /**
     * 获取系统api版本
     *
     * @return api版本
     */
    public static int getApiLevel() {
        Bundle bundle = new Bundle();
        ApiUtil.setParams(bundle, SystemProviderDefines.METHOD_COMMON,
                SystemProviderDefines.COMMON_API_LEVEL, null, false);
        Integer ret = ApiUtil.getData(bundle, Integer.class);
        int result = ret != null ? ret : 0;
        SkySSSLog.i(TAG, "getApiLevel result=" + result);
        return result;
    }

    /**
     * 发送UART数据 uartChannel UART通道， data发送的数据
     * uartChannel: UART通道
     * SysConst.UART_CHANNEL_INFRARED = 0;
     * SysConst.UART_CHANNEL_FRIDGE = 1;
     * SysConst.UART_CHANNEL_ZIGBEE = 2;
     * SysConst.UART_CHANNEL_DEVICEWELL = 3;
     * SysConst.E_UART_CHANNEL_USB_GENERAL = 4;
     * data: 要发送的数据
     */
    public boolean sendUartData(int uartChannel, byte[] data) {
        if (data == null) {
            return false;
        }
        ContentProviderClient client = ApiUtil.getClient();
        if (client == null) {
            return false;
        }
        Bundle bundle = new Bundle();
        bundle.putInt("uartChannel", uartChannel);
        bundle.putByteArray("data", data);
        Bundle ret = null;
        try {
            ret = client.call(SystemProviderDefines.METHOD_SYSTEM_SEND_UART, null, bundle);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (ret == null) {
            return false;
        }
        return ret.getBoolean("ret");
    }

    /**
     * 设置UartDataListener监听，接收UART数据
     * uartChannel: UART通道
     * SysConst.UART_CHANNEL_INFRARED = 0;
     * SysConst.UART_CHANNEL_FRIDGE = 1;
     * SysConst.UART_CHANNEL_ZIGBEE = 2;
     * SysConst.UART_CHANNEL_DEVICEWELL = 3;
     * SysConst.E_UART_CHANNEL_USB_GENERAL = 4;
     */
    public boolean setOnUartDataListener(int uartChannel, OnUartDataListener listener) {
        SerializableBinder binderListener = mUartDataListenerManager.setOnUartDataListener(listener);
        if (binderListener == null) {
            return false;
        }
        ContentProviderClient client = ApiUtil.getClient();
        if (client == null) {
            return false;
        }
        Bundle bundle = new Bundle();
        bundle.putInt("uartChannel", uartChannel);
        bundle.putBoolean("isSet", listener != null ? true : false);
        bundle.putSerializable("listener", binderListener);
        Bundle ret = null;
        try {
            ret = client.call(SystemProviderDefines.METHOD_SYSTEM_SET_UART_LISTENER, null, bundle);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (ret == null) {
            return false;
        }
        return ret.getBoolean("ret");
    }

    /**
     * 发送UART数据，并且同步接收
     *
     * @param uartChannel UART通道， data发送的数据
     * @return data
     */
    public byte[] sendUartDataSync(int uartChannel, byte[] data) {
        if (data == null) {
            return null;
        }
        ContentProviderClient client = ApiUtil.getClient();
        if (client == null) {
            return null;
        }
        Bundle bundle = new Bundle();
        bundle.putInt("uartChannel", uartChannel);
        bundle.putByteArray("data", data);
        try {
            Bundle ret = client.call(SystemProviderDefines.METHOD_SYSTEM_SEND_UART_SYNC, null, bundle);
            if (ret == null) {
                return null;
            }
            return ret.getByteArray("ret");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取设置AI开关值
     *
     * @return true:AI待机开启；false:AI待机关闭
     */
    public boolean getAIStandbyOnOff() {
        Bundle bundle = new Bundle();
        Bundle param = new Bundle();
        param.putString(KEY_PARAM, SystemProviderDefines.METHOD_AOD_AI_SWITCH);
        ApiUtil.setParams(bundle, SystemProviderDefines.METHOD_COMMON,
                SystemProviderDefines.METHOD_AOD, param, false);
        Boolean ret = ApiUtil.getData(bundle, Boolean.class);
        SkySSSLog.d(TAG, "getAIStandbyOnOff ret=" + ret);
        return ret == null ? false : ret;
    }

    /**
     * 获取主屏AOD待机模式
     *
     * @return {@link SysConst#SKY_CFG_TV_AOD_WAKE_STANDBY_MODE},
     * {@link SysConst#SKY_CFG_TV_AOD_AI_STANDBY_MODE}
     */
    public int getAodMainState() {
        Bundle bundle = new Bundle();
        Bundle param = new Bundle();
        param.putInt("type", 1);
        param.putString(KEY_PARAM, SystemProviderDefines.METHOD_AOD_SWITCH);
        ApiUtil.setParams(bundle, SystemProviderDefines.METHOD_COMMON,
                SystemProviderDefines.METHOD_AOD, param, false);
        Integer ret = ApiUtil.getData(bundle, Integer.class);
        SkySSSLog.d(TAG, "getAodMainState ret=" + ret);
        return ret == null ? -1 : ret;
    }

    /**
     * 获取副屏AOD待机模式
     *
     * @return {@link SysConst#SKY_CFG_TV_AOD_WAKE_ALWAY_MODE_ASSISTANT},
     * {@link SysConst#SKY_CFG_TV_AOD_WAKE_STANDBY_MODE_ASSISTANT},
     * {@link SysConst#SKY_CFG_TV_AOD_WAKE_SYSTEM_MODE_ASSISTANT}
     */
    public int getAodSubState() {
        Bundle bundle = new Bundle();
        Bundle param = new Bundle();
        param.putInt("type", 2);
        param.putString(KEY_PARAM, SystemProviderDefines.METHOD_AOD_SWITCH);
        ApiUtil.setParams(bundle, SystemProviderDefines.METHOD_COMMON,
                SystemProviderDefines.METHOD_AOD, param, false);
        Integer ret = ApiUtil.getData(bundle, Integer.class);
        int result = ret == null ? 0 : ret;
        SkySSSLog.d(TAG, "getAodSubState result=" + result);
        return result;
    }

    /**
     * 注册AOD相关设置项变更监听
     */
    public void registerAodConfigListener(OnAodConfigListener configCallback) {
        SkySSSLog.d(TAG, "registerAodConfigListener");
        mTCSystemApiHelper.registerAodConfigListener(configCallback);
    }

    /**
     * 解注册AOD相关设置项变更监听
     */
    public void unRegisterAodConfigListener() {
        SkySSSLog.d(TAG, "unRegisterAodConfigListener");
        mTCSystemApiHelper.unRegisterAodConfigListener();
    }

    @SuppressWarnings("unchecked")
    private <T> T getCommonData(String key, Class<T> cls) {
        if (cls == null) {
            return null;
        }
        Bundle ret = null;
        ContentProviderClient client = ApiUtil.getClient();
        if (client == null) {
            return null;
        }
        try {
            ret = client.call(SystemProviderDefines.METHOD_COMMON, key, null);
        } catch (Exception e) {
            SkySSSLog.e(TAG, "getCommonData err=" + e.getMessage());
        }
        if (ret == null) {
            return null;
        }
        T result = null;
        if (cls.getSimpleName().equals("Integer")) {
            result = (T) (Integer) ret.getInt(KEY_RESULT);
        } else if (cls.getSimpleName().equals("String")) {
            result = (T) ret.getString(KEY_RESULT);
        } else if (cls.getSimpleName().equals("Boolean")) {
            result = (T) (Boolean) ret.getBoolean(KEY_RESULT);
        }
        SkySSSLog.d(TAG, "getCommonData result=" + result);
        return result;
    }

//    /**
//     * 电视屏幕是否是触摸屏
//     *
//     * @return true：是
//     */
//    public boolean isTouchScreen() {
//        Object result = getCommonData(SystemProviderDefines.COMMON_IS_TOUCH_SCREEN, Boolean.class);
//        SkySSSLog.d(TAG, "isTouchScreen result=" + result);
//        if (result == null) {
//            return false;
//        }
//        return (Boolean) result;
//    }
//

    /**
     * 是否支持环境光
     *
     * @return true:支持
     */
    public boolean isEnvironmentLight() {
        Boolean result = getCommonData(SystemProviderDefines.COMMON_IS_ENVIRONMENT_LIGHT_SUPPORT, Boolean.class);
        SkySSSLog.d(TAG, "isEnvironmentLight result=" + result);
        if (result == null) {
            return false;
        }
        return result;
    }

    /**
     * 设置EDID后是否需要重启
     *
     * @return true:需要重启
     */
    public boolean isNeedRebootAfterSetEDID() {
        Boolean result = getCommonData(SystemProviderDefines.COMMON_IS_REBOOT_EDID, Boolean.class);
        SkySSSLog.d(TAG, "isNeedRebootAfterSetEDID result=" + result);
        if (result == null) {
            return true;
        }
        return result;
    }

    /**
     * 是否支持CMO
     *
     * @return true:支持
     */
    public boolean isSupportCMO() {
        Boolean result = getCommonData(SystemProviderDefines.COMMON_IS_SUPPORT_CMO, Boolean.class);
        SkySSSLog.d(TAG, "isSupportCMO result=" + result);
        if (result == null) {
            return true;
        }
        return result;
    }

//    public boolean installApk(String apkPathName) {
////        if (TextUtils.isEmpty(apkPathName))
////            return false;
////        if (mContentResolver == null) {
////            return false;
////        }
////        Bundle bundle = new Bundle();
////        bundle.putString("apkPathName", apkPathName);
////        Bundle ret = null;
////        try {
////            ret = mContentResolver.call(Uri.parse(SystemProviderDefines.URI_PATH_METHOD), SystemProviderDefines.METHOD_INSTALL_APK, null, bundle);
////        } catch (Exception e) {
////            e.printStackTrace();
////        }
////        if (ret == null) {
////            return false;
////        }
////        return ret.getBoolean("ret");
////    }

    /**
     * 设置旋转挂架监听
     */
    public void setRotateHangerCallback(RotateHangerCallback callback) {
        mTCSystemApiHelper.setRotateHangerCallback(callback);
    }

    /**
     * 切换旋转挂架方向
     */
    public void setRotateHangerOrientation() {
        Bundle bundle = new Bundle();
        ApiUtil.setParams(bundle, SystemProviderDefines.METHOD_COMMON,
                SystemProviderDefines.COMMON_RH_IS_ENABLE_OR_SWITCH, null, true);
        boolean ret = ApiUtil.setData(bundle);
        SkySSSLog.i(TAG, "setRotateHangerOrientation ret=" + ret);
    }

    /**
     * 旋转挂架能否切换
     */
    public boolean rotateHangerCanSwitch() {
        Bundle bundle = new Bundle();
        ApiUtil.setParams(bundle, SystemProviderDefines.METHOD_COMMON,
                SystemProviderDefines.COMMON_RH_IS_ENABLE_OR_SWITCH, null, false);
        Boolean ret = ApiUtil.getData(bundle, Boolean.class);
        boolean can = false;
        SkySSSLog.i(TAG, "rotateHangerCanSwitch ret=" + ret);
        if (ret != null) {
            can = ret;
        }
        return can;
    }

    /**
     * 获取挂架当前方向
     * *@return true：竖屏；false：横屏
     */
    public boolean rotateHangerIsPortrait() {
        Bundle bundle = new Bundle();
        Bundle param = new Bundle();
        param.putString("param", "orientation");
        ApiUtil.setParams(bundle, SystemProviderDefines.METHOD_COMMON,
                SystemProviderDefines.COMMON_RH_IS_ENABLE_OR_SWITCH, param, false);
        Boolean ret = ApiUtil.getData(bundle, Boolean.class);
        boolean portrait = false;
        SkySSSLog.i(TAG, "rotateHangerIsPortrait ret=" + ret);
        if (ret != null) {
            portrait = ret;
        }
        return portrait;
    }

    /**
     * 旋转挂架自动旋转开关是否开启
     * *@return true：是；false：否
     */
    public boolean rotateHangerIsAutoRotate() {
        Bundle bundle = new Bundle();
        Bundle param = new Bundle();
        param.putString("param", "autoRotate");
        ApiUtil.setParams(bundle, SystemProviderDefines.METHOD_COMMON,
                SystemProviderDefines.COMMON_RH_IS_ENABLE_OR_SWITCH, param, false);
        Boolean ret = ApiUtil.getData(bundle, Boolean.class);
        boolean portrait = false;
        SkySSSLog.i(TAG, "rotateHangerIsAutoRotate ret=" + ret);
        if (ret != null) {
            portrait = ret;
        }
        return portrait;
    }

    /**
     * 调整摄像头拍摄角度等级
     *
     * @param level 等级0-10
     */
    public void setCameraAngleLevel(int level) {
        Bundle bundle = new Bundle();
        Bundle param = new Bundle();
        param.putInt(ServiceUtil.MENU_SELECT_INDEX_KEY, level);
        ApiUtil.setParams(bundle, SystemProviderDefines.METHOD_COMMON,
                SystemProviderDefines.COMMON_ADJUST_CAMERA_ANGLE, param, true);
        boolean ret = ApiUtil.setData(bundle);
        SkySSSLog.i(TAG, "setCameraAngleLevel ret=" + ret);
    }

    /**
     * 获取摄像头拍摄角度等级
     *
     * @return int 当前拍摄等级
     */
    public int getCameraAngleLevel() {
        Bundle bundle = new Bundle();
        ApiUtil.setParams(bundle, SystemProviderDefines.METHOD_COMMON,
                SystemProviderDefines.COMMON_ADJUST_CAMERA_ANGLE, null, false);
        Integer ret = ApiUtil.getData(bundle, Integer.class);
        SkySSSLog.i(TAG, "getCameraAngleLevel ret=" + ret);
        if (ret != null) {
            return ret;
        }
        return 0;
    }

    /**
     * 调整摄像头变焦等级
     *
     * @param level 等级0-40
     */
    public void setCameraZoomLevel(int level) {
        Bundle bundle = new Bundle();
        Bundle param = new Bundle();
        param.putInt(ServiceUtil.MENU_SELECT_INDEX_KEY, level);
        ApiUtil.setParams(bundle, SystemProviderDefines.METHOD_COMMON,
                SystemProviderDefines.COMMON_ADJUST_CAMERA_ZOOM, param, true);
        boolean ret = ApiUtil.setData(bundle);
        SkySSSLog.d(TAG, "setCameraZoomLevel ret=" + ret);
    }

    /**
     * 获取摄像头变焦等级
     *
     * @return int 当前变焦等级
     */
    public int getCameraZoomLevel() {
        Bundle bundle = new Bundle();
        ApiUtil.setParams(bundle, SystemProviderDefines.METHOD_COMMON,
                SystemProviderDefines.COMMON_ADJUST_CAMERA_ZOOM, null, false);
        Integer ret = ApiUtil.getData(bundle, Integer.class);
        SkySSSLog.d(TAG, "getCameraZoomLevel ret=" + ret);
        if (ret != null) {
            return ret;
        }
        return 0;
    }

    /**
     * 摄像头升降状态
     *
     * @return true：升，false ：降
     */
    public boolean isCameraIsUp() {
        Bundle bundle = new Bundle();
        ApiUtil.setParams(bundle, SystemProviderDefines.METHOD_COMMON,
                SystemProviderDefines.COMMON_CAMERA_STATE,
                null, false);
        Boolean ret = ApiUtil.getData(bundle, Boolean.class);
        SkySSSLog.d(TAG, "isCameraIsUp ret=" + ret);
        if (ret != null) {
            return ret;
        }
        return false;
    }

    /**
     * 悬浮球开关
     *
     * @param isOn true开启悬浮球
     */
    public void setFloatBallSwitch(boolean isOn) {
        Bundle param = new Bundle();
        param.putInt(KEY_TC_TYPE, TC_DATA_SWITCH);
        param.putInt(KEY_PARAM_TYPE, PARAM_RET_BYTES);
        param.putBoolean(KEY_IS_PLUGIN, true);
        param.putByteArray(KEY_PARAM, String.valueOf(isOn).getBytes());
        Bundle ret = executeCmd(SkyConfigDefs.SKY_CFG_TV_FLOATING_BALL, param, true);
        SkySSSLog.i(TAG, "setFloatBallSwitch ret=" + ret);
    }

    /**
     * 获取悬浮球开关状态
     *
     * @return true开启
     */
    public boolean getFloatBallSwitch() {
        Bundle param = new Bundle();
        param.putInt(KEY_RET_TYPE, PARAM_RET_BYTES);
        param.putInt(KEY_TC_TYPE, TC_DATA_SWITCH);
        param.putBoolean(KEY_IS_PLUGIN, true);
        Bundle ret = executeCmd(SkyConfigDefs.SKY_CFG_TV_FLOATING_BALL, param, false);
        boolean result = parseBool(ret.getByteArray(KEY_RESULT));
        SkySSSLog.i(TAG, "getFloatBallSwitch result=" + result);
        return result;
    }

    /**
     * to set music light effect switch
     *
     * @param isOn true
     */
    public void setMusicLightEffectSwitch(boolean isOn) {
        Bundle param = new Bundle();
        param.putInt(KEY_TC_TYPE, TC_DATA_SWITCH);
        param.putInt(KEY_PARAM_TYPE, PARAM_RET_BYTES);
        param.putBoolean(KEY_IS_PLUGIN, true);
        param.putByteArray(KEY_PARAM, String.valueOf(isOn).getBytes());
        Bundle ret = executeCmd(SkyConfigDefs.SKY_CFG_MUSIC_LIGHT_EFFECT_SWITCH, param, true);
        SkySSSLog.i(TAG, "setMusicLightEffectSwitch ret=" + ret);
    }

    /**
     * to get music light effect switch state
     *
     * @return true open
     */
    public boolean getMusicLightEffectSwitch() {
        Bundle param = new Bundle();
        param.putInt(KEY_RET_TYPE, PARAM_RET_BYTES);
        param.putInt(KEY_TC_TYPE, TC_DATA_SWITCH);
        param.putBoolean(KEY_IS_PLUGIN, true);
        Bundle ret = executeCmd(SkyConfigDefs.SKY_CFG_MUSIC_LIGHT_EFFECT_SWITCH, param, false);
        boolean result = parseBool(ret.getByteArray(KEY_RESULT));
        SkySSSLog.i(TAG, "getMusicLightEffectSwitch result=" + result);
        return result;
    }


    private Bundle executeCmd(String cmd, Bundle body, boolean isSet) {
        return ApiUtil.executeSystemCmd(cmd, body, isSet);
    }

    public void addIPCCallback(String cmd, IPCCallback ipcCallback) {
        mTCSystemApiHelper.addIPCCallback(cmd, ipcCallback);
    }

    private void addBroadcastCallback(String cmd, IPCCallback ipcCallback) {
        mTCSystemApiHelper.addBroadcastCallback(cmd, ipcCallback);
    }

    public void removeIPCCallback(String cmd, Object callback) {
        mTCSystemApiHelper.removeIPCCallback(cmd, callback);
    }

    /**
     * 获取当前已连接的无线网络的SSID
     *
     * @return 无线网络的SSID
     */
    public String getConnectWifiSSID() {
        Bundle param = new Bundle();
        param.putInt(KEY_RET_TYPE, PARAM_RET_SERIAL);
        Bundle ret = executeCmd(TCNetworkCmd.TC_NETWORK_CMD_WIFI_GET_CONNECT_SSID.toString(),
                param, false);
        byte[] bytes = ret.getByteArray(KEY_RESULT);
        if (bytes == null) {
            SkySSSLog.i(TAG, "getConnectWifiSSID result is null");
            return null;
        }
        String result = new String(bytes);
        SkySSSLog.i(TAG, "getConnectWifiSSID result=" + result);
        return result;
    }

    /**
     * 获取当前系统已存在的无线列表
     *
     * @return 无线列表
     */
    public List<SkyWifiAPItem> getWifiList() {
        Bundle param = new Bundle();
        param.putInt(KEY_RET_TYPE, PARAM_RET_SERIAL);
        Bundle ret = executeCmd(TCNetworkCmd.TC_NETWORK_CMD_WIFI_GET_CURRENT_WIFI_INFO_LIST.toString(),
                param, false);
        byte[] bytes = ret.getByteArray(KEY_RESULT);
        List<SkyWifiAPItem> list = SkyObjectByteSerialzie.toObject(bytes, List.class);
        SkySSSLog.i(TAG, "getWifiList ret=" + ret);
        return list;
    }

    /**
     * 启动搜索周围无线网络功能
     */
    public void startWifiScan(OnWifiScanCallback callback) {
        IPCCallback callback1 = new IPCCallback(callback, new String[]{"onWifiScanned"},
                new Class[][]{{List.class}}, false);
        String cmd = TCNetworkCmd.TC_NETWORK_CMD_WIFI_START_SCAN_WIFI_INFO_LIST.toString();
        addBroadcastCallback(cmd, callback1);
        Bundle ret = executeCmd(cmd, null, true);
        SkySSSLog.i(TAG, "startWifiScan ret=" + ret);
    }

    /**
     * 解注册wifi扫描结果回调
     */
    public void cancelWifiScan(OnWifiScanCallback callback) {
        removeIPCCallback(TCNetworkCmd.TC_NETWORK_CMD_WIFI_START_SCAN_WIFI_INFO_LIST.toString(), callback);
    }

    /**
     * 无线网络连接状态
     *
     * @return 连接状态
     */
    public String getWifiConnectState() {
        Bundle param = new Bundle();
        param.putInt(KEY_RET_TYPE, PARAM_RET_SERIAL);
        Bundle ret = executeCmd(TCNetworkCmd.TC_NETWORK_CMD_WIFI_GET_CONNECT_STATE.toString(),
                param, false);
        byte[] bytes = ret.getByteArray(KEY_RESULT);
        if (bytes == null) {
            SkySSSLog.i(TAG, "getWifiConnectState result is null");
            return null;
        }
        String result = new String(bytes);
        SkySSSLog.i(TAG, "getWifiConnectState result=" + result);
        return result;
    }

    /**
     * 获取无线模块是否可用
     *
     * @return true可用
     */
    public boolean getWifiEnable() {
        Bundle param = new Bundle();
        param.putInt(KEY_RET_TYPE, PARAM_RET_SERIAL);
        Bundle ret = executeCmd(TCNetworkCmd.TC_NETWORK_CMD_WIFI_GET_ENABLE.toString(),
                param, false);
        boolean result = parseBool(ret.getByteArray(KEY_RESULT));
        SkySSSLog.i(TAG, "getWifiEnable result=" + result);
        return result;
    }

    /**
     * 设置无线模块是否可用
     *
     * @param enable true可用
     */
    public boolean setWifiEnable(boolean enable) {
        Bundle param = new Bundle();
        param.putInt(KEY_PARAM_TYPE, PARAM_RET_SERIAL);
        param.putInt(KEY_RET_TYPE, PARAM_RET_SERIAL);
        param.putByteArray(KEY_PARAM, String.valueOf(enable).getBytes());
        Bundle ret = executeCmd(TCNetworkCmd.TC_NETWORK_CMD_WIFI_SET_ENABLE.toString(),
                param, true);
        boolean result = parseBool(ret.getByteArray(KEY_RESULT));
        SkySSSLog.i(TAG, "setWifiEnable result=" + result + ",enable=" + enable);
        return result;
    }

    /**
     * 动态配置连接无线网络
     *
     * @param item 配置参数
     * @return true配置成功
     */
    public boolean configWifiByDHCP(SkyWifiAPItem item) {
        Bundle param = new Bundle();
        param.putInt(KEY_PARAM_TYPE, PARAM_RET_BYTES);
        param.putInt(KEY_RET_TYPE, PARAM_RET_SERIAL);
        param.putByteArray(KEY_PARAM, SkyObjectByteSerialzie.toBytes(item));
        Bundle ret = executeCmd(TCNetworkCmd.TC_NETWORK_CMD_WIFI_CONNECT_BY_DHCP.toString(),
                param, true);
        boolean result = parseBool(ret.getByteArray(KEY_RESULT));
        SkySSSLog.i(TAG, "configWifiByDHCP result=" + result);
        return result;
    }

    /**
     * 静态配置连接无线网络
     *
     * @param item 配置参数
     * @return true配置成功
     */
    public boolean configWifiByStatic(SkyWifiAPStaticItem item) {
        Bundle param = new Bundle();
        param.putInt(KEY_PARAM_TYPE, PARAM_RET_BYTES);
        param.putInt(KEY_RET_TYPE, PARAM_RET_SERIAL);
        param.putByteArray(KEY_PARAM, SkyObjectByteSerialzie.toBytes(item));
        Bundle ret = executeCmd(TCNetworkCmd.TC_NETWORK_CMD_WIFI_CONNECT_BY_STATIC.toString(),
                param, true);
        boolean result = parseBool(ret.getByteArray(KEY_RESULT));
        SkySSSLog.i(TAG, "configWifiByStatic result=" + result);
        return result;
    }

    /**
     * 动态配置连接无线网络,非HiddenSSID
     *
     * @param item 配置参数
     * @return true配置成功
     */
    public boolean configWifiByDHCPNotHiddenSSID(SkyWifiAPItem item) {
        Bundle param = new Bundle();
        param.putInt(KEY_PARAM_TYPE, PARAM_RET_BYTES);
        param.putInt(KEY_RET_TYPE, PARAM_RET_SERIAL);
        param.putByteArray(KEY_PARAM, SkyObjectByteSerialzie.toBytes(item));
        Bundle ret = executeCmd(TCNetworkCmd.TC_NETWORK_CMD_WIFI_CONNECT_BY_DHCP_NOT_HIDDENSSID.toString(),
                param, true);
        boolean result = parseBool(ret.getByteArray(KEY_RESULT));
        SkySSSLog.i(TAG, "configWifiByDHCPNotHiddenSSID result=" + result);
        return result;
    }

    /**
     * 静态配置连接无线网络,非HiddenSSID
     *
     * @param item 配置参数
     * @return true配置成功
     */
    public boolean configWifiByStaticNotHiddenSSID(SkyWifiAPStaticItem item) {
        Bundle param = new Bundle();
        param.putInt(KEY_PARAM_TYPE, PARAM_RET_BYTES);
        param.putInt(KEY_RET_TYPE, PARAM_RET_SERIAL);
        param.putByteArray(KEY_PARAM, SkyObjectByteSerialzie.toBytes(item));
        Bundle ret = executeCmd(TCNetworkCmd.TC_NETWORK_CMD_WIFI_CONNECT_BY_STATIC_NOT_HIDDENSSID.toString(),
                param, false);
        boolean result = parseBool(ret.getByteArray(KEY_RESULT));
        SkySSSLog.i(TAG, "configWifiByStaticNotHiddenSSID result=" + result);
        return result;
    }

    /**
     * 是否支持无线一键通
     *
     * @return true支持
     */
    public boolean isSupportWps() {
        Bundle param = new Bundle();
        param.putInt(KEY_RET_TYPE, PARAM_RET_SERIAL);
        Bundle ret = executeCmd(TCNetworkCmd.TC_NETWORK_CMD_WIFI_IS_SUPPORT_WPS.toString(),
                param, false);
        boolean result = parseBool(ret.getByteArray(KEY_RESULT));
        SkySSSLog.i(TAG, "isSupportWps result=" + result);
        return result;
    }

    /**
     * 是否支持AP
     *
     * @return true支持
     */
    public boolean isSupportHotSpot() {
        Bundle param = new Bundle();
        param.putInt(KEY_RET_TYPE, PARAM_RET_SERIAL);
        Bundle ret = executeCmd(TCNetworkCmd.TC_NETWORK_CMD_WIFI_IS_SUPPORT_HOTSPOT.toString(),
                param, false);
        boolean result = parseBool(ret.getByteArray(KEY_RESULT));
        SkySSSLog.i(TAG, "isSupportHotSpot result=" + result);
        return result;
    }

    /**
     * 启动AP
     *
     * @return true启动成功
     */
    public boolean startHotSpot(SkyHotSpotData hotSpotData) {
        Bundle param = new Bundle();
        param.putInt(KEY_PARAM_TYPE, PARAM_RET_BYTES);
        param.putInt(KEY_RET_TYPE, PARAM_RET_SERIAL);
        param.putByteArray(KEY_PARAM, SkyObjectByteSerialzie.toBytes(hotSpotData));
        Bundle ret = executeCmd(TCNetworkCmd.TC_NETWORK_CMD_WIFI_START_HOTSPOT.toString(),
                param, true);
        boolean result = parseBool(ret.getByteArray(KEY_RESULT));
        SkySSSLog.i(TAG, "startHotSpot result=" + result);
        return result;
    }

    /**
     * 关闭Ap
     *
     * @return true关闭成功
     */
    public boolean closeHotSpot() {
        Bundle param = new Bundle();
        param.putInt(KEY_RET_TYPE, PARAM_RET_SERIAL);
        Bundle ret = executeCmd(TCNetworkCmd.TC_NETWORK_CMD_WIFI_CLOSE_HOTSPOT.toString(),
                param, true);
        boolean result = parseBool(ret.getByteArray(KEY_RESULT));
        SkySSSLog.i(TAG, "closeHotSpot result=" + result);
        return result;
    }

    /**
     * 获取本地缓存的无线AP列表信息
     *
     * @return 无线AP列表信息
     */
    public List<String> getWifiLocalApList() {
        Bundle param = new Bundle();
        param.putInt(KEY_RET_TYPE, PARAM_RET_SERIAL);
        Bundle ret = executeCmd(TCNetworkCmd.TC_NETWORK_CMD_WIFI_GET_LOCAL_AP_LIST.toString(),
                param, false);
        byte[] bytes = ret.getByteArray(KEY_RESULT);
        List<String> result = SkyObjectByteSerialzie.toObject(bytes, List.class);
        SkySSSLog.i(TAG, "getWifiLocalApList result=" + result);
        return result;
    }

    /**
     * 获取上一次对应SSID的ap保存的密码
     *
     * @param ssid the ssid
     * @return 密码 pwd by ssid
     */
    public String getPwdBySSID(String ssid) {
        Bundle param = new Bundle();
        param.putInt(KEY_PARAM_TYPE, PARAM_RET_STRING);
        param.putInt(KEY_RET_TYPE, PARAM_RET_SERIAL);
        param.putString(KEY_PARAM, ssid);
        Bundle ret = executeCmd(TCNetworkCmd.TC_NETWORK_CMD_WIFI_GET_LAST_PWD_BY_SSID.toString(),
                param, false);
        byte[] bytes = ret.getByteArray(KEY_RESULT);
        if (bytes == null) {
            SkySSSLog.i(TAG, "getPwdBySSID result is null");
            return null;
        }
        String result = new String(bytes);
        SkySSSLog.i(TAG, "getPwdBySSID result=" + result);
        return result;
    }

    /**
     * 获取当前连接wifi的信号强度
     *
     * @return wifi信号强度
     */
    public int getRssi() {
        Bundle param = new Bundle();
        param.putInt(KEY_RET_TYPE, PARAM_RET_SERIAL);
        Bundle ret = executeCmd(TCNetworkCmd.TC_NETWORK_CMD_WIFI_GET_RSSI.toString(),
                param, false);
        int result = parseInt(ret.getByteArray(KEY_RESULT));
        SkySSSLog.i(TAG, "getRssi result=" + result);
        return result;
    }

    /**
     * 忽略某个指定的ap
     *
     * @param ssid 被忽略的ssid
     * @return true 操作成功
     */
    public boolean forgetAp(String ssid) {
        Bundle param = new Bundle();
        param.putInt(KEY_PARAM_TYPE, PARAM_RET_STRING);
        param.putInt(KEY_RET_TYPE, PARAM_RET_SERIAL);
        param.putString(KEY_PARAM, ssid);
        Bundle ret = executeCmd(TCNetworkCmd.TC_NETWORK_CMD_WIFI_FORGET_AP.toString(),
                param, true);
        boolean result = parseBool(ret.getByteArray(KEY_RESULT));
        SkySSSLog.i(TAG, "forgetAp result=" + result);
        return result;
    }

    /**
     * 重置网络
     *
     * @return true 操作成功
     */
    public boolean resetNet() {
        Bundle ret = executeCmd(TCNetworkCmd.TC_NETWORK_CMD_RESET_NET.toString(),
                new Bundle(), true);
        boolean result = parseBool(ret.getByteArray(KEY_RESULT));
        SkySSSLog.i(TAG, "forgetAp result=" + result);
        return result;
    }

    /**
     * 获取Ap状态
     *
     * @return {@link NetworkDefs.HotspotState#AP_STATE_DISABLING},{@link NetworkDefs.HotspotState#AP_STATE_DISABLED},
     * {@link NetworkDefs.HotspotState#AP_STATE_ENABLING},{@link NetworkDefs.HotspotState#AP_STATE_ENABLED},
     * {@link NetworkDefs.HotspotState#AP_STATE_FAILED}
     */
    public int getHotspotState() {
        Bundle param = new Bundle();
        param.putInt(KEY_RET_TYPE, PARAM_RET_SERIAL);
        Bundle ret = executeCmd(TCNetworkCmd.TC_NETWORK_CMD_WIFI_GET_HOTSPOT_STATE.toString(),
                param, false);
        int result = parseInt(ret.getByteArray(KEY_RESULT));
        SkySSSLog.i(TAG, "getHotspotState result=" + result);
        return result;
    }

    /**
     * 获取当前连接wifi的相关信息
     *
     * @return Ap状态
     */
    public SkyWifiAPItem getWifiInfo() {
        Bundle param = new Bundle();
        param.putInt(KEY_RET_TYPE, PARAM_RET_BYTES);
        Bundle ret = executeCmd(TCNetworkCmd.TC_NETWORK_CMD_WIFI_GET_CURRENT_CONNECTED_AP_INFO.toString(),
                param, false);
        byte[] bytes = ret.getByteArray(KEY_RESULT);
        SkyWifiAPItem result = SkyObjectByteSerialzie.toObject(bytes, SkyWifiAPItem.class);
        SkySSSLog.i(TAG, "getWifiInfo result=" + result);
        return result;
    }

    /**
     * 获取某个ap是否配置过
     *
     * @return true 配置过
     */
    public boolean isAPConfigured(SkyWifiAPItem item) {
        Bundle param = new Bundle();
        param.putInt(KEY_PARAM_TYPE, PARAM_RET_BYTES);
        param.putInt(KEY_RET_TYPE, PARAM_RET_SERIAL);
        param.putByteArray(KEY_PARAM, SkyObjectByteSerialzie.toBytes(item));
        Bundle ret = executeCmd(TCNetworkCmd.TC_NETWORK_CMD_WIFI_GET_AP_IS_CONFIG.toString(),
                param, true);
        boolean result = parseBool(ret.getByteArray(KEY_RESULT));
        SkySSSLog.i(TAG, "isAPConfigured result=" + result);
        return result;
    }

    /**
     * 保存某个ap的配置
     *
     * @return true 保存成功
     */
    public boolean saveAPConfig(SkyWifiAPStaticItem item) {
        Bundle param = new Bundle();
        param.putInt(KEY_PARAM_TYPE, PARAM_RET_BYTES);
        param.putInt(KEY_RET_TYPE, PARAM_RET_SERIAL);
        param.putByteArray(KEY_PARAM, SkyObjectByteSerialzie.toBytes(item));
        Bundle ret = executeCmd(TCNetworkCmd.TC_NETWORK_CMD_WIFI_SAVE_AP_CONFIG.toString(),
                param, true);
        boolean result = parseBool(ret.getByteArray(KEY_RESULT));
        SkySSSLog.i(TAG, "saveAPConfig result=" + result);
        return result;
    }

    /**
     * 获取网线是否插上
     *
     * @return true 插上
     */
    public boolean isCableConnected() {
        Bundle param = new Bundle();
        param.putInt(KEY_RET_TYPE, PARAM_RET_SERIAL);
        Bundle ret = executeCmd(TCNetworkCmd.TC_NETWORK_CMD_ETHERNET_IS_CABLE_CONNECT.toString(),
                param, false);
        boolean result = parseBool(ret.getByteArray(KEY_RESULT));
        SkySSSLog.i(TAG, "isCableConnected result=" + result);
        return result;
    }

    /**
     * 获取有线模块是否可用
     *
     * @return true 可用
     */
    public boolean isEthernetEnable() {
        Bundle param = new Bundle();
        param.putInt(KEY_RET_TYPE, PARAM_RET_SERIAL);
        Bundle ret = executeCmd(TCNetworkCmd.TC_NETWORK_CMD_ETHERNET_GET_ENABLE.toString(),
                param, false);
        boolean result = parseBool(ret.getByteArray(KEY_RESULT));
        SkySSSLog.i(TAG, "isEthernetEnable result=" + result);
        return result;
    }

    /**
     * 动态连接以太网
     *
     * @return true配置成功
     */
    public boolean configEthernetByDHCP() {
        Bundle param = new Bundle();
        param.putInt(KEY_RET_TYPE, PARAM_RET_SERIAL);
        Bundle ret = executeCmd(TCNetworkCmd.TC_NETWORK_CMD_ETHERNET_CONNECT_BY_DHCP.toString(),
                param, true);
        boolean result = parseBool(ret.getByteArray(KEY_RESULT));
        SkySSSLog.i(TAG, "configEthernetByDHCP result=" + result);
        return result;
    }

    /**
     * 静态连接以太网
     *
     * @param ipInfo 配置参数
     * @return true配置成功
     */
    public boolean configEthernetByStatic(SkyIpInfo ipInfo) {
        Bundle param = new Bundle();
        param.putInt(KEY_PARAM_TYPE, PARAM_RET_BYTES);
        param.putInt(KEY_RET_TYPE, PARAM_RET_SERIAL);
        param.putByteArray(KEY_PARAM, SkyObjectByteSerialzie.toBytes(ipInfo));
        Bundle ret = executeCmd(TCNetworkCmd.TC_NETWORK_CMD_ETHERNET_CONNECT_BY_STATIC.toString(),
                param, true);
        boolean result = parseBool(ret.getByteArray(KEY_RESULT));
        SkySSSLog.i(TAG, "configEthernetByStatic result=" + result);
        return result;
    }

    /**
     * 以太网连接状态
     *
     * @return {@link NetworkDefs.DevConnectStatus#CONNECTING},{@link NetworkDefs.DevConnectStatus#CONNECTED},
     * {@link NetworkDefs.DevConnectStatus#DISCONNECTING},{@link NetworkDefs.DevConnectStatus#DISCONNECTED},
     * {@link NetworkDefs.DevConnectStatus#UNKNOWN}
     */
    public int getEthernetConnectState() {
        Bundle param = new Bundle();
        param.putInt(KEY_RET_TYPE, PARAM_RET_SERIAL);
        Bundle ret = executeCmd(TCNetworkCmd.TC_NETWORK_CMD_ETHERNET_GET_CONNECT_STATE.toString(),
                param, false);
        int result = parseInt(ret.getByteArray(KEY_RESULT));
        SkySSSLog.i(TAG, "getEthernetConnectState result=" + result);
        return result;
    }

    /**
     * 获取IP信息
     *
     * @param value net类型（ethernet/wifi）
     * @return IP信息
     */
    public SkyIpInfo getIpInfoWithNetTypeParam(String value) {
        Bundle param = new Bundle();
        param.putInt(KEY_PARAM_TYPE, PARAM_RET_STRING);
        param.putInt(KEY_RET_TYPE, PARAM_RET_BYTES);
        param.putString(KEY_PARAM, value);
        Bundle ret = executeCmd(TCNetworkCmd.TC_NETWORK_CMD_GET_IP_INFO_WITH_NET_TYPE_PARAM.toString(),
                param, false);
        SkyIpInfo result = SkyObjectByteSerialzie.toObject(ret.getByteArray(KEY_RESULT), SkyIpInfo.class);
        if (result != null) {
            SkySSSLog.i(TAG, "getIpInfoWithNetTypeParam result=" + result.ip);
        } else {
            SkySSSLog.i(TAG, "getIpInfoWithNetTypeParam result=null");
        }
        return result;
    }

    /**
     * 获取网络连接类型
     *
     * @return 类型
     */
    public String getNetConnectMode() {
        Bundle param = new Bundle();
        param.putInt(KEY_RET_TYPE, PARAM_RET_SERIAL);
        Bundle ret = executeCmd(TCNetworkCmd.TC_NETWORK_CMD_CONNECT_MODE.toString(),
                param, false);
        byte[] bytes = ret.getByteArray(KEY_RESULT);
        if (bytes == null) {
            SkySSSLog.i(TAG, "getNetConnectMode result is null");
            return null;
        }
        String result = new String(bytes);
        SkySSSLog.i(TAG, "getNetConnectMode result=" + result);
        return result;
    }

    /**
     * 获取网络类型(备用命令)
     *
     * @return 类型
     */
    public String getNetConnectType() {
        Bundle param = new Bundle();
        param.putInt(KEY_RET_TYPE, PARAM_RET_SERIAL);
        Bundle ret = executeCmd(TCNetworkCmd.TC_NETWORK_CMD_GET_NET_TYPE.toString(),
                param, false);
        byte[] bytes = ret.getByteArray(KEY_RESULT);
        if (bytes == null) {
            SkySSSLog.i(TAG, "getNetConnectType result is null");
            return null;
        }
        String result = new String(bytes);
        SkySSSLog.i(TAG, "getNetConnectType result=" + result);
        return result;
    }

    /**
     * 设置路由配置信息
     *
     * @return true配置成功
     */
    public boolean setRouteConfig(SkyRouteConfig config) {
        Bundle param = new Bundle();
        param.putInt(KEY_PARAM_TYPE, PARAM_RET_BYTES);
        param.putInt(KEY_RET_TYPE, PARAM_RET_SERIAL);
        param.putByteArray(KEY_PARAM, SkyObjectByteSerialzie.toBytes(config));
        Bundle ret = executeCmd(TCNetworkCmd.TC_NETWORK_CMD_SET_ROUTE_CONFIG.toString(),
                param, true);
        boolean result = parseBool(ret.getByteArray(KEY_RESULT));
        SkySSSLog.i(TAG, "setRouteConfig result=" + result);
        return result;
    }

    /**
     * 获取保存的路由配置信息
     *
     * @return 路由配置信息
     */
    public SkyRouteConfig getRouteConfig(SkyRouteConfig config) {
        Bundle param = new Bundle();
        param.putInt(KEY_PARAM_TYPE, PARAM_RET_SERIAL);
        param.putInt(KEY_RET_TYPE, PARAM_RET_SERIAL);
        param.putByteArray(KEY_PARAM, SkyObjectByteSerialzie.toBytes(config));
        Bundle ret = executeCmd(TCNetworkCmd.TC_NETWORK_CMD_GET_ROUTE_CONFIG.toString(),
                param, false);
        SkyRouteConfig result = SkyObjectByteSerialzie.toObject(ret.getByteArray(KEY_RESULT), SkyRouteConfig.class);
        SkySSSLog.i(TAG, "getRouteConfig result=" + result);
        return result;
    }

    /**
     * 获取网络兼容性(兼容是指ethernet&wifi能同时连接上)
     *
     * @return true为能兼容, false则不能兼容
     */
    public boolean isNetworkCompatible() {
        Bundle param = new Bundle();
        param.putInt(KEY_RET_TYPE, PARAM_RET_SERIAL);
        Bundle ret = executeCmd(TCNetworkCmd.TC_NETWORK_CMD_GET_COMPATIBLE.toString(),
                param, false);
        boolean result = parseBool(ret.getByteArray(KEY_RESULT));
        SkySSSLog.i(TAG, "isNetworkCompatible result=" + result);
        return result;
    }

    /**
     * 开机启动首页
     *
     * @param isOn true启动首页
     */
    public void setBootOptions(boolean isOn) {
        Bundle param = new Bundle();
        param.putInt(KEY_PARAM_TYPE, PARAM_RET_INT);
        param.putInt(KEY_TC_TYPE, TC_DATA_ENUM);
        param.putInt(KEY_PARAM, isOn ? 0 : 1);
        Bundle ret = executeCmd(TCEnvKey.SKY_SYSTEM_ENV_BOOT_START_HOME_PAGE, param, true);
        SkySSSLog.i(TAG, "setBootOptions ret=" + ret + ",isOn=" + isOn);
    }

    /**
     * 开机是否启动首页
     *
     * @return true启动首页
     */
    public boolean getBootOptionIsHomePage() {
        Bundle param = new Bundle();
        param.putInt(KEY_RET_TYPE, PARAM_RET_INT);
        param.putInt(KEY_TC_TYPE, TC_DATA_ENUM);
        Bundle ret = executeCmd(TCEnvKey.SKY_SYSTEM_ENV_BOOT_START_HOME_PAGE, param, false);
        int result = ret.getInt(KEY_RESULT);
        SkySSSLog.i(TAG, "getBootOptionIsHomePage result=" + result);
        return result == 0;
    }

    /**
     * 设置睡眠时间
     *
     * @param index [0-5]{"SKY_SYSTEM_ENV_SLEEP_TIMER_0", "SKY_SYSTEM_ENV_SLEEP_TIMER_15",
     *              "SKY_SYSTEM_ENV_SLEEP_TIMER_30", "SKY_SYSTEM_ENV_SLEEP_TIMER_60",
     *              "SKY_SYSTEM_ENV_SLEEP_TIMER_90"，"SKY_SYSTEM_ENV_SLEEP_TIMER_120"}
     * @return true设置成功
     */
    public boolean setSleepTimerData(int index) {
        Bundle param = new Bundle();
        param.putInt(KEY_PARAM_TYPE, PARAM_RET_INT);
        param.putInt(KEY_RET_TYPE, PARAM_RET_BOOL);
        param.putInt(KEY_TC_TYPE, TC_DATA_ENUM);
        param.putInt(KEY_PARAM, index);
        Bundle ret = executeCmd(TCEnvKey.SKY_SYSTEM_ENV_SLEEP_TIMER, param, true);
        boolean result = ret.getBoolean(KEY_RESULT);
        SkySSSLog.i(TAG, "setSleepTimerData result=" + result + ",index=" + index);
        return result;
    }

    /**
     * 获取睡眠时间
     *
     * @return 睡眠时间
     */
    public int getSleepTimerData() {
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_RET_TYPE, PARAM_RET_INT);
        Bundle ret = executeCmd(TCEnvKey.SKY_SYSTEM_ENV_SLEEP_TIMER, bundle, false);
        int result = ret.getInt(KEY_RESULT);
        SkySSSLog.i(TAG, "getSleepTimerData result=" + result);
        return result;
    }

    /**
     * 恢复出厂设置
     *
     * @param from              表示进入恢复出厂设置的入口，不同的入口会有不同的操作
     * @param needUninstallApps 是否需要卸载用户安装的应用
     */
    public void recovery(String from, boolean needUninstallApps) {
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_PARAM_TYPE, PARAM_RET_BYTES);
        SkyData data = new SkyData();
        data.add("from", from); //注意，from字段是必须的，Factory表示是工厂菜单里面的恢复出厂设置；FactoryKey 表示是工厂遥控器的恢复出厂键
        data.add("needUninstallApps", needUninstallApps);
        bundle.putByteArray(KEY_PARAM, data.toBytes());
        Bundle ret = executeCmd(TCEnvKey.SKY_SYSTEM_ENV_RECOVERY, bundle, true);
        String result = ret.getString(KEY_RESULT);
        SkySSSLog.i(TAG, "recovery result=" + result + ",from=" + from + ",needUninstallApps=" + needUninstallApps);
    }

    /**
     * 设置自动关机，海外产品功能
     *
     * @param index [0-4]{"NONE", "ONE_HOUR", "TWO_HOUR", "THREE_HOUR", "FOUR_HOUR"}
     */
    public void setAutoPowerOffReminderData(int index) {
        Bundle param = new Bundle();
        param.putInt(KEY_PARAM_TYPE, PARAM_RET_INT);
        param.putInt(KEY_TC_TYPE, TC_DATA_ENUM);
        param.putInt(KEY_PARAM, index);
        Bundle ret = executeCmd(TCEnvKey.SKY_SYSTEM_ENV_AUTO_POWEROFF, param, true);
        String result = ret.getString(KEY_RESULT);
        SkySSSLog.i(TAG, "setAutoPowerOffReminderData result=" + result + ",index=" + index);
    }

    /**
     * 获取自动关机选项，海外产品功能
     *
     * @return 自动关机选项
     */
    public String getAutoPowerOffReminderData() {
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_RET_TYPE, PARAM_RET_STRING);
        Bundle ret = executeCmd(TCEnvKey.SKY_SYSTEM_ENV_AUTO_POWEROFF, bundle, false);
        String result = ret.getString(KEY_RESULT);
        SkySSSLog.i(TAG, "getAutoPowerOffReminderData result=" + result);
        return result;
    }

    /**
     * 设置连续观看电视提醒，海外产品功能
     *
     * @param index [0-3]{"NONE", "ONE_HOUR", "TWO_HOUR", "THREE_HOUR"}
     */
    public void setWatchTimeReminderData(int index) {
        Bundle param = new Bundle();
        param.putInt(KEY_PARAM_TYPE, PARAM_RET_INT);
        param.putInt(KEY_TC_TYPE, TC_DATA_ENUM);
        param.putInt(KEY_PARAM, index);
        Bundle ret = executeCmd(TCEnvKey.SKY_SYSTEM_ENV_WATCH_TIME_REMINDER, param, true);
        SkySSSLog.i(TAG, "setWatchTimeReminderData ret=" + ret + ",index=" + index);
    }

    /**
     * 获取连续观看电视提醒，海外产品功能
     *
     * @return 连续观看电视提醒{"NONE", "ONE_HOUR", "TWO_HOUR", "THREE_HOUR"}
     */
    public String getWatchTimeReminderData() {
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_RET_TYPE, PARAM_RET_STRING);
        Bundle ret = executeCmd(TCEnvKey.SKY_SYSTEM_ENV_WATCH_TIME_REMINDER, bundle, false);
        String result = ret.getString(KEY_RESULT);
        SkySSSLog.i(TAG, "getWatchTimeReminderData result=" + result);
        return result;
    }

    /**
     * 获取指定index的一键直达内容
     *
     * @param index [0-2]三个按键
     */
    public String getOneKeyAction(int index) {
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_PARAM, index);
        bundle.putInt(KEY_PARAM_TYPE, PARAM_RET_INT);
        bundle.putInt(KEY_RET_TYPE, PARAM_RET_STRING);
        Bundle ret = executeCmd(TCSystemCmd.TC_SYSTEM_CMD_GET_ONE_KEY_ACTION_PROGRAM.toString(),
                bundle, false);
        String result = ret.getString(KEY_RESULT);
        SkySSSLog.i(TAG, "getOneKeyAction result=" + result);
        return result;
    }

    /**
     * 设置语言
     *
     * @param language 语言
     */
    public void setLanguage(String language) {
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_PARAM_TYPE, PARAM_RET_STRING);
        bundle.putString(KEY_PARAM, language);
        Bundle ret = executeCmd(TCSystemCmd.TC_SYSTEM_CMD_SET_LANGUAGE.toString(),
                bundle, false);
        SkySSSLog.i(TAG, "setLanguage ret=" + ret + ",language=" + language);
    }

    /**
     * 设置指定index的一键直达内容
     *
     * @param keyIndex       一键直达index
     * @param keyActionValue 一键直达内容
     */
    public void setOneKeyAction(int keyIndex, String keyActionValue) {
        Bundle bundle = new Bundle();
        OneKeyActionData data = new OneKeyActionData(keyIndex, keyActionValue);
        bundle.putInt(KEY_PARAM_TYPE, PARAM_RET_BYTES);
        bundle.putByteArray(KEY_PARAM, SkyObjectByteSerialzie.toBytes(data));
        Bundle ret = executeCmd(TCSystemCmd.TC_SYSTEM_CMD_SET_ONE_KEY_ACTION_PROGRAM.toString(),
                bundle, true);
        SkySSSLog.i(TAG, "setOneKeyAction ret=" + ret + ",keyIndex="
                + keyIndex + ",keyActionValue=" + keyActionValue);
    }

    /**
     * 获取一键直达内容列表
     *
     * @return 一键直达内容列表
     */
    public List<OneKeyData> getOneKeyActionList() {
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_RET_TYPE, PARAM_RET_BYTES);
        Bundle ret = executeCmd(TCSystemCmd.TC_SYSTEM_CMD_GET_ONE_KEY_ACTION_LIST.toString(),
                bundle, false);
        byte[] bytes = ret.getByteArray(KEY_RESULT);
        List<OneKeyData> result = SkyObjectByteSerialzie.toObject(bytes, List.class);
        SkySSSLog.i(TAG, "getOneKeyActionList result=" + result);
        return result;
    }

    /**
     * 待机（一般是STR快速待机）前，TV资源释放完
     *
     * @param need 是否需要释放资源
     */
    public void onReleaseFinishBeforeSuspend(boolean need) {
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_PARAM_TYPE, PARAM_RET_BOOL);
        bundle.putBoolean(KEY_PARAM, need);
        Bundle ret = executeCmd("SYSTEM_CMD_ON_RELEASE_FINISH_BEFORE_SUSPEND",
                bundle, false);
        SkySSSLog.i(TAG, "onReleaseFinishBeforeSuspend ret=" + ret + ",need=" + need);
    }

    /**
     * 获取最接近的关机时间点，并返回该时间
     *
     * @param time 时间戳，单位为ms
     * @return 返回最接近的时间点 ，类型为long 注意事项：返回可能为0的情况。
     * 1.tv开机没法获取时间 2.初次开机或者初次调用记录关机时间服务，而尚未形成时间列表 3.记录关机时间服务启动失败
     */
    public long getNearlyEndTime(long time) {
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_PARAM_TYPE, PARAM_RET_LONG);
        bundle.putInt(KEY_RET_TYPE, PARAM_RET_LONG);
        bundle.putLong(KEY_PARAM, time);
        Bundle ret = executeCmd(TCSystemCmd.TC_SYSTEM_CMD_RETURN_NEARLY_TIME.toString(),
                bundle, false);
        long result = ret.getLong(KEY_RESULT);
        SkySSSLog.i(TAG, "getNearlyEndTime result=" + result + ",time=" + time);
        return result;
    }

    /**
     * 设置背光亮度监听
     */
    public void setBackLightCallback(OnBackLigthChange callback) {
        mTCSystemApiHelper.setBackLightCallback(callback);
    }

    /**
     * 设置图像设置饱和度监听
     */
    public void setTVColorCallback(OnTVColorChange callback) {
        mTCSystemApiHelper.setTVColorCallback(callback);
    }

    /**
     * 设置动态范围扩展监听
     */
    public void setSpecularCallback(OnSpecularBoostChange callback) {
        mTCSystemApiHelper.setSpecularBoostCallback(callback);
    }

    /**
     * 设置摄像头供电状态
     *
     * @param state： 1，表示主板给摄像头的供电要输出
     *               0，表示主板给摄像头的供电要关闭
     * @return 0：调用成功\n  -1：调用失败
     */
    public int setCameraPowerState(int state) {
        Bundle bundle = new Bundle();
        Bundle param = new Bundle();
        param.putInt(KEY_PARAM, state);
        ApiUtil.setParams(bundle, SystemProviderDefines.METHOD_COMMON,
                SystemProviderDefines.COMMON_CAMERA_POWER_STATE, param, true);
        Integer ret = ApiUtil.getData(bundle, Integer.class);
        SysLog.info(TAG, "setCameraPowerState ret=" + ret + ",state=" + state);
        if (ret != null) {
            return ret;
        }
        return -1;
    }

    /**
     * 获取摄像头供电状态
     *
     * @return 1：开启；0：关闭
     */
    public int getCameraPowerState() {
        Bundle bundle = new Bundle();
        ApiUtil.setParams(bundle, SystemProviderDefines.METHOD_COMMON,
                SystemProviderDefines.COMMON_CAMERA_POWER_STATE, null, false);
        Integer ret = ApiUtil.getData(bundle, Integer.class);
        int result = ret != null ? ret : 1;
        SkySSSLog.i(TAG, "getCameraPowerState result=" + result);
        return result;
    }

    /**
     * 控制云台摄像头
     *
     * @param iOptionCmd 命令
     * @param iFuncDesc  功能
     * @param iExtra1    设定参数
     * @param iExtra2    设定参数
     * @return 失败-1 成功为0
     */
    public int ptzCameraInterface(int iOptionCmd, int iFuncDesc, int iExtra1, int iExtra2) {
        Bundle bundle = new Bundle();
        Bundle param = new Bundle();
        param.putInt("iOptionCmd", iOptionCmd);
        param.putInt("iFuncDesc", iFuncDesc);
        param.putInt("iExtra1", iExtra1);
        param.putInt("iExtra2", iExtra2);
        ApiUtil.setParams(bundle, SystemProviderDefines.METHOD_COMMON,
                SystemProviderDefines.COMMON_CONTROL_PTZ_CAMERA, param, true);
        Integer ret = ApiUtil.getData(bundle, Integer.class);
        SysLog.info(TAG, "ptzCameraInterface ret=" + ret + ",iOptionCmd=" + iOptionCmd +
                ",iFuncDesc=" + iFuncDesc + ",iExtra1=" + iExtra1 + ",iExtra2=" + iExtra2);
        if (ret != null) {
            return ret;
        }
        return -1;
    }

    /**
     * 二代控制云台摄像头
     *
     * @param iOptionCmd 命令
     * @param iFuncDesc  功能
     * @param iExtra1    设定参数
     * @param iExtra2    设定参数
     * @return 失败-1 成功为0
     */
    public int sendCameraCmd(int iOptionCmd, int iFuncDesc, int iExtra1, int iExtra2) {
        Bundle bundle = new Bundle();
        Bundle param = new Bundle();
        param.putInt("iOptionCmd", iOptionCmd);
        param.putInt("iFuncDesc", iFuncDesc);
        param.putInt("iExtra1", iExtra1);
        param.putInt("iExtra2", iExtra2);
        ApiUtil.setParams(bundle, SystemProviderDefines.METHOD_COMMON,
                SystemProviderDefines.COMMON_CONTROL_SEND_CAMERA_CMD, param, true);
        Integer ret = ApiUtil.getData(bundle, Integer.class);
        SysLog.info(TAG, "sendCameraCmd ret=" + ret + ",iOptionCmd=" + iOptionCmd +
                ",iFuncDesc=" + iFuncDesc + ",iExtra1=" + iExtra1 + ",iExtra2=" + iExtra2);
        if (ret != null) {
            return ret;
        }
        return -1;
    }

    /**
     * 是否支持云台摄像头
     *
     * @return true：支持；false：不支持
     */
    public boolean isSupportPtzCamera() {
        Bundle bundle = new Bundle();
        ApiUtil.setParams(bundle, SystemProviderDefines.METHOD_COMMON,
                SystemProviderDefines.COMMON_CONTROL_PTZ_CAMERA, null, false);
        Boolean ret = ApiUtil.getData(bundle, Boolean.class);
        SkySSSLog.i(TAG, "isSupportPtzCamera ret=" + ret);
        return ret == null ? false : ret;
    }

    /**
     * 显示云台摄像头控制界面
     */
    public void showPtzCameraControlUI() {
        Bundle bundle = new Bundle();
        Bundle param = new Bundle();
        param.putInt(KEY_PARAM, 1);
        ApiUtil.setParams(bundle, SystemProviderDefines.METHOD_COMMON,
                SystemProviderDefines.COMMON_CONTROL_PTZ_CAMERA, param, true);
        boolean ret = ApiUtil.setData(bundle);
        SkySSSLog.i(TAG, "showPtzCameraControlUI ret=" + ret);

    }

    /**
     * 设置强制激活标志
     *
     * @param needForceActivate false：不需要强制激活；true：需要强制激活
     * @return true：调用成功\n  false:调用失败
     */
    public boolean setForceActivateFlag(boolean needForceActivate) {
        Bundle bundle = new Bundle();
        Bundle param = new Bundle();
        param.putBoolean(KEY_PARAM, needForceActivate);
        ApiUtil.setParams(bundle, SystemProviderDefines.METHOD_COMMON,
                SystemProviderDefines.COMMON_FORCE_ACTIVATE_FLAG, param, true);
        boolean ret = ApiUtil.setData(bundle);
        SkySSSLog.i(TAG, "setForceActivateFlag ret=" + ret);
        return ret;
    }

    /**
     * 获取强制激活标志
     *
     * @return false：不需要强制激活；true：需要强制激活
     */
    public boolean getForceActivateFlag() {
        Bundle bundle = new Bundle();
        ApiUtil.setParams(bundle, SystemProviderDefines.METHOD_COMMON,
                SystemProviderDefines.COMMON_FORCE_ACTIVATE_FLAG, null, false);
        Boolean ret = ApiUtil.getData(bundle, Boolean.class);
        boolean can = false;
        SkySSSLog.i(TAG, "getForceActivateFlag ret=" + ret);
        if (ret != null) {
            can = ret;
        }
        return can;
    }

    public String getTvName() {
        Bundle bundle = new Bundle();
        ApiUtil.setParams(bundle, SystemProviderDefines.METHOD_COMMON,
                SystemProviderDefines.COMMON_GET_TV_NAME, null, false);
        String ret = ApiUtil.getData(bundle, String.class);
        SkySSSLog.i(TAG, "getTvName ret=" + ret);
        return ret;
    }

    /**
     * 获取ip信息
     *
     * @return SkyIpInfo中包含了当前连接设备的ip, mac, dns, gateway, mask
     */
    public SkyIpInfo getIpInfo() {
        Bundle bundle = new Bundle();
        ApiUtil.setParams(bundle, SystemProviderDefines.METHOD_COMMON,
                SystemProviderDefines.COMMON_GET_IP, null, false);
        byte[] bytes = ApiUtil.getData(bundle, byte[].class);
        SkyIpInfo result = SkyObjectByteSerialzie.toObject(bytes, SkyIpInfo.class);
        SkySSSLog.i(TAG, "getIpInfo result=" + result);
        return result;
    }

    /**
     * 获取ip信息
     *
     * @return SkyIpInfo中包含了当前连接设备的ip, mac, dns, gateway, mask
     */
    public SkyIpInfo getIpV6Info() {
        Bundle bundle = new Bundle();
        ApiUtil.setParams(bundle, SystemProviderDefines.METHOD_COMMON,
                SystemProviderDefines.COMMON_GET_IPV6, null, false);
        byte[] bytes = ApiUtil.getData(bundle, byte[].class);
        SkyIpInfo result = SkyObjectByteSerialzie.toObject(bytes, SkyIpInfo.class);
        SkySSSLog.i(TAG, "getIpInfo result=" + result);
        return result;
    }

    /**
     * 使用静态配置方式连接以太网
     *
     * @param ipInfo 各项除mac无需填写外, 其他项必须正确填写
     */
    public void connectEthByStatic(SkyIpInfo ipInfo) {
        SkySSSLog.i(TAG, "connectEthByStatic ipInfo=" + ipInfo);
        Bundle bundle = new Bundle();
        Bundle param = new Bundle();
        param.putByteArray(KEY_PARAM, SkyObjectByteSerialzie.toBytes(ipInfo));
        ApiUtil.setParams(bundle, SystemProviderDefines.METHOD_COMMON,
                SystemProviderDefines.COMMON_CONNECTETHBYSTATIC,
                param, true);
        ApiUtil.setData(bundle);
    }

    /**
     * 使用动态配置方式连接以太网
     */
    public void connectEthByDhcp() {
        SkySSSLog.i(TAG, "connectEthByDhcp");
        Bundle bundle = new Bundle();
        ApiUtil.setParams(bundle, SystemProviderDefines.METHOD_COMMON,
                SystemProviderDefines.COMMON_CONNECTETHBYDHCP, null, true);
        ApiUtil.setData(bundle);
    }

    /**
     * 获取网络类型静态or动态
     *
     * @return
     */
    public String getConnectMode() {
        Bundle bundle = new Bundle();
        ApiUtil.setParams(bundle, SystemProviderDefines.METHOD_COMMON,
                SystemProviderDefines.COMMON_GETCONNECTMODE, null, false);
        String result = ApiUtil.getData(bundle, String.class);
        SkySSSLog.i(TAG, "getConnectMode result=" + result);
        return result;
    }

    /**
     * 是否支持
     *
     * @return 0:未提示，1：自动用户允许 2：自动用户取消
     */
    public int getCMORemindState() {
        int result = getCommonData(SystemProviderDefines.COMMON_CMO_REMIND_STATE, Integer.class);
        SkySSSLog.d(TAG, "getCMORemindState result=" + result);
        return result;
    }

    /**
     * 获取上电启动模式
     *
     * @return 0：上电开机；1：上电关机；2：上电记忆
     */
    public int getAddElectricPowerMode() {
        Bundle bundle = new Bundle();
        ApiUtil.setParams(bundle, SystemProviderDefines.METHOD_COMMON,
                SystemProviderDefines.COMMON_ADDELECTRICPOWERMODE, null, false);
        Integer ret = ApiUtil.getData(bundle, Integer.class);
        int result = ret != null ? ret : 0;
        SkySSSLog.i(TAG, "getAddElectricPowerMode result=" + result);
        return result;
    }

    /**
     * 设置上电启动模式
     *
     * @param mode 0：上电开机；1：上电关机；2：上电记忆
     * @return true 设置成功
     */
    public boolean setAddElectricPowerMode(int mode) {
        Bundle bundle = new Bundle();
        Bundle param = new Bundle();
        param.putInt(KEY_PARAM, mode);
        ApiUtil.setParams(bundle, SystemProviderDefines.METHOD_COMMON,
                SystemProviderDefines.COMMON_ADDELECTRICPOWERMODE, param, true);
        boolean ret = ApiUtil.setData(bundle);
        SkySSSLog.i(TAG, "setAddElectricPowerMode ret=" + ret + ",mode=" + mode);
        return ret;
    }

    /**
     * 获取画面比例模式
     *
     * @return 0：16：9模式；1：4：3模式；
     */
    public int getDisplayMode() {
        Bundle bundle = new Bundle();
        ApiUtil.setParams(bundle, SystemProviderDefines.METHOD_COMMON,
                SystemProviderDefines.COMMON_DISPLAYMODE, null, false);
        Integer ret = ApiUtil.getData(bundle, Integer.class);
        int result = ret != null ? ret : 0;
        SkySSSLog.i(TAG, "getDisplayMode result=" + result);
        return result;
    }

    /**
     * 设置画面比例模式
     *
     * @param mode 0：16：9模式；1：4：3模式；
     * @return true 设置成功
     */
    public boolean setDisplayMode(int mode) {
        Bundle bundle = new Bundle();
        Bundle param = new Bundle();
        param.putInt(KEY_PARAM, mode);
        ApiUtil.setParams(bundle, SystemProviderDefines.METHOD_COMMON,
                SystemProviderDefines.COMMON_DISPLAYMODE, param, true);
        boolean ret = ApiUtil.setData(bundle);
        SkySSSLog.i(TAG, "setDisplayMode ret=" + ret + ",mode=" + mode);
        return ret;
    }

    /**
     * 执行Linux命令（阻塞）
     *
     * @param cmd    命令，LINUX_CMD的枚举类型
     * @param params 命令的参数，如果不带参数，则传入""
     * @return 异常时返回false
     */
    public boolean execLinuxCmd(LinuxCmd cmd, String params) {
        Bundle bundle = new Bundle();
        Bundle param = new Bundle();
        param.putInt("cmd", cmd.ordinal());
        param.putString(KEY_PARAM, params);
        ApiUtil.setParams(bundle, SystemProviderDefines.METHOD_COMMON,
                SystemProviderDefines.COMMON_EXEC_LINUX_CMD, param, true);
        boolean ret = ApiUtil.setData(bundle);
        SkySSSLog.i(TAG, "execLinuxCmd ret=" + ret + ",cmd=" + cmd + ",params=" + params);
        return ret;
    }

    /**
     * 获取青少年模式开启状态
     *
     * @return true 打开 false 关闭
     */
    public boolean getIsTeenModeOpen() {
        Bundle bundle = new Bundle();
        ApiUtil.setParams(bundle, SystemProviderDefines.METHOD_COMMON,
                SystemProviderDefines.COMMON_TEEN_MODE_OPEN_STATUS, null, false);
        Boolean ret = ApiUtil.getData(bundle, Boolean.class);
        boolean result = ret != null ? ret : false;
        SysLog.info(TAG, "getIsTeenModeOpen result=" + result);
        return result;
    }

    /**
     * 设置青少年模式打开状态
     *
     * @param isOn true 记录青少年模式开启
     * @return true 设置成功
     */
    public boolean setIsOpenTeenMode(boolean isOn) {
        Bundle bundle = new Bundle();
        Bundle param = new Bundle();
        param.putBoolean(KEY_PARAM, isOn);
        ApiUtil.setParams(bundle, SystemProviderDefines.METHOD_COMMON,
                SystemProviderDefines.COMMON_TEEN_MODE_OPEN_STATUS, param, true);
        boolean ret = ApiUtil.setData(bundle);
        SysLog.info(TAG, "setIsOpenTeenMode ret=" + ret + ",mode=" + isOn);
        return ret;
    }

    /**
     * 去coocaa主页
     */
    public void gotoHomePage() {
        Bundle bundle = new Bundle();
        ApiUtil.setParams(bundle, SystemProviderDefines.METHOD_COMMON,
                SystemProviderDefines.COMMON_GO_TO_HOME_PAGE, null, false);
        boolean ret = ApiUtil.setData(bundle);
    }

    /**
     * Wisa模块重新连接三个音箱
     */
    public void reConnWisa() {
        Bundle bundle = new Bundle();
        ApiUtil.setParams(bundle, SystemProviderDefines.METHOD_COMMON,
                SystemProviderDefines.COMMON_WISA_RECONN_SPEAKER, null, false);
        ApiUtil.setData(bundle);
    }

    /**
     * Wisa模块断连三个音箱
     */
    public void detachConnWisa() {
        Bundle bundle = new Bundle();
        ApiUtil.setParams(bundle, SystemProviderDefines.METHOD_COMMON,
                SystemProviderDefines.COMMON_WISA_DETACH_SPEAKER, null, false);
        ApiUtil.setData(bundle);
    }

    /**
     * 获取查询左音箱是否连接
     *
     * @return true 连接 false 木有链接
     */
    public boolean isWisaLSpeakerConnected() {

        Bundle bundle = new Bundle();
        ApiUtil.setParams(bundle, SystemProviderDefines.METHOD_COMMON,
                SystemProviderDefines.COMMON_WISA_CONN_L_SPEAKER, null, false);
        Boolean ret = ApiUtil.getData(bundle, Boolean.class);
        boolean result = ret != null ? ret : false;
        return result;
    }

    /**
     * 获取查询右音箱是否连接
     *
     * @return true 连接 false 木有链接
     */
    public boolean isWisaRSpeakerConnected() {
        Bundle bundle = new Bundle();
        ApiUtil.setParams(bundle, SystemProviderDefines.METHOD_COMMON,
                SystemProviderDefines.COMMON_WISA_CONN_R_SPEAKER, null, false);
        Boolean ret = ApiUtil.getData(bundle, Boolean.class);
        boolean result = ret != null ? ret : false;
        return result;
    }

    /**
     * 获取查询低音炮是否连接
     *
     * @return true 连接 false 木有链接
     */
    public boolean isWisaSubwooferConnected() {
        Bundle bundle = new Bundle();
        ApiUtil.setParams(bundle, SystemProviderDefines.METHOD_COMMON,
                SystemProviderDefines.COMMON_WISA_CONN_S_SPEAKER, null, false);
        Boolean ret = ApiUtil.getData(bundle, Boolean.class);
        boolean result = ret != null ? ret : false;
        return result;
    }

    /**
     * 设置打开/关闭音箱模式
     */
    public void setWisaMode(boolean isOn) {
        Bundle bundle = new Bundle();
        Bundle param = new Bundle();
        param.putBoolean(KEY_PARAM, isOn);
        ApiUtil.setParams(bundle, SystemProviderDefines.METHOD_COMMON,
                SystemProviderDefines.COMMON_WISA_MODE_SET, param, true);
        ApiUtil.setData(bundle);
    }

    public boolean getWisaMode() {
        Bundle bundle = new Bundle();
        ApiUtil.setParams(bundle, SystemProviderDefines.METHOD_COMMON,
                SystemProviderDefines.COMMON_WISA_MODE_SET, null, false);
        Boolean ret = ApiUtil.getData(bundle, Boolean.class);
        boolean result = ret != null ? ret : false;
        return result;
    }

    private IWisaCallBackManager manager = null;

    /**
     * @param callback 注册的callback
     * @param isAdd    true 注册 false 解注冊
     */
    public void setWisaConnCallback(WisaCallback callback, boolean isAdd) {

        if (callback == null) {
            return;
        }

        if (manager == null) {
            manager = new IWisaCallBackManager();
        }

        if (isAdd) {
            manager.addWisaCallback(callback);
        } else {
            manager.removeWisaCallback(callback);
        }
    }


    private IHookFaceCallBackManager iHookFaceCallBackManager = null;

    /**
     * 曲面屏状态回调
     *
     * @param callback 注册的callback
     * @param isAdd    true 注册 false 解注冊
     */
    public void setHookFaceCallback(HookFaceCallback callback, boolean isAdd) {
        if (callback == null) {
            return;
        }

        if (iHookFaceCallBackManager == null) {
            iHookFaceCallBackManager = new IHookFaceCallBackManager();
        }

        if (isAdd) {
            iHookFaceCallBackManager.addHookFaceCallback(callback);
        } else {
            iHookFaceCallBackManager.removeHookFaceCallback(callback);
        }
    }

    /**
     * 获取是否支持护眼模式开关(旧接口)
     * 备注：此方式通过配置平台配置判断是否支持护眼模式开关
     */
    @Deprecated
    public boolean getIsSupportEyeProtect() {
        boolean isSupport = SettingConfig.haveXmlConfig("health_eye");
        SkySSSLog.d(TAG, "getIsSupportEyeProtect:" + isSupport);
        return isSupport;
    }

    /**
     * 设置{开，关}护眼模式
     *
     * @param mode true：开；false:关
     */
    public void setNewEyeProtectMode(boolean mode) {
        SkySSSLog.d(TAG, "setNewEyeProtectMode mode=" + mode);
        Bundle bundle = new Bundle();
        Bundle param = new Bundle();
        param.putBoolean(KEY_PARAM, mode);
        ApiUtil.setParams(bundle, SystemProviderDefines.METHOD_COMMON,
                SystemProviderDefines.COMMON_NEW_EYE_PROTECT_MODE, param, true);
        ApiUtil.setData(bundle);
    }


    /**
     * 获取{开，关}护眼模式状态
     *
     * @return true：开；false:关
     */
    public boolean getNewEyeProtectMode() {
        Bundle bundle = new Bundle();
        ApiUtil.setParams(bundle, SystemProviderDefines.METHOD_COMMON,
                SystemProviderDefines.COMMON_NEW_EYE_PROTECT_MODE, null, false);
        Boolean ret = ApiUtil.getData(bundle, Boolean.class);
        boolean result = ret != null ? ret : false;
        SkySSSLog.d(TAG, "getNewEyeProtectMode ret=" + ret);
        return result;
    }

    /**
     * 是否支持护眼模式
     * 备注：通过中间件提供接口判断是否支持护眼模式，目前此接口仅在新护目模式时生效(酷开9.0)，暂不兼容旧系统。
     *
     * @return
     */
    public boolean isSupportHealthEyeMode() {
        TCEnumSetData data = (TCEnumSetData) TCSettingApi.getData(SkyConfigDefs.SKY_CFG_TV_HEALTH_EYE_MODE);
        return !SkyConfigDefs.SKY_CFG_TV_HEALTH_EYE_MODE_ENUM.NOT_SUPPORT.toString().equals(data.getCurrent());
    }

    /**
     * {关闭，全天开启，定时开启}新护眼模式
     * <p>
     * 此功能结合了两个功能：
     * 1.{开，关}护眼模式{@link TCSystemApi#getNewEyeProtectMode()}
     * 2.{开，关}护眼模式时间设定接口{SkyConfigDefs.SKY_CFG_TV_NIGHT_GUARD_EYE_TIME_SWITCH}
     *
     * @param {@link SkyConfigDefs.SKY_CFG_TV_HEALTH_EYE_MODE_ACTION_ENUM}
     *               备注：有必要的话，可自行通过Action启动“定时开启”设置页面
     *               action:“com.skyworth.setting.healtheye.settime”
     */
    public void setHealthEyeMode(SkyConfigDefs.SKY_CFG_TV_HEALTH_EYE_MODE_ACTION_ENUM value) {
        TCEnumSetData data = (TCEnumSetData) TCSettingApi.getData(SkyConfigDefs.SKY_CFG_TV_HEALTH_EYE_MODE_ACTION);
        data.setCurrent(value.toString());
        TCSettingApi.setData(data);
    }

    /**
     * 获取{关闭，全天开启，定时开启}新护眼模式当前状态
     *
     * @return {@link SkyConfigDefs.SKY_CFG_TV_HEALTH_EYE_MODE_ACTION_ENUM}
     */
    public SkyConfigDefs.SKY_CFG_TV_HEALTH_EYE_MODE_ACTION_ENUM getHealthEyeMode() {
        TCEnumSetData data = (TCEnumSetData) TCSettingApi.getData(SkyConfigDefs.SKY_CFG_TV_HEALTH_EYE_MODE_ACTION);
        return SkyConfigDefs.SKY_CFG_TV_HEALTH_EYE_MODE_ACTION_ENUM.valueOf(data.getCurrent());
    }

    /**
     * 设置壁纸模式10档色温（目前仅艺术模式，本地屏保模式下生效）
     * @param value
     */
    public void setWallpaperColorTemperature(SkyConfigDefs.TCWallpaperEnumColorTemperature value) {
        TCEnumSetData data = (TCEnumSetData) TCSettingApi.getData(SkyConfigDefs.SKY_CFG_TV_COLOR_TEMPERATURE_LSS);
        data.setCurrent(value.toString());
        TCSettingApi.setData(data);
    }

    /**
     * 设置壁纸模式10档色温（目前仅艺术模式，本地屏保模式下生效）
     *
     * @return {@link SkyConfigDefs.TCWallpaperEnumColorTemperature}
     */
    public SkyConfigDefs.TCWallpaperEnumColorTemperature getWallpaperColorTemperature() {
        TCEnumSetData data = (TCEnumSetData) TCSettingApi.getData(SkyConfigDefs.SKY_CFG_TV_COLOR_TEMPERATURE_LSS);
        return SkyConfigDefs.TCWallpaperEnumColorTemperature.valueOf(data.getCurrent());
    }

    /**
     * 是否支持自动光感（调节亮度和色温）
     *
     * @return 是否支持
     */
    public boolean isSupportEnvironmentLight() {
        TCSwitchSetData data = (TCSwitchSetData) TCSettingApi.getData(SkyConfigDefs.SKY_GFC_TV_SUPPORT_ENVIRONMENT_LIGHT);
        return data.isOn();
    }

    /**
     * 自动光感（调节亮度和色温）
     *
     * @param isOn true:开启
     */
    public void setEnvironmentLight(boolean isOn) {
        TCSwitchSetData data = new TCSwitchSetData();
        data.setName(SkyConfigDefs.SKY_GFC_TV_ENVIRONMENT_LIGHT);
        data.setOn(isOn);
        TCSettingApi.setData(data);
    }

    /**
     * 自动光感（调节亮度和色温）
     *
     * @return true:开启
     */
    public boolean getEnvironmentLight() {
        TCSwitchSetData data = (TCSwitchSetData) TCSettingApi.getData(SkyConfigDefs.SKY_GFC_TV_ENVIRONMENT_LIGHT);
        return data.isOn();
    }


    /**
     * 应用层通知中间件进入壁纸模式(包括壁纸应用或者本地屏保)，激活10档色温调节
     * 此状态中间件不存储，开机后还是恢复到正常的模式
     * @param active true:激活 false:不激活
     */
    public void setWallpaperModeActive(boolean active){
        TCSwitchSetData data = new TCSwitchSetData();
        data.setName(SkyConfigDefs.SKY_CFG_TV_ACTIVE_LOCAL_SCREENSAVER);
        data.setOn(active);
        TCSettingApi.setData(data);
    }

    /**
     * 获取当前壁纸应用下自动关机的时间
     * @see SkyConfigDefs.SKY_CFG_TV_WALLPAPER_MODE_AUTO_SLEEP_TIME_ENUM
     * @return
     */
    public SkyConfigDefs.SKY_CFG_TV_WALLPAPER_MODE_AUTO_SLEEP_TIME_ENUM getWallpaperModeAutoSleepTime() {
        TCEnumSetData data = (TCEnumSetData) TCSettingApi.getData(SkyConfigDefs.SKY_CFG_TV_WALLPAPER_MODE_AUTO_SLEEP_TIME);
        return SkyConfigDefs.SKY_CFG_TV_WALLPAPER_MODE_AUTO_SLEEP_TIME_ENUM.valueOf(data.getCurrent());
    }


    /**
     * 当前是否打开了壁纸模式（仅壁纸电视有此开关）
     * @return
     */
    public boolean getWallpaperMode(){
        TCSwitchSetData data = (TCSwitchSetData) TCSettingApi.getData(SkyConfigDefs.SKY_CFG_TV_WALLPAPER_MODE);
        return data.isOn();
    }


    /**
     * 注册保活app
     *
     * @param pckName 包名
     * @param action  服务action
     * @param flag    0：表示开机不拉起；1：开机拉起，其他保留他用
     */
    public void registerKeepLiveApp(String pckName, String action, int flag) {
        SkySSSLog.d(TAG, "registerKeepLiveApp pckName=" + pckName + ",action=" + action);
        Bundle bundle = new Bundle();
        Bundle param = new Bundle();
        param.putBoolean(KEY_PARAM, true);
        param.putString("pckName", pckName);
        param.putString("action", action);
        param.putInt("flag", flag);
        ApiUtil.setParams(bundle, SystemProviderDefines.METHOD_COMMON,
                SystemProviderDefines.COMMON_KEEP_LIVE_APP, param, true);
        ApiUtil.setData(bundle);
    }

    /**
     * 解注册保活app
     *
     * @param pckName 包名
     */
    public void unregisterKeepLiveApp(String pckName) {
        SkySSSLog.d(TAG, "unregisterKeepLiveApp pckName=" + pckName);
        Bundle bundle = new Bundle();
        Bundle param = new Bundle();
        param.putBoolean(KEY_PARAM, false);
        param.putString("pckName", pckName);
        ApiUtil.setParams(bundle, SystemProviderDefines.METHOD_COMMON,
                SystemProviderDefines.COMMON_KEEP_LIVE_APP, param, true);
        ApiUtil.setData(bundle);
    }


    /**
     * 获取ai 开机广告是否运行
     *
     * @return
     */
    public boolean getISADPlaying() {
        Bundle bundle = new Bundle();
        ApiUtil.setParams(bundle, SystemProviderDefines.METHOD_COMMON,
                SystemProviderDefines.COMMON_IS_OPEN_AD_PLAYING, null, false);
        Boolean ret = ApiUtil.getData(bundle, Boolean.class);
        boolean result = ret != null ? ret : false;
        return result;
    }

    /**
     * 调节屏幕曲度及角度
     *
     * @param mode      功能模式 0：复位；1：左转；2：右转；3：平面；4：曲面；5：停止
     * @param paramType 参数类型 0:一点（底层的默认值）；1：最大值；2：自定义
     * @param param     参数值，当paramType==2时有效
     */
    public void adjustScreenCurvatureAngle(int mode, int paramType, int param) {
        Bundle bundle = new Bundle();
        Bundle value = new Bundle();
        value.putInt("mode", mode);
        value.putInt("paramType", paramType);
        value.putInt("param", param);
        ApiUtil.setParams(bundle, SystemProviderDefines.METHOD_COMMON,
                SystemProviderDefines.ADJUST_SCREEN_CURVATURE_ANGLE, value, true);
        ApiUtil.setData(bundle);
        SkySSSLog.d(TAG, "adjustScreenCurvatureAngle mode=" + mode + ",paramType=" +
                paramType + ",param=" + param);
    }

    /**
     * 获取屏幕曲度及角度
     *
     * @return int[0]:表示曲度；int[1]:表示角度
     */
    public int[] getScreenCurvatureAngle() {
        Bundle bundle = new Bundle();
        ApiUtil.setParams(bundle, SystemProviderDefines.METHOD_COMMON,
                SystemProviderDefines.ADJUST_SCREEN_CURVATURE_ANGLE, null, false);
        int[] ret = ApiUtil.getData(bundle, int[].class);
        SkySSSLog.d(TAG, "getScreenCurvatureAngle ret=" + ret);
        return ret;
    }

    /**
     * 设置弯曲屏模式
     */
    public void setBendScreenMode(String mode) {
        if (TextUtils.isEmpty(mode)) return;
        int screenCurvatureAngle = getScreenCurvatureAngle()[0];
        if ("game".equals(mode)) {
            adjustScreenCurvatureAngle(screenCurvatureAngle > 66 ? 3 : 4, 2, Math.abs(66 - screenCurvatureAngle));
        } else if ("film".equals(mode)) {
            adjustScreenCurvatureAngle(screenCurvatureAngle > 50 ? 3 : 4, 2, Math.abs(50 - screenCurvatureAngle));
        } else if ("user".equals(mode)) {
            int lastTimeCurve = SystemPropertiesUtil.getPropertyInt("persist.sys.screen.user.angle", 0);
            adjustScreenCurvatureAngle(screenCurvatureAngle > lastTimeCurve ? 3 : 4, 2, Math.abs(lastTimeCurve - screenCurvatureAngle));
        }
        SystemPropertiesUtil.setProperty("persist.sys.screen.type", mode);
    }

    /**
     * 儿童模式 距离提醒开关
     */
    public void setChildDistanceRemind(Context context, boolean isSwitch) {
        SystemPropertiesUtil.setProperty("persist.sys.distance.remind", isSwitch ? "1" : "0");
        Intent switchIntent = new Intent(BroadcastAction.BROADCAST_SMART_CAMERA_CONTROL);
        switchIntent.setClassName("com.skyworth.smc", "com.skyworth.smc.receiver.SkySCReceiver");
        switchIntent.putExtra("switchItem", "SKY_CFG_TV_DISTANCE_REMIND");
        switchIntent.putExtra("value", isSwitch ? "1" : "0");
        context.sendBroadcast(switchIntent);
    }

    /**
     * 儿童模式 距离提醒设置的距离
     */
    public String getChildDistanceRemind() {
        String property = SystemPropertiesUtil.getProperty("persist.sys.distance.remind", "0");
        String distanceMode = SystemPropertiesUtil.getProperty("persist.sys.smart.distance.set", "0");
        SkySSSLog.d(TAG, "getChildDistanceRemind ret=" + property);
        return "1".equals(property) ? distanceMode : "-1";
    }

    /**
     * 儿童模式 距离提醒开关
     */
    public boolean getChildDistanceRemindStatus() {
        String property = SystemPropertiesUtil.getProperty("persist.sys.distance.remind", "0");
        return "1".equals(property);
    }

    /**
     * 获取是否支持距离提醒开关
     */
    public boolean getIsSupportDistanceRemind(Context context) {
        boolean hasRemindConfig = SettingConfig.haveXmlConfig("SKY_CFG_TV_DISTANCE_REMIND");
        boolean isSmcInstall = isPackageInstalled(context, "com.skyworth.smc") || isPackageInstalled(context, "com.sf.aikit");
        SkySSSLog.d(TAG, "getIsSupportDistanceRemind:" + hasRemindConfig + " isSmcInstall:" + isSmcInstall);
        return hasRemindConfig && isSmcInstall;
    }

    /**
     * 获取云台摄像头角度限位值
     *
     * @return
     */
    public int[] getPtzCameraLimit() {
        Bundle bundle = new Bundle();
        ApiUtil.setParams(bundle, SystemProviderDefines.METHOD_COMMON,
                SystemProviderDefines.GET_PTZ_CAMERA_LIMIT, null, false);
        int[] ret = ApiUtil.getData(bundle, int[].class);
        SkySSSLog.d(TAG, "getPtzCameraLimit ret=" + ret);
        return ret;
    }

    /**
     * 获取摄像头转动角度范围值
     * 数据的四个数据代表上下左右
     *
     * @return
     */

    public int[] getCameraTurnLimit() {
        if (getCameraTurnType() == 2) {
            if ("CM401N0".equals(SkySystemProperties.getProperty("persist.sys.skycamera.name"))) {
                int[] ret = new int[4];
                ret[0] = 25;
                ret[1] = 0;
                ret[2] = -45;
                ret[3] = 45;
                return ret;
            }
            Bundle bundle = new Bundle();
            ApiUtil.setParams(bundle, SystemProviderDefines.METHOD_COMMON,
                    SystemProviderDefines.GET_PTZ_CAMERA_LIMIT, null, false);
            int[] ret = ApiUtil.getData(bundle, int[].class);
            SkySSSLog.d(TAG, "getPtzCameraLimit ret=" + ret);
            return ret;
        } else if (getCameraTurnType() == 1) {
            int[] ret = new int[4];
            ret[0] = 10;
            ret[1] = 0;
            ret[2] = 0;
            ret[3] = 0;
            return ret;
        }
        return new int[4];
    }

    /**
     * 摄像头控制接口
     *
     * @param vDegree 纵向转动值
     * @param hDegree 横向转动值
     * @return
     */
    //二代云台结构是反的，且转动范围是0-25，通过挡位来控制转动方向并跟一代保持参数一致
    public static final int[] vDegree_Position = {25, 24, 23, 22, 21, 20, 19, 18, 17, 16, 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0};

    public boolean setCameraDegree(int vDegree, int hDegree) {
        SysLog.info(TAG, "setCameraDegree iExtra1=" + vDegree + ",iExtra2=" + hDegree);
        //当前支持上下左右转动
        if (getCameraTurnType() == 2) {
            Bundle bundle = new Bundle();
            Bundle param = new Bundle();
            //传入3 当前是转动控制
            param.putInt("iOptionCmd", 3);
            //传2 指定角度转动
            param.putInt("iFuncDesc", 2);
            param.putInt("iExtra1", hDegree);
            if ("CM401N0".equals(SkySystemProperties.getProperty("persist.sys.skycamera.name"))) {
                //vDegree传0-25对应26个角度挡位
                if (vDegree < 0) {
                    vDegree = 0;
                } else if (vDegree > 25) {
                    vDegree = 25;
                }
                param.putInt("iExtra2", vDegree_Position[vDegree]);
            } else {
                param.putInt("iExtra2", vDegree);
            }
            ApiUtil.setParams(bundle, SystemProviderDefines.METHOD_COMMON,
                    SystemProviderDefines.COMMON_CONTROL_PTZ_CAMERA, param, true);
            Integer ret = ApiUtil.getData(bundle, Integer.class);
            SysLog.info(TAG, "setCameraDegree ret=" + ret + ",iOptionCmd=" + "iExtra1=" + vDegree + ",iExtra2=" + hDegree);
            if (ret != null) {
                return ret == 0;
            }
        } else if (getCameraTurnType() == 1) {
            //当前支持上下转动
            if (vDegree < 0)
                vDegree = 0;
            if (vDegree > 10)
                vDegree = 10;
            return setCameraDegreeLevel(vDegree);
        }

        return false;
    }

    /**
     * 非云台摄像头设置纵向转动等级
     *
     * @param level
     * @return
     */
    public boolean setCameraDegreeLevel(int level) {
        Bundle bundle = new Bundle();
        Bundle param = new Bundle();
        param.putInt(ServiceUtil.MENU_SELECT_INDEX_KEY, level);
        ApiUtil.setParams(bundle, SystemProviderDefines.METHOD_COMMON,
                SystemProviderDefines.COMMON_ADJUST_CAMERA_ANGLE, param, true);
        boolean ret = ApiUtil.setData(bundle);
        SkySSSLog.i(TAG, "setCameraDegreeLevel ret=" + ret);
        return ret;
    }

    /**
     * 当前支持摄像头转动的类型
     *
     * @return 0 不支持；1 支持上下转动 ；2 支持上下左右转动
     */
    public int getCameraTurnType() {
        String camera_type_name = SkySystemProperties.getProperty("persist.sys.skycamera.name");
        String camera_type_table_name = SkySystemProperties.getProperty("persist.sys.cameraLiftable");
        int type = 0;
        SkySSSLog.d(TAG, "getCameraTurnType current camera_type_name:" + camera_type_name + " camera_type_table_name:" + camera_type_table_name);
        if (!TextUtils.isEmpty(camera_type_table_name) && "true".equals(camera_type_table_name)) {
            type = 1;
        }
        if (!TextUtils.isEmpty(camera_type_name)) {
            if ("SNOPPA".equals(camera_type_name) || "CM401N0".equals(camera_type_name))
                type = 2;
        }

        return type;
    }

    /**
     * 当前摄像头是否支持动体自动跟踪
     * 目前只有云台摄像头支持动体跟踪
     *
     * @return
     */
    public boolean isSupportAutoFollow() {
        String camera_type_name = SkySystemProperties.getProperty("persist.sys.skycamera.name");
        SkySSSLog.d(TAG, "isSupportAutofollow current camera_type_name:" + camera_type_name);
        if (TextUtils.isEmpty(camera_type_name)) {
            return false;
        }
        if ("CM401N0".equals(camera_type_name) || "SNOPPA".equals(camera_type_name)) {
            return true;
        }
        return false;
    }


    /**
     * @param isStart
     * @return
     */
    public boolean setAutoFollowState(boolean isStart) {
        Bundle bundle = new Bundle();
        Bundle param = new Bundle();
        //传入9 当前是相机模式切换
        param.putInt("iOptionCmd", 9);
        //传4 普通模式 传2 动体追踪
        param.putInt("iFuncDesc", isStart ? 2 : 4);
        param.putInt("iExtra1", 0);
        param.putInt("iExtra2", 0);
        ApiUtil.setParams(bundle, SystemProviderDefines.METHOD_COMMON,
                SystemProviderDefines.COMMON_CONTROL_PTZ_CAMERA, param, true);
        Integer ret = ApiUtil.getData(bundle, Integer.class);
        SysLog.info(TAG, "setAutoFollowState ret=" + ret + ",iOptionCmd=setAutoFollowState" + "isStart:" + isStart);
        if (ret != null) {
            return ret == 0;
        }
        return false;
    }

    /**
     * 人脸跟踪
     *
     * @param isStart
     * @return
     */
    public boolean setFaceFollowState(boolean isStart) {
        Bundle bundle = new Bundle();
        Bundle param = new Bundle();
        //传入9 当前是相机模式切换
        param.putInt("iOptionCmd", 9);
        //传4 普通模式 传1 人脸追踪
        param.putInt("iFuncDesc", isStart ? 1 : 4);
        param.putInt("iExtra1", 0);
        param.putInt("iExtra2", 0);
        ApiUtil.setParams(bundle, SystemProviderDefines.METHOD_COMMON,
                SystemProviderDefines.COMMON_CONTROL_PTZ_CAMERA, param, true);
        Integer ret = ApiUtil.getData(bundle, Integer.class);
        SysLog.info(TAG, "setFaceFollowState ret=" + ret + ",iOptionCmd=setFaceFollowState" + "isStart:" + isStart);
        if (ret != null) {
            return ret == 0;
        }
        return false;
    }

    /**
     * 是否支持手势切焦
     *
     * @return
     */
    public boolean isSupportGestureFocusSwitch(Context context) {
        try {
            if ("CM401N0".equals(SkySystemProperties.getProperty("persist.sys.skycamera.name"))) {
                List<PackageInfo> packageInfos = context.getPackageManager().getInstalledPackages(0);
                boolean smartCameraInstalled = false;
                if (null == packageInfos) {
                    SysLog.info(TAG, "packageInfos is null");
                    return false;
                }
                for (PackageInfo info : packageInfos) {
                    if (info.packageName.equals("com.skyworth.smc")) {
                        smartCameraInstalled = true;
                        break;
                    }
                }
                return smartCameraInstalled;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 手势切焦
     *
     * @param isStart
     * @return
     */
    public boolean setGestureFocusSwitchState(boolean isStart) {
        Bundle bundle = new Bundle();
        Bundle param = new Bundle();
        //传入100 当前是手势切焦
        param.putInt("iOptionCmd", 100);
        //传1 打开手势切焦 传0 关闭手势切焦
        param.putInt("iFuncDesc", isStart ? 1 : 0);
        param.putInt("iExtra1", 0);
        param.putInt("iExtra2", 0);
        ApiUtil.setParams(bundle, SystemProviderDefines.METHOD_COMMON,
                SystemProviderDefines.COMMON_CONTROL_PTZ_CAMERA, param, true);
        Integer ret = ApiUtil.getData(bundle, Integer.class);
        SysLog.info(TAG, "setGestureFocusSwitchState ret=" + ret + ",iOptionCmd=setGestureFocusSwitchState" + "isStart:" + isStart);
        if (ret != null) {
            return ret == 0;
        }
        return false;
    }

    /**
     * 手势设置开关
     *
     * @param isOn 是否开启
     * @return false：不支持此功能  true:支持此功能，设置之后返回true
     */
    public boolean setGestureSetting(boolean isOn) {
        if (!isSupportGestureFocusSwitch(context)) {
            return false;
        } else {
            Bundle param = new Bundle();
            param.putInt(KEY_PARAM_TYPE, PARAM_RET_BOOL);
            param.putInt(KEY_TC_TYPE, TC_DATA_SWITCH);
            param.putBoolean(KEY_PARAM, isOn);
            Bundle ret = executeCmd(TCEnvKey.SKY_SYSTEM_ENV_GESTURE_RECOGNITION, param, true);
            SkySSSLog.i(TAG, "setGestureSetting ret=" + ret + ",isOn=" + isOn);
            return true;
        }
    }


    /**
     * 获得垂直方向摄像头的转动角度
     *
     * @return
     */
    public int getCameraVDegree() {
        //二代云台摄像头直接返回9避免第一次调节角度过大的问题
        if ("CM401N0".equals(SkySystemProperties.getProperty("persist.sys.skycamera.name"))) {
            return 25;
        }
        //目前只支持非云台摄像头
        if (getCameraTurnType() == 1) {
            Bundle bundle = new Bundle();
            ApiUtil.setParams(bundle, SystemProviderDefines.METHOD_COMMON,
                    SystemProviderDefines.COMMON_ADJUST_CAMERA_ANGLE, null, false);
            Integer ret = ApiUtil.getData(bundle, Integer.class);
            SkySSSLog.i(TAG, "getCameraVDegree ret=" + ret);
            if (ret != null) {
                return ret;
            }
        }
        return 0;
    }

    /**
     * 获得水平方向摄像头的转动角度
     *
     * @return
     */
    public int getCameraHDegree() {
        //目前不支持水平横向，后续支持后加入
        return 0;
    }

    /**
     * 设置待机后定时开机的接口
     *
     * @param wakeupTime:      定时参数，单位为秒
     *                                            当此值为0时，关闭定时开机功能
     *                                            当此值不为0时，PM需要在此时间后自动开机
     * @param timerWakeupMode: 用来指定timer唤醒的类型
     *                                                 0:预约刻录唤醒
     *                                                 1:预约收看节目唤醒
     * @param isNeedPanelOn:   0表示timer唤醒时不点屏，1表示timer唤醒时要点屏
     * @return 返回值 0：成功，-1：失败
     */
    public int setTimerWakeupParam(int wakeupTime, int timerWakeupMode, int isNeedPanelOn) {
        Bundle bundle = new Bundle();
        Bundle param = new Bundle();
        param.putInt("wakeupTime", wakeupTime);
        param.putInt("timerWakeupMode", timerWakeupMode);
        param.putInt("isNeedPanelOn", isNeedPanelOn);
        ApiUtil.setParams(bundle, SystemProviderDefines.METHOD_COMMON,
                SystemProviderDefines.SKY_CFG_SET_TIMER_WAKEUP_PARAM, param, true);
        Integer ret = ApiUtil.getData(bundle, Integer.class);
        SysLog.info(TAG, "setTimerWakeupParam ret=" + ret + ",wakeupTime=" + wakeupTime +
                ",timerWakeupMode=" + timerWakeupMode + ",isNeedPanelOn=" + isNeedPanelOn);
        if (ret != null) {
            return ret;
        }
        return -1;
    }

    /**
     * 分屏相关方法
     * 获取当前声音输出设备
     *
     * @param side 0表示左边窗口，1表示右边窗口
     * @return 0表示本机，1表示蓝牙
     */
    public int getSoundOutPutDevice(int side) {
        Bundle bundle = new Bundle();
        Bundle param = new Bundle();
        param.putInt("side", side);
        ApiUtil.setParams(bundle, SystemProviderDefines.METHOD_COMMON,
                SystemProviderDefines.GET_SOUND_OUTPUT_DEVICE, param, true);
        Integer ret = ApiUtil.getData(bundle, Integer.class);
        SkySSSLog.d(TAG, "getSoundOutPutDevice ret=" + ret + " side=" + side);
        return ret;
    }

    /**
     * 分屏相关方法
     * 设置左右窗口的声音输出设备
     *
     * @param side   0表示左边窗口，1表示右边窗口
     * @param device 0表示本机设备，1表示蓝牙设备
     * @return 是否设置成功
     */
    public boolean setSoundOutPutDevice(int side, int device) {
        Bundle bundle = new Bundle();
        Bundle param = new Bundle();
        param.putInt("side", side);
        param.putInt("device", device);
        ApiUtil.setParams(bundle, SystemProviderDefines.METHOD_COMMON,
                SystemProviderDefines.SET_SOUND_OUTPUT_DEVICE, param, true);
        Boolean ret = ApiUtil.getData(bundle, Boolean.class);
        SysLog.info(TAG, "setSoundOutPutDevice ret=" + ret + ",side=" + side +
                ",device=" + device);
        if (ret != null) {
            return ret;
        }
        return false;
    }

    /**
     * 分屏相关方法
     * 获取窗口声音输出设备的音量
     *
     * @param side 0表示左边窗口，1表示右边窗口
     * @return 音量大小
     */
    public int getWindowVolume(int side) {
        Bundle bundle = new Bundle();
        Bundle param = new Bundle();
        param.putInt("side", side);
        ApiUtil.setParams(bundle, SystemProviderDefines.METHOD_COMMON,
                SystemProviderDefines.GET_WINDOW_VOLUME, param, true);
        Integer ret = ApiUtil.getData(bundle, Integer.class);
        SkySSSLog.d(TAG, "getWindowVolume ret=" + ret + " side=" + side);
        return ret;
    }

    /**
     * 分屏相关方法
     * 设置左右窗口声音输出设备的音量
     *
     * @param side  0表示左边窗口，1表示右边窗口
     * @param value 音量大小
     * @return 是否设置成功
     */
    public boolean setWindowVolume(int side, int value) {
        Bundle bundle = new Bundle();
        Bundle param = new Bundle();
        param.putInt("side", side);
        param.putInt("value", value);
        ApiUtil.setParams(bundle, SystemProviderDefines.METHOD_COMMON,
                SystemProviderDefines.SET_WINDOW_VOLUME, param, true);
        Boolean ret = ApiUtil.getData(bundle, Boolean.class);
        SysLog.info(TAG, "setWindowVolume ret=" + ret + ",side=" + side +
                ",value=" + value);
        if (ret != null) {
            return ret;
        }
        return false;
    }

    /**
     * 获取左边的声音权重，右边的声音权重=100-左边
     *
     * @return 0-100
     */
    public int getLeftSoundWeight() {
        Bundle bundle = new Bundle();
        Bundle param = new Bundle();
        ApiUtil.setParams(bundle, SystemProviderDefines.METHOD_COMMON,
                SystemProviderDefines.GET_LEFT_WINDOW_VOLUME_WEIGHT, null, false);
        Integer ret = ApiUtil.getData(bundle, Integer.class);
        SkySSSLog.d(TAG, "getLeftSoundWeight ret=" + ret);
        return ret;
    }

    /**
     * 设置左边的声音权重，右边的声音权重=100-左边
     *
     * @param value 0-100
     * @return
     */
    public boolean setLeftSoundWeight(int value) {
        Bundle bundle = new Bundle();
        Bundle param = new Bundle();
        param.putInt("weight", value);
        ApiUtil.setParams(bundle, SystemProviderDefines.METHOD_COMMON,
                SystemProviderDefines.SET_LEFT_WINDOW_VOLUME_WEIGHT, param, true);
        Boolean ret = ApiUtil.getData(bundle, Boolean.class);
        SysLog.info(TAG, "setWindowVolume ret=" + ret + ",value=" + value);
        if (ret != null) {
            return ret;
        }
        return false;
    }

    /**
     * 是否为新机器
     *
     * @return 0:新机； 其他：非新机
     */
    public int getIsNewMachine() {
        Bundle bundle = new Bundle();
        Bundle param = new Bundle();
        //此接口位于getSysSets().getIsNewMachine()，设置false表示要取的值是否为新机器
        param.putBoolean(KEY_PARAM, false);
        ApiUtil.setParams(bundle, SystemProviderDefines.METHOD_COMMON,
                SystemProviderDefines.IS_NEW_MACHINE, param, false);
        Integer ret = ApiUtil.getData(bundle, Integer.class);
        SkySSSLog.d(TAG, "getIsNewMachine ret=" + ret);
        return ret;
    }

    /**
     * 是否支持强制激活
     *
     * @return 是否支持
     */
    public boolean isSupportForceActive() {
        Bundle bundle = new Bundle();
        Bundle param = new Bundle();
        //此接口位于getSysSets().getIsNewMachine()，设置true表示要取的值是否支持强制激活
        param.putBoolean(KEY_PARAM, true);
        ApiUtil.setParams(bundle, SystemProviderDefines.METHOD_COMMON,
                SystemProviderDefines.IS_NEW_MACHINE, param, false);
        Integer ret = ApiUtil.getData(bundle, Integer.class);
        SkySSSLog.d(TAG, "getIsNewMachine ret=" + ret);
        return ret == 1;
    }

    /**
     * 设置是否为新机器
     *
     * @param code 0:新机； 其他：非新机
     * @return 是否设置成功
     */
    public boolean setIsNewMachine(int code) {
        Bundle bundle = new Bundle();
        Bundle param = new Bundle();
        param.putInt(KEY_PARAM, code);
        ApiUtil.setParams(bundle, SystemProviderDefines.METHOD_COMMON,
                SystemProviderDefines.IS_NEW_MACHINE, param, true);
        Boolean ret = ApiUtil.getData(bundle, Boolean.class);
        SysLog.info(TAG, "setIsNewMachine ret=" + ret + ",code=" + code);
        if (ret != null) {
            return ret;
        }
        return false;
    }


    /**
     * 重新激活的原因
     *
     * @return 1：用户模式恢复出厂 2：工厂模式恢复出厂 3：双清 9：未知情况
     */
    public int getReActiveReason() {
        Bundle bundle = new Bundle();
        ApiUtil.setParams(bundle, SystemProviderDefines.METHOD_COMMON,
                SystemProviderDefines.RE_ACTIVE_REASON, null, false);
        Integer ret = ApiUtil.getData(bundle, Integer.class);
        SkySSSLog.d(TAG, "getIsNewMachine ret=" + ret);
        return ret;
    }

    /**
     * 设置重新激活的原因
     *
     * @param code 1：用户模式恢复出厂 2：工厂模式恢复出厂 3：双清 9：未知情况
     * @return 是否设置成功
     */
    public boolean setReActiveReason(int code) {
        Bundle bundle = new Bundle();
        Bundle param = new Bundle();
        param.putInt(KEY_PARAM, code);
        ApiUtil.setParams(bundle, SystemProviderDefines.METHOD_COMMON,
                SystemProviderDefines.RE_ACTIVE_REASON, param, true);
        Boolean ret = ApiUtil.getData(bundle, Boolean.class);
        SysLog.info(TAG, "setIsNewMachine ret=" + ret + ",code=" + code);
        if (ret != null) {
            return ret;
        }
        return false;
    }

    /**
     * get Camera Version
     *
     * @return String version
     */
    public String getCameraVersion() {
        Bundle bundle = new Bundle();
        ApiUtil.setParams(bundle, SystemProviderDefines.METHOD_COMMON,
                SystemProviderDefines.CAMERA_VERSION, null, false);
        String ret = ApiUtil.getData(bundle, String.class);
        SkySSSLog.d(TAG, "getCameraVersion ret=" + ret);
        return ret;
    }

    /**
     * Camera upgrade
     *
     * @return true success；false fail
     */
    public boolean cameraUpgrade(String filePath) {
        Bundle bundle = new Bundle();
        Bundle param = new Bundle();
        param.putString(KEY_PARAM, filePath);
        ApiUtil.setParams(bundle, SystemProviderDefines.METHOD_COMMON,
                SystemProviderDefines.CAMERA_UPGRADE, param, true);
        Boolean ret = ApiUtil.getData(bundle, Boolean.class);
        SysLog.info(TAG, "cameraUpgrade ret=" + ret + ",filePath=" + filePath);
        if (ret != null) {
            return ret;
        }
        return false;
    }

    /**
     * 打开或关闭Mini Led灯
     *
     * @param open
     * @return 打开成功：true 打开失败： false
     */
    public boolean setMiniLedSwitchStatus(boolean open) {
        Bundle bundle = new Bundle();
        Bundle param = new Bundle();
        param.putBoolean(KEY_PARAM, open);
        ApiUtil.setParams(bundle, SystemProviderDefines.METHOD_COMMON,
                SystemProviderDefines.MINI_LED_SWITCH_STATUS, param, true);
        Boolean ret = ApiUtil.getData(bundle, Boolean.class);
        SysLog.info(TAG, "set_mini_led_switch_status ret=" + ret + ",open=" + open);
        if (ret != null) {
            return ret;
        }
        return false;
    }

    /**
     * 获取MiniLed灯开闭状态
     *
     * @return
     */
    public boolean getMiniLedSwitchStatus() {
        Bundle bundle = new Bundle();
        ApiUtil.setParams(bundle, SystemProviderDefines.METHOD_COMMON,
                SystemProviderDefines.MINI_LED_SWITCH_STATUS, null, false);
        boolean ret = ApiUtil.getData(bundle, Boolean.class);
        SkySSSLog.d(TAG, "get_mini_led_switch_status ret=" + ret);
        return ret;
    }

    /**
     * 获取亮度
     *
     * @return
     */
    public int getMiniLedBrightness() {
        Bundle bundle = new Bundle();
        ApiUtil.setParams(bundle, SystemProviderDefines.METHOD_COMMON,
                SystemProviderDefines.MINI_LED_BRIGHTNESS, null, false);
        Integer ret = ApiUtil.getData(bundle, Integer.class);
        SkySSSLog.d(TAG, "get_mini_led_brightness ret=" + ret);
        return ret;
    }

    /**
     * 获取亮度
     *
     * @return
     */
    public boolean setMiniLedBrightness(int brightness) {
        Bundle bundle = new Bundle();
        Bundle param = new Bundle();
        param.putInt(KEY_PARAM, brightness);
        ApiUtil.setParams(bundle, SystemProviderDefines.METHOD_COMMON,
                SystemProviderDefines.MINI_LED_BRIGHTNESS, param, true);
        Boolean ret = ApiUtil.getData(bundle, Boolean.class);
        SysLog.info(TAG, "set_mini_led_switch_status ret=" + ret + ",brightness=" + brightness);
        if (ret != null) {
            return ret;
        }
        return false;
    }

    /**
     * 获取MiniLed灯连接状态
     *
     * @return
     */
    public boolean getLedConnectedStatus() {
        Bundle bundle = new Bundle();
        ApiUtil.setParams(bundle, SystemProviderDefines.METHOD_COMMON,
                SystemProviderDefines.GET_LED_CONNECTED_STATUS, null, false);
        boolean ret = ApiUtil.getData(bundle, Boolean.class);
        SkySSSLog.i(TAG, "get_led_connected_status ret=" + ret);
        return ret;
    }


    /**
     * 显示旋转屏一键直达引导页
     */
    public void showRHGuidePage() {
        Bundle bundle = new Bundle();
        ApiUtil.setParams(bundle, SystemProviderDefines.METHOD_COMMON,
                SystemProviderDefines.SHOW_RH_GUIDE_PAGE, null, false);
        boolean ret = ApiUtil.getData(bundle, Boolean.class);
        SkySSSLog.i(TAG, "showRHGuidePage:" + ret);
    }


    /**
     * 数据采集接口
     *
     * @param eventId   日志项
     * @param eventData HashMap<日志名(String)，具体日志数据(String)>
     */
    public void systemDaterCollect(String eventId, HashMap eventData) {
        SkySSSLog.d("TCSystemApi", "eventId = " + eventId + ";eventData size = " + eventData.size());
        Iterator var3 = eventData.keySet().iterator();

        Bundle bundle = new Bundle();
        Bundle param = new Bundle();
        param.putString("eventId", eventId);
        param.putSerializable("eventData", eventData);
        ApiUtil.setParams(bundle, SystemProviderDefines.METHOD_COMMON, SystemProviderDefines.SYSTEM_DATER_COLLECT, param, true);
        boolean ret = (Boolean) ApiUtil.getData(bundle, Boolean.class);
        SkySSSLog.i("TCSystemApi", "SYSTEM_DATER_COLLECT:" + ret);
    }

    /**
     * get Game Speed White List
     *
     * @return List<String>  game speed white list
     */
    public List<String> getGameSpeedWhiteList() {
        Bundle bundle = new Bundle();
        ApiUtil.setParams(bundle, SystemProviderDefines.METHOD_COMMON,
                SystemProviderDefines.GAME_SPEED_WHITE_ARRAY, null, false);
        String[] retArray = ApiUtil.getData(bundle, String[].class);
        List resList = new ArrayList<String>();
        if (retArray != null) {
            resList = java.util.Arrays.asList(retArray);
        }
        SkySSSLog.d(TAG, "getGameSpeedWhiteList ret=" + resList);
        return resList;
    }

    /**
     * set Game Speed White List
     *
     * @param gameSpeedWhiteList :List to set
     */
    public boolean setGameSpeedWhiteList(List<String> gameSpeedWhiteList) {
        if (gameSpeedWhiteList == null) {
            return false;
        }
        String[] gameSpeedWhiteArray = new String[gameSpeedWhiteList.size()];
        gameSpeedWhiteList.toArray(gameSpeedWhiteArray);
        Bundle bundle = new Bundle();
        Bundle param = new Bundle();
        param.putStringArray(KEY_PARAM, gameSpeedWhiteArray);
        ApiUtil.setParams(bundle, SystemProviderDefines.METHOD_COMMON,
                SystemProviderDefines.GAME_SPEED_WHITE_ARRAY, param, true);
        Boolean ret = ApiUtil.getData(bundle, Boolean.class);
        SysLog.info(TAG, "setGameSpeedWhiteList ret=" + ret);
        if (ret != null) {
            return ret;
        }
        return false;
    }

    /**
     * 获取应用使用时间
     *
     * @return
     */
    public List<AppUsageInfo> getAppUsageInfoList() {
        Bundle param = new Bundle();
        param.putInt(KEY_RET_TYPE, PARAM_RET_BYTES);
        Bundle ret = executeCmd(TCSystemCmd.TC_SYSTEM_CMD_APP_USAGE.toString(),
                param, false);
        byte[] bytes = ret.getByteArray(KEY_RESULT);
        SkySSSLog.i("TCSystemApi", "getAppUsageInfoList result=" + bytes.length);
        List<AppUsageInfo> result = (List) SkyObjectByteSerialzie.toObject(bytes, List.class);
        SkySSSLog.i("TCSystemApi", "getAppUsageInfoList result=" + result);
        return result;
    }

    /**
     * 蓝牙灯带的设备信息
     *
     * @return String[]
     * String[0] device_id(设备id)
     * String[1] product_type_id(设备类型id)
     * String[2] product_brand_id(设备品牌id)
     * String[3] product_model(设备型号)
     * String[4] module_chip(设备当前是哦那个的模组型号)
     * String[5] protocol_version(协议版本号)
     */
    public String[] getTapeLightDeviceInfo() {
        Bundle bundle = new Bundle();
        ApiUtil.setParams(bundle, SystemProviderDefines.METHOD_COMMON,
                SystemProviderDefines.GET_TAPE_LIGHT_DEVICE_INFO, null, false);
        String[] retArray = ApiUtil.getData(bundle, String[].class);
        List resList = new ArrayList<String>();
        if (retArray != null) {
            resList = java.util.Arrays.asList(retArray);
        }
        SkySSSLog.d(TAG, "getGameSpeedWhiteList ret=" + resList);
        return retArray;
    }


    /**
     * 获取蓝牙灯带的连接状态
     *
     * @param macAddress
     * @return
     */
    public String getTapeLightConnectStatus(String macAddress) {
        Bundle bundle = new Bundle();
        Bundle param = new Bundle();
        param.putString("macAddress", macAddress);
        ApiUtil.setParams(bundle, SystemProviderDefines.METHOD_COMMON,
                SystemProviderDefines.SKY_CFG_TV_TAPE_LIGHT_CONNECTED_STATUS, param, false);
        String status = ApiUtil.getData(bundle, String.class);
        SkySSSLog.i(TAG, "SKY_CFG_TV_TAPE_LIGHT_CONNECTED_STATUS getTapeLightConnectStatus status=" + status);
        return status;
    }

    /**
     * 蓝牙灯带通知升级接口
     *
     * @param path 升级包路径
     * @return 底层是否收到了升级请求
     */
    public boolean requestUpgrade(String path) {
        Bundle bundle = new Bundle();
        Bundle param = new Bundle();
        param.putString("path", path);
        ApiUtil.setParams(bundle, SystemProviderDefines.METHOD_COMMON,
                SystemProviderDefines.SKY_CFG_TV_TAPE_LIGHT_REQUEST_UPGRADE, param, true);
        boolean status = ApiUtil.getData(bundle, Boolean.class);
        SkySSSLog.i(TAG, "SKY_CFG_TV_TAPE_LIGHT_REQUEST_UPGRADE requestUpgrade status=" + status);
        return status;
    }

    /**
     * 蓝牙灯带亮灯方向
     *
     * @return 1:从左到右  2:从右到左
     */
    public int getTapLightDirection() {
        Bundle bundle = new Bundle();
        ApiUtil.setParams(bundle, SystemProviderDefines.METHOD_COMMON,
                SystemProviderDefines.SKY_CFG_TV_TAPE_GET_LIGHT_DIRECTION, null, false);
        int direction = ApiUtil.getData(bundle, Integer.class);
        SkySSSLog.i(TAG, "SKY_CFG_TV_TAPE_LIGHT_DIRECTION getLightDirection direction=" + direction);
        return direction;
    }

    /**
     * set蓝牙灯带亮灯方向
     *
     * @return 1:从左到右  2:从右到左
     */
    public boolean setTapLightDirection(int direction) {
        Bundle bundle = new Bundle();
        Bundle param = new Bundle();
        param.putInt(KEY_PARAM, direction);
        ApiUtil.setParams(bundle, SystemProviderDefines.METHOD_COMMON,
                SystemProviderDefines.SKY_CFG_TV_TAPE_SET_LIGHT_DIRECTION, param, true);
        boolean ret = ApiUtil.getData(bundle, Boolean.class);
        SkySSSLog.i(TAG, "SKY_CFG_TV_TAPE_SET_LIGHT_DIRECTION setTapLightDirection ret=" + ret);
        return ret;
    }


    /**
     * 蓝牙灯带是否为低配版本
     *
     * @return
     */
    public boolean getLightStripIsLowProfile() {
        Bundle bundle = new Bundle();
        ApiUtil.setParams(bundle, SystemProviderDefines.METHOD_COMMON,
                SystemProviderDefines.SKY_CFG_TV_TAPE_LIGHT_STRIP_IS_LOW_PROFILE, null, false);
        boolean ret = ApiUtil.getData(bundle, Boolean.class);
        SkySSSLog.i(TAG, "SKY_CFG_TV_TAPE_LIGHT_STRIP_IS_LOW_PROFILE getLightStripIsLowProfile ret=" + ret);
        return ret;
    }

    /**
     * 蓝牙灯带升级回调监听
     *
     * @param callBack 回调接口
     * @param register 是否注册回调
     */
    public void setITapeLightUpgradeStatusChangeListener(ITapeLightUpgradeStatusChangeCallBack callBack, boolean register) {
        SkySSSLog.d(TAG, "setITapeLightUpgradeStatusChangeListener callBack=" + callBack + ",register="
                + register);
        String cmd = SkyConfigDefs.SKY_CFG_TV_TAPE_LIGHT_UPGRADE_STATUS_CHANGE;
        if (register) {
            IPCCallback callback1 = new IPCCallback(callBack, new String[]{
                    "onFailure", "onSuccess"},
                    new Class[][]{{String.class}, {String.class}}, false);
            addIPCCallback(cmd, callback1);
            Bundle bundle = new Bundle();
            bundle.putBoolean(KEY_IS_PLUGIN, true);
            executeCmd(cmd, bundle, true);
        } else {
            removeIPCCallback(cmd, callBack);
        }
    }

    /**
     * 是否支持OPS功能
     *
     * @return
     */
    public boolean isSupportOps() {
        Bundle bundle = new Bundle();
        ApiUtil.setParams(bundle, SystemProviderDefines.METHOD_COMMON,
                SystemProviderDefines.COMMON_TV_SUPPORT_OPS, null, false);
        boolean ret = ApiUtil.getData(bundle, Boolean.class);
        SkySSSLog.i(TAG, "isSupportOps ret=" + ret);
        return ret;
    }

    /**
     * 获取机型，只是供显示用。
     *
     * @return String
     */
    public String getSkyTypeForDisplay() {
        String skyTypeForDisplay = GeneralConfig.getConfig("SKYTYPE_EXTERNAL");
        if (TextUtils.isEmpty(skyTypeForDisplay) || "无".equals(skyTypeForDisplay)) {
            skyTypeForDisplay = SystemProperties.get("third.get.skytype");
            if (TextUtils.isEmpty(skyTypeForDisplay)) {
                skyTypeForDisplay = SystemProperties.get("ro.build.skytype");
                SkySSSLog.d(TAG, "skyTypeForDisplay ro:" + skyTypeForDisplay);
            } else {
                SkySSSLog.d(TAG, "skyTypeForDisplay third:" + skyTypeForDisplay);
            }
        } else {
            SkySSSLog.d(TAG, "skyTypeForDisplay config:" + skyTypeForDisplay);
        }
        return skyTypeForDisplay;
    }

    /**
     * 设置平台模式启动项式及模式主页（包名类名参数）
     * 设置后，下次开机启动要进入这个模式主页，并且按主页键也启动这个。
     */
    public void setPlatformModelBootSetting(Intent intent) {
        if (intent == null) {
            return;
        }
        ComponentName componentName = intent.getComponent();
        if (componentName == null)
            return;
        String packageName = componentName.getPackageName();
        String className = componentName.getClassName();
        String modelName = intent.getStringExtra("modelName");
        SkySSSLog.d(TAG, "setPlatformModelBootSetting packageName = " + packageName + ";className = " + className + "  modelName:" + modelName);
        Bundle bundle = new Bundle();
        Bundle param = new Bundle();
        param.putString("packageName", packageName);
        param.putString("className", className);
        param.putString("modelName", modelName);
        ApiUtil.setParams(bundle, SystemProviderDefines.METHOD_COMMON, SystemProviderDefines.PLATFORM_MODE_BOOT_SET, param, true);
        boolean ret = (Boolean) ApiUtil.getData(bundle, Boolean.class);
        SkySSSLog.i(TAG, "setPlatformModelBootSetting:" + ret);
    }

    /**
     * 读取平台模式启动项式及模式主页（包名类名参数）
     */
    public Intent getPlatformModelBootSetting() {
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        ApiUtil.setParams(bundle, SystemProviderDefines.METHOD_COMMON,
                SystemProviderDefines.PLATFORM_MODE_BOOT_GET, null, false);
        String jsonStr = ApiUtil.getData(bundle, String.class);
        if (!TextUtils.isEmpty(jsonStr)) {
            JSONObject json;
            try {
                json = JSONObject.parseObject(jsonStr);
                String packageName = json.getString("packageName");
                String className = json.getString("className");
                String modelName = json.getString("modelName");
                ComponentName componentName = new ComponentName(packageName, className);
                intent.setComponent(componentName);
                intent.putExtra("modelName", modelName);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return intent;
    }

    /**
     * 清除平台开机启动模式及模式主页（包名类名参数）
     */
    public void clearPlatformModelBootSetting() {
        Bundle bundle = new Bundle();
        ApiUtil.setParams(bundle, SystemProviderDefines.METHOD_COMMON,
                SystemProviderDefines.PLATFORM_MODE_BOOT_CLEAR, null, true);
        boolean ret = ApiUtil.setData(bundle);
        SkySSSLog.i(TAG, "clearPlatformModelBootSetting ret=" + ret);
    }

    private static boolean isPackageInstalled(Context context, String pkg) {
        PackageManager mPackageManager = context.getApplicationContext().getPackageManager();
        PackageInfo intent;
        try {
            intent = mPackageManager.getPackageInfo(pkg, 0);
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
        if (intent == null)
            return false;
        else
            return true;
    }

    /**
     * 把系统时间
     * return: 格式化时间
     */
    public boolean setSyetemWebTime() {
        Bundle bundle = new Bundle();
        ApiUtil.setParams(bundle, SystemProviderDefines.METHOD_COMMON,
                SystemProviderDefines.COMMON_WEB_TIME, null, true);
        boolean ret = ApiUtil.getData(bundle, Boolean.class);
        SkySSSLog.i(TAG, "web Time ret=" + ret);
        return ret;
    }

    /**
     * 设置rtc的开机时间
     *
     * @param cmdType     指令类型
     *                    #define CMDID_EXTRA_RTCSETSYSTIME                               1
     *                    #define CMDID_EXTRA_RTCSETALARMTIME                             2
     *                    #define CMDID_EXTRA_RTCCLEARALARM                               3
     *                    #define CMDID_EXTRA_WRITERTCINITTIME                            4
     *                    #define CMDID_EXTRA_RTCENABLEALARM                              5
     *                    #define CMDID_EXTRA_RTCSETWAKEUPDATE                            6
     *                    #define CMDID_EXTRA_RTCGETWAKEUPDIFFSECONDS                     7
     * @param enableAlarm enable:1  disable:0
     * @param type
     * @param timeStr     时间（格式: 2022/1/11 15:30:25 2)
     * @return 是否设置成功
     */
    public boolean setRTCStartUpTime(int cmdType, int enableAlarm, int type, String timeStr) {
        Bundle bundle = new Bundle();
        Bundle param = new Bundle();
        param.putInt("cmdType", cmdType);
        param.putInt("enableAlarm", enableAlarm);
        param.putInt("type", type);
        param.putString("timeStr", timeStr);
        ApiUtil.setParams(bundle, SystemProviderDefines.METHOD_COMMON,
                SystemProviderDefines.SET_RTC_STARTUP_TIME, param, true);
        boolean ret = ApiUtil.getData(bundle, Boolean.class);
        SkySSSLog.i(TAG, "setRTCStartUpTime =" + ret);
        return ret;
    }


    /**
     * 启动图像半屏设置
     */
    public void startPictureSetting() {
        try {
            Intent intent = new Intent();
            SystemManagerApi systemManagerApi;
            if(SkyContext.context!=null){
                systemManagerApi = new SystemManagerApi(SkyContext.context);
            }else {
                systemManagerApi = new SystemManagerApi(this.context);
            }
            if (systemManagerApi.getSystemVersion().startsWith("9")) {
                intent.setClassName("com.skyworth.setting", "com.skyworth.setting.service.HalfScreenService");
                intent.putExtra("Key", "android.settings.PICTURE_SETTINGS");
                if(SkyContext.context!=null){
                    SkyContext.context.startService(intent);
                }else {
                    this.context.startService(intent);
                }
            } else {
                intent.setAction("android.settings.PICTURE_SETTINGS");
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                if(SkyContext.context!=null){
                    SkyContext.context.startActivity(intent);
                }else {
                    this.context.startActivity(intent);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 启动声音半屏设置
     */
    public void startSoundSetting() {
        try {
            Intent intent = new Intent();
            SystemManagerApi systemManagerApi;
            if(SkyContext.context!=null){
                systemManagerApi = new SystemManagerApi(SkyContext.context);
            }else {
                systemManagerApi = new SystemManagerApi(this.context);
            }
            if (systemManagerApi.getSystemVersion().startsWith("9")) {
                intent.setClassName("com.skyworth.setting", "com.skyworth.setting.service.HalfScreenService");
                intent.putExtra("Key", "android.settings.SOUND_SETTINGS");
                if(SkyContext.context!=null){
                    SkyContext.context.startService(intent);
                }else {
                    this.context.startService(intent);
                }

            } else {
                intent.setAction("android.settings.SOUND_SETTINGS");
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                if(SkyContext.context!=null){
                    SkyContext.context.startActivity(intent);
                }else {
                    this.context.startActivity(intent);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 是否支持Feature
     *
     * @param featureName
     * @return 是否支持
     */
    public boolean isFeatureSupport(String featureName) {
        boolean isFeatureSupport = false;
        if ("FEATURE_BLUETOOTH".equals(featureName)) {
            isFeatureSupport = isBluetoothSupported();
            return isFeatureSupport;
        }else if("SKY_CFG_TV_EQUIPMENT".equals(featureName)) {
            isFeatureSupport = isEquipmentSupported();
            return isFeatureSupport;
        }
        Bundle bundle = new Bundle();
        Bundle param = new Bundle();
        param.putString(KEY_PARAM, featureName);
        ApiUtil.setParams(bundle, SystemProviderDefines.METHOD_COMMON,
                SystemProviderDefines.IS_FEATURE_SUPPORT, param, false);
        Boolean ret = ApiUtil.getData(bundle, Boolean.class);
        SkySSSLog.d(TAG, "isFeatureSupport ret=" + ret);

        if (ret != null) {
            isFeatureSupport = ret;
        }
        return isFeatureSupport;
    }

    /**
     * 是否支持蓝牙
     *
     * @return true:支持
     */
    private static boolean isBluetoothSupported() {
        boolean isSupport = SettingConfig.haveXmlConfig("SKY_CFG_TV_BLUETOOTH");
        SkySSSLog.d(TAG, "isBluetoothSupported:" + isSupport);
        return isSupport;
    }

    /**
     * 是否支持外设
     *
     * @return true:支持
     */
    private static boolean isEquipmentSupported() {
        boolean isSupport = SettingConfig.haveXmlConfig("SKY_CFG_TV_EQUIPMENT");
        SkySSSLog.d(TAG, "isEquipmentSupported:" + isSupport);
        return isSupport;
    }

    /**
     * 设置日志开关
     *
     * @param index 枚举数组的位置 0:OFF(关闭日志打印),1:DEBUG(调试模式),2:LOG(抓取日志)
     *              {@link SkyConfigDefs.SKY_CFG_LOG_CONTROL_MODE_ENUM}
     */
    public void setLogControlSwitcher(int index) {
        Bundle param = new Bundle();
        param.putInt(KEY_PARAM_TYPE, PARAM_RET_INT);
        param.putInt(KEY_TC_TYPE, TC_DATA_ENUM);
        param.putInt(KEY_PARAM, index);
        Bundle ret = executeCmd(SkyConfigDefs.SKY_CFG_LOG_CONTROL_MODE, param, true);
        String result = ret.getString(KEY_RESULT);
        SkySSSLog.i(TAG, "setLogControlSwitcher result=" + result + ",index=" + index);
    }

    /**
     * 获取日志开关当前状态
     *
     * @return 枚举对象对应的字符串 "OFF","DEBUG","LOG"
     * {@link SkyConfigDefs.SKY_CFG_LOG_CONTROL_MODE_ENUM}
     */
    public String getLogControlSwitcher() {
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_RET_TYPE, PARAM_RET_STRING);
        Bundle ret = executeCmd(SkyConfigDefs.SKY_CFG_LOG_CONTROL_MODE, bundle, false);
        String result = ret.getString(KEY_RESULT);
        SkySSSLog.i(TAG, "getLogControlSwitcher result=" + result);
        return result;
    }

    /**
     * 是否支持日志控制
     */
    public boolean isSupportLogControl() {
        Bundle param = new Bundle();
        param.putInt(KEY_RET_TYPE, PARAM_RET_SERIAL);
        Bundle ret = executeCmd(SkyConfigDefs.SKY_CFG_IS_SUPPORT_LOG_CONTROL.toString(),
                param, false);
        boolean result = parseBool(ret.getByteArray(KEY_RESULT));
        SkySSSLog.i(TAG, "isSupportLogControl result=" + result);
        return result;
    }

    /**
     * 获取升级前的酷开系统版本
     */
    public String getLastSystemVersion() {
        Bundle bundle = new Bundle();
        Bundle param = new Bundle();
        ApiUtil.setParams(bundle, SystemProviderDefines.METHOD_COMMON,
                SystemProviderDefines.SKY_LAST_SYSTEM_VERSION, param, false);
        String lastSysVer = ApiUtil.getData(bundle, String.class);
        SkySSSLog.i(TAG, "lastSysVer =" + lastSysVer);
        return lastSysVer;
    }

    /**
     * 获取升级前的编译版本
     */
    public String getLastBuildVersion() {
        Bundle bundle = new Bundle();
        Bundle param = new Bundle();
        ApiUtil.setParams(bundle, SystemProviderDefines.METHOD_COMMON,
                SystemProviderDefines.SKY_LAST_BUILD_VERSION, param, false);
        String lastBuildVer = ApiUtil.getData(bundle, String.class);
        SkySSSLog.i(TAG, "getLastBuildVersion lastBuildVer=" + lastBuildVer);
        return lastBuildVer;
    }

//    /**
//     * 设置媒体播放场景
//     *
//     * @param mediaScene：  0，退出播放
//     *                      1，帧享 4K 60
//     *                      2 帧享 4K 120
//     * 	                    3 帧享 4K 25
//     *                      4 非帧享播放
//     * @return 0：调用成功\n  -1：调用失败
//     */
//    public int setMediaScene(int mediaScene) {
//        Bundle bundle = new Bundle();
//        Bundle param = new Bundle();
//        param.putInt(KEY_PARAM, mediaScene);
//        ApiUtil.setParams(bundle, SystemProviderDefines.METHOD_COMMON,
//                SystemProviderDefines.COMMON_MEDIA_SCENE, param, true);
//        Integer ret = ApiUtil.getData(bundle, Integer.class);
//        SysLog.info(TAG, "setMediaScene ret=" + ret + ",mediaScene=" + mediaScene);
//        if (ret != null) {
//            return ret;
//        }
//        return -1;
//    }

    /**
     * 概述：设置待机原因<br/>
     * 适用条件：<br/>
     * 执行流程：<br/>
     * 注意事项：<br/>
     * @param reason  STANDBY_REASON_VGA_NO_SIGNAL = 0x01;
     *                STANDBY_REASON_HDMI_NO_SIGNAL = 0x02;
     *                STANDBY_REASON_POWER_KEY = 0x03;
     * @return
     * @date 222-08-31
     */
    public boolean setStandbyReason(int reason) {
        Bundle bundle = new Bundle();
        Bundle param = new Bundle();
        param.putInt(KEY_PARAM, reason);
        ApiUtil.setParams(bundle, SystemProviderDefines.METHOD_COMMON,
                SystemProviderDefines.COMMON_STANDBY_REASON, param, true);
        Boolean ret = ApiUtil.getData(bundle, Boolean.class);
        SysLog.info(TAG, "setStandbyReason reason=" + reason);
        if (ret != null) {
            return ret;
        }
        return false;
    }

    /**
     * 删除/data/log
     */
    public void deleteDataLog() {
        deleteFolderFile("/data/log");
    }

    private void deleteFolderFile(String filePath) {
        if (!TextUtils.isEmpty(filePath)) {
            File file = new File(filePath);
            if (file.isDirectory()) {
                File files[] = file.listFiles();
                for (int i = 0; i < files.length; i++) {
                    deleteFolderFile(files[i].getAbsolutePath());
                }
            }
            if (!file.isDirectory()) {
                file.delete();
            } else {
                if (file.listFiles().length == 0) {
                    file.delete();
                }
            }
        }
    }
	
	
    /**
     * 外部程序向Systemservice传递数据
     * @param info : data e.g. {"eventId":4,"data":{"isUserAllowAppUpgrade":true}}
     *  eventId :详细参看 com.tianci.system.appupgrade.Data.AppUpgrade  定义
     */
    public boolean notifyToSystemService(String info){
        if(!TextUtils.isEmpty(info)){
            Bundle param = new Bundle();
            param.putInt(KEY_PARAM_TYPE, PARAM_RET_STRING);
            param.putInt(KEY_RET_TYPE, PARAM_RET_SERIAL);
            param.putString(KEY_PARAM, info);
            Bundle ret = executeCmd(SkyConfigDefs.SKY_CFG_SEND_DATA_TO_SYSTEM_SERVICE, param, true);
            boolean result = parseBool(ret.getByteArray(KEY_RESULT));
            Log.d(TAG, "notifyToSystemService param = " + info + ",ret=" + result);
            return result;
        }
        return false;
    }

    /**
     * 获取解码器是否可用
     */
    public boolean isDecoderAvailable(){
        Bundle bundle = new Bundle();
        ApiUtil.setParams(bundle, SystemProviderDefines.METHOD_COMMON,
                SystemProviderDefines.COMMON_IS_DECODER_AVAILABLE,
                null, false);
        Boolean ret = ApiUtil.getData(bundle, Boolean.class);
        SkySSSLog.d(TAG, "isDecoderAvailable ret=" + ret);
        if (ret != null) {
            return ret;
        }
        return true;
    }

    /**
     * 获取EMMC 寿命
     * 0X267 Register  0x01 Normal Normal ;0x02  Warning  Consumed 80% ofreserved block ; 0x03 Urgent
     * 0X268 Register  0x0A  90% - 100% device life time used ; 0x0B Exceeded its maximum estimated device life time
     * 0X269 Register  0x0A  90% - 100% device life time used ; 0x0B Exceeded its maximum estimated device life time
     * @return
     */
    public int[] getEMMCLife() {
        Bundle bundle = new Bundle();
        ApiUtil.setParams(bundle, SystemProviderDefines.METHOD_COMMON,
                SystemProviderDefines.GET_EMMC_LIFE, null, false);
        int[] ret = ApiUtil.getData(bundle, int[].class);
        SkySSSLog.d(TAG, "getEMMCLife ret=" + ret);
        return ret;
    }

    /**
     * 是否支持更换牌照商
     *
     * @return boolean true:支持
     *                 false:不支持
     */
    public boolean isSupportChangeLicensor(){
        Bundle bundle = new Bundle();
        ApiUtil.setParams(bundle, SystemProviderDefines.METHOD_COMMON,
                SystemProviderDefines.IS_SUPPORT_CHANGE_LICENSOR,
                null, false);
        Boolean ret = ApiUtil.getData(bundle, Boolean.class);
        SkySSSLog.d(TAG, "isSupportChangeLicensor ret=" + ret);
        if (ret != null) {
            return ret;
        }
        return false;
    }
    /**
     * get VirtualSkyType
     *
     * @return String virtualSkyType
     */
    public String getVirtualSkyType() {
        Bundle bundle = new Bundle();
        ApiUtil.setParams(bundle, SystemProviderDefines.METHOD_COMMON,
                SystemProviderDefines.VIRTUAL_SKY_TYPE, null, false);
        String ret = ApiUtil.getData(bundle, String.class);
        SkySSSLog.d(TAG, "getVirtualSkyType ret=" + ret);
        return ret;
    }

    /**
     * set virtualSkyType
     *
     * @return true success；false fail
     */
    public boolean setVirtualSkyType(String virtualSkyType) {
        Bundle bundle = new Bundle();
        Bundle param = new Bundle();
        param.putString(KEY_PARAM, virtualSkyType);
        ApiUtil.setParams(bundle, SystemProviderDefines.METHOD_COMMON,
                SystemProviderDefines.VIRTUAL_SKY_TYPE, param, true);
        Boolean ret = ApiUtil.getData(bundle, Boolean.class);
        SysLog.info(TAG, "setVirtualSkyType ret=" + ret + ",virtualSkyType=" + virtualSkyType);
        if (ret != null) {
            return ret;
        }
        return false;
    }

    /**
     * set mac
     * macAddress的格式参考  2026aaf35cef
     * @return true success；false fail
     */
    public boolean setDevMacAddress(String macAddress) {
        Bundle bundle = new Bundle();
        Bundle param = new Bundle();
        param.putString(KEY_PARAM, macAddress);
        ApiUtil.setParams(bundle, SystemProviderDefines.METHOD_COMMON,
                SystemProviderDefines.DEV_MAC, param, true);
        Boolean ret = ApiUtil.getData(bundle, Boolean.class);
        SysLog.info(TAG, "setDevMacAddress ret=" + ret + ",macAddress=" + macAddress);
        if (ret != null) {
            return ret;
        }
        return false;
    }

    /**
     * query SkyUsageStatus
     */
    public JSONArray querySkyUsageStatus(long startTime, long endTime){
        JSONArray mSkyUsageStatus = new JSONArray();
        Bundle bundle = new Bundle();
        Bundle param = new Bundle();
        param.putLong("startTime", startTime);
        param.putLong("endTime", endTime);
        ApiUtil.setParams(bundle, SystemProviderDefines.METHOD_COMMON,
                SystemProviderDefines.QUERY_SKY_USAGE_STATUS, param, false);
        String[] retArray = ApiUtil.getData(bundle, String[].class);
        List<String> resList = new ArrayList<String>();
        if (retArray != null) {
            resList = java.util.Arrays.asList(retArray);
        }
        if(resList!=null){
            for(String strTmp:resList){
                try {
                    JSONObject jsonTmp = JSONObject.parseObject(strTmp);
                    mSkyUsageStatus.add(jsonTmp);
//                  SkySSSLog.d(TAG, "querySkyUsageStatus " + jsonTmp);
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }else {
            SkySSSLog.d(TAG, "querySkyUsageStatus null");
        }
        return mSkyUsageStatus;
    }

    /**
     * 系统启动时间，单位秒，获取不到默认返回0
     */
    public int systemBootDuration(){
        Bundle bundle = new Bundle();
        ApiUtil.setParams(bundle, SystemProviderDefines.METHOD_COMMON,
                SystemProviderDefines.SYSTEM_BOOT_DURATION, null, false);
        Integer ret = ApiUtil.getData(bundle, Integer.class);
        int result = ret != null ? ret : 0;
        SkySSSLog.i(TAG, "systemBootDuration result=" + result);
        return result;
    }

    /**
     * 是否支持HDR
     *  hdrType
     *    SysConst.YOUKU_FRAME_ENJOY_HDR = 0;  // 优酷帧享HDR
     *     SysConst.IQIYI_FRAME_QI_HDR = 1;   // 爱奇艺臻绮映像
     * @return true: support
     *         false:not support
     */
    public boolean isSupportHDR(int hdrType) {
        boolean isSupportHDR = false;
        Bundle bundle = new Bundle();
        Bundle param = new Bundle();
        param.putInt(KEY_PARAM, hdrType);
        ApiUtil.setParams(bundle, SystemProviderDefines.METHOD_COMMON,
                SystemProviderDefines.COMMON_IS_SUPPORT_HDR, param, false);
        Boolean ret = ApiUtil.getData(bundle, Boolean.class);
        if (ret != null) {
            isSupportHDR = ret;
        }
        SkySSSLog.i(TAG, "isSupportHDR hdrType:"+hdrType+" ret=" + isSupportHDR);
        return isSupportHDR;
    }
}