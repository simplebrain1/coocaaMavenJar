package com.tianci.system.listener;

public interface HookFaceCallback {

    /*曲屏 0*/
    int TYPE_SCREENT = 0;
    /*底座 1*/
    int TYPE_PEDESTAL = 1;

    /*平直*/
    int TYPE_STRAIGHTNESS= 0;
    /*弯曲*/
    int TYPE_CURVE= 1;

    /*居中*/
    int TYPE_CENTER= 0;
    /*右极限*/
    int TYPE_RIGHT= 1;
    /*左极限*/
    int TYPE_LEFT= 2;
    /**
     * 障碍物
     *
     * @param type 底座还是曲屏  0:曲屏  1:底座
     */
    void onBarrierException(int type);


    /**
     * 电机异常
     * @param type  底座还是曲屏  0:曲屏  1:底座
     */
    void onMotorException(int type);


    /**
     * 系统是否繁忙
     * @param isBusy
     */
    void onSystemBusy(boolean isBusy);

    /**
     * 屏幕位置
     * @param type 平直:0 弯曲:1
     */
    void onScreenLocationMax(int type);

    /**
     * 底座位置
     * @param type 居中:0 右极限:1 左极限:2
     */
    void onPedestalLocationMax(int type);
}
