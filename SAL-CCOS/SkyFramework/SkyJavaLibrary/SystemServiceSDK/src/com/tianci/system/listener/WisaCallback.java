package com.tianci.system.listener;

public interface WisaCallback {

    int TYPE_LEFT = 0;
    int TYPE_RIGHT = 1;
    int TYPE_SUBWOOFER = 2;

    /**
     * 连接回调
     *
     * @param type 音箱类型
     */
    void onConnect(int type);

    /**
     * 异常回调
     *
     * @param   exceptionType    0转接板问题 1 wisa模块异常
     */
     void onException(int exceptionType);

    /**
     * 断连回调
     *
     * @param type
     */
    void onDisConnect(int type);
}
