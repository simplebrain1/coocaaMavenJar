package com.tianci.system.callback;

/**
 * 旋转挂架回调接口
 */
public class RotateHangerCallback {
    /**
     * 横竖屏切换开始
     */
    public void onScreenSwitchStart() {

    }

    /**
     * 横竖屏切换结束
     */
    public void onScreenSwitchEnd() {

    }

    /**
     * 横竖屏切换出错
     */
    public void onScreenSwitchError() {

    }

    /**
     * 挂架连接状态
     *
     * @param connect true:连接上；false：断开连接
     */
    public void onConnectChanged(boolean connect) {

    }
}
