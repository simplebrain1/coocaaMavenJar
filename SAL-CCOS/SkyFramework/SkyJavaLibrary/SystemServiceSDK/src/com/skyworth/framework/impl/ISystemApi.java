package com.skyworth.framework.impl;

import com.skyworth.framework.define.SystemDefines;
import com.skyworth.framework.data.ExternDiskInfo;
import com.skyworth.framework.data.Mode;

import java.util.List;

public class ISystemApi {
    public int getOledScreenFixState() {
        return SystemDefines.SCREEN_STATE_UNKNOWN;
    }

    public String getSystemVersion() {
        return null;
    }

    public String getBuildVersion() {
        return null;
    }

    public String getDeviceType() {
        return null;
    }

    public String getDeviceModel() {
        return null;
    }

    public String getChipId() {
        return null;
    }

    public String getBarcode() {
        return null;
    }

    public String getMacAddress() {
        return null;
    }

    public String getEmmcId() {
        return null;
    }

    public void setVoiceLogoControl(int mode, int arg) {
    }

    public boolean setBacklight(int backlight) {
        return false;
    }

    public int getBacklight(){
        return 0;
    }

    public boolean setColor(int color) {
        return false;
    }

    public int getColor(){
        return 0;
    }

    public boolean getSportMode() {
        return false;
    }

    public boolean setSportMode(boolean mode) {
        return false;
    }

    public List<Mode> getPictureModeList() {
        return null;
    }

    public Mode getPictureMode() {
        return null;
    }

    public boolean setPictureMode(Mode mode) {
        return false;
    }

    public boolean setContentSceneMode(Mode mode) {
        return false;
    }

    public Mode getContentSceneMode() {
        return null;
    }

    public List<Mode> getSoundModeList() {
        return null;
    }

    public Mode getSoundMode() {
        return null;
    }

    public boolean setSoundMode(Mode mode) {
        return false;
    }

    public boolean getQuickDemoMode() {
        return false;
    }

    public boolean setQuickDemoMode(boolean mode) {
        return false;
    }

    public String getPanelSize() {
        return null;
    }

    public int getStreamType() {
        return SystemDefines.STREAM_TYPE_UNKNOWN;
    }

    public String getSystemOwner() {
        return null;
    }
    public boolean isSupportCareMode() {
        return false;
    }

    public List<ExternDiskInfo> getExternalDisks() {
        return null;
    }

    public void reboot() {

    }

    public boolean isSupportXDR() {
        return false;
    }

    public boolean getXDRMode() {
        return false;
    }

    public boolean setXDRMode(boolean mode) {
        return false;
    }

    public List<String> getTemperatureColorList(){
        return null;
    }

    public String getCurrentTemperatureColor(){
        return null;
    }

    public boolean setTemperatureColor(String colorMode){return false;}

}
