/**
 * Copyright (C) 2012 The SkyTvOS Project
 *
 * Version     Date           Author
 * ─────────────────────────────────────
 *           2013-8-1         xingkong207
 *
 */

package com.tianci.loader;

/**
 * <p>
 * Description:
 * </p>
 * <p>
 * write something
 * </p>
 * 
 * @ClassName SkyLoaderServiceDefs
 * @author wei li
 * @date 2013-8-1
 * @version V1.0.0
 */
public class SkyLoaderServiceDefs
{
    // public enum UpgradeToastEnum
    // {
    // /**
    // * Description:检测到新版本
    // */
    // NEW_VERSION,
    // /**
    // * Description: 下载完成
    // */
    // DOWNLOADED,
    // }

    public static final String SHOW_UPGRADE_FOCUS_TOAST = "show_upgrade_focus_toast";
    public static final String SHOW_UPGRADE_MINI_TOAST = "show_upgrade_mini_toast";
    public static final String SHOW_UPGRADE_UI_OPT = "show_upgrade_ui_oprate";
    public static final String SHOW_UPGRADE_MAIN_VIEW = "show_upgrade_main_view";
    // 定义启动升级主界面的Action
    public static final String ACTION_UPGRADE_MAIN = "com.tianci.system.loader.main_activity";
    public static final String ACTION_BC_LOADER_FLOAT_UI = "com.skyworth.broadcast.loader.float_ui";

    public enum SkyLoaderServiceCmdEnum
    {
        LOADERSERVICE_CMD_SHOW_ONLINE_UPGRADE_DIALOG, // 在线升级
        LOADERSERVICE_CMD_SHOW_LOCAL_UPGRADE_DIALOG, // 本地升级
        LOADERSERVICE_CMD_SHOW_EXTENDS_UPGRADE_DIALOG, // 系统扩展
        LOADERSERVICE_CMD_CLICK_TOAST,
        LOADERSERVICE_CMD_HAS_UPGRADE, // 存在在线升级包
        LOADERSERVICE_CMD_REG_UPGRADE_PACKAGE, // 注册待升级的包名
        LOADERSERVICE_CMD_PUSH_EPG_UPGRADE, // epg数据库更新
        LOADERSERVICE_CMD_PUSH_VIDEO_PLUGIN_UPGRADE, // 视频解析插件更新
        LOADERSERVICE_CMD_PUSH_AUDIO_PLUGIN_UPGRADE, // 音乐解析插件更新
        LOADERSERVICE_CMD_GET_DOWNLOAD_PERCENT, // 获取下载进度
        LOADERSERVICE_CMD_DOWNLOAD_PERCENT_BROADCAST, // 下载进度广播

        CMD_CHECK_UPGRADE, // 检查系统更新
        CMD_DELAY_PROMPT, // 延迟更新提示
        CMD_CLICK_BUTTON, // 升级界面按钮点击
        CMD_UPDATE_ACTIVITY, // 更新升级界面

        CMD_SET_UI_FOREGROUND, // 设置升级主界面是否前台显示
        CMD_GET_UI_VALUES, // 获取升级界面各项值
        CMD_START_INSTALL, // 开始安装更新（广播）
        CMD_ENTER_INSTALL_PROCESS, // 进入（Loader）统一升级入口
        CMD_STLOADER_UPGRADE_FROM_FACTORY,
    }

    public enum LoaderModule
    {
        EPG_DB, // epg 数据库
        VIDEO_PLUGIN, // 视频播放器插件升级
        AUDIO_PLUGIN, // 音乐播放插件升级
        SYSTEM, // 系统升级
        DTV, // DTV升级
        IPTV, // iptv升级
        APPSTORE, // 应用商场升级
        MODULE, // 模块升级
        PLUGIN, // plugin升级
    }

    public enum LoaderKeys
    {
        MODULE_TYPE, MODULE_NAME, MODULE_VERSION,
    }
}
