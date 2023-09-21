package com.tianci.tv.api.setting;

import android.content.Context;

import com.tianci.tv.define.object.ChannelInfo;
import com.tianci.tv.define.object.Source;
import com.tianci.tv.framework.api.SkyTvApi;
import com.tianci.tv.framework.implement.setting.ContentProviderSettingAPIImpl;
import com.tianci.tv.framework.implement.setting.IPCServiceSettingAPIImpl;
import com.tianci.tv.framework.implement.setting.SettingAPI;
import com.tianci.tv.framework.implement.setting.SysContentProviderSettingAPIImpl;
import com.tianci.tv.utils.SkyTvUtils;
import com.tianci.tv.utils.TVSDKDebug;

import java.util.List;

/**
 * <p>
 * Description:
 * </p>
 * <p>
 * TV通用设置类接口
 * </p>
 *
 * @author xiangge
 * @version V*.*.*
 * @ClassName SkyTvSettingApi
 * @date 2013-11-20
 */
public class SkyTvSettingApi extends SkyTvApi {
    private SettingAPI mSettingAPIImpl;
    private boolean isOldTv= false;
    private boolean isC9OrUpper = false;



    public SkyTvSettingApi(Context context) {
        super(context);
        boolean isTianciTvAppInstalled = SkyTvUtils.isAppInstalled(context, "com.tianci.tv");
        boolean isTVProviderAppInstalled = SkyTvUtils.isAppInstalled(context, "com.skyworth.tv.provider");
        boolean isTcCoocaaOSUpe6_50 = SkyTvUtils.isTcCoocaaOSUpe6_50();
        TVSDKDebug.info("SkyTvSettingApi isTianciTvAppInstalled：" + isTianciTvAppInstalled+"  isTcCoocaaOSUpe6_50:"+isTcCoocaaOSUpe6_50);
        Context ctx = context.getApplicationContext();
        if (ctx == null) {
            ctx = context;
        }
        if (isTianciTvAppInstalled) {
            if(!isTcCoocaaOSUpe6_50){
                mSettingAPIImpl = new IPCServiceSettingAPIImpl(listener);
            }else {
                isC9OrUpper = SkyTvUtils.getSystemVersion().startsWith("9");
                if(isC9OrUpper){
                    mSettingAPIImpl = new SysContentProviderSettingAPIImpl(ctx);
                }else {
                    mSettingAPIImpl = new ContentProviderSettingAPIImpl(ctx);
                }
            }
        } else {
            if (isTVProviderAppInstalled){
                mSettingAPIImpl = new ContentProviderSettingAPIImpl(ctx);
            }else{
                mSettingAPIImpl = new SysContentProviderSettingAPIImpl(ctx);
            }
        }
    }

    /**
     * 概述：设置开机通道<br/>
     * 适用条件：<br/>
     * 执行流程：<br/>
     * 使用方法：<br/>
     * 注意事项：<br/>
     *
     * @param source
     * @return boolean
     * @date 2013-11-20
     */
    public boolean setBootsource(Source source) {
        if (mSettingAPIImpl == null) {
            return false;
        }
        return mSettingAPIImpl.setBootsource(source);
    }

    /**
     * 概述：获取当前设置的开机通道<br/>
     * 适用条件：<br/>
     * 执行流程：<br/>
     * 使用方法：<br/>
     * 注意事项：<br/>
     *
     * @return Source
     * @date 2013-11-20
     */
    public Source getBootsource() {
        if (mSettingAPIImpl == null) {
            return null;
        }
        return mSettingAPIImpl.getBootsource();
    }



    /**
     * 概述：设置电视模式对应的通道<br/>
     * 适用条件：个别定制机的需求<br/>
     * 执行流程：<br/>
     * 使用方法：<br/>
     * 注意事项：<br/>
     *
     * @param source
     * @return boolean
     * @date 2022-10-20
     */
    public boolean setTvModeSource(Source source) {
        if (mSettingAPIImpl == null) {
            return false;
        }
        return mSettingAPIImpl.setTvModeSource(source);
    }

    /**
     * 概述：获取电视模式对应的通道<br/>
     * 适用条件：个别定制机的需求<br/>
     * 执行流程：<br/>
     * 使用方法：<br/>
     * 注意事项：<br/>
     *
     * @return Source
     * @date 2022-10-20
     */
    public Source getTvModeSource() {
        if (mSettingAPIImpl == null) {
            return null;
        }
        return mSettingAPIImpl.getTvModeSource();
    }


    /**
     * 概述：获取系统内的开机通道列表，如果没有则返回size是0的list<br/>
     * 适用条件：<br/>
     * 执行流程：<br/>
     * 使用方法：<br/>
     * 注意事项：<br/>
     *
     * @return List<Source>
     * @date 2013-11-20
     */
    @SuppressWarnings("unchecked")
    public List<Source> getBootsourceList() {
        if (mSettingAPIImpl == null) {
            return null;
        }
        return mSettingAPIImpl.getBootsourceList();
    }
    
    public boolean setBootChannel(ChannelInfo channelInfo){
        if (mSettingAPIImpl == null) {
            return false;
        }
        return mSettingAPIImpl.setBootChannel(channelInfo);
    }

    public ChannelInfo getBootChannel(){
        if (mSettingAPIImpl == null) {
            return null;
        }
        return mSettingAPIImpl.getBootChannel();
    }

    public List<ChannelInfo> getBootChannelList()
    {
        if (mSettingAPIImpl == null) {
            return null;
        }
        return mSettingAPIImpl.getBootChannelList();
    }

    /**
     * 概述：设置一触即播开关<br/>
     * 适用条件：version >= 0x40000<br/>
     * 执行流程：<br/>
     * 使用方法：<br/>
     * 注意事项：新TV应用上使用<br/>
     *
     * @param on -- true: 开      false ：关
     * @return boolean -- true: 成功      false ：失败
     * @date 2019-8-24
     */
    public boolean setAutoSwitchSource(boolean on) {
        if (mSettingAPIImpl == null) {
            return false;
        }
        return mSettingAPIImpl.setAutoSwitchSource(on);
    }

    /**
     * 概述：获取一触即播开关<br/>
     * 适用条件：version >= 0x40000<br/>
     * 执行流程：<br/>
     * 使用方法：<br/>
     * 注意事项：新TV应用上使用<br/>
     *
     * @return boolean -- true: 开      false ：关
     * @date 2019-8-24
     */
    public boolean getAutoSwitchSource() {
        if (mSettingAPIImpl == null) {
            return false;
        }
        return mSettingAPIImpl.getAutoSwitchSource();
    }

    /**
     * setSourceTypePurpose .now used in display
     * 信号源设置，对该通道进行用途设定
     * @param source
     *        type 0:standard 外接普通设备;    1:game 外接游戏设备
     */
    public void setSourceTypePurpose(Source source, int purpose) {
        if (mSettingAPIImpl == null) {
            return ;
        }
         mSettingAPIImpl.setSourceTypePurpose(source,purpose);
    }

    /**
     * getSourceTypePurpose .now used in display
     * 信号源设置，获取指定通道的用途
     * @param source
     * @return  int 0:standard 外接普通设备;    1:game 外接游戏设备
     */
    public int getSourceTypePurpose(Source source) {
        if (mSettingAPIImpl == null) {
            return 0;
        }
        return mSettingAPIImpl.getSourceTypePurpose(source);
    }

    /**
     * setSourceTypeName .now used in display
     * 信号源设置，对该通道进行用途设定
     * @param source
     *        String name
     */

    public void setSourceTypeName(Source source, String name) {
        if (mSettingAPIImpl == null) {
            return;
        }
        mSettingAPIImpl.setSourceTypeName(source,name);
    }

    /**
     * getSourceTypeName .now used in display
     * 信号源设置，获取指定通道的名字
     * @param source
     * @return  String name
     */

    public String getSourceTypeName(Source source) {
        if (mSettingAPIImpl == null) {
            return "";
        }
        return mSettingAPIImpl.getSourceTypeName(source);
    }

    public void setDisPlayerQuickKey(boolean isback,int index){
        if (mSettingAPIImpl == null) {
            return;
        }
        mSettingAPIImpl.setDisPlayerQuickKey(isback,index);
    }

    public int getDisPlayerQuickKey(boolean isBack){
        if (mSettingAPIImpl == null) {
            return 0;
        }
       return mSettingAPIImpl.getDisPlayerQuickKey(isBack);
    }

    public int[] getDisplayModes(Source source){
        if (mSettingAPIImpl == null) {
            return null;
        }
        return mSettingAPIImpl.getDisplayModes(source);
    }

    public boolean setDisplayMode(int mode){
        if (mSettingAPIImpl == null) {
            return false;
        }
        return mSettingAPIImpl.setDisplayMode(mode);
    }

    public int getDisplayMode(){
        if (mSettingAPIImpl == null) {
            return 0;
        }
        return mSettingAPIImpl.getDisplayMode();
    }

    public boolean sourceRename(Source source,String newName){
        if (mSettingAPIImpl == null) {
            return false;
        }
        return mSettingAPIImpl.sourceRename(source,newName);
    }

    public String getSourceRename(Source source){
        if (mSettingAPIImpl == null) {
            return "";
        }
        return mSettingAPIImpl.getSourceRename(source);
    }
}
