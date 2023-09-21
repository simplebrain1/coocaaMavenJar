package com.tianci.system.api;

import android.content.Context;
import android.os.Bundle;

import com.tianci.system.define.SysConst;
import com.tianci.system.define.SystemProviderDefines;
import com.tianci.system.utils.ApiUtil;
import com.tianci.utils.SkySSSLog;

import static com.tianci.system.utils.ApiUtil.KEY_PARAM;
import static com.tianci.system.utils.ApiUtil.KEY_PARAM_TYPE;

public class VirtualInputApi {
    private static final String TAG = "VirtualInputApi";

    public VirtualInputApi(Context context) {
        Context ctx = context.getApplicationContext();
        if (ctx == null) {
            ctx = context;
        }
        ApiUtil.setContext(ctx);
    }

    /**
     * 鼠标点击输入
     *
     * @param btnCode 按键码
     */
    public void clickButton(int btnCode) {
        SkySSSLog.d(TAG, "clickButton btnCode=" + btnCode);
        Bundle param = new Bundle();
        param.putInt(KEY_PARAM_TYPE, SysConst.VIRTUALINPUT_clickButton);
        param.putInt(KEY_PARAM, btnCode);
        setData(param);
    }

    /**
     * 鼠标按钮按下输入
     *
     * @param btnCode 按键码
     */
    public void buttonDown(int btnCode) {
        SkySSSLog.d(TAG, "buttonDown btnCode=" + btnCode);
        Bundle param = new Bundle();
        param.putInt(KEY_PARAM_TYPE, SysConst.VIRTUALINPUT_buttonDown);
        param.putInt(KEY_PARAM, btnCode);
        setData(param);
    }

    /**
     * 鼠标按钮松开输入
     *
     * @param btnCode 按键码
     */
    public void buttonUp(int btnCode) {
        SkySSSLog.d(TAG, "buttonUp btnCode=" + btnCode);
        Bundle param = new Bundle();
        param.putInt(KEY_PARAM_TYPE, SysConst.VIRTUALINPUT_buttonUp);
        param.putInt(KEY_PARAM, btnCode);
        setData(param);
    }

    /**
     * 鼠标向上滚动输入
     */
    public void rollMdlBtnUp() {
        SkySSSLog.d(TAG, "rollMdlBtnUp");
        Bundle param = new Bundle();
        param.putInt(KEY_PARAM_TYPE, SysConst.VIRTUALINPUT_rollMdlBtnUp);
        setData(param);
    }

    /**
     * 鼠标向下滚动输入
     */
    public void rollMdlBtnDown() {
        SkySSSLog.d(TAG, "rollMdlBtnDown");
        Bundle param = new Bundle();
        param.putInt(KEY_PARAM_TYPE, SysConst.VIRTUALINPUT_rollMdlBtnDown);
        setData(param);
    }

    /**
     * 鼠标位置输入（相对位置）
     *
     * @param x 坐标
     * @param y 坐标
     */
    public void moveMouse(float x, float y) {
        SkySSSLog.d(TAG, "moveMouse x=" + x + ",y=" + y);
        Bundle param = new Bundle();
        param.putInt(KEY_PARAM_TYPE, SysConst.VIRTUALINPUT_moveMouse);
        param.putFloat("x", x);
        param.putFloat("y", y);
        setData(param);
    }

    /**
     * 鼠标位置输入（绝对位置）
     *
     * @param x 坐标
     * @param y 坐标
     */
    public void moveMouseTo(float x, float y) {
        SkySSSLog.d(TAG, "moveMouseTo x=" + x + ",y=" + y);
        Bundle param = new Bundle();
        param.putInt(KEY_PARAM_TYPE, SysConst.VIRTUALINPUT_moveMouseTo);
        param.putFloat("x", x);
        param.putFloat("y", y);
        setData(param);
    }

    /**
     * 触摸输入
     *
     * @param x      坐标
     * @param y      坐标
     * @param status 状态
     */
    public void touch(float[] x, float[] y, int status) {
        SkySSSLog.d(TAG, "touch x=" + x + ",y=" + y + ",status=" + status);
        Bundle param = new Bundle();
        param.putInt(KEY_PARAM_TYPE, SysConst.VIRTUALINPUT_touch);
        param.putFloatArray("x", x);
        param.putFloatArray("y", y);
        param.putInt("status", status);
        setData(param);
    }

    public void touchDown() {
        SkySSSLog.d(TAG, "touchDown");
        Bundle param = new Bundle();
        param.putInt(KEY_PARAM_TYPE, SysConst.VIRTUALINPUT_touch_down);
        setData(param);
    }

    public void touchUp() {
        SkySSSLog.d(TAG, "touchUp");
        Bundle param = new Bundle();
        param.putInt(KEY_PARAM_TYPE, SysConst.VIRTUALINPUT_touch_up);
        setData(param);
    }

    /**
     * 按下按键
     *
     * @param keyCode 按键键值
     */
    public void pressKey(int keyCode) {
        SkySSSLog.d(TAG, "pressKey keyCode=" + keyCode);
        Bundle param = new Bundle();
        param.putInt(KEY_PARAM_TYPE, SysConst.VIRTUALINPUT_pressKey);
        param.putInt(KEY_PARAM, keyCode);
        setData(param);
    }

    /**
     * 松开事件
     *
     * @param keyCode 按键键值
     */
    public void releaseKey(int keyCode) {
        SkySSSLog.d(TAG, "releaseKey keyCode=" + keyCode);
        Bundle param = new Bundle();
        param.putInt(KEY_PARAM_TYPE, SysConst.VIRTUALINPUT_releaseKey);
        param.putInt(KEY_PARAM, keyCode);
        setData(param);
    }

    /**
     * 点击事件
     *
     * @param keyCode 按键键值
     */
    public void clickKey(int keyCode) {
        SkySSSLog.d(TAG, "clickKey keyCode=" + keyCode);
        Bundle param = new Bundle();
        param.putInt(KEY_PARAM_TYPE, SysConst.VIRTUALINPUT_clickKey);
        param.putInt(KEY_PARAM, keyCode);
        setData(param);
    }

    /**
     * 传感器事件输入
     *
     * @param sensortype 传感器类型
     * @param accuracy   进度
     * @param timestemp  时间戳
     * @param data       数据
     */
    public void sensorChangedInput(int sensortype, int accuracy, long timestemp, float[] data) {
        SkySSSLog.d(TAG, "sensorChangedInput sensortype=" + sensortype + ",accuracy=" +
                accuracy + ",timestemp=" + timestemp);
        Bundle param = new Bundle();
        param.putInt(KEY_PARAM_TYPE, SysConst.VIRTUALINPUT_sensorChangedInput);
        param.putInt("sensortype", sensortype);
        param.putInt("accuracy", accuracy);
        param.putLong("timestemp", timestemp);
        param.putFloatArray("data", data);
        setData(param);
    }

    private void setData(Bundle param) {
        Bundle bundle = new Bundle();
        ApiUtil.setParams(bundle, SystemProviderDefines.METHOD_COMMON,
                SystemProviderDefines.COMMON_VIRTUAL_INPUT, param, true);
        ApiUtil.setData(bundle);
    }
}
