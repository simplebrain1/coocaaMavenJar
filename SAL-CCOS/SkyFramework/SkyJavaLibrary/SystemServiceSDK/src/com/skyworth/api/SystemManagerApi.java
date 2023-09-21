package com.skyworth.api;

import android.content.Context;

import com.skyworth.api.utils.LogApi;
import com.skyworth.framework.data.ExternDiskInfo;
import com.skyworth.framework.data.Mode;
import com.skyworth.framework.define.SystemDefines;
import com.skyworth.framework.impl.BootPathSystemApiImpl;
import com.skyworth.framework.impl.IPCServiceSystemApiImpl;
import com.skyworth.framework.impl.ISystemApi;
import com.skyworth.framework.skysdk.android.SkySystemUtil;
import com.skyworth.framework.skysdk.ipc.SkyApplication;
import com.tianci.system.utils.ApiUtil;

import java.util.List;

/**
 * 兼容版本系统扩展接口
 */
public class SystemManagerApi {
    private final static String TAG = "SystemManagerApi";
    private ISystemApi mSystemApiImpl;

    public SystemManagerApi(Context context) {
        boolean isBootPathImpl = ApiUtil.isNewPlatform();
        LogApi.d(TAG, "isBootPathImpl:" + isBootPathImpl);
        Context ctx = context.getApplicationContext();
        if (ctx == null) {
            ctx = context;
        }
        if (isBootPathImpl) {
            mSystemApiImpl = new BootPathSystemApiImpl(ctx);
        } else {
            mSystemApiImpl = new IPCServiceSystemApiImpl(ctx, SkyApplication.getListener());
        }
    }

    /**
     * 获取屏体修复状态
     *
     * @return {@link SystemDefines#SCREEN_STATE_UNKNOWN}, {@link SystemDefines#SCRENN_STATE_OFF_AND_FIXING},
     * {@link SystemDefines#SCRENN_STATE_OFF_AND_FIXED}, {@link SystemDefines#SCRENN_STATE_ON_AND_FIXED}
     */
    public int getOledScreenFixState() {
        return mSystemApiImpl.getOledScreenFixState();
    }

    /**
     * 获取酷开系统版本
     *
     * @return 系统版本字符串
     */
    public String getSystemVersion() {
        return mSystemApiImpl.getSystemVersion();
    }

    /**
     * 获取系统编译版本
     *
     * @return 编译版本字符串
     */
    public String getBuildVersion() {
        return mSystemApiImpl.getBuildVersion();
    }

    /**
     * 获取机型<br/>
     *
     * @return String
     */
    public String getDeviceType() {
        return mSystemApiImpl.getDeviceType();
    }

    /**
     * 获取机芯<br/>
     *
     * @return String
     */
    public String getDeviceModel() {
        return mSystemApiImpl.getDeviceModel();
    }

    /**
     * 获取芯片<br/>
     *
     * @return String
     */
    public String getChipId() {
        return mSystemApiImpl.getChipId();
    }

    /**
     * 获取Barcode
     *
     * @return barcode字符串
     */
    public String getBarcode() {
        return mSystemApiImpl.getBarcode();
    }

    /**
     * 获取MAC地址
     *
     * @return MAC字符串
     */
    public String getMacAddress() {
        return mSystemApiImpl.getMacAddress();
    }

    /**
     * 获取emmc id
     *
     * @return emmc id 字符串
     */
    public String getEmmcId() {
        return mSystemApiImpl.getEmmcId();
    }

    /**
     * 设置Logo灯状态
     *
     * @param mode {@link SystemDefines#LOGO_VOICE_OFF}, {@link SystemDefines#LOGO_VOICE_ON},
     *             {@link SystemDefines#LOGO_REVEIVE_IR},
     *             {@link SystemDefines#LOGO_POWER_ON}, {@link SystemDefines#LOGO_POWER_OFF},
     *             {@link SystemDefines#LOGO_AI_POWERON}, {@link SystemDefines#LOGO_AI_POWEROFF}
     * @param arg
     */
    public void setVoiceLogoControl(int mode, int arg) {
        mSystemApiImpl.setVoiceLogoControl(mode, arg);
    }

    /**
     * 设置屏幕亮度（背光）
     *
     * @param backlight 屏幕亮度（背光）数值
     * @return true 成功 false 失败
     */
    public boolean setBacklight(int backlight) {
        return mSystemApiImpl.setBacklight(backlight);
    }

    /**
     * 获取屏幕亮度
     *
     * @return 屏幕亮度（背光）数值
     */
    public int getBacklight() {
        return mSystemApiImpl.getBacklight();
    }

    /**
     * 设置色彩饱和度
     *
     * @param color 色彩饱和度
     * @return true 成功 false 失败
     */

    public boolean setColor(int color) {
        return mSystemApiImpl.setColor(color);
    }

    /**
     * 获取色彩饱和度
     *
     * @return 色彩饱和度
     */
    public int getColor(){
        return mSystemApiImpl.getColor();
    }

    /**
     * 获取是否体育模式
     *
     * @return true 是 false 否
     */
    public boolean getSportMode() {
        return mSystemApiImpl.getSportMode();
    }

    /**
     * 设置体育模式
     *
     * @param mode true 是 false 否
     * @return true 成功 false 失败
     */
    public boolean setSportMode(boolean mode) {
        return mSystemApiImpl.setSportMode(mode);
    }

    /**
     * 获取当前支持的图像模式列表
     *
     * @return List<Mode> 当前支持的图像模式列表 {@link Mode}
     */
    public List<Mode> getPictureModeList() {
        return mSystemApiImpl.getPictureModeList();
    }

    /**
     * 获取当前图像模式
     *
     * @return Mode 图像模式数据结构 {@link Mode}
     */
    public Mode getPictureMode() {
        return mSystemApiImpl.getPictureMode();
    }

    /**
     * 设置当前图像模式
     *
     * @param mode 图像模式数据结构 {@link Mode}
     * @return true 成功 false 失败
     */
    public boolean setPictureMode(Mode mode) {
        return mSystemApiImpl.setPictureMode(mode);
    }

    /**
     * 获取当前支持的声音模式列表
     *
     * @return List<Mode> 当前支持的声音模式列表 {@link Mode}
     */
    public List<Mode> getSoundModeList() {
        return mSystemApiImpl.getSoundModeList();
    }

    /**
     * 获取当前声音模式
     *
     * @return Mode 声音模式数据结构 {@link Mode}
     */
    public Mode getSoundMode() {
        return mSystemApiImpl.getSoundMode();
    }

    /**
     * 设置当前声音模式
     *
     * @param mode 声音模式数据结构 {@link Mode}
     * @return true 成功 false 失败
     */
    public boolean setSoundMode(Mode mode) {
        return mSystemApiImpl.setSoundMode(mode);
    }

    /**
     * 是否支持 XDR
     * @return true:支持,false:不支持
     */
    public boolean isSupportXDR() {return mSystemApiImpl.isSupportXDR(); }

    /**
     * 获取XDR 开关状态
     * @return true打开，false关闭
     */
    public boolean getXDRMode() {return mSystemApiImpl.getXDRMode(); }

    /**
     * 设置 XDR 开关状态
     * @return true：设置成功，false：设置失败
     */
    public boolean setXDRMode(boolean mode) {return mSystemApiImpl.setXDRMode(mode); }

    /**
     * 获取演示模式状态
     *
     * @return true 开 false 关
     */
    public boolean getQuickDemoMode() {
        return mSystemApiImpl.getQuickDemoMode();
    }

    /**
     * 设置演示模式状态
     *
     * @param mode 演示模式状态 true 开 false 关
     * @return true 开 false 关
     */
    public boolean setQuickDemoMode(boolean mode) {
        return mSystemApiImpl.setQuickDemoMode(mode);
    }

    /**
     * 获取屏幕尺寸
     *
     * @return 屏幕尺寸字符串
     */
    public String getPanelSize() {
        return mSystemApiImpl.getPanelSize();
    }

    /**
     * 获取当前正在播放视频的类型
     *
     * @return {@link SystemDefines#STREAM_TYPE_UNKNOWN}, {@link SystemDefines#STREAM_TYPE_SDR},
     * {@link SystemDefines#STREAM_TYPE_HDR10}, {@link SystemDefines#STREAM_TYPE_DOLBY_HDR},
     * {@link SystemDefines#STREAM_TYPE_HLG_HDR}
     */
    public int getStreamType() {
        return mSystemApiImpl.getStreamType();
    }

    /**
     * 获取系统所属<br/>
     *
     * @return 系统所属
     */
    public String getSystemOwner() {
        return mSystemApiImpl.getSystemOwner();
    }
    /**
     * 获取是否支持系统关怀模式
     * return true 支持；false 不支持
     */
    public boolean isSupportCareMode() {
        return mSystemApiImpl.isSupportCareMode();
    }

    /**
     * 获得所挂载的外部硬盘的磁盘信息
     *
     * @return 磁盘信息列表 {@link SkySystemUtil.ExternDiskInfo}
     */
    public List<ExternDiskInfo> getExternalDisks() {
        return mSystemApiImpl.getExternalDisks();
    }

    public boolean setContentSceneMode(Mode mode){return mSystemApiImpl.setContentSceneMode(mode);}

    public Mode getContentSceneMode(){
        return mSystemApiImpl.getContentSceneMode();
    }
    public void reboot(){
        mSystemApiImpl.reboot();
    }

    /**
     * 获取的支持色温列表
     * @return
     * @see com.tianci.system.define.SkyConfigDefs.SKY_CFG_TV_COLOR_TEMPERATURE_ENUM_TYPE
     */
    public List<String> getTemperatureColorList(){
        return mSystemApiImpl.getTemperatureColorList();
    }

    /**
     * 获取当前色温
     * @return
     */
    public String getCurrentTemperatureColor(){
        return mSystemApiImpl.getCurrentTemperatureColor();
    }

    /**
     * 设置当前色温
     * @param colorMode
     * @return
     */
    public boolean setTemperatureColor(String colorMode){return mSystemApiImpl.setTemperatureColor(colorMode);}

}
