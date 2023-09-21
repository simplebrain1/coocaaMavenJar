package com.tianci.loader;

/**
 * <p>
 * Description: 系统升级界面类型定义
 * </p>
 * 
 * @ClassName LoaderViewStatus
 * @author wen
 * @date 2014年12月25日
 */
public enum LoaderViewStatus
{
    /**
     * Description: 当前已是最新版本界面
     */
    IDLE,
    /**
     * Description: 检查更新界面
     */
    CHECK,
    /**
     * Description: 有新的升级包界面
     */
    NEW,
    /**
     * Description: 下载中界面
     */
    DOWNLOAD,
    /**
     * Description: 恢复、重新下载界面
     */
    REDOWNLOAD,
    /**
     * Description: 下载完成，MD5校验中
     */
    MD5,
    /**
     * Description: MD5校验成，安装界面
     */
    INSTALL,
    /**
     * @Fields INSTALLED 安装完成界面，应用升级独有
     */
    @Deprecated
    INSTALLED,
    /**
     * @Fields REINSTALL 安装失败界面，应用升级独有
     */
    @Deprecated
    REINSTALL,
    /**
     * @Fields DIALOG 升级Dialog框，只是为了复用Button点击响应，不得复用UI
     */
    DIALOG,
    /**
     * @Fields CUR_INFO 当前版本系统功能介绍
     */
    CUR_INFO,
    /**
     * @Fields GET_CUR_INFO 获取当前版本系统功能介绍
     */
    GET_CUR_INFO,
    /**
     * @Fields CHECK_FAILED 检测升级失败（网络已连接的情况下）
     */
    CHECK_FAILED,
}
