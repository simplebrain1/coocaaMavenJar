package com.tianci.system.callback;

/**
 * @author : whw
 * @Description :
 * @Date : 2021/10/9
 */
public interface ITapeLightUpgradeStatusChangeCallBack {
    /**
     * 升级失败
     * @param errorInfo 错误原因
     */
    void onFailure(String errorInfo);

    /**
     * 升级成功
     */
    void onSuccess(String msg);
}
