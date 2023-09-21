package com.tianci.system.callback;

import com.tianci.system.data.SkyScreenshotRetData;

public interface ScreenShotCallback {
    /**
     * 截图结果回调
     *
     * @param data 为null表示截图失败，不为null则截图成功
     */
    void onScreenShotCallback(SkyScreenshotRetData data);
}
