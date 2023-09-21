package com.tianci.system.api;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.skyworth.framework.config.GeneralConfig;
import com.skyworth.framework.skysdk.util.SkyData;
import com.skyworth.framework.skysdk.util.SkyJSONUtil;
import com.tianci.loader.LoaderParams;
import com.tianci.loader.LoaderViewStatus;
import com.tianci.loader.SkyLoaderServiceDefs.SkyLoaderServiceCmdEnum;
import com.tianci.system.callback.CommonObserver;
import com.tianci.system.command.TCSystemCmd;
import com.tianci.system.data.TCEnumSetData;
import com.tianci.system.data.TCInfoSetData;
import com.tianci.system.data.TCSetData;
import com.tianci.system.data.TCSwitchSetData;
import com.tianci.system.define.SkyConfigDefs;
import com.tianci.system.define.SysConst;
import com.tianci.system.define.SystemProviderDefines;
import com.tianci.system.define.TCEnvKey;
import com.tianci.system.utils.ApiUtil;
import com.tianci.utils.SkySSSLog;

import static com.tianci.system.define.SkyConfigDefs.SKY_CFG_TV_HEALTH_EYE_MODE;
import static com.tianci.system.utils.ApiUtil.KEY_IS_PLUGIN;
import static com.tianci.system.utils.ApiUtil.KEY_IS_SERIALIZE;
import static com.tianci.system.utils.ApiUtil.KEY_PARAM;
import static com.tianci.system.utils.ApiUtil.KEY_PARAM_TYPE;
import static com.tianci.system.utils.ApiUtil.KEY_RESULT;
import static com.tianci.system.utils.ApiUtil.KEY_RET_TYPE;
import static com.tianci.system.utils.ApiUtil.KEY_TC_TYPE;
import static com.tianci.system.utils.ApiUtil.PARAM_RET_BOOL;
import static com.tianci.system.utils.ApiUtil.PARAM_RET_BYTES;
import static com.tianci.system.utils.ApiUtil.PARAM_RET_INT;
import static com.tianci.system.utils.ApiUtil.PARAM_RET_STRING;
import static com.tianci.system.utils.ApiUtil.TC_DATA_ENUM;
import static com.tianci.system.utils.ApiUtil.TC_DATA_INFO;
import static com.tianci.system.utils.ApiUtil.TC_DATA_SWITCH;

/**
 * @Date : 2015年8月25日
 * @Author : Zhan Yu
 * @Description : 设置API
 */
public class TCSettingApi {
    private static final String TAG = "TCSettingApi";

    public TCSettingApi(Context context) {
        SkySSSLog.w(TAG, "created");
        Context ctx = context.getApplicationContext();
        if (ctx == null) {
            ctx = context;
        }
        ApiUtil.setContext(ctx);
    }

    private final static String SUPPORT_XDR = "SUPPORT_XDR";

    public static TCSetData getData(String key) {
        SkySSSLog.d(TAG, "getData key=" + key);
        if (SkyConfigDefs.SKY_CFG_IS_SUPPORT_XDR_MODE.equalsIgnoreCase(key)) {
            boolean supportXDR = GeneralConfig.getBoolConfig(SUPPORT_XDR, false);
            SkySSSLog.d(TAG, "getData supportXDR=" + supportXDR);
            TCSwitchSetData data0 = new TCSwitchSetData();
            data0.setOn(supportXDR);
            return data0;
        }
        Bundle bundle = new Bundle();
        bundle.putString(KEY_PARAM, key);
        try {
            Bundle ret = ApiUtil.getTCData(bundle);
            byte[] bytes = ret.getByteArray(KEY_RESULT);
            int type = ret.getInt(KEY_TC_TYPE);
            boolean serialize = ret.getBoolean(KEY_IS_SERIALIZE);
            return ApiUtil.getTCSetDataSerialize(ret, bytes, type, serialize);
        } catch (Exception e) {
            SkySSSLog.e(TAG, "getData e=" + e.getMessage());
        }
        return null;
    }

    public static TCSetData setData(TCSetData data) {
        Bundle bundle = new Bundle();
        try {
            SkySSSLog.d(TAG, "setData key=" + data.getName());
            ApiUtil.setTCSetDataType(bundle, data);
            bundle.putByteArray(KEY_PARAM, data.toBytes());
            Bundle ret = ApiUtil.setTCData(bundle);
            byte[] bytes = ret.getByteArray(KEY_RESULT);
            int type = ret.getInt(KEY_TC_TYPE);
            return ApiUtil.getTCSetDataType(bytes, type);
        } catch (Exception e) {
            SkySSSLog.e(TAG, "setData e=" + e.getMessage());
        }
        return null;
    }

    /**
     * 延迟系统更新提示
     *
     * @param status 更新的状态
     */
    public void upgradeDelayPrompt(LoaderViewStatus status) {
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_PARAM_TYPE, PARAM_RET_INT);
        bundle.putInt(KEY_PARAM, status.ordinal());
        Bundle ret = executeCmd(SkyLoaderServiceCmdEnum.CMD_DELAY_PROMPT.toString(),
                bundle, true);
        SkySSSLog.d(TAG, "upgradeDelayPrompt ret=" + ret);
    }

    /**
     * 检查系统更新
     *
     * @param isUI 是否显示UI
     */
    public void upgradeCheck(boolean isUI) {
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_PARAM_TYPE, PARAM_RET_BOOL);
        bundle.putBoolean(KEY_PARAM, isUI);
        Bundle ret = executeCmd(SkyLoaderServiceCmdEnum.CMD_CHECK_UPGRADE.toString(),
                bundle, true);
        SkySSSLog.d(TAG, "upgradeCheck ret=" + ret + ",isUI=" + isUI);
    }

    /**
     * 升级界面按钮点击
     *
     * @param status view状态
     */
    public void upgradeButtonClick(LoaderViewStatus status) {
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_PARAM_TYPE, PARAM_RET_INT);
        bundle.putInt(KEY_PARAM, status.ordinal());
        Bundle ret = executeCmd(SkyLoaderServiceCmdEnum.CMD_CLICK_BUTTON.toString(),
                bundle, true);
        SkySSSLog.d(TAG, "upgradeButtonClick ret=" + ret + ",status=" + status);
    }

    /**
     * 获取升级界面各项值
     *
     * @return LoaderParams
     */
    public LoaderParams upgradeGetValues() {
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_RET_TYPE, PARAM_RET_STRING);
        Bundle ret = executeCmd(SkyLoaderServiceCmdEnum.CMD_GET_UI_VALUES.toString(),
                bundle, false);
        String string = ret.getString(KEY_RESULT);
        LoaderParams result = (LoaderParams) SkyJSONUtil.getInstance().parse(string, LoaderParams.class);
        SkySSSLog.d(TAG, "upgradeGetValues ret=" + ret + ",string=" + string + ",result=" + result);
        return result;
    }

    /**
     * 获取升级下载进度
     *
     * @return 进度
     */
    public int upgradeDownloadProgress() {
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_RET_TYPE, PARAM_RET_INT);
        Bundle ret = executeCmd(SkyLoaderServiceCmdEnum.LOADERSERVICE_CMD_GET_DOWNLOAD_PERCENT.toString(),
                bundle, false);
        int result = ret.getInt(KEY_RESULT);
        SkySSSLog.d(TAG, "upgradeDownloadProgress ret=" + ret + ",result=" + result);
        return result;
    }

    /**
     * 是否存在在线升级包
     *
     * @return true存在
     */
    public boolean upgradeExist() {
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_RET_TYPE, PARAM_RET_BOOL);
        Bundle ret = executeCmd(SkyLoaderServiceCmdEnum.LOADERSERVICE_CMD_HAS_UPGRADE.toString(),
                bundle, false);
        boolean result = ret.getBoolean(KEY_RESULT);
        SkySSSLog.d(TAG, "upgradeExist ret=" + ret + ",result=" + result);
        return result;
    }

    /**
     * 设置升级主界面是否前台显示
     *
     * @param foreground true:在前台显示；false:不在前台
     */
    public void upgradeSettingForeground(boolean foreground) {
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_PARAM_TYPE, PARAM_RET_BOOL);
        bundle.putBoolean(KEY_PARAM, foreground);
        executeCmd(SkyLoaderServiceCmdEnum.CMD_SET_UI_FOREGROUND.toString(), bundle, false);
        SkySSSLog.d(TAG, "upgradeSettingForeground foreground=" + foreground);
    }

    /**
     * 设置区域位置
     *
     * @param location 位置信息
     */
    public void setLocation(String location) {
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_PARAM_TYPE, PARAM_RET_BYTES);
        bundle.putInt(KEY_TC_TYPE, TC_DATA_INFO);
        bundle.putByteArray(KEY_PARAM, location.getBytes());
        Bundle ret = executeCmd(TCEnvKey.SKY_SYSTEM_ENV_LOCATION, bundle, true);
        SkySSSLog.d(TAG, "setLocation ret=" + ret + ",location=" + location);
    }

    /**
     * 本地校验激活码是否正确
     *
     * @param code 激活码，mac 售后输入的mac地址
     * @return true 正确
     */
    public boolean isCheckCodeAndMacInLocal(String code, String mac) {
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_PARAM_TYPE, PARAM_RET_BYTES);
        bundle.putInt(KEY_RET_TYPE, PARAM_RET_BOOL);
        SkyData skyData = new SkyData();
        skyData.add("code", code);
        skyData.add("mac", mac);
        bundle.putByteArray(KEY_PARAM, skyData.toBytes());
        Bundle ret = executeCmd(TCSystemCmd.TC_SYSTEM_CMD_CHECK_CODE_MAC_LOCAL.toString(),
                bundle, false);
        SkySSSLog.d(TAG, "isCheckCodeAndMacInLocal ret=" + ret + ",code=" + code + ",mac=" + mac);
        return ret.getBoolean(KEY_RESULT);
    }

    private Bundle executeCmd(String cmd, Bundle body, boolean isSet) {
        return ApiUtil.executeSystemCmd(cmd, body, isSet);
    }

    /**
     * 关屏待机默认开关设置
     *
     * @param value true开；false关
     */
    public void setCloseScreenSound(boolean value) {
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_TC_TYPE, TC_DATA_SWITCH);
        bundle.putInt(KEY_PARAM_TYPE, PARAM_RET_BOOL);
        bundle.putBoolean(KEY_IS_PLUGIN, true);
        bundle.putBoolean(KEY_PARAM, value);
        executeCmd(SkyConfigDefs.SKY_CFG_TV_CLOSE_SCREEN_SOUND_SWITCH, bundle, true);
        SkySSSLog.i(TAG, "setCloseScreenSound value=" + value);
    }

    /**
     * 获取关屏待机默认开关
     *
     * @return true开；false关
     */
    public boolean getCloseScreenSound() {
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_RET_TYPE, PARAM_RET_BOOL);
        bundle.putBoolean(KEY_IS_PLUGIN, true);
        Bundle ret = executeCmd(SkyConfigDefs.SKY_CFG_TV_CLOSE_SCREEN_SOUND_SWITCH, bundle, false);
        boolean result = ret.getBoolean(KEY_RESULT);
        SkySSSLog.i(TAG, "getCloseScreenSound result=" + result);
        return result;
    }

    /**
     * AIPQ 智能化调节 提示开关设置
     *
     * @param value true开；false关
     */
    public void setAipqTipsSwitch(boolean value) {
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_TC_TYPE, TC_DATA_SWITCH);
        bundle.putInt(KEY_PARAM_TYPE, PARAM_RET_BOOL);
        bundle.putBoolean(KEY_PARAM, value);
        bundle.putBoolean(KEY_IS_PLUGIN, true);
        executeCmd(SkyConfigDefs.SKY_CFG_TV_AIPQ_TIPS_SWITCH, bundle, true);
        SkySSSLog.i(TAG, "setAipqTipsSwitch value=" + value);
    }

    /**
     * 获取AIPQ 智能化调节 提示开关
     *
     * @return true开；false关
     */
    public boolean getAipqTipsSwitch() {
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_RET_TYPE, PARAM_RET_BOOL);
        bundle.putBoolean(KEY_IS_PLUGIN, true);
        Bundle ret = executeCmd(SkyConfigDefs.SKY_CFG_TV_AIPQ_TIPS_SWITCH, bundle, false);
        boolean result = ret.getBoolean(KEY_RESULT);
        SkySSSLog.i(TAG, "getAipqTipsSwitch result=" + result);
        return result;
    }

    /**
     * 屏幕显示方向设置
     *
     * @param value {@link SysConst#SKY_CFG_TV_DISPLAY_MODE_ROTATION_LEVEL},
     *              {@link SysConst#SKY_CFG_TV_DISPLAY_MODE_ROTATION_CLOCKWISE},
     *              {@link SysConst#SKY_CFG_TV_DISPLAY_MODE_ROTATION_ANTI_CLOCKWISE}
     */
    public void setDisplayModeRotation(int value) {
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_TC_TYPE, TC_DATA_ENUM);
        bundle.putInt(KEY_PARAM_TYPE, PARAM_RET_INT);
        bundle.putInt(KEY_PARAM, value);
        bundle.putBoolean(KEY_IS_PLUGIN, true);
        executeCmd(SkyConfigDefs.SKY_CFG_TV_DISPLAY_MODE_ROTATION, bundle, true);
        SkySSSLog.i(TAG, "setDisplayModeRotation value=" + value);
    }

    /**
     * 获取屏幕显示方向
     *
     * @return {@link SysConst#SKY_CFG_TV_DISPLAY_MODE_ROTATION_LEVEL},
     * {@link SysConst#SKY_CFG_TV_DISPLAY_MODE_ROTATION_CLOCKWISE},
     * {@link SysConst#SKY_CFG_TV_DISPLAY_MODE_ROTATION_ANTI_CLOCKWISE}
     */
    public int getDisplayModeRotation() {
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_RET_TYPE, PARAM_RET_INT);
        bundle.putBoolean(KEY_IS_PLUGIN, true);
        Bundle ret = executeCmd(SkyConfigDefs.SKY_CFG_TV_DISPLAY_MODE_ROTATION, bundle, false);
        int result = ret.getInt(KEY_RESULT);
        SkySSSLog.i(TAG, "getDisplayModeRotation result=" + result);
        return result;
    }

    /**
     * Setting在设置声音模式后，调用此方法解静音
     */
    public void setSoundModeShouldCall() {
        Bundle bundle = new Bundle();
        bundle.putBoolean(KEY_IS_PLUGIN, true);
        executeCmd(SkyConfigDefs.SKY_CFG_TV_SOUND_MODE, bundle, true);
        SkySSSLog.i(TAG, "setSoundModeShouldCall");
    }

    /**
     * 设置快速演示模式开关
     *
     * @param value true开；false关
     */
    public void setQuickDemoMode(boolean value) {
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_PARAM_TYPE, PARAM_RET_BOOL);
        bundle.putInt(KEY_TC_TYPE, TC_DATA_SWITCH);
        bundle.putBoolean(KEY_PARAM, value);
        executeCmd(TCEnvKey.SKY_SYSTEM_ENV_QUICK_DEMO_MODE, bundle, true);
        SkySSSLog.i(TAG, "setQuickDemoMode value=" + value);
    }

    /**
     * 快速演示模式开关
     *
     * @return true开；false关
     */
    public boolean getQuickDemoMode() {
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_RET_TYPE, PARAM_RET_BOOL);
        Bundle ret = executeCmd(TCEnvKey.SKY_SYSTEM_ENV_QUICK_DEMO_MODE, bundle, false);
        boolean result = ret.getBoolean(KEY_RESULT);
        SkySSSLog.i(TAG, "getQuickDemoMode result=" + result);
        return result;
    }

    /**
     * 设置品保时间选项
     *
     * @param value {@link SysConst#ONE_MIN},{@link SysConst#TWO_MIN},
     *              {@link SysConst#FIVE_MIN},{@link SysConst#TEN_MIN},
     *              {@link SysConst#THIRTY_MIN},{@link SysConst#NONE}
     */
    public void setSaveScreenTime(int value) {
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_PARAM_TYPE, PARAM_RET_INT);
        bundle.putInt(KEY_TC_TYPE, TC_DATA_ENUM);
        bundle.putInt(KEY_PARAM, value);
        executeCmd(TCEnvKey.SKY_SYSTEM_ENV_START_SCREEN_SAVER_MODE, bundle, true);
        SkySSSLog.i(TAG, "setSaveScreenTime value=" + value);
    }

    /**
     * 获取品保时间选项
     *
     * @return {@link SysConst#ONE_MIN},{@link SysConst#TWO_MIN},
     * {@link SysConst#FIVE_MIN},{@link SysConst#TEN_MIN},
     * {@link SysConst#THIRTY_MIN},{@link SysConst#NONE}
     */
    public int getSaveScreenTime() {
        Bundle param = new Bundle();
        param.putInt(KEY_RET_TYPE, PARAM_RET_INT);
        Bundle ret = executeCmd(TCEnvKey.SKY_SYSTEM_ENV_START_SCREEN_SAVER_MODE, param, false);
        int result = ret.getInt(KEY_RESULT);
        SkySSSLog.d(TAG, "getSaveScreenTime result=" + result);
        return result;
    }


    /**
     * 设置壁纸模式下自动休眠时间
     * @param index
     * @see com.tianci.system.define.SkyConfigDefs.SKY_CFG_TV_WALLPAPER_MODE_AUTO_SLEEP_TIME_ENUM
     */
    public void setWallpaperModeAutoSleepTime(int index){
        TCEnumSetData data = new TCEnumSetData();
        data.setCurrentIndex(index);
        data.setName(SkyConfigDefs.SKY_CFG_TV_WALLPAPER_MODE_AUTO_SLEEP_TIME);
        setData(data);
    }

    /**
     * 获取壁纸模式下自动休眠时间
     * @return
     * @see com.tianci.system.define.SkyConfigDefs.SKY_CFG_TV_WALLPAPER_MODE_AUTO_SLEEP_TIME_ENUM
     */
    public int getWallpaperModeAutoSleepTime(){
        TCEnumSetData data = (TCEnumSetData) TCSettingApi.getData(SkyConfigDefs.SKY_CFG_TV_WALLPAPER_MODE_AUTO_SLEEP_TIME);
        return data.getCurrentIndex();
    }


    /**
     * 设置互动消息开关
     *
     * @param value true开；false关
     */
    public void setInteractionSwitch(boolean value) {
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_PARAM_TYPE, PARAM_RET_BOOL);
        bundle.putInt(KEY_TC_TYPE, TC_DATA_SWITCH);
        bundle.putBoolean(KEY_PARAM, value);
        executeCmd(TCEnvKey.SKY_SYSTEM_ENV_INTERACTION_SWITCH, bundle, true);
        SkySSSLog.i(TAG, "setInteractionSwitch value=" + value);
    }

    /**
     * 获取互动消息开关
     *
     * @return true开；false关
     */
    public boolean getInteractionSwitch() {
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_RET_TYPE, PARAM_RET_BOOL);
        Bundle ret = executeCmd(TCEnvKey.SKY_SYSTEM_ENV_INTERACTION_SWITCH, bundle, false);
        boolean result = ret.getBoolean(KEY_RESULT);
        SkySSSLog.i(TAG, "getInteractionSwitch result=" + result);
        return result;
    }

    /**
     * 设置快捷按键禁止用开关
     *
     * @param value true开；false关
     */
    public void setSettingDisable(boolean value) {
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_PARAM_TYPE, PARAM_RET_BOOL);
        bundle.putInt(KEY_TC_TYPE, TC_DATA_SWITCH);
        bundle.putBoolean(KEY_PARAM, value);
        executeCmd(TCEnvKey.SKY_SYSTEM_ENV_SETTING_DISABLE, bundle, true);
        SkySSSLog.i(TAG, "setSettingDisable value=" + value);
    }

    /**
     * 获取设置快捷按键禁止用开关
     *
     * @return true开；false关
     */
    public boolean getSettingDisable() {
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_RET_TYPE, PARAM_RET_BOOL);
        Bundle ret = executeCmd(TCEnvKey.SKY_SYSTEM_ENV_SETTING_DISABLE, bundle, false);
        boolean result = ret.getBoolean(KEY_RESULT);
        SkySSSLog.i(TAG, "getSettingDisable result=" + result);
        return result;
    }


    /**
     * 设置是否在wifi列表界面
     *
     * @param isOn false：不在wifi列表界面；true：在
     */
    public void setWifiViewFlag(boolean isOn) {
        Bundle bundle = new Bundle();
        Bundle param = new Bundle();
        param.putBoolean(KEY_PARAM, isOn);
        ApiUtil.setParams(bundle, SystemProviderDefines.METHOD_COMMON,
                SystemProviderDefines.COMMON_WIFI_VIEW_FLAG, param, true);
        boolean ret = ApiUtil.setData(bundle);
        SkySSSLog.i(TAG, "setWifiViewFlag ret=" + ret);
    }

    /**
     * tv当天使用时间
     */
    public int[] getTVUseTimeDay() {
        Bundle bundle = new Bundle();
        Bundle param = new Bundle();
        ApiUtil.setParams(bundle, SystemProviderDefines.METHOD_COMMON,
                SystemProviderDefines.COMMON_TV_USE_TIME_DAY, param, false);
        int[] ret = ApiUtil.getData(bundle, int[].class);
        SkySSSLog.i(TAG, "getTVUseTimeDay ret=" + ret);
        return ret;
    }

    /**
     * tv7天使用时间
     */
    public int[] getTVUseTimeWeek() {
        Bundle bundle = new Bundle();
        Bundle param = new Bundle();
        ApiUtil.setParams(bundle, SystemProviderDefines.METHOD_COMMON,
                SystemProviderDefines.COMMON_TV_USE_TIME_WEEK, param, false);
        int[] ret = ApiUtil.getData(bundle, int[].class);
        SkySSSLog.i(TAG, "getTVUseTimeWeek ret=" + ret);
        return ret;
    }

    /**
     * @param filePath 文件路径
     *                 3d lut  SDR
     */
    public void setSDRLUT(String filePath) {
        Bundle bundle = new Bundle();
        Bundle param = new Bundle();
        param.putString(KEY_PARAM, filePath);
        ApiUtil.setParams(bundle, SystemProviderDefines.METHOD_COMMON,
                SystemProviderDefines.COMMON_SDR_LUT_INPUT, param, true);
        boolean ret = ApiUtil.setData(bundle);
        SkySSSLog.i(TAG, "setLUT ret=" + ret);

    }

    /**
     * @param filePath 文件路径
     *                 3d lut  HDR
     */
    public void setHDRLUT(String filePath) {
        Bundle bundle = new Bundle();
        Bundle param = new Bundle();
        param.putString(KEY_PARAM, filePath);
        ApiUtil.setParams(bundle, SystemProviderDefines.METHOD_COMMON,
                SystemProviderDefines.COMMON_HDR_LUT_INPUT, param, true);
        boolean ret = ApiUtil.setData(bundle);
        SkySSSLog.i(TAG, "setLUT ret=" + ret);
    }

    /**
     * @param filePath 文件路径
     *                 1D lut
     */
    public void setOneLUT(String filePath) {
        Bundle bundle = new Bundle();
        Bundle param = new Bundle();
        param.putString(KEY_PARAM, filePath);
        ApiUtil.setParams(bundle, SystemProviderDefines.METHOD_COMMON,
                SystemProviderDefines.COMMON_ONE_LUT_INPUT, param, true);
        boolean ret = ApiUtil.setData(bundle);
        SkySSSLog.i(TAG, "setLUT ret=" + ret);
    }

    /**
     * 3d lut  HDR
     */
    public boolean isLUTInputNeedLoading() {
        Bundle bundle = new Bundle();
        Bundle ret = ApiUtil.executeCommonCmd(SystemProviderDefines.COMMON_HDR_LUT_INPUT_LOADING, bundle);
        boolean result = ret.getBoolean(KEY_RESULT);
        SkySSSLog.i(TAG, "isHDRLUTInputNeedLoading result=" + result);
        return result;
    }

    /**
     * @param fileInputObserver 文件导入回调
     *                          return true：设置回调成功  false：不需要回调
     *                          3d lut  HDR
     */
    public boolean setHDRLUTListener(CommonObserver fileInputObserver) {
        ApiUtil.registerCallback(SystemProviderDefines.COMMON_HDR_LUT_INPUT, fileInputObserver);
        return true;
    }

    /**
     * @param fileInputObserver 文件导入回调
     *                          return true：设置回调成功  false：不需要回调
     *                          3d lut  SDR
     */
    public boolean setSDRLUTListener(CommonObserver fileInputObserver) {
        ApiUtil.registerCallback(SystemProviderDefines.COMMON_SDR_LUT_INPUT, fileInputObserver);
        return true;
    }

    /**
     * @param fileInputObserver 文件导入回调
     *                          return true：设置回调成功  false：不需要回调
     *                          3d lut  SDR
     */
    public boolean setONELUTListener(CommonObserver fileInputObserver) {
        ApiUtil.registerCallback(SystemProviderDefines.COMMON_ONE_LUT_INPUT, fileInputObserver);
        return true;
    }

    /**
     * 设置画面缩放
     *
     * @param mode 0：缩小；1：放大
     */
    public void setPictureZoom(int mode) {
        SkySSSLog.i(TAG, "setPictureZoom mode=" + mode);
        TCInfoSetData data = new TCInfoSetData();
        data.setName(SkyConfigDefs.SKY_CFG_TV_PICTURE_ZOOM);
        data.setCurrent(mode + "");
        setData(data);
    }

    /**
     * 获取当前画面缩放值
     *
     * @return 当前画面缩放值
     */
    public int getPictureZoom() {
        int ret = 0;
        try {
            TCInfoSetData data = (TCInfoSetData) getData(SkyConfigDefs.SKY_CFG_TV_PICTURE_ZOOM);
            if (data != null)
                ret = Integer.parseInt(data.getCurrent());
        } catch (Exception e) {
            SkySSSLog.e(TAG, "getPictureZoom e=" + e.getMessage());
        }
        SkySSSLog.i(TAG, "getPictureZoom ret=" + ret);
        return ret;
    }

    public void setProjectionCorrection() {
        SkySSSLog.i(TAG, "setProjectionCorrection ");
        Bundle bundle = new Bundle();
        ApiUtil.setParams(bundle, SystemProviderDefines.METHOD_COMMON,
                SystemProviderDefines.SET_PROJECTION_CORRECTION, null, false);
        boolean ret = ApiUtil.setData(bundle);
        SkySSSLog.i(TAG, "setProjectionCorrection ret=" + ret);
    }

    /**
     * 投影放大缩小
     *
     * @param scale 0:放大  1:缩小
     */
    public void setProjectionScale(int scale) {
        SkySSSLog.i(TAG, "setProjectionScale ");
        Bundle bundle = new Bundle();
        Bundle param = new Bundle();
        param.putInt(KEY_PARAM, scale);
        ApiUtil.setParams(bundle, SystemProviderDefines.METHOD_COMMON,
                SystemProviderDefines.SET_PROJECTION_SCALE, param, true);
        boolean ret = ApiUtil.setData(bundle);
        SkySSSLog.i(TAG, "setProjectionScale ret=" + ret);
    }

    /**
     * @param slideLine 0:左边线  1:上边线  2:右边线  3:下边线
     * @param move      0:向外移  1:向内移
     */
    public void setProjectionSlideLine(int slideLine, int move) {
        SkySSSLog.i(TAG, "setProjectionSlideLine " + slideLine + "_" + move);
        Bundle bundle = new Bundle();
        Bundle param = new Bundle();
        param.putInt("line", slideLine);
        param.putInt("direction", move);
        ApiUtil.setParams(bundle, SystemProviderDefines.METHOD_COMMON,
                SystemProviderDefines.SET_PROJECTION_SLIDE_LINE, param, true);
        boolean ret = ApiUtil.setData(bundle);
        SkySSSLog.i(TAG, "setProjectionSlideLine ret=" + ret);
    }

    /**
     * 5.8重低音环绕音音响 重低音状态
     *
     * @return
     */
    public boolean getG58BassState() {
//        Bundle bundle = new Bundle();
        Bundle ret = ApiUtil.executeCommonCmd(SystemProviderDefines.COMMON_G58_BASS_STATE, null);
        boolean result = ret.getBoolean(KEY_RESULT);
        SkySSSLog.i(TAG, "getG58BassState result=" + result);
        return result;
    }

    /**
     * 5.8重低音环绕音音响 重低音状态
     *
     * @return
     */
    public boolean getG58AroundState() {
//        Bundle bundle = new Bundle();
        Bundle ret = ApiUtil.executeCommonCmd(SystemProviderDefines.COMMON_G58_AROUND_STATE, null);
        boolean result = ret.getBoolean(KEY_RESULT);
        SkySSSLog.i(TAG, "getG58AroundState result=" + result);
        return result;
    }

    /**
     * 设置OELD屏修复(OledDrv组件新接口)
     *
     * @param repairType：修复类型，OffRs：0;         JB：1
     * @param actionAfterRepair：开屏：0，关机：1，重启：2
     */
    public void setOledRepair(int repairType, int actionAfterRepair) {
        SkySSSLog.i(TAG, "setOledRepair repairType:" + repairType + " actionAfterRepair:" + actionAfterRepair);
        Bundle bundle = new Bundle();
        Bundle param = new Bundle();
        param.putInt("repairType", repairType);
        param.putInt("actAfterRepair", actionAfterRepair);
        ApiUtil.setParams(bundle, SystemProviderDefines.METHOD_COMMON,
                SystemProviderDefines.SET_OLED_REPAIR, param, true);
        boolean ret = ApiUtil.setData(bundle);
        SkySSSLog.i(TAG, "setOledRepair ret=" + ret);
    }

}