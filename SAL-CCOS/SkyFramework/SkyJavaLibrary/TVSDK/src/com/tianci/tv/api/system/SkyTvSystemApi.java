package com.tianci.tv.api.system;

import android.content.Context;
import android.os.Bundle;
import android.view.Surface;

import com.tianci.tv.define.SkyTvDefine;
import com.tianci.tv.define.SkyTvDefine.SOURCE_SIGNAL_STATE;
import com.tianci.tv.define.object.CECDeviceInfo;
import com.tianci.tv.define.object.CaCardInfo;
import com.tianci.tv.define.object.CaEntitle;
import com.tianci.tv.define.object.CaOperator;
import com.tianci.tv.define.object.Channel;
import com.tianci.tv.define.object.ChannelInfo;
import com.tianci.tv.define.object.Source;
import com.tianci.tv.define.object.SourceInfo;
import com.tianci.tv.framework.api.SkyTvApi;
import com.tianci.tv.framework.implement.system.ContentProviderSystemAPIImpl;
import com.tianci.tv.framework.implement.system.IPCServiceSystemAPIImpl;
import com.tianci.tv.framework.implement.system.SystemAPI;
import com.tianci.tv.framework.implement.system.listener.OnCACardInfoListener;
import com.tianci.tv.framework.implement.system.listener.OnCECDeviceChangedListener;
import com.tianci.tv.utils.AddonTextUtils;
import com.tianci.tv.utils.SkyTvUtils;
import com.tianci.tv.utils.TVSDKDebug;

import java.util.List;

/**
 * <p>
 * Description:
 * </p>
 * <p>
 * TV系统API
 * </p>
 *
 * @author xiangge
 * @version V*.*.*
 * @ClassName SkyTvSystemApi
 * @date 2013-11-20
 */
public class SkyTvSystemApi extends SkyTvApi {
    private SystemAPI mSystemAPIImpl;

    public SkyTvSystemApi(Context context) {
        super(context);
        boolean isTianciTvAppInstalled = SkyTvUtils.isAppInstalled(context, "com.tianci.tv");
        boolean isTcCoocaaOSUpe6_50 = SkyTvUtils.isTcCoocaaOSUpe6_50();
        TVSDKDebug.info("SkyTvSettingApi isTianciTvAppInstalled：" + isTianciTvAppInstalled+"  isTcCoocaaOSUpe6_50:"+isTcCoocaaOSUpe6_50);
        Context ctx = context.getApplicationContext();
        if (ctx == null) {
            ctx = context;
        }
        if (isTianciTvAppInstalled) {
            if(!isTcCoocaaOSUpe6_50){
                mSystemAPIImpl = new IPCServiceSystemAPIImpl(listener);
            }else
                {
                mSystemAPIImpl = new ContentProviderSystemAPIImpl(ctx, isTianciTvAppInstalled);
            }
        } else {
            mSystemAPIImpl = new ContentProviderSystemAPIImpl(ctx, isTianciTvAppInstalled);
        }

        AddonTextUtils.getInstance(context);
    }

    /**
     * 概述：获取TV接口版本<br/>
     * 适用条件：<br/>
     * 执行流程：<br/>
     * 使用方法：<br/>
     * 注意事项：<br/>
     *
     * @param
     * @return int
     * @date 2018-07-18
     */
    public int getApiLevel() {
        if (mSystemAPIImpl == null) {
            return 0;
        }
        return mSystemAPIImpl.getApiLevel();
    }

    /**
     * 概述：换到指定频道<br/>
     * 适用条件：<br/>
     * 执行流程：<br/>
     * 使用方法：<br/>
     * 注意事项：<br/>
     *
     * @param channelInfo
     * @return int
     * @date 2017-11-10
     */
    public int switchChannel(ChannelInfo channelInfo) {
        if (mSystemAPIImpl == null) {
            return SkyTvDefine.SWITCH_CHANNEL_EXCEPTION;
        }
        return mSystemAPIImpl.switchChannel(channelInfo);
    }

    /**
     * 概述：注册CA卡的状态等信息变化的监听器<br/>
     * 适用条件：<br/>
     * 执行流程：<br/>
     * 使用方法：<br/>
     * 注意事项：<br/>
     *
     * @return boolean
     * @date 2017-11-28
     */
    public boolean setOnCaCardInfoListener(OnCACardInfoListener listener) {
        if (mSystemAPIImpl == null) {
            return false;
        }
        return mSystemAPIImpl.setOnCaCardInfoListener(listener);
    }

    /**
     * 概述：获取CA卡的状态等信息<br/>
     * 适用条件：<br/>
     * 执行流程：<br/>
     * 使用方法：<br/>
     * 注意事项：<br/>
     *
     * @return CaCardInfo
     * @date 2017-11-28
     */
    public CaCardInfo getCaCardInfo() {
        if (mSystemAPIImpl == null) {
            return null;
        }
        return mSystemAPIImpl.getCaCardInfo();
    }

    /**
     * 概述：切换通道<br/>
     * 适用条件：<br/>
     * 执行流程：<br/>
     * 使用方法：<br/>
     * 注意事项：<br/>
     *
     * @param source 由Source产生指定的通道对象，或者从getSourceList()返回的list中选择一个
     * @return boolean true:成功 false:失败
     * @date 2013-11-20
     */
    public boolean switchSource(Source source) {
        if (mSystemAPIImpl == null) {
            return false;
        }
        return mSystemAPIImpl.switchSource(source);
    }

    /**
     * 概述：切换通道，并且不起ui<br/>
     * 适用条件：<br/>
     * 执行流程：<br/>
     * 使用方法：<br/>
     * 注意事项：供工厂使用<br/>
     *
     * @return boolean
     * @date 2015-11-18
     */
    public boolean switchSourceBackGround(Context context, Source source) {
        if (mSystemAPIImpl == null) {
            return false;
        }
        return mSystemAPIImpl.switchSourceBackGround(source);
    }

    /**
     * 概述：切换通道<br/>
     * 适用条件：非TV应用下需要使用通道时<br/>
     * 执行流程：<br/>
     * 使用方法：<br/>
     * 注意事项：<br/>
     *
     * @param sourceInfo
     * @return boolean
     * @date 2017-11-10
     */
    public boolean holdSource(SourceInfo sourceInfo, ChannelInfo channelInfo, Surface surface) {
        return holdSource(sourceInfo, channelInfo, surface, -1, -1, -1, -1, 0);
    }

    /***
     * 概述：切换通道并设置窗口尺寸
     * 适用条件：非TV应用下需要使用通道时
     * @param sourceInfo
     * @param channelInfo
     * @param surface
     * @param x 默认0 不设置窗口尺寸传-1
     * @param y 默认0 不设置窗口尺寸传-1
     * @param w -1
     * @param h -1
     * @param flag 默认：0， 不需要保存通道：SkyTvDefine.HOLD_SOURCE_FLAG_DONT_SAVE
     * @return
     */
    public boolean holdSource(SourceInfo sourceInfo, ChannelInfo channelInfo, Surface surface,
                              int x, int y, int w, int h, int flag) {
        if (mSystemAPIImpl == null) {
            return false;
        }
        return mSystemAPIImpl.holdSource(sourceInfo, channelInfo, surface, x, y, w, h, flag);
    }

    /**
     * 概述：释放通道<br/>
     * 适用条件：非TV应用退出应用时<br/>
     * 执行流程：<br/>
     * 使用方法：<br/>
     * 注意事项：<br/>
     *
     * @param sourceInfo
     * @return boolean
     * @date 2017-11-10
     */
    public boolean releaseSource(SourceInfo sourceInfo) {
        if (mSystemAPIImpl == null) {
            return false;
        }
        return mSystemAPIImpl.releaseSource(sourceInfo);
    }

    /**
     * 概述：释放通道<br/>
     * 适用条件：非TV应用要使用多媒体通道资源时<br/>
     * 执行流程：<br/>
     * 使用方法：<br/>
     * 注意事项：<br/>
     *
     * @param
     * @return boolean
     * @date 2018-07-18
     */
    public boolean forceReleaseSource() {
        if (mSystemAPIImpl == null) {
            return false;
        }
        return mSystemAPIImpl.forceReleaseSource();
    }

    /**
     * 概述：获取当前通道<br/>
     * 适用条件：<br/>
     * 执行流程：<br/>
     * 使用方法：<br/>
     * 注意事项：<br/>
     *
     * @return Source
     * @date 2013-11-20
     */
    public Source getCurrentSource() {
        if (mSystemAPIImpl == null) {
            return null;
        }
        return mSystemAPIImpl.getCurrentSource();
    }

    /**
     * 概述：获取当前实际的通道<br/>
     * 适用条件：<br/>
     * 执行流程：<br/>
     * 使用方法：<br/>
     * 注意事项：<br/>
     *
     * @return SourceInfo
     * @date 2018-11-14
     */
    public SourceInfo getRealSource() {
        if (mSystemAPIImpl == null) {
            return null;
        }
        if (SkyTvUtils.isStartTV()){
            return mSystemAPIImpl.getRealSource();
        }else{
            return null;
        }
    }

    /**
     * 概述：获取系统内的通道列表，如果无则返回size是0的list<br/>
     * 适用条件：<br/>
     * 执行流程：<br/>
     * 使用方法：<br/>
     * 注意事项：<br/>
     *
     * @return List<Source>
     * @date 2013-11-20
     */
    @SuppressWarnings("unchecked")
    public List<Source> getSourceList() {
        if (mSystemAPIImpl == null) {
            return null;
        }
        return mSystemAPIImpl.getSourceList();
    }

    public String getSourceResolution(Source source){
        if (mSystemAPIImpl == null) {
            return null;
        }
        return mSystemAPIImpl.getSourceResolution(source);
    }
    public boolean isReady() {
        if (mSystemAPIImpl == null) {
            return false;
        }
        return mSystemAPIImpl.isReady();
    }

    /**
     * 概述：获取tv是否处于被释放状态<br/>
     * 适用条件：<br/>
     * 执行流程：<br/>
     * 使用方法：<br/>
     * 注意事项：<br/>
     *
     * @return boolean
     * @date 2013-11-20
     */
    public boolean isReleased() {
        if (mSystemAPIImpl == null) {
            return false;
        }
        return mSystemAPIImpl.isReleased();
    }

    /**
     * 概述：把tv 拉到最上面，根据当前通道和平台配置决定是否需要释放tv通道的资源<br/>
     * 适用条件：快速待机执行序列中的一个步骤<br/>
     * 执行流程：<br/>
     * 使用方法：<br/>
     * 注意事项：仅限快速开机执行时调用<br/>
     *
     * @return boolean
     * @date 2015-07-29
     */
    public boolean releaseAllWithStandby() {
        if (mSystemAPIImpl == null) {
            return false;
        }
        return mSystemAPIImpl.releaseAllWithStandby();
    }

    /**
     * 概述：快速开机中返回TV<br/>
     * 适用条件：快速开机执行序列中的一个步骤<br/>
     * 执行流程：<br/>
     * 使用方法：<br/>
     * 注意事项：仅限快速开机执行时调用<br/>
     *
     * @return boolean
     * @date 2014-07-30
     */
    public boolean backToTv() {
        if (mSystemAPIImpl == null) {
            return false;
        }
        return mSystemAPIImpl.backToTv();
    }

    /**
     * 概述：返回TV<br/>
     * 适用条件：<br/>
     * 执行流程：<br/>
     * 使用方法：<br/>
     * 注意事项：供任务管理器使用<br/>
     *
     * @return boolean
     * @date 2015-11-18
     */
    public boolean backToTvSource(Context context) {
        if (mSystemAPIImpl == null) {
            return false;
        }
        return mSystemAPIImpl.backToTvSource();
    }

    /**
     * 概述：当前是否可以截屏<br/>
     * 适用条件：<br/>
     * 执行流程：<br/>
     * 使用方法：<br/>
     * 注意事项：当返回可以截屏时，这时通道的视频正在播放，但是视频上面也许会有菜单或者透明的应用。
     * 所以，该接口只适用于那些可以只截取视频层的平台。否则会把当时菜单也截取进来，如果当时有的话<br/>
     *
     * @return boolean
     * true: 可以截屏      false ：不能截屏
     * @date 2015-4-15
     */
    public boolean canCaptureScreen() {
        if (mSystemAPIImpl == null) {
            return false;
        }
        return mSystemAPIImpl.canCaptureScreen();
    }

    /**
     * 概述：设置以后是否需要显示开机引导<br/>
     * 适用条件：<br/>
     * 执行流程：<br/>
     * 使用方法：<br/>
     * 注意事项：仅限很早的coocaa 系统使用，其他coocaa 系统不能使用<br/>
     *
     * @return boolean
     * true: 需要显示      false ：不需要显示
     * @date 2015-3-15
     */
    @Deprecated
    public boolean setNeedShowBootGuide(boolean setValue) {
        if (mSystemAPIImpl == null) {
            return false;
        }
        return mSystemAPIImpl.setNeedShowBootGuide(setValue);
    }

    public int setTimeUpdateFlag(int flag) {
        if (mSystemAPIImpl == null) {
            return 0;
        }
        return mSystemAPIImpl.setTimeUpdateFlag(flag);
    }

    /**
     * 概述：当前通道的视频上是否没有UI<br/>
     * 适用条件：<br/>
     * 执行流程：<br/>
     * 使用方法：<br/>
     * 注意事项：<br/>
     *
     * @return boolean
     * true: 当前通道的视频上没有UI      false ：当前通道的视频上有UI
     * @date 2015-4-15
     */
    public boolean isTvVideoFocused() {
        if (mSystemAPIImpl == null) {
            return false;
        }
        return mSystemAPIImpl.isTvVideoFocused();
    }

    /**
     * 概述：获取指定source的信号状态<br/>
     * 适用条件：<br/>
     * 执行流程：<br/>
     * 使用方法：<br/>
     * 注意事项：<br/>
     *
     * @return SOURCE_SIGNAL_STATE 信号状态
     * @in Source source ：指定的source
     * @date 2014-3-15
     */
    public SOURCE_SIGNAL_STATE signalState(Source source) {
        if (mSystemAPIImpl == null) {
            return SOURCE_SIGNAL_STATE.NOSIGNAL;
        }
        return mSystemAPIImpl.signalState(source);
    }

    /**
     * 概述：当前是否可以弹出互动平台的卡片<br/>
     * 适用条件：<br/>
     * 执行流程：<br/>
     * 使用方法：<br/>
     * 注意事项：<br/>
     *
     * @return boolean
     * true: 可以弹出      false ：不能弹出
     * @date 2015-4-15
     */
    public boolean canPopupInteractionNow() {
        if (mSystemAPIImpl == null) {
            return false;
        }
        return mSystemAPIImpl.canPopupInteractionNow();
    }

    /**
     * 概述：互动平台是否使能<br/>
     * 适用条件：<br/>
     * 执行流程：<br/>
     * 使用方法：<br/>
     * 注意事项：互动平台的总开关<br/>
     *
     * @return boolean
     * true: 可是使用互动平台      false ：不能使用互动。
     * @date 2015-4-15
     */
    public boolean isInteractionEnable() {
        if (mSystemAPIImpl == null) {
            return false;
        }
        return mSystemAPIImpl.isInteractionEnable();
    }
    //=====================================================================================================//

    /**
     * 概述：注册tv状态变化的监听<br/>
     * 适用条件：<br/>
     * 执行流程：<br/>
     * 使用方法：<br/>
     * 注意事项：<br/>
     *
     * @return boolean
     * true: 注册成功      false ：注册失败
     * @date 2015-4-15
     */
    public boolean registerSystemListener() {
        if (mSystemAPIImpl == null) {
            return false;
        }
        return mSystemAPIImpl.registerSystemListener();
    }

    /**
     * 概述：反注册tv状态变化的监听<br/>
     * 适用条件：<br/>
     * 执行流程：<br/>
     * 使用方法：<br/>
     * 注意事项：<br/>
     *
     * @return boolean
     * true: 反注册成功      false ：反注册失败
     * @date 2015-4-15
     */

    public boolean unregisterSystemListener() {
        if (mSystemAPIImpl == null) {
            return false;
        }
        return mSystemAPIImpl.unregisterSystemListener();
    }

    /**
     * 概述：恢复预置频点<br/>
     * 适用条件：<br/>
     * 执行流程：<br/>
     * 使用方法：<br/>
     * 注意事项：供工厂使用<br/>
     *
     * @return boolean
     * @date 2016-06-13
     */
    public boolean restorePresetChannel(Context context) {
        if (mSystemAPIImpl == null) {
            return false;
        }
        return mSystemAPIImpl.restorePresetChannel(context);
    }

    @SuppressWarnings("unchecked")
    public List<Source> getExternalSourceList() {
        if (mSystemAPIImpl == null) {
            return null;
        }
        return mSystemAPIImpl.getExternalSourceList();
    }

    /**
     * 概述：使能或者禁用通道<br/>
     * 适用条件：<br/>
     * 执行流程：<br/>
     * 使用方法：<br/>
     * 注意事项：<br/>
     *
     * @param source -- 目标通道
     *         enable -- true: 使能      false ：禁用
     * @return boolean -- true: 成功      false ：失败
     * @date
     */
    public boolean enableExternalSource(Source source, boolean val) {
        if (mSystemAPIImpl == null) {
            return false;
        }
        return mSystemAPIImpl.enableExternalSource(source, val);
    }

    public boolean getExternalSourceEnable(Source source) {
        if (mSystemAPIImpl == null) {
            return false;
        }
        return mSystemAPIImpl.getExternalSourceEnable(source);
    }

    /**
     * 概述：切换到上一个通道<br/>
     * 适用条件：<br/>
     * 执行流程：<br/>
     * 使用方法：目前工厂里面有用该方法<br/>
     * 注意事项：<br/>
     *
     * @return boolean
     * @date 2013-11-20
     */
    public boolean sourceUp() {
        if (mSystemAPIImpl == null) {
            return false;
        }
        return mSystemAPIImpl.sourceUp();
    }

    /**
     * 概述：切换到下一个通道<br/>
     * 适用条件：<br/>
     * 执行流程：<br/>
     * 使用方法：目前工厂里面有用该方法<br/>
     * 注意事项：<br/>
     *
     * @return boolean
     * @date 2013-11-20
     */
    public boolean sourceDown() {
        if (mSystemAPIImpl == null) {
            return false;
        }
        return mSystemAPIImpl.sourceDown();
    }

    /**
     * 概述：换到指定频道<br/>
     * 适用条件：<br/>
     * 执行流程：<br/>
     * 使用方法：<br/>
     * 注意事项：<br/>
     *
     * @param channel
     * @return boolean
     * @date 2013-11-20
     */
    public boolean switchChannel(Channel channel) {
        if (mSystemAPIImpl == null) {
            return false;
        }
        return mSystemAPIImpl.switchChannel(channel);
    }

    /**
     * 概述：获取当前直播的频道，如果处于release状态，则返回进入release状态前的直播频道<br/>
     * 适用条件：<br/>
     * 执行流程：<br/>
     * 使用方法：<br/>
     * 注意事项：<br/>
     *
     * @return Channel
     * @date 2013-11-20
     */
    public Channel getCurrentChannel() {
        if (mSystemAPIImpl == null) {
            return null;
        }
        return mSystemAPIImpl.getCurrentChannel();
    }

    /**
     * 概述：是否正在切换通道<br/>
     * 适用条件：<br/>
     * 执行流程：<br/>
     * 使用方法：<br/>
     * 注意事项：<br/>
     *
     * @return boolean
     * @date 2014-08-07
     */
    public boolean isSwitchSource() {
        if (mSystemAPIImpl == null) {
            return false;
        }
        return mSystemAPIImpl.isSwitchSource();
    }

    /**
     * 概述：是否正在切换频道<br/>
     * 适用条件：<br/>
     * 执行流程：<br/>
     * 使用方法：<br/>
     * 注意事项：<br/>
     *
     * @return boolean
     * @date 2014-08-07
     */
    public boolean isSwitchChannel() {
        if (mSystemAPIImpl == null) {
            return false;
        }
        return mSystemAPIImpl.isSwitchChannel();
    }

    /**
     * 概述：是否正在搜台<br/>
     * 适用条件：<br/>
     * 执行流程：<br/>
     * 使用方法：<br/>
     * 注意事项：<br/>
     *
     * @return boolean
     * @date 2017-06-27
     */
    public boolean isSearchingChannel() {
        if (mSystemAPIImpl == null) {
            return false;
        }
        return mSystemAPIImpl.isSearchingChannel();
    }

    /**
     * 概述：异步获取指定source的信号状态<br/>
     * 适用条件：<br/>
     * 执行流程：<br/>
     * 使用方法：<br/>
     * 注意事项：<br/>
     *
     * @in Source source ：指定的source
     * @date 2014-3-15
     */
    public void getSignalStateAsync(Source source) {
        if (mSystemAPIImpl == null) {
            return;
        }
        mSystemAPIImpl.getSignalStateAsync(source);
    }

    public boolean resetToFactoryMode(boolean restorePresetChannel) {
        if (mSystemAPIImpl == null) {
            return false;
        }
        return mSystemAPIImpl.resetToFactoryMode(restorePresetChannel);
    }

    /**
     * 概述：是否支持设置窗口大小<br/>
     * 适用条件：getApiLevel() >= 0x00006 <br/>
     * 执行流程：<br/>
     * 注意事项：<br/>
     * @return boolean
     * @date 2019-09-18
     */
    public boolean canSetWindowSize() {
        if (mSystemAPIImpl == null) {
            return false;
        }
        return mSystemAPIImpl.canSetWindowSize();
    }

    /**
     * 概述：首页小窗口设置窗口大小<br/>
     * 适用条件：<br/>
     * 执行流程：<br/>
     * 注意事项：<br/>
     * @param x,y,w,h: 小窗口的坐标和小窗口的宽和高。
     *         flag参数为预留，后面按需要可能会按位来表示作用
     * @return int
     * @date 2018-05-07
     */
    public int setWindowSize(int x, int y, int w, int h, int flag) {
        if (mSystemAPIImpl != null) {
            return mSystemAPIImpl.setWindowSize(x,y,w,h,flag);
        }
        return SkyTvDefine.SET_WINDOW_SIZE_EXCEPTION;
    }

    /**
     * 概述：设置待机原因<br/>
     * 适用条件：<br/>
     * 执行流程：<br/>
     * 注意事项：<br/>
     * @param reason  STANDBY_REASON_VGA_NO_SIGNAL = 0x01;
     * @return
     * @date 2018-09-11
     */
    public void setStandbyReason(int reason) {
        if (mSystemAPIImpl != null) {
            mSystemAPIImpl.setStandbyReason(reason);
        }
    }
    
    /**
     * 概述：是否支持检测非当前通道的信号输入状态<br/>
     * 适用条件：<br/>
     * 执行流程：<br/>
     * 注意事项：<br/>开机时过早执行（如IPC 还没有建立等场景)，可能返回不正确的值。最好在已经拿到信号源列表后，再调用这个方法。
     * @param 
     * @return  boolean
     * @date 2018-12-04
     */
    public boolean isOfflineCheckSupported(){
        if (mSystemAPIImpl == null) {
            return true;
        }
        return mSystemAPIImpl.isOfflineCheckSupported();
    }

    /**
     * 概述：是否支持按通道设置<br/>
     * 适用条件：getApiLevel() >= 0x00007 <br/>
     * 执行流程：<br/>
     * 使用方法：<br/>
     * 注意事项：<br/>
     *
     * @return boolean true:支持 false:不支持
     * @date 2019-10-16
     */
    public boolean isEdidBySourceSupported() {
        if (mSystemAPIImpl == null) {
            return false;
        }
        return mSystemAPIImpl.isEdidBySourceSupported();
    }

    public List<CaOperator> getCaOperatorList() {
        if (mSystemAPIImpl == null) {
            return null;
        }
        return mSystemAPIImpl.getCaOperatorList();
    }

    public List<CaEntitle> getCaEntitleList(String id) {
        if (mSystemAPIImpl == null) {
            return null;
        }
        return mSystemAPIImpl.getCaEntitleList(id);
    }

    public boolean sendMessage(int what, int arg1, int arg2, Bundle object) {
        if (mSystemAPIImpl == null) {
            return false;
        }
        return mSystemAPIImpl.sendMessage(what, arg1, arg2, object);
    }

    /**
     * 概述：是否支持频道搜索或编辑<br/>
     * 适用条件：getApiLevel() >= 0x00007 <br/>
     * 执行流程：<br/>
     * 使用方法：<br/>
     * 注意事项：<br/>
     *
     * @return boolean true:支持 false:不支持
     * @date 2020-03-02
     */
    public boolean getChannelSeachOrEditEnable() {
        if (mSystemAPIImpl == null) {
            return false;
        }
        return mSystemAPIImpl.getChannelSeachOrEditEnable();
    }

    /**
     * 概述：设置是否支持频道搜索或编辑<br/>
     * 适用条件：getApiLevel() >= 0x00007 <br/>
     * 执行流程：<br/>
     * 使用方法：<br/>
     * 注意事项：<br/>
     * @param enable  true:支持 false:不支持
     * @return
     * @date 2020-03-02
     */
    public void setChannelSeachOrEditEnable(boolean enable) {
        if (mSystemAPIImpl == null) {
            return;
        }
        mSystemAPIImpl.setChannelSeachOrEditEnable(enable);
    }

    /**
     * 概述：设置天线电压开关<br/>
     * 适用条件：getApiLevel() >= 0x00008 <br/>
     * 执行流程：<br/>
     * 使用方法：<br/>
     * 注意事项：<br/>
     *
     * @param mode true 开，false 关
     * @return true 成功，false 失败
     * @date 2020-04-16
     */
    public boolean setAntennaVoltage(boolean mode) {
        if (mSystemAPIImpl == null) {
            return false;
        }
        return mSystemAPIImpl.setAntennaVoltage(mode);
    }

    /**
     * 概述：获取天线电压开关<br/>
     * 适用条件：getApiLevel() >= 0x00008 <br/>
     * 执行流程：<br/>
     * 使用方法：<br/>
     * 注意事项：<br/>
     *
     * @return true 开，false 关
     * @date 2020-04-16
     */
    public boolean getAntennaVoltage() {
        if (mSystemAPIImpl == null) {
            return false;
        }
        return mSystemAPIImpl.getAntennaVoltage();
    }
    /**
     * 概述：获取 resolution of current source<br/>
     * 适用条件：<br/>
     * 执行流程：<br/>
     * 使用方法：<br/>
     * 注意事项：<br/>
     *
     * @return String
     * @date 2020-10-20
     */
    public String getResolution(){
        if (mSystemAPIImpl == null) {
            return null;
        }
        return mSystemAPIImpl.getResolution();
    }

    /**
     * 概述：Memc 是否有效<br/>
     * 适用条件：HDMI通道下，开启FreeSync后，返回false;<br/>
     * 执行流程：<br/>
     * 使用方法：<br/>
     * 注意事项：<br/>
     *
     * @return boolean
     * @date 2021-03-18
     */
    public boolean isMEMCValid() {
        if (mSystemAPIImpl == null) {
            return false;
        }
        return mSystemAPIImpl.isMEMCValid();
    }

   public boolean startSplitScreenTV(int x,int y,int w,int h,int flag){
        if (mSystemAPIImpl == null) {
            return false;
        }
        return mSystemAPIImpl.startSplitScreenTV(x,y,w,h,flag);
    }

    public boolean exitSplitScreenTV(){
        if (mSystemAPIImpl == null) {
            return false;
        }
        return mSystemAPIImpl.exitSplitScreenTV();
    }

    public boolean reSizeSplitScreenTV(int x, int y, int w, int h, int flag){
        if (mSystemAPIImpl == null) {
            return false;
        }
        return mSystemAPIImpl.reSizeSplitScreenTV(x,y,w,h,flag);
    }
    public boolean isTVForeground(){
        if (mSystemAPIImpl == null) {
            return false;
        }
        return mSystemAPIImpl.isTVForeground();
    }

    public boolean getFreeSyncStatus(Source source){
        if (mSystemAPIImpl == null) {
            return false;
        }
        return mSystemAPIImpl.getFreeSyncStatus(source);
    }

    public boolean setFreeSyncStatus(Source source,boolean status){
        if (mSystemAPIImpl == null) {
            return false;
        }
        return mSystemAPIImpl.setFreeSyncStatus(source,status);
    }


    public boolean isFreeSyncSupported(){
        if (mSystemAPIImpl == null) {
            return false;
        }
        return mSystemAPIImpl.isFreeSyncSupported();
    }

    public boolean setEDID(Source source,int edid){
        if (mSystemAPIImpl == null) {
            return false;
        }
        return mSystemAPIImpl.setEdid(source,edid);
    }

    public int getEDID(Source source){
        if (mSystemAPIImpl == null) {
            return -1;
        }
        return mSystemAPIImpl.getEdid(source);
    }

    public int[] getEdidSupportList(Source source){
        if (mSystemAPIImpl == null) {
            return null;
        }
        return mSystemAPIImpl.getSupportedEdidList(source);
    }

    /**
     * 概述：获取 source 对应的频道列表<br/>
     * 适用条件： <br/>
     * 执行流程：<br/>
     * 使用方法：<br/>
     * 注意事项：<br/>
     *
     * @return List<ChannelInfo>
     * @date 2020-08-13
     */
    public List<ChannelInfo> getChannelInfoList(Source source){
        if (mSystemAPIImpl == null) {
            return null;
        }
        return mSystemAPIImpl.getChannelInfoList(source);
    }

    public boolean gotoSourceWithChannel(Source source,ChannelInfo channel){
        if (mSystemAPIImpl == null) {
            return false;
        }
        return mSystemAPIImpl.gotoSourceWithChannel(source,channel);
    }

    public CECDeviceInfo getHDMICECDeviceInfo(Source source){
        if (mSystemAPIImpl == null) {
            return null;
        }
        return mSystemAPIImpl.getHDMICECDeviceInfo(source);
    }

    public boolean setCecDeviceChangeListener(OnCECDeviceChangedListener listener){
        if (mSystemAPIImpl ==null){
            return false;
        }
        return mSystemAPIImpl.setOnDeviceChangedListener(listener);
    }

}
