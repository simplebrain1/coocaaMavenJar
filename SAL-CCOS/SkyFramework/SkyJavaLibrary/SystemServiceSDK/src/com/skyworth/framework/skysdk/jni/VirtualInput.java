package com.skyworth.framework.skysdk.jni;

import com.skyworth.framework.utils.internel.log.Logger;

/**
 * 虚拟键发送
 */
public class VirtualInput {
    static {
        try {
            System.loadLibrary("JLibVirtualInput");
        } catch (UnsatisfiedLinkError e) {
            Logger.e("VirtualInput", "VirtualInput loadLibrary error: " + e.getMessage());
            e.printStackTrace();
        }
    }

//    /*
//     *	修改open方法。
//     */
//    public boolean open(Context context)
//    {
//		return open(context.getPackageName());
//    }
    /**
     * Description： 打开输入设备 </br>
     *
     * @param device
     *            vKeyBoard 按键类输入(如鼠标、键盘、遥控器等), vTouch 触摸输入,sensor输入
     * @return boolean
     * @date 2013-10-15
     */
    /**
     * Description： 打开输入设备 </br>
     *
     * @param packagename 新框架下此处修改为使用该接口的应用程序包名。
     * @return boolean
     * @date 2015-3-30
     */
    public native boolean open(String packagename);

    /**
     * Remote Key Control
     *
     * @param keyCode
     */
    public native void pressKey(int keyCode);

    public native void releaseKey(int keyCode);

    public native void clickKey(int keyCode);

    /**
     * Mouse Control
     *
     * @param keyCode or coordinate
     */
    public native void clickButton(int btnCode);

    public native void buttonDown(int btnCode);

    public native void buttonUp(int btnCode);

    public native void rollMdlBtnUp();

    public native void rollMdlBtnDown();

    public native void moveMouse(float x, float y);

    public native void moveMouseTo(float x, float y);

    /**
     * Touch Control
     *
     * @param coordinate
     */
    public native void touch(float[] x, float[] y, int status);

    /**
     * Touch Control
     * touch_down
     * @param
     */
    public native void touchDown();

    /**
     * Touch Control
     * touch_up
     * @param
     */
    public native void touchUp();

    public native void close();

    /**
     * Sensor Control
     *
     * @param coordinate
     */
    public native void sensorChangedInput(int sensortype, int accuracy, long timestemp, float[] data);
}
