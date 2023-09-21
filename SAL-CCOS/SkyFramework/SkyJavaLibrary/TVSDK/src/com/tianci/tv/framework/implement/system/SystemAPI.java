package com.tianci.tv.framework.implement.system;

import android.content.Context;
import android.os.Bundle;
import android.view.Surface;

import com.tianci.tv.define.SkyTvDefine;
import com.tianci.tv.define.object.CECDeviceInfo;
import com.tianci.tv.define.object.CaCardInfo;
import com.tianci.tv.define.object.CaEntitle;
import com.tianci.tv.define.object.CaOperator;
import com.tianci.tv.define.object.Channel;
import com.tianci.tv.define.object.ChannelInfo;
import com.tianci.tv.define.object.Source;
import com.tianci.tv.define.object.SourceInfo;
import com.tianci.tv.framework.implement.system.listener.OnCACardInfoListener;
import com.tianci.tv.framework.implement.system.listener.OnCECDeviceChangedListener;

import java.util.List;

/**
 * Created by hq on 2017/12/2.
 */

public abstract class SystemAPI {
    public int getApiLevel() {
        return 0;
    }

    public int switchChannel(ChannelInfo channelInfo) {
        return SkyTvDefine.SWITCH_CHANNEL_EXCEPTION;
    }

    public boolean setOnCaCardInfoListener(OnCACardInfoListener listener) {
        return false;
    }

    public CaCardInfo getCaCardInfo() {
        return null;
    }

    public boolean switchSource(Source source) {
        return false;
    }

    public boolean switchSourceBackGround(Source source) {
        return false;
    }

    public boolean holdSource(SourceInfo sourceInfo, ChannelInfo channelInfo, Surface surface
            ,int x,int y,int w,int h,int flag) {
        return false;
    }
    public boolean releaseSource(SourceInfo sourceInfo) {
        return false;
    }

    public boolean forceReleaseSource() {
        return false;
    }

    public Source getCurrentSource() {
        return null;
    }

    public SourceInfo getRealSource() {
        return null;
    }

    public List<Source> getSourceList() {
        return null;
    }

    public String getSourceResolution(Source source){
        return "2K";
    }
    public boolean isReady() {
        return false;
    }

    public boolean isReleased() {
        return false;
    }

    public boolean releaseAllWithStandby() {
        return false;
    }

    public boolean backToTv() {
        return false;
    }

    public boolean backToTvSource() {
        return false;
    }

    public boolean canCaptureScreen() {
        return false;
    }

    public boolean setNeedShowBootGuide(boolean setValue) {
        return false;
    }

    public int setTimeUpdateFlag(int flag) {
        return 0;
    }

    public boolean isTvVideoFocused() {
        return false;
    }

    public SkyTvDefine.SOURCE_SIGNAL_STATE signalState(Source source) {
        return SkyTvDefine.SOURCE_SIGNAL_STATE.NOSIGNAL;
    }

    public boolean canPopupInteractionNow() {
        return false;
    }

    public boolean isInteractionEnable() {
        return false;
    }

    public boolean registerSystemListener() {
        return false;
    }

    public boolean unregisterSystemListener() {
        return false;
    }

    public boolean restorePresetChannel(Context context) {
        return false;
    }

    public List<Source> getExternalSourceList() {
        return null;
    }

    public boolean enableExternalSource(Source source, boolean val) {
        return false;
    }

    public boolean getExternalSourceEnable(Source source) {
        return false;
    }

    public boolean sourceUp() {
        return false;
    }

    public boolean sourceDown() {
        return false;
    }

    public boolean switchChannel(Channel channel) {
        return false;
    }

    public Channel getCurrentChannel() {
        return null;
    }

    public boolean isSwitchSource() {
        return false;
    }

    public boolean isSwitchChannel() {
        return false;
    }

    public boolean isMEMCValid() {
        return true;
    }

    public boolean isSearchingChannel() {
        return false;
    }

    public void getSignalStateAsync(Source source) {
    }

    public boolean resetToFactoryMode(boolean restorePresetChannel) {
        return false;
    }

    public boolean canSetWindowSize() {
        return false;
    }

    public int setWindowSize(int x, int y, int w, int h, int flag) {
        return SkyTvDefine.SET_WINDOW_SIZE_EXCEPTION;
    }

    public void setStandbyReason(int reason) {
    }

    public boolean isOfflineCheckSupported() {
        return true;
    }

    public boolean isEdidBySourceSupported() {
        return false;
    }

    public int getEdid(Source source){return 0;}

    public boolean setEdid(Source source, int edid){
        return false;
    }

    public int[] getSupportedEdidList(Source source){
        return new int[0];
    }

    public List<CaOperator> getCaOperatorList() {
        return null;
    }

    public List<CaEntitle> getCaEntitleList(String id) {
        return null;
    }

    public boolean sendMessage(int what, int arg1, int arg2, Bundle object) {
        return false;
    }

    public boolean getChannelSeachOrEditEnable() {
        return true;
    }

    public void setChannelSeachOrEditEnable(boolean enable) {

    }

    public boolean setAntennaVoltage(boolean mode) {
        return false;
    }

    public boolean getAntennaVoltage() {
        return false;
    }

    public List<ChannelInfo> getChannelInfoList(Source source){
        return null;
    }

    public String getResolution(){
        return null;
    }

    public boolean isTVForeground(){
        return false;
    }

    public boolean getFreeSyncStatus(Source source){
        return false;
    }

    public boolean isFreeSyncSupported(){
        return false;
    }

    public boolean setFreeSyncStatus(Source source, boolean status){
        return false;
    }
	
	public boolean startSplitScreenTV(int x,int y,int w,int h,int flag){
        return false;
    }

    public boolean exitSplitScreenTV(){return false;}

    public boolean reSizeSplitScreenTV(int x, int y, int w, int h, int flag){return false;}


    public boolean gotoSourceWithChannel(Source source, ChannelInfo channelInfo){
        return false;
    }

    public CECDeviceInfo getHDMICECDeviceInfo(Source source){
        return null;

    }

    public boolean setOnDeviceChangedListener(OnCECDeviceChangedListener listener) {
        return false;
    }
}
