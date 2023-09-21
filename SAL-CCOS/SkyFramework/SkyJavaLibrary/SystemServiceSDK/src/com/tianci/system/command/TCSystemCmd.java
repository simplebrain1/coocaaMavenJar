/**
 * Copyright (C) 2012 The SkyTvOS Project
 * <p>
 * Version     Date           Author
 * ─────────────────────────────────────
 * 2013-11-22         xingkong207
 */

package com.tianci.system.command;

/**
 * <p>
 * Description:SystemService提供出去的命令列表
 * </p>
 * <p>
 * write something
 * </p>
 *
 * @ClassName TCSystemCmd
 * @author wei li
 * @date 2013-11-22
 * @version V1.0.0
 */
public enum TCSystemCmd {
    /**
     * Description:获取内容
     */
    TC_SYSTEM_CMD_GET_CONFIG,
    /**
     * Description:设置内容
     */
    TC_SYSTEM_CMD_SET_CONFIG, TC_SYSTEM_CMD_GET_ENV_CONFIG, TC_SYSTEM_CMD_SET_ENV_CONFIG,
    /**
     * Description:杀进程
     */
    TC_SYSTEM_CMD_KILL_PROCESSES,
    /**
     * Description:杀掉除了指定进程外的全部进程
     */
    TC_SYSTEM_CMD_KILL_ALL_EXECPT_PROCESSES,
    /**
     * Description:注册不要被杀掉的进程
     */
    TC_SYSTEM_CMD_REG_NOT_ALLOWED_KILLED,
    /**
     * Description:设置语言
     */
    TC_SYSTEM_CMD_SET_LANGUAGE,
    /**
     * Description:重启系统
     */
    TC_SYSTEM_CMD_REBOOT_SYSTEM,
    /**
     * Description:重启系统并擦出cache
     */
    TC_SYSTEM_CMD_REBOOT_WIP_USERDATA,
    /**
     * Description:清除SystemService缓存
     */
    TC_SYSTEM_CMD_RESET_SYSTEMSERVICE,
    /**
     * Description:处理其他模块停止运行的异常信息
     */
    TC_SYSTEM_CMD_PROCESS_UNCAUGHTEXCEPTION,
    /**
     * Description:push过来的命令获取log
     */
    TC_SYSTEM_CMD_PROCESS_GET_REALTIME_LOG,

    /**
     * Description:获取截图资源
     */
    @Deprecated
    TC_SYSTEM_CMD_REQUEST_SCREENSHOT,
    /**
     * Description:释放截图资源
     */
    @Deprecated
    TC_SYSTEM_CMD_RELEASE_SCREENSHOT,
    /**
     * Description:设置语言
     */
    TC_SYSTEM_CMD_SET_LANGUAGE_BY_LANGUAGE_AND_COUNTRY,
    /**
     * Description:同步后台时间到当前设备
     */
    TC_SYSTEM_CMD_SYNC_WEBSERVICE_SYSTEM_TIME,

    /**
     * Description:获取后门值开关
     */
    TC_SYSTEM_CMD_GET_BACKDOOR_VALUE,

    /**
     * Description:播放器解析库插件更新消息
     */
    TC_SYSTEM_CMD_PUSH_REFRESH_VIDEO_PLUGIN_SO,
    /**
     * Description:AD更新消息
     */
    TC_SYSTEM_CMD_PUSH_UPDATE_AD,
    /**
     * Description:显示酷开系统统一网络Mini toast
     */
    @Deprecated
    TC_SYSTEM_CMD_SHOW_MINI_TOAST,
    /**
     * Description:是否支持3D的命令
     */
    TC_SYSTEM_CMD_IS_SUPPORT_3D,

    /**
     * Description:获取自定义宽高的截图(含UI)
     */
    TC_SYSTEM_CMD_GET_SCREENSHOT_WITH_UI,
    /**
     * Description:获取自定义宽高的截图
     */
    TC_SYSTEM_CMD_GET_SCREENSHOT_CUSTOM_SIZE,
    /**
     * Description:获取当前电视视频层截图
     */
    TC_SYSTEM_CMD_GET_SCREENSHOT,
    /**
     * Description:
     */
    TC_SYSTEM_CMD_IS_SUPPORT_SCREENSHOT,

    /**
     * @Description 返回最接近的时间
     */
    TC_SYSTEM_CMD_RETURN_NEARLY_TIME,

    /**
     * @Description 接收应用启动
     */
    TC_SYSTEM_CMD_COLLECT_APP_START,
    /**
     * @Description 启动屏保
     */
    TC_SYSTEM_CMD_START_DREAM,
    /**
     * @Description 获取顶层应用信息
     */
    TC_SYSTEM_CMD_GET_SKY_USAGE_EVENT,
    /**
     *  本地校验激活码和mac
     */
    TC_SYSTEM_CMD_CHECK_CODE_MAC_LOCAL,
    /**
     *  注册关机事件
     */
    TC_SYSTEM_CMD_REG_POWER_TIME_EVENT,

    /**
     *  反注册关机事件
     */
    TC_SYSTEM_CMD_UN_REG_POWER_TIME_EVENT,
    /**
     *  获取系统会话id
     */
    TC_SYSTEM_CMD_GET_SYSTEM_SESSION_ID,

    /**
     * @Descrption:设置遥控按键一键启动程序索引
     */
    TC_SYSTEM_CMD_SET_ONE_KEY_ACTION_PROGRAM,
    /**
     * @Descrption:获取遥控按键一键启动程序索引
     */
    TC_SYSTEM_CMD_GET_ONE_KEY_ACTION_PROGRAM,
    /**
     * @Descrption:获取遥控器所有一键启动程序的列表
     */
    TC_SYSTEM_CMD_GET_ONE_KEY_ACTION_LIST,

    /**
     * 设置单独听模式
     */
    TC_SYSTEM_CMD_SET_AUDIO_ONLY_MODE,
    /**
     * AI待机开屏
     */
    TC_SYSTEM_CMD_SET_AI_SCREEN_MODE,
    /**
     * 语音控制logo
     */
    TC_SYSTEM_CMD_SET_VOICE_CONTROL_LOGO,
    /**
     * 广告调用关机接口
     */
    TC_SYSTEM_CMD_SET_AD_TO_STANDBY,
    /**
     * 红外学习回调失败
     */
    TC_SYSTEM_CMD_INFRARED_LEARNING_FAILED,
    /**
     * 红外学习回调成功
     */
    TC_SYSTEM_CMD_INFRARED_LEARNING_SUCCESS,
    /**
     * AI图像 智能化调节
     */
    TC_SYSTEM_CMD_PICTURE_AIPQ_TIPS,
    /**
     * AI声音 智能化调节
     */
    TC_SYSTEM_CMD_SOUND_AIPQ_TIPS,
    /**
     * AI图像声音 智能化调节
     */
    TC_SYSTEM_CMD_PICTURE_SOUND_AIPQ_TIPS,
    /**
     * 给第三方apk使用，用于旋转屏幕横竖屏的接口
     */
    TC_SYSTEM_CMD_MESH_FOR_ROTATE,

    /**
     * 获取应用使用时间
     */
    TC_SYSTEM_CMD_APP_USAGE,
    /**
     * 当前是否在单独听状态
     */
    TC_SYSTEM_CMD_IS_IN_AUDIO_ONLY_MODE,
}

