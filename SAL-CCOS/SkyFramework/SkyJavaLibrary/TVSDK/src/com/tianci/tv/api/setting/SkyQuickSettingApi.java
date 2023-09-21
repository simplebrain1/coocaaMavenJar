package com.tianci.tv.api.setting;

import android.content.Context;

import com.tianci.framework.player.kernel.parameter.SkyPlayerParameter;
import com.tianci.tv.define.SkyTvDefine;
import com.tianci.tv.framework.implement.setting.TVQuickSettingAPIImpl;

import java.util.List;

/**
 * 快捷设置专用api
 * 快捷设置对接TV
 */
public class SkyQuickSettingApi {

    private final Context mContext;
    private final TVQuickSettingAPIImpl tvQuickSettingAPIImpl;

    public SkyQuickSettingApi(Context context){
        this.mContext = context;
        tvQuickSettingAPIImpl = new TVQuickSettingAPIImpl(mContext);
    }

    public boolean isSupportVrr(){
       return tvQuickSettingAPIImpl.isSupport(SkyTvDefine.SUPPORT_VRR);
    }

    public boolean isEnableVrr(){
        return tvQuickSettingAPIImpl.isEnable(SkyTvDefine.ENABLE_VRR);
    }

    public boolean getCurrentVrr(){
        return tvQuickSettingAPIImpl.getCurrentIndex(SkyTvDefine.GET_VRR) == 1;
    }

    public boolean setVrr(boolean current){
        return tvQuickSettingAPIImpl.setCurrentByIndex(SkyTvDefine.SET_VRR,current ? 1 : 0);
    }

    public boolean isEnableEDID(){
        return tvQuickSettingAPIImpl.isEnable(SkyTvDefine.ENABLE_EDID);
    }

    public int getCurrentEDID(){
        return tvQuickSettingAPIImpl.getCurrentIndex(SkyTvDefine.GET_EDIE);
    }

    public List<String> getEDIDList(){
        return tvQuickSettingAPIImpl.getSettingList(SkyTvDefine.GET_EDIE_LIST);
    }

    public boolean setEDID(int current){
        return tvQuickSettingAPIImpl.setCurrentByIndex(SkyTvDefine.SET_EDIE,current);
    }

    public boolean isEnableDisplayMode(){
        return tvQuickSettingAPIImpl.isEnable(SkyTvDefine.ENABLE_DISPLAYMODE);
    }

    public List<String> getDisplayModes(){
        return tvQuickSettingAPIImpl.getSettingList(SkyTvDefine.GET_DISPLAYMODE_LIST);
    }

    public int getCurrentDisplayMode(){
        return tvQuickSettingAPIImpl.getCurrentIndex(SkyTvDefine.GET_DISPLAYMODE);
    }

    public boolean setDisplayMode(int current){
        return tvQuickSettingAPIImpl.setCurrentByIndex(SkyTvDefine.SET_DISPLAYMODE,current);
    }

    public boolean isEnableXDR(){
        return tvQuickSettingAPIImpl.isEnable(SkyTvDefine.ENABLE_XDR);
    }

    public boolean getCurrentXDR(){
        return tvQuickSettingAPIImpl.getCurrentIndex(SkyTvDefine.GET_XDR) == 1;
    }

    public boolean setXDR(boolean current){
        return tvQuickSettingAPIImpl.setCurrentByIndex(SkyTvDefine.SET_XDR,current ? 1 : 0);
    }

    public boolean isEnableVideoSignal(){
        return tvQuickSettingAPIImpl.isEnable(SkyTvDefine.ENABLE_VIDEO_SIGNAL);
    }

    public List<String> getVideoSignalList(){
        return tvQuickSettingAPIImpl.getSettingList(SkyTvDefine.GET_VIDEO_SIGNAL_LIST);
    }

    public int getCurrentVideoSignal(){
        return tvQuickSettingAPIImpl.getCurrentIndex(SkyTvDefine.GET_VIDEO_SIGNAL);
    }

    public boolean setVideoSignal(int current){
        return tvQuickSettingAPIImpl.setCurrentByIndex(SkyTvDefine.SET_VIDEO_SIGNAL,current);
    }

    public boolean isEnableSoundChannel(){
        return tvQuickSettingAPIImpl.isEnable(SkyTvDefine.ENABLE_SOUND_CHANNEL);
    }

    public List<String> getSoundChannelList(){
        return tvQuickSettingAPIImpl.getSettingList(SkyTvDefine.GET_SOUND_CHANNEL_LIST);
    }

    public int getCurSoundChannel(){
        return tvQuickSettingAPIImpl.getCurrentIndex(SkyTvDefine.GET_SOUND_CHANNEL);
    }

    public boolean setSoundChannel(int current){
        return tvQuickSettingAPIImpl.setCurrentByIndex(SkyTvDefine.SET_SOUND_CHANNEL,current);
    }

    public boolean isEnableSoundTrack(){
        return tvQuickSettingAPIImpl.isEnable(SkyTvDefine.ENABLE_SOUND_TRACK);
    }
    public List<String> getSoundTrackList(){
        return tvQuickSettingAPIImpl.getSettingList(SkyTvDefine.GET_SOUND_TRACK_LIST);
    }

    public int getCurSoundTrack(){
        return tvQuickSettingAPIImpl.getCurrentIndex(SkyTvDefine.GET_SOUND_TRACK);
    }

    public boolean setSoundTrack(int current){
        return tvQuickSettingAPIImpl.setCurrentByIndex(SkyTvDefine.SET_SOUND_TRACK,current);
    }

    public boolean isEnableSubtitleMode(){
        return tvQuickSettingAPIImpl.isEnable(SkyTvDefine.ENABLE_SUBTITLE_MODE);
    }

    public List<String> getSubtitleModeList(){
        return tvQuickSettingAPIImpl.getSettingList(SkyTvDefine.GET_SUBTITLE_MODE_LIST);
    }

    public int getCurSubtitleMode(){
        return tvQuickSettingAPIImpl.getCurrentIndex(SkyTvDefine.GET_SUBTITLE_MODE);
    }

    public boolean setSubtitleMode(int current){
        return tvQuickSettingAPIImpl.setCurrentByIndex(SkyTvDefine.SET_SUBTITLE_MODE,current);
    }

    public boolean isEnableColourSystem() {
        return tvQuickSettingAPIImpl.isEnable(SkyTvDefine.ENABLE_COLOUR_SYSTEM);
    }

    public List<String> getColourSystemList() {
        return tvQuickSettingAPIImpl.getSettingList(SkyTvDefine.GET_COLOUR_SYSTEM_LIST);
    }

    public int getCurColourSystem() {
        return tvQuickSettingAPIImpl.getCurrentIndex(SkyTvDefine.GET_COLOUR_SYSTEM);
    }

    public boolean setColourSystem(int current) {
        return tvQuickSettingAPIImpl.setCurrentByIndex(SkyTvDefine.SET_COLOUR_SYSTEM,current);
    }


    public boolean isEnableSearchChannel() {
        return tvQuickSettingAPIImpl.isEnable(SkyTvDefine.ENABLE_SEARCH_CHANNEL);
    }

    public boolean isEnableChannelDetail() {
        return tvQuickSettingAPIImpl.isEnable(SkyTvDefine.ENABLE_CHANNEL_DETAIL);
    }

    public boolean isEnableTVMode() {
        return tvQuickSettingAPIImpl.isEnable(SkyTvDefine.ENABLE_TV_MODE);
    }

    public boolean isEnableResetGuide() {
        return tvQuickSettingAPIImpl.isEnable(SkyTvDefine.ENABLE_RESET_GUIDE);
    }

    public boolean isEnableLogoSwitch(){
        return tvQuickSettingAPIImpl.isEnable(SkyTvDefine.ENABLE_LOGO_SWITCH);
    }

    public boolean getCurrentLogoSwitch(){
        return tvQuickSettingAPIImpl.getCurrentIndex(SkyTvDefine.GET_LOGO_SWITCH) == 1;
    }

    public boolean setLogoSwitch(boolean current){
        return tvQuickSettingAPIImpl.setCurrentByIndex(SkyTvDefine.SET_LOGO_SWITCH,current ? 1 : 0);
    }

    public boolean isEnableGameBar() {
        return tvQuickSettingAPIImpl.isEnable(SkyTvDefine.ENABLE_GAME_BAR);
    }

    public boolean isNeedCheckBeforeSetEDID() {
        return tvQuickSettingAPIImpl.isNeedCheck(SkyTvDefine.CHECK_SET_EDID);
    }
}
